public class Class0 {
	private Object SCHEMA_LANGUAGE;
	private Object fType;
	private Object SCHEMA_SOURCE;
	private Object JAXP_PROPERTY_PREFIX;
	private Object fMessage;
	private Object DOM_CHARSET_OVERRIDES_XML_ENCODING;
	private Object DOM_NORMALIZE_CHARACTERS;
	private Object DOM_RESOURCE_RESOLVER;
	private Object INVALID_STATE_ERR;
	private Object DOM_SCHEMA_LOCATION;
	private Object DOM_CHECK_CHAR_NORMALIZATION;
	private Object DOM_PSVI;
	private Object DOM_ERROR_HANDLER;
	private Object DOM_SUPPORTED_MEDIATYPES_ONLY;
	private Object ENGLISH;
	private Object DOM_DATATYPE_NORMALIZATION;
	private Object DOM_SPLIT_CDATA;
	private Object PARSE_ERR;
	private Object SEVERITY_FATAL_ERROR;
	private Object XERCES_FEATURE_PREFIX;
	private Object fException;
	private Object DOM_COMMENTS;
	private Object DOM_SCHEMA_TYPE;
	private Object TYPE_MISMATCH_ERR;
	private Object DOM_NAMESPACE_DECLARATIONS;
	private Object DOM_ELEMENT_CONTENT_WHITESPACE;
	private Object DOM_ENTITIES;
	private Object TRUE;
	private Object DOM_VALIDATE_IF_SCHEMA;
	private Object fSeverity;
	private Object DOM_VALIDATE;
	private Object DOM_CANONICAL_FORM;
	private Object DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS;
	private Object NS_XMLSCHEMA;
	private Object DOM_DOMAIN;
	private Object DOM_CDATA_SECTIONS;
	private Object DOM_WELLFORMED;
	private Object DOM_NAMESPACES;
	private Object NS_DTD;
	private Object NOT_SUPPORTED_ERR;
	private Object DOM_DISALLOW_DOCTYPE;
	private Object NOT_FOUND_ERR;
	private Object SCHEMA_VALIDATION_FEATURE;
	private Object DOM_INFOSET;
	private Object FALSE;

