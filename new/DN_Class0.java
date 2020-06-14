public class Class0 {
	private Object fRelatedNode;
	private Object ENTITY_REFERENCE_NODE;
	private Object XMLGRAMMAR_POOL_PROPERTY;
	private Object NSDECL;
	private Object CDATA_SECTION_NODE;
	private Object ELEMENT_NODE;
	private Object WELLFORMED;
	private Object XERCES_PROPERTY_PREFIX;
	private Object COMMENT_NODE;
	private Object COMMENTS;
	private Object out;
	private Object DOM_ERROR_HANDLER;
	private Object EMPTY_STRING;
	private Object PROCESSING_INSTRUCTION_NODE;
	private Object errorChecking;
	private Object SPLITCDATA;
	private Object uri;
	private Object fDocumentURI;
	private Object XML_SCHEMA;
	private Object rawname;
	private Object XML_DTD;
	private Object features;
	private Object SEVERITY_WARNING;
	private Object ENTITIES;
	private Object ENTITY_NODE;
	private Object encoding;
	private Object XERCES_VALIDATION;
	private Object DOCUMENT_TYPE_NODE;
	private Object PREFIX_XMLNS;
	private Object SEVERITY_ERROR;
	private Object CDATA;
	private Object SYMBOL_TABLE;
	private Object NAMESPACES;
	private Object NS_XMLSCHEMA;
	private Object DOM_DOMAIN;
	private Object fCurrentNode;
	private Object PSVI;
	private Object TEXT_NODE;
	private Object SCHEMA;
	private Object singleton;
	private Object localpart;
	private Object fErrorHandlerWrapper;
	private Object VALIDATE;
	private Object JAXP_SCHEMA_LANGUAGE;

	public Class0(Object fRelatedNode, Object ENTITY_REFERENCE_NODE, Object XMLGRAMMAR_POOL_PROPERTY, Object NSDECL, Object CDATA_SECTION_NODE, Object ELEMENT_NODE, Object WELLFORMED, Object XERCES_PROPERTY_PREFIX, Object COMMENT_NODE, Object COMMENTS, Object out, Object DOM_ERROR_HANDLER, Object EMPTY_STRING, Object PROCESSING_INSTRUCTION_NODE, Object errorChecking, Object SPLITCDATA, Object uri, Object fDocumentURI, Object XML_SCHEMA, Object rawname, Object XML_DTD, Object features, Object SEVERITY_WARNING, Object ENTITIES, Object ENTITY_NODE, Object encoding, Object XERCES_VALIDATION, Object DOCUMENT_TYPE_NODE, Object PREFIX_XMLNS, Object SEVERITY_ERROR, Object CDATA, Object SYMBOL_TABLE, Object NAMESPACES, Object NS_XMLSCHEMA, Object DOM_DOMAIN, Object fCurrentNode, Object PSVI, Object TEXT_NODE, Object SCHEMA, Object singleton, Object localpart, Object fErrorHandlerWrapper, Object VALIDATE, Object JAXP_SCHEMA_LANGUAGE){
		this.fRelatedNode = fRelatedNode;
		this.ENTITY_REFERENCE_NODE = ENTITY_REFERENCE_NODE;
		this.XMLGRAMMAR_POOL_PROPERTY = XMLGRAMMAR_POOL_PROPERTY;
		this.NSDECL = NSDECL;
		this.CDATA_SECTION_NODE = CDATA_SECTION_NODE;
		this.ELEMENT_NODE = ELEMENT_NODE;
		this.WELLFORMED = WELLFORMED;
		this.XERCES_PROPERTY_PREFIX = XERCES_PROPERTY_PREFIX;
		this.COMMENT_NODE = COMMENT_NODE;
		this.COMMENTS = COMMENTS;
		this.out = out;
		this.DOM_ERROR_HANDLER = DOM_ERROR_HANDLER;
		this.EMPTY_STRING = EMPTY_STRING;
		this.PROCESSING_INSTRUCTION_NODE = PROCESSING_INSTRUCTION_NODE;
		this.errorChecking = errorChecking;
		this.SPLITCDATA = SPLITCDATA;
		this.uri = uri;
		this.fDocumentURI = fDocumentURI;
		this.XML_SCHEMA = XML_SCHEMA;
		this.rawname = rawname;
		this.XML_DTD = XML_DTD;
		this.features = features;
		this.SEVERITY_WARNING = SEVERITY_WARNING;
		this.ENTITIES = ENTITIES;
		this.ENTITY_NODE = ENTITY_NODE;
		this.encoding = encoding;
		this.XERCES_VALIDATION = XERCES_VALIDATION;
		this.DOCUMENT_TYPE_NODE = DOCUMENT_TYPE_NODE;
		this.PREFIX_XMLNS = PREFIX_XMLNS;
		this.SEVERITY_ERROR = SEVERITY_ERROR;
		this.CDATA = CDATA;
		this.SYMBOL_TABLE = SYMBOL_TABLE;
		this.NAMESPACES = NAMESPACES;
		this.NS_XMLSCHEMA = NS_XMLSCHEMA;
		this.DOM_DOMAIN = DOM_DOMAIN;
		this.fCurrentNode = fCurrentNode;
		this.PSVI = PSVI;
		this.TEXT_NODE = TEXT_NODE;
		this.SCHEMA = SCHEMA;
		this.singleton = singleton;
		this.localpart = localpart;
		this.fErrorHandlerWrapper = fErrorHandlerWrapper;
		this.VALIDATE = VALIDATE;
		this.JAXP_SCHEMA_LANGUAGE = JAXP_SCHEMA_LANGUAGE;
	}
	/**
     * 
     * This method acts as if the document was going through a save
     * and load cycle, putting the document in a "normal" form. The actual result
     * depends on the features being set and governing what operations actually
     * take place. See setNormalizationFeature for details. Noticeably this method
     * normalizes Text nodes, makes the document "namespace wellformed",
     * according to the algorithm described below in pseudo code, by adding missing
     * namespace declaration attributes and adding or changing namespace prefixes, updates
     * the replacement tree of EntityReference nodes,normalizes attribute values, etc.
     * 
     * @param node   Modified node or null. If node is returned, we need
     *               to normalize again starting on the node returned.
     * @return  the normalized Node
     */
protected Node normalizeNode(Node node) {
    int type = node.getNodeType();
    boolean wellformed;
    fLocator.fRelatedNode = node;
    switch(type) {
        case Node.DOCUMENT_TYPE_NODE:
            {
                if (DEBUG_ND) {
                    System.out.println("==>normalizeNode:{doctype}");
                }
                DocumentTypeImpl docType = (DocumentTypeImpl) node;
                fDTDValidator = (XMLDTDValidator) CoreDOMImplementationImpl.singleton.getValidator(XMLGrammarDescription.XML_DTD);
                fDTDValidator.setDocumentHandler(this);
                fConfiguration.setProperty(Constants.XERCES_PROPERTY_PREFIX + Constants.XMLGRAMMAR_POOL_PROPERTY, createGrammarPool(docType));
                fDTDValidator.reset(fConfiguration);
                fDTDValidator.startDocument(new SimpleLocator(fDocument.fDocumentURI, fDocument.fDocumentURI, -1, -1), fDocument.encoding, fNamespaceContext, null);
                fDTDValidator.doctypeDecl(docType.getName(), docType.getPublicId(), docType.getSystemId(), null);
                //REVISIT: well-formness encoding info
                break;
            }
        case Node.ELEMENT_NODE:
            {
                if (DEBUG_ND) {
                    System.out.println("==>normalizeNode:{element} " + node.getNodeName());
                }
                //application has set the value of well-formed features to true
                if (fDocument.errorChecking) {
                    if (((fConfiguration.features & DOMConfigurationImpl.WELLFORMED) != 0) && fDocument.isXMLVersionChanged()) {
                        if (fNamespaceValidation) {
                            wellformed = CoreDocumentImpl.isValidQName(node.getPrefix(), node.getLocalName(), fDocument.isXML11Version());
                        } else {
                            wellformed = CoreDocumentImpl.isXMLName(node.getNodeName(), fDocument.isXML11Version());
                        }
                        if (!wellformed) {
                            String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "wf-invalid-character-in-node-name", new Object[] { "Element", node.getNodeName() });
                            reportDOMError(fErrorHandler, fError, fLocator, msg, DOMError.SEVERITY_ERROR, "wf-invalid-character-in-node-name");
                        }
                    }
                }
                // push namespace context
                fNamespaceContext.pushContext();
                fLocalNSBinder.reset();
                ElementImpl elem = (ElementImpl) node;
                if (elem.needsSyncChildren()) {
                    elem.synchronizeChildren();
                }
                AttributeMap attributes = (elem.hasAttributes()) ? (AttributeMap) elem.getAttributes() : null;
                // fix namespaces and remove default attributes
                if ((fConfiguration.features & DOMConfigurationImpl.NAMESPACES) != 0) {
                    // fix namespaces
                    // normalize attribute values
                    // remove default attributes
                    namespaceFixUp(elem, attributes);
                    if ((fConfiguration.features & DOMConfigurationImpl.NSDECL) == 0 && attributes != null) {
                        for (int i = 0; i < attributes.getLength(); ++i) {
                            Attr att = (Attr) attributes.getItem(i);
                            if (XMLSymbols.PREFIX_XMLNS.equals(att.getPrefix()) || XMLSymbols.PREFIX_XMLNS.equals(att.getName())) {
                                elem.removeAttributeNode(att);
                                --i;
                            }
                        }
                    }
                } else {
                    if (attributes != null) {
                        for (int i = 0; i < attributes.getLength(); ++i) {
                            Attr attr = (Attr) attributes.item(i);
                            //removeDefault(attr, attributes);
                            attr.normalize();
                            if (fDocument.errorChecking && ((fConfiguration.features & DOMConfigurationImpl.WELLFORMED) != 0)) {
                                isAttrValueWF(fErrorHandler, fError, fLocator, attributes, (AttrImpl) attr, attr.getValue(), fDocument.isXML11Version());
                                if (fDocument.isXMLVersionChanged()) {
                                    wellformed = CoreDocumentImpl.isXMLName(node.getNodeName(), fDocument.isXML11Version());
                                    if (!wellformed) {
                                        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "wf-invalid-character-in-node-name", new Object[] { "Attr", node.getNodeName() });
                                        reportDOMError(fErrorHandler, fError, fLocator, msg, DOMError.SEVERITY_ERROR, "wf-invalid-character-in-node-name");
                                    }
                                }
                            }
                        }
                    }
                }
                if (fValidationHandler != null) {
                    // REVISIT: possible solutions to discard default content are:
                    //         either we pass some flag to XML Schema validator
                    //         or rely on the PSVI information.
                    fAttrProxy.setAttributes(attributes, fDocument, elem);
                    // updates global qname
                    updateQName(elem, fQName);
                    // set error node in the dom error wrapper
                    // so if error occurs we can report an error node
                    fConfiguration.fErrorHandlerWrapper.fCurrentNode = node;
                    fCurrentNode = node;
                    // call re-validation handler
                    fValidationHandler.startElement(fQName, fAttrProxy, null);
                }
                if (fDTDValidator != null) {
                    // REVISIT: possible solutions to discard default content are:
                    //         either we pass some flag to XML Schema validator
                    //         or rely on the PSVI information.
                    fAttrProxy.setAttributes(attributes, fDocument, elem);
                    // updates global qname
                    updateQName(elem, fQName);
                    // set error node in the dom error wrapper
                    // so if error occurs we can report an error node
                    fConfiguration.fErrorHandlerWrapper.fCurrentNode = node;
                    fCurrentNode = node;
                    // call re-validation handler
                    fDTDValidator.startElement(fQName, fAttrProxy, null);
                }
                // normalize children
                Node kid, next;
                for (kid = elem.getFirstChild(); kid != null; kid = next) {
                    next = kid.getNextSibling();
                    kid = normalizeNode(kid);
                    if (kid != null) {
                        // don't advance
                        next = kid;
                    }
                }
                if (DEBUG_ND) {
                    // normalized subtree
                    System.out.println("***The children of {" + node.getNodeName() + "} are normalized");
                    for (kid = elem.getFirstChild(); kid != null; kid = next) {
                        next = kid.getNextSibling();
                        System.out.println(kid.getNodeName() + "[" + kid.getNodeValue() + "]");
                    }
                }
                if (fValidationHandler != null) {
                    // updates global qname
                    updateQName(elem, fQName);
                    //
                    // set error node in the dom error wrapper
                    // so if error occurs we can report an error node
                    fConfiguration.fErrorHandlerWrapper.fCurrentNode = node;
                    fCurrentNode = node;
                    fValidationHandler.endElement(fQName, null);
                }
                if (fDTDValidator != null) {
                    // updates global qname
                    updateQName(elem, fQName);
                    //
                    // set error node in the dom error wrapper
                    // so if error occurs we can report an error node
                    fConfiguration.fErrorHandlerWrapper.fCurrentNode = node;
                    fCurrentNode = node;
                    fDTDValidator.endElement(fQName, null);
                }
                // pop namespace context
                fNamespaceContext.popContext();
                break;
            }
        case Node.COMMENT_NODE:
            {
                if (DEBUG_ND) {
                    System.out.println("==>normalizeNode:{comments}");
                }
                if ((fConfiguration.features & DOMConfigurationImpl.COMMENTS) == 0) {
                    Node prevSibling = node.getPreviousSibling();
                    Node parent = node.getParentNode();
                    // remove the comment node
                    parent.removeChild(node);
                    if (prevSibling != null && prevSibling.getNodeType() == Node.TEXT_NODE) {
                        Node nextSibling = prevSibling.getNextSibling();
                        if (nextSibling != null && nextSibling.getNodeType() == Node.TEXT_NODE) {
                            ((TextImpl) nextSibling).insertData(0, prevSibling.getNodeValue());
                            parent.removeChild(prevSibling);
                            return nextSibling;
                        }
                    }
                } else //if comment node need not be removed
                {
                    if (fDocument.errorChecking && ((fConfiguration.features & DOMConfigurationImpl.WELLFORMED) != 0)) {
                        String commentdata = ((Comment) node).getData();
                        // check comments for invalid xml chracter as per the version
                        // of the document                            
                        isCommentWF(fErrorHandler, fError, fLocator, commentdata, fDocument.isXML11Version());
                    }
                }
                //end-else if comment node is not to be removed.
                break;
            }
        case Node.ENTITY_REFERENCE_NODE:
            {
                if (DEBUG_ND) {
                    System.out.println("==>normalizeNode:{entityRef} " + node.getNodeName());
                }
                if ((fConfiguration.features & DOMConfigurationImpl.ENTITIES) == 0) {
                    Node prevSibling = node.getPreviousSibling();
                    Node parent = node.getParentNode();
                    ((EntityReferenceImpl) node).setReadOnly(false, true);
                    expandEntityRef(parent, node);
                    parent.removeChild(node);
                    Node next = (prevSibling != null) ? prevSibling.getNextSibling() : parent.getFirstChild();
                    // we should not advance
                    if (prevSibling != null && next != null && prevSibling.getNodeType() == Node.TEXT_NODE && next.getNodeType() == Node.TEXT_NODE) {
                        // Don't advance                          
                        return prevSibling;
                    }
                    return next;
                } else {
                    if (fDocument.errorChecking && ((fConfiguration.features & DOMConfigurationImpl.WELLFORMED) != 0) && fDocument.isXMLVersionChanged()) {
                        CoreDocumentImpl.isXMLName(node.getNodeName(), fDocument.isXML11Version());
                    }
                // REVISIT: traverse entity reference and send appropriate calls to the validator
                // (no normalization should be performed for the children).
                }
                break;
            }
        case Node.CDATA_SECTION_NODE:
            {
                if (DEBUG_ND) {
                    System.out.println("==>normalizeNode:{cdata}");
                }
                if ((fConfiguration.features & DOMConfigurationImpl.CDATA) == 0) {
                    // convert CDATA to TEXT nodes
                    Node prevSibling = node.getPreviousSibling();
                    if (prevSibling != null && prevSibling.getNodeType() == Node.TEXT_NODE) {
                        ((Text) prevSibling).appendData(node.getNodeValue());
                        node.getParentNode().removeChild(node);
                        //don't advance                        
                        return prevSibling;
                    } else {
                        Text text = fDocument.createTextNode(node.getNodeValue());
                        Node parent = node.getParentNode();
                        node = parent.replaceChild(text, node);
                        //don't advance
                        return text;
                    }
                }
                // send characters call for CDATA
                if (fValidationHandler != null) {
                    // set error node in the dom error wrapper
                    // so if error occurs we can report an error node
                    fConfiguration.fErrorHandlerWrapper.fCurrentNode = node;
                    fCurrentNode = node;
                    fValidationHandler.startCDATA(null);
                    fValidationHandler.characterData(node.getNodeValue(), null);
                    fValidationHandler.endCDATA(null);
                }
                if (fDTDValidator != null) {
                    // set error node in the dom error wrapper
                    // so if error occurs we can report an error node
                    fConfiguration.fErrorHandlerWrapper.fCurrentNode = node;
                    fCurrentNode = node;
                    fDTDValidator.startCDATA(null);
                    fDTDValidator.characterData(node.getNodeValue(), null);
                    fDTDValidator.endCDATA(null);
                }
                String value = node.getNodeValue();
                if ((fConfiguration.features & DOMConfigurationImpl.SPLITCDATA) != 0) {
                    int index;
                    Node parent = node.getParentNode();
                    if (fDocument.errorChecking) {
                        isXMLCharWF(fErrorHandler, fError, fLocator, node.getNodeValue(), fDocument.isXML11Version());
                    }
                    while ((index = value.indexOf("]]>")) >= 0) {
                        node.setNodeValue(value.substring(0, index + 2));
                        value = value.substring(index + 2);
                        Node firstSplitNode = node;
                        Node newChild = fDocument.createCDATASection(value);
                        parent.insertBefore(newChild, node.getNextSibling());
                        node = newChild;
                        // issue warning
                        fLocator.fRelatedNode = firstSplitNode;
                        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "cdata-sections-splitted", null);
                        reportDOMError(fErrorHandler, fError, fLocator, msg, DOMError.SEVERITY_WARNING, "cdata-sections-splitted");
                    }
                } else if (fDocument.errorChecking) {
                    // check well-formedness
                    isCDataWF(fErrorHandler, fError, fLocator, value, fDocument.isXML11Version());
                }
                break;
            }
        case Node.TEXT_NODE:
            {
                if (DEBUG_ND) {
                    System.out.println("==>normalizeNode(text):{" + node.getNodeValue() + "}");
                }
                // If node is a text node, we need to check for one of two
                // conditions:
                //   1) There is an adjacent text node
                //   2) There is no adjacent text node, but node is
                //      an empty text node.
                Node next = node.getNextSibling();
                // If an adjacent text node, merge it with this node
                if (next != null && next.getNodeType() == Node.TEXT_NODE) {
                    ((Text) node).appendData(next.getNodeValue());
                    node.getParentNode().removeChild(next);
                    // Don't advance;                   
                    return node;
                } else if (node.getNodeValue().length() == 0) {
                    // If kid is empty, remove it
                    node.getParentNode().removeChild(node);
                } else {
                    // validator.characters() call and well-formness
                    // Don't send characters or check well-formness in the following cases:
                    // 1. entities is false, next child is entity reference: expand tree first
                    // 2. comments is false, and next child is comment
                    // 3. cdata is false, and next child is cdata
                    short nextType = (next != null) ? next.getNodeType() : -1;
                    if (nextType == -1 || !(((fConfiguration.features & DOMConfigurationImpl.ENTITIES) == 0 && nextType == Node.ENTITY_NODE) || ((fConfiguration.features & DOMConfigurationImpl.COMMENTS) == 0 && nextType == Node.COMMENT_NODE) || ((fConfiguration.features & DOMConfigurationImpl.CDATA) == 0) && nextType == Node.CDATA_SECTION_NODE)) {
                        if (fDocument.errorChecking && ((fConfiguration.features & DOMConfigurationImpl.WELLFORMED) != 0)) {
                            isXMLCharWF(fErrorHandler, fError, fLocator, node.getNodeValue(), fDocument.isXML11Version());
                        }
                        if (fValidationHandler != null) {
                            fConfiguration.fErrorHandlerWrapper.fCurrentNode = node;
                            fCurrentNode = node;
                            fValidationHandler.characterData(node.getNodeValue(), null);
                            if (DEBUG_ND) {
                                System.out.println("=====>characterData()," + nextType);
                            }
                        }
                        if (fDTDValidator != null) {
                            fConfiguration.fErrorHandlerWrapper.fCurrentNode = node;
                            fCurrentNode = node;
                            fDTDValidator.characterData(node.getNodeValue(), null);
                            if (DEBUG_ND) {
                                System.out.println("=====>characterData()," + nextType);
                            }
                            if (allWhitespace) {
                                allWhitespace = false;
                                ((TextImpl) node).setIgnorableWhitespace(true);
                            }
                        }
                    } else {
                        if (DEBUG_ND) {
                            System.out.println("=====>don't send characters()," + nextType);
                        }
                    }
                }
                break;
            }
        case Node.PROCESSING_INSTRUCTION_NODE:
            {
                //do the well-formed valid PI target name , data check when application has set the value of well-formed feature to true
                if (fDocument.errorChecking && (fConfiguration.features & DOMConfigurationImpl.WELLFORMED) != 0) {
                    ProcessingInstruction pinode = (ProcessingInstruction) node;
                    String target = pinode.getTarget();
                    //1.check PI target name
                    if (fDocument.isXML11Version()) {
                        wellformed = XML11Char.isXML11ValidName(target);
                    } else {
                        wellformed = XMLChar.isValidName(target);
                    }
                    if (!wellformed) {
                        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "wf-invalid-character-in-node-name", new Object[] { "Element", node.getNodeName() });
                        reportDOMError(fErrorHandler, fError, fLocator, msg, DOMError.SEVERITY_ERROR, "wf-invalid-character-in-node-name");
                    }
                    //2. check PI data
                    //processing isntruction data may have certain characters
                    //which may not be valid XML character               
                    isXMLCharWF(fErrorHandler, fError, fLocator, pinode.getData(), fDocument.isXML11Version());
                }
            }
    }
    //end of switch
    return null;
}
	/**
     * Normalizes document.
     * Note: reset() must be called before this method.
     */
