public class Class0 {
	private Object NOTATION_NODE;
	private Object fType;
	private Object SEVERITY_WARNING;
	private Object FILTER_REJECT;
	private Object ENTITY_NODE;
	private Object fRelatedData;
	private Object SHOW_PROCESSING_INSTRUCTION;
	private Object ELEMENT_NODE;
	private Object fSeverity;
	private Object SHOW_ENTITY_REFERENCE;
	private Object out;
	private Object DOM_DOMAIN;
	private Object TEXT_NODE;
	private Object PROCESSING_INSTRUCTION_NODE;
	private Object length;
	private Object FILTER_SKIP;
	private Object FILTER_INTERRUPT;

	public Class0(Object NOTATION_NODE, Object fType, Object SEVERITY_WARNING, Object FILTER_REJECT, Object ENTITY_NODE, Object fRelatedData, Object SHOW_PROCESSING_INSTRUCTION, Object ELEMENT_NODE, Object fSeverity, Object SHOW_ENTITY_REFERENCE, Object out, Object DOM_DOMAIN, Object TEXT_NODE, Object PROCESSING_INSTRUCTION_NODE, Object length, Object FILTER_SKIP, Object FILTER_INTERRUPT){
		this.NOTATION_NODE = NOTATION_NODE;
		this.fType = fType;
		this.SEVERITY_WARNING = SEVERITY_WARNING;
		this.FILTER_REJECT = FILTER_REJECT;
		this.ENTITY_NODE = ENTITY_NODE;
		this.fRelatedData = fRelatedData;
		this.SHOW_PROCESSING_INSTRUCTION = SHOW_PROCESSING_INSTRUCTION;
		this.ELEMENT_NODE = ELEMENT_NODE;
		this.fSeverity = fSeverity;
		this.SHOW_ENTITY_REFERENCE = SHOW_ENTITY_REFERENCE;
		this.out = out;
		this.DOM_DOMAIN = DOM_DOMAIN;
		this.TEXT_NODE = TEXT_NODE;
		this.PROCESSING_INSTRUCTION_NODE = PROCESSING_INSTRUCTION_NODE;
		this.length = length;
		this.FILTER_SKIP = FILTER_SKIP;
		this.FILTER_INTERRUPT = FILTER_INTERRUPT;
	}
	// setLocale(Locale)
//
// XMLDocumentHandler methods
//
/**
     * This method notifies the start of a general entity.
     * <p>
     * <strong>Note:</strong> This method is not called for entity references
     * appearing as part of attribute values.
     *
     * @param name     The name of the general entity.
     * @param identifier The resource identifier.
     * @param encoding The auto-detected IANA encoding name of the entity
     *                 stream. This value will be null in those situations
     *                 where the entity encoding is not auto-detected (e.g.
     *                 internal entities or a document entity that is
     *                 parsed from a java.io.Reader).
     * @param augs     Additional information that may include infoset augmentations
     *
     * @exception XNIException Thrown by handler to signal an error.
     */
public void startGeneralEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>startGeneralEntity (" + name + ")");
        if (DEBUG_BASEURI) {
            System.out.println("   expandedSystemId( **baseURI): " + identifier.getExpandedSystemId());
            System.out.println("   baseURI:" + identifier.getBaseSystemId());
        }
    }
    // entity as a part of doctype
    if (!fDeferNodeExpansion) {
        if (fFilterReject) {
            return;
        }
        setCharacterData(true);
        EntityReference er = fDocument.createEntityReference(name);
        if (fDocumentImpl != null) {
            // REVISIT: baseURI/actualEncoding
            //         remove dependency on our implementation when DOM L3 is REC
            //
            EntityReferenceImpl erImpl = (EntityReferenceImpl) er;
            // set base uri
            erImpl.setBaseURI(identifier.getExpandedSystemId());
            if (fDocumentType != null) {
                // set actual encoding
                NamedNodeMap entities = fDocumentType.getEntities();
                fCurrentEntityDecl = (EntityImpl) entities.getNamedItem(name);
                if (fCurrentEntityDecl != null) {
                    fCurrentEntityDecl.setInputEncoding(encoding);
                }
            }
            // we don't need synchronization now, because entity ref will be
            // expanded anyway. Synch only needed when user creates entityRef node
            erImpl.needsSyncChildren(false);
        }
        fInEntityRef = true;
        fCurrentNode.appendChild(er);
        fCurrentNode = er;
    } else {
        int er = fDeferredDocumentImpl.createDeferredEntityReference(name, identifier.getExpandedSystemId());
        if (fDocumentTypeIndex != -1) {
            // find corresponding Entity decl
            int node = fDeferredDocumentImpl.getLastChild(fDocumentTypeIndex, false);
            while (node != -1) {
                short nodeType = fDeferredDocumentImpl.getNodeType(node, false);
                if (nodeType == Node.ENTITY_NODE) {
                    String nodeName = fDeferredDocumentImpl.getNodeName(node, false);
                    if (nodeName.equals(name)) {
                        fDeferredEntityDecl = node;
                        fDeferredDocumentImpl.setInputEncoding(node, encoding);
                        break;
                    }
                }
                node = fDeferredDocumentImpl.getRealPrevSibling(node, false);
            }
        }
        fDeferredDocumentImpl.appendChild(fCurrentNodeIndex, er);
        fCurrentNodeIndex = er;
    }
}
	// startDTD(XMLLocator)
