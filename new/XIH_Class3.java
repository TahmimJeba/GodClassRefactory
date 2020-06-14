public class Class3 {
	private Object length;

	public Class3(Object length){
		this.length = length;
	}
	/**
     * Returns the [base URI] of the include parent.
     * @return the base URI of the include parent.
     */
private String getIncludeParentBaseURI() {
    int depth = getIncludeParentDepth();
    if (!isRootDocument() && depth == 0) {
        return fParentXIncludeHandler.getIncludeParentBaseURI();
    } else {
        return this.getBaseURI(depth);
    }
}
	/**
     * Returns the [language] of the include parent.
     * 
     * @return the language property of the include parent.
     */
private String getIncludeParentLanguage() {
    int depth = getIncludeParentDepth();
    if (!isRootDocument() && depth == 0) {
        return fParentXIncludeHandler.getIncludeParentLanguage();
    } else {
        return getLanguage(depth);
    }
}
	/**
     * Returns whether an &lt;fallback&gt; was encountered at the specified depth,
     * as an ancestor of the current element, or as a sibling of an ancestor of the
     * current element.
     *
     * @param depth
     */
protected boolean getSawFallback(int depth) {
    if (depth >= fSawFallback.length) {
        return false;
    }
    return fSawFallback[depth];
}
	/**
     * Return whether an &lt;include&gt; was encountered at the specified depth,
     * as an ancestor of the current item.
     *
     * @param depth
     * @return
     */
protected boolean getSawInclude(int depth) {
    if (depth >= fSawInclude.length) {
        return false;
    }
    return fSawInclude[depth];
}
	// We need to find two consecutive elements in the scope stack,
// such that the first is lower than 'depth' (or equal), and the
// second is higher.
private int scopeOfBaseURI(int depth) {
    for (int i = fBaseURIScope.size() - 1; i >= 0; i--) {
        if (fBaseURIScope.elementAt(i) <= depth)
            return i;
    }
    // we should never get here, because 0 was put on the stack in startDocument()
    return -1;
}
	/**
     * Gets the language that was in use at that depth.
     * @param depth
     * @return the language
     */
public String getLanguage(int depth) {
    int scope = scopeOfLanguage(depth);
    return (String) fLanguageStack.elementAt(scope);
}
	/**
     * Gets the base URI that was in use at that depth
     * @param depth
     * @return the base URI
     */
public String getBaseURI(int depth) {
    int scope = scopeOfBaseURI(depth);
    return (String) fExpandedSystemID.elementAt(scope);
}
	// getFeatureDefault(String):Boolean
/**
     * Returns the default state for a property, or null if this
     * component does not want to report a default value for this
     * property.
     *
     * @param propertyId The property identifier.
     *
     * @since Xerces 2.2.0
     */
public Object getPropertyDefault(String propertyId) {
    for (int i = 0; i < RECOGNIZED_PROPERTIES.length; i++) {
        if (RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
            return PROPERTY_DEFAULTS[i];
        }
    }
    return null;
}
	/**
     * Returns true if the current [base URI] is the same as the [base URI] that
     * was in effect on the include parent.  This method should <em>only</em> be called
     * when the current element is a top level included element, i.e. the direct child
     * of a fallback element, or the root elements in an included document.
     * The "include parent" is the element which, in the result infoset, will be the
     * direct parent of the current element.
     * @return true if the [base URIs] are the same string
     */
protected boolean sameBaseURIAsIncludeParent() {
    String parentBaseURI = getIncludeParentBaseURI();
    String baseURI = fCurrentBaseURI.getExpandedSystemId();
    //       The decision also affects whether we output the file name of the URI, or just the path.
    return parentBaseURI != null && parentBaseURI.equals(baseURI);
}
	/**
     * Returns true if the current [language] is equivalent to the [language] that
     * was in effect on the include parent, taking case-insensitivity into account
     * as per [RFC 3066].  This method should <em>only</em> be called when the
     * current element is a top level included element, i.e. the direct child
     * of a fallback element, or the root elements in an included document.
     * The "include parent" is the element which, in the result infoset, will be the
     * direct parent of the current element.
     * 
     * @return true if the [language] properties have the same value
     * taking case-insensitivity into account as per [RFC 3066].
     */
protected boolean sameLanguageAsIncludeParent() {
    String parentLanguage = getIncludeParentLanguage();
    return parentLanguage != null && parentLanguage.equalsIgnoreCase(fCurrentLanguage);
}
	/**
     * Returns a URI, relative to the include parent's base URI, of the current
     * [base URI].  For instance, if the current [base URI] was "dir1/dir2/file.xml"
     * and the include parent's [base URI] was "dir/", this would return "dir2/file.xml".
     * @return the relative URI
     */
protected String getRelativeBaseURI() throws MalformedURIException {
    int includeParentDepth = getIncludeParentDepth();
    String relativeURI = this.getRelativeURI(includeParentDepth);
    if (isRootDocument()) {
        return relativeURI;
    } else {
        if (relativeURI.equals("")) {
            relativeURI = fCurrentBaseURI.getLiteralSystemId();
        }
        if (includeParentDepth == 0) {
            if (fParentRelativeURI == null) {
                fParentRelativeURI = fParentXIncludeHandler.getRelativeBaseURI();
            }
            if (fParentRelativeURI.equals("")) {
                return relativeURI;
            }
            URI base = new URI(fParentRelativeURI, true);
            URI uri = new URI(base, relativeURI);
            /** Check whether the scheme components are equal. */
            final String baseScheme = base.getScheme();
            final String literalScheme = uri.getScheme();
            if (!isEqual(baseScheme, literalScheme)) {
                return relativeURI;
            }
            /** Check whether the authority components are equal. */
            final String baseAuthority = base.getAuthority();
            final String literalAuthority = uri.getAuthority();
            if (!isEqual(baseAuthority, literalAuthority)) {
                return uri.getSchemeSpecificPart();
            }
            /** 
                 * The scheme and authority components are equal,
                 * return the path and the possible query and/or
                 * fragment which follow.
                 */
            final String literalPath = uri.getPath();
            final String literalQuery = uri.getQueryString();
            final String literalFragment = uri.getFragment();
            if (literalQuery != null || literalFragment != null) {
                StringBuffer buffer = new StringBuffer();
                if (literalPath != null) {
                    buffer.append(literalPath);
                }
                if (literalQuery != null) {
                    buffer.append('?');
                    buffer.append(literalQuery);
                }
                if (literalFragment != null) {
                    buffer.append('#');
                    buffer.append(literalFragment);
                }
                return buffer.toString();
            }
            return literalPath;
        } else {
            return relativeURI;
        }
    }
}
	/**
     * Returns the depth of the include parent.  Here, the include parent is
     * calculated as the last non-include or non-fallback element. It is assumed
     * this method is called when the current element is a top level included item.
     * Returning 0 indicates that the top level element in this document
     * was an include element.
     * @return the depth of the top level include element
     */
private int getIncludeParentDepth() {
    // or an include element, when this method is called.
    for (int i = fDepth - 1; i >= 0; i--) {
        // we'll always be in STATE_IGNORE)
        if (!getSawInclude(i) && !getSawFallback(i)) {
            return i;
        }
    }
    // a fallback element
    return 0;
}
	/**
     * Records that an &lt;fallback&gt; was encountered at the specified depth,
     * as an ancestor of the current element, or as a sibling of an ancestor of the
     * current element.
     *
     * @param depth
     * @param val
     */
protected void setSawFallback(int depth, boolean val) {
    if (depth >= fSawFallback.length) {
        boolean[] newarray = new boolean[depth * 2];
        System.arraycopy(fSawFallback, 0, newarray, 0, fSawFallback.length);
        fSawFallback = newarray;
    }
    fSawFallback[depth] = val;
}
	/**
     * Records that an &lt;include&gt; was encountered at the specified depth,
     * as an ancestor of the current item.
     *
     * @param depth
     * @param val
     */
protected void setSawInclude(int depth, boolean val) {
    if (depth >= fSawInclude.length) {
        boolean[] newarray = new boolean[depth * 2];
        System.arraycopy(fSawInclude, 0, newarray, 0, fSawInclude.length);
        fSawInclude = newarray;
    }
    fSawInclude[depth] = val;
}
	/**
     * Returns a relative URI, which when resolved against the base URI at the
     * specified depth, will create the current base URI.
     * This is accomplished by merged the literal system IDs.
     * @param depth the depth at which to start creating the relative URI
     * @return a relative URI to convert the base URI at the given depth to the current
     *         base URI
     */
public String getRelativeURI(int depth) throws MalformedURIException {
    // The literal system id at the location given by "start" is *in focus* at
    // the given depth. So we need to adjust it to the next scope, so that we
    // only process out of focus literal system ids
    int start = scopeOfBaseURI(depth) + 1;
    if (start == fBaseURIScope.size()) {
        // If that is the last system id, then we don't need a relative URI
        return "";
    }
    URI uri = new URI("file", (String) fLiteralSystemID.elementAt(start));
    for (int i = start + 1; i < fBaseURIScope.size(); i++) {
        uri = new URI(uri, (String) fLiteralSystemID.elementAt(i));
    }
    return uri.getPath();
}
	/**
     * Returns <code>true</code> if the given string 
     * would be valid in an HTTP header.
     * 
     * @param value string to check
     * @return <code>true</code> if the given string
     * would be valid in an HTTP header
     */
private boolean isValidInHTTPHeader(String value) {
    char ch;
    for (int i = value.length() - 1; i >= 0; --i) {
        ch = value.charAt(i);
        if (ch < 0x20 || ch > 0x7E) {
            return false;
        }
    }
    return true;
}
	//
// Escape an href value according to (4.1.1):
//
// To convert the value of the href attribute to an IRI reference, the following characters must be escaped:
// space #x20
// the delimiters < #x3C, > #x3E and " #x22
// the unwise characters { #x7B, } #x7D, | #x7C, \ #x5C, ^ #x5E and ` #x60
//
// To convert an IRI reference to a URI reference, the following characters must also be escaped:
// the Unicode plane 0 characters #xA0 - #xD7FF, #xF900-#xFDCF, #xFDF0-#xFFEF
// the Unicode plane 1-14 characters #x10000-#x1FFFD ... #xE0000-#xEFFFD
//
private String escapeHref(String href) {
    int len = href.length();
    int ch;
    StringBuffer buffer = new StringBuffer(len * 3);
    // for each character in the href
    int i = 0;
    for (; i < len; i++) {
        ch = href.charAt(i);
        // if it's not an ASCII character (excluding 0x7F), break here, and use UTF-8 encoding
        if (ch > 0x7E) {
            break;
        }
        // abort: href does not allow this character
        if (ch < 0x20) {
            return href;
        }
        if (gNeedEscaping[ch]) {
            buffer.append('%');
            buffer.append(gAfterEscaping1[ch]);
            buffer.append(gAfterEscaping2[ch]);
        } else {
            buffer.append((char) ch);
        }
    }
    // we saw some non-ascii character
    if (i < len) {
        // check if remainder of href contains any illegal characters before proceeding
        for (int j = i; j < len; ++j) {
            ch = href.charAt(j);
            if ((ch >= 0x20 && ch <= 0x7E) || (ch >= 0xA0 && ch <= 0xD7FF) || (ch >= 0xF900 && ch <= 0xFDCF) || (ch >= 0xFDF0 && ch <= 0xFFEF)) {
                continue;
            }
            if (XMLChar.isHighSurrogate(ch) && ++j < len) {
                int ch2 = href.charAt(j);
                if (XMLChar.isLowSurrogate(ch2)) {
                    ch2 = XMLChar.supplemental((char) ch, (char) ch2);
                    if (ch2 < 0xF0000 && (ch2 & 0xFFFF) <= 0xFFFD) {
                        continue;
                    }
                }
            }
            // abort: href does not allow this character
            return href;
        }
        // get UTF-8 bytes for the remaining sub-string
        byte[] bytes = null;
        byte b;
        try {
            bytes = href.substring(i).getBytes("UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            // should never happen
            return href;
        }
        len = bytes.length;
        // for each byte
        for (i = 0; i < len; i++) {
            b = bytes[i];
            // for non-ascii character: make it positive, then escape
            if (b < 0) {
                ch = b + 256;
                buffer.append('%');
                buffer.append(gHexChs[ch >> 4]);
                buffer.append(gHexChs[ch & 0xf]);
            } else if (gNeedEscaping[b]) {
                buffer.append('%');
                buffer.append(gAfterEscaping1[b]);
                buffer.append(gAfterEscaping2[b]);
            } else {
                buffer.append((char) b);
            }
        }
    }
    // otherwise, return the orginal one.
    if (buffer.length() != len) {
        return buffer.toString();
    } else {
        return href;
    }
}
}