	public Class0(Object SCHEMA_LANGUAGE, Object fType, Object SCHEMA_SOURCE, Object JAXP_PROPERTY_PREFIX, Object fMessage, Object DOM_CHARSET_OVERRIDES_XML_ENCODING, Object DOM_NORMALIZE_CHARACTERS, Object DOM_RESOURCE_RESOLVER, Object INVALID_STATE_ERR, Object DOM_SCHEMA_LOCATION, Object DOM_CHECK_CHAR_NORMALIZATION, Object DOM_PSVI, Object DOM_ERROR_HANDLER, Object DOM_SUPPORTED_MEDIATYPES_ONLY, Object ENGLISH, Object DOM_DATATYPE_NORMALIZATION, Object DOM_SPLIT_CDATA, Object PARSE_ERR, Object SEVERITY_FATAL_ERROR, Object XERCES_FEATURE_PREFIX, Object fException, Object DOM_COMMENTS, Object DOM_SCHEMA_TYPE, Object TYPE_MISMATCH_ERR, Object DOM_NAMESPACE_DECLARATIONS, Object DOM_ELEMENT_CONTENT_WHITESPACE, Object DOM_ENTITIES, Object TRUE, Object DOM_VALIDATE_IF_SCHEMA, Object fSeverity, Object DOM_VALIDATE, Object DOM_CANONICAL_FORM, Object DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS, Object NS_XMLSCHEMA, Object DOM_DOMAIN, Object DOM_CDATA_SECTIONS, Object DOM_WELLFORMED, Object DOM_NAMESPACES, Object NS_DTD, Object NOT_SUPPORTED_ERR, Object DOM_DISALLOW_DOCTYPE, Object NOT_FOUND_ERR, Object SCHEMA_VALIDATION_FEATURE, Object DOM_INFOSET, Object FALSE){
		this.SCHEMA_LANGUAGE = SCHEMA_LANGUAGE;
		this.fType = fType;
		this.SCHEMA_SOURCE = SCHEMA_SOURCE;
		this.JAXP_PROPERTY_PREFIX = JAXP_PROPERTY_PREFIX;
		this.fMessage = fMessage;
		this.DOM_CHARSET_OVERRIDES_XML_ENCODING = DOM_CHARSET_OVERRIDES_XML_ENCODING;
		this.DOM_NORMALIZE_CHARACTERS = DOM_NORMALIZE_CHARACTERS;
		this.DOM_RESOURCE_RESOLVER = DOM_RESOURCE_RESOLVER;
		this.INVALID_STATE_ERR = INVALID_STATE_ERR;
		this.DOM_SCHEMA_LOCATION = DOM_SCHEMA_LOCATION;
		this.DOM_CHECK_CHAR_NORMALIZATION = DOM_CHECK_CHAR_NORMALIZATION;
		this.DOM_PSVI = DOM_PSVI;
		this.DOM_ERROR_HANDLER = DOM_ERROR_HANDLER;
		this.DOM_SUPPORTED_MEDIATYPES_ONLY = DOM_SUPPORTED_MEDIATYPES_ONLY;
		this.ENGLISH = ENGLISH;
		this.DOM_DATATYPE_NORMALIZATION = DOM_DATATYPE_NORMALIZATION;
		this.DOM_SPLIT_CDATA = DOM_SPLIT_CDATA;
		this.PARSE_ERR = PARSE_ERR;
		this.SEVERITY_FATAL_ERROR = SEVERITY_FATAL_ERROR;
		this.XERCES_FEATURE_PREFIX = XERCES_FEATURE_PREFIX;
		this.fException = fException;
		this.DOM_COMMENTS = DOM_COMMENTS;
		this.DOM_SCHEMA_TYPE = DOM_SCHEMA_TYPE;
		this.TYPE_MISMATCH_ERR = TYPE_MISMATCH_ERR;
		this.DOM_NAMESPACE_DECLARATIONS = DOM_NAMESPACE_DECLARATIONS;
		this.DOM_ELEMENT_CONTENT_WHITESPACE = DOM_ELEMENT_CONTENT_WHITESPACE;
		this.DOM_ENTITIES = DOM_ENTITIES;
		this.TRUE = TRUE;
		this.DOM_VALIDATE_IF_SCHEMA = DOM_VALIDATE_IF_SCHEMA;
		this.fSeverity = fSeverity;
		this.DOM_VALIDATE = DOM_VALIDATE;
		this.DOM_CANONICAL_FORM = DOM_CANONICAL_FORM;
		this.DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS = DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS;
		this.NS_XMLSCHEMA = NS_XMLSCHEMA;
		this.DOM_DOMAIN = DOM_DOMAIN;
		this.DOM_CDATA_SECTIONS = DOM_CDATA_SECTIONS;
		this.DOM_WELLFORMED = DOM_WELLFORMED;
		this.DOM_NAMESPACES = DOM_NAMESPACES;
		this.NS_DTD = NS_DTD;
		this.NOT_SUPPORTED_ERR = NOT_SUPPORTED_ERR;
		this.DOM_DISALLOW_DOCTYPE = DOM_DISALLOW_DOCTYPE;
		this.NOT_FOUND_ERR = NOT_FOUND_ERR;
		this.SCHEMA_VALIDATION_FEATURE = SCHEMA_VALIDATION_FEATURE;
		this.DOM_INFOSET = DOM_INFOSET;
		this.FALSE = FALSE;
	}
	/**
     * Parse an XML document from a location identified by an URI reference.
     * If the URI contains a fragment identifier (see section 4.1 in ), the
     * behavior is not defined by this specification.
     *
     */
public Document parseURI(String uri) throws LSException {
    // method is called, then raise INVALID_STATE_ERR according to DOM L3 LS spec
    if (fBusy) {
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null);
        throw new DOMException(DOMException.INVALID_STATE_ERR, msg);
    }
    XMLInputSource source = new XMLInputSource(null, uri, null);
    try {
        currentThread = Thread.currentThread();
        fBusy = true;
        parse(source);
        fBusy = false;
        if (abortNow && currentThread.isInterrupted()) {
            //reset interrupt state 
            abortNow = false;
            Thread.interrupted();
        }
    } catch (Exception e) {
        fBusy = false;
        if (abortNow && currentThread.isInterrupted()) {
            Thread.interrupted();
        }
        if (abortNow) {
            abortNow = false;
            restoreHandlers();
            return null;
        }
        // issued an interrupt or an abort.
        if (e != abort) {
            if (!(e instanceof XMLParseException) && fErrorHandler != null) {
                DOMErrorImpl error = new DOMErrorImpl();
                error.fException = e;
                error.fMessage = e.getMessage();
                error.fSeverity = DOMError.SEVERITY_FATAL_ERROR;
                fErrorHandler.getErrorHandler().handleError(error);
            }
            if (DEBUG) {
                e.printStackTrace();
            }
            throw new LSException(LSException.PARSE_ERR, e.getMessage());
        }
    }
    return getDocument();
}
	/**
     * Parse an XML document from a resource identified by an
     * <code>LSInput</code>.
     *
     */