/**
     * The end of the DTD.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endDTD(Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>endDTD()");
    }
    fInDTD = false;
    if (!fBaseURIStack.isEmpty()) {
        fBaseURIStack.pop();
    }
    String internalSubset = fInternalSubset != null && fInternalSubset.length() > 0 ? fInternalSubset.toString() : null;
    if (fDeferNodeExpansion) {
        if (internalSubset != null) {
            fDeferredDocumentImpl.setInternalSubset(fDocumentTypeIndex, internalSubset);
        }
    } else if (fDocumentImpl != null) {
        if (internalSubset != null) {
            ((DocumentTypeImpl) fDocumentType).setInternalSubset(internalSubset);
        }
    }
}
	//
// XMLDTDHandler methods
//
/**
     * The start of the DTD.
     *
     * @param locator  The document locator, or null if the document
     *                 location cannot be reported during the parsing of
     *                 the document DTD. However, it is <em>strongly</em>
     *                 recommended that a locator be supplied that can
     *                 at least report the base system identifier of the
     *                 DTD.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void startDTD(XMLLocator locator, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>startDTD");
        if (DEBUG_BASEURI) {
            System.out.println("   expandedSystemId: " + locator.getExpandedSystemId());
            System.out.println("   baseURI:" + locator.getBaseSystemId());
        }
    }
    fInDTD = true;
    if (locator != null) {
        fBaseURIStack.push(locator.getBaseSystemId());
    }
    if (fDeferNodeExpansion || fDocumentImpl != null) {
        fInternalSubset = new StringBuffer(1024);
    }
}
	/**
     * This method notifies the end of a parameter entity. Parameter entity
     * names begin with a '%' character.
     *
     * @param name The name of the parameter entity.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endParameterEntity(String name, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>endParameterEntity: " + name);
    }
    fBaseURIStack.pop();
}
	// endConditional()
/**
     * The start of the DTD external subset.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void startExternalSubset(XMLResourceIdentifier identifier, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>startExternalSubset");
        if (DEBUG_BASEURI) {
            System.out.println("   expandedSystemId: " + identifier.getExpandedSystemId());
            System.out.println("   baseURI:" + identifier.getBaseSystemId());
        }
    }
    fBaseURIStack.push(identifier.getBaseSystemId());
    fInDTDExternalSubset = true;
}
	// externalEntityDecl(String,XMLResourceIdentifier, Augmentations)
/**
     * This method notifies of the start of a parameter entity. The parameter
     * entity name start with a '%' character.
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
    if (DEBUG_EVENTS) {
        System.out.println("==>startParameterEntity: " + name);
        if (DEBUG_BASEURI) {
            System.out.println("   expandedSystemId: " + identifier.getExpandedSystemId());
            System.out.println("   baseURI:" + identifier.getBaseSystemId());
        }
    }
    fBaseURIStack.push(identifier.getExpandedSystemId());
}
	// internalEntityDecl(String,XMLString,XMLString)
/**
     * An external entity declaration.
     *
     * @param name     The name of the entity. Parameter entity names start
     *                 with '%', whereas the name of a general entity is just
     *                 the entity name.
     * @param identifier    An object containing all location information
     *                      pertinent to this notation.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void externalEntityDecl(String name, XMLResourceIdentifier identifier, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>externalEntityDecl: " + name);
        if (DEBUG_BASEURI) {
            System.out.println("   expandedSystemId:" + identifier.getExpandedSystemId());
            System.out.println("   baseURI:" + identifier.getBaseSystemId());
        }
    }
    // internal subset string
    String publicId = identifier.getPublicId();
    String literalSystemId = identifier.getLiteralSystemId();
    if (fInternalSubset != null && !fInDTDExternalSubset) {
        fInternalSubset.append("<!ENTITY ");
        if (name.startsWith("%")) {
            fInternalSubset.append("% ");
            fInternalSubset.append(name.substring(1));
        } else {
            fInternalSubset.append(name);
        }
        fInternalSubset.append(' ');
        if (publicId != null) {
            fInternalSubset.append("PUBLIC '");
            fInternalSubset.append(publicId);
            fInternalSubset.append("' '");
        } else {
            fInternalSubset.append("SYSTEM '");
        }
        fInternalSubset.append(literalSystemId);
        fInternalSubset.append("'>\n");
    }
    // don't add parameter entities!
    if (name.startsWith("%"))
        return;
    if (fDocumentType != null) {
        NamedNodeMap entities = fDocumentType.getEntities();
        EntityImpl entity = (EntityImpl) entities.getNamedItem(name);
        if (entity == null) {
            entity = (EntityImpl) fDocumentImpl.createEntity(name);
            entity.setPublicId(publicId);
            entity.setSystemId(literalSystemId);
            entity.setBaseURI(identifier.getBaseSystemId());
            entities.setNamedItem(entity);
        }
    }
    // create deferred node
    if (fDocumentTypeIndex != -1) {
        boolean found = false;
        int nodeIndex = fDeferredDocumentImpl.getLastChild(fDocumentTypeIndex, false);
        while (nodeIndex != -1) {
            short nodeType = fDeferredDocumentImpl.getNodeType(nodeIndex, false);
            if (nodeType == Node.ENTITY_NODE) {
                String nodeName = fDeferredDocumentImpl.getNodeName(nodeIndex, false);
                if (nodeName.equals(name)) {
                    found = true;
                    break;
                }
            }
            nodeIndex = fDeferredDocumentImpl.getRealPrevSibling(nodeIndex, false);
        }
        if (!found) {
            int entityIndex = fDeferredDocumentImpl.createDeferredEntity(name, publicId, literalSystemId, null, identifier.getBaseSystemId());
            fDeferredDocumentImpl.appendChild(fDocumentTypeIndex, entityIndex);
        }
    }
}
	// endExternalSubset(Augmentations)
/**
     * An internal entity declaration.
     *
     * @param name The name of the entity. Parameter entity names start with
     *             '%', whereas the name of a general entity is just the
     *             entity name.
     * @param text The value of the entity.
     * @param nonNormalizedText The non-normalized value of the entity. This
     *             value contains the same sequence of characters that was in
     *             the internal entity declaration, without any entity
     *             references expanded.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void internalEntityDecl(String name, XMLString text, XMLString nonNormalizedText, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>internalEntityDecl: " + name);
        if (DEBUG_BASEURI) {
            System.out.println("   baseURI:" + (String) fBaseURIStack.peek());
        }
    }
    // internal subset string
    if (fInternalSubset != null && !fInDTDExternalSubset) {
        fInternalSubset.append("<!ENTITY ");
        if (name.startsWith("%")) {
            fInternalSubset.append("% ");
            fInternalSubset.append(name.substring(1));
        } else {
            fInternalSubset.append(name);
        }
        fInternalSubset.append(' ');
        String value = nonNormalizedText.toString();
        boolean singleQuote = value.indexOf('\'') == -1;
        fInternalSubset.append(singleQuote ? '\'' : '"');
        fInternalSubset.append(value);
        fInternalSubset.append(singleQuote ? '\'' : '"');
        fInternalSubset.append(">\n");
    }
    // don't add parameter entities!
    if (name.startsWith("%"))
        return;
    if (fDocumentType != null) {
        NamedNodeMap entities = fDocumentType.getEntities();
        EntityImpl entity = (EntityImpl) entities.getNamedItem(name);
        if (entity == null) {
            entity = (EntityImpl) fDocumentImpl.createEntity(name);
            entity.setBaseURI((String) fBaseURIStack.peek());
            entities.setNamedItem(entity);
        }
    }
    // create deferred node
    if (fDocumentTypeIndex != -1) {
        boolean found = false;
        int node = fDeferredDocumentImpl.getLastChild(fDocumentTypeIndex, false);
        while (node != -1) {
            short nodeType = fDeferredDocumentImpl.getNodeType(node, false);
            if (nodeType == Node.ENTITY_NODE) {
                String nodeName = fDeferredDocumentImpl.getNodeName(node, false);
                if (nodeName.equals(name)) {
                    found = true;
                    break;
                }
            }
            node = fDeferredDocumentImpl.getRealPrevSibling(node, false);
        }
        if (!found) {
            int entityIndex = fDeferredDocumentImpl.createDeferredEntity(name, null, null, null, (String) fBaseURIStack.peek());
            fDeferredDocumentImpl.appendChild(fDocumentTypeIndex, entityIndex);
        }
    }
}
	/**
     * An unparsed entity declaration.
     *
     * @param name     The name of the entity.
     * @param identifier    An object containing all location information
     *                      pertinent to this entity.
     * @param notation The name of the notation.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void unparsedEntityDecl(String name, XMLResourceIdentifier identifier, String notation, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>unparsedEntityDecl: " + name);
        if (DEBUG_BASEURI) {
            System.out.println("   expandedSystemId:" + identifier.getExpandedSystemId());
            System.out.println("   baseURI:" + identifier.getBaseSystemId());
        }
    }
    // internal subset string
    String publicId = identifier.getPublicId();
    String literalSystemId = identifier.getLiteralSystemId();
    if (fInternalSubset != null && !fInDTDExternalSubset) {
        fInternalSubset.append("<!ENTITY ");
        fInternalSubset.append(name);
        fInternalSubset.append(' ');
        if (publicId != null) {
            fInternalSubset.append("PUBLIC '");
            fInternalSubset.append(publicId);
            if (literalSystemId != null) {
                fInternalSubset.append("' '");
                fInternalSubset.append(literalSystemId);
            }
        } else {
            fInternalSubset.append("SYSTEM '");
            fInternalSubset.append(literalSystemId);
        }
        fInternalSubset.append("' NDATA ");
        fInternalSubset.append(notation);
        fInternalSubset.append(">\n");
    }
    // create full node
    if (fDocumentType != null) {
        NamedNodeMap entities = fDocumentType.getEntities();
        EntityImpl entity = (EntityImpl) entities.getNamedItem(name);
        if (entity == null) {
            entity = (EntityImpl) fDocumentImpl.createEntity(name);
            entity.setPublicId(publicId);
            entity.setSystemId(literalSystemId);
            entity.setNotationName(notation);
            entity.setBaseURI(identifier.getBaseSystemId());
            entities.setNamedItem(entity);
        }
    }
    // create deferred node
    if (fDocumentTypeIndex != -1) {
        boolean found = false;
        int nodeIndex = fDeferredDocumentImpl.getLastChild(fDocumentTypeIndex, false);
        while (nodeIndex != -1) {
            short nodeType = fDeferredDocumentImpl.getNodeType(nodeIndex, false);
            if (nodeType == Node.ENTITY_NODE) {
                String nodeName = fDeferredDocumentImpl.getNodeName(nodeIndex, false);
                if (nodeName.equals(name)) {
                    found = true;
                    break;
                }
            }
            nodeIndex = fDeferredDocumentImpl.getRealPrevSibling(nodeIndex, false);
        }
        if (!found) {
            int entityIndex = fDeferredDocumentImpl.createDeferredEntity(name, publicId, literalSystemId, notation, identifier.getBaseSystemId());
            fDeferredDocumentImpl.appendChild(fDocumentTypeIndex, entityIndex);
        }
    }
}
	// <init>(XMLParserConfiguration)
/**
     * This method retreives the name of current document class.
     */
