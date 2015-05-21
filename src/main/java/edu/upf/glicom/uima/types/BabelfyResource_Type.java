
/* First created by JCasGen Wed May 20 13:33:26 CEST 2015 */
package edu.upf.glicom.uima.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Wed May 20 13:33:26 CEST 2015
 * @generated */
public class BabelfyResource_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (BabelfyResource_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = BabelfyResource_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new BabelfyResource(addr, BabelfyResource_Type.this);
  			   BabelfyResource_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new BabelfyResource(addr, BabelfyResource_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = BabelfyResource.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.upf.glicom.uima.types.BabelfyResource");
 
  /** @generated */
  final Feature casFeat_babelSynsetID;
  /** @generated */
  final int     casFeatCode_babelSynsetID;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getBabelSynsetID(int addr) {
        if (featOkTst && casFeat_babelSynsetID == null)
      jcas.throwFeatMissing("babelSynsetID", "edu.upf.glicom.uima.types.BabelfyResource");
    return ll_cas.ll_getStringValue(addr, casFeatCode_babelSynsetID);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setBabelSynsetID(int addr, String v) {
        if (featOkTst && casFeat_babelSynsetID == null)
      jcas.throwFeatMissing("babelSynsetID", "edu.upf.glicom.uima.types.BabelfyResource");
    ll_cas.ll_setStringValue(addr, casFeatCode_babelSynsetID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_DBpediaURL;
  /** @generated */
  final int     casFeatCode_DBpediaURL;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDBpediaURL(int addr) {
        if (featOkTst && casFeat_DBpediaURL == null)
      jcas.throwFeatMissing("DBpediaURL", "edu.upf.glicom.uima.types.BabelfyResource");
    return ll_cas.ll_getStringValue(addr, casFeatCode_DBpediaURL);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDBpediaURL(int addr, String v) {
        if (featOkTst && casFeat_DBpediaURL == null)
      jcas.throwFeatMissing("DBpediaURL", "edu.upf.glicom.uima.types.BabelfyResource");
    ll_cas.ll_setStringValue(addr, casFeatCode_DBpediaURL, v);}
    
  
 
  /** @generated */
  final Feature casFeat_BabelNetURL;
  /** @generated */
  final int     casFeatCode_BabelNetURL;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getBabelNetURL(int addr) {
        if (featOkTst && casFeat_BabelNetURL == null)
      jcas.throwFeatMissing("BabelNetURL", "edu.upf.glicom.uima.types.BabelfyResource");
    return ll_cas.ll_getStringValue(addr, casFeatCode_BabelNetURL);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setBabelNetURL(int addr, String v) {
        if (featOkTst && casFeat_BabelNetURL == null)
      jcas.throwFeatMissing("BabelNetURL", "edu.upf.glicom.uima.types.BabelfyResource");
    ll_cas.ll_setStringValue(addr, casFeatCode_BabelNetURL, v);}
    
  
 
  /** @generated */
  final Feature casFeat_score;
  /** @generated */
  final int     casFeatCode_score;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getScore(int addr) {
        if (featOkTst && casFeat_score == null)
      jcas.throwFeatMissing("score", "edu.upf.glicom.uima.types.BabelfyResource");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_score);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setScore(int addr, double v) {
        if (featOkTst && casFeat_score == null)
      jcas.throwFeatMissing("score", "edu.upf.glicom.uima.types.BabelfyResource");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_score, v);}
    
  
 
  /** @generated */
  final Feature casFeat_coherenceScore;
  /** @generated */
  final int     casFeatCode_coherenceScore;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getCoherenceScore(int addr) {
        if (featOkTst && casFeat_coherenceScore == null)
      jcas.throwFeatMissing("coherenceScore", "edu.upf.glicom.uima.types.BabelfyResource");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_coherenceScore);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCoherenceScore(int addr, double v) {
        if (featOkTst && casFeat_coherenceScore == null)
      jcas.throwFeatMissing("coherenceScore", "edu.upf.glicom.uima.types.BabelfyResource");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_coherenceScore, v);}
    
  
 
  /** @generated */
  final Feature casFeat_globalScore;
  /** @generated */
  final int     casFeatCode_globalScore;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getGlobalScore(int addr) {
        if (featOkTst && casFeat_globalScore == null)
      jcas.throwFeatMissing("globalScore", "edu.upf.glicom.uima.types.BabelfyResource");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_globalScore);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setGlobalScore(int addr, double v) {
        if (featOkTst && casFeat_globalScore == null)
      jcas.throwFeatMissing("globalScore", "edu.upf.glicom.uima.types.BabelfyResource");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_globalScore, v);}
    
  
 
  /** @generated */
  final Feature casFeat_source;
  /** @generated */
  final int     casFeatCode_source;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSource(int addr) {
        if (featOkTst && casFeat_source == null)
      jcas.throwFeatMissing("source", "edu.upf.glicom.uima.types.BabelfyResource");
    return ll_cas.ll_getStringValue(addr, casFeatCode_source);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSource(int addr, String v) {
        if (featOkTst && casFeat_source == null)
      jcas.throwFeatMissing("source", "edu.upf.glicom.uima.types.BabelfyResource");
    ll_cas.ll_setStringValue(addr, casFeatCode_source, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public BabelfyResource_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_babelSynsetID = jcas.getRequiredFeatureDE(casType, "babelSynsetID", "uima.cas.String", featOkTst);
    casFeatCode_babelSynsetID  = (null == casFeat_babelSynsetID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_babelSynsetID).getCode();

 
    casFeat_DBpediaURL = jcas.getRequiredFeatureDE(casType, "DBpediaURL", "uima.cas.String", featOkTst);
    casFeatCode_DBpediaURL  = (null == casFeat_DBpediaURL) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_DBpediaURL).getCode();

 
    casFeat_BabelNetURL = jcas.getRequiredFeatureDE(casType, "BabelNetURL", "uima.cas.String", featOkTst);
    casFeatCode_BabelNetURL  = (null == casFeat_BabelNetURL) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_BabelNetURL).getCode();

 
    casFeat_score = jcas.getRequiredFeatureDE(casType, "score", "uima.cas.Double", featOkTst);
    casFeatCode_score  = (null == casFeat_score) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_score).getCode();

 
    casFeat_coherenceScore = jcas.getRequiredFeatureDE(casType, "coherenceScore", "uima.cas.Double", featOkTst);
    casFeatCode_coherenceScore  = (null == casFeat_coherenceScore) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_coherenceScore).getCode();

 
    casFeat_globalScore = jcas.getRequiredFeatureDE(casType, "globalScore", "uima.cas.Double", featOkTst);
    casFeatCode_globalScore  = (null == casFeat_globalScore) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_globalScore).getCode();

 
    casFeat_source = jcas.getRequiredFeatureDE(casType, "source", "uima.cas.String", featOkTst);
    casFeatCode_source  = (null == casFeat_source) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_source).getCode();

  }
}



    