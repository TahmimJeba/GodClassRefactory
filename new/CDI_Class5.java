public class Class5 {
	private Object DOM_DOMAIN;
	private Object ELEMENT_NODE;
	private Object DOCUMENT_TYPE_NODE;
	private Object NO_MODIFICATION_ALLOWED_ERR;
	private Object NODE_ADOPTED;
	private Object NOT_SUPPORTED_ERR;

	public Class5(Object DOM_DOMAIN, Object ELEMENT_NODE, Object DOCUMENT_TYPE_NODE, Object NO_MODIFICATION_ALLOWED_ERR, Object NODE_ADOPTED, Object NOT_SUPPORTED_ERR){
		this.DOM_DOMAIN = DOM_DOMAIN;
		this.ELEMENT_NODE = ELEMENT_NODE;
		this.DOCUMENT_TYPE_NODE = DOCUMENT_TYPE_NODE;
		this.NO_MODIFICATION_ALLOWED_ERR = NO_MODIFICATION_ALLOWED_ERR;
		this.NODE_ADOPTED = NODE_ADOPTED;
		this.NOT_SUPPORTED_ERR = NOT_SUPPORTED_ERR;
	}
	// importNode(Node,boolean,boolean,Hashtable):Node
/**
     * DOM Level 3 WD - Experimental
     * Change the node's ownerDocument, and its subtree, to this Document
     *
     * @param source The node to adopt.
     * @see #importNode
     **/
public Node adoptNode(Node source) {
    NodeImpl node;
    Hashtable userData = null;
    try {
        node = (NodeImpl) source;
    } catch (ClassCastException e) {
        // source node comes from a different DOMImplementation
        return null;
    }
    if (source == null) {
        return null;
    } else if (source != null && source.getOwnerDocument() != null) {
        DOMImplementation thisImpl = this.getImplementation();
        DOMImplementation otherImpl = source.getOwnerDocument().getImplementation();
        // when the source node comes from a different implementation.
        if (thisImpl != otherImpl) {
            // Adopting from a DefferedDOM to DOM
            if (thisImpl instanceof org.apache.xerces.dom.DOMImplementationImpl && otherImpl instanceof org.apache.xerces.dom.DeferredDOMImplementationImpl) {
                // traverse the DOM and expand deffered nodes and then allow adoption
                undeferChildren(node);
            } else if (thisImpl instanceof org.apache.xerces.dom.DeferredDOMImplementationImpl && otherImpl instanceof org.apache.xerces.dom.DOMImplementationImpl) {
            // Adopting from a DOM into a DefferedDOM, this should be okay
            } else {
                // Adopting between two dissimilar DOM's is not allowed
                return null;
            }
        }
    }
    switch(node.getNodeType()) {
        case ATTRIBUTE_NODE:
            {
                AttrImpl attr = (AttrImpl) node;
                // remove node from wherever it is
                if (attr.getOwnerElement() != null) {
                    //1. owner element attribute is set to null
                    attr.getOwnerElement().removeAttributeNode(attr);
                }
                //2. specified flag is set to true
                attr.isSpecified(true);
                userData = node.getUserDataRecord();
                //3. change ownership
                attr.setOwnerDocument(this);
                if (userData != null)
                    setUserDataTable(node, userData);
                break;
            }
        //runtime will fall through to NOTATION_NODE
        case ENTITY_NODE:
        case NOTATION_NODE:
            {
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null);
                throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, msg);
            }
        //runtime will fall through to DocumentTypeNode
        case DOCUMENT_NODE:
        case DOCUMENT_TYPE_NODE:
            {
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null);
                throw new DOMException(DOMException.NOT_SUPPORTED_ERR, msg);
            }
        case ENTITY_REFERENCE_NODE:
            {
                userData = node.getUserDataRecord();
                // remove node from wherever it is
                Node parent = node.getParentNode();
                if (parent != null) {
                    parent.removeChild(source);
                }
                // discard its replacement value
                Node child;
                while ((child = node.getFirstChild()) != null) {
                    node.removeChild(child);
                }
                // change ownership
                node.setOwnerDocument(this);
                if (userData != null)
                    setUserDataTable(node, userData);
                // set its new replacement value if any
                if (docType == null) {
                    break;
                }
                NamedNodeMap entities = docType.getEntities();
                Node entityNode = entities.getNamedItem(node.getNodeName());
                if (entityNode == null) {
                    break;
                }
                for (child = entityNode.getFirstChild(); child != null; child = child.getNextSibling()) {
                    Node childClone = child.cloneNode(true);
                    node.appendChild(childClone);
                }
                break;
            }
        case ELEMENT_NODE:
            {
                userData = node.getUserDataRecord();
                // remove node from wherever it is
                Node parent = node.getParentNode();
                if (parent != null) {
                    parent.removeChild(source);
                }
                // change ownership
                node.setOwnerDocument(this);
                if (userData != null)
                    setUserDataTable(node, userData);
                // reconcile default attributes
                ((ElementImpl) node).reconcileDefaultAttributes();
                break;
            }
        default:
            {
                userData = node.getUserDataRecord();
                // remove node from wherever it is
                Node parent = node.getParentNode();
                if (parent != null) {
                    parent.removeChild(source);
                }
                // change ownership
                node.setOwnerDocument(this);
                if (userData != null)
                    setUserDataTable(node, userData);
            }
    }
    //http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core.html#UserDataHandler-ADOPTED
    if (userData != null)
        callUserDataHandlers(source, null, UserDataHandler.NODE_ADOPTED, userData);
    return node;
}
	//