protected String getDocumentClassName() {
    return fDocumentClassName;
}
	/**
     * This method allows the programmer to decide which document
     * factory to use when constructing the DOM tree. However, doing
     * so will lose the functionality of the default factory. Also,
     * a document class other than the default will lose the ability
     * to defer node expansion on the DOM tree produced.
     *
     * @param documentClassName The fully qualified class name of the
     *                      document factory to use when constructing
     *                      the DOM tree.
     *
     * @see #getDocumentClassName
     * @see #DEFAULT_DOCUMENT_CLASS_NAME
     */
protected void setDocumentClassName(String documentClassName) {
    // normalize class name
    if (documentClassName == null) {
        documentClassName = DEFAULT_DOCUMENT_CLASS_NAME;
    }
    if (!documentClassName.equals(DEFAULT_DOCUMENT_CLASS_NAME) && !documentClassName.equals(PSVI_DOCUMENT_CLASS_NAME)) {
        // verify that this class exists and is of the right type
        try {
            Class _class = ObjectFactory.findProviderClass(documentClassName, ObjectFactory.findClassLoader(), true);
            //if (!_class.isAssignableFrom(Document.class)) {
            if (!Document.class.isAssignableFrom(_class)) {
                throw new IllegalArgumentException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "InvalidDocumentClassName", new Object[] { documentClassName }));
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "MissingDocumentClassName", new Object[] { documentClassName }));
        }
    }
    // set document class name
    fDocumentClassName = documentClassName;
    if (!documentClassName.equals(DEFAULT_DOCUMENT_CLASS_NAME)) {
        fDeferNodeExpansion = false;
    }
}
	// processingInstruction(String,XMLString)
