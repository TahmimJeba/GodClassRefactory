public class Class2 {
	private Object DOM_DOMAIN;
	private Object INVALID_CHARACTER_ERR;
	private Object NODE_RENAMED;
	private Object NAMESPACE_ERR;
	private Object NOT_SUPPORTED_ERR;
	private Object WRONG_DOCUMENT_ERR;

	public Class2(Object DOM_DOMAIN, Object INVALID_CHARACTER_ERR, Object NODE_RENAMED, Object NAMESPACE_ERR, Object NOT_SUPPORTED_ERR, Object WRONG_DOCUMENT_ERR){
		this.DOM_DOMAIN = DOM_DOMAIN;
		this.INVALID_CHARACTER_ERR = INVALID_CHARACTER_ERR;
		this.NODE_RENAMED = NODE_RENAMED;
		this.NAMESPACE_ERR = NAMESPACE_ERR;
		this.NOT_SUPPORTED_ERR = NOT_SUPPORTED_ERR;
		this.WRONG_DOCUMENT_ERR = WRONG_DOCUMENT_ERR;
	}
	//
// Document methods
//
// factory methods
/**
     * Factory method; creates an Attribute having this Document as its
     * OwnerDoc.
     * 
     * @param name The name of the attribute. Note that the attribute's value is
     * _not_ established at the factory; remember to set it!
     * 
     * @throws DOMException(INVALID_NAME_ERR)
     * if the attribute name is not acceptable.
     */
public Attr createAttribute(String name) throws DOMException {
    if (errorChecking && !isXMLName(name, xml11Version)) {
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null);
        throw new DOMException(DOMException.INVALID_CHARACTER_ERR, msg);
    }
    return new AttrImpl(this, name);
}
	/**
     * Factory method; creates an Element having this Document
     * as its OwnerDoc.
     *
     * @param tagName The name of the element type to instantiate. For
     * XML, this is case-sensitive. For HTML, the tagName parameter may
     * be provided in any case, but it must be mapped to the canonical
     * uppercase form by the DOM implementation.
     *
     * @throws DOMException(INVALID_NAME_ERR) if the tag name is not
     * acceptable.
     */
public Element createElement(String tagName) throws DOMException {
    if (errorChecking && !isXMLName(tagName, xml11Version)) {
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null);
        throw new DOMException(DOMException.INVALID_CHARACTER_ERR, msg);
    }
    return new ElementImpl(this, tagName);
}
	// createElement(String):Element
/**
     * Factory method; creates an EntityReference having this Document
     * as its OwnerDoc.
     *
     * @param name The name of the Entity we wish to refer to
     *
     * @throws DOMException(NOT_SUPPORTED_ERR) for HTML documents, where
     * nonstandard entities are not permitted. (HTML not yet
     * implemented.)
     */
public EntityReference createEntityReference(String name) throws DOMException {
    if (errorChecking && !isXMLName(name, xml11Version)) {
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null);
        throw new DOMException(DOMException.INVALID_CHARACTER_ERR, msg);
    }
    return new EntityReferenceImpl(this, name);
}
	// createEntityReference(String):EntityReference
/**
     * Factory method; creates a ProcessingInstruction having this Document
     * as its OwnerDoc.
     *
     * @param target The target "processor channel"
     * @param data Parameter string to be passed to the target.
     *
     * @throws DOMException(INVALID_NAME_ERR) if the target name is not
     * acceptable.
     *
     * @throws DOMException(NOT_SUPPORTED_ERR) for HTML documents. (HTML
     * not yet implemented.)
     */
public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
    if (errorChecking && !isXMLName(target, xml11Version)) {
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null);
        throw new DOMException(DOMException.INVALID_CHARACTER_ERR, msg);
    }
    return new ProcessingInstructionImpl(this, target, data);
}
	// createDocumentType(String):DocumentType
/**
     * NON-DOM
     * Factory method; creates an Entity having this Document
     * as its OwnerDoc. (REC-DOM-Level-1-19981001 left the process of building
     * DTD information unspecified.)
     *
     * @param name The name of the Entity we wish to provide a value for.
     *
     * @throws DOMException(NOT_SUPPORTED_ERR) for HTML documents, where
     * nonstandard entities are not permitted. (HTML not yet
     * implemented.)
     */
public Entity createEntity(String name) throws DOMException {
    if (errorChecking && !isXMLName(name, xml11Version)) {
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null);
        throw new DOMException(DOMException.INVALID_CHARACTER_ERR, msg);
    }
    return new EntityImpl(this, name);
}
	// createEntity(String):Entity
