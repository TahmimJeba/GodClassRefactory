public class Class3 {

	public Class3(){
	}
	/**
     * Returns the suitable entity reference for this character value,
     * or null if no such entity exists. Calling this method with <tt>'&amp;'</tt>
     * will return <tt>"&amp;amp;"</tt>.
     *
     * @param ch Character value
     * @return Character entity name, or null
     */
protected abstract String getEntityRef(int ch);
	/**
     * Called to print additional text with whitespace handling.
     * If spaces are preserved, the text is printed as if by calling
     * {@link #printText(String,boolean,boolean)} with a call to {@link Printer#breakLine}
     * for each new line. If spaces are not preserved, the text is
     * broken at space boundaries if longer than the line width;
     * Multiple spaces are printed as such, but spaces at beginning
     * of line are removed.
     *
     * @param text The text to print
     * @param preserveSpace Space preserving flag
     * @param unescaped Print unescaped
     */
protected void printText(char[] chars, int start, int length, boolean preserveSpace, boolean unescaped) throws IOException {
    int index;
    char ch;
    if (preserveSpace) {
        // break will occur.
        while (length-- > 0) {
            ch = chars[start];
            ++start;
            if (ch == '\n' || ch == '\r' || unescaped)
                _printer.printText(ch);
            else
                printEscaped(ch);
        }
    } else {
        // no different than other text part.
        while (length-- > 0) {
            ch = chars[start];
            ++start;
            if (ch == ' ' || ch == '\f' || ch == '\t' || ch == '\n' || ch == '\r')
                _printer.printSpace();
            else if (unescaped)
                _printer.printText(ch);
            else
                printEscaped(ch);
        }
    }
}
	protected void printText(String text, boolean preserveSpace, boolean unescaped) throws IOException {
    int index;
    char ch;
    if (preserveSpace) {
        // break will occur.
        for (index = 0; index < text.length(); ++index) {
            ch = text.charAt(index);
            if (ch == '\n' || ch == '\r' || unescaped)
                _printer.printText(ch);
            else
                printEscaped(ch);
        }
    } else {
        // no different than other text part.
        for (index = 0; index < text.length(); ++index) {
            ch = text.charAt(index);
            if (ch == ' ' || ch == '\f' || ch == '\t' || ch == '\n' || ch == '\r')
                _printer.printSpace();
            else if (unescaped)
                _printer.printText(ch);
            else
                printEscaped(ch);
        }
    }
}
	/**
	 * Escapes chars
	 */
final void printHex(int ch) throws IOException {
    _printer.printText("&#x");
    _printer.printText(Integer.toHexString(ch));
    _printer.printText(';');
}
	protected void printEscaped(int ch) throws IOException {
    String charRef;
    // If there is a suitable entity reference for this
    // character, print it. The list of available entity
    // references is almost but not identical between
    // XML and HTML.
    charRef = getEntityRef(ch);
    if (charRef != null) {
        _printer.printText('&');
        _printer.printText(charRef);
        _printer.printText(';');
    } else if ((ch >= ' ' && _encodingInfo.isPrintable((char) ch) && ch != 0xF7) || ch == '\n' || ch == '\r' || ch == '\t') {
        // terminator, ASCII delete, or above a certain Unicode threshold.
        if (ch < 0x10000) {
            _printer.printText((char) ch);
        } else {
            _printer.printText((char) (((ch - 0x10000) >> 10) + 0xd800));
            _printer.printText((char) (((ch - 0x10000) & 0x3ff) + 0xdc00));
        }
    } else {
        printHex(ch);
    }
}
	/**
     * Escapes a string so it may be printed as text content or attribute
     * value. Non printable characters are escaped using character references.
     * Where the format specifies a deault entity reference, that reference
     * is used (e.g. <tt>&amp;lt;</tt>).
     *
     * @param source The string to escape
     */
protected void printEscaped(String source) throws IOException {
    for (int i = 0; i < source.length(); ++i) {
        int ch = source.charAt(i);
        if ((ch & 0xfc00) == 0xd800 && i + 1 < source.length()) {
            int lowch = source.charAt(i + 1);
            if ((lowch & 0xfc00) == 0xdc00) {
                ch = 0x10000 + ((ch - 0xd800) << 10) + lowch - 0xdc00;
                i++;
            }
        }
        printEscaped(ch);
    }
}
}