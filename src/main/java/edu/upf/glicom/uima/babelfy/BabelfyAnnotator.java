package edu.upf.glicom.uima.babelfy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.xml.sax.SAXException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import edu.upf.glicom.uima.types.BabelfyResource;
import edu.upf.glicom.uima.types.TopBabelfyResource;


/**
 * Wrapper for the DbpediaSpotlight Annotate Web Service. This annotator assumes that the
 * web service endpoint specified in the configuration has already been started.
 * 
 * The annotator has no input size limitation, 
 * however it assumes the input is structured as one sentence at a line.
 * This is not a strict requirement though,
 * the annotator would still work fine as long as there are no lines containing extra-long text.
 *   
 * @author Mustafa Nural
 */
public class BabelfyAnnotator extends JCasAnnotator_ImplBase {

	Logger LOG = Logger.getLogger(this.getClass());

	/**
	 * The endpoint for Spotlight Annotate service
	 */
	public static final String PARAM_ENDPOINT = "endPoint";
	@ConfigurationParameter(name=PARAM_ENDPOINT, defaultValue="https://babelfy.io/v1", description="The endpoint for Spotlight Annotate service")
	private String babelfy_url;

	public static final String PARAM_LANG = "lang";
	@ConfigurationParameter(name=PARAM_LANG, defaultValue="AGNOSTIC")
	private String lang;

	public static final String PARAM_KEY = "key";
	@ConfigurationParameter(name=PARAM_KEY, defaultValue="KEY")
	private String key;


	public static final String PARAM_CONFIDENCE = "confidence";
	@ConfigurationParameter(name=PARAM_CONFIDENCE, defaultValue="0.0")
	private double CONFIDENCE;
	public static final String PARAM_SUPPORT = "support";
	@ConfigurationParameter(name=PARAM_SUPPORT, defaultValue="0")
	private int SUPPORT;
	public static final String PARAM_TYPES = "types";
	@ConfigurationParameter(name=PARAM_TYPES, defaultValue="")
	private String TYPES;
	public static final String PARAM_COREFERENCE_RESOLUTION = "coferenceResolution";
	@ConfigurationParameter(name=PARAM_COREFERENCE_RESOLUTION, defaultValue="true")
	private boolean COREFERENCE_RESOLUTION;
	public static final String PARAM_SPOTTER = "spotter";
	@ConfigurationParameter(name=PARAM_SPOTTER, defaultValue="Default")
	private String SPOTTER;
	public static final String PARAM_DISAMBIGUATOR = "disambiguator";
	@ConfigurationParameter(name=PARAM_DISAMBIGUATOR, defaultValue="Default")
	private String DISAMBIGUATOR;
	public static final String PARAM_ALL_CANDIDATES = "allCandidates";
	@ConfigurationParameter(name=PARAM_ALL_CANDIDATES, defaultValue="false", description="output all candidate annotations")
	private boolean ALL_CANDIDATES;

	private final int BATCH_SIZE = 10; 

	public void process(JCas jCas) throws AnalysisEngineProcessException {
		String documentText = jCas.getDocumentText();

		// don't query endpoint without text
		if (StringUtils.isBlank(documentText)) {
			return;
		}

		Client c = Client.create();

		BufferedReader documentReader = new BufferedReader(new StringReader(documentText));
		//Send requests to the server by dividing the document into sentence chunks determined by BATCH_SIZE.
		int documentOffset = 0;
		int numLines = 0;
		boolean moreLines = true;
		while (moreLines){
			String request = "";
			for (int index = 0; index < BATCH_SIZE; index++) {
				String line = null;
				try {
					line = documentReader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					LOG.error("Can't read from input file",e);
				}
				if (line == null) {
					moreLines = false;
					break;
				}else if (index !=0){
					request += "\n";
				}
				request += line;
				numLines++;
			}
			if (StringUtils.isBlank(request)) {
				continue;
			}

			ClientResponse response = null;
			boolean retry = false;
			int retryCount = 0;
			do{
				try{

					LOG.info("Sending request to the server");
					WebResource r = 
							c.resource(babelfy_url+"/disambiguate")
							.queryParam("text", URLEncoder.encode(request, "UTF-8"))
							.queryParam("lang", this.lang)
							.queryParam("key", this.key)
							//.queryParam("annType", SPOTTER)
							//.queryParam("annRes", SPOTTER)
							//.queryParam("th", SPOTTER)
							//.queryParam("match", SPOTTER)
							//.queryParam("MCS", SPOTTER)
							//.queryParam("dens", SPOTTER)
							//.queryParam("cands", SPOTTER)
							//.queryParam("posTag", SPOTTER)
							//.queryParam("extAIDA", SPOTTER)
							;
					LOG.info(r.getURI());
					response =
							r.type("application/x-www-form-urlencoded;charset=UTF-8")
							.accept(MediaType.APPLICATION_JSON)
							.get(ClientResponse.class);
					retry = false;
				} catch (Exception e){
					//In case of a failure, try sending the request with a 2 second delay at least three times before throwing an exception
					LOG.error("Server request failed. Will try again in 2 seconds..", e);
					LOG.error("Failed request payload: " +request);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						LOG.error("Thread interrupted",e1);
					}
					if (retryCount++ < 3){
						retry = true;	
					} else {
						throw new AnalysisEngineProcessException("The server request failed", null);
					}
				}
			}while(retry);

			LOG.debug("Server request completed. Writing to the index");
			String jsonString = response.getEntity(String.class);
			JSONArray jsonOutput = (JSONArray) JSONValue.parse(jsonString); 

			/*
			 * create annotations
			 */
			//TODO: enable multiple candidates
			for (Object resource : jsonOutput) {
				BabelfyResource ann= new TopBabelfyResource(jCas);
				JSONObject res = (JSONObject) resource;
				JSONObject offsets = (JSONObject) res.get("charFragment");
				ann.setBegin(((Long) offsets.get("start")).intValue());
				ann.setEnd(((Long) offsets.get("end")).intValue()+1); // adjust offsets to Java style (open set)
				ann.setBabelSynsetID((String) res.get("babelSynsetID"));
				ann.setDBpediaURL((String) res.get("DBpediaURL"));
				ann.setBabelNetURL((String) res.get("BabelNetURL"));
				ann.setScore((Double) res.get("score"));
				ann.setCoherenceScore((Double) res.get("coherenceScore"));
				ann.setGlobalScore((Double) res.get("globalScore"));
				ann.setSource((String) res.get("source"));
				ann.addToIndexes();
				LOG.debug("surfaceForm: " + ann.getCoveredText());
			}
			documentOffset += request.length() + 1 ;
		}
		try {
			documentReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * return example descriptor (XML) when calling main method
	 * @param args not used
	 * @throws ResourceInitializationException
	 * @throws FileNotFoundException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ResourceInitializationException, FileNotFoundException, SAXException, IOException {
		AnalysisEngineFactory.createEngineDescription(BabelfyAnnotator.class).toXML(System.out);
	}
}
