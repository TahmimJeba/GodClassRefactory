public class Class0 {
	private Object prefix;
	private Object UNICODE_NORMALIZATION_CHECKING_FEATURE;
	private Object NOT_RECOGNIZED;
	private Object SAX_PROPERTY_PREFIX;
	private Object DOCUMENT_XML_VERSION_PROPERTY;
	private Object STRING_INTERNING_FEATURE;
	private Object RESOLVE_DTD_URIS_FEATURE;
	private Object uri;
	private Object NAMESPACES_FEATURE;
	private Object USE_ATTRIBUTES2_FEATURE;
	private Object rawname;
	private Object SAX_FEATURE_PREFIX;
	private Object USE_LOCATOR2_FEATURE;
	private Object DOM_NODE_PROPERTY;
	private Object DECLARATION_HANDLER_PROPERTY;
	private Object XML_11_FEATURE;
	private Object IS_STANDALONE_FEATURE;
	private Object TRUE;
	private Object PREFIX_XMLNS;
	private Object ENTITY_SKIPPED;
	private Object XMLNS_URIS_FEATURE;
	private Object LEXICAL_HANDLER_PARAMETER_ENTITIES_FEATURE;
	private Object LEXICAL_HANDLER_PROPERTY;
	private Object NAMESPACE_PREFIXES_FEATURE;
	private Object USE_ENTITY_RESOLVER2_FEATURE;
	private Object localpart;

	public Class0(Object prefix, Object UNICODE_NORMALIZATION_CHECKING_FEATURE, Object NOT_RECOGNIZED, Object SAX_PROPERTY_PREFIX, Object DOCUMENT_XML_VERSION_PROPERTY, Object STRING_INTERNING_FEATURE, Object RESOLVE_DTD_URIS_FEATURE, Object uri, Object NAMESPACES_FEATURE, Object USE_ATTRIBUTES2_FEATURE, Object rawname, Object SAX_FEATURE_PREFIX, Object USE_LOCATOR2_FEATURE, Object DOM_NODE_PROPERTY, Object DECLARATION_HANDLER_PROPERTY, Object XML_11_FEATURE, Object IS_STANDALONE_FEATURE, Object TRUE, Object PREFIX_XMLNS, Object ENTITY_SKIPPED, Object XMLNS_URIS_FEATURE, Object LEXICAL_HANDLER_PARAMETER_ENTITIES_FEATURE, Object LEXICAL_HANDLER_PROPERTY, Object NAMESPACE_PREFIXES_FEATURE, Object USE_ENTITY_RESOLVER2_FEATURE, Object localpart){
		this.prefix = prefix;
		this.UNICODE_NORMALIZATION_CHECKING_FEATURE = UNICODE_NORMALIZATION_CHECKING_FEATURE;
		this.NOT_RECOGNIZED = NOT_RECOGNIZED;
		this.SAX_PROPERTY_PREFIX = SAX_PROPERTY_PREFIX;
		this.DOCUMENT_XML_VERSION_PROPERTY = DOCUMENT_XML_VERSION_PROPERTY;
		this.STRING_INTERNING_FEATURE = STRING_INTERNING_FEATURE;
		this.RESOLVE_DTD_URIS_FEATURE = RESOLVE_DTD_URIS_FEATURE;
		this.uri = uri;
		this.NAMESPACES_FEATURE = NAMESPACES_FEATURE;
		this.USE_ATTRIBUTES2_FEATURE = USE_ATTRIBUTES2_FEATURE;
		this.rawname = rawname;
		this.SAX_FEATURE_PREFIX = SAX_FEATURE_PREFIX;
		this.USE_LOCATOR2_FEATURE = USE_LOCATOR2_FEATURE;
		this.DOM_NODE_PROPERTY = DOM_NODE_PROPERTY;
		this.DECLARATION_HANDLER_PROPERTY = DECLARATION_HANDLER_PROPERTY;
		this.XML_11_FEATURE = XML_11_FEATURE;
		this.IS_STANDALONE_FEATURE = IS_STANDALONE_FEATURE;
		this.TRUE = TRUE;
		this.PREFIX_XMLNS = PREFIX_XMLNS;
		this.ENTITY_SKIPPED = ENTITY_SKIPPED;
		this.XMLNS_URIS_FEATURE = XMLNS_URIS_FEATURE;
		this.LEXICAL_HANDLER_PARAMETER_ENTITIES_FEATURE = LEXICAL_HANDLER_PARAMETER_ENTITIES_FEATURE;
		this.LEXICAL_HANDLER_PROPERTY = LEXICAL_HANDLER_PROPERTY;
		this.NAMESPACE_PREFIXES_FEATURE = NAMESPACE_PREFIXES_FEATURE;
		this.USE_ENTITY_RESOLVER2_FEATURE = USE_ENTITY_RESOLVER2_FEATURE;
		this.localpart = localpart;
	}
	// doctypeDecl(String,String,String)
/**
     * This method notifies of the start of an entity. The DTD has the
     * pseudo-name of "[dtd]" parameter entity names start with '%'; and
     * general entity names are just the entity name.
     * <p>
     * <strong>Note:</strong> Since the document is an entity, the handler
     * will be notified of the start of the document entity by calling the
     * startEntity method with the entity name "[xml]" <em>before</em> calling
     * the startDocument method. When exposing entity boundaries through the
     * SAX API, the document entity is never reported, however.
     * <p>
     * <strong>Note:</strong> This method is not called for entity references
     * appearing as part of attribute values.
     *
     * @param name     The name of the entity.
     * @param identifier The resource identifier.
     * @param encoding The auto-detected IANA encoding name of the entity
     *                 stream. This value will be null in those situations
     *                 where the entity encoding is not auto-detected (e.g.
     *                 internal parameter entities).
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void startGeneralEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
    try {
        // Only report startEntity if this entity was actually read.
        if (augs != null && Boolean.TRUE.equals(augs.getItem(Constants.ENTITY_SKIPPED))) {
            // report skipped entity to content handler
            if (fContentHandler != null) {
                fContentHandler.skippedEntity(name);
            }
        } else {
            // SAX2 extension
            if (fLexicalHandler != null) {
                fLexicalHandler.startEntity(name);
            }
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// startGeneralEntity(String,String,String,String,String)
/**
     * This method notifies the end of an entity. The DTD has the pseudo-name
     * of "[dtd]" parameter entity names start with '%'; and general entity
     * names are just the entity name.
     * <p>
     * <strong>Note:</strong> Since the document is an entity, the handler
     * will be notified of the end of the document entity by calling the
     * endEntity method with the entity name "[xml]" <em>after</em> calling
     * the endDocument method. When exposing entity boundaries through the
     * SAX API, the document entity is never reported, however.
     * <p>
     * <strong>Note:</strong> This method is not called for entity references
     * appearing as part of attribute values.
     *
     * @param name The name of the entity.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
    try {
        // Only report endEntity if this entity was actually read.
        if (augs == null || !Boolean.TRUE.equals(augs.getItem(Constants.ENTITY_SKIPPED))) {
            // SAX2 extension
            if (fLexicalHandler != null) {
                fLexicalHandler.endEntity(name);
            }
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// endElement(QName)
/**
     * The start of a CDATA section.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void startCDATA(Augmentations augs) throws XNIException {
    try {
        // SAX2 extension
        if (fLexicalHandler != null) {
            fLexicalHandler.startCDATA();
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// startCDATA()
/**
     * The end of a CDATA section.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endCDATA(Augmentations augs) throws XNIException {
    try {
        // SAX2 extension
        if (fLexicalHandler != null) {
            fLexicalHandler.endCDATA();
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// processingInstruction(String,XMLString)
/**
     * The end of the document.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endDocument(Augmentations augs) throws XNIException {
    try {
        // SAX1
        if (fDocumentHandler != null) {
            fDocumentHandler.endDocument();
        }
        // SAX2
        if (fContentHandler != null) {
            fContentHandler.endDocument();
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// startParameterEntity(String,identifier,String,Augmentation)
/**
     * This method notifies the end of an entity. The DTD has the pseudo-name
     * of "[dtd]" parameter entity names start with '%'; and general entity
     * names are just the entity name.
     * <p>
     * <strong>Note:</strong> Since the document is an entity, the handler
     * will be notified of the end of the document entity by calling the
     * endEntity method with the entity name "[xml]" <em>after</em> calling
     * the endDocument method. When exposing entity boundaries through the
     * SAX API, the document entity is never reported, however.
     * <p>
     * <strong>Note:</strong> This method is not called for entity references
     * appearing as part of attribute values.
     *
     * @param name The name of the parameter entity.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endParameterEntity(String name, Augmentations augs) throws XNIException {
    try {
        // Only report endEntity if this entity was actually read.
        if (augs == null || !Boolean.TRUE.equals(augs.getItem(Constants.ENTITY_SKIPPED))) {
            // SAX2 extension
            if (fLexicalHandler != null && fLexicalHandlerParameterEntities) {
                fLexicalHandler.endEntity(name);
            }
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	/**
     * The end of the DTD external subset.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endExternalSubset(Augmentations augs) throws XNIException {
    endParameterEntity("[dtd]", augs);
}
	/**
     * This method notifies of the start of parameter entity. The DTD has the
     * pseudo-name of "[dtd]" parameter entity names start with '%'; and
     * general entity names are just the entity name.
     * <p>
     * <strong>Note:</strong> Since the document is an entity, the handler
     * will be notified of the start of the document entity by calling the
     * startEntity method with the entity name "[xml]" <em>before</em> calling
     * the startDocument method. When exposing entity boundaries through the
     * SAX API, the document entity is never reported, however.
     * <p>
     * <strong>Note:</strong> This method is not called for entity references
     * appearing as part of attribute values.
     *
     * @param name     The name of the parameter entity.
     * @param identifier The resource identifier.
     * @param encoding The auto-detected IANA encoding name of the entity
     *                 stream. This value will be null in those situations
     *                 where the entity encoding is not auto-detected (e.g.
     *                 internal parameter entities).
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void startParameterEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
    try {
        // Only report startEntity if this entity was actually read.
        if (augs != null && Boolean.TRUE.equals(augs.getItem(Constants.ENTITY_SKIPPED))) {
            // report skipped entity to content handler
            if (fContentHandler != null) {
                fContentHandler.skippedEntity(name);
            }
        } else {
            // SAX2 extension
            if (fLexicalHandler != null && fLexicalHandlerParameterEntities) {
                fLexicalHandler.startEntity(name);
            }
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// endDocument()
//
// XMLDTDHandler methods
//
/**
     * The start of the DTD external subset.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void startExternalSubset(XMLResourceIdentifier identifier, Augmentations augs) throws XNIException {
    startParameterEntity("[dtd]", null, null, augs);
}
	// <init>(XMLParserConfiguration)
//
// XMLDocumentHandler methods
//
/**
     * The start of the document.
     *
     * @param locator The document locator, or null if the document
     *                 location cannot be reported during the parsing
     *                 of this document. However, it is <em>strongly</em>
     *                 recommended that a locator be supplied that can
     *                 at least report the system identifier of the
     *                 document.
     * @param encoding The auto-detected IANA encoding name of the entity
     *                 stream. This value will be null in those situations
     *                 where the entity encoding is not auto-detected (e.g.
     *                 internal entities or a document entity that is
     *                 parsed from a java.io.Reader).
     * @param namespaceContext
     *                 The namespace context in effect at the
     *                 start of this document.
     *                 This object represents the current context.
     *                 Implementors of this class are responsible
     *                 for copying the namespace bindings from the
     *                 the current context (and its parent contexts)
     *                 if that information is important.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
    fNamespaceContext = namespaceContext;
    try {
        // SAX1
        if (fDocumentHandler != null) {
            if (locator != null) {
                fDocumentHandler.setDocumentLocator(new LocatorProxy(locator));
            }
            fDocumentHandler.startDocument();
        }
        // SAX2
        if (fContentHandler != null) {
            if (locator != null) {
                fContentHandler.setDocumentLocator(new LocatorProxy(locator));
            }
            fContentHandler.startDocument();
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// startDocument(locator,encoding,augs)
/**
     * Notifies of the presence of an XMLDecl line in the document. If
     * present, this method will be called immediately following the
     * startDocument call.
     * 
     * @param version    The XML version.
     * @param encoding   The IANA encoding name of the document, or null if
     *                   not specified.
     * @param standalone The standalone value, or null if not specified.
     * @param augs   Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
    // the version need only be set once; if
    // document's XML 1.0|1.1, that's how it'll stay
    fVersion = version;
    fStandalone = "yes".equals(standalone);
}
	// xmlDecl(String,String,String)
/**
     * Notifies of the presence of the DOCTYPE line in the document.
     *
     * @param rootElement The name of the root element.
     * @param publicId    The public identifier if an external DTD or null
     *                    if the external DTD is specified using SYSTEM.
     * @param systemId    The system identifier if an external DTD, null
     *                    otherwise.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void doctypeDecl(String rootElement, String publicId, String systemId, Augmentations augs) throws XNIException {
    fInDTD = true;
    try {
        // SAX2 extension
        if (fLexicalHandler != null) {
            fLexicalHandler.startDTD(rootElement, publicId, systemId);
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
    // is there a DeclHandler?
    if (fDeclHandler != null) {
        fDeclaredAttrs = new SymbolHash();
    }
}
	// endEntity(String)
/**
     * The start of an element. If the document specifies the start element
     * by using an empty tag, then the startElement method will immediately
     * be followed by the endElement method, with no intervening methods.
     *
     * @param element    The name of the element.
     * @param attributes The element attributes.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
    try {
        // SAX1
        if (fDocumentHandler != null) {
            // REVISIT: should we support schema-normalized-value for SAX1 events
            // 
            fAttributesProxy.setAttributes(attributes);
            fDocumentHandler.startElement(element.rawname, fAttributesProxy);
        }
        // SAX2
        if (fContentHandler != null) {
            if (fNamespaces) {
                // send prefix mapping events
                startNamespaceMapping();
                // REVISIT: It should not be necessary to iterate over the attribute
                // list when the set of [namespace attributes] is empty for this
                // element. This should be computable from the NamespaceContext, but
                // since we currently don't report the mappings for the xml prefix
                // we cannot use the declared prefix count for the current context
                // to skip this section. -- mrglavas
                int len = attributes.getLength();
                if (!fNamespacePrefixes) {
                    for (int i = len - 1; i >= 0; --i) {
                        attributes.getName(i, fQName);
                        if ((fQName.prefix == XMLSymbols.PREFIX_XMLNS) || (fQName.rawname == XMLSymbols.PREFIX_XMLNS)) {
                            // remove namespace declaration attributes
                            attributes.removeAttributeAt(i);
                        }
                    }
                } else if (!fXMLNSURIs) {
                    for (int i = len - 1; i >= 0; --i) {
                        attributes.getName(i, fQName);
                        if ((fQName.prefix == XMLSymbols.PREFIX_XMLNS) || (fQName.rawname == XMLSymbols.PREFIX_XMLNS)) {
                            // localpart should be empty string as per SAX documentation:
                            // http://www.saxproject.org/?selected=namespaces
                            fQName.prefix = "";
                            fQName.uri = "";
                            fQName.localpart = "";
                            attributes.setName(i, fQName);
                        }
                    }
                }
            }
            fAugmentations = augs;
            String uri = element.uri != null ? element.uri : "";
            String localpart = fNamespaces ? element.localpart : "";
            fAttributesProxy.setAttributes(attributes);
            fContentHandler.startElement(uri, localpart, element.rawname, fAttributesProxy);
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// ignorableWhitespace(XMLString)
/**
     * The end of an element.
     *
     * @param element The name of the element.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endElement(QName element, Augmentations augs) throws XNIException {
    try {
        // SAX1
        if (fDocumentHandler != null) {
            fDocumentHandler.endElement(element.rawname);
        }
        // SAX2
        if (fContentHandler != null) {
            fAugmentations = augs;
            String uri = element.uri != null ? element.uri : "";
            String localpart = fNamespaces ? element.localpart : "";
            fContentHandler.endElement(uri, localpart, element.rawname);
            if (fNamespaces) {
                endNamespaceMapping();
            }
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// endEntity(String)
/**
     * An element declaration.
     *
     * @param name         The name of the element.
     * @param contentModel The element content model.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void elementDecl(String name, String contentModel, Augmentations augs) throws XNIException {
    try {
        // SAX2 extension
        if (fDeclHandler != null) {
            fDeclHandler.elementDecl(name, contentModel);
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// internalEntityDecl(String,XMLString,XMLString)
/**
     * An external entity declaration.
     *
     * @param name     The name of the entity. Parameter entity names start
     *                 with '%', whereas the name of a general entity is just
     *                 the entity name.
     * @param identifier    An object containing all location information 
     *                      pertinent to this entity.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void externalEntityDecl(String name, XMLResourceIdentifier identifier, Augmentations augs) throws XNIException {
    try {
        // SAX2 extension
        if (fDeclHandler != null) {
            String publicId = identifier.getPublicId();
            String systemId = fResolveDTDURIs ? identifier.getExpandedSystemId() : identifier.getLiteralSystemId();
            fDeclHandler.externalEntityDecl(name, publicId, systemId);
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// unparsedEntityDecl(String,XMLResourceIdentifier, String, Augmentations)
/**
     * A notation declaration
     *
     * @param name     The name of the notation.
     * @param identifier    An object containing all location information 
     *                      pertinent to this notation.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void notationDecl(String name, XMLResourceIdentifier identifier, Augmentations augs) throws XNIException {
    try {
        // SAX1 and SAX2
        if (fDTDHandler != null) {
            String publicId = identifier.getPublicId();
            String systemId = fResolveDTDURIs ? identifier.getExpandedSystemId() : identifier.getLiteralSystemId();
            fDTDHandler.notationDecl(name, publicId, systemId);
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// externalEntityDecl(String,,XMLResourceIdentifier, Augmentations)
/**
     * An unparsed entity declaration.
     *
     * @param name     The name of the entity.
     * @param identifier    An object containing all location information 
     *                      pertinent to this entity.
     * @param notation The name of the notation.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void unparsedEntityDecl(String name, XMLResourceIdentifier identifier, String notation, Augmentations augs) throws XNIException {
    try {
        // SAX2 extension
        if (fDTDHandler != null) {
            String publicId = identifier.getPublicId();
            String systemId = fResolveDTDURIs ? identifier.getExpandedSystemId() : identifier.getLiteralSystemId();
            fDTDHandler.unparsedEntityDecl(name, publicId, systemId, notation);
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// notationDecl(String,XMLResourceIdentifier, Augmentations)
/**
     * The end of the DTD.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endDTD(Augmentations augs) throws XNIException {
    fInDTD = false;
    try {
        // SAX2 extension
        if (fLexicalHandler != null) {
            fLexicalHandler.endDTD();
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
    if (fDeclaredAttrs != null) {
        // help out the GC
        fDeclaredAttrs.clear();
    }
}
	// endDTD()
//
// Parser and XMLReader methods
//
/**
     * Parses the input source specified by the given system identifier.
     * <p>
     * This method is equivalent to the following:
     * <pre>
     *     parse(new InputSource(systemId));
     * </pre>
     *
     * @param systemId The system identifier (URI).
     *
     * @exception org.xml.sax.SAXException Throws exception on SAX error.
     * @exception java.io.IOException Throws exception on i/o error.
     */
public void parse(String systemId) throws SAXException, IOException {
    // parse document
    XMLInputSource source = new XMLInputSource(null, systemId, null);
    try {
        parse(source);
    }// wrap XNI exceptions as SAX exceptions
     catch (XMLParseException e) {
        Exception ex = e.getException();
        if (ex == null) {
            // must be a parser exception; mine it for locator info and throw
            // a SAXParseException
            LocatorImpl locatorImpl = new LocatorImpl() {

                public String getXMLVersion() {
                    return fVersion;
                }

                // since XMLParseExceptions know nothing about encoding,
                // we cannot return anything meaningful in this context.
                // We *could* consult the LocatorProxy, but the
                // application can do this itself if it wishes to possibly
                // be mislead.
                public String getEncoding() {
                    return null;
                }
            };
            locatorImpl.setPublicId(e.getPublicId());
            locatorImpl.setSystemId(e.getExpandedSystemId());
            locatorImpl.setLineNumber(e.getLineNumber());
            locatorImpl.setColumnNumber(e.getColumnNumber());
            throw new SAXParseException(e.getMessage(), locatorImpl);
        }
        if (ex instanceof SAXException) {
            // why did we create an XMLParseException?
            throw (SAXException) ex;
        }
        if (ex instanceof IOException) {
            throw (IOException) ex;
        }
        throw new SAXException(ex);
    } catch (XNIException e) {
        Exception ex = e.getException();
        if (ex == null) {
            throw new SAXException(e.getMessage());
        }
        if (ex instanceof SAXException) {
            throw (SAXException) ex;
        }
        if (ex instanceof IOException) {
            throw (IOException) ex;
        }
        throw new SAXException(ex);
    }
}
	// endDTD()
//
// Parser and XMLReader methods
//
/**
     * Parses the input source specified by the given system identifier.
     * <p>
     * This method is equivalent to the following:
     * <pre>
     *     parse(new InputSource(systemId));
     * </pre>
     *
     * @param systemId The system identifier (URI).
     *
     * @exception org.xml.sax.SAXException Throws exception on SAX error.
     * @exception java.io.IOException Throws exception on i/o error.
     */
public void parse(String systemId) throws SAXException, IOException {
    // parse document
    XMLInputSource source = new XMLInputSource(null, systemId, null);
    try {
        parse(source);
    }// wrap XNI exceptions as SAX exceptions
     catch (XMLParseException e) {
        Exception ex = e.getException();
        if (ex == null) {
            // must be a parser exception; mine it for locator info and throw
            // a SAXParseException
            LocatorImpl locatorImpl = new LocatorImpl() {

                public String getXMLVersion() {
                    return fVersion;
                }

                // since XMLParseExceptions know nothing about encoding,
                // we cannot return anything meaningful in this context.
                // We *could* consult the LocatorProxy, but the
                // application can do this itself if it wishes to possibly
                // be mislead.
                public String getEncoding() {
                    return null;
                }
            };
            locatorImpl.setPublicId(e.getPublicId());
            locatorImpl.setSystemId(e.getExpandedSystemId());
            locatorImpl.setLineNumber(e.getLineNumber());
            locatorImpl.setColumnNumber(e.getColumnNumber());
            throw new SAXParseException(e.getMessage(), locatorImpl);
        }
        if (ex instanceof SAXException) {
            // why did we create an XMLParseException?
            throw (SAXException) ex;
        }
        if (ex instanceof IOException) {
            throw (IOException) ex;
        }
        throw new SAXException(ex);
    } catch (XNIException e) {
        Exception ex = e.getException();
        if (ex == null) {
            throw new SAXException(e.getMessage());
        }
        if (ex instanceof SAXException) {
            throw (SAXException) ex;
        }
        if (ex instanceof IOException) {
            throw (IOException) ex;
        }
        throw new SAXException(ex);
    }
}
	// parse(String)
/**
     * parse
     *
     * @param inputSource
     *
     * @exception org.xml.sax.SAXException
     * @exception java.io.IOException
     */
public void parse(InputSource inputSource) throws SAXException, IOException {
    // parse document
    try {
        XMLInputSource xmlInputSource = new XMLInputSource(inputSource.getPublicId(), inputSource.getSystemId(), null);
        xmlInputSource.setByteStream(inputSource.getByteStream());
        xmlInputSource.setCharacterStream(inputSource.getCharacterStream());
        xmlInputSource.setEncoding(inputSource.getEncoding());
        parse(xmlInputSource);
    }// wrap XNI exceptions as SAX exceptions
     catch (XMLParseException e) {
        Exception ex = e.getException();
        if (ex == null) {
            // must be a parser exception; mine it for locator info and throw
            // a SAXParseException
            LocatorImpl locatorImpl = new LocatorImpl() {

                public String getXMLVersion() {
                    return fVersion;
                }

                // since XMLParseExceptions know nothing about encoding,
                // we cannot return anything meaningful in this context.
                // We *could* consult the LocatorProxy, but the
                // application can do this itself if it wishes to possibly
                // be mislead.
                public String getEncoding() {
                    return null;
                }
            };
            locatorImpl.setPublicId(e.getPublicId());
            locatorImpl.setSystemId(e.getExpandedSystemId());
            locatorImpl.setLineNumber(e.getLineNumber());
            locatorImpl.setColumnNumber(e.getColumnNumber());
            throw new SAXParseException(e.getMessage(), locatorImpl);
        }
        if (ex instanceof SAXException) {
            // why did we create an XMLParseException?
            throw (SAXException) ex;
        }
        if (ex instanceof IOException) {
            throw (IOException) ex;
        }
        throw new SAXException(ex);
    } catch (XNIException e) {
        Exception ex = e.getException();
        if (ex == null) {
            throw new SAXException(e.getMessage());
        }
        if (ex instanceof SAXException) {
            throw (SAXException) ex;
        }
        if (ex instanceof IOException) {
            throw (IOException) ex;
        }
        throw new SAXException(ex);
    }
}
	// parse(String)
/**
     * parse
     *
     * @param inputSource
     *
     * @exception org.xml.sax.SAXException
     * @exception java.io.IOException
     */
public void parse(InputSource inputSource) throws SAXException, IOException {
    // parse document
    try {
        XMLInputSource xmlInputSource = new XMLInputSource(inputSource.getPublicId(), inputSource.getSystemId(), null);
        xmlInputSource.setByteStream(inputSource.getByteStream());
        xmlInputSource.setCharacterStream(inputSource.getCharacterStream());
        xmlInputSource.setEncoding(inputSource.getEncoding());
        parse(xmlInputSource);
    }// wrap XNI exceptions as SAX exceptions
     catch (XMLParseException e) {
        Exception ex = e.getException();
        if (ex == null) {
            // must be a parser exception; mine it for locator info and throw
            // a SAXParseException
            LocatorImpl locatorImpl = new LocatorImpl() {

                public String getXMLVersion() {
                    return fVersion;
                }

                // since XMLParseExceptions know nothing about encoding,
                // we cannot return anything meaningful in this context.
                // We *could* consult the LocatorProxy, but the
                // application can do this itself if it wishes to possibly
                // be mislead.
                public String getEncoding() {
                    return null;
                }
            };
            locatorImpl.setPublicId(e.getPublicId());
            locatorImpl.setSystemId(e.getExpandedSystemId());
            locatorImpl.setLineNumber(e.getLineNumber());
            locatorImpl.setColumnNumber(e.getColumnNumber());
            throw new SAXParseException(e.getMessage(), locatorImpl);
        }
        if (ex instanceof SAXException) {
            // why did we create an XMLParseException?
            throw (SAXException) ex;
        }
        if (ex instanceof IOException) {
            throw (IOException) ex;
        }
        throw new SAXException(ex);
    } catch (XNIException e) {
        Exception ex = e.getException();
        if (ex == null) {
            throw new SAXException(e.getMessage());
        }
        if (ex instanceof SAXException) {
            throw (SAXException) ex;
        }
        if (ex instanceof IOException) {
            throw (IOException) ex;
        }
        throw new SAXException(ex);
    }
}
	public String getXMLVersion() {
    return fVersion;
}
	// since XMLParseExceptions know nothing about encoding,
// we cannot return anything meaningful in this context.
// We *could* consult the LocatorProxy, but the
// application can do this itself if it wishes to possibly
// be mislead.
public String getEncoding() {
    return null;
}
	// since XMLParseExceptions know nothing about encoding,
// we cannot return anything meaningful in this context.
// We *could* consult the LocatorProxy, but the
// application can do this itself if it wishes to possibly
// be mislead.
public String getEncoding() {
    return null;
}
	public String getXMLVersion() {
    return fVersion;
}
	// since XMLParseExceptions know nothing about encoding,
// we cannot return anything meaningful in this context.
// We *could* consult the LocatorProxy, but the
// application can do this itself if it wishes to possibly
// be mislead.
public String getEncoding() {
    return null;
}
	// since XMLParseExceptions know nothing about encoding,
// we cannot return anything meaningful in this context.
// We *could* consult the LocatorProxy, but the
// application can do this itself if it wishes to possibly
// be mislead.
public String getEncoding() {
    return null;
}
	// parse(InputSource)
/**
     * Sets the resolver used to resolve external entities. The EntityResolver
     * interface supports resolution of public and system identifiers.
     *
     * @param resolver The new entity resolver. Passing a null value will
     *                 uninstall the currently installed resolver.
     */
public void setEntityResolver(EntityResolver resolver) {
    try {
        XMLEntityResolver xer = (XMLEntityResolver) fConfiguration.getProperty(ENTITY_RESOLVER);
        if (fUseEntityResolver2 && resolver instanceof EntityResolver2) {
            if (xer instanceof EntityResolver2Wrapper) {
                EntityResolver2Wrapper er2w = (EntityResolver2Wrapper) xer;
                er2w.setEntityResolver((EntityResolver2) resolver);
            } else {
                fConfiguration.setProperty(ENTITY_RESOLVER, new EntityResolver2Wrapper((EntityResolver2) resolver));
            }
        } else {
            if (xer instanceof EntityResolverWrapper) {
                EntityResolverWrapper erw = (EntityResolverWrapper) xer;
                erw.setEntityResolver(resolver);
            } else {
                fConfiguration.setProperty(ENTITY_RESOLVER, new EntityResolverWrapper(resolver));
            }
        }
    } catch (XMLConfigurationException e) {
    // do nothing
    }
}
	// setEntityResolver(EntityResolver)
/**
     * Return the current entity resolver.
     *
     * @return The current entity resolver, or null if none
     *         has been registered.
     * @see #setEntityResolver
     */
public EntityResolver getEntityResolver() {
    EntityResolver entityResolver = null;
    try {
        XMLEntityResolver xmlEntityResolver = (XMLEntityResolver) fConfiguration.getProperty(ENTITY_RESOLVER);
        if (xmlEntityResolver != null) {
            if (xmlEntityResolver instanceof EntityResolverWrapper) {
                entityResolver = ((EntityResolverWrapper) xmlEntityResolver).getEntityResolver();
            } else if (xmlEntityResolver instanceof EntityResolver2Wrapper) {
                entityResolver = ((EntityResolver2Wrapper) xmlEntityResolver).getEntityResolver();
            }
        }
    } catch (XMLConfigurationException e) {
    // do nothing
    }
    return entityResolver;
}
	// getEntityResolver():EntityResolver
/**
     * Allow an application to register an error event handler.
     *
     * <p>If the application does not register an error handler, all
     * error events reported by the SAX parser will be silently
     * ignored; however, normal processing may not continue.  It is
     * highly recommended that all SAX applications implement an
     * error handler to avoid unexpected bugs.</p>
     *
     * <p>Applications may register a new or different handler in the
     * middle of a parse, and the SAX parser must begin using the new
     * handler immediately.</p>
     *
     * @param errorHandler The error handler.
     * @see #getErrorHandler
     */
public void setErrorHandler(ErrorHandler errorHandler) {
    try {
        XMLErrorHandler xeh = (XMLErrorHandler) fConfiguration.getProperty(ERROR_HANDLER);
        if (xeh instanceof ErrorHandlerWrapper) {
            ErrorHandlerWrapper ehw = (ErrorHandlerWrapper) xeh;
            ehw.setErrorHandler(errorHandler);
        } else {
            fConfiguration.setProperty(ERROR_HANDLER, new ErrorHandlerWrapper(errorHandler));
        }
    } catch (XMLConfigurationException e) {
    // do nothing
    }
}
	// setErrorHandler(ErrorHandler)
/**
     * Return the current error handler.
     *
     * @return The current error handler, or null if none
     *         has been registered.
     * @see #setErrorHandler
     */
public ErrorHandler getErrorHandler() {
    ErrorHandler errorHandler = null;
    try {
        XMLErrorHandler xmlErrorHandler = (XMLErrorHandler) fConfiguration.getProperty(ERROR_HANDLER);
        if (xmlErrorHandler != null && xmlErrorHandler instanceof ErrorHandlerWrapper) {
            errorHandler = ((ErrorHandlerWrapper) xmlErrorHandler).getErrorHandler();
        }
    } catch (XMLConfigurationException e) {
    // do nothing
    }
    return errorHandler;
}
	// getErrorHandler():ErrorHandler
/**
     * Set the locale to use for messages.
     *
     * @param locale The locale object to use for localization of messages.
     *
     * @exception SAXException An exception thrown if the parser does not
     *                         support the specified locale.
     *
     * @see org.xml.sax.Parser
     */
public void setLocale(Locale locale) throws SAXException {
    //REVISIT:this methods is not part of SAX2 interfaces, we should throw exception
    //if any application uses SAX2 and sets locale also. -nb
    fConfiguration.setLocale(locale);
}
	// setLocale(Locale)
/**
     * Allow an application to register a DTD event handler.
     * <p>
     * If the application does not register a DTD handler, all DTD
     * events reported by the SAX parser will be silently ignored.
     * <p>
     * Applications may register a new or different handler in the
     * middle of a parse, and the SAX parser must begin using the new
     * handler immediately.
     *
     * @param dtdHandler The DTD handler.
     *

     * @see #getDTDHandler
     */
public void setDTDHandler(DTDHandler dtdHandler) {
    fDTDHandler = dtdHandler;
}
	// setDTDHandler(DTDHandler)
//
// Parser methods
//
/**
     * Allow an application to register a document event handler.
     * <p>
     * If the application does not register a document handler, all
     * document events reported by the SAX parser will be silently
     * ignored (this is the default behaviour implemented by
     * HandlerBase).
     * <p>
     * Applications may register a new or different handler in the
     * middle of a parse, and the SAX parser must begin using the new
     * handler immediately.
     *
     * @param documentHandler The document handler.
     */
public void setDocumentHandler(DocumentHandler documentHandler) {
    fDocumentHandler = documentHandler;
}
	// setDocumentHandler(DocumentHandler)
//
// XMLReader methods
//
/**
     * Allow an application to register a content event handler.
     * <p>
     * If the application does not register a content handler, all
     * content events reported by the SAX parser will be silently
     * ignored.
     * <p>
     * Applications may register a new or different handler in the
     * middle of a parse, and the SAX parser must begin using the new
     * handler immediately.
     *
     * @param contentHandler The content handler.
     *
     * @see #getContentHandler
     */
public void setContentHandler(ContentHandler contentHandler) {
    fContentHandler = contentHandler;
}
	// setContentHandler(ContentHandler)
/**
     * Return the current content handler.
     *
     * @return The current content handler, or null if none
     *         has been registered.
     *
     * @see #setContentHandler
     */
public ContentHandler getContentHandler() {
    return fContentHandler;
}
	// getContentHandler():ContentHandler
/**
     * Return the current DTD handler.
     *
     * @return The current DTD handler, or null if none
     *         has been registered.
     * @see #setDTDHandler
     */
public DTDHandler getDTDHandler() {
    return fDTDHandler;
}
	// getDTDHandler():DTDHandler
/**
     * Set the state of any feature in a SAX2 parser.  The parser
     * might not recognize the feature, and if it does recognize
     * it, it might not be able to fulfill the request.
     *
     * @param featureId The unique identifier (URI) of the feature.
     * @param state The requested state of the feature (true or false).
     *
     * @exception SAXNotRecognizedException If the
     *            requested feature is not known.
     * @exception SAXNotSupportedException If the
     *            requested feature is known, but the requested
     *            state is not supported.
     */
public void setFeature(String featureId, boolean state) throws SAXNotRecognizedException, SAXNotSupportedException {
    try {
        if (featureId.startsWith(Constants.SAX_FEATURE_PREFIX)) {
            final int suffixLength = featureId.length() - Constants.SAX_FEATURE_PREFIX.length();
            // http://xml.org/sax/features/namespaces
            if (suffixLength == Constants.NAMESPACES_FEATURE.length() && featureId.endsWith(Constants.NAMESPACES_FEATURE)) {
                fConfiguration.setFeature(featureId, state);
                fNamespaces = state;
                return;
            }
            //
            if (suffixLength == Constants.NAMESPACE_PREFIXES_FEATURE.length() && featureId.endsWith(Constants.NAMESPACE_PREFIXES_FEATURE)) {
                fConfiguration.setFeature(featureId, state);
                fNamespacePrefixes = state;
                return;
            }
            //
            if (suffixLength == Constants.STRING_INTERNING_FEATURE.length() && featureId.endsWith(Constants.STRING_INTERNING_FEATURE)) {
                if (!state) {
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "false-not-supported", new Object[] { featureId }));
                }
                return;
            }
            //
            if (suffixLength == Constants.LEXICAL_HANDLER_PARAMETER_ENTITIES_FEATURE.length() && featureId.endsWith(Constants.LEXICAL_HANDLER_PARAMETER_ENTITIES_FEATURE)) {
                fLexicalHandlerParameterEntities = state;
                return;
            }
            //
            if (suffixLength == Constants.RESOLVE_DTD_URIS_FEATURE.length() && featureId.endsWith(Constants.RESOLVE_DTD_URIS_FEATURE)) {
                fResolveDTDURIs = state;
                return;
            }
            //
            if (suffixLength == Constants.UNICODE_NORMALIZATION_CHECKING_FEATURE.length() && featureId.endsWith(Constants.UNICODE_NORMALIZATION_CHECKING_FEATURE)) {
                // checking is supported -- mrglavas.
                if (state) {
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "true-not-supported", new Object[] { featureId }));
                }
                return;
            }
            //
            if (suffixLength == Constants.XMLNS_URIS_FEATURE.length() && featureId.endsWith(Constants.XMLNS_URIS_FEATURE)) {
                fXMLNSURIs = state;
                return;
            }
            //
            if (suffixLength == Constants.USE_ENTITY_RESOLVER2_FEATURE.length() && featureId.endsWith(Constants.USE_ENTITY_RESOLVER2_FEATURE)) {
                if (state != fUseEntityResolver2) {
                    fUseEntityResolver2 = state;
                    // Refresh EntityResolver wrapper.
                    setEntityResolver(getEntityResolver());
                }
                return;
            }
            //   reports whether the parser supports both XML 1.1 and XML 1.0.
            if ((suffixLength == Constants.IS_STANDALONE_FEATURE.length() && featureId.endsWith(Constants.IS_STANDALONE_FEATURE)) || (suffixLength == Constants.USE_ATTRIBUTES2_FEATURE.length() && featureId.endsWith(Constants.USE_ATTRIBUTES2_FEATURE)) || (suffixLength == Constants.USE_LOCATOR2_FEATURE.length() && featureId.endsWith(Constants.USE_LOCATOR2_FEATURE)) || (suffixLength == Constants.XML_11_FEATURE.length() && featureId.endsWith(Constants.XML_11_FEATURE))) {
                throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "feature-read-only", new Object[] { featureId }));
            }
        //
        // Drop through and perform default processing
        //
        }
        //
        // Xerces Features
        //
        /*
            else if (featureId.startsWith(XERCES_FEATURES_PREFIX)) {
                String feature = featureId.substring(XERCES_FEATURES_PREFIX.length());
                //
                // Drop through and perform default processing
                //
            }
            */
        //
        // Default handling
        //
        fConfiguration.setFeature(featureId, state);
    } catch (XMLConfigurationException e) {
        String identifier = e.getIdentifier();
        if (e.getType() == XMLConfigurationException.NOT_RECOGNIZED) {
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "feature-not-recognized", new Object[] { identifier }));
        } else {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "feature-not-supported", new Object[] { identifier }));
        }
    }
}
	// setFeature(String,boolean)
