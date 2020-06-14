public class Class2 {
	private Object ch;
	private Object length;

	public Class2(Object ch, Object length){
		this.ch = ch;
		this.length = length;
	}
	/**
         * Sets the augmentations of the attribute at the specified index.
         * 
         * @param attrIndex The attribute index.
         * @param augs      The augmentations.
         */
public void setAugmentations(int attrIndex, Augmentations augs) {
    fAugmentations.setElementAt(augs, attrIndex);
}
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
}
	/**
     * A comment.
     * 
     * @param text   The text in the comment.
     * @param augs   Additional information that may include infoset augmentations
     *               
     * @exception XNIException
     *                   Thrown by application to signal an error.
     */
public void comment(XMLString text, Augmentations augs) throws XNIException {
}
	/**
     * Character content.
     * 
     * @param text   The content.
     * @param augs   Additional information that may include infoset augmentations
     *               
     * @exception XNIException
     *                   Thrown by handler to signal an error.
     */
public void characters(XMLString text, Augmentations augs) throws XNIException {
}
	/**
     * Ignorable whitespace. For this method to be called, the document
     * source must have some way of determining that the text containing
     * only whitespace characters should be considered ignorable. For
     * example, the validator can determine if a length of whitespace
     * characters in the document are ignorable based on the element
     * content model.
     * 
     * @param text   The ignorable whitespace.
     * @param augs   Additional information that may include infoset augmentations
     *               
     * @exception XNIException
     *                   Thrown by handler to signal an error.
     */
public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
    allWhitespace = true;
}
	/**
     * The start of a CDATA section.
     * 
     * @param augs   Additional information that may include infoset augmentations
     *               
     * @exception XNIException
     *                   Thrown by handler to signal an error.
     */
public void startCDATA(Augmentations augs) throws XNIException {
}
	/**
     * The end of a CDATA section.
     * 
     * @param augs   Additional information that may include infoset augmentations
     *               
     * @exception XNIException
     *                   Thrown by handler to signal an error.
     */
public void endCDATA(Augmentations augs) throws XNIException {
}
	/**
     * The end of the document.
     * 
     * @param augs   Additional information that may include infoset augmentations
     *               
     * @exception XNIException
     *                   Thrown by handler to signal an error.
     */
public void endDocument(Augmentations augs) throws XNIException {
}
	/* REVISIT: remove this method if DOM does not change spec.
	 * Performs partial XML 1.0 attribute value normalization and replaces
     * attribute value if the value is changed after the normalization.
     * DOM defines that normalizeDocument acts as if the document was going 
     * through a save and load cycle, given that serializer will not escape
     * any '\n' or '\r' characters on load those will be normalized.
     * Thus during normalize document we need to do the following:
     * - perform "2.11 End-of-Line Handling"
     * - replace #xD, #xA, #x9 with #x20 (white space).
     * Note: This alg. won't attempt to resolve entity references or character entity 
     * references, since '&' will be escaped during serialization and during loading
     * this won't be recognized as entity reference, i.e. attribute value "&foo;" will 
     * be serialized as "&amp;foo;" and thus after loading will be "&foo;" again.
	 * @param value current attribute value
	 * @param attr current attribute
	 * @return String the value (could be original if normalization did not change 
     * the string)
	 */
final String normalizeAttributeValue(String value, Attr attr) {
    if (!attr.getSpecified()) {
        // since those were added by validator
        return value;
    }
    int end = value.length();
    // ensure capacity 
    if (fNormalizedValue.ch.length < end) {
        fNormalizedValue.ch = new char[end];
    }
    fNormalizedValue.length = 0;
    boolean normalized = false;
    for (int i = 0; i < end; i++) {
        char c = value.charAt(i);
        if (c == 0x0009 || c == 0x000A) {
            fNormalizedValue.ch[fNormalizedValue.length++] = ' ';
            normalized = true;
        } else if (c == 0x000D) {
            normalized = true;
            fNormalizedValue.ch[fNormalizedValue.length++] = ' ';
            int next = i + 1;
            // skip following xA
            if (next < end && value.charAt(next) == 0x000A)
                i = next;
        } else {
            fNormalizedValue.ch[fNormalizedValue.length++] = c;
        }
    }
    if (normalized) {
        value = fNormalizedValue.toString();
        attr.setValue(value);
    }
    return value;
}
	// 
// XMLDocumentHandler methods
//
/**
     * The start of the document.
     * 
     * @param locator  The document locator, or null if the document
     *                 location cannot be reported during the parsing
     *                 of this document. However, it is <em>strongly</em>
     *                 recommended that a locator be supplied that can
     *                 at least report the system identifier of the
     *                 document.
     * @param encoding The auto-detected IANA encoding name of the entity
     *                 stream. This value will be null in those situations
     *                 where the entity encoding is not auto-detected (e.g.
     *                 internal entities or a document entity that is
     *                 parsed from a java.io.Reader).
     * @param namespaceContext
     *                 The namespace context in effect at the
     *                 start of this document.
     *                 This object represents the current context.
     *                 Implementors of this class are responsible
     *                 for copying the namespace bindings from the
     *                 the current context (and its parent contexts)
     *                 if that information is important.
     *                 
     * @param augs     Additional information that may include infoset augmentations
     * @exception XNIException
     *                   Thrown by handler to signal an error.
     */
public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
}
	/**
     * Notifies of the presence of an XMLDecl line in the document. If
     * present, this method will be called immediately following the
     * startDocument call.
     * 
     * @param version    The XML version.
     * @param encoding   The IANA encoding name of the document, or null if
     *                   not specified.
     * @param standalone The standalone value, or null if not specified.
     * @param augs       Additional information that may include infoset augmentations
     *                   
     * @exception XNIException
     *                   Thrown by handler to signal an error.
     */
public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
}
	/**
     * Notifies of the presence of a TextDecl line in an entity. If present,
     * this method will be called immediately following the startEntity call.
     * <p>
     * <strong>Note:</strong> This method will never be called for the
     * document entity; it is only called for external general entities
     * referenced in document content.
     * <p>
     * <strong>Note:</strong> This method is not called for entity references
     * appearing as part of attribute values.
     * 
     * @param version  The XML version, or null if not specified.
     * @param encoding The IANA encoding name of the entity.
     * @param augs     Additional information that may include infoset augmentations
     *                 
     * @exception XNIException
     *                   Thrown by handler to signal an error.
     */
public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
}
	/**
     * Notifies of the presence of the DOCTYPE line in the document.
     * 
     * @param rootElement
     *                 The name of the root element.
     * @param publicId The public identifier if an external DTD or null
     *                 if the external DTD is specified using SYSTEM.
     * @param systemId The system identifier if an external DTD, null
     *                 otherwise.
     * @param augs     Additional information that may include infoset augmentations
     *                 
     * @exception XNIException
     *                   Thrown by handler to signal an error.
     */
public void doctypeDecl(String rootElement, String publicId, String systemId, Augmentations augs) throws XNIException {
}
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
     * @param augs   Additional information that may include infoset augmentations
     *               
     * @exception XNIException
     *                   Thrown by handler to signal an error.
     */
public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
}
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
}
}