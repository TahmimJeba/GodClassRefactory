public class Class1 {
	private Object DOCUMENT_NODE;
	private Object HIERARCHY_REQUEST_ERR;
	private Object ELEMENT_NODE;
	private Object DOM_DOMAIN;
	private Object DOCUMENT_TYPE_NODE;
	private Object ownerDocument;

	public Class1(Object DOCUMENT_NODE, Object HIERARCHY_REQUEST_ERR, Object ELEMENT_NODE, Object DOM_DOMAIN, Object DOCUMENT_TYPE_NODE, Object ownerDocument){
		this.DOCUMENT_NODE = DOCUMENT_NODE;
		this.HIERARCHY_REQUEST_ERR = HIERARCHY_REQUEST_ERR;
		this.ELEMENT_NODE = ELEMENT_NODE;
		this.DOM_DOMAIN = DOM_DOMAIN;
		this.DOCUMENT_TYPE_NODE = DOCUMENT_TYPE_NODE;
		this.ownerDocument = ownerDocument;
	}
	// cloneNode(CoreDocumentImpl,boolean):void
/**
     * Since a Document may contain at most one top-level Element child,
     * and at most one DocumentType declaraction, we need to subclass our
     * add-children methods to implement this constraint.
     * Since appendChild() is implemented as insertBefore(,null),
     * altering the latter fixes both.
     * <p>
     * While I'm doing so, I've taken advantage of the opportunity to
     * cache documentElement and docType so we don't have to
     * search for them.
     *
     * REVISIT: According to the spec it is not allowed to alter neither the
     * document element nor the document type in any way
     */
public Node insertBefore(Node newChild, Node refChild) throws DOMException {
    // Only one such child permitted
    int type = newChild.getNodeType();
    if (errorChecking) {
        if ((type == Node.ELEMENT_NODE && docElement != null) || (type == Node.DOCUMENT_TYPE_NODE && docType != null)) {
            String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null);
            throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR, msg);
        }
    }
    // Adopt orphan doctypes
    if (newChild.getOwnerDocument() == null && newChild instanceof DocumentTypeImpl) {
        ((DocumentTypeImpl) newChild).ownerDocument = this;
    }
    super.insertBefore(newChild, refChild);
    // If insert succeeded, cache the kid appropriately
    if (type == Node.ELEMENT_NODE) {
        docElement = (ElementImpl) newChild;
    } else if (type == Node.DOCUMENT_TYPE_NODE) {
        docType = (DocumentTypeImpl) newChild;
    }
    return newChild;
}
	// removeChild(Node):Node
/**
     * Since we cache the docElement (and, currently, docType),
     * replaceChild has to update the cache
     *
     * REVISIT: According to the spec it is not allowed to alter neither the
     * document element nor the document type in any way
     */
public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
    // Adopt orphan doctypes
    if (newChild.getOwnerDocument() == null && newChild instanceof DocumentTypeImpl) {
        ((DocumentTypeImpl) newChild).ownerDocument = this;
    }
    if (errorChecking && ((docType != null && oldChild.getNodeType() != Node.DOCUMENT_TYPE_NODE && newChild.getNodeType() == Node.DOCUMENT_TYPE_NODE) || (docElement != null && oldChild.getNodeType() != Node.ELEMENT_NODE && newChild.getNodeType() == Node.ELEMENT_NODE))) {
        throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
    }
    super.replaceChild(newChild, oldChild);
    int type = oldChild.getNodeType();
    if (type == Node.ELEMENT_NODE) {
        docElement = (ElementImpl) newChild;
    } else if (type == Node.DOCUMENT_TYPE_NODE) {
        docType = (DocumentTypeImpl) newChild;
    }
    return oldChild;
}
	// insertBefore(Node,Node):Node
/**
     * Since insertBefore caches the docElement (and, currently, docType),
     * removeChild has to know how to undo the cache
     *
     * REVISIT: According to the spec it is not allowed to alter neither the
     * document element nor the document type in any way
     */
public Node removeChild(Node oldChild) throws DOMException {
    super.removeChild(oldChild);
    // If remove succeeded, un-cache the kid appropriately
    int type = oldChild.getNodeType();
    if (type == Node.ELEMENT_NODE) {
        docElement = null;
    } else if (type == Node.DOCUMENT_TYPE_NODE) {
        docType = null;
    }
    return oldChild;
}
	//
// Node methods
//
// even though ownerDocument refers to this in this implementation
// the DOM Level 2 spec says it must be null, so make it appear so
public final Document getOwnerDocument() {
    return null;
}
	/** Returns the node type. */
public short getNodeType() {
    return Node.DOCUMENT_NODE;
}
}