/**
     * The start of the document.
     *
     * @param locator The system identifier of the entity if the entity
     *                 is external, null otherwise.
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
    if (!fDeferNodeExpansion) {
        if (fDocumentClassName.equals(DEFAULT_DOCUMENT_CLASS_NAME)) {
            fDocument = new DocumentImpl();
            fDocumentImpl = (CoreDocumentImpl) fDocument;
            // REVISIT: when DOM Level 3 is REC rely on Document.support
            //          instead of specific class
            // set DOM error checking off
            fDocumentImpl.setStrictErrorChecking(false);
            // set actual encoding
            fDocumentImpl.setInputEncoding(encoding);
            // set documentURI
            fDocumentImpl.setDocumentURI(locator.getExpandedSystemId());
        } else if (fDocumentClassName.equals(PSVI_DOCUMENT_CLASS_NAME)) {
            fDocument = new PSVIDocumentImpl();
            fDocumentImpl = (CoreDocumentImpl) fDocument;
            fStorePSVI = true;
            // REVISIT: when DOM Level 3 is REC rely on Document.support
            //          instead of specific class
            // set DOM error checking off
            fDocumentImpl.setStrictErrorChecking(false);
            // set actual encoding
            fDocumentImpl.setInputEncoding(encoding);
            // set documentURI
            fDocumentImpl.setDocumentURI(locator.getExpandedSystemId());
        } else {
            // use specified document class
            try {
                ClassLoader cl = ObjectFactory.findClassLoader();
                Class documentClass = ObjectFactory.findProviderClass(fDocumentClassName, cl, true);
                fDocument = (Document) documentClass.newInstance();
                // if subclass of our own class that's cool too
                Class defaultDocClass = ObjectFactory.findProviderClass(CORE_DOCUMENT_CLASS_NAME, cl, true);
                if (defaultDocClass.isAssignableFrom(documentClass)) {
                    fDocumentImpl = (CoreDocumentImpl) fDocument;
                    Class psviDocClass = ObjectFactory.findProviderClass(PSVI_DOCUMENT_CLASS_NAME, cl, true);
                    if (psviDocClass.isAssignableFrom(documentClass)) {
                        fStorePSVI = true;
                    }
                    // REVISIT: when DOM Level 3 is REC rely on
                    //          Document.support instead of specific class
                    // set DOM error checking off
                    fDocumentImpl.setStrictErrorChecking(false);
                    // set actual encoding
                    fDocumentImpl.setInputEncoding(encoding);
                    // set documentURI
                    if (locator != null) {
                        fDocumentImpl.setDocumentURI(locator.getExpandedSystemId());
                    }
                }
            } catch (ClassNotFoundException e) {
            // won't happen we already checked that earlier
            } catch (Exception e) {
                throw new RuntimeException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "CannotCreateDocumentClass", new Object[] { fDocumentClassName }));
            }
        }
        fCurrentNode = fDocument;
    } else {
        fDeferredDocumentImpl = new DeferredDocumentImpl(fNamespaceAware);
        fDocument = fDeferredDocumentImpl;
        fDocumentIndex = fDeferredDocumentImpl.createDeferredDocument();
        // REVISIT: strict error checking is not implemented in deferred dom.
        //          Document.support instead of specific class
        // set actual encoding
        fDeferredDocumentImpl.setInputEncoding(encoding);
        // set documentURI
        fDeferredDocumentImpl.setDocumentURI(locator.getExpandedSystemId());
        fCurrentNodeIndex = fDocumentIndex;
    }
}
	// setDocumentClassName(String)
//
// Public methods
//
/** Returns the DOM document object. */
public Document getDocument() {
    return fDocument;
}
	// getDocument():Document
//
// XMLDocumentParser methods
//
/**
     * Resets the parser state.
     *
     * @throws SAXException Thrown on initialization error.
     */
public void reset() throws XNIException {
    super.reset();
    // get feature state
    fCreateEntityRefNodes = fConfiguration.getFeature(CREATE_ENTITY_REF_NODES);
    fIncludeIgnorableWhitespace = fConfiguration.getFeature(INCLUDE_IGNORABLE_WHITESPACE);
    fDeferNodeExpansion = fConfiguration.getFeature(DEFER_NODE_EXPANSION);
    fNamespaceAware = fConfiguration.getFeature(NAMESPACES);
    fIncludeComments = fConfiguration.getFeature(INCLUDE_COMMENTS_FEATURE);
    fCreateCDATANodes = fConfiguration.getFeature(CREATE_CDATA_NODES_FEATURE);
    // get property
    setDocumentClassName((String) fConfiguration.getProperty(DOCUMENT_CLASS_NAME));
    // reset dom information
    fDocument = null;
    fDocumentImpl = null;
    fStorePSVI = false;
    fDocumentType = null;
    fDocumentTypeIndex = -1;
    fDeferredDocumentImpl = null;
    fCurrentNode = null;
    // reset string buffer
    fStringBuffer.setLength(0);
    // reset state information
    fRoot.clear();
    fInDTD = false;
    fInDTDExternalSubset = false;
    fInCDATASection = false;
    fFirstChunk = false;
    fCurrentCDATASection = null;
    fCurrentCDATASectionIndex = -1;
    fBaseURIStack.removeAllElements();
}
	// startGeneralEntity(String,XMLResourceIdentifier, Augmentations)
