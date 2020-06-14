public class Class0 {
	private Object expandedSystemId;
	private Object notation;
	private Object EMPTY_STRING;
	private Object XML_URI;
	private Object systemId;
	private Object augmentations;
	private Object offset;
	private Object ch;
	private Object HTTP_ACCEPT_LANGUAGE;
	private Object length;
	private Object TRUE;
	private Object baseURI;
	private Object HTTP_ACCEPT;
	private Object name;
	private Object publicId;

	public Class0(Object expandedSystemId, Object notation, Object EMPTY_STRING, Object XML_URI, Object systemId, Object augmentations, Object offset, Object ch, Object HTTP_ACCEPT_LANGUAGE, Object length, Object TRUE, Object baseURI, Object HTTP_ACCEPT, Object name, Object publicId){
		this.expandedSystemId = expandedSystemId;
		this.notation = notation;
		this.EMPTY_STRING = EMPTY_STRING;
		this.XML_URI = XML_URI;
		this.systemId = systemId;
		this.augmentations = augmentations;
		this.offset = offset;
		this.ch = ch;
		this.HTTP_ACCEPT_LANGUAGE = HTTP_ACCEPT_LANGUAGE;
		this.length = length;
		this.TRUE = TRUE;
		this.baseURI = baseURI;
		this.HTTP_ACCEPT = HTTP_ACCEPT;
		this.name = name;
		this.publicId = publicId;
	}
	/**
     * Caches an unparsed entity.
     * @param name the name of the unparsed entity
     * @param identifier the location of the unparsed entity
     * @param augmentations any Augmentations that were on the original unparsed entity declaration
     */
protected void addUnparsedEntity(String name, XMLResourceIdentifier identifier, String notation, Augmentations augmentations) {
    UnparsedEntity ent = new UnparsedEntity();
    ent.name = name;
    ent.systemId = identifier.getLiteralSystemId();
    ent.publicId = identifier.getPublicId();
    ent.baseURI = identifier.getBaseSystemId();
    ent.expandedSystemId = identifier.getExpandedSystemId();
    ent.notation = notation;
    ent.augmentations = augmentations;
    fUnparsedEntities.add(ent);
}
	/**
     * Caches a notation.
     * @param name the name of the notation
     * @param identifier the location of the notation
     * @param augmentations any Augmentations that were on the original notation declaration
     */
protected void addNotation(String name, XMLResourceIdentifier identifier, Augmentations augmentations) {
    Notation not = new Notation();
    not.name = name;
    not.systemId = identifier.getLiteralSystemId();
    not.publicId = identifier.getPublicId();
    not.baseURI = identifier.getBaseSystemId();
    not.expandedSystemId = identifier.getExpandedSystemId();
    not.augmentations = augmentations;
    fNotations.add(not);
}
	/**
     * The purpose of this method is to check if a Notation conflicts with a previously
     * declared notation in the current pipeline stack.  If there is no conflict, the
     * Notation is sent by the root pipeline.
     *
     * @param not the Notation to check for conflicts
     */
protected void checkAndSendNotation(Notation not) {
    if (isRootDocument()) {
        int index = fNotations.indexOf(not);
        if (index == -1) {
            // There is no notation with the same name that we have sent.
            XMLResourceIdentifier id = new XMLResourceIdentifierImpl(not.publicId, not.systemId, not.baseURI, not.expandedSystemId);
            addNotation(not.name, id, not.augmentations);
            if (fSendUEAndNotationEvents && fDTDHandler != null) {
                fDTDHandler.notationDecl(not.name, id, not.augmentations);
            }
        } else {
            Notation localNotation = (Notation) fNotations.get(index);
            if (!not.isDuplicate(localNotation)) {
                reportFatalError("NonDuplicateNotation", new Object[] { not.name });
            }
        }
    } else {
        fParentXIncludeHandler.checkAndSendNotation(not);
    }
}
	/**
     * Checks if a Notation with the given name was declared in the DTD of the document
     * for the current pipeline.  If so, that Notation is passed to the root pipeline to
     * be checked for conflicts, and sent to the root DTDHandler
     *
     * @param notName the name of the Notation to check
     */
protected void checkNotation(String notName) {
    Notation not = new Notation();
    not.name = notName;
    int index = fNotations.indexOf(not);
    if (index != -1) {
        not = (Notation) fNotations.get(index);
        checkAndSendNotation(not);
    }
}
	/**
     * The purpose of this method is to check if an UnparsedEntity conflicts with a previously
     * declared entity in the current pipeline stack.  If there is no conflict, the
     * UnparsedEntity is sent by the root pipeline.
     *
     * @param ent the UnparsedEntity to check for conflicts
     */
protected void checkAndSendUnparsedEntity(UnparsedEntity ent) {
    if (isRootDocument()) {
        int index = fUnparsedEntities.indexOf(ent);
        if (index == -1) {
            // There is no unparsed entity with the same name that we have sent.
            // Calling unparsedEntityDecl() will add the entity to our local store,
            // and also send the unparsed entity to the DTDHandler
            XMLResourceIdentifier id = new XMLResourceIdentifierImpl(ent.publicId, ent.systemId, ent.baseURI, ent.expandedSystemId);
            addUnparsedEntity(ent.name, id, ent.notation, ent.augmentations);
            if (fSendUEAndNotationEvents && fDTDHandler != null) {
                fDTDHandler.unparsedEntityDecl(ent.name, id, ent.notation, ent.augmentations);
            }
        } else {
            UnparsedEntity localEntity = (UnparsedEntity) fUnparsedEntities.get(index);
            if (!ent.isDuplicate(localEntity)) {
                reportFatalError("NonDuplicateUnparsedEntity", new Object[] { ent.name });
            }
        }
    } else {
        fParentXIncludeHandler.checkAndSendUnparsedEntity(ent);
    }
}
	/**
     * Checks if an UnparsedEntity with the given name was declared in the DTD of the document
     * for the current pipeline.  If so, then the notation for the UnparsedEntity is checked.
     * If that turns out okay, then the UnparsedEntity is passed to the root pipeline to
     * be checked for conflicts, and sent to the root DTDHandler.
     *
     * @param entName the name of the UnparsedEntity to check
     */
protected void checkUnparsedEntity(String entName) {
    UnparsedEntity ent = new UnparsedEntity();
    ent.name = entName;
    int index = fUnparsedEntities.indexOf(ent);
    if (index != -1) {
        ent = (UnparsedEntity) fUnparsedEntities.get(index);
        // first check the notation of the unparsed entity
        checkNotation(ent.notation);
        checkAndSendUnparsedEntity(ent);
    }
}
	/**
     * Modify the augmentations.  Add an [included] infoset item, if the current
     * element is a top level included item.
     * @param augs the Augmentations to modify.
     * @return the modified Augmentations
     */
protected Augmentations modifyAugmentations(Augmentations augs) {
    return modifyAugmentations(augs, false);
}
	/**
     * Modify the augmentations.  Add an [included] infoset item, if the current
     * element is a top level included item.
     * @param augs the Augmentations to modify.
     * @return the modified Augmentations
     */
protected Augmentations modifyAugmentations(Augmentations augs) {
    return modifyAugmentations(augs, false);
}
	/**
     * Modify the augmentations.  Add an [included] infoset item, if <code>force</code>
     * is true, or if the current element is a top level included item.
     * @param augs the Augmentations to modify.
     * @param force whether to force modification
     * @return the modified Augmentations
     */
protected Augmentations modifyAugmentations(Augmentations augs, boolean force) {
    if (force || isTopLevelIncludedItem()) {
        if (augs == null) {
            augs = new AugmentationsImpl();
        }
        augs.putItem(XINCLUDE_INCLUDED, Boolean.TRUE);
    }
    return augs;
}
	/**
     * Modify the augmentations.  Add an [included] infoset item, if <code>force</code>
     * is true, or if the current element is a top level included item.
     * @param augs the Augmentations to modify.
     * @param force whether to force modification
     * @return the modified Augmentations
     */
protected Augmentations modifyAugmentations(Augmentations augs, boolean force) {
    if (force || isTopLevelIncludedItem()) {
        if (augs == null) {
            augs = new AugmentationsImpl();
        }
        augs.putItem(XINCLUDE_INCLUDED, Boolean.TRUE);
    }
    return augs;
}
	// reset(XMLComponentManager)
/**
     * Returns a list of feature identifiers that are recognized by
     * this component. This method may return null if no features
     * are recognized by this component.
     */
public String[] getRecognizedFeatures() {
    return RECOGNIZED_FEATURES;
}
	// setFeature(String,boolean)
/**
     * Returns a list of property identifiers that are recognized by
     * this component. This method may return null if no properties
     * are recognized by this component.
     */
public String[] getRecognizedProperties() {
    return RECOGNIZED_PROPERTIES;
}
	// XMLDocumentHandler methods
/**
     * Event sent at the start of the document.
     *
     * A fatal error will occur here, if it is detected that this document has been processed
     * before.
     *
     * This event is only passed on to the document handler if this is the root document.
     */
public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
    // we do this to ensure that the proper location is reported in errors
    // otherwise, the locator from the root document would always be used
    fErrorReporter.setDocumentLocator(locator);
    if (!isRootDocument() && fParentXIncludeHandler.searchForRecursiveIncludes(locator)) {
        reportFatalError("RecursiveInclude", new Object[] { locator.getExpandedSystemId() });
    }
    if (!(namespaceContext instanceof XIncludeNamespaceSupport)) {
        reportFatalError("IncompatibleNamespaceContext");
    }
    fNamespaceContext = (XIncludeNamespaceSupport) namespaceContext;
    fDocLocation = locator;
    // initialize the current base URI
    fCurrentBaseURI.setBaseSystemId(locator.getBaseSystemId());
    fCurrentBaseURI.setExpandedSystemId(locator.getExpandedSystemId());
    fCurrentBaseURI.setLiteralSystemId(locator.getLiteralSystemId());
    saveBaseURI();
    if (augs == null) {
        augs = new AugmentationsImpl();
    }
    augs.putItem(CURRENT_BASE_URI, fCurrentBaseURI);
    // initialize the current language
    fCurrentLanguage = XMLSymbols.EMPTY_STRING;
    saveLanguage(fCurrentLanguage);
    if (isRootDocument() && fDocumentHandler != null) {
        fDocumentHandler.startDocument(locator, encoding, namespaceContext, augs);
    }
}
	/**
     * Returns true if the current element is a top level included item.  This means
     * it's either the child of a fallback element, or the top level item in an
     * included document
     * @return true if the current element is a top level included item
     */
