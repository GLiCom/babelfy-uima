package edu.upf.glicom.uima.babelfy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

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

import edu.upf.glicom.uima.types.BabelfyResource;
import edu.upf.glicom.uima.types.TopBabelfyResource;


/**
 * Wrapper for the BabelFy Web Service. This annotator assumes that the
 * web service endpoint specified in the configuration is running.
 * 
 * Long documents may be sent as several requests (because of limitations of HTTP GET
 * requests). The split is done at newline boundaries in chunks of BATCH_SIZE (default 10) lines.
 * If there are newlines within sentences it is possible that incomplete sentences
 * will be sent to the service. Also note the disambiguation is done per chunk, not
 * on the full document. 
 */
public class BabelfyAnnotator extends JCasAnnotator_ImplBase {

	Logger LOG = Logger.getLogger(this.getClass());

	/**
	 * The endpoint for Spotlight Annotate service
	 */
	public static final String PARAM_ENDPOINT = "endPoint";
	@ConfigurationParameter(name=PARAM_ENDPOINT, defaultValue="https://babelfy.io/v1", description="The endpoint for the babelfy service")
	private String babelfy_url;

	public static final String PARAM_LANG = "lang";
	@ConfigurationParameter(name=PARAM_LANG, defaultValue="AGNOSTIC")
	private String lang;

	public static final String PARAM_KEY = "key";
	@ConfigurationParameter(name=PARAM_KEY, defaultValue="KEY")
	private String key;

	private final int BATCH_SIZE = 10; 

	public void process(JCas jCas) throws AnalysisEngineProcessException {
		String documentText = jCas.getDocumentText();

		// don't query endpoint without text
		if (StringUtils.isBlank(documentText)) {
			return;
		}

		Client client = ClientBuilder.newClient();
		
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

			String response = null;
			boolean retry = false;
			int retryCount = 0;
			do{
				try{

					LOG.info("Sending request to the server");
					WebTarget target =
							client.target(babelfy_url+"/disambiguate")
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
					LOG.info(target.getUri());
					//response = target.request(MediaType.APPLICATION_JSON).get(ClientResponse.class);
					response = target.request(MediaType.APPLICATION_JSON).get(String.class);
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
			//String jsonString = response.readEntity(String.class);
			String jsonString = response;
			JSONArray jsonOutput = (JSONArray) JSONValue.parse(jsonString); 

			/*
			 * create annotations
			 */
			//TODO: enable multiple candidates
			for (Object resource : jsonOutput) {
				BabelfyResource ann= new TopBabelfyResource(jCas);
				JSONObject res = (JSONObject) resource;
				JSONObject offsets = (JSONObject) res.get("charFragment");
				ann.setBegin(((Long) offsets.get("start")).intValue() + documentOffset);
				ann.setEnd(((Long) offsets.get("end")).intValue()+1 + documentOffset); // adjust offsets to Java style (open set)
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