public Document parse(LSInput is) throws LSException {
    // need to wrap the LSInput with an XMLInputSource
    XMLInputSource xmlInputSource = dom2xmlInputSource(is);
    if (fBusy) {
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null);
        throw new DOMException(DOMException.INVALID_STATE_ERR, msg);
    }
    try {
        currentThread = Thread.currentThread();
        fBusy = true;
        parse(xmlInputSource);
        fBusy = false;
        if (abortNow && currentThread.isInterrupted()) {
            //reset interrupt state 
            abortNow = false;
            Thread.interrupted();
        }
    } catch (Exception e) {
        fBusy = false;
        if (abortNow && currentThread.isInterrupted()) {
            Thread.interrupted();
        }
        if (abortNow) {
            abortNow = false;
            restoreHandlers();
            return null;
        }
        // issued an interrupt or an abort.
        if (e != abort) {
            if (!(e instanceof XMLParseException) && fErrorHandler != null) {
                DOMErrorImpl error = new DOMErrorImpl();
                error.fException = e;
                error.fMessage = e.getMessage();
                error.fSeverity = DOMError.SEVERITY_FATAL_ERROR;
                fErrorHandler.getErrorHandler().handleError(error);
            }
            if (DEBUG) {
                e.printStackTrace();
            }
            throw new LSException(LSException.PARSE_ERR, e.getMessage());
        }
    }
    return getDocument();
}
	/**
     * @see org.w3c.dom.ls.DOMParser#abort()
     */
public void abort() {
    // If parse operation is in progress then reset it
    if (fBusy) {
        fBusy = false;
        if (currentThread != null) {
            abortNow = true;
            fConfiguration.setDocumentHandler(abortHandler);
            fConfiguration.setDTDHandler(abortHandler);
            fConfiguration.setDTDContentModelHandler(abortHandler);
            if (currentThread == Thread.currentThread())
                throw abort;
            currentThread.interrupt();
        }
    }
    // If not busy then this is noop
    return;
}
	/**
     * Resets the parser state.
     *
     * @throws SAXException Thrown on initialization error.
     */
public void reset() {
    super.reset();
    // get state of namespace-declarations parameter.
    fNamespaceDeclarations = fConfiguration.getFeature(Constants.DOM_NAMESPACE_DECLARATIONS);
    // DOM Filter
    if (fSkippedElemStack != null) {
        fSkippedElemStack.removeAllElements();
    }
    fSchemaLocations.clear();
    fRejectedElement.clear();
    fFilterReject = false;
    fSchemaType = null;
}
	/**
     *  When the application provides a filter, the parser will call out to
     * the filter at the completion of the construction of each
     * <code>Element</code> node. The filter implementation can choose to
     * remove the element from the document being constructed (unless the
     * element is the document element) or to terminate the parse early. If
     * the document is being validated when it's loaded the validation
     * happens before the filter is called.
     */
public LSParserFilter getFilter() {
    return fDOMFilter;
}
	/**
     *  When the application provides a filter, the parser will call out to
     * the filter at the completion of the construction of each
     * <code>Element</code> node. The filter implementation can choose to
     * remove the element from the document being constructed (unless the
     * element is the document element) or to terminate the parse early. If
     * the document is being validated when it's loaded the validation
     * happens before the filter is called.
     */
