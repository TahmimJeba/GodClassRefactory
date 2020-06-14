public class Class4 {
	private Object NODE_IMPORTED;
	private Object ENTITY_NODE;
	private Object NOT_SUPPORTED_ERR;
	private Object DOM_DOMAIN;

	public Class4(Object NODE_IMPORTED, Object ENTITY_NODE, Object NOT_SUPPORTED_ERR, Object DOM_DOMAIN){
		this.NODE_IMPORTED = NODE_IMPORTED;
		this.ENTITY_NODE = ENTITY_NODE;
		this.NOT_SUPPORTED_ERR = NOT_SUPPORTED_ERR;
		this.DOM_DOMAIN = DOM_DOMAIN;
	}
	/**
     * DOM Level 3 WD - Experimental.
     * Replace the content of the document with the result of parsing the
     * input string, this method is always synchronous.
     * @param source A string containing an XML document.
     * @return <code>true</code> if parsing the input string succeeded
     *   without errors, otherwise <code>false</code>.
     */
public boolean loadXML(String source) {
    return false;
}
	/**
     * Copies a node from another document to this document. The new nodes are
     * created using this document's factory methods and are populated with the
     * data from the source's accessor methods defined by the DOM interfaces.
     * Its behavior is otherwise similar to that of cloneNode.
     * <p>
     * According to the DOM specifications, document nodes cannot be imported
     * and a NOT_SUPPORTED_ERR exception is thrown if attempted.
     */
public Node importNode(Node source, boolean deep) throws DOMException {
    return importNode(source, deep, false, null);
}
	/**
     * Copies a node from another document to this document. The new nodes are
     * created using this document's factory methods and are populated with the
     * data from the source's accessor methods defined by the DOM interfaces.
     * Its behavior is otherwise similar to that of cloneNode.
     * <p>
     * According to the DOM specifications, document nodes cannot be imported
     * and a NOT_SUPPORTED_ERR exception is thrown if attempted.
     */
public Node importNode(Node source, boolean deep) throws DOMException {
    return importNode(source, deep, false, null);
}
	// importNode(Node,boolean):Node
/**
     * Overloaded implementation of DOM's importNode method. This method
     * provides the core functionality for the public importNode and cloneNode
     * methods.
     *
     * The reversedIdentifiers parameter is provided for cloneNode to
     * preserve the document's identifiers. The Hashtable has Elements as the
     * keys and their identifiers as the values. When an element is being
     * imported, a check is done for an associated identifier. If one exists,
     * the identifier is registered with the new, imported element. If
     * reversedIdentifiers is null, the parameter is not applied.
     */
private Node importNode(Node source, boolean deep, boolean cloningDoc, Hashtable reversedIdentifiers) throws DOMException {
    Node newnode = null;
    Hashtable userData = null;
    // else
    if (source instanceof NodeImpl)
        userData = ((NodeImpl) source).getUserDataRecord();
    int type = source.getNodeType();
    switch(type) {
        case ELEMENT_NODE:
            {
                Element newElement;
                boolean domLevel20 = source.getOwnerDocument().getImplementation().hasFeature("XML", "2.0");
                // Create element according to namespace support/qualification.
                if (domLevel20 == false || source.getLocalName() == null)
                    newElement = createElement(source.getNodeName());
                else
                    newElement = createElementNS(source.getNamespaceURI(), source.getNodeName());
                // Copy element's attributes, if any.
                NamedNodeMap sourceAttrs = source.getAttributes();
                if (sourceAttrs != null) {
                    int length = sourceAttrs.getLength();
                    for (int index = 0; index < length; index++) {
                        Attr attr = (Attr) sourceAttrs.item(index);
                        // But for importNode defaults should be ignored.
                        if (attr.getSpecified() || cloningDoc) {
                            Attr newAttr = (Attr) importNode(attr, true, cloningDoc, reversedIdentifiers);
                            // support/qualification.
                            if (domLevel20 == false || attr.getLocalName() == null)
                                newElement.setAttributeNode(newAttr);
                            else
                                newElement.setAttributeNodeNS(newAttr);
                        }
                    }
                }
                // Register element identifier.
                if (reversedIdentifiers != null) {
                    // Does element have an associated identifier?
                    Object elementId = reversedIdentifiers.get(source);
                    if (elementId != null) {
                        if (identifiers == null)
                            identifiers = new Hashtable();
                        identifiers.put(elementId, newElement);
                    }
                }
                newnode = newElement;
                break;
            }
        case ATTRIBUTE_NODE:
            {
                if (source.getOwnerDocument().getImplementation().hasFeature("XML", "2.0")) {
                    if (source.getLocalName() == null) {
                        newnode = createAttribute(source.getNodeName());
                    } else {
                        newnode = createAttributeNS(source.getNamespaceURI(), source.getNodeName());
                    }
                } else {
                    newnode = createAttribute(source.getNodeName());
                }
                // avoid creating the child nodes if possible
                if (source instanceof AttrImpl) {
                    AttrImpl attr = (AttrImpl) source;
                    if (attr.hasStringValue()) {
                        AttrImpl newattr = (AttrImpl) newnode;
                        newattr.setValue(attr.getValue());
                        deep = false;
                    } else {
                        deep = true;
                    }
                } else {
                    // directly.
                    if (source.getFirstChild() == null) {
                        newnode.setNodeValue(source.getNodeValue());
                        deep = false;
                    } else {
                        deep = true;
                    }
                }
                break;
            }
        case TEXT_NODE:
            {
                newnode = createTextNode(source.getNodeValue());
                break;
            }
        case CDATA_SECTION_NODE:
            {
                newnode = createCDATASection(source.getNodeValue());
                break;
            }
        case ENTITY_REFERENCE_NODE:
            {
                newnode = createEntityReference(source.getNodeName());
                // the subtree is created according to this doc by the method
                // above, so avoid carrying over original subtree
                deep = false;
                break;
            }
        case ENTITY_NODE:
            {
                Entity srcentity = (Entity) source;
                EntityImpl newentity = (EntityImpl) createEntity(source.getNodeName());
                newentity.setPublicId(srcentity.getPublicId());
                newentity.setSystemId(srcentity.getSystemId());
                newentity.setNotationName(srcentity.getNotationName());
                // Kids carry additional value,
                // allow deep import temporarily
                newentity.isReadOnly(false);
                newnode = newentity;
                break;
            }
        case PROCESSING_INSTRUCTION_NODE:
            {
                newnode = createProcessingInstruction(source.getNodeName(), source.getNodeValue());
                break;
            }
        case COMMENT_NODE:
            {
                newnode = createComment(source.getNodeValue());
                break;
            }
        case DOCUMENT_TYPE_NODE:
            {
                // forbid it for the sake of being compliant to the DOM spec
                if (!cloningDoc) {
                    String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null);
                    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, msg);
                }
                DocumentType srcdoctype = (DocumentType) source;
                DocumentTypeImpl newdoctype = (DocumentTypeImpl) createDocumentType(srcdoctype.getNodeName(), srcdoctype.getPublicId(), srcdoctype.getSystemId());
                // Values are on NamedNodeMaps
                NamedNodeMap smap = srcdoctype.getEntities();
                NamedNodeMap tmap = newdoctype.getEntities();
                if (smap != null) {
                    for (int i = 0; i < smap.getLength(); i++) {
                        tmap.setNamedItem(importNode(smap.item(i), true, true, reversedIdentifiers));
                    }
                }
                smap = srcdoctype.getNotations();
                tmap = newdoctype.getNotations();
                if (smap != null) {
                    for (int i = 0; i < smap.getLength(); i++) {
                        tmap.setNamedItem(importNode(smap.item(i), true, true, reversedIdentifiers));
                    }
                }
                // NOTE: At this time, the DOM definition of DocumentType
                // doesn't cover Elements and their Attributes. domimpl's
                // extentions in that area will not be preserved, even if
                // copying from domimpl to domimpl. We could special-case
                // that here. Arguably we should. Consider. ?????
                newnode = newdoctype;
                break;
            }
        case DOCUMENT_FRAGMENT_NODE:
            {
                newnode = createDocumentFragment();
                // No name, kids carry value
                break;
            }
        case NOTATION_NODE:
            {
                Notation srcnotation = (Notation) source;
                NotationImpl newnotation = (NotationImpl) createNotation(source.getNodeName());
                newnotation.setPublicId(srcnotation.getPublicId());
                newnotation.setSystemId(srcnotation.getSystemId());
                // Kids carry additional value
                newnode = newnotation;
                // No name, no value
                break;
            }
        // Can't import document nodes
        case DOCUMENT_NODE:
        default:
            {
                // Unknown node type
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null);
                throw new DOMException(DOMException.NOT_SUPPORTED_ERR, msg);
            }
    }
    if (userData != null)
        callUserDataHandlers(source, newnode, UserDataHandler.NODE_IMPORTED, userData);
    // If deep, replicate and attach the kids.
    if (deep) {
        for (Node srckid = source.getFirstChild(); srckid != null; srckid = srckid.getNextSibling()) {
            newnode.appendChild(importNode(srckid, true, cloningDoc, reversedIdentifiers));
        }
    }
    if (newnode.getNodeType() == Node.ENTITY_NODE) {
        ((NodeImpl) newnode).setReadOnly(true, true);
    }
    return newnode;
}
	// importNode(Node,boolean):Node
