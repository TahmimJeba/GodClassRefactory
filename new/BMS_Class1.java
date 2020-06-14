public class Class1 {
	private Object ENTITY_REFERENCE_NODE;
	private Object SHOW_TEXT;
	private Object COMMENT_NODE;
	private Object doCData;
	private Object SHOW_ENTITY_REFERENCE;
	private Object SHOW_ELEMENT;
	private Object TEXT_NODE;
	private Object ENTITIES;
	private Object FILTER_REJECT;
	private Object CDATA_SECTION_NODE;
	private Object PROCESSING_INSTRUCTION_NODE;
	private Object DOCUMENT_NODE;
	private Object SHOW_COMMENT;
	private Object FILTER_SKIP;
	private Object SHOW_PROCESSING_INSTRUCTION;
	private Object ELEMENT_NODE;
	private Object preserveSpace;
	private Object unescaped;
	private Object DOCUMENT_FRAGMENT_NODE;
	private Object SHOW_CDATA_SECTION;
	private Object inCData;
	private Object CDATA;

	public Class1(Object ENTITY_REFERENCE_NODE, Object SHOW_TEXT, Object COMMENT_NODE, Object doCData, Object SHOW_ENTITY_REFERENCE, Object SHOW_ELEMENT, Object TEXT_NODE, Object ENTITIES, Object FILTER_REJECT, Object CDATA_SECTION_NODE, Object PROCESSING_INSTRUCTION_NODE, Object DOCUMENT_NODE, Object SHOW_COMMENT, Object FILTER_SKIP, Object SHOW_PROCESSING_INSTRUCTION, Object ELEMENT_NODE, Object preserveSpace, Object unescaped, Object DOCUMENT_FRAGMENT_NODE, Object SHOW_CDATA_SECTION, Object inCData, Object CDATA){
		this.ENTITY_REFERENCE_NODE = ENTITY_REFERENCE_NODE;
		this.SHOW_TEXT = SHOW_TEXT;
		this.COMMENT_NODE = COMMENT_NODE;
		this.doCData = doCData;
		this.SHOW_ENTITY_REFERENCE = SHOW_ENTITY_REFERENCE;
		this.SHOW_ELEMENT = SHOW_ELEMENT;
		this.TEXT_NODE = TEXT_NODE;
		this.ENTITIES = ENTITIES;
		this.FILTER_REJECT = FILTER_REJECT;
		this.CDATA_SECTION_NODE = CDATA_SECTION_NODE;
		this.PROCESSING_INSTRUCTION_NODE = PROCESSING_INSTRUCTION_NODE;
		this.DOCUMENT_NODE = DOCUMENT_NODE;
		this.SHOW_COMMENT = SHOW_COMMENT;
		this.FILTER_SKIP = FILTER_SKIP;
		this.SHOW_PROCESSING_INSTRUCTION = SHOW_PROCESSING_INSTRUCTION;
		this.ELEMENT_NODE = ELEMENT_NODE;
		this.preserveSpace = preserveSpace;
		this.unescaped = unescaped;
		this.DOCUMENT_FRAGMENT_NODE = DOCUMENT_FRAGMENT_NODE;
		this.SHOW_CDATA_SECTION = SHOW_CDATA_SECTION;
		this.inCData = inCData;
		this.CDATA = CDATA;
	}
	public void characters(char[] chars, int start, int length) throws SAXException {
    ElementState state;
    try {
        state = content();
        if (state.inCData || state.doCData) {
            int saveIndent;
            // The contents of a text node is considered space preserving.
            if (!state.inCData) {
                _printer.printText("<![CDATA[");
                state.inCData = true;
            }
            saveIndent = _printer.getNextIndent();
            _printer.setNextIndent(0);
            char ch;
            final int end = start + length;
            for (int index = start; index < end; ++index) {
                ch = chars[index];
                if (ch == ']' && index + 2 < end && chars[index + 1] == ']' && chars[index + 2] == '>') {
                    _printer.printText("]]]]><![CDATA[>");
                    index += 2;
                    continue;
                }
                if (!XMLChar.isValid(ch)) {
                    // check if it is surrogate
                    if (++index < end) {
                        surrogates(ch, chars[index]);
                    } else {
                        fatalError("The character '" + (char) ch + "' is an invalid XML character");
                    }
                    continue;
                } else {
                    if ((ch >= ' ' && _encodingInfo.isPrintable((char) ch) && ch != 0xF7) || ch == '\n' || ch == '\r' || ch == '\t') {
                        _printer.printText((char) ch);
                    } else {
                        // The character is not printable -- split CDATA section
                        _printer.printText("]]>&#x");
                        _printer.printText(Integer.toHexString(ch));
                        _printer.printText(";<![CDATA[");
                    }
                }
            }
            _printer.setNextIndent(saveIndent);
        } else {
            int saveIndent;
            if (state.preserveSpace) {
                // If preserving space then hold of indentation so no
                // excessive spaces are printed at line breaks, escape
                // the text content without replacing spaces and print
                // the text breaking only at line breaks.
                saveIndent = _printer.getNextIndent();
                _printer.setNextIndent(0);
                printText(chars, start, length, true, state.unescaped);
                _printer.setNextIndent(saveIndent);
            } else {
                printText(chars, start, length, false, state.unescaped);
            }
        }
    } catch (IOException except) {
        throw new SAXException(except);
    }
}
	/**
     * Called to print the text contents in the prevailing element format.
     * Since this method is capable of printing text as CDATA, it is used
     * for that purpose as well. White space handling is determined by the
     * current element state. In addition, the output format can dictate
     * whether the text is printed as CDATA or unescaped.
     *
     * @param text The text to print
     * @param unescaped True is should print unescaped
     * @throws IOException An I/O exception occured while
     *   serializing
     */
protected void characters(String text) throws IOException {
    ElementState state;
    state = content();
    if (state.inCData || state.doCData) {
        int index;
        int saveIndent;
        // The contents of a text node is considered space preserving.
        if (!state.inCData) {
            _printer.printText("<![CDATA[");
            state.inCData = true;
        }
        saveIndent = _printer.getNextIndent();
        _printer.setNextIndent(0);
        printCDATAText(text);
        _printer.setNextIndent(saveIndent);
    } else {
        int saveIndent;
        if (state.preserveSpace) {
            // If preserving space then hold of indentation so no
            // excessive spaces are printed at line breaks, escape
            // the text content without replacing spaces and print
            // the text breaking only at line breaks.
            saveIndent = _printer.getNextIndent();
            _printer.setNextIndent(0);
            printText(text, true, state.unescaped);
            _printer.setNextIndent(saveIndent);
        } else {
            printText(text, false, state.unescaped);
        }
    }
}
	//------------------------------------------//
// Generic node serializing methods methods //
//------------------------------------------//
/**
     * Serialize the DOM node. This method is shared across XML, HTML and XHTML
     * serializers and the differences are masked out in a separate {@link
     * #serializeElement}.
     *
     * @param node The node to serialize
     * @see #serializeElement
     * @throws IOException An I/O exception occured while
     *   serializing
     */
protected void serializeNode(Node node) throws IOException {
    fCurrentNode = node;
    // handled by SAX are serialized directly.
    switch(node.getNodeType()) {
        case Node.TEXT_NODE:
            {
                String text;
                text = node.getNodeValue();
                if (text != null) {
                    if (fDOMFilter != null && (fDOMFilter.getWhatToShow() & NodeFilter.SHOW_TEXT) != 0) {
                        short code = fDOMFilter.acceptNode(node);
                        switch(code) {
                            case NodeFilter.FILTER_REJECT:
                            case NodeFilter.FILTER_SKIP:
                                {
                                    break;
                                }
                            default:
                                {
                                    characters(text);
                                }
                        }
                    } else if (!_indenting || getElementState().preserveSpace || (text.replace('\n', ' ').trim().length() != 0))
                        characters(text);
                }
                break;
            }
        case Node.CDATA_SECTION_NODE:
            {
                String text = node.getNodeValue();
                if ((features & DOMSerializerImpl.CDATA) != 0) {
                    if (text != null) {
                        if (fDOMFilter != null && (fDOMFilter.getWhatToShow() & NodeFilter.SHOW_CDATA_SECTION) != 0) {
                            short code = fDOMFilter.acceptNode(node);
                            switch(code) {
                                case NodeFilter.FILTER_REJECT:
                                case NodeFilter.FILTER_SKIP:
                                    {
                                        // skip the CDATA node
                                        return;
                                    }
                                default:
                                    {
                                    //fall through..
                                    }
                            }
                        }
                        startCDATA();
                        characters(text);
                        endCDATA();
                    }
                } else {
                    // transform into a text node
                    characters(text);
                }
                break;
            }
        case Node.COMMENT_NODE:
            {
                String text;
                if (!_format.getOmitComments()) {
                    text = node.getNodeValue();
                    if (text != null) {
                        if (fDOMFilter != null && (fDOMFilter.getWhatToShow() & NodeFilter.SHOW_COMMENT) != 0) {
                            short code = fDOMFilter.acceptNode(node);
                            switch(code) {
                                case NodeFilter.FILTER_REJECT:
                                case NodeFilter.FILTER_SKIP:
                                    {
                                        // skip the comment node
                                        return;
                                    }
                                default:
                                    {
                                    // fall through
                                    }
                            }
                        }
                        comment(text);
                    }
                }
                break;
            }
        case Node.ENTITY_REFERENCE_NODE:
            {
                Node child;
                endCDATA();
                content();
                if (((features & DOMSerializerImpl.ENTITIES) != 0) || (node.getFirstChild() == null)) {
                    if (fDOMFilter != null && (fDOMFilter.getWhatToShow() & NodeFilter.SHOW_ENTITY_REFERENCE) != 0) {
                        short code = fDOMFilter.acceptNode(node);
                        switch(code) {
                            case NodeFilter.FILTER_REJECT:
                                {
                                    // remove the node
                                    return;
                                }
                            case NodeFilter.FILTER_SKIP:
                                {
                                    child = node.getFirstChild();
                                    while (child != null) {
                                        serializeNode(child);
                                        child = child.getNextSibling();
                                    }
                                    return;
                                }
                            default:
                                {
                                // fall through
                                }
                        }
                    }
                    checkUnboundNamespacePrefixedNode(node);
                    _printer.printText("&");
                    _printer.printText(node.getNodeName());
                    _printer.printText(";");
                } else {
                    child = node.getFirstChild();
                    while (child != null) {
                        serializeNode(child);
                        child = child.getNextSibling();
                    }
                }
                break;
            }
        case Node.PROCESSING_INSTRUCTION_NODE:
            {
                if (fDOMFilter != null && (fDOMFilter.getWhatToShow() & NodeFilter.SHOW_PROCESSING_INSTRUCTION) != 0) {
                    short code = fDOMFilter.acceptNode(node);
                    switch(code) {
                        case NodeFilter.FILTER_REJECT:
                        case NodeFilter.FILTER_SKIP:
                            {
                                // skip this node                      
                                return;
                            }
                        default:
                            {
                            // fall through
                            }
                    }
                }
                processingInstructionIO(node.getNodeName(), node.getNodeValue());
                break;
            }
        case Node.ELEMENT_NODE:
            {
                if (fDOMFilter != null && (fDOMFilter.getWhatToShow() & NodeFilter.SHOW_ELEMENT) != 0) {
                    short code = fDOMFilter.acceptNode(node);
                    switch(code) {
                        case NodeFilter.FILTER_REJECT:
                            {
                                return;
                            }
                        case NodeFilter.FILTER_SKIP:
                            {
                                Node child = node.getFirstChild();
                                while (child != null) {
                                    serializeNode(child);
                                    child = child.getNextSibling();
                                }
                                // skip this node                      
                                return;
                            }
                        default:
                            {
                            // fall through
                            }
                    }
                }
                serializeElement((Element) node);
                break;
            }
        case Node.DOCUMENT_NODE:
            {
                DocumentType docType;
                DOMImplementation domImpl;
                NamedNodeMap map;
                Entity entity;
                Notation notation;
                int i;
                // If there is a document type, use the SAX events to
                // serialize it.
                docType = ((Document) node).getDoctype();
                if (docType != null) {
                    // DOM Level 2 (or higher)
                    domImpl = ((Document) node).getImplementation();
                    try {
                        String internal;
                        _printer.enterDTD();
                        _docTypePublicId = docType.getPublicId();
                        _docTypeSystemId = docType.getSystemId();
                        internal = docType.getInternalSubset();
                        if (internal != null && internal.length() > 0)
                            _printer.printText(internal);
                        endDTD();
                    }// DOM Level 1 -- does implementation have methods?
                     catch (NoSuchMethodError nsme) {
                        Class docTypeClass = docType.getClass();
                        String docTypePublicId = null;
                        String docTypeSystemId = null;
                        try {
                            java.lang.reflect.Method getPublicId = docTypeClass.getMethod("getPublicId", (Class[]) null);
                            if (getPublicId.getReturnType().equals(String.class)) {
                                docTypePublicId = (String) getPublicId.invoke(docType, (Object[]) null);
                            }
                        } catch (Exception e) {
                        // ignore
                        }
                        try {
                            java.lang.reflect.Method getSystemId = docTypeClass.getMethod("getSystemId", (Class[]) null);
                            if (getSystemId.getReturnType().equals(String.class)) {
                                docTypeSystemId = (String) getSystemId.invoke(docType, (Object[]) null);
                            }
                        } catch (Exception e) {
                        // ignore
                        }
                        _printer.enterDTD();
                        _docTypePublicId = docTypePublicId;
                        _docTypeSystemId = docTypeSystemId;
                        endDTD();
                    }
                }
            // !! Fall through
            }
        case Node.DOCUMENT_FRAGMENT_NODE:
            {
                Node child;
                // By definition this will happen if the node is a document,
                // document fragment, etc. Just serialize its contents. It will
                // work well for other nodes that we do not know how to serialize.
                child = node.getFirstChild();
                while (child != null) {
                    serializeNode(child);
                    child = child.getNextSibling();
                }
                break;
            }
        default:
            break;
    }
}
}