/**
     * Query the state of a feature.
     *
     * Query the current state of any feature in a SAX2 parser.  The
     * parser might not recognize the feature.
     *
     * @param featureId The unique identifier (URI) of the feature
     *                  being set.
     * @return The current state of the feature.
     * @exception org.xml.sax.SAXNotRecognizedException If the
     *            requested feature is not known.
     * @exception SAXNotSupportedException If the
     *            requested feature is known but not supported.
     */
public boolean getFeature(String featureId) throws SAXNotRecognizedException, SAXNotSupportedException {
    try {
        if (featureId.startsWith(Constants.SAX_FEATURE_PREFIX)) {
            final int suffixLength = featureId.length() - Constants.SAX_FEATURE_PREFIX.length();
            //
            if (suffixLength == Constants.NAMESPACE_PREFIXES_FEATURE.length() && featureId.endsWith(Constants.NAMESPACE_PREFIXES_FEATURE)) {
                boolean state = fConfiguration.getFeature(featureId);
                return state;
            }
            //
            if (suffixLength == Constants.STRING_INTERNING_FEATURE.length() && featureId.endsWith(Constants.STRING_INTERNING_FEATURE)) {
                return true;
            }
            //
            if (suffixLength == Constants.IS_STANDALONE_FEATURE.length() && featureId.endsWith(Constants.IS_STANDALONE_FEATURE)) {
                return fStandalone;
            }
            //
            if (suffixLength == Constants.XML_11_FEATURE.length() && featureId.endsWith(Constants.XML_11_FEATURE)) {
                return (fConfiguration instanceof XML11Configurable);
            }
            //
            if (suffixLength == Constants.LEXICAL_HANDLER_PARAMETER_ENTITIES_FEATURE.length() && featureId.endsWith(Constants.LEXICAL_HANDLER_PARAMETER_ENTITIES_FEATURE)) {
                return fLexicalHandlerParameterEntities;
            }
            //   their base URIs before reporting.
            if (suffixLength == Constants.RESOLVE_DTD_URIS_FEATURE.length() && featureId.endsWith(Constants.RESOLVE_DTD_URIS_FEATURE)) {
                return fResolveDTDURIs;
            }
            //
            if (suffixLength == Constants.XMLNS_URIS_FEATURE.length() && featureId.endsWith(Constants.XMLNS_URIS_FEATURE)) {
                return fXMLNSURIs;
            }
            //
            if (suffixLength == Constants.UNICODE_NORMALIZATION_CHECKING_FEATURE.length() && featureId.endsWith(Constants.UNICODE_NORMALIZATION_CHECKING_FEATURE)) {
                // checking is supported -- mrglavas.
                return false;
            }
            //
            if (suffixLength == Constants.USE_ENTITY_RESOLVER2_FEATURE.length() && featureId.endsWith(Constants.USE_ENTITY_RESOLVER2_FEATURE)) {
                return fUseEntityResolver2;
            }
            //
            if ((suffixLength == Constants.USE_ATTRIBUTES2_FEATURE.length() && featureId.endsWith(Constants.USE_ATTRIBUTES2_FEATURE)) || (suffixLength == Constants.USE_LOCATOR2_FEATURE.length() && featureId.endsWith(Constants.USE_LOCATOR2_FEATURE))) {
                return true;
            }
        //
        // Drop through and perform default processing
        //
        }
        return fConfiguration.getFeature(featureId);
    } catch (XMLConfigurationException e) {
        String identifier = e.getIdentifier();
        if (e.getType() == XMLConfigurationException.NOT_RECOGNIZED) {
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "feature-not-recognized", new Object[] { identifier }));
        } else {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "feature-not-supported", new Object[] { identifier }));
        }
    }
}
	// getFeature(String):boolean