/**
     * Notifies of the presence of a TextDecl line in an entity. If present,
     * this method will be called immediately following the startEntity call.
     * <p>
     * <strong>Note:</strong> This method will never be called for the
     * document entity; it is only called for external general entities
     * referenced in document content.
     * <p>
     * <strong>Note:</strong> This method is not called for entity references
     * appearing as part of attribute values.
     *
     * @param version  The XML version, or null if not specified.
     * @param encoding The IANA encoding name of the entity.
     * @param augs       Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
    if (fInDTD) {
        return;
    }
    if (!fDeferNodeExpansion) {
        if (fCurrentEntityDecl != null && !fFilterReject) {
            fCurrentEntityDecl.setXmlEncoding(encoding);
            if (version != null)
                fCurrentEntityDecl.setXmlVersion(version);
        }
    } else {
        if (fDeferredEntityDecl != -1) {
            fDeferredDocumentImpl.setEntityInfo(fDeferredEntityDecl, version, encoding);
        }
    }
}
	// comment(XMLString)
/**
     * A processing instruction. Processing instructions consist of a
     * target name and, optionally, text data. The data is only meaningful
     * to the application.
     * <p>
     * Typically, a processing instruction's data will contain a series
     * of pseudo-attributes. These pseudo-attributes follow the form of
     * element attributes but are <strong>not</strong> parsed or presented
     * to the application as anything other than text. The application is
     * responsible for parsing the data.
     *
     * @param target The target.
     * @param data   The data or null if none specified.
     * @param augs       Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
    if (fInDTD) {
        if (fInternalSubset != null && !fInDTDExternalSubset) {
            fInternalSubset.append("<?");
            fInternalSubset.append(target);
            fInternalSubset.append(' ');
            fInternalSubset.append(data.toString());
            fInternalSubset.append("?>");
        }
        return;
    }
    if (DEBUG_EVENTS) {
        System.out.println("==>processingInstruction (" + target + ")");
    }
    if (!fDeferNodeExpansion) {
        if (fFilterReject) {
            return;
        }
        ProcessingInstruction pi = fDocument.createProcessingInstruction(target, data.toString());
        setCharacterData(false);
        fCurrentNode.appendChild(pi);
        if (fDOMFilter != null && !fInEntityRef && (fDOMFilter.getWhatToShow() & NodeFilter.SHOW_PROCESSING_INSTRUCTION) != 0) {
            short code = fDOMFilter.acceptNode(pi);
            switch(code) {
                case LSParserFilter.FILTER_INTERRUPT:
                    {
                        throw abort;
                    }
                case LSParserFilter.FILTER_REJECT:
                    {
                    // fall through to SKIP since PI has no children.
                    }
                case LSParserFilter.FILTER_SKIP:
                    {
                        fCurrentNode.removeChild(pi);
                        // fFirstChunk must be set to true so that data
                        // won't be lost in the case where the child before PI is
                        // a text node and the next event is characters.
                        fFirstChunk = true;
                        return;
                    }
                default:
                    {
                    }
            }
        }
    } else {
        int pi = fDeferredDocumentImpl.createDeferredProcessingInstruction(target, data.toString());
        fDeferredDocumentImpl.appendChild(fCurrentNodeIndex, pi);
    }
}
	// startDocument(String,String)
/**
     * Notifies of the presence of an XMLDecl line in the document. If
     * present, this method will be called immediately following the
     * startDocument call.
     *
     * @param version    The XML version.
     * @param encoding   The IANA encoding name of the document, or null if
     *                   not specified.
     * @param standalone The standalone value, or null if not specified.
     * @param augs       Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
    if (!fDeferNodeExpansion) {
        //          instead of specific class
        if (fDocumentImpl != null) {
            if (version != null)
                fDocumentImpl.setXmlVersion(version);
            fDocumentImpl.setXmlEncoding(encoding);
            fDocumentImpl.setXmlStandalone("yes".equals(standalone));
        }
    } else {
        if (version != null)
            fDeferredDocumentImpl.setXmlVersion(version);
        fDeferredDocumentImpl.setXmlEncoding(encoding);
        fDeferredDocumentImpl.setXmlStandalone("yes".equals(standalone));
    }
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
    if (!fDeferNodeExpansion) {
        if (fDocumentImpl != null) {
            fDocumentType = fDocumentImpl.createDocumentType(rootElement, publicId, systemId);
            fCurrentNode.appendChild(fDocumentType);
        }
    } else {
        fDocumentTypeIndex = fDeferredDocumentImpl.createDeferredDocumentType(rootElement, publicId, systemId);
        fDeferredDocumentImpl.appendChild(fCurrentNodeIndex, fDocumentTypeIndex);
    }
}
	// emptyElement(QName,XMLAttributes)
/**
     * Character content.
     *
     * @param text The content.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void characters(XMLString text, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>characters(): " + text.toString());
    }
    if (!fDeferNodeExpansion) {
        if (fFilterReject) {
            return;
        }
        if (fInCDATASection && fCreateCDATANodes) {
            if (fCurrentCDATASection == null) {
                fCurrentCDATASection = fDocument.createCDATASection(text.toString());
                fCurrentNode.appendChild(fCurrentCDATASection);
                fCurrentNode = fCurrentCDATASection;
            } else {
                fCurrentCDATASection.appendData(text.toString());
            }
        } else if (!fInDTD) {
            // character call with empty data
            if (text.length == 0) {
                return;
            }
            String value = text.toString();
            Node child = fCurrentNode.getLastChild();
            if (child != null && child.getNodeType() == Node.TEXT_NODE) {
                // collect all the data into the string buffer.
                if (fFirstChunk) {
                    if (fDocumentImpl != null) {
                        fStringBuffer.append(((TextImpl) child).removeData());
                    } else {
                        fStringBuffer.append(((Text) child).getData());
                        ((Text) child).setNodeValue(null);
                    }
                    fFirstChunk = false;
                }
                fStringBuffer.append(value);
            } else {
                fFirstChunk = true;
                Text textNode = fDocument.createTextNode(value);
                fCurrentNode.appendChild(textNode);
            }
        }
    } else {
        // the DOM in the deferred case.
        if (fInCDATASection && fCreateCDATANodes) {
            if (fCurrentCDATASectionIndex == -1) {
                int cs = fDeferredDocumentImpl.createDeferredCDATASection(text.toString());
                fDeferredDocumentImpl.appendChild(fCurrentNodeIndex, cs);
                fCurrentCDATASectionIndex = cs;
                fCurrentNodeIndex = cs;
            } else {
                int txt = fDeferredDocumentImpl.createDeferredTextNode(text.toString(), false);
                fDeferredDocumentImpl.appendChild(fCurrentNodeIndex, txt);
            }
        } else if (!fInDTD) {
            // character call with empty data
            if (text.length == 0) {
                return;
            }
            String value = text.toString();
            int txt = fDeferredDocumentImpl.createDeferredTextNode(value, false);
            fDeferredDocumentImpl.appendChild(fCurrentNodeIndex, txt);
        }
    }
}
	// characters(XMLString)
/**
     * Ignorable whitespace. For this method to be called, the document
     * source must have some way of determining that the text containing
     * only whitespace characters should be considered ignorable. For
     * example, the validator can determine if a length of whitespace
     * characters in the document are ignorable based on the element
     * content model.
     *
     * @param text The ignorable whitespace.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
    if (!fIncludeIgnorableWhitespace || fFilterReject) {
        return;
    }
    if (!fDeferNodeExpansion) {
        Node child = fCurrentNode.getLastChild();
        if (child != null && child.getNodeType() == Node.TEXT_NODE) {
            Text textNode = (Text) child;
            textNode.appendData(text.toString());
        } else {
            Text textNode = fDocument.createTextNode(text.toString());
            if (fDocumentImpl != null) {
                TextImpl textNodeImpl = (TextImpl) textNode;
                textNodeImpl.setIgnorableWhitespace(true);
            }
            fCurrentNode.appendChild(textNode);
        }
    } else {
        // The Text normalization is taken care of within the DOM in the
        // deferred case.
        int txt = fDeferredDocumentImpl.createDeferredTextNode(text.toString(), true);
        fDeferredDocumentImpl.appendChild(fCurrentNodeIndex, txt);
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
    fInCDATASection = true;
    if (!fDeferNodeExpansion) {
        if (fFilterReject) {
            return;
        }
        if (fCreateCDATANodes) {
            setCharacterData(false);
        }
    }
}
	// endCDATA()
/**
     * The end of the document.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endDocument(Augmentations augs) throws XNIException {
    if (!fDeferNodeExpansion) {
        // set DOM error checking back on
        if (fDocumentImpl != null) {
            fDocumentImpl.setStrictErrorChecking(true);
        }
        fCurrentNode = null;
    } else {
        fCurrentNodeIndex = -1;
    }
}
	// endDocument()
/**
     * This method notifies the end of a general entity.
     * <p>
     * <strong>Note:</strong> This method is not called for entity references
     * appearing as part of attribute values.
     *
     * @param name   The name of the entity.
     * @param augs   Additional information that may include infoset augmentations
     *
     * @exception XNIException
     *                   Thrown by handler to signal an error.
     */
