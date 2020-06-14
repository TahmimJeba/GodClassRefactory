public class Class3 {
	private Object NOTATION_NODE;
	private Object fType;
	private Object SEVERITY_WARNING;
	private Object FILTER_REJECT;
	private Object ENTITY_NODE;
	private Object fRelatedData;
	private Object ELEMENT_NODE;
	private Object fSeverity;
	private Object SHOW_ENTITY_REFERENCE;
	private Object out;
	private Object TEXT_NODE;
	private Object PROCESSING_INSTRUCTION_NODE;
	private Object FILTER_SKIP;
	private Object FILTER_INTERRUPT;

	public Class3(Object NOTATION_NODE, Object fType, Object SEVERITY_WARNING, Object FILTER_REJECT, Object ENTITY_NODE, Object fRelatedData, Object ELEMENT_NODE, Object fSeverity, Object SHOW_ENTITY_REFERENCE, Object out, Object TEXT_NODE, Object PROCESSING_INSTRUCTION_NODE, Object FILTER_SKIP, Object FILTER_INTERRUPT){
		this.NOTATION_NODE = NOTATION_NODE;
		this.fType = fType;
		this.SEVERITY_WARNING = SEVERITY_WARNING;
		this.FILTER_REJECT = FILTER_REJECT;
		this.ENTITY_NODE = ENTITY_NODE;
		this.fRelatedData = fRelatedData;
		this.ELEMENT_NODE = ELEMENT_NODE;
		this.fSeverity = fSeverity;
		this.SHOW_ENTITY_REFERENCE = SHOW_ENTITY_REFERENCE;
		this.out = out;
		this.TEXT_NODE = TEXT_NODE;
		this.PROCESSING_INSTRUCTION_NODE = PROCESSING_INSTRUCTION_NODE;
		this.FILTER_SKIP = FILTER_SKIP;
		this.FILTER_INTERRUPT = FILTER_INTERRUPT;
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
}