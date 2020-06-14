public class Class4 {
	private Object length;

	public Class4(Object length){
		this.length = length;
	}
	// getRecognizedFeatures():String[]
/**
     * Sets the state of a feature. This method is called by the component
     * manager any time after reset when a feature changes state.
     * <p>
     * <strong>Note:</strong> Components should silently ignore features
     * that do not affect the operation of the component.
     *
     * @param featureId The feature identifier.
     * @param state     The state of the feature.
     *
     * @throws SAXNotRecognizedException The component should not throw
     *                                   this exception.
     * @throws SAXNotSupportedException The component should not throw
     *                                  this exception.
     */
public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
    if (featureId.equals(ALLOW_UE_AND_NOTATION_EVENTS)) {
        fSendUEAndNotationEvents = state;
    }
    if (fSettings != null) {
        fNeedCopyFeatures = true;
        fSettings.setFeature(featureId, state);
    }
}
	// setProperty(String,Object)
/**
     * Returns the default state for a feature, or null if this
     * component does not want to report a default value for this
     * feature.
     *
     * @param featureId The feature identifier.
     *
     * @since Xerces 2.2.0
     */
public Boolean getFeatureDefault(String featureId) {
    for (int i = 0; i < RECOGNIZED_FEATURES.length; i++) {
        if (RECOGNIZED_FEATURES[i].equals(featureId)) {
            return FEATURE_DEFAULTS[i];
        }
    }
    return null;
}
}