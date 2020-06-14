public class Class1 {
	private Object length;
	private Object SHOW_COMMENT;
	private Object FILTER_SKIP;
	private Object out;
	private Object SHOW_PROCESSING_INSTRUCTION;
	private Object TEXT_NODE;
	private Object FILTER_INTERRUPT;
	private Object FILTER_REJECT;

	public Class1(Object length, Object SHOW_COMMENT, Object FILTER_SKIP, Object out, Object SHOW_PROCESSING_INSTRUCTION, Object TEXT_NODE, Object FILTER_INTERRUPT, Object FILTER_REJECT){
		this.length = length;
		this.SHOW_COMMENT = SHOW_COMMENT;
		this.FILTER_SKIP = FILTER_SKIP;
		this.out = out;
		this.SHOW_PROCESSING_INSTRUCTION = SHOW_PROCESSING_INSTRUCTION;
		this.TEXT_NODE = TEXT_NODE;
		this.FILTER_INTERRUPT = FILTER_INTERRUPT;
		this.FILTER_REJECT = FILTER_REJECT;
	}
	// startConditional(short)
/**
     * The end of a conditional section.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endConditional(Augmentations augs) throws XNIException {
}
	/**
     * This method notifies the end of a parameter entity. Parameter entity
     * names begin with a '%' character.
     *
     * @param name The name of the parameter entity.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endParameterEntity(String name, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>endParameterEntity: " + name);
    }
    fBaseURIStack.pop();
}
	// startAttlist(String)
/**
     * The end of an attribute list.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endAttlist(Augmentations augs) throws XNIException {
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
	// comment(XMLString)
/**
     * A processing instruction. Processing instructions consist of a
     * target name and, optionally, text data. The data is only meaningful
     * to the application.
     * <p>
     * Typically, a processing instruction's data will contain a series
     * of pseudo-attributes. These pseudo-attributes follow the form of
     * element attributes but are <strong>not</strong> parsed or presented
     * to the application as anything other than text. The application is
     * responsible for parsing the data.
     *
     * @param target The target.
     * @param data   The data or null if none specified.
     * @param augs       Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
    if (fInDTD) {
        if (fInternalSubset != null && !fInDTDExternalSubset) {
            fInternalSubset.append("<?");
            fInternalSubset.append(target);
            fInternalSubset.append(' ');
            fInternalSubset.append(data.toString());
            fInternalSubset.append("?>");
        }
        return;
    }
    if (DEBUG_EVENTS) {
        System.out.println("==>processingInstruction (" + target + ")");
    }
    if (!fDeferNodeExpansion) {
        if (fFilterReject) {
            return;
        }
        ProcessingInstruction pi = fDocument.createProcessingInstruction(target, data.toString());
        setCharacterData(false);
        fCurrentNode.appendChild(pi);
        if (fDOMFilter != null && !fInEntityRef && (fDOMFilter.getWhatToShow() & NodeFilter.SHOW_PROCESSING_INSTRUCTION) != 0) {
            short code = fDOMFilter.acceptNode(pi);
            switch(code) {
                case LSParserFilter.FILTER_INTERRUPT:
                    {
                        throw abort;
                    }
                case LSParserFilter.FILTER_REJECT:
                    {
                    // fall through to SKIP since PI has no children.
                    }
                case LSParserFilter.FILTER_SKIP:
                    {
                        fCurrentNode.removeChild(pi);
                        // fFirstChunk must be set to true so that data
                        // won't be lost in the case where the child before PI is
                        // a text node and the next event is characters.
                        fFirstChunk = true;
                        return;
                    }
                default:
                    {
                    }
            }
        }
    } else {
        int pi = fDeferredDocumentImpl.createDeferredProcessingInstruction(target, data.toString());
        fDeferredDocumentImpl.appendChild(fCurrentNodeIndex, pi);
    }
}
	// emptyElement(QName,XMLAttributes)
/**
     * Character content.
     *
     * @param text The content.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void characters(XMLString text, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>characters(): " + text.toString());
    }
    if (!fDeferNodeExpansion) {
        if (fFilterReject) {
            return;
        }
        if (fInCDATASection && fCreateCDATANodes) {
            if (fCurrentCDATASection == null) {
                fCurrentCDATASection = fDocument.createCDATASection(text.toString());
                fCurrentNode.appendChild(fCurrentCDATASection);
                fCurrentNode = fCurrentCDATASection;
            } else {
                fCurrentCDATASection.appendData(text.toString());
            }
        } else if (!fInDTD) {
            // character call with empty data
            if (text.length == 0) {
                return;
            }
            String value = text.toString();
            Node child = fCurrentNode.getLastChild();
            if (child != null && child.getNodeType() == Node.TEXT_NODE) {
                // collect all the data into the string buffer.
                if (fFirstChunk) {
                    if (fDocumentImpl != null) {
                        fStringBuffer.append(((TextImpl) child).removeData());
                    } else {
                        fStringBuffer.append(((Text) child).getData());
                        ((Text) child).setNodeValue(null);
                    }
                    fFirstChunk = false;
                }
                fStringBuffer.append(value);
            } else {
                fFirstChunk = true;
                Text textNode = fDocument.createTextNode(value);
                fCurrentNode.appendChild(textNode);
            }
        }
    } else {
        // the DOM in the deferred case.
        if (fInCDATASection && fCreateCDATANodes) {
            if (fCurrentCDATASectionIndex == -1) {
                int cs = fDeferredDocumentImpl.createDeferredCDATASection(text.toString());
                fDeferredDocumentImpl.appendChild(fCurrentNodeIndex, cs);
                fCurrentCDATASectionIndex = cs;
                fCurrentNodeIndex = cs;
            } else {
                int txt = fDeferredDocumentImpl.createDeferredTextNode(text.toString(), false);
                fDeferredDocumentImpl.appendChild(fCurrentNodeIndex, txt);
            }
        } else if (!fInDTD) {
            // character call with empty data
            if (text.length == 0) {
                return;
            }
            String value = text.toString();
            int txt = fDeferredDocumentImpl.createDeferredTextNode(value, false);
            fDeferredDocumentImpl.appendChild(fCurrentNodeIndex, txt);
        }
    }
}
	// characters(XMLString)
/**
     * Ignorable whitespace. For this method to be called, the document
     * source must have some way of determining that the text containing
     * only whitespace characters should be considered ignorable. For
     * example, the validator can determine if a length of whitespace
     * characters in the document are ignorable based on the element
     * content model.
     *
     * @param text The ignorable whitespace.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
    if (!fIncludeIgnorableWhitespace || fFilterReject) {
        return;
    }
    if (!fDeferNodeExpansion) {
        Node child = fCurrentNode.getLastChild();
        if (child != null && child.getNodeType() == Node.TEXT_NODE) {
            Text textNode = (Text) child;
            textNode.appendData(text.toString());
        } else {
            Text textNode = fDocument.createTextNode(text.toString());
            if (fDocumentImpl != null) {
                TextImpl textNodeImpl = (TextImpl) textNode;
                textNodeImpl.setIgnorableWhitespace(true);
            }
            fCurrentNode.appendChild(textNode);
        }
    } else {
        // The Text normalization is taken care of within the DOM in the
        // deferred case.
        int txt = fDeferredDocumentImpl.createDeferredTextNode(text.toString(), true);
        fDeferredDocumentImpl.appendChild(fCurrentNodeIndex, txt);
    }
}
	// endElement(QName)
/**
     * The start of a CDATA section.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void startCDATA(Augmentations augs) throws XNIException {
    fInCDATASection = true;
    if (!fDeferNodeExpansion) {
        if (fFilterReject) {
            return;
        }
        if (fCreateCDATANodes) {
            setCharacterData(false);
        }
    }
}
	// endCDATA()
/**
     * The end of the document.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endDocument(Augmentations augs) throws XNIException {
    if (!fDeferNodeExpansion) {
        // set DOM error checking back on
        if (fDocumentImpl != null) {
            fDocumentImpl.setStrictErrorChecking(true);
        }
        fCurrentNode = null;
    } else {
        fCurrentNodeIndex = -1;
    }
}
	//
// XMLDTDHandler methods
//
/**
     * The start of the DTD.
     *
     * @param locator  The document locator, or null if the document
     *                 location cannot be reported during the parsing of
     *                 the document DTD. However, it is <em>strongly</em>
     *                 recommended that a locator be supplied that can
     *                 at least report the base system identifier of the
     *                 DTD.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void startDTD(XMLLocator locator, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>startDTD");
        if (DEBUG_BASEURI) {
            System.out.println("   expandedSystemId: " + locator.getExpandedSystemId());
            System.out.println("   baseURI:" + locator.getBaseSystemId());
        }
    }
    fInDTD = true;
    if (locator != null) {
        fBaseURIStack.push(locator.getBaseSystemId());
    }
    if (fDeferNodeExpansion || fDocumentImpl != null) {
        fInternalSubset = new StringBuffer(1024);
    }
}
	// startDTD(XMLLocator)
/**
     * The end of the DTD.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void endDTD(Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>endDTD()");
    }
    fInDTD = false;
    if (!fBaseURIStack.isEmpty()) {
        fBaseURIStack.pop();
    }
    String internalSubset = fInternalSubset != null && fInternalSubset.length() > 0 ? fInternalSubset.toString() : null;
    if (fDeferNodeExpansion) {
        if (internalSubset != null) {
            fDeferredDocumentImpl.setInternalSubset(fDocumentTypeIndex, internalSubset);
        }
    } else if (fDocumentImpl != null) {
        if (internalSubset != null) {
            ((DocumentTypeImpl) fDocumentType).setInternalSubset(internalSubset);
        }
    }
}
	// notationDecl(String,XMLResourceIdentifier, Augmentations)
/**
     * Characters within an IGNORE conditional section.
     *
     * @param text The ignored text.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void ignoredCharacters(XMLString text, Augmentations augs) throws XNIException {
}
	// ignoredCharacters(XMLString, Augmentations)
/**
     * An element declaration.
     *
     * @param name         The name of the element.
     * @param contentModel The element content model.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void elementDecl(String name, String contentModel, Augmentations augs) throws XNIException {
    // internal subset string
    if (fInternalSubset != null && !fInDTDExternalSubset) {
        fInternalSubset.append("<!ELEMENT ");
        fInternalSubset.append(name);
        fInternalSubset.append(' ');
        fInternalSubset.append(contentModel);
        fInternalSubset.append(">\n");
    }
}
	// attributeDecl(String,String,String,String[],String,XMLString, XMLString, Augmentations)
/**
     * The start of an attribute list.
     *
     * @param elementName The name of the element that this attribute
     *                    list is associated with.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void startAttlist(String elementName, Augmentations augs) throws XNIException {
}
}