/**
     * NON-DOM
     * Factory method; creates a Notation having this Document
     * as its OwnerDoc. (REC-DOM-Level-1-19981001 left the process of building
     * DTD information unspecified.)
     *
     * @param name The name of the Notation we wish to describe
     *
     * @throws DOMException(NOT_SUPPORTED_ERR) for HTML documents, where
     * notations are not permitted. (HTML not yet
     * implemented.)
     */
public Notation createNotation(String name) throws DOMException {
    if (errorChecking && !isXMLName(name, xml11Version)) {
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null);
        throw new DOMException(DOMException.INVALID_CHARACTER_ERR, msg);
    }
    return new NotationImpl(this, name);
}
	// createNotation(String):Notation
/**
     * NON-DOM Factory method: creates an element definition. Element
     * definitions hold default attribute values.
     */
public ElementDefinitionImpl createElementDefinition(String name) throws DOMException {
    if (errorChecking && !isXMLName(name, xml11Version)) {
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null);
        throw new DOMException(DOMException.INVALID_CHARACTER_ERR, msg);
    }
    return new ElementDefinitionImpl(this, name);
}
	/**
     * DOM Level 3 CR - Experimental.
     * version - An attribute specifying, as part of the XML declaration,
     * the version number of this document.
     */
public void setXmlVersion(String value) {
    if (value.equals("1.0") || value.equals("1.1")) {
        // when the version set is different than already set.
        if (!getXmlVersion().equals(value)) {
            xmlVersionChanged = true;
            //change the normalization value back to false
            isNormalized(false);
            version = value;
        }
    } else {
        //NOT_SUPPORTED_ERR: Raised if the vesion is set to a value that is not supported by
        //this document
        //we dont support any other XML version
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null);
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, msg);
    }
    if ((getXmlVersion()).equals("1.1")) {
        xml11Version = true;
    } else {
        xml11Version = false;
    }
}
	/**
     * DOM Level 3 WD - Experimental.
     * Renaming node
     */
public Node renameNode(Node n, String namespaceURI, String name) throws DOMException {
    if (errorChecking && n.getOwnerDocument() != this && n != this) {
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null);
        throw new DOMException(DOMException.WRONG_DOCUMENT_ERR, msg);
    }
    switch(n.getNodeType()) {
        case ELEMENT_NODE:
            {
                ElementImpl el = (ElementImpl) n;
                if (el instanceof ElementNSImpl) {
                    ((ElementNSImpl) el).rename(namespaceURI, name);
                    // fire user data NODE_RENAMED event
                    callUserDataHandlers(el, null, UserDataHandler.NODE_RENAMED);
                } else {
                    if (namespaceURI == null) {
                        if (errorChecking) {
                            int colon1 = name.indexOf(':');
                            if (colon1 != -1) {
                                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null);
                                throw new DOMException(DOMException.NAMESPACE_ERR, msg);
                            }
                            if (!isXMLName(name, xml11Version)) {
                                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null);
                                throw new DOMException(DOMException.INVALID_CHARACTER_ERR, msg);
                            }
                        }
                        el.rename(name);
                        // fire user data NODE_RENAMED event
                        callUserDataHandlers(el, null, UserDataHandler.NODE_RENAMED);
                    } else {
                        // we need to create a new object
                        ElementNSImpl nel = new ElementNSImpl(this, namespaceURI, name);
                        // register event listeners on new node
                        copyEventListeners(el, nel);
                        // remove user data from old node
                        Hashtable data = removeUserDataTable(el);
                        // remove old node from parent if any
                        Node parent = el.getParentNode();
                        Node nextSib = el.getNextSibling();
                        if (parent != null) {
                            parent.removeChild(el);
                        }
                        // move children to new node
                        Node child = el.getFirstChild();
                        while (child != null) {
                            el.removeChild(child);
                            nel.appendChild(child);
                            child = el.getFirstChild();
                        }
                        // move specified attributes to new node
                        nel.moveSpecifiedAttributes(el);
                        // attach user data to new node
                        setUserDataTable(nel, data);
                        // and fire user data NODE_RENAMED event
                        callUserDataHandlers(el, nel, UserDataHandler.NODE_RENAMED);
                        // insert new node where old one was
                        if (parent != null) {
                            parent.insertBefore(nel, nextSib);
                        }
                        el = nel;
                    }
                }
                // fire ElementNameChanged event
                renamedElement((Element) n, el);
                return el;
            }
        case ATTRIBUTE_NODE:
            {
                AttrImpl at = (AttrImpl) n;
                // dettach attr from element
                Element el = at.getOwnerElement();
                if (el != null) {
                    el.removeAttributeNode(at);
                }
                if (n instanceof AttrNSImpl) {
                    ((AttrNSImpl) at).rename(namespaceURI, name);
                    // reattach attr to element
                    if (el != null) {
                        el.setAttributeNodeNS(at);
                    }
                    // fire user data NODE_RENAMED event
                    callUserDataHandlers(at, null, UserDataHandler.NODE_RENAMED);
                } else {
                    if (namespaceURI == null) {
                        at.rename(name);
                        // reattach attr to element
                        if (el != null) {
                            el.setAttributeNode(at);
                        }
                        // fire user data NODE_RENAMED event
                        callUserDataHandlers(at, null, UserDataHandler.NODE_RENAMED);
                    } else {
                        // we need to create a new object
                        AttrNSImpl nat = new AttrNSImpl(this, namespaceURI, name);
                        // register event listeners on new node
                        copyEventListeners(at, nat);
                        // remove user data from old node
                        Hashtable data = removeUserDataTable(at);
                        // move children to new node
                        Node child = at.getFirstChild();
                        while (child != null) {
                            at.removeChild(child);
                            nat.appendChild(child);
                            child = at.getFirstChild();
                        }
                        // attach user data to new node
                        setUserDataTable(nat, data);
                        // and fire user data NODE_RENAMED event
                        callUserDataHandlers(at, nat, UserDataHandler.NODE_RENAMED);
                        // reattach attr to element
                        if (el != null) {
                            el.setAttributeNode(nat);
                        }
                        at = nat;
                    }
                }
                // fire AttributeNameChanged event
                renamedAttrNode((Attr) n, at);
                return at;
            }
        default:
            {
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null);
                throw new DOMException(DOMException.NOT_SUPPORTED_ERR, msg);
            }
    }
}
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
public void setAsync(boolean async) {
    if (async) {
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null);
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, msg);
    }
}
	/**
     * DOM Level 3 WD - Experimental.
     * Save the document or the given node and all its descendants to a string
     * (i.e. serialize the document or node).
     * <br>The parameters used in the <code>LSSerializer</code> interface are
     * assumed to have their default values when invoking this method.
     * <br> The result of a call to this method is the same the result of a
     * call to <code>LSSerializer.writeToString</code> with the document as
     * the node to write.
     * @param node Specifies what to serialize, if this parameter is
     *   <code>null</code> the whole document is serialized, if it's
     *   non-null the given node is serialized.
     * @return The serialized document or <code>null</code> in case an error
     *   occurred.
     * @exception DOMException
     *   WRONG_DOCUMENT_ERR: Raised if the node passed in as the node
     *   parameter is from an other document.
     */
