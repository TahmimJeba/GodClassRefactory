public class Class0 {
	private Object LOAD_DTD_GRAMMAR_FEATURE;
	private Object DEFAULT_ATTRIBUTE_VALUES_FEATURE;
	private Object DYNAMIC_VALIDATION_FEATURE;
	private Object DTD_SCANNER_PROPERTY;
	private Object XERCES_FEATURE_PREFIX;
	private Object VALIDATE_CONTENT_MODELS_FEATURE;
	private Object VALIDATE_DATATYPES_FEATURE;
	private Object LOAD_EXTERNAL_DTD_FEATURE;
	private Object SCHEMA_SOURCE;
	private Object JAXP_PROPERTY_PREFIX;
	private Object TRUE;
	private Object NOT_SUPPORTED;
	private Object XERCES_PROPERTY_PREFIX;

	public Class0(Object LOAD_DTD_GRAMMAR_FEATURE, Object DEFAULT_ATTRIBUTE_VALUES_FEATURE, Object DYNAMIC_VALIDATION_FEATURE, Object DTD_SCANNER_PROPERTY, Object XERCES_FEATURE_PREFIX, Object VALIDATE_CONTENT_MODELS_FEATURE, Object VALIDATE_DATATYPES_FEATURE, Object LOAD_EXTERNAL_DTD_FEATURE, Object SCHEMA_SOURCE, Object JAXP_PROPERTY_PREFIX, Object TRUE, Object NOT_SUPPORTED, Object XERCES_PROPERTY_PREFIX){
		this.LOAD_DTD_GRAMMAR_FEATURE = LOAD_DTD_GRAMMAR_FEATURE;
		this.DEFAULT_ATTRIBUTE_VALUES_FEATURE = DEFAULT_ATTRIBUTE_VALUES_FEATURE;
		this.DYNAMIC_VALIDATION_FEATURE = DYNAMIC_VALIDATION_FEATURE;
		this.DTD_SCANNER_PROPERTY = DTD_SCANNER_PROPERTY;
		this.XERCES_FEATURE_PREFIX = XERCES_FEATURE_PREFIX;
		this.VALIDATE_CONTENT_MODELS_FEATURE = VALIDATE_CONTENT_MODELS_FEATURE;
		this.VALIDATE_DATATYPES_FEATURE = VALIDATE_DATATYPES_FEATURE;
		this.LOAD_EXTERNAL_DTD_FEATURE = LOAD_EXTERNAL_DTD_FEATURE;
		this.SCHEMA_SOURCE = SCHEMA_SOURCE;
		this.JAXP_PROPERTY_PREFIX = JAXP_PROPERTY_PREFIX;
		this.TRUE = TRUE;
		this.NOT_SUPPORTED = NOT_SUPPORTED;
		this.XERCES_PROPERTY_PREFIX = XERCES_PROPERTY_PREFIX;
	}
	// setInputSource(XMLInputSource)
/**
     * Parses the document in a pull parsing fashion.
     *
     * @param complete True if the pull parser should parse the
     *                 remaining document completely.
     *
     * @return True if there is more document to parse.
     *
     * @exception XNIException Any XNI exception, possibly wrapping 
     *                         another exception.
     * @exception IOException  An IO exception from the parser, possibly
     *                         from a byte stream or character stream
     *                         supplied by the parser.
     *
     * @see #setInputSource
     */
public boolean parse(boolean complete) throws XNIException, IOException {
    // reset and configure pipeline and set InputSource.
    if (fInputSource != null) {
        try {
            // resets and sets the pipeline.
            reset();
            fScanner.setInputSource(fInputSource);
            fInputSource = null;
        } catch (XNIException ex) {
            if (PRINT_EXCEPTION_STACK_TRACE)
                ex.printStackTrace();
            throw ex;
        } catch (IOException ex) {
            if (PRINT_EXCEPTION_STACK_TRACE)
                ex.printStackTrace();
            throw ex;
        } catch (RuntimeException ex) {
            if (PRINT_EXCEPTION_STACK_TRACE)
                ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            if (PRINT_EXCEPTION_STACK_TRACE)
                ex.printStackTrace();
            throw new XNIException(ex);
        }
    }
    try {
        return fScanner.scanDocument(complete);
    } catch (XNIException ex) {
        if (PRINT_EXCEPTION_STACK_TRACE)
            ex.printStackTrace();
        throw ex;
    } catch (IOException ex) {
        if (PRINT_EXCEPTION_STACK_TRACE)
            ex.printStackTrace();
        throw ex;
    } catch (RuntimeException ex) {
        if (PRINT_EXCEPTION_STACK_TRACE)
            ex.printStackTrace();
        throw ex;
    } catch (Exception ex) {
        if (PRINT_EXCEPTION_STACK_TRACE)
            ex.printStackTrace();
        throw new XNIException(ex);
    }
}
	//
// XMLParserConfiguration methods
//
/**
     * Parses the specified input source.
     *
     * @param source The input source.
     *
     * @exception XNIException Throws exception on XNI error.
     * @exception java.io.IOException Throws exception on i/o error.
     */
public void parse(XMLInputSource source) throws XNIException, IOException {
    if (fParseInProgress) {
        // REVISIT - need to add new error message
        throw new XNIException("FWK005 parse may not be called while parsing.");
    }
    fParseInProgress = true;
    try {
        setInputSource(source);
        parse(true);
    } catch (XNIException ex) {
        if (PRINT_EXCEPTION_STACK_TRACE)
            ex.printStackTrace();
        throw ex;
    } catch (IOException ex) {
        if (PRINT_EXCEPTION_STACK_TRACE)
            ex.printStackTrace();
        throw ex;
    } catch (RuntimeException ex) {
        if (PRINT_EXCEPTION_STACK_TRACE)
            ex.printStackTrace();
        throw ex;
    } catch (Exception ex) {
        if (PRINT_EXCEPTION_STACK_TRACE)
            ex.printStackTrace();
        throw new XNIException(ex);
    } finally {
        fParseInProgress = false;
        // close all streams opened by xerces
        this.cleanup();
    }
}
	// getFeature(String):boolean
//
// XMLPullParserConfiguration methods
//
public boolean getFeature(String featureId) throws XMLConfigurationException {
    // make this feature special
    if (featureId.equals(PARSER_SETTINGS)) {
        return fConfigUpdated;
    }
    return super.getFeature(featureId);
}
	// parsing
/**
     * Sets the input source for the document to parse.
     *
     * @param inputSource The document's input source.
     *
     * @exception XMLConfigurationException Thrown if there is a 
     *                        configuration error when initializing the
     *                        parser.
     * @exception IOException Thrown on I/O error.
     *
     * @see #parse(boolean)
     */
public void setInputSource(XMLInputSource inputSource) throws XMLConfigurationException, IOException {
    // REVISIT: this method used to reset all the components and
    //          construct the pipeline. Now reset() is called
    //          in parse (boolean) just before we parse the document
    //          Should this method still throw exceptions..?
    fInputSource = inputSource;
}
	// parse(boolean):boolean
/**
     * If the application decides to terminate parsing before the xml document
     * is fully parsed, the application should call this method to free any
     * resource allocated during parsing. For example, close all opened streams.
     */
public void cleanup() {
    fEntityManager.closeReaders();
}
	// parse(InputSource)
//
// Protected methods
//
/** 
     * Reset all components before parsing. 
     *
     * @throws XNIException Thrown if an error occurs during initialization.
     */
protected void reset() throws XNIException {
    if (fValidationManager != null)
        fValidationManager.reset();
    // configure the pipeline and initialize the components
    configurePipeline();
    super.reset();
}
	// reset()
/** Configures the pipeline. */
protected void configurePipeline() {
    // and register it as one of the components.
    if (fFeatures.get(NAMESPACES) == Boolean.TRUE) {
        if (fNamespaceScanner == null) {
            fNamespaceScanner = new XMLNSDocumentScannerImpl();
            addComponent((XMLComponent) fNamespaceScanner);
        }
        fProperties.put(DOCUMENT_SCANNER, fNamespaceScanner);
        fNamespaceScanner.setDTDValidator(null);
        fScanner = fNamespaceScanner;
    } else {
        if (fNonNSScanner == null) {
            fNonNSScanner = new XMLDocumentScannerImpl();
            addComponent((XMLComponent) fNonNSScanner);
        }
        fProperties.put(DOCUMENT_SCANNER, fNonNSScanner);
        fScanner = fNonNSScanner;
    }
    fScanner.setDocumentHandler(fDocumentHandler);
    fLastComponent = fScanner;
    // setup dtd pipeline
    if (fDTDScanner != null) {
        fDTDScanner.setDTDHandler(fDTDHandler);
        fDTDScanner.setDTDContentModelHandler(fDTDContentModelHandler);
    }
}
	// configurePipeline()
// features and properties
/**
     * Check a feature. If feature is know and supported, this method simply
     * returns. Otherwise, the appropriate exception is thrown.
     *
     * @param featureId The unique identifier (URI) of the feature.
     *
     * @throws XMLConfigurationException Thrown for configuration error.
     *                                   In general, components should
     *                                   only throw this exception if
     *                                   it is <strong>really</strong>
     *                                   a critical error.
     */
protected void checkFeature(String featureId) throws XMLConfigurationException {
    if (featureId.startsWith(Constants.XERCES_FEATURE_PREFIX)) {
        final int suffixLength = featureId.length() - Constants.XERCES_FEATURE_PREFIX.length();
        //
        if (suffixLength == Constants.DYNAMIC_VALIDATION_FEATURE.length() && featureId.endsWith(Constants.DYNAMIC_VALIDATION_FEATURE)) {
            return;
        }
        //
        if (suffixLength == Constants.DEFAULT_ATTRIBUTE_VALUES_FEATURE.length() && featureId.endsWith(Constants.DEFAULT_ATTRIBUTE_VALUES_FEATURE)) {
            // REVISIT
            short type = XMLConfigurationException.NOT_SUPPORTED;
            throw new XMLConfigurationException(type, featureId);
        }
        //
        if (suffixLength == Constants.VALIDATE_CONTENT_MODELS_FEATURE.length() && featureId.endsWith(Constants.VALIDATE_CONTENT_MODELS_FEATURE)) {
            // REVISIT
            short type = XMLConfigurationException.NOT_SUPPORTED;
            throw new XMLConfigurationException(type, featureId);
        }
        //
        if (suffixLength == Constants.LOAD_DTD_GRAMMAR_FEATURE.length() && featureId.endsWith(Constants.LOAD_DTD_GRAMMAR_FEATURE)) {
            return;
        }
        //
        if (suffixLength == Constants.LOAD_EXTERNAL_DTD_FEATURE.length() && featureId.endsWith(Constants.LOAD_EXTERNAL_DTD_FEATURE)) {
            return;
        }
        //
        if (suffixLength == Constants.VALIDATE_DATATYPES_FEATURE.length() && featureId.endsWith(Constants.VALIDATE_DATATYPES_FEATURE)) {
            short type = XMLConfigurationException.NOT_SUPPORTED;
            throw new XMLConfigurationException(type, featureId);
        }
    }
    //
    // Not recognized
    //
    super.checkFeature(featureId);
}
	// checkFeature(String)
/**
     * Check a property. If the property is know and supported, this method
     * simply returns. Otherwise, the appropriate exception is thrown.
     *
     * @param propertyId The unique identifier (URI) of the property
     *                   being set.
     *
     * @throws XMLConfigurationException Thrown for configuration error.
     *                                   In general, components should
     *                                   only throw this exception if
     *                                   it is <strong>really</strong>
     *                                   a critical error.
     */
protected void checkProperty(String propertyId) throws XMLConfigurationException {
    if (propertyId.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
        final int suffixLength = propertyId.length() - Constants.XERCES_PROPERTY_PREFIX.length();
        if (suffixLength == Constants.DTD_SCANNER_PROPERTY.length() && propertyId.endsWith(Constants.DTD_SCANNER_PROPERTY)) {
            return;
        }
    }
    if (propertyId.startsWith(Constants.JAXP_PROPERTY_PREFIX)) {
        final int suffixLength = propertyId.length() - Constants.JAXP_PROPERTY_PREFIX.length();
        if (suffixLength == Constants.SCHEMA_SOURCE.length() && propertyId.endsWith(Constants.SCHEMA_SOURCE)) {
            return;
        }
    }
    //
    // Not recognized
    //
    super.checkProperty(propertyId);
}
	// createErrorReporter():XMLErrorReporter
/** Create a document scanner. */
protected XMLDocumentScanner createDocumentScanner() {
    return null;
}
	// createDocumentScanner():XMLDocumentScanner
/** Create a DTD scanner. */
protected XMLDTDScanner createDTDScanner() {
    return new XMLDTDScannerImpl();
}
	// createDTDScanner():XMLDTDScanner
/** Create a datatype validator factory. */
protected DTDDVFactory createDatatypeValidatorFactory() {
    return DTDDVFactory.getInstance();
}
	// <init>(SymbolTable,XMLGrammarPool)
//
// Public methods
//
public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
    fConfigUpdated = true;
    super.setFeature(featureId, state);
}
	//
public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
    fConfigUpdated = true;
    super.setProperty(propertyId, value);
}
	/**
     * Set the locale to use for messages.
     *
     * @param locale The locale object to use for localization of messages.
     *
     * @exception XNIException Thrown if the parser does not support the
     *                         specified locale.
     */
// setLocale(Locale)
public void setLocale(Locale locale) throws XNIException {
    super.setLocale(locale);
    fErrorReporter.setLocale(locale);
}
	// createDatatypeValidatorFactory():DatatypeValidatorFactory
protected ValidationManager createValidationManager() {
    return new ValidationManager();
}
}