public void setFilter(LSParserFilter filter) {
    fDOMFilter = filter;
    if (fSkippedElemStack == null) {
        fSkippedElemStack = new Stack();
    }
}
	/**
     * Set parameters and properties
     */
public void setParameter(String name, Object value) throws DOMException {
    if (value instanceof Boolean) {
        boolean state = ((Boolean) value).booleanValue();
        try {
            if (name.equalsIgnoreCase(Constants.DOM_COMMENTS)) {
                fConfiguration.setFeature(INCLUDE_COMMENTS_FEATURE, state);
            } else if (name.equalsIgnoreCase(Constants.DOM_DATATYPE_NORMALIZATION)) {
                fConfiguration.setFeature(NORMALIZE_DATA, state);
            } else if (name.equalsIgnoreCase(Constants.DOM_ENTITIES)) {
                fConfiguration.setFeature(CREATE_ENTITY_REF_NODES, state);
            } else if (name.equalsIgnoreCase(Constants.DOM_DISALLOW_DOCTYPE)) {
                fConfiguration.setFeature(DISALLOW_DOCTYPE_DECL_FEATURE, state);
            } else if (name.equalsIgnoreCase(Constants.DOM_SUPPORTED_MEDIATYPES_ONLY) || name.equalsIgnoreCase(Constants.DOM_NORMALIZE_CHARACTERS) || name.equalsIgnoreCase(Constants.DOM_CHECK_CHAR_NORMALIZATION) || name.equalsIgnoreCase(Constants.DOM_CANONICAL_FORM)) {
                if (state) {
                    // true is not supported
                    String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[] { name });
                    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, msg);
                }
            // setting those features to false is no-op
            } else if (name.equalsIgnoreCase(Constants.DOM_NAMESPACES)) {
                fConfiguration.setFeature(NAMESPACES, state);
            } else if (name.equalsIgnoreCase(Constants.DOM_INFOSET)) {
                // Setting false has no effect.
                if (state) {
                    // true: namespaces, namespace-declarations, 
                    // comments, element-content-whitespace
                    fConfiguration.setFeature(NAMESPACES, true);
                    fConfiguration.setFeature(Constants.DOM_NAMESPACE_DECLARATIONS, true);
                    fConfiguration.setFeature(INCLUDE_COMMENTS_FEATURE, true);
                    fConfiguration.setFeature(INCLUDE_IGNORABLE_WHITESPACE, true);
                    // false: validate-if-schema, entities,
                    // datatype-normalization, cdata-sections
                    fConfiguration.setFeature(DYNAMIC_VALIDATION, false);
                    fConfiguration.setFeature(CREATE_ENTITY_REF_NODES, false);
                    fConfiguration.setFeature(NORMALIZE_DATA, false);
                    fConfiguration.setFeature(CREATE_CDATA_NODES_FEATURE, false);
                }
            } else if (name.equalsIgnoreCase(Constants.DOM_CDATA_SECTIONS)) {
                fConfiguration.setFeature(CREATE_CDATA_NODES_FEATURE, state);
            } else if (name.equalsIgnoreCase(Constants.DOM_NAMESPACE_DECLARATIONS)) {
                fConfiguration.setFeature(Constants.DOM_NAMESPACE_DECLARATIONS, state);
            } else if (name.equalsIgnoreCase(Constants.DOM_WELLFORMED) || name.equalsIgnoreCase(Constants.DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS)) {
                if (!state) {
                    // false is not supported
                    String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[] { name });
                    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, msg);
                }
            // setting these features to true is no-op
            // REVISIT: implement "namespace-declaration" feature
            } else if (name.equalsIgnoreCase(Constants.DOM_VALIDATE)) {
                fConfiguration.setFeature(VALIDATION_FEATURE, state);
                if (fSchemaType != Constants.NS_DTD) {
                    fConfiguration.setFeature(XMLSCHEMA, state);
                }
                if (state) {
                    fConfiguration.setFeature(DYNAMIC_VALIDATION, false);
                }
            } else if (name.equalsIgnoreCase(Constants.DOM_VALIDATE_IF_SCHEMA)) {
                fConfiguration.setFeature(DYNAMIC_VALIDATION, state);
                // Note: validation and dynamic validation are mutually exclusive
                if (state) {
                    fConfiguration.setFeature(VALIDATION_FEATURE, false);
                }
            } else if (name.equalsIgnoreCase(Constants.DOM_ELEMENT_CONTENT_WHITESPACE)) {
                fConfiguration.setFeature(INCLUDE_IGNORABLE_WHITESPACE, state);
            } else if (name.equalsIgnoreCase(Constants.DOM_PSVI)) {
                //XSModel - turn on PSVI augmentation
                fConfiguration.setFeature(PSVI_AUGMENT, true);
                fConfiguration.setProperty(DOCUMENT_CLASS_NAME, "org.apache.xerces.dom.PSVIDocumentImpl");
            } else {
                // Constants.DOM_CHARSET_OVERRIDES_XML_ENCODING feature,
                // Constants.DOM_SPLIT_CDATA feature,
                // or any Xerces feature
                fConfiguration.setFeature(name.toLowerCase(Locale.ENGLISH), state);
            }
        } catch (XMLConfigurationException e) {
            String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_FOUND", new Object[] { name });
            throw new DOMException(DOMException.NOT_FOUND_ERR, msg);
        }
    } else {
        // set properties
        if (name.equalsIgnoreCase(Constants.DOM_ERROR_HANDLER)) {
            if (value instanceof DOMErrorHandler || value == null) {
                try {
                    fErrorHandler = new DOMErrorHandlerWrapper((DOMErrorHandler) value);
                    fConfiguration.setProperty(ERROR_HANDLER, fErrorHandler);
                } catch (XMLConfigurationException e) {
                }
            } else {
                // REVISIT: type mismatch
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "TYPE_MISMATCH_ERR", new Object[] { name });
                throw new DOMException(DOMException.TYPE_MISMATCH_ERR, msg);
            }
        } else if (name.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER)) {
            if (value instanceof LSResourceResolver || value == null) {
                try {
                    fConfiguration.setProperty(ENTITY_RESOLVER, new DOMEntityResolverWrapper((LSResourceResolver) value));
                } catch (XMLConfigurationException e) {
                }
            } else {
                // REVISIT: type mismatch
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "TYPE_MISMATCH_ERR", new Object[] { name });
                throw new DOMException(DOMException.TYPE_MISMATCH_ERR, msg);
            }
        } else if (name.equalsIgnoreCase(Constants.DOM_SCHEMA_LOCATION)) {
            if (value instanceof String || value == null) {
                try {
                    if (value == null) {
                        fSchemaLocation = null;
                        fConfiguration.setProperty(Constants.JAXP_PROPERTY_PREFIX + Constants.SCHEMA_SOURCE, null);
                    } else {
                        fSchemaLocation = (String) value;
                        // map DOM schema-location to JAXP schemaSource property
                        // tokenize location string
                        StringTokenizer t = new StringTokenizer(fSchemaLocation, " \n\t\r");
                        if (t.hasMoreTokens()) {
                            fSchemaLocations.clear();
                            fSchemaLocations.add(t.nextToken());
                            while (t.hasMoreTokens()) {
                                fSchemaLocations.add(t.nextToken());
                            }
                            fConfiguration.setProperty(Constants.JAXP_PROPERTY_PREFIX + Constants.SCHEMA_SOURCE, fSchemaLocations.toArray());
                        } else {
                            fConfiguration.setProperty(Constants.JAXP_PROPERTY_PREFIX + Constants.SCHEMA_SOURCE, value);
                        }
                    }
                } catch (XMLConfigurationException e) {
                }
            } else {
                // REVISIT: type mismatch
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "TYPE_MISMATCH_ERR", new Object[] { name });
                throw new DOMException(DOMException.TYPE_MISMATCH_ERR, msg);
            }
        } else if (name.equalsIgnoreCase(Constants.DOM_SCHEMA_TYPE)) {
            if (value instanceof String || value == null) {
                try {
                    if (value == null) {
                        // turn off schema feature
                        fConfiguration.setFeature(Constants.XERCES_FEATURE_PREFIX + Constants.SCHEMA_VALIDATION_FEATURE, false);
                        // map to JAXP schemaLanguage
                        fConfiguration.setProperty(Constants.JAXP_PROPERTY_PREFIX + Constants.SCHEMA_LANGUAGE, null);
                        fSchemaType = null;
                    } else if (value.equals(Constants.NS_XMLSCHEMA)) {
                        // turn on schema feature
                        fConfiguration.setFeature(Constants.XERCES_FEATURE_PREFIX + Constants.SCHEMA_VALIDATION_FEATURE, true);
                        // map to JAXP schemaLanguage
                        fConfiguration.setProperty(Constants.JAXP_PROPERTY_PREFIX + Constants.SCHEMA_LANGUAGE, Constants.NS_XMLSCHEMA);
                        fSchemaType = Constants.NS_XMLSCHEMA;
                    } else if (value.equals(Constants.NS_DTD)) {
                        // turn off schema feature
                        fConfiguration.setFeature(Constants.XERCES_FEATURE_PREFIX + Constants.SCHEMA_VALIDATION_FEATURE, false);
                        // map to JAXP schemaLanguage
                        fConfiguration.setProperty(Constants.JAXP_PROPERTY_PREFIX + Constants.SCHEMA_LANGUAGE, Constants.NS_DTD);
                        fSchemaType = Constants.NS_DTD;
                    }
                } catch (XMLConfigurationException e) {
                }
            } else {
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "TYPE_MISMATCH_ERR", new Object[] { name });
                throw new DOMException(DOMException.TYPE_MISMATCH_ERR, msg);
            }
        } else if (name.equalsIgnoreCase(DOCUMENT_CLASS_NAME)) {
            fConfiguration.setProperty(DOCUMENT_CLASS_NAME, value);
        } else {
            // REVISIT: check if this is a boolean parameter -- type mismatch should be thrown.
            //parameter is not recognized
            String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_FOUND", new Object[] { name });
            throw new DOMException(DOMException.NOT_FOUND_ERR, msg);
        }
    }
}
	/**
     * Look up the value of a feature or a property.
     */