protected boolean isTopLevelIncludedItem() {
    return isTopLevelIncludedItemViaInclude() || isTopLevelIncludedItemViaFallback();
}
	/** 
     * Returns the current element depth of the result infoset.
     */
private int getResultDepth() {
    return fResultDepth;
}
	/**
     * Set the parent of this XIncludeHandler in the tree
     * @param parent
     */
protected void setParent(XIncludeHandler parent) {
    fParentXIncludeHandler = parent;
}
	// used to know whether to pass declarations to the document handler
protected boolean isRootDocument() {
    return fParentXIncludeHandler == null;
}
	/**
     * Checks whether the string only contains white space characters.
     * 
     * @param value the text to check
     */
private void checkWhitespace(XMLString value) {
    int end = value.offset + value.length;
    for (int i = value.offset; i < end; ++i) {
        if (!XMLChar.isSpace(value.ch[i])) {
            reportFatalError("ContentIllegalAtTopLevel");
            return;
        }
    }
}
	/**
     * Checks whether the root element has already been processed.
     */
private void checkMultipleRootElements() {
    if (getRootElementProcessed()) {
        reportFatalError("MultipleRootElements");
    }
    setRootElementProcessed(true);
}
	/**
     * Sets whether the root element has been processed.
     */
private void setRootElementProcessed(boolean seenRoot) {
    if (isRootDocument()) {
        fSeenRootElement = seenRoot;
        return;
    }
    fParentXIncludeHandler.setRootElementProcessed(seenRoot);
}
	/**
     * Returns whether the root element has been processed.
     */