public String saveXML(Node node) throws DOMException {
    if (errorChecking && node != null && this != node.getOwnerDocument()) {
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null);
        throw new DOMException(DOMException.WRONG_DOCUMENT_ERR, msg);
    }
    DOMImplementationLS domImplLS = (DOMImplementationLS) DOMImplementationImpl.getDOMImplementation();
    LSSerializer xmlWriter = domImplLS.createLSSerializer();
    if (node == null) {
        node = this;
    }
    return xmlWriter.writeToString(node);
}
	// isXMLName(String):boolean
/**
     * Checks if the given qualified name is legal with respect
     * to the version of XML to which this document must conform.
     *
     * @param prefix prefix of qualified name
     * @param local local part of qualified name
     */
public static final boolean isValidQName(String prefix, String local, boolean xml11Version) {
    // check that both prefix and local part match NCName
    if (local == null)
        return false;
    boolean validNCName = false;
    if (!xml11Version) {
        validNCName = (prefix == null || XMLChar.isValidNCName(prefix)) && XMLChar.isValidNCName(local);
    } else {
        validNCName = (prefix == null || XML11Char.isXML11ValidNCName(prefix)) && XML11Char.isXML11ValidNCName(local);
    }
    return validNCName;
}
	/**
     * Checks if the given qualified name is legal with respect
     * to the version of XML to which this document must conform.
     *
     * @param prefix prefix of qualified name
     * @param local local part of qualified name
     */
protected final void checkQName(String prefix, String local) {
    if (!errorChecking) {
        return;
    }
    // check that both prefix and local part match NCName
    boolean validNCName = false;
    if (!xml11Version) {
        validNCName = (prefix == null || XMLChar.isValidNCName(prefix)) && XMLChar.isValidNCName(local);
    } else {
        validNCName = (prefix == null || XML11Char.isXML11ValidNCName(prefix)) && XML11Char.isXML11ValidNCName(local);
    }
    if (!validNCName) {
        // REVISIT: add qname parameter to the message
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null);
        throw new DOMException(DOMException.INVALID_CHARACTER_ERR, msg);
    }
}
}