/**
     * Set the value of any property in a SAX2 parser.  The parser
     * might not recognize the property, and if it does recognize
     * it, it might not support the requested value.
     *
     * @param propertyId The unique identifier (URI) of the property
     *                   being set.
     * @param value The value to which the property is being set.
     *
     * @exception SAXNotRecognizedException If the
     *            requested property is not known.
     * @exception SAXNotSupportedException If the
     *            requested property is known, but the requested
     *            value is not supported.
     */
public void setProperty(String propertyId, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
    try {
        if (propertyId.startsWith(Constants.SAX_PROPERTY_PREFIX)) {
            final int suffixLength = propertyId.length() - Constants.SAX_PROPERTY_PREFIX.length();
            //
            if (suffixLength == Constants.LEXICAL_HANDLER_PROPERTY.length() && propertyId.endsWith(Constants.LEXICAL_HANDLER_PROPERTY)) {
                try {
                    setLexicalHandler((LexicalHandler) value);
                } catch (ClassCastException e) {
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "incompatible-class", new Object[] { propertyId, "org.xml.sax.ext.LexicalHandler" }));
                }
                return;
            }
            //
            if (suffixLength == Constants.DECLARATION_HANDLER_PROPERTY.length() && propertyId.endsWith(Constants.DECLARATION_HANDLER_PROPERTY)) {
                try {
                    setDeclHandler((DeclHandler) value);
                } catch (ClassCastException e) {
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "incompatible-class", new Object[] { propertyId, "org.xml.sax.ext.DeclHandler" }));
                }
                return;
            }
            //
            if ((suffixLength == Constants.DOM_NODE_PROPERTY.length() && propertyId.endsWith(Constants.DOM_NODE_PROPERTY)) || (suffixLength == Constants.DOCUMENT_XML_VERSION_PROPERTY.length() && propertyId.endsWith(Constants.DOCUMENT_XML_VERSION_PROPERTY))) {
                throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "property-read-only", new Object[] { propertyId }));
            }
        //
        // Drop through and perform default processing
        //
        }
        //
        // Xerces Properties
        //
        /*
            else if (propertyId.startsWith(XERCES_PROPERTIES_PREFIX)) {
                //
                // Drop through and perform default processing
                //
            }
            */
        //
        // Perform default processing
        //
        fConfiguration.setProperty(propertyId, value);
    } catch (XMLConfigurationException e) {
        String identifier = e.getIdentifier();
        if (e.getType() == XMLConfigurationException.NOT_RECOGNIZED) {
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "property-not-recognized", new Object[] { identifier }));
        } else {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "property-not-supported", new Object[] { identifier }));
        }
    }
}
	// setProperty(String,Object)