private boolean getRootElementProcessed() {
    return isRootDocument() ? fSeenRootElement : fParentXIncludeHandler.getRootElementProcessed();
}
	// The following methods are used for XML Base processing
/**
     * Saves the current base URI to the top of the stack.
     */
protected void saveBaseURI() {
    fBaseURIScope.push(fDepth);
    fBaseURI.push(fCurrentBaseURI.getBaseSystemId());
    fLiteralSystemID.push(fCurrentBaseURI.getLiteralSystemId());
    fExpandedSystemID.push(fCurrentBaseURI.getExpandedSystemId());
}
	/**
     * Discards the URIs at the top of the stack, and restores the ones beneath it.
     */
protected void restoreBaseURI() {
    fBaseURI.pop();
    fLiteralSystemID.pop();
    fExpandedSystemID.pop();
    fBaseURIScope.pop();
    fCurrentBaseURI.setBaseSystemId((String) fBaseURI.peek());
    fCurrentBaseURI.setLiteralSystemId((String) fLiteralSystemID.peek());
    fCurrentBaseURI.setExpandedSystemId((String) fExpandedSystemID.peek());
}
	/**
     * Discards the language at the top of the stack, and returns the one beneath it.
     */
public String restoreLanguage() {
    fLanguageStack.pop();
    fLanguageScope.pop();
    return (String) fLanguageStack.peek();
}
	/**
     * Search for a xml:base attribute, and if one is found, put the new base URI into
     * effect.
     */
protected void processXMLBaseAttributes(XMLAttributes attributes) {
    String baseURIValue = attributes.getValue(NamespaceContext.XML_URI, "base");
    if (baseURIValue != null) {
        try {
            String expandedValue = XMLEntityManager.expandSystemId(baseURIValue, fCurrentBaseURI.getExpandedSystemId(), false);
            fCurrentBaseURI.setLiteralSystemId(baseURIValue);
            fCurrentBaseURI.setBaseSystemId(fCurrentBaseURI.getExpandedSystemId());
            fCurrentBaseURI.setExpandedSystemId(expandedValue);
            // push the new values on the stack
            saveBaseURI();
        } catch (MalformedURIException e) {
        // REVISIT: throw error here
        }
    }
}
	/**
     * Returns a new <code>XMLInputSource</code> from the given parameters.
     */
private XMLInputSource createInputSource(String publicId, String systemId, String baseSystemId, String accept, String acceptLanguage) {
    HTTPInputSource httpSource = new HTTPInputSource(publicId, systemId, baseSystemId);
    if (accept != null && accept.length() > 0) {
        httpSource.setHTTPRequestProperty(XIncludeHandler.HTTP_ACCEPT, accept);
    }
    if (acceptLanguage != null && acceptLanguage.length() > 0) {
        httpSource.setHTTPRequestProperty(XIncludeHandler.HTTP_ACCEPT_LANGUAGE, acceptLanguage);
    }
    return httpSource;
}
}