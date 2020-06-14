public class Class5 {
	private Object SAX_FEATURE_PREFIX;
	private Object XERCES_FEATURE_PREFIX;

	public Class5(Object SAX_FEATURE_PREFIX, Object XERCES_FEATURE_PREFIX){
		this.SAX_FEATURE_PREFIX = SAX_FEATURE_PREFIX;
		this.XERCES_FEATURE_PREFIX = XERCES_FEATURE_PREFIX;
	}
	// It would be nice if we didn't have to repeat code like this, but there's no interface that has
// setFeature() and addRecognizedFeatures() that the objects have in common.
protected void copyFeatures(XMLComponentManager from, ParserConfigurationSettings to) {
    Enumeration features = Constants.getXercesFeatures();
    copyFeatures1(features, Constants.XERCES_FEATURE_PREFIX, from, to);
    features = Constants.getSAXFeatures();
    copyFeatures1(features, Constants.SAX_FEATURE_PREFIX, from, to);
}
	protected void copyFeatures(XMLComponentManager from, XMLParserConfiguration to) {
    Enumeration features = Constants.getXercesFeatures();
    copyFeatures1(features, Constants.XERCES_FEATURE_PREFIX, from, to);
    features = Constants.getSAXFeatures();
    copyFeatures1(features, Constants.SAX_FEATURE_PREFIX, from, to);
}
}