/**
     * Query the value of a property.
     *
     * Return the current value of a property in a SAX2 parser.
     * The parser might not recognize the property.
     *
     * @param propertyId The unique identifier (URI) of the property
     *                   being set.
     * @return The current value of the property.
     * @exception org.xml.sax.SAXNotRecognizedException If the
     *            requested property is not known.
     * @exception SAXNotSupportedException If the
     *            requested property is known but not supported.
     */
public Object getProperty(String propertyId) throws SAXNotRecognizedException, SAXNotSupportedException {
    try {
        if (propertyId.startsWith(Constants.SAX_PROPERTY_PREFIX)) {
            final int suffixLength = propertyId.length() - Constants.SAX_PROPERTY_PREFIX.length();
            //
            if (suffixLength == Constants.DOCUMENT_XML_VERSION_PROPERTY.length() && propertyId.endsWith(Constants.DOCUMENT_XML_VERSION_PROPERTY)) {
                return fVersion;
            }
            //
            if (suffixLength == Constants.LEXICAL_HANDLER_PROPERTY.length() && propertyId.endsWith(Constants.LEXICAL_HANDLER_PROPERTY)) {
                return getLexicalHandler();
            }
            //
            if (suffixLength == Constants.DECLARATION_HANDLER_PROPERTY.length() && propertyId.endsWith(Constants.DECLARATION_HANDLER_PROPERTY)) {
                return getDeclHandler();
            }
            //
            if (suffixLength == Constants.DOM_NODE_PROPERTY.length() && propertyId.endsWith(Constants.DOM_NODE_PROPERTY)) {
                // we are not iterating a DOM tree
                throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "dom-node-read-not-supported", null));
            }
        //
        // Drop through and perform default processing
        //
        }
        return fConfiguration.getProperty(propertyId);
    } catch (XMLConfigurationException e) {
        String identifier = e.getIdentifier();
        if (e.getType() == XMLConfigurationException.NOT_RECOGNIZED) {
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "property-not-recognized", new Object[] { identifier }));
        } else {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "property-not-supported", new Object[] { identifier }));
        }
    }
}
	// getProperty(String):Object