// Protected methods
//
/**
     * Uses the kidOK lookup table to check whether the proposed
     * tree structure is legal.
     */
protected boolean isKidOK(Node parent, Node child) {
    if (allowGrammarAccess && parent.getNodeType() == Node.DOCUMENT_TYPE_NODE) {
        return child.getNodeType() == Node.ELEMENT_NODE;
    }
    return 0 != (kidOK[parent.getNodeType()] & 1 << child.getNodeType());
}
	/**
     * Remove user data table for the given node.
     * @param n The node this operation applies to.
     * @return The removed table.
     */
Hashtable removeUserDataTable(Node n) {
    if (userData == null) {
        return null;
    }
    return (Hashtable) userData.get(n);
}
	// Notification methods overidden in subclasses
/**
     * A method to be called when some text was changed in a text node,
     * so that live objects can be notified.
     */
void replacedText(NodeImpl node) {
}
	/**
     * NON-DOM: kept for backward compatibility
     * Retreive user data related to a given node
     */
protected Object getUserData(NodeImpl n) {
    return getUserData(n, "XERCES1DOMUSERDATA");
}
	/**
     * NON-DOM: kept for backward compatibility
     * Retreive user data related to a given node
     */
protected Object getUserData(NodeImpl n) {
    return getUserData(n, "XERCES1DOMUSERDATA");
}
	/**
     * A method to be called when a character data node is about to be modified
     */
void modifyingCharacterData(NodeImpl node, boolean replace) {
}
	/**
     * A method to be called when a character data node has been modified
     */
void modifiedCharacterData(NodeImpl node, String oldvalue, String value, boolean replace) {
}
	/**
     * A method to be called when a node is about to be inserted in the tree.
     */
void insertingNode(NodeImpl node, boolean replace) {
}
	/**
     * A method to be called when a node has been inserted in the tree.
     */
void insertedNode(NodeImpl node, NodeImpl newInternal, boolean replace) {
}
	/**
     * A method to be called when a node is about to be removed from the tree.
     */
void removingNode(NodeImpl node, NodeImpl oldChild, boolean replace) {
}
	/**
     * A method to be called when a node has been removed from the tree.
     */
void removedNode(NodeImpl node, boolean replace) {
}
	/**
     * A method to be called when a node is about to be replaced in the tree.
     */
void replacingNode(NodeImpl node) {
}
	/**
     * A method to be called when a node has been replaced in the tree.
     */
void replacedNode(NodeImpl node) {
}
	/**
     * A method to be called when a character data node is about to be replaced
     */
void replacingData(NodeImpl node) {
}
	/**
     *  method to be called when a character data node has been replaced.
     */
void replacedCharacterData(NodeImpl node, String oldvalue, String value) {
}
	/**
     * A method to be called when an attribute value has been modified
     */
void modifiedAttrValue(AttrImpl attr, String oldvalue) {
}
	/**
     * A method to be called when an attribute node has been set
     */
void setAttrNode(AttrImpl attr, AttrImpl previous) {
}
	/**
     * A method to be called when an attribute node has been removed
     */
void removedAttrNode(AttrImpl attr, NodeImpl oldOwner, String name) {
}
	/**
     * Traverses the DOM Tree and expands deferred nodes and their
     * children.
     * 
     */
protected void undeferChildren(Node node) {
    Node top = node;
    while (null != node) {
        if (((NodeImpl) node).needsSyncData()) {
            ((NodeImpl) node).synchronizeData();
        }
        Node nextNode = null;
        nextNode = node.getFirstChild();
        while (null == nextNode) {
            if (top.equals(node))
                break;
            nextNode = node.getNextSibling();
            if (null == nextNode) {
                node = node.getParentNode();
                if ((null == node) || (top.equals(node))) {
                    nextNode = null;
                    break;
                }
            }
        }
        node = nextNode;
    }
}
	/**
     * Call user data handlers when a node is deleted (finalized)
     * @param n The node this operation applies to.
     * @param c The copy node or null.
     * @param operation The operation - import, clone, or delete.
     */
void callUserDataHandlers(Node n, Node c, short operation) {
    if (userData == null) {
        return;
    }
    //Hashtable t = (Hashtable) userData.get(n);
    if (n instanceof NodeImpl) {
        Hashtable t = ((NodeImpl) n).getUserDataRecord();
        if (t == null || t.isEmpty()) {
            return;
        }
        callUserDataHandlers(n, c, operation, t);
    }
}
	/**
     * Call user data handlers when a node is deleted (finalized)
     * @param n The node this operation applies to.
     * @param c The copy node or null.
     * @param operation The operation - import, clone, or delete.
     */
void callUserDataHandlers(Node n, Node c, short operation) {
    if (userData == null) {
        return;
    }
    //Hashtable t = (Hashtable) userData.get(n);
    if (n instanceof NodeImpl) {
        Hashtable t = ((NodeImpl) n).getUserDataRecord();
        if (t == null || t.isEmpty()) {
            return;
        }
        callUserDataHandlers(n, c, operation, t);
    }
}
	/**
     * A method to be called when some text was deleted from a text node,
     * so that live objects can be notified.
     */
void deletedText(NodeImpl node, int offset, int count) {
}
	/**
     * A method to be called when some text was inserted into a text node,
     * so that live objects can be notified.
     */
void insertedText(NodeImpl node, int offset, int count) {
}
	/**
     * A method to be called when an attribute node has been renamed
     */
void renamedAttrNode(Attr oldAt, Attr newAt) {
}
}