/**
     * Overloaded implementation of DOM's importNode method. This method
     * provides the core functionality for the public importNode and cloneNode
     * methods.
     *
     * The reversedIdentifiers parameter is provided for cloneNode to
     * preserve the document's identifiers. The Hashtable has Elements as the
     * keys and their identifiers as the values. When an element is being
     * imported, a check is done for an associated identifier. If one exists,
     * the identifier is registered with the new, imported element. If
     * reversedIdentifiers is null, the parameter is not applied.
     */
private Node importNode(Node source, boolean deep, boolean cloningDoc, Hashtable reversedIdentifiers) throws DOMException {
    Node newnode = null;
    Hashtable userData = null;
    // else
    if (source instanceof NodeImpl)
        userData = ((NodeImpl) source).getUserDataRecord();
    int type = source.getNodeType();
    switch(type) {
        case ELEMENT_NODE:
            {
                Element newElement;
                boolean domLevel20 = source.getOwnerDocument().getImplementation().hasFeature("XML", "2.0");
                // Create element according to namespace support/qualification.
                if (domLevel20 == false || source.getLocalName() == null)
                    newElement = createElement(source.getNodeName());
                else
                    newElement = createElementNS(source.getNamespaceURI(), source.getNodeName());
                // Copy element's attributes, if any.
                NamedNodeMap sourceAttrs = source.getAttributes();
                if (sourceAttrs != null) {
                    int length = sourceAttrs.getLength();
                    for (int index = 0; index < length; index++) {
                        Attr attr = (Attr) sourceAttrs.item(index);
                        // But for importNode defaults should be ignored.
                        if (attr.getSpecified() || cloningDoc) {
                            Attr newAttr = (Attr) importNode(attr, true, cloningDoc, reversedIdentifiers);
                            // support/qualification.
                            if (domLevel20 == false || attr.getLocalName() == null)
                                newElement.setAttributeNode(newAttr);
                            else
                                newElement.setAttributeNodeNS(newAttr);
                        }
                    }
                }
                // Register element identifier.
                if (reversedIdentifiers != null) {
                    // Does element have an associated identifier?
                    Object elementId = reversedIdentifiers.get(source);
                    if (elementId != null) {
                        if (identifiers == null)
                            identifiers = new Hashtable();
                        identifiers.put(elementId, newElement);
                    }
                }
                newnode = newElement;
                break;
            }
        case ATTRIBUTE_NODE:
            {
                if (source.getOwnerDocument().getImplementation().hasFeature("XML", "2.0")) {
                    if (source.getLocalName() == null) {
                        newnode = createAttribute(source.getNodeName());
                    } else {
                        newnode = createAttributeNS(source.getNamespaceURI(), source.getNodeName());
                    }
                } else {
                    newnode = createAttribute(source.getNodeName());
                }
                // avoid creating the child nodes if possible
                if (source instanceof AttrImpl) {
                    AttrImpl attr = (AttrImpl) source;
                    if (attr.hasStringValue()) {
                        AttrImpl newattr = (AttrImpl) newnode;
                        newattr.setValue(attr.getValue());
                        deep = false;
                    } else {
                        deep = true;
                    }
                } else {
                    // directly.
                    if (source.getFirstChild() == null) {
                        newnode.setNodeValue(source.getNodeValue());
                        deep = false;
                    } else {
                        deep = true;
                    }
                }
                break;
            }
        case TEXT_NODE:
            {
                newnode = createTextNode(source.getNodeValue());
                break;
            }
        case CDATA_SECTION_NODE:
            {
                newnode = createCDATASection(source.getNodeValue());
                break;
            }
        case ENTITY_REFERENCE_NODE:
            {
                newnode = createEntityReference(source.getNodeName());
                // the subtree is created according to this doc by the method
                // above, so avoid carrying over original subtree
                deep = false;
                break;
            }
        case ENTITY_NODE:
            {
                Entity srcentity = (Entity) source;
                EntityImpl newentity = (EntityImpl) createEntity(source.getNodeName());
                newentity.setPublicId(srcentity.getPublicId());
                newentity.setSystemId(srcentity.getSystemId());
                newentity.setNotationName(srcentity.getNotationName());
                // Kids carry additional value,
                // allow deep import temporarily
                newentity.isReadOnly(false);
                newnode = newentity;
                break;
            }
        case PROCESSING_INSTRUCTION_NODE:
            {
                newnode = createProcessingInstruction(source.getNodeName(), source.getNodeValue());
                break;
            }
        case COMMENT_NODE:
            {
                newnode = createComment(source.getNodeValue());
                break;
            }
        case DOCUMENT_TYPE_NODE:
            {
                // forbid it for the sake of being compliant to the DOM spec
                if (!cloningDoc) {
                    String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null);
                    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, msg);
                }
                DocumentType srcdoctype = (DocumentType) source;
                DocumentTypeImpl newdoctype = (DocumentTypeImpl) createDocumentType(srcdoctype.getNodeName(), srcdoctype.getPublicId(), srcdoctype.getSystemId());
                // Values are on NamedNodeMaps
                NamedNodeMap smap = srcdoctype.getEntities();
                NamedNodeMap tmap = newdoctype.getEntities();
                if (smap != null) {
                    for (int i = 0; i < smap.getLength(); i++) {
                        tmap.setNamedItem(importNode(smap.item(i), true, true, reversedIdentifiers));
                    }
                }
                smap = srcdoctype.getNotations();
                tmap = newdoctype.getNotations();
                if (smap != null) {
                    for (int i = 0; i < smap.getLength(); i++) {
                        tmap.setNamedItem(importNode(smap.item(i), true, true, reversedIdentifiers));
                    }
                }
                // NOTE: At this time, the DOM definition of DocumentType
                // doesn't cover Elements and their Attributes. domimpl's
                // extentions in that area will not be preserved, even if
                // copying from domimpl to domimpl. We could special-case
                // that here. Arguably we should. Consider. ?????
                newnode = newdoctype;
                break;
            }
        case DOCUMENT_FRAGMENT_NODE:
            {
                newnode = createDocumentFragment();
                // No name, kids carry value
                break;
            }
        case NOTATION_NODE:
            {
                Notation srcnotation = (Notation) source;
                NotationImpl newnotation = (NotationImpl) createNotation(source.getNodeName());
                newnotation.setPublicId(srcnotation.getPublicId());
                newnotation.setSystemId(srcnotation.getSystemId());
                // Kids carry additional value
                newnode = newnotation;
                // No name, no value
                break;
            }
        // Can't import document nodes
        case DOCUMENT_NODE:
        default:
            {
                // Unknown node type
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null);
                throw new DOMException(DOMException.NOT_SUPPORTED_ERR, msg);
            }
    }
    if (userData != null)
        callUserDataHandlers(source, newnode, UserDataHandler.NODE_IMPORTED, userData);
    // If deep, replicate and attach the kids.
    if (deep) {
        for (Node srckid = source.getFirstChild(); srckid != null; srckid = srckid.getNextSibling()) {
            newnode.appendChild(importNode(srckid, true, cloningDoc, reversedIdentifiers));
        }
    }
    if (newnode.getNodeType() == Node.ENTITY_NODE) {
        ((NodeImpl) newnode).setReadOnly(true, true);
    }
    return newnode;
}
	/** Returns the node name. */
public String getNodeName() {
    return "#document";
}
	/**
     * Factory method; creates a DocumentFragment having this Document
     * as its OwnerDoc.
     */
public DocumentFragment createDocumentFragment() {
    return new DocumentFragmentImpl(this);
}
	/**
     * Retrieve information describing the abilities of this particular
     * DOM implementation. Intended to support applications that may be
     * using DOMs retrieved from several different sources, potentially
     * with different underlying representations.
     */
public DOMImplementation getImplementation() {
    // information anyway.
    return CoreDOMImplementationImpl.getDOMImplementation();
}
}
