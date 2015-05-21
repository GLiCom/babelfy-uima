

/* First created by JCasGen Wed May 20 13:33:26 CEST 2015 */
package edu.upf.glicom.uima.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed May 20 13:33:26 CEST 2015
 * XML source: /home/jgrivolla/workspace/babelfy-uima/src/main/resources/edu/upf/glicom/uima/babelfy/types/BabelfyTypeSystemDescriptor.xml
 * @generated */
public class BabelfyResource extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(BabelfyResource.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected BabelfyResource() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public BabelfyResource(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public BabelfyResource(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public BabelfyResource(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: babelSynsetID

  /** getter for babelSynsetID - gets 
   * @generated
   * @return value of the feature 
   */
  public String getBabelSynsetID() {
    if (BabelfyResource_Type.featOkTst && ((BabelfyResource_Type)jcasType).casFeat_babelSynsetID == null)
      jcasType.jcas.throwFeatMissing("babelSynsetID", "edu.upf.glicom.uima.types.BabelfyResource");
    return jcasType.ll_cas.ll_getStringValue(addr, ((BabelfyResource_Type)jcasType).casFeatCode_babelSynsetID);}
    
  /** setter for babelSynsetID - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setBabelSynsetID(String v) {
    if (BabelfyResource_Type.featOkTst && ((BabelfyResource_Type)jcasType).casFeat_babelSynsetID == null)
      jcasType.jcas.throwFeatMissing("babelSynsetID", "edu.upf.glicom.uima.types.BabelfyResource");
    jcasType.ll_cas.ll_setStringValue(addr, ((BabelfyResource_Type)jcasType).casFeatCode_babelSynsetID, v);}    
   
    
  //*--------------*
  //* Feature: DBpediaURL

  /** getter for DBpediaURL - gets 
   * @generated
   * @return value of the feature 
   */
  public String getDBpediaURL() {
    if (BabelfyResource_Type.featOkTst && ((BabelfyResource_Type)jcasType).casFeat_DBpediaURL == null)
      jcasType.jcas.throwFeatMissing("DBpediaURL", "edu.upf.glicom.uima.types.BabelfyResource");
    return jcasType.ll_cas.ll_getStringValue(addr, ((BabelfyResource_Type)jcasType).casFeatCode_DBpediaURL);}
    
  /** setter for DBpediaURL - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDBpediaURL(String v) {
    if (BabelfyResource_Type.featOkTst && ((BabelfyResource_Type)jcasType).casFeat_DBpediaURL == null)
      jcasType.jcas.throwFeatMissing("DBpediaURL", "edu.upf.glicom.uima.types.BabelfyResource");
    jcasType.ll_cas.ll_setStringValue(addr, ((BabelfyResource_Type)jcasType).casFeatCode_DBpediaURL, v);}    
   
    
  //*--------------*
  //* Feature: BabelNetURL

  /** getter for BabelNetURL - gets 
   * @generated
   * @return value of the feature 
   */
  public String getBabelNetURL() {
    if (BabelfyResource_Type.featOkTst && ((BabelfyResource_Type)jcasType).casFeat_BabelNetURL == null)
      jcasType.jcas.throwFeatMissing("BabelNetURL", "edu.upf.glicom.uima.types.BabelfyResource");
    return jcasType.ll_cas.ll_getStringValue(addr, ((BabelfyResource_Type)jcasType).casFeatCode_BabelNetURL);}
    
  /** setter for BabelNetURL - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setBabelNetURL(String v) {
    if (BabelfyResource_Type.featOkTst && ((BabelfyResource_Type)jcasType).casFeat_BabelNetURL == null)
      jcasType.jcas.throwFeatMissing("BabelNetURL", "edu.upf.glicom.uima.types.BabelfyResource");
    jcasType.ll_cas.ll_setStringValue(addr, ((BabelfyResource_Type)jcasType).casFeatCode_BabelNetURL, v);}    
   
    
  //*--------------*
  //* Feature: score

  /** getter for score - gets 
   * @generated
   * @return value of the feature 
   */
  public double getScore() {
    if (BabelfyResource_Type.featOkTst && ((BabelfyResource_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "edu.upf.glicom.uima.types.BabelfyResource");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((BabelfyResource_Type)jcasType).casFeatCode_score);}
    
  /** setter for score - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setScore(double v) {
    if (BabelfyResource_Type.featOkTst && ((BabelfyResource_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "edu.upf.glicom.uima.types.BabelfyResource");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((BabelfyResource_Type)jcasType).casFeatCode_score, v);}    
   
    
  //*--------------*
  //* Feature: coherenceScore

  /** getter for coherenceScore - gets 
   * @generated
   * @return value of the feature 
   */
  public double getCoherenceScore() {
    if (BabelfyResource_Type.featOkTst && ((BabelfyResource_Type)jcasType).casFeat_coherenceScore == null)
      jcasType.jcas.throwFeatMissing("coherenceScore", "edu.upf.glicom.uima.types.BabelfyResource");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((BabelfyResource_Type)jcasType).casFeatCode_coherenceScore);}
    
  /** setter for coherenceScore - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCoherenceScore(double v) {
    if (BabelfyResource_Type.featOkTst && ((BabelfyResource_Type)jcasType).casFeat_coherenceScore == null)
      jcasType.jcas.throwFeatMissing("coherenceScore", "edu.upf.glicom.uima.types.BabelfyResource");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((BabelfyResource_Type)jcasType).casFeatCode_coherenceScore, v);}    
   
    
  //*--------------*
  //* Feature: globalScore

  /** getter for globalScore - gets 
   * @generated
   * @return value of the feature 
   */
  public double getGlobalScore() {
    if (BabelfyResource_Type.featOkTst && ((BabelfyResource_Type)jcasType).casFeat_globalScore == null)
      jcasType.jcas.throwFeatMissing("globalScore", "edu.upf.glicom.uima.types.BabelfyResource");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((BabelfyResource_Type)jcasType).casFeatCode_globalScore);}
    
  /** setter for globalScore - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setGlobalScore(double v) {
    if (BabelfyResource_Type.featOkTst && ((BabelfyResource_Type)jcasType).casFeat_globalScore == null)
      jcasType.jcas.throwFeatMissing("globalScore", "edu.upf.glicom.uima.types.BabelfyResource");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((BabelfyResource_Type)jcasType).casFeatCode_globalScore, v);}    
   
    
  //*--------------*
  //* Feature: source

  /** getter for source - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSource() {
    if (BabelfyResource_Type.featOkTst && ((BabelfyResource_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "edu.upf.glicom.uima.types.BabelfyResource");
    return jcasType.ll_cas.ll_getStringValue(addr, ((BabelfyResource_Type)jcasType).casFeatCode_source);}
    
  /** setter for source - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSource(String v) {
    if (BabelfyResource_Type.featOkTst && ((BabelfyResource_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "edu.upf.glicom.uima.types.BabelfyResource");
    jcasType.ll_cas.ll_setStringValue(addr, ((BabelfyResource_Type)jcasType).casFeatCode_source, v);}    
  }

    