public Object getParameter(String name) throws DOMException {
    if (name.equalsIgnoreCase(Constants.DOM_COMMENTS)) {
        return (fConfiguration.getFeature(INCLUDE_COMMENTS_FEATURE)) ? Boolean.TRUE : Boolean.FALSE;
    } else if (name.equalsIgnoreCase(Constants.DOM_DATATYPE_NORMALIZATION)) {
        return (fConfiguration.getFeature(NORMALIZE_DATA)) ? Boolean.TRUE : Boolean.FALSE;
    } else if (name.equalsIgnoreCase(Constants.DOM_ENTITIES)) {
        return (fConfiguration.getFeature(CREATE_ENTITY_REF_NODES)) ? Boolean.TRUE : Boolean.FALSE;
    } else if (name.equalsIgnoreCase(Constants.DOM_NAMESPACES)) {
        return (fConfiguration.getFeature(NAMESPACES)) ? Boolean.TRUE : Boolean.FALSE;
    } else if (name.equalsIgnoreCase(Constants.DOM_VALIDATE)) {
        return (fConfiguration.getFeature(VALIDATION_FEATURE)) ? Boolean.TRUE : Boolean.FALSE;
    } else if (name.equalsIgnoreCase(Constants.DOM_VALIDATE_IF_SCHEMA)) {
        return (fConfiguration.getFeature(DYNAMIC_VALIDATION)) ? Boolean.TRUE : Boolean.FALSE;
    } else if (name.equalsIgnoreCase(Constants.DOM_ELEMENT_CONTENT_WHITESPACE)) {
        return (fConfiguration.getFeature(INCLUDE_IGNORABLE_WHITESPACE)) ? Boolean.TRUE : Boolean.FALSE;
    } else if (name.equalsIgnoreCase(Constants.DOM_DISALLOW_DOCTYPE)) {
        return (fConfiguration.getFeature(DISALLOW_DOCTYPE_DECL_FEATURE)) ? Boolean.TRUE : Boolean.FALSE;
    } else if (name.equalsIgnoreCase(Constants.DOM_INFOSET)) {
        // REVISIT: This is somewhat expensive to compute
        // but it's possible that the user has a reference
        // to the configuration and is changing the values
        // of these features directly on it.
        boolean infoset = fConfiguration.getFeature(NAMESPACES) && fConfiguration.getFeature(Constants.DOM_NAMESPACE_DECLARATIONS) && fConfiguration.getFeature(INCLUDE_COMMENTS_FEATURE) && fConfiguration.getFeature(INCLUDE_IGNORABLE_WHITESPACE) && !fConfiguration.getFeature(DYNAMIC_VALIDATION) && !fConfiguration.getFeature(CREATE_ENTITY_REF_NODES) && !fConfiguration.getFeature(NORMALIZE_DATA) && !fConfiguration.getFeature(CREATE_CDATA_NODES_FEATURE);
        return (infoset) ? Boolean.TRUE : Boolean.FALSE;
    } else if (name.equalsIgnoreCase(Constants.DOM_CDATA_SECTIONS)) {
        return (fConfiguration.getFeature(CREATE_CDATA_NODES_FEATURE)) ? Boolean.TRUE : Boolean.FALSE;
    } else if (name.equalsIgnoreCase(Constants.DOM_CHECK_CHAR_NORMALIZATION) || name.equalsIgnoreCase(Constants.DOM_NORMALIZE_CHARACTERS)) {
        return Boolean.FALSE;
    } else if (name.equalsIgnoreCase(Constants.DOM_NAMESPACE_DECLARATIONS) || name.equalsIgnoreCase(Constants.DOM_WELLFORMED) || name.equalsIgnoreCase(Constants.DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS) || name.equalsIgnoreCase(Constants.DOM_CANONICAL_FORM) || name.equalsIgnoreCase(Constants.DOM_SUPPORTED_MEDIATYPES_ONLY) || name.equalsIgnoreCase(Constants.DOM_SPLIT_CDATA) || name.equalsIgnoreCase(Constants.DOM_CHARSET_OVERRIDES_XML_ENCODING)) {
        return (fConfiguration.getFeature(name.toLowerCase(Locale.ENGLISH))) ? Boolean.TRUE : Boolean.FALSE;
    } else if (name.equalsIgnoreCase(Constants.DOM_ERROR_HANDLER)) {
        if (fErrorHandler != null) {
            return fErrorHandler.getErrorHandler();
        }
        return null;
    } else if (name.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER)) {
        try {
            XMLEntityResolver entityResolver = (XMLEntityResolver) fConfiguration.getProperty(ENTITY_RESOLVER);
            if (entityResolver != null && entityResolver instanceof DOMEntityResolverWrapper) {
                return ((DOMEntityResolverWrapper) entityResolver).getEntityResolver();
            }
            return null;
        } catch (XMLConfigurationException e) {
        }
    } else if (name.equalsIgnoreCase(Constants.DOM_SCHEMA_TYPE)) {
        return fConfiguration.getProperty(Constants.JAXP_PROPERTY_PREFIX + Constants.SCHEMA_LANGUAGE);
    } else if (name.equalsIgnoreCase(Constants.DOM_SCHEMA_LOCATION)) {
        return fSchemaLocation;
    } else if (name.equalsIgnoreCase(SYMBOL_TABLE)) {
        return fConfiguration.getProperty(SYMBOL_TABLE);
    } else if (name.equalsIgnoreCase(DOCUMENT_CLASS_NAME)) {
        return fConfiguration.getProperty(DOCUMENT_CLASS_NAME);
    } else {
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_FOUND", new Object[] { name });
        throw new DOMException(DOMException.NOT_FOUND_ERR, msg);
    }
    return null;
}
	/**
     *  DOM Level 3 CR - Experimental.
     *
     *  The list of the parameters supported by this
     * <code>DOMConfiguration</code> object and for which at least one value
     * can be set by the application. Note that this list can also contain
     * parameter names defined outside this specification.
     */