public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>endGeneralEntity: (" + name + ")");
    }
    if (!fDeferNodeExpansion) {
        if (fFilterReject) {
            return;
        }
        setCharacterData(true);
        if (fDocumentType != null) {
            // get current entity declaration
            NamedNodeMap entities = fDocumentType.getEntities();
            fCurrentEntityDecl = (EntityImpl) entities.getNamedItem(name);
            if (fCurrentEntityDecl != null) {
                if (fCurrentEntityDecl != null && fCurrentEntityDecl.getFirstChild() == null) {
                    fCurrentEntityDecl.setReadOnly(false, true);
                    Node child = fCurrentNode.getFirstChild();
                    while (child != null) {
                        Node copy = child.cloneNode(true);
                        fCurrentEntityDecl.appendChild(copy);
                        child = child.getNextSibling();
                    }
                    fCurrentEntityDecl.setReadOnly(true, true);
                //entities.setNamedItem(fCurrentEntityDecl);
                }
                fCurrentEntityDecl = null;
            }
        }
        fInEntityRef = false;
        boolean removeEntityRef = false;
        if (fCreateEntityRefNodes) {
            if (fDocumentImpl != null) {
                // Make entity ref node read only
                ((NodeImpl) fCurrentNode).setReadOnly(true, true);
            }
            if (fDOMFilter != null && (fDOMFilter.getWhatToShow() & NodeFilter.SHOW_ENTITY_REFERENCE) != 0) {
                short code = fDOMFilter.acceptNode(fCurrentNode);
                switch(code) {
                    case LSParserFilter.FILTER_INTERRUPT:
                        {
                            throw abort;
                        }
                    case LSParserFilter.FILTER_REJECT:
                        {
                            Node parent = fCurrentNode.getParentNode();
                            parent.removeChild(fCurrentNode);
                            fCurrentNode = parent;
                            return;
                        }
                    case LSParserFilter.FILTER_SKIP:
                        {
                            // make sure we don't loose chars if next event is characters()
                            fFirstChunk = true;
                            removeEntityRef = true;
                            break;
                        }
                    default:
                        {
                            fCurrentNode = fCurrentNode.getParentNode();
                        }
                }
            } else {
                fCurrentNode = fCurrentNode.getParentNode();
            }
        }
        if (!fCreateEntityRefNodes || removeEntityRef) {
            // move entity reference children to the list of
            // siblings of its parent and remove entity reference
            NodeList children = fCurrentNode.getChildNodes();
            Node parent = fCurrentNode.getParentNode();
            int length = children.getLength();
            if (length > 0) {
                // get previous sibling of the entity reference
                Node node = fCurrentNode.getPreviousSibling();
                // normalize text nodes
                Node child = children.item(0);
                if (node != null && node.getNodeType() == Node.TEXT_NODE && child.getNodeType() == Node.TEXT_NODE) {
                    ((Text) node).appendData(child.getNodeValue());
                    fCurrentNode.removeChild(child);
                } else {
                    node = parent.insertBefore(child, fCurrentNode);
                    handleBaseURI(node);
                }
                for (int i = 1; i < length; i++) {
                    node = parent.insertBefore(children.item(0), fCurrentNode);
                    handleBaseURI(node);
                }
            }
            // length > 0
            parent.removeChild(fCurrentNode);
            fCurrentNode = parent;
        }
    } else {
        if (fDocumentTypeIndex != -1) {
            // find corresponding Entity decl
            int node = fDeferredDocumentImpl.getLastChild(fDocumentTypeIndex, false);
            while (node != -1) {
                short nodeType = fDeferredDocumentImpl.getNodeType(node, false);
                if (nodeType == Node.ENTITY_NODE) {
                    String nodeName = fDeferredDocumentImpl.getNodeName(node, false);
                    if (nodeName.equals(name)) {
                        fDeferredEntityDecl = node;
                        break;
                    }
                }
                node = fDeferredDocumentImpl.getRealPrevSibling(node, false);
            }
        }
        if (fDeferredEntityDecl != -1 && fDeferredDocumentImpl.getLastChild(fDeferredEntityDecl, false) == -1) {
            // entity definition exists and it does not have any children
            int prevIndex = -1;
            int childIndex = fDeferredDocumentImpl.getLastChild(fCurrentNodeIndex, false);
            while (childIndex != -1) {
                int cloneIndex = fDeferredDocumentImpl.cloneNode(childIndex, true);
                fDeferredDocumentImpl.insertBefore(fDeferredEntityDecl, cloneIndex, prevIndex);
                prevIndex = cloneIndex;
                childIndex = fDeferredDocumentImpl.getRealPrevSibling(childIndex, false);
            }
        }
        if (fCreateEntityRefNodes) {
            fCurrentNodeIndex = fDeferredDocumentImpl.getParentNode(fCurrentNodeIndex, false);
        } else {
            //!fCreateEntityRefNodes
            // move children of entity ref before the entity ref.
            // remove entity ref.
            // holds a child of entity ref
            int childIndex = fDeferredDocumentImpl.getLastChild(fCurrentNodeIndex, false);
            int parentIndex = fDeferredDocumentImpl.getParentNode(fCurrentNodeIndex, false);
            int prevIndex = fCurrentNodeIndex;
            int lastChild = childIndex;
            int sibling = -1;
            while (childIndex != -1) {
                handleBaseURI(childIndex);
                sibling = fDeferredDocumentImpl.getRealPrevSibling(childIndex, false);
                fDeferredDocumentImpl.insertBefore(parentIndex, childIndex, prevIndex);
                prevIndex = childIndex;
                childIndex = sibling;
            }
            if (lastChild != -1)
                fDeferredDocumentImpl.setAsLastChild(parentIndex, lastChild);
            else {
                sibling = fDeferredDocumentImpl.getRealPrevSibling(prevIndex, false);
                fDeferredDocumentImpl.setAsLastChild(parentIndex, sibling);
            }
            fCurrentNodeIndex = parentIndex;
        }
        fDeferredEntityDecl = -1;
    }
}
	// endGeneralEntity(String, Augmentations)
