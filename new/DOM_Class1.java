public class Class1 {
	private Object ATTRIBUTE_PSVI;
	private Object SHOW_ELEMENT;
	private Object FILTER_REJECT;
	private Object offset;
	private Object TRUE;
	private Object SHOW_COMMENT;
	private Object ATTRIBUTE_DECLARED;
	private Object SHOW_TEXT;
	private Object out;
	private Object ELEMENT_PSVI;
	private Object TEXT_NODE;
	private Object ch;
	private Object localpart;
	private Object length;
	private Object FILTER_SKIP;
	private Object uri;
	private Object SHOW_CDATA_SECTION;
	private Object XMLNS_URI;
	private Object FILTER_INTERRUPT;
	private Object rawname;

	public Class1(Object ATTRIBUTE_PSVI, Object SHOW_ELEMENT, Object FILTER_REJECT, Object offset, Object TRUE, Object SHOW_COMMENT, Object ATTRIBUTE_DECLARED, Object SHOW_TEXT, Object out, Object ELEMENT_PSVI, Object TEXT_NODE, Object ch, Object localpart, Object length, Object FILTER_SKIP, Object uri, Object SHOW_CDATA_SECTION, Object XMLNS_URI, Object FILTER_INTERRUPT, Object rawname){
		this.ATTRIBUTE_PSVI = ATTRIBUTE_PSVI;
		this.SHOW_ELEMENT = SHOW_ELEMENT;
		this.FILTER_REJECT = FILTER_REJECT;
		this.offset = offset;
		this.TRUE = TRUE;
		this.SHOW_COMMENT = SHOW_COMMENT;
		this.ATTRIBUTE_DECLARED = ATTRIBUTE_DECLARED;
		this.SHOW_TEXT = SHOW_TEXT;
		this.out = out;
		this.ELEMENT_PSVI = ELEMENT_PSVI;
		this.TEXT_NODE = TEXT_NODE;
		this.ch = ch;
		this.localpart = localpart;
		this.length = length;
		this.FILTER_SKIP = FILTER_SKIP;
		this.uri = uri;
		this.SHOW_CDATA_SECTION = SHOW_CDATA_SECTION;
		this.XMLNS_URI = XMLNS_URI;
		this.FILTER_INTERRUPT = FILTER_INTERRUPT;
		this.rawname = rawname;
	}
	// doctypeDecl(String,String,String)
/**
     * The start of an element. If the document specifies the start element
     * by using an empty tag, then the startElement method will immediately
     * be followed by the endElement method, with no intervening methods.
     *
     * @param element    The name of the element.
     * @param attributes The element attributes.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>startElement (" + element.rawname + ")");
    }
    if (!fDeferNodeExpansion) {
        if (fFilterReject) {
            return;
        }
        Element el = createElementNode(element);
        int attrCount = attributes.getLength();
        for (int i = 0; i < attrCount; i++) {
            attributes.getName(i, fAttrQName);
            Attr attr = createAttrNode(fAttrQName);
            String attrValue = attributes.getValue(i);
            AttributePSVI attrPSVI = (AttributePSVI) attributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_PSVI);
            if (fStorePSVI && attrPSVI != null) {
                ((PSVIAttrNSImpl) attr).setPSVI(attrPSVI);
            }
            attr.setValue(attrValue);
            el.setAttributeNode(attr);
            //       value from the attribute list. -Ac
            if (fDocumentImpl != null) {
                AttrImpl attrImpl = (AttrImpl) attr;
                Object type = null;
                boolean id = false;
                // namespaces to false when schema processing is turned on.
                if (attrPSVI != null && fNamespaceAware) {
                    // XML Schema
                    type = attrPSVI.getMemberTypeDefinition();
                    if (type == null) {
                        type = attrPSVI.getTypeDefinition();
                        if (type != null) {
                            id = ((XSSimpleType) type).isIDType();
                            attrImpl.setType(type);
                        }
                    } else {
                        id = ((XSSimpleType) type).isIDType();
                        attrImpl.setType(type);
                    }
                } else {
                    // DTD
                    boolean isDeclared = Boolean.TRUE.equals(attributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_DECLARED));
                    // in the DTD.
                    if (isDeclared) {
                        type = attributes.getType(i);
                        id = "ID".equals(type);
                    }
                    attrImpl.setType(type);
                }
                if (id) {
                    ((ElementImpl) el).setIdAttributeNode(attr, true);
                }
                attrImpl.setSpecified(attributes.isSpecified(i));
            // REVISIT: Handle entities in attribute value.
            }
        }
        setCharacterData(false);
        if (augs != null) {
            ElementPSVI elementPSVI = (ElementPSVI) augs.getItem(Constants.ELEMENT_PSVI);
            if (elementPSVI != null && fNamespaceAware) {
                XSTypeDefinition type = elementPSVI.getMemberTypeDefinition();
                if (type == null) {
                    type = elementPSVI.getTypeDefinition();
                }
                ((ElementNSImpl) el).setType(type);
            }
        }
        // filter nodes
        if (fDOMFilter != null && !fInEntityRef) {
            if (fRoot.rawname == null) {
                // fill value of the root element
                fRoot.setValues(element);
            } else {
                short code = fDOMFilter.startElement(el);
                switch(code) {
                    case LSParserFilter.FILTER_INTERRUPT:
                        {
                            throw abort;
                        }
                    case LSParserFilter.FILTER_REJECT:
                        {
                            fFilterReject = true;
                            fRejectedElement.setValues(element);
                            return;
                        }
                    case LSParserFilter.FILTER_SKIP:
                        {
                            fSkippedElemStack.push(element.clone());
                            return;
                        }
                    default:
                        {
                        }
                }
            }
        }
        fCurrentNode.appendChild(el);
        fCurrentNode = el;
    } else {
        Object type = null;
        if (augs != null) {
            ElementPSVI elementPSVI = (ElementPSVI) augs.getItem(Constants.ELEMENT_PSVI);
            if (elementPSVI != null) {
                type = elementPSVI.getMemberTypeDefinition();
                if (type == null) {
                    type = elementPSVI.getTypeDefinition();
                }
            }
        }
        int el = fDeferredDocumentImpl.createDeferredElement(fNamespaceAware ? element.uri : null, element.rawname, type);
        int attrCount = attributes.getLength();
        for (int i = 0; i < attrCount; i++) {
            // set type information
            AttributePSVI attrPSVI = (AttributePSVI) attributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_PSVI);
            boolean id = false;
            // namespaces to false when schema processing is turned on.
            if (attrPSVI != null && fNamespaceAware) {
                // XML Schema
                type = attrPSVI.getMemberTypeDefinition();
                if (type == null) {
                    type = attrPSVI.getTypeDefinition();
                    if (type != null) {
                        id = ((XSSimpleType) type).isIDType();
                    }
                } else {
                    id = ((XSSimpleType) type).isIDType();
                }
            } else {
                // DTD
                boolean isDeclared = Boolean.TRUE.equals(attributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_DECLARED));
                // in the DTD.
                if (isDeclared) {
                    type = attributes.getType(i);
                    id = "ID".equals(type);
                }
            }
            // create attribute
            fDeferredDocumentImpl.setDeferredAttribute(el, attributes.getQName(i), attributes.getURI(i), attributes.getValue(i), attributes.isSpecified(i), id, type);
        }
        fDeferredDocumentImpl.appendChild(fCurrentNodeIndex, el);
        fCurrentNodeIndex = el;
    }
}
	// endAttlist()
// method to create an element node.
// subclasses can override this method to create element nodes in other ways.
protected Element createElementNode(QName element) {
    Element el = null;
    if (fNamespaceAware) {
        // own constructor to reuse the strings we have here.
        if (fDocumentImpl != null) {
            el = fDocumentImpl.createElementNS(element.uri, element.rawname, element.localpart);
        } else {
            el = fDocument.createElementNS(element.uri, element.rawname);
        }
    } else {
        el = fDocument.createElement(element.rawname);
    }
    return el;
}
	// method to create an attribute node.
// subclasses can override this method to create attribute nodes in other ways.
protected Attr createAttrNode(QName attrQName) {
    Attr attr = null;
    if (fNamespaceAware) {
        if (fDocumentImpl != null) {
            // if we are using xerces DOM implementation, call our
            // own constructor to reuse the strings we have here.
            attr = fDocumentImpl.createAttributeNS(attrQName.uri, attrQName.rawname, attrQName.localpart);
        } else {
            attr = fDocument.createAttributeNS(attrQName.uri, attrQName.rawname);
        }
    } else {
        attr = fDocument.createAttribute(attrQName.rawname);
    }
    return attr;
}
	// textDecl(String,String)
/**
     * A comment.
     *
     * @param text The text in the comment.
     * @param augs       Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by application to signal an error.
     */
public void comment(XMLString text, Augmentations augs) throws XNIException {
    if (fInDTD) {
        if (fInternalSubset != null && !fInDTDExternalSubset) {
            fInternalSubset.append("<!-- ");
            fInternalSubset.append(text.toString());
            fInternalSubset.append(" -->");
        }
        return;
    }
    if (!fIncludeComments || fFilterReject) {
        return;
    }
    if (!fDeferNodeExpansion) {
        Comment comment = fDocument.createComment(text.toString());
        setCharacterData(false);
        fCurrentNode.appendChild(comment);
        if (fDOMFilter != null && !fInEntityRef && (fDOMFilter.getWhatToShow() & NodeFilter.SHOW_COMMENT) != 0) {
            short code = fDOMFilter.acceptNode(comment);
            switch(code) {
                case LSParserFilter.FILTER_INTERRUPT:
                    {
                        throw abort;
                    }
                case LSParserFilter.FILTER_REJECT:
                    {
                    // REVISIT: the constant FILTER_REJECT should be changed when new
                    // DOM LS specs gets published
                    // fall through to SKIP since comment has no children.
                    }
                case LSParserFilter.FILTER_SKIP:
                    {
                        // REVISIT: the constant FILTER_SKIP should be changed when new
                        // DOM LS specs gets published
                        fCurrentNode.removeChild(comment);
                        // make sure we don't loose chars if next event is characters()
                        fFirstChunk = true;
                        return;
                    }
                default:
                    {
                    // accept node
                    }
            }
        }
    } else {
        int comment = fDeferredDocumentImpl.createDeferredComment(text.toString());
        fDeferredDocumentImpl.appendChild(fCurrentNodeIndex, comment);
    }
}
	// startElement(QName,XMLAttributes)
/**
     * An empty element.
     *
     * @param element    The name of the element.
     * @param attributes The element attributes.
     * @param augs   Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
    startElement(element, attributes, augs);
    endElement(element, augs);
}
	// ignorableWhitespace(XMLString)
/**
     * The end of an element.
     *
     * @param element The name of the element.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endElement(QName element, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>endElement (" + element.rawname + ")");
    }
    if (!fDeferNodeExpansion) {
        // REVISIT: Should this happen after we call the filter?
        if (augs != null && fDocumentImpl != null && (fNamespaceAware || fStorePSVI)) {
            ElementPSVI elementPSVI = (ElementPSVI) augs.getItem(Constants.ELEMENT_PSVI);
            if (elementPSVI != null) {
                // end of an element.
                if (fNamespaceAware) {
                    XSTypeDefinition type = elementPSVI.getMemberTypeDefinition();
                    if (type == null) {
                        type = elementPSVI.getTypeDefinition();
                    }
                    ((ElementNSImpl) fCurrentNode).setType(type);
                }
                if (fStorePSVI) {
                    ((PSVIElementNSImpl) fCurrentNode).setPSVI(elementPSVI);
                }
            }
        }
        if (fDOMFilter != null) {
            if (fFilterReject) {
                if (element.equals(fRejectedElement)) {
                    fFilterReject = false;
                }
                return;
            }
            if (!fSkippedElemStack.isEmpty()) {
                if (fSkippedElemStack.peek().equals(element)) {
                    fSkippedElemStack.pop();
                    return;
                }
            }
            setCharacterData(false);
            if (!fRoot.equals(element) && !fInEntityRef && (fDOMFilter.getWhatToShow() & NodeFilter.SHOW_ELEMENT) != 0) {
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
                            // make sure that if any char data is available
                            // the fFirstChunk is true, so that if the next event
                            // is characters(), and the last node is text, we will copy
                            // the value already in the text node to fStringBuffer
                            // (not to loose it).
                            fFirstChunk = true;
                            // replace children
                            Node parent = fCurrentNode.getParentNode();
                            NodeList ls = fCurrentNode.getChildNodes();
                            int length = ls.getLength();
                            for (int i = 0; i < length; i++) {
                                parent.appendChild(ls.item(0));
                            }
                            parent.removeChild(fCurrentNode);
                            fCurrentNode = parent;
                            return;
                        }
                    default:
                        {
                        }
                }
            }
            fCurrentNode = fCurrentNode.getParentNode();
        } else // end-if DOMFilter
        {
            setCharacterData(false);
            fCurrentNode = fCurrentNode.getParentNode();
        }
    } else {
        fCurrentNodeIndex = fDeferredDocumentImpl.getParentNode(fCurrentNodeIndex, false);
    }
}
	// startCDATA()
/**
     * The end of a CDATA section.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endCDATA(Augmentations augs) throws XNIException {
    fInCDATASection = false;
    if (!fDeferNodeExpansion) {
        if (fFilterReject) {
            return;
        }
        if (fCurrentCDATASection != null) {
            if (fDOMFilter != null && !fInEntityRef && (fDOMFilter.getWhatToShow() & NodeFilter.SHOW_CDATA_SECTION) != 0) {
                short code = fDOMFilter.acceptNode(fCurrentCDATASection);
                switch(code) {
                    case LSParserFilter.FILTER_INTERRUPT:
                        {
                            throw abort;
                        }
                    case LSParserFilter.FILTER_REJECT:
                        {
                        // fall through to SKIP since CDATA section has no children.
                        }
                    case LSParserFilter.FILTER_SKIP:
                        {
                            Node parent = fCurrentNode.getParentNode();
                            parent.removeChild(fCurrentCDATASection);
                            fCurrentNode = parent;
                            return;
                        }
                    default:
                        {
                        // accept node
                        }
                }
            }
            fCurrentNode = fCurrentNode.getParentNode();
            fCurrentCDATASection = null;
        }
    } else {
        if (fCurrentCDATASectionIndex != -1) {
            fCurrentNodeIndex = fDeferredDocumentImpl.getParentNode(fCurrentNodeIndex, false);
            fCurrentCDATASectionIndex = -1;
        }
    }
}
	// endDTD()
/**
     * The start of a conditional section.
     *
     * @param type The type of the conditional section. This value will
     *             either be CONDITIONAL_INCLUDE or CONDITIONAL_IGNORE.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     *
     * @see #CONDITIONAL_INCLUDE
     * @see #CONDITIONAL_IGNORE
     */
public void startConditional(short type, Augmentations augs) throws XNIException {
}
	// elementDecl(String,String)
/**
     * An attribute declaration.
     *
     * @param elementName   The name of the element that this attribute
     *                      is associated with.
     * @param attributeName The name of the attribute.
     * @param type          The attribute type. This value will be one of
     *                      the following: "CDATA", "ENTITY", "ENTITIES",
     *                      "ENUMERATION", "ID", "IDREF", "IDREFS",
     *                      "NMTOKEN", "NMTOKENS", or "NOTATION".
     * @param enumeration   If the type has the value "ENUMERATION" or
     *                      "NOTATION", this array holds the allowed attribute
     *                      values; otherwise, this array is null.
     * @param defaultType   The attribute default type. This value will be
     *                      one of the following: "#FIXED", "#IMPLIED",
     *                      "#REQUIRED", or null.
     * @param defaultValue  The attribute default value, or null if no
     *                      default value is specified.
     * @param nonNormalizedDefaultValue  The attribute default value with no normalization
     *                      performed, or null if no default value is specified.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void attributeDecl(String elementName, String attributeName, String type, String[] enumeration, String defaultType, XMLString defaultValue, XMLString nonNormalizedDefaultValue, Augmentations augs) throws XNIException {
    // internal subset string
    if (fInternalSubset != null && !fInDTDExternalSubset) {
        fInternalSubset.append("<!ATTLIST ");
        fInternalSubset.append(elementName);
        fInternalSubset.append(' ');
        fInternalSubset.append(attributeName);
        fInternalSubset.append(' ');
        if (type.equals("ENUMERATION")) {
            fInternalSubset.append('(');
            for (int i = 0; i < enumeration.length; i++) {
                if (i > 0) {
                    fInternalSubset.append('|');
                }
                fInternalSubset.append(enumeration[i]);
            }
            fInternalSubset.append(')');
        } else {
            fInternalSubset.append(type);
        }
        if (defaultType != null) {
            fInternalSubset.append(' ');
            fInternalSubset.append(defaultType);
        }
        if (defaultValue != null) {
            fInternalSubset.append(" '");
            for (int i = 0; i < defaultValue.length; i++) {
                char c = defaultValue.ch[defaultValue.offset + i];
                if (c == '\'') {
                    fInternalSubset.append("&apos;");
                } else {
                    fInternalSubset.append(c);
                }
            }
            fInternalSubset.append('\'');
        }
        fInternalSubset.append(">\n");
    }
    // deferred expansion
    if (fDeferredDocumentImpl != null) {
        // get the default value
        if (defaultValue != null) {
            // get element definition
            int elementDefIndex = fDeferredDocumentImpl.lookupElementDefinition(elementName);
            // create element definition if not already there
            if (elementDefIndex == -1) {
                elementDefIndex = fDeferredDocumentImpl.createDeferredElementDefinition(elementName);
                fDeferredDocumentImpl.appendChild(fDocumentTypeIndex, elementDefIndex);
            }
            // add default attribute
            int attrIndex = fDeferredDocumentImpl.createDeferredAttribute(attributeName, defaultValue.toString(), false);
            if ("ID".equals(type)) {
                fDeferredDocumentImpl.setIdAttribute(attrIndex);
            }
            // REVISIT: set ID type correctly
            fDeferredDocumentImpl.appendChild(elementDefIndex, attrIndex);
        }
    } else // full expansion
    if (fDocumentImpl != null) {
        // get the default value
        if (defaultValue != null) {
            // get element definition node
            NamedNodeMap elements = ((DocumentTypeImpl) fDocumentType).getElements();
            ElementDefinitionImpl elementDef = (ElementDefinitionImpl) elements.getNamedItem(elementName);
            if (elementDef == null) {
                elementDef = fDocumentImpl.createElementDefinition(elementName);
                ((DocumentTypeImpl) fDocumentType).getElements().setNamedItem(elementDef);
            }
            // REVISIT: Check for uniqueness of element name? -Ac
            // create attribute and set properties
            boolean nsEnabled = fNamespaceAware;
            AttrImpl attr;
            if (nsEnabled) {
                String namespaceURI = null;
                // done here.
                if (attributeName.startsWith("xmlns:") || attributeName.equals("xmlns")) {
                    namespaceURI = NamespaceContext.XMLNS_URI;
                }
                attr = (AttrImpl) fDocumentImpl.createAttributeNS(namespaceURI, attributeName);
            } else {
                attr = (AttrImpl) fDocumentImpl.createAttribute(attributeName);
            }
            attr.setValue(defaultValue.toString());
            attr.setSpecified(false);
            attr.setIdAttribute("ID".equals(type));
            // add default attribute to element definition
            if (nsEnabled) {
                elementDef.getAttributes().setNamedItemNS(attr);
            } else {
                elementDef.getAttributes().setNamedItem(attr);
            }
        }
    }
// if NOT defer-node-expansion
}
	/*
     * When the first characters() call is received, the data is stored in
     * a new Text node. If right after the first characters() we receive another chunk of data,
     * the data from the Text node, following the new characters are appended
     * to the fStringBuffer and the text node data is set to empty.
     *
     * This function is called when the state is changed and the
     * data must be appended to the current node.
     *
     * Note: if DOMFilter is set, you must make sure that if Node is skipped,
     * or removed fFistChunk must be set to true, otherwise some data can be lost.
     *
     */
protected void setCharacterData(boolean sawChars) {
    // handle character data
    fFirstChunk = sawChars;
    // if we have data in the buffer we must have created
    // a text node already.
    Node child = fCurrentNode.getLastChild();
    if (child != null) {
        if (fStringBuffer.length() > 0) {
            // REVISIT: should this check be performed?
            if (child.getNodeType() == Node.TEXT_NODE) {
                if (fDocumentImpl != null) {
                    ((TextImpl) child).replaceData(fStringBuffer.toString());
                } else {
                    ((Text) child).setData(fStringBuffer.toString());
                }
            }
            // reset string buffer
            fStringBuffer.setLength(0);
        }
        if (fDOMFilter != null && !fInEntityRef) {
            if ((child.getNodeType() == Node.TEXT_NODE) && ((fDOMFilter.getWhatToShow() & NodeFilter.SHOW_TEXT) != 0)) {
                short code = fDOMFilter.acceptNode(child);
                switch(code) {
                    case LSParserFilter.FILTER_INTERRUPT:
                        {
                            throw abort;
                        }
                    case LSParserFilter.FILTER_REJECT:
                        {
                        // fall through to SKIP since Comment has no children.
                        }
                    case LSParserFilter.FILTER_SKIP:
                        {
                            fCurrentNode.removeChild(child);
                            return;
                        }
                    default:
                        {
                        // accept node -- do nothing
                        }
                }
            }
        }
    // end-if fDOMFilter !=null
    }
// end-if child !=null
}
}