public DOMStringList getParameterNames() {
    if (fRecognizedParameters == null) {
        Vector parameters = new Vector();
        // REVISIT: add Xerces recognized properties/features
        parameters.add(Constants.DOM_NAMESPACES);
        parameters.add(Constants.DOM_CDATA_SECTIONS);
        parameters.add(Constants.DOM_CANONICAL_FORM);
        parameters.add(Constants.DOM_NAMESPACE_DECLARATIONS);
        parameters.add(Constants.DOM_SPLIT_CDATA);
        parameters.add(Constants.DOM_ENTITIES);
        parameters.add(Constants.DOM_VALIDATE_IF_SCHEMA);
        parameters.add(Constants.DOM_VALIDATE);
        parameters.add(Constants.DOM_DATATYPE_NORMALIZATION);
        parameters.add(Constants.DOM_CHARSET_OVERRIDES_XML_ENCODING);
        parameters.add(Constants.DOM_CHECK_CHAR_NORMALIZATION);
        parameters.add(Constants.DOM_SUPPORTED_MEDIATYPES_ONLY);
        parameters.add(Constants.DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS);
        parameters.add(Constants.DOM_NORMALIZE_CHARACTERS);
        parameters.add(Constants.DOM_WELLFORMED);
        parameters.add(Constants.DOM_INFOSET);
        parameters.add(Constants.DOM_DISALLOW_DOCTYPE);
        parameters.add(Constants.DOM_ELEMENT_CONTENT_WHITESPACE);
        parameters.add(Constants.DOM_COMMENTS);
        parameters.add(Constants.DOM_ERROR_HANDLER);
        parameters.add(Constants.DOM_RESOURCE_RESOLVER);
        parameters.add(Constants.DOM_SCHEMA_LOCATION);
        parameters.add(Constants.DOM_SCHEMA_TYPE);
        fRecognizedParameters = new DOMStringListImpl(parameters);
    }
    return fRecognizedParameters;
}
	/**
     *  Parse an XML document or fragment from a resource identified by an
     * <code>LSInput</code> and insert the content into an existing
     * document at the position epcified with the <code>contextNode</code>
     * and <code>action</code> arguments. When parsing the input stream the
     * context node is used for resolving unbound namespace prefixes.
     *
     * @param is  The <code>LSInput</code> from which the source
     *   document is to be read.
     * @param cnode  The <code>Node</code> that is used as the context for
     *   the data that is being parsed.
     * @param action This parameter describes which action should be taken
     *   between the new set of node being inserted and the existing
     *   children of the context node. The set of possible actions is
     *   defined above.
     * @exception DOMException
     *   HIERARCHY_REQUEST_ERR: Thrown if this action results in an invalid
     *   hierarchy (i.e. a Document with more than one document element).
     */
