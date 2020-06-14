public class Class2 {
	private Object DOM_DOMAIN;
	private Object out;

	public Class2(Object DOM_DOMAIN, Object out){
		this.DOM_DOMAIN = DOM_DOMAIN;
		this.out = out;
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
}