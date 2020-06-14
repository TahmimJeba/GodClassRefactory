public class Class2 {
	private Object length;
	private Object doCData;
	private Object SERIALIZER_DOMAIN;
	private Object rawName;
	private Object empty;
	private Object localName;
	private Object preserveSpace;
	private Object unescaped;
	private Object prefixes;
	private Object afterElement;
	private Object afterComment;
	private Object namespaceURI;
	private Object inCData;

	public Class2(Object length, Object doCData, Object SERIALIZER_DOMAIN, Object rawName, Object empty, Object localName, Object preserveSpace, Object unescaped, Object prefixes, Object afterElement, Object afterComment, Object namespaceURI, Object inCData){
		this.length = length;
		this.doCData = doCData;
		this.SERIALIZER_DOMAIN = SERIALIZER_DOMAIN;
		this.rawName = rawName;
		this.empty = empty;
		this.localName = localName;
		this.preserveSpace = preserveSpace;
		this.unescaped = unescaped;
		this.prefixes = prefixes;
		this.afterElement = afterElement;
		this.afterComment = afterComment;
		this.namespaceURI = namespaceURI;
		this.inCData = inCData;
	}
	/**
     * Leave the current element state and return to the
     * state of the parent element. If this was the root
     * element, return to the state of the document.
     *                                                                             
     * @return Previous element state
     */
protected ElementState leaveElementState() {
    if (_elementStateCount > 0) {
        /*Corrected by David Blondeau (blondeau@intalio.com)*/
        _prefixes = null;
        //_prefixes = _elementStates[ _elementStateCount ].prefixes;
        --_elementStateCount;
        return _elementStates[_elementStateCount];
    } else {
        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "Internal", null);
        throw new IllegalStateException(msg);
    }
}
	/**
     * Returns the namespace prefix for the specified URI.
     * If the URI has been mapped to a prefix, returns the
     * prefix, otherwise returns null.
     *
     * @param namespaceURI The namespace URI
     * @return The namespace prefix if known, or null
     */
protected String getPrefix(String namespaceURI) {
    String prefix;
    if (_prefixes != null) {
        prefix = (String) _prefixes.get(namespaceURI);
        if (prefix != null)
            return prefix;
    }
    if (_elementStateCount == 0)
        return null;
    else {
        for (int i = _elementStateCount; i > 0; --i) {
            if (_elementStates[i].prefixes != null) {
                prefix = (String) _elementStates[i].prefixes.get(namespaceURI);
                if (prefix != null)
                    return prefix;
            }
        }
    }
    return null;
}
	/**
     * Enter a new element state for the specified element.
     * Tag name and space preserving is specified, element
     * state is initially empty.
     *
     * @return Current element state, or null
     */
protected ElementState enterElementState(String namespaceURI, String localName, String rawName, boolean preserveSpace) {
    ElementState state;
    if (_elementStateCount + 1 == _elementStates.length) {
        ElementState[] newStates;
        // Need to create a larger array of states. This does not happen
        // often, unless the document is really deep.
        newStates = new ElementState[_elementStates.length + 10];
        for (int i = 0; i < _elementStates.length; ++i) newStates[i] = _elementStates[i];
        for (int i = _elementStates.length; i < newStates.length; ++i) newStates[i] = new ElementState();
        _elementStates = newStates;
    }
    ++_elementStateCount;
    state = _elementStates[_elementStateCount];
    state.namespaceURI = namespaceURI;
    state.localName = localName;
    state.rawName = rawName;
    state.preserveSpace = preserveSpace;
    state.empty = true;
    state.afterElement = false;
    state.afterComment = false;
    state.doCData = state.inCData = false;
    state.unescaped = false;
    state.prefixes = _prefixes;
    _prefixes = null;
    return state;
}
}