public Node parseWithContext(LSInput is, Node cnode, short action) throws DOMException, LSException {
    // REVISIT: need to implement.
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Not supported");
}
	/**
     * NON-DOM: convert LSInput to XNIInputSource
     *
     * @param is
     * @return
     */
XMLInputSource dom2xmlInputSource(LSInput is) {
    // need to wrap the LSInput with an XMLInputSource
    XMLInputSource xis = null;
    // according to DOM, we need to treat such reader as "UTF-16".
    if (is.getCharacterStream() != null) {
        xis = new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI(), is.getCharacterStream(), "UTF-16");
    } else // check whether there is an InputStream
    if (is.getByteStream() != null) {
        xis = new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI(), is.getByteStream(), is.getEncoding());
    } else // according to DOM, we need to treat such data as "UTF-16".
    if (is.getStringData() != null && is.getStringData().length() > 0) {
        xis = new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI(), new StringReader(is.getStringData()), "UTF-16");
    } else // otherwise, just use the public/system/base Ids
    if ((is.getSystemId() != null && is.getSystemId().length() > 0) || (is.getPublicId() != null && is.getPublicId().length() > 0)) {
        xis = new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI());
    } else {
        // all inputs are null
        if (fErrorHandler != null) {
            DOMErrorImpl error = new DOMErrorImpl();
            error.fType = "no-input-specified";
            error.fMessage = "no-input-specified";
            error.fSeverity = DOMError.SEVERITY_FATAL_ERROR;
            fErrorHandler.getErrorHandler().handleError(error);
        }
        throw new LSException(LSException.PARSE_ERR, "no-input-specified");
    }
    return xis;
}
}