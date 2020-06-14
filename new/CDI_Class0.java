public class Class0 {
	private Object next;
	private Object fChildIndex;
	private Object fLength;
	private Object docType;
	private Object docElement;
	private Object errorChecking;
	private Object NODE_CLONED;
	private Object nextSibling;
	private Object fOwner;
	private Object fNodeListCache;
	private Object fChild;
	private Object allowGrammarAccess;

	public Class0(Object next, Object fChildIndex, Object fLength, Object docType, Object docElement, Object errorChecking, Object NODE_CLONED, Object nextSibling, Object fOwner, Object fNodeListCache, Object fChild, Object allowGrammarAccess){
		this.next = next;
		this.fChildIndex = fChildIndex;
		this.fLength = fLength;
		this.docType = docType;
		this.docElement = docElement;
		this.errorChecking = errorChecking;
		this.NODE_CLONED = NODE_CLONED;
		this.nextSibling = nextSibling;
		this.fOwner = fOwner;
		this.fNodeListCache = fNodeListCache;
		this.fChild = fChild;
		this.allowGrammarAccess = allowGrammarAccess;
	}
	/**
     * Deep-clone a document, including fixing ownerDoc for the cloned
     * children. Note that this requires bypassing the WRONG_DOCUMENT_ERR
     * protection. I've chosen to implement it by calling importNode
     * which is DOM Level 2.
     *
     * @return org.w3c.dom.Node
     * @param deep boolean, iff true replicate children
     */
public Node cloneNode(boolean deep) {
    CoreDocumentImpl newdoc = new CoreDocumentImpl();
    callUserDataHandlers(this, newdoc, UserDataHandler.NODE_CLONED);
    cloneNode(newdoc, deep);
    return newdoc;
}
	/**
     * Deep-clone a document, including fixing ownerDoc for the cloned
     * children. Note that this requires bypassing the WRONG_DOCUMENT_ERR
     * protection. I've chosen to implement it by calling importNode
     * which is DOM Level 2.
     *
     * @return org.w3c.dom.Node
     * @param deep boolean, iff true replicate children
     */
public Node cloneNode(boolean deep) {
    CoreDocumentImpl newdoc = new CoreDocumentImpl();
    callUserDataHandlers(this, newdoc, UserDataHandler.NODE_CLONED);
    cloneNode(newdoc, deep);
    return newdoc;
}
	// cloneNode(boolean):Node
/**
     * internal method to share code with subclass
     **/
protected void cloneNode(CoreDocumentImpl newdoc, boolean deep) {
    // clone the children by importing them
    if (needsSyncChildren()) {
        synchronizeChildren();
    }
    if (deep) {
        Hashtable reversedIdentifiers = null;
        if (identifiers != null) {
            // Build a reverse mapping from element to identifier.
            reversedIdentifiers = new Hashtable();
            Enumeration elementIds = identifiers.keys();
            while (elementIds.hasMoreElements()) {
                Object elementId = elementIds.nextElement();
                reversedIdentifiers.put(identifiers.get(elementId), elementId);
            }
        }
        // Copy children into new document.
        for (ChildNode kid = firstChild; kid != null; kid = kid.nextSibling) {
            newdoc.appendChild(newdoc.importNode(kid, true, true, reversedIdentifiers));
        }
    }
    // experimental
    newdoc.allowGrammarAccess = allowGrammarAccess;
    newdoc.errorChecking = errorChecking;
}
	// cloneNode(boolean):Node
/**
     * internal method to share code with subclass
     **/
protected void cloneNode(CoreDocumentImpl newdoc, boolean deep) {
    // clone the children by importing them
    if (needsSyncChildren()) {
        synchronizeChildren();
    }
    if (deep) {
        Hashtable reversedIdentifiers = null;
        if (identifiers != null) {
            // Build a reverse mapping from element to identifier.
            reversedIdentifiers = new Hashtable();
            Enumeration elementIds = identifiers.keys();
            while (elementIds.hasMoreElements()) {
                Object elementId = elementIds.nextElement();
                reversedIdentifiers.put(identifiers.get(elementId), elementId);
            }
        }
        // Copy children into new document.
        for (ChildNode kid = firstChild; kid != null; kid = kid.nextSibling) {
            newdoc.appendChild(newdoc.importNode(kid, true, true, reversedIdentifiers));
        }
    }
    // experimental
    newdoc.allowGrammarAccess = allowGrammarAccess;
    newdoc.errorChecking = errorChecking;
}
	//
// Object methods
//
/** Clone. */
public Object clone() throws CloneNotSupportedException {
    CoreDocumentImpl newdoc = (CoreDocumentImpl) super.clone();
    newdoc.docType = null;
    newdoc.docElement = null;
    return newdoc;
}
	// replaceChild(Node,Node):Node
/*
     * Get Node text content
     * @since DOM Level 3
     */
public String getTextContent() throws DOMException {
    return null;
}
	/*
     * Set Node text content
     * @since DOM Level 3
     */
public void setTextContent(String textContent) throws DOMException {
// no-op
}
	// other document methods
/**
     * For XML, this provides access to the Document Type Definition.
     * For HTML documents, and XML documents which don't specify a DTD,
     * it will be null.
     */
public DocumentType getDoctype() {
    if (needsSyncChildren()) {
        synchronizeChildren();
    }
    return docType;
}
	/**
     * Convenience method, allowing direct access to the child node
     * which is considered the root of the actual document content. For
     * HTML, where it is legal to have more than one Element at the top
     * level of the document, we pick the one with the tagName
     * "HTML". For XML there should be only one top-level
     *
     * (HTML not yet supported.)
     */
public Element getDocumentElement() {
    if (needsSyncChildren()) {
        synchronizeChildren();
    }
    return docElement;
}
	/**
     * Return a <em>live</em> collection of all descendent Elements (not just
     * immediate children) having the specified tag name.
     *
     * @param tagname The type of Element we want to gather. "*" will be
     * taken as a wildcard, meaning "all elements in the document."
     *
     * @see DeepNodeListImpl
     */
public NodeList getElementsByTagName(String tagname) {
    return new DeepNodeListImpl(this, tagname);
}
	/*
     * DOM Level 3 WD - Experimental.
     */
public void setStrictErrorChecking(boolean check) {
    errorChecking = check;
}
	/**
     * Returns true if the DOM implementation performs error checking.
     */
public boolean getErrorChecking() {
    return errorChecking;
}
	/*
     * DOM Level 3 WD - Experimental.
     */
public boolean getStrictErrorChecking() {
    return errorChecking;
}
	/**
     * DOM Level 3 CR - Experimental. (Was getActualEncoding)
     *
     * An attribute specifying the encoding used for this document
     * at the time of the parsing. This is <code>null</code> when
     * it is not known, such as when the <code>Document</code> was
     * created in memory.
     * @since DOM Level 3
     */
public String getInputEncoding() {
    return actualEncoding;
}
	/**
     * DOM Internal
     * (Was a DOM L3 Core WD public interface method setActualEncoding )
     *
     * An attribute specifying the actual encoding of this document. This is
     * <code>null</code> otherwise.
     * <br> This attribute represents the property [character encoding scheme]
     * defined in .
     */
public void setInputEncoding(String value) {
    actualEncoding = value;
}
	/**
     * DOM Internal
     * (Was a DOM L3 Core WD public interface method setXMLEncoding )
     *
     * An attribute specifying, as part of the XML declaration,
     * the encoding of this document. This is null when unspecified.
     */
public void setXmlEncoding(String value) {
    encoding = value;
}
	/**
     * @deprecated This method is internal and only exists for
     * compatibility with older applications. New applications
     * should never call this method.
     */
public void setEncoding(String value) {
    setXmlEncoding(value);
}
	/**
     * DOM Level 3 WD - Experimental.
     * The encoding of this document (part of XML Declaration)
     */
public String getXmlEncoding() {
    return encoding;
}
	/**
     * @deprecated This method is internal and only exists for
     * compatibility with older applications. New applications
     * should never call this method.
     */
public String getEncoding() {
    return getXmlEncoding();
}
	/**
     * @deprecated This method is internal and only exists for
     * compatibility with older applications. New applications
     * should never call this method.
     */
public void setVersion(String value) {
    setXmlVersion(value);
}
	/**
     * DOM Level 3 WD - Experimental.
     * The version of this document (part of XML Declaration)
     */
public String getXmlVersion() {
    return (version == null) ? "1.0" : version;
}
	/**
     * @deprecated This method is internal and only exists for
     * compatibility with older applications. New applications
     * should never call this method.
     */
public String getVersion() {
    return getXmlVersion();
}
	/**
     * DOM Level 3 CR - Experimental.
     *
     * Xmlstandalone - An attribute specifying, as part of the XML declaration,
     * whether this document is standalone
     * @exception DOMException
     *    NOT_SUPPORTED_ERR: Raised if this document does not support the
     *   "XML" feature.
     * @since DOM Level 3
     */
public void setXmlStandalone(boolean value) throws DOMException {
    standalone = value;
}
	/**
     * @deprecated This method is internal and only exists for
     * compatibility with older applications. New applications
     * should never call this method.
     */
public void setStandalone(boolean value) {
    setXmlStandalone(value);
}
	/**
     * DOM Level 3 WD - Experimental.
     * standalone that specifies whether this document is standalone
     * (part of XML Declaration)
     */
public boolean getXmlStandalone() {
    return standalone;
}
	/**
     * @deprecated This method is internal and only exists for
     * compatibility with older applications. New applications
     * should never call this method.
     */
public boolean getStandalone() {
    return getXmlStandalone();
}
	/**
     * DOM Level 3 WD - Experimental.
     * The location of the document or <code>null</code> if undefined.
     * <br>Beware that when the <code>Document</code> supports the feature
     * "HTML" , the href attribute of the HTML BASE element takes precedence
     * over this attribute.
     * @since DOM Level 3
     */
public String getDocumentURI() {
    return fDocumentURI;
}
	/**
     *  DOM Level 3 WD - Experimental
     *  Normalize document.
     */
public void normalizeDocument() {
    // No need to normalize if already normalized.
    if (isNormalized() && !isNormalizeDocRequired()) {
        return;
    }
    if (needsSyncChildren()) {
        synchronizeChildren();
    }
    if (domNormalizer == null) {
        domNormalizer = new DOMNormalizer();
    }
    if (fConfiguration == null) {
        fConfiguration = new DOMConfigurationImpl();
    } else {
        fConfiguration.reset();
    }
    domNormalizer.normalizeDocument(this, fConfiguration);
    isNormalized(true);
    //set the XMLversion changed value to false -- once we have finished
    //doing normalization
    xmlVersionChanged = false;
}
	/**
     * DOM Level 3 CR - Experimental
     *
     *  The configuration used when <code>Document.normalizeDocument</code> is
     * invoked.
     * @since DOM Level 3
     */
public DOMConfiguration getDomConfig() {
    if (fConfiguration == null) {
        fConfiguration = new DOMConfigurationImpl();
    }
    return fConfiguration;
}
	/**
     * Returns the absolute base URI of this node or null if the implementation
     * wasn't able to obtain an absolute URI. Note: If the URI is malformed, a
     * null is returned.
     * 
     * @return The absolute base URI of this node or null.
     * @since DOM Level 3
     */
public String getBaseURI() {
    if (fDocumentURI != null && fDocumentURI.length() != 0) {
        // attribute value is always empty string
        try {
            return new URI(fDocumentURI).toString();
        } catch (org.apache.xerces.util.URI.MalformedURIException e) {
            // REVISIT: what should happen in this case?
            return null;
        }
    }
    return fDocumentURI;
}
	/**
     * DOM Level 3 WD - Experimental.
     */
public void setDocumentURI(String documentURI) {
    fDocumentURI = documentURI;
}
	//
// DOM L3 LS
//
/**
     * DOM Level 3 WD - Experimental.
     * Indicates whether the method load should be synchronous or
     * asynchronous. When the async attribute is set to <code>true</code>
     * the load method returns control to the caller before the document has
     * completed loading. The default value of this property is
     * <code>false</code>.
     * <br>Setting the value of this attribute might throw NOT_SUPPORTED_ERR
     * if the implementation doesn't support the mode the attribute is being
     * set to. Should the DOM spec define the default value of this
     * property? What if implementing both async and sync IO is impractical
     * in some systems?  2001-09-14. default is <code>false</code> but we
     * need to check with Mozilla and IE.
     */
public boolean getAsync() {
    return false;
}
	/**
     * DOM Level 3 WD - Experimental.
     * If the document is currently being loaded as a result of the method
     * <code>load</code> being invoked the loading and parsing is
     * immediately aborted. The possibly partial result of parsing the
     * document is discarded and the document is cleared.
     */
public void abort() {
}
	/**
     * DOM Level 3 WD - Experimental.
     *
     * Replaces the content of the document with the result of parsing the
     * given URI. Invoking this method will either block the caller or
     * return to the caller immediately depending on the value of the async
     * attribute. Once the document is fully loaded a "load" event (as
     * defined in [<a href='http://www.w3.org/TR/2003/WD-DOM-Level-3-Events-20030331'>DOM Level 3 Events</a>]
     * , except that the <code>Event.targetNode</code> will be the document,
     * not an element) will be dispatched on the document. If an error
     * occurs, an implementation dependent "error" event will be dispatched
     * on the document. If this method is called on a document that is
     * currently loading, the current load is interrupted and the new URI
     * load is initiated.
     * <br> When invoking this method the parameters used in the
     * <code>DOMParser</code> interface are assumed to have their default
     * values with the exception that the parameters <code>"entities"</code>
     * , <code>"normalize-characters"</code>,
     * <code>"check-character-normalization"</code> are set to
     * <code>"false"</code>.
     * <br> The result of a call to this method is the same the result of a
     * call to <code>DOMParser.parseWithContext</code> with an input stream
     * referencing the URI that was passed to this call, the document as the
     * context node, and the action <code>ACTION_REPLACE_CHILDREN</code>.
     * @param uri The URI reference for the XML file to be loaded. If this is
     *  a relative URI, the base URI used by the implementation is
     *  implementation dependent.
     * @return If async is set to <code>true</code> <code>load</code> returns
     *   <code>true</code> if the document load was successfully initiated.
     *   If an error occurred when initiating the document load,
     *   <code>load</code> returns <code>false</code>.If async is set to
     *   <code>false</code> <code>load</code> returns <code>true</code> if
     *   the document was successfully loaded and parsed. If an error
     *   occurred when either loading or parsing the URI, <code>load</code>
     *   returns <code>false</code>.
     */
public boolean load(String uri) {
    return false;
}
	/**
     * Sets whether the DOM implementation generates mutation events
     * upon operations.
     */
void setMutationEvents(boolean set) {
// does nothing by default - overidden in subclass
}
	/**
     * Returns true if the DOM implementation generates mutation events.
     */
boolean getMutationEvents() {
    // does nothing by default - overriden in subclass
    return false;
}
	// createElementDefinition(String):ElementDefinitionImpl
// other non-DOM methods
/** NON-DOM:  Get the number associated with this document.   Used to
     * order documents in the implementation.
     */
protected int getNodeNumber() {
    if (documentNumber == 0) {
        CoreDOMImplementationImpl cd = (CoreDOMImplementationImpl) CoreDOMImplementationImpl.getDOMImplementation();
        documentNumber = cd.assignDocumentNumber();
    }
    return documentNumber;
}
	// createElementDefinition(String):ElementDefinitionImpl
// other non-DOM methods
/** NON-DOM:  Get the number associated with this document.   Used to
     * order documents in the implementation.
     */
protected int getNodeNumber() {
    if (documentNumber == 0) {
        CoreDOMImplementationImpl cd = (CoreDOMImplementationImpl) CoreDOMImplementationImpl.getDOMImplementation();
        documentNumber = cd.assignDocumentNumber();
    }
    return documentNumber;
}
	// identifier maintenence
/**
     * Introduced in DOM Level 2
     * Returns the Element whose ID is given by elementId. If no such element
     * exists, returns null. Behavior is not defined if more than one element
     * has this ID.
     * <p>
     * Note: The DOM implementation must have information that says which
     * attributes are of type ID. Attributes with the name "ID" are not of type
     * ID unless so defined. Implementations that do not know whether
     * attributes are of type ID or not are expected to return null.
     * @see #getIdentifier
     */
public Element getElementById(String elementId) {
    return getIdentifier(elementId);
}
	/**
     * Remove all identifiers from the ID table
     */
protected final void clearIdentifiers() {
    if (identifiers != null) {
        identifiers.clear();
    }
}
	/**
     * Denotes that this node has changed.
     */
protected void changed() {
    changes++;
}
	/**
     * Returns the number of changes to this node.
     */
protected int changes() {
    return changes;
}
	//  NodeListCache pool
/**
     * Returns a NodeListCache for the given node.
     */
NodeListCache getNodeListCache(ParentNode owner) {
    if (fFreeNLCache == null) {
        return new NodeListCache(owner);
    }
    NodeListCache c = fFreeNLCache;
    fFreeNLCache = fFreeNLCache.next;
    c.fChild = null;
    c.fChildIndex = -1;
    c.fLength = -1;
    // revoke previous ownership
    if (c.fOwner != null) {
        c.fOwner.fNodeListCache = null;
    }
    c.fOwner = owner;
    // c.next = null; not necessary, except for confused people...
    return c;
}
	/**
     * Puts the given NodeListCache in the free list.
     * Note: The owner node can keep using it until we reuse it
     */
void freeNodeListCache(NodeListCache c) {
    c.next = fFreeNLCache;
    fFreeNLCache = c;
}
	/**
     * We could have more xml versions in future , but for now we could
     * do with this to handle XML 1.0 and 1.1
     */
boolean isXML11Version() {
    return xml11Version;
}
	//we should be checking the (elements, attribute, entity etc.) names only when
//version of the document is changed.
boolean isXMLVersionChanged() {
    return xmlVersionChanged;
}
}