/**
     * Record baseURI information for the Element (by adding xml:base attribute)
     * or for the ProcessingInstruction (by setting a baseURI field)
     * Non deferred DOM.
     *
     * @param node
     */
protected final void handleBaseURI(Node node) {
    if (fDocumentImpl != null) {
        // REVISIT: remove dependency on our implementation when
        //          DOM L3 becomes REC
        String baseURI = null;
        short nodeType = node.getNodeType();
        if (nodeType == Node.ELEMENT_NODE) {
            // do nothing
            if (fNamespaceAware) {
                if (((Element) node).getAttributeNodeNS("http://www.w3.org/XML/1998/namespace", "base") != null) {
                    return;
                }
            } else if (((Element) node).getAttributeNode("xml:base") != null) {
                return;
            }
            // retrive the baseURI from the entity reference
            baseURI = ((EntityReferenceImpl) fCurrentNode).getBaseURI();
            if (baseURI != null && !baseURI.equals(fDocumentImpl.getDocumentURI())) {
                if (fNamespaceAware) {
                    ((Element) node).setAttributeNS("http://www.w3.org/XML/1998/namespace", "base", baseURI);
                } else {
                    ((Element) node).setAttribute("xml:base", baseURI);
                }
            }
        } else if (nodeType == Node.PROCESSING_INSTRUCTION_NODE) {
            baseURI = ((EntityReferenceImpl) fCurrentNode).getBaseURI();
            if (baseURI != null && fErrorHandler != null) {
                DOMErrorImpl error = new DOMErrorImpl();
                error.fType = "pi-base-uri-not-preserved";
                error.fRelatedData = baseURI;
                error.fSeverity = DOMError.SEVERITY_WARNING;
                fErrorHandler.getErrorHandler().handleError(error);
            }
        }
    }
}
	// endGeneralEntity(String, Augmentations)
/**
     * Record baseURI information for the Element (by adding xml:base attribute)
     * or for the ProcessingInstruction (by setting a baseURI field)
     * Non deferred DOM.
     *
     * @param node
     */
protected final void handleBaseURI(Node node) {
    if (fDocumentImpl != null) {
        // REVISIT: remove dependency on our implementation when
        //          DOM L3 becomes REC
        String baseURI = null;
        short nodeType = node.getNodeType();
        if (nodeType == Node.ELEMENT_NODE) {
            // do nothing
            if (fNamespaceAware) {
                if (((Element) node).getAttributeNodeNS("http://www.w3.org/XML/1998/namespace", "base") != null) {
                    return;
                }
            } else if (((Element) node).getAttributeNode("xml:base") != null) {
                return;
            }
            // retrive the baseURI from the entity reference
            baseURI = ((EntityReferenceImpl) fCurrentNode).getBaseURI();
            if (baseURI != null && !baseURI.equals(fDocumentImpl.getDocumentURI())) {
                if (fNamespaceAware) {
                    ((Element) node).setAttributeNS("http://www.w3.org/XML/1998/namespace", "base", baseURI);
                } else {
                    ((Element) node).setAttribute("xml:base", baseURI);
                }
            }
        } else if (nodeType == Node.PROCESSING_INSTRUCTION_NODE) {
            baseURI = ((EntityReferenceImpl) fCurrentNode).getBaseURI();
            if (baseURI != null && fErrorHandler != null) {
                DOMErrorImpl error = new DOMErrorImpl();
                error.fType = "pi-base-uri-not-preserved";
                error.fRelatedData = baseURI;
                error.fSeverity = DOMError.SEVERITY_WARNING;
                fErrorHandler.getErrorHandler().handleError(error);
            }
        }
    }
}
	/**
     *
     * Record baseURI information for the Element (by adding xml:base attribute)
     * or for the ProcessingInstruction (by setting a baseURI field)
     * Deferred DOM.
     *
     * @param node
     */
protected final void handleBaseURI(int node) {
    short nodeType = fDeferredDocumentImpl.getNodeType(node, false);
    if (nodeType == Node.ELEMENT_NODE) {
        String baseURI = fDeferredDocumentImpl.getNodeValueString(fCurrentNodeIndex, false);
        if (baseURI == null) {
            baseURI = fDeferredDocumentImpl.getDeferredEntityBaseURI(fDeferredEntityDecl);
        }
        if (baseURI != null && !baseURI.equals(fDeferredDocumentImpl.getDocumentURI())) {
            fDeferredDocumentImpl.setDeferredAttribute(node, "xml:base", "http://www.w3.org/XML/1998/namespace", baseURI, true);
        }
    } else if (nodeType == Node.PROCESSING_INSTRUCTION_NODE) {
        // retrieve baseURI from the entity reference
        String baseURI = fDeferredDocumentImpl.getNodeValueString(fCurrentNodeIndex, false);
        if (baseURI == null) {
            // try baseURI of the entity declaration
            baseURI = fDeferredDocumentImpl.getDeferredEntityBaseURI(fDeferredEntityDecl);
        }
        if (baseURI != null && fErrorHandler != null) {
            DOMErrorImpl error = new DOMErrorImpl();
            error.fType = "pi-base-uri-not-preserved";
            error.fRelatedData = baseURI;
            error.fSeverity = DOMError.SEVERITY_WARNING;
            fErrorHandler.getErrorHandler().handleError(error);
        }
    }
}
	/**
     *
     * Record baseURI information for the Element (by adding xml:base attribute)
     * or for the ProcessingInstruction (by setting a baseURI field)
     * Deferred DOM.
     *
     * @param node
     */