//
// Protected methods
//
// SAX2 core properties
/**
     * Set the DTD declaration event handler.
     * <p>
     * This method is the equivalent to the property:
     * <pre>
     * http://xml.org/sax/properties/declaration-handler
     * </pre>
     *
     * @param handler The new handler.
     *
     * @see #getDeclHandler
     * @see #setProperty
     */
protected void setDeclHandler(DeclHandler handler) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (fParseInProgress) {
        throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "property-not-parsing-supported", new Object[] { "http://xml.org/sax/properties/declaration-handler" }));
    }
    fDeclHandler = handler;
}
	// setDeclHandler(DeclHandler)
/**
     * Returns the DTD declaration event handler.
     *
     * @see #setDeclHandler
     */
protected DeclHandler getDeclHandler() throws SAXNotRecognizedException, SAXNotSupportedException {
    return fDeclHandler;
}
	// getDeclHandler():DeclHandler
/**
     * Set the lexical event handler.
     * <p>
     * This method is the equivalent to the property:
     * <pre>
     * http://xml.org/sax/properties/lexical-handler
     * </pre>
     *
     * @param handler lexical event handler
     *
     * @see #getLexicalHandler
     * @see #setProperty
     */
protected void setLexicalHandler(LexicalHandler handler) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (fParseInProgress) {
        throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(fConfiguration.getLocale(), "property-not-parsing-supported", new Object[] { "http://xml.org/sax/properties/lexical-handler" }));
    }
    fLexicalHandler = handler;
}
	// setLexicalHandler(LexicalHandler)
