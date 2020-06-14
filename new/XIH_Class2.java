public class Class2 {
	private Object prefix;
	private Object fENTITYSymbol;
	private Object fENTITIESSymbol;
	private Object EMPTY_STRING;
	private Object XML_URI;
	private Object fCDATASymbol;
	private Object localpart;
	private Object fNOTATIONSymbol;
	private Object uri;
	private Object PREFIX_XMLNS;
	private Object XMLNS_URI;
	private Object rawname;

	public Class2(Object prefix, Object fENTITYSymbol, Object fENTITIESSymbol, Object EMPTY_STRING, Object XML_URI, Object fCDATASymbol, Object localpart, Object fNOTATIONSymbol, Object uri, Object PREFIX_XMLNS, Object XMLNS_URI, Object rawname){
		this.prefix = prefix;
		this.fENTITYSymbol = fENTITYSymbol;
		this.fENTITIESSymbol = fENTITIESSymbol;
		this.EMPTY_STRING = EMPTY_STRING;
		this.XML_URI = XML_URI;
		this.fCDATASymbol = fCDATASymbol;
		this.localpart = localpart;
		this.fNOTATIONSymbol = fNOTATIONSymbol;
		this.uri = uri;
		this.PREFIX_XMLNS = PREFIX_XMLNS;
		this.XMLNS_URI = XMLNS_URI;
		this.rawname = rawname;
	}
	/**
     * Returns true if the element has the namespace "http://www.w3.org/2001/XInclude"
     * @param element the element to check
     * @return true if the element has the namespace "http://www.w3.org/2001/XInclude"
     */
protected boolean hasXIncludeNamespace(QName element) {
    // context? -- mrglavas
    return element.uri == XINCLUDE_NS_URI || fNamespaceContext.getURI(element.prefix) == XINCLUDE_NS_URI;
}
	/**
     * Checks if the element is an &lt;include&gt; element.  The element must have
     * the XInclude namespace, and a local name of "include".
     *
     * @param element the element to check
     * @return true if the element is an &lt;include&gt; element
     * @see #hasXIncludeNamespace(QName)
     */
protected boolean isIncludeElement(QName element) {
    return element.localpart.equals(XINCLUDE_INCLUDE) && hasXIncludeNamespace(element);
}
	/**
     * Checks if the element is an &lt;fallback&gt; element.  The element must have
     * the XInclude namespace, and a local name of "fallback".
     *
     * @param element the element to check
     * @return true if the element is an &lt;fallback; element
     * @see #hasXIncludeNamespace(QName)
     */
protected boolean isFallbackElement(QName element) {
    return element.localpart.equals(XINCLUDE_FALLBACK) && hasXIncludeNamespace(element);
}
	/**
     * Processes the XMLAttributes object of startElement() calls.  Performs the following tasks:
     * <ul>
     * <li> If the element is a top level included item whose [base URI] is different from the
     * [base URI] of the include parent, then an xml:base attribute is added to specify the
     * true [base URI]
     * <li> For all namespace prefixes which are in-scope in an included item, but not in scope
     * in the include parent, a xmlns:prefix attribute is added
     * <li> For all attributes with a type of ENTITY, ENTITIES or NOTATIONS, the notations and
     * unparsed entities are processed as described in the spec, sections 4.5.1 and 4.5.2
     * </ul>
     * @param attributes
     * @return
     */
protected XMLAttributes processAttributes(XMLAttributes attributes) {
    if (isTopLevelIncludedItem()) {
        // base URI than their include parent.
        if (fFixupBaseURIs && !sameBaseURIAsIncludeParent()) {
            if (attributes == null) {
                attributes = new XMLAttributesImpl();
            }
            // This causes errors with schema validation, if the schema doesn't
            // specify that these elements can have an xml:base attribute
            String uri = null;
            try {
                uri = this.getRelativeBaseURI();
            } catch (MalformedURIException e) {
                // this shouldn't ever happen, since by definition, we had to traverse
                // the same URIs to even get to this place
                uri = fCurrentBaseURI.getExpandedSystemId();
            }
            int index = attributes.addAttribute(XML_BASE_QNAME, XMLSymbols.fCDATASymbol, uri);
            attributes.setSpecified(index, true);
        }
        // [language] than their include parent.
        if (fFixupLanguage && !sameLanguageAsIncludeParent()) {
            if (attributes == null) {
                attributes = new XMLAttributesImpl();
            }
            int index = attributes.addAttribute(XML_LANG_QNAME, XMLSymbols.fCDATASymbol, fCurrentLanguage);
            attributes.setSpecified(index, true);
        }
        // Modify attributes of included items to do namespace-fixup. (spec 4.5.4)
        Enumeration inscopeNS = fNamespaceContext.getAllPrefixes();
        while (inscopeNS.hasMoreElements()) {
            String prefix = (String) inscopeNS.nextElement();
            String parentURI = fNamespaceContext.getURIFromIncludeParent(prefix);
            String uri = fNamespaceContext.getURI(prefix);
            if (parentURI != uri && attributes != null) {
                if (prefix == XMLSymbols.EMPTY_STRING) {
                    if (attributes.getValue(NamespaceContext.XMLNS_URI, XMLSymbols.PREFIX_XMLNS) == null) {
                        if (attributes == null) {
                            attributes = new XMLAttributesImpl();
                        }
                        QName ns = (QName) NEW_NS_ATTR_QNAME.clone();
                        ns.prefix = null;
                        ns.localpart = XMLSymbols.PREFIX_XMLNS;
                        ns.rawname = XMLSymbols.PREFIX_XMLNS;
                        int index = attributes.addAttribute(ns, XMLSymbols.fCDATASymbol, uri != null ? uri : XMLSymbols.EMPTY_STRING);
                        attributes.setSpecified(index, true);
                        // Need to re-declare this prefix in the current context
                        // in order for the SAX parser to report the appropriate
                        // start and end prefix mapping events. -- mrglavas
                        fNamespaceContext.declarePrefix(prefix, uri);
                    }
                } else if (attributes.getValue(NamespaceContext.XMLNS_URI, prefix) == null) {
                    if (attributes == null) {
                        attributes = new XMLAttributesImpl();
                    }
                    QName ns = (QName) NEW_NS_ATTR_QNAME.clone();
                    ns.localpart = prefix;
                    ns.rawname += prefix;
                    ns.rawname = (fSymbolTable != null) ? fSymbolTable.addSymbol(ns.rawname) : ns.rawname.intern();
                    int index = attributes.addAttribute(ns, XMLSymbols.fCDATASymbol, uri != null ? uri : XMLSymbols.EMPTY_STRING);
                    attributes.setSpecified(index, true);
                    // Need to re-declare this prefix in the current context
                    // in order for the SAX parser to report the appropriate 
                    // start and end prefix mapping events. -- mrglavas
                    fNamespaceContext.declarePrefix(prefix, uri);
                }
            }
        }
    }
    if (attributes != null) {
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            String type = attributes.getType(i);
            String value = attributes.getValue(i);
            if (type == XMLSymbols.fENTITYSymbol) {
                this.checkUnparsedEntity(value);
            }
            if (type == XMLSymbols.fENTITIESSymbol) {
                // 4.5.1 - Unparsed Entities
                StringTokenizer st = new StringTokenizer(value);
                while (st.hasMoreTokens()) {
                    String entName = st.nextToken();
                    this.checkUnparsedEntity(entName);
                }
            } else if (type == XMLSymbols.fNOTATIONSymbol) {
                // 4.5.2 - Notations
                this.checkNotation(value);
            }
        /* We actually don't need to do anything for 4.5.3, because at this stage the
                 * value of the attribute is just a string. It will be taken care of later
                 * in the pipeline, when the IDREFs are actually resolved against IDs.
                 *
                 * if (type == XMLSymbols.fIDREFSymbol || type == XMLSymbols.fIDREFSSymbol) { }
                 */
        }
    }
    return attributes;
}
	// The following methods are used for language processing
/**
     * Saves the given language on the top of the stack.
     * 
     * @param lanaguage the language to push onto the stack.
     */
protected void saveLanguage(String language) {
    fLanguageScope.push(fDepth);
    fLanguageStack.push(language);
}
	/**
     * Search for a xml:lang attribute, and if one is found, put the new 
     * [language] into effect.
     */
protected void processXMLLangAttributes(XMLAttributes attributes) {
    String language = attributes.getValue(NamespaceContext.XML_URI, "lang");
    if (language != null) {
        fCurrentLanguage = language;
        saveLanguage(fCurrentLanguage);
    }
}
}