protected void normalizeDocument(CoreDocumentImpl document, DOMConfigurationImpl config) {
    fDocument = document;
    fConfiguration = config;
    // intialize and reset DOMNormalizer component
    // 
    fSymbolTable = (SymbolTable) fConfiguration.getProperty(DOMConfigurationImpl.SYMBOL_TABLE);
    // reset namespace context
    fNamespaceContext.reset();
    fNamespaceContext.declarePrefix(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
    if ((fConfiguration.features & DOMConfigurationImpl.VALIDATE) != 0) {
        String schemaLang = (String) fConfiguration.getProperty(DOMConfigurationImpl.JAXP_SCHEMA_LANGUAGE);
        if (schemaLang != null && schemaLang.equals(Constants.NS_XMLSCHEMA)) {
            fValidationHandler = CoreDOMImplementationImpl.singleton.getValidator(XMLGrammarDescription.XML_SCHEMA);
            fConfiguration.setFeature(DOMConfigurationImpl.SCHEMA, true);
            // report fatal error on DOM Level 1 nodes
            fNamespaceValidation = true;
            // check if we need to fill in PSVI
            fPSVI = ((fConfiguration.features & DOMConfigurationImpl.PSVI) != 0) ? true : false;
        }
        fConfiguration.setFeature(DOMConfigurationImpl.XERCES_VALIDATION, true);
        // reset ID table           
        fDocument.clearIdentifiers();
        if (fValidationHandler != null)
            // reset schema validator
            ((XMLComponent) fValidationHandler).reset(fConfiguration);
    }
    fErrorHandler = (DOMErrorHandler) fConfiguration.getParameter(Constants.DOM_ERROR_HANDLER);
    if (fValidationHandler != null) {
        fValidationHandler.setDocumentHandler(this);
        fValidationHandler.startDocument(new SimpleLocator(fDocument.fDocumentURI, fDocument.fDocumentURI, -1, -1), fDocument.encoding, fNamespaceContext, null);
    }
    try {
        Node kid, next;
        for (kid = fDocument.getFirstChild(); kid != null; kid = next) {
            next = kid.getNextSibling();
            kid = normalizeNode(kid);
            if (kid != null) {
                // don't advance
                next = kid;
            }
        }
        // release resources
        if (fValidationHandler != null) {
            fValidationHandler.endDocument(null);
            CoreDOMImplementationImpl.singleton.releaseValidator(XMLGrammarDescription.XML_SCHEMA, fValidationHandler);
            fValidationHandler = null;
        }
    } catch (RuntimeException e) {
        if (e == abort)
            // processing aborted by the user
            return;
        // otherwise re-throw.
        throw e;
    }
}
	/** Sets the document source. */
public void setDocumentSource(XMLDocumentSource source) {
}
	/** Returns the document source. */
public XMLDocumentSource getDocumentSource() {
    return null;
}
	/**
         * This method adds default declarations
		 * @see org.apache.xerces.xni.XMLAttributes#addAttribute(QName, String, String)
		 */
public int addAttribute(QName qname, String attrType, String attrValue) {
    int index = fElement.getXercesAttribute(qname.uri, qname.localpart);
    // add defaults to the tree
    if (index < 0) {
        // the default attribute was removed by a user and needed to 
        // be added back
        AttrImpl attr = (AttrImpl) ((CoreDocumentImpl) fElement.getOwnerDocument()).createAttributeNS(qname.uri, qname.rawname, qname.localpart);
        // REVISIT: the following should also update ID table
        index = fElement.setXercesAttributeNode(attr);
        attr.setNodeValue(attrValue);
        fAugmentations.insertElementAt(new AugmentationsImpl(), index);
        attr.setSpecified(false);
    } else {
    // default attribute is in the tree
    // we don't need to do anything since prefix was already fixed
    // at the namespace fixup time and value must be same value, otherwise
    // attribute will be treated as specified and we will never reach 
    // this method.
    }
    return index;
}
}