/**
     * Returns the lexical handler.
     *
     * @see #setLexicalHandler
     */
protected LexicalHandler getLexicalHandler() throws SAXNotRecognizedException, SAXNotSupportedException {
    return fLexicalHandler;
}
	//
// XMLDocumentParser methods
//
/**
     * Reset all components before parsing.
     *
     * @throws XNIException Thrown if an error occurs during initialization.
     */
public void reset() throws XNIException {
    super.reset();
    // reset state
    fInDTD = false;
    fVersion = "1.0";
    fStandalone = false;
    // features
    fNamespaces = fConfiguration.getFeature(NAMESPACES);
    fNamespacePrefixes = fConfiguration.getFeature(NAMESPACE_PREFIXES);
    fAugmentations = null;
    fDeclaredAttrs = null;
}
	//
// Locator methods
//
/** Public identifier. */
public String getPublicId() {
    return fLocator.getPublicId();
}
	/** System identifier. */
public String getSystemId() {
    return fLocator.getExpandedSystemId();
}
	/** Line number. */
public int getLineNumber() {
    return fLocator.getLineNumber();
}
	/** Column number. */
public int getColumnNumber() {
    return fLocator.getColumnNumber();
}
	// Locator2 methods
public String getXMLVersion() {
    return fLocator.getXMLVersion();
}
	public String getEncoding() {
    return fLocator.getEncoding();
}
	public String getEncoding() {
    return fLocator.getEncoding();
}
	//
// Public methods
//
/** Sets the XML attributes. */
public void setAttributes(XMLAttributes attributes) {
    fAttributes = attributes;
}
	/**
     * Send endPrefixMapping events
     */
protected final void endNamespaceMapping() throws SAXException {
    int count = fNamespaceContext.getDeclaredPrefixCount();
    if (count > 0) {
        for (int i = 0; i < count; i++) {
            fContentHandler.endPrefixMapping(fNamespaceContext.getDeclaredPrefixAt(i));
        }
    }
}
	// getLexicalHandler():LexicalHandler
/**
     * Send startPrefixMapping events
     */
protected final void startNamespaceMapping() throws SAXException {
    int count = fNamespaceContext.getDeclaredPrefixCount();
    if (count > 0) {
        String prefix = null;
        String uri = null;
        for (int i = 0; i < count; i++) {
            prefix = fNamespaceContext.getDeclaredPrefixAt(i);
            uri = fNamespaceContext.getURI(prefix);
            fContentHandler.startPrefixMapping(prefix, (uri == null) ? "" : uri);
        }
    }
}
}