protected final void handleBaseURI(int node) {
    short nodeType = fDeferredDocumentImpl.getNodeType(node, false);
    if (nodeType == Node.ELEMENT_NODE) {
        String baseURI = fDeferredDocumentImpl.getNodeValueString(fCurrentNodeIndex, false);
        if (baseURI == null) {
            baseURI = fDeferredDocumentImpl.getDeferredEntityBaseURI(fDeferredEntityDecl);
        }
        if (baseURI != null && !baseURI.equals(fDeferredDocumentImpl.getDocumentURI())) {
            fDeferredDocumentImpl.setDeferredAttribute(node, "xml:base", "http://www.w3.org/XML/1998/namespace", baseURI, true);
        }
    } else if (nodeType == Node.PROCESSING_INSTRUCTION_NODE) {
        // retrieve baseURI from the entity reference
        String baseURI = fDeferredDocumentImpl.getNodeValueString(fCurrentNodeIndex, false);
        if (baseURI == null) {
            // try baseURI of the entity declaration
            baseURI = fDeferredDocumentImpl.getDeferredEntityBaseURI(fDeferredEntityDecl);
        }
        if (baseURI != null && fErrorHandler != null) {
            DOMErrorImpl error = new DOMErrorImpl();
            error.fType = "pi-base-uri-not-preserved";
            error.fRelatedData = baseURI;
            error.fSeverity = DOMError.SEVERITY_WARNING;
            fErrorHandler.getErrorHandler().handleError(error);
        }
    }
}
	// startConditional(short)
/**
     * The end of a conditional section.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endConditional(Augmentations augs) throws XNIException {
}
	// startAttlist(String)
/**
     * The end of an attribute list.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endAttlist(Augmentations augs) throws XNIException {
}
	// startExternalSubset(Augmentations)
/**
     * The end of the DTD external subset.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endExternalSubset(Augmentations augs) throws XNIException {
    fInDTDExternalSubset = false;
    fBaseURIStack.pop();
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
    // internal subset string
    String publicId = identifier.getPublicId();
    String literalSystemId = identifier.getLiteralSystemId();
    if (fInternalSubset != null && !fInDTDExternalSubset) {
        fInternalSubset.append("<!NOTATION ");
        fInternalSubset.append(name);
        if (publicId != null) {
            fInternalSubset.append(" PUBLIC '");
            fInternalSubset.append(publicId);
            if (literalSystemId != null) {
                fInternalSubset.append("' '");
                fInternalSubset.append(literalSystemId);
            }
        } else {
            fInternalSubset.append(" SYSTEM '");
            fInternalSubset.append(literalSystemId);
        }
        fInternalSubset.append("'>\n");
    }
    // create full node
    if (fDocumentImpl != null && fDocumentType != null) {
        NamedNodeMap notations = fDocumentType.getNotations();
        if (notations.getNamedItem(name) == null) {
            NotationImpl notation = (NotationImpl) fDocumentImpl.createNotation(name);
            notation.setPublicId(publicId);
            notation.setSystemId(literalSystemId);
            notation.setBaseURI(identifier.getBaseSystemId());
            notations.setNamedItem(notation);
        }
    }
    // create deferred node
    if (fDocumentTypeIndex != -1) {
        boolean found = false;
        int nodeIndex = fDeferredDocumentImpl.getLastChild(fDocumentTypeIndex, false);
        while (nodeIndex != -1) {
            short nodeType = fDeferredDocumentImpl.getNodeType(nodeIndex, false);
            if (nodeType == Node.NOTATION_NODE) {
                String nodeName = fDeferredDocumentImpl.getNodeName(nodeIndex, false);
                if (nodeName.equals(name)) {
                    found = true;
                    break;
                }
            }
            nodeIndex = fDeferredDocumentImpl.getPrevSibling(nodeIndex, false);
        }
        if (!found) {
            int notationIndex = fDeferredDocumentImpl.createDeferredNotation(name, publicId, literalSystemId, identifier.getBaseSystemId());
            fDeferredDocumentImpl.appendChild(fDocumentTypeIndex, notationIndex);
        }
    }
}
	// notationDecl(String,XMLResourceIdentifier, Augmentations)
/**
     * Characters within an IGNORE conditional section.
     *
     * @param text The ignored text.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void ignoredCharacters(XMLString text, Augmentations augs) throws XNIException {
}
	// ignoredCharacters(XMLString, Augmentations)
/**
     * An element declaration.
     *
     * @param name         The name of the element.
     * @param contentModel The element content model.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void elementDecl(String name, String contentModel, Augmentations augs) throws XNIException {
    // internal subset string
    if (fInternalSubset != null && !fInDTDExternalSubset) {
        fInternalSubset.append("<!ELEMENT ");
        fInternalSubset.append(name);
        fInternalSubset.append(' ');
        fInternalSubset.append(contentModel);
        fInternalSubset.append(">\n");
    }
}
	// attributeDecl(String,String,String,String[],String,XMLString, XMLString, Augmentations)
/**
     * The start of an attribute list.
     *
     * @param elementName The name of the element that this attribute
     *                    list is associated with.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void startAttlist(String elementName, Augmentations augs) throws XNIException {
}
	/**
     * @see org.w3c.dom.ls.LSParser#abort()
     */
public void abort() {
    throw abort;
}
	// reset()
/**
     * Set the locale to use for messages.
     *
     * @param locale The locale object to use for localization of messages.
     *
     */
public void setLocale(Locale locale) {
    fConfiguration.setLocale(locale);
}
}
