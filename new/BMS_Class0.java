public class Class0 {
	private Object fType;
	private Object afterElement;
	private Object fSeverity;
	private Object afterComment;
	private Object fMessage;
	private Object inCData;
	private Object doCData;
	private Object fLocator;
	private Object empty;

	public Class0(Object fType, Object afterElement, Object fSeverity, Object afterComment, Object fMessage, Object inCData, Object doCData, Object fLocator, Object empty){
		this.fType = fType;
		this.afterElement = afterElement;
		this.fSeverity = fSeverity;
		this.afterComment = afterComment;
		this.fMessage = fMessage;
		this.inCData = inCData;
		this.doCData = doCData;
		this.fLocator = fLocator;
		this.empty = empty;
	}
	/**
     * Called at the end of the document to wrap it up.
     * Will flush the output stream and throw an exception
     * if any I/O error occured while serializing.
     *
     * @throws SAXException An I/O exception occured during
     *  serializing
     */
public void endDocument() throws SAXException {
    try {
        // Print all the elements accumulated outside of
        // the root element.
        serializePreRoot();
        // Flush the output, this is necessary for fStrBuffered output.
        _printer.flush();
    } catch (IOException except) {
        throw new SAXException(except);
    }
}
	//----------------------------------//
// DOM document serializing methods //
//----------------------------------//
/**
     * Serializes the DOM element using the previously specified
     * writer and output format. Throws an exception only if
     * an I/O exception occured while serializing.
     *
     * @param elem The element to serialize
     * @throws IOException An I/O exception occured while
     *   serializing
     */
public void serialize(Element elem) throws IOException {
    reset();
    prepare();
    serializeNode(elem);
    _printer.flush();
    if (_printer.getException() != null)
        throw _printer.getException();
}
	//----------------------------------//
// DOM document serializing methods //
//----------------------------------//
/**
     * Serializes the DOM element using the previously specified
     * writer and output format. Throws an exception only if
     * an I/O exception occured while serializing.
     *
     * @param elem The element to serialize
     * @throws IOException An I/O exception occured while
     *   serializing
     */
public void serialize(Element elem) throws IOException {
    reset();
    prepare();
    serializeNode(elem);
    _printer.flush();
    if (_printer.getException() != null)
        throw _printer.getException();
}
	//----------------------------------//
// DOM document serializing methods //
//----------------------------------//
/**
     * Serializes the DOM element using the previously specified
     * writer and output format. Throws an exception only if
     * an I/O exception occured while serializing.
     *
     * @param elem The element to serialize
     * @throws IOException An I/O exception occured while
     *   serializing
     */
public void serialize(Element elem) throws IOException {
    reset();
    prepare();
    serializeNode(elem);
    _printer.flush();
    if (_printer.getException() != null)
        throw _printer.getException();
}
	/**
     * Called to serializee the DOM element. The element is serialized based on
     * the serializer's method (XML, HTML, XHTML).
     *
     * @param elem The element to serialize
     * @throws IOException An I/O exception occured while
     *   serializing
     */
protected abstract void serializeElement(Element elem) throws IOException;
	/**
     * Serializes the DOM document fragmnt using the previously specified
     * writer and output format. Throws an exception only if
     * an I/O exception occured while serializing.
     *
     * @param elem The element to serialize
     * @throws IOException An I/O exception occured while
     *   serializing
     */
public void serialize(DocumentFragment frag) throws IOException {
    reset();
    prepare();
    serializeNode(frag);
    _printer.flush();
    if (_printer.getException() != null)
        throw _printer.getException();
}
	/**
     * Serializes the DOM document fragmnt using the previously specified
     * writer and output format. Throws an exception only if
     * an I/O exception occured while serializing.
     *
     * @param elem The element to serialize
     * @throws IOException An I/O exception occured while
     *   serializing
     */
public void serialize(DocumentFragment frag) throws IOException {
    reset();
    prepare();
    serializeNode(frag);
    _printer.flush();
    if (_printer.getException() != null)
        throw _printer.getException();
}
	/**
     * Serializes the DOM document fragmnt using the previously specified
     * writer and output format. Throws an exception only if
     * an I/O exception occured while serializing.
     *
     * @param elem The element to serialize
     * @throws IOException An I/O exception occured while
     *   serializing
     */
public void serialize(DocumentFragment frag) throws IOException {
    reset();
    prepare();
    serializeNode(frag);
    _printer.flush();
    if (_printer.getException() != null)
        throw _printer.getException();
}
	/**
     * Serializes the DOM document using the previously specified
     * writer and output format. Throws an exception only if
     * an I/O exception occured while serializing.
     *
     * @param doc The document to serialize
     * @throws IOException An I/O exception occured while
     *   serializing
     */
public void serialize(Document doc) throws IOException {
    reset();
    prepare();
    serializeNode(doc);
    serializePreRoot();
    _printer.flush();
    if (_printer.getException() != null)
        throw _printer.getException();
}
	/**
     * Serializes the DOM document using the previously specified
     * writer and output format. Throws an exception only if
     * an I/O exception occured while serializing.
     *
     * @param doc The document to serialize
     * @throws IOException An I/O exception occured while
     *   serializing
     */
public void serialize(Document doc) throws IOException {
    reset();
    prepare();
    serializeNode(doc);
    serializePreRoot();
    _printer.flush();
    if (_printer.getException() != null)
        throw _printer.getException();
}
	/**
     * Serializes the DOM document using the previously specified
     * writer and output format. Throws an exception only if
     * an I/O exception occured while serializing.
     *
     * @param doc The document to serialize
     * @throws IOException An I/O exception occured while
     *   serializing
     */
public void serialize(Document doc) throws IOException {
    reset();
    prepare();
    serializeNode(doc);
    serializePreRoot();
    _printer.flush();
    if (_printer.getException() != null)
        throw _printer.getException();
}
	/**
     * Comments and PIs cannot be serialized before the root element,
     * because the root element serializes the document type, which
     * generally comes first. Instead such PIs and comments are
     * accumulated inside a vector and serialized by calling this
     * method. Will be called when the root element is serialized
     * and when the document finished serializing.
     *
     * @throws IOException An I/O exception occured while
     *   serializing
     */
protected void serializePreRoot() throws IOException {
    int i;
    if (_preRoot != null) {
        for (i = 0; i < _preRoot.size(); ++i) {
            printText((String) _preRoot.elementAt(i), true, true);
            if (_indenting)
                _printer.breakLine();
        }
        _preRoot.removeAllElements();
    }
}
	//--------------------------------//
// Element state handling methods //
//--------------------------------//
/**
     * Return the state of the current element.
     *
     * @return Current element state
     */
protected ElementState getElementState() {
    return _elementStates[_elementStateCount];
}
	/**
     * Returns true if in the state of the document.
     * Returns true before entering any element and after
     * leaving the root element.
     *
     * @return True if in the state of the document
     */
protected boolean isDocumentState() {
    return _elementStateCount == 0;
}
	/**
     * Must be called by a method about to print any type of content.
     * If the element was just opened, the opening tag is closed and
     * will be matched to a closing tag. Returns the current element
     * state with <tt>empty</tt> and <tt>afterElement</tt> set to false.
     *
     * @return The current element state
     * @throws IOException An I/O exception occured while
     *   serializing
     */
protected ElementState content() throws IOException {
    ElementState state;
    state = getElementState();
    if (!isDocumentState()) {
        // Need to close CData section first
        if (state.inCData && !state.doCData) {
            _printer.printText("]]>");
            state.inCData = false;
        }
        // opening element tag.
        if (state.empty) {
            _printer.printText('>');
            state.empty = false;
        }
        // Except for one content type, all of them
        // are not last element. That one content
        // type will take care of itself.
        state.afterElement = false;
        // Except for one content type, all of them
        // are not last comment. That one content
        // type will take care of itself.
        state.afterComment = false;
    }
    return state;
}
	/**
     * Print a document type public or system identifier URL.
     * Encapsulates the URL in double quotes, escapes non-printing
     * characters and print it equivalent to {@link #printText}.
     *
     * @param url The document type url to print
     */
protected void printDoctypeURL(String url) throws IOException {
    int i;
    _printer.printText('"');
    for (i = 0; i < url.length(); ++i) {
        if (url.charAt(i) == '"' || url.charAt(i) < 0x20 || url.charAt(i) > 0x7F) {
            _printer.printText('%');
            _printer.printText(Integer.toHexString(url.charAt(i)));
        } else
            _printer.printText(url.charAt(i));
    }
    _printer.printText('"');
}
	/**
	 * DOM level 3: 
	 * Check a node to determine if it contains unbound namespace prefixes.
	 *
	 * @param node The node to check for unbound namespace prefices
	 */
protected void checkUnboundNamespacePrefixedNode(Node node) throws IOException {
}
	/**
     * The method modifies global DOM error object
     * 
     * @param message
     * @param severity
     * @param type
     * @return a DOMError
     */
protected DOMError modifyDOMError(String message, short severity, String type, Node node) {
    fDOMError.reset();
    fDOMError.fMessage = message;
    fDOMError.fType = type;
    fDOMError.fSeverity = severity;
    fDOMError.fLocator = new DOMLocatorImpl(-1, -1, -1, node, null);
    return fDOMError;
}
}