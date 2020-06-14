public class Class1 {
	private Object offset;
	private Object ch;
	private Object length;
	private Object TRUE;

	public Class1(Object offset, Object ch, Object length, Object TRUE){
		this.offset = offset;
		this.ch = ch;
		this.length = length;
		this.TRUE = TRUE;
	}
	// startElement(QName,XMLAttributes)
/**
     * Character content.
     *
     * @param text The content.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void characters(XMLString text, Augmentations augs) throws XNIException {
    // character call with empty data
    if (text.length == 0) {
        return;
    }
    try {
        // SAX1
        if (fDocumentHandler != null) {
            // REVISIT: should we support schema-normalized-value for SAX1 events
            // 
            fDocumentHandler.characters(text.ch, text.offset, text.length);
        }
        // SAX2
        if (fContentHandler != null) {
            fContentHandler.characters(text.ch, text.offset, text.length);
        }
    } catch (SAXException e) {
        throw new XNIException(e);
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
    try {
        // SAX1
        if (fDocumentHandler != null) {
            fDocumentHandler.ignorableWhitespace(text.ch, text.offset, text.length);
        }
        // SAX2
        if (fContentHandler != null) {
            fContentHandler.ignorableWhitespace(text.ch, text.offset, text.length);
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// endCDATA()
/**
     * A comment.
     *
     * @param text The text in the comment.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by application to signal an error.
     */
public void comment(XMLString text, Augmentations augs) throws XNIException {
    try {
        // SAX2 extension
        if (fLexicalHandler != null) {
            fLexicalHandler.comment(text.ch, 0, text.length);
        }
    } catch (SAXException e) {
        throw new XNIException(e);
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
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
    try {
        // SAX1
        if (fDocumentHandler != null) {
            fDocumentHandler.processingInstruction(target, data.toString());
        }
        // SAX2
        if (fContentHandler != null) {
            fContentHandler.processingInstruction(target, data.toString());
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// elementDecl(String,String, Augmentations)
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
     *
     * @param nonNormalizedDefaultValue  The attribute default value with no normalization 
     *                      performed, or null if no default value is specified.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void attributeDecl(String elementName, String attributeName, String type, String[] enumeration, String defaultType, XMLString defaultValue, XMLString nonNormalizedDefaultValue, Augmentations augs) throws XNIException {
    try {
        // SAX2 extension
        if (fDeclHandler != null) {
            // used as a key to detect duplicate attribute definitions.
            String elemAttr = new StringBuffer(elementName).append("<").append(attributeName).toString();
            if (fDeclaredAttrs.get(elemAttr) != null) {
                // we aren't permitted to return duplicate attribute definitions
                return;
            }
            fDeclaredAttrs.put(elemAttr, Boolean.TRUE);
            if (type.equals("NOTATION") || type.equals("ENUMERATION")) {
                StringBuffer str = new StringBuffer();
                if (type.equals("NOTATION")) {
                    str.append(type);
                    str.append(" (");
                } else {
                    str.append("(");
                }
                for (int i = 0; i < enumeration.length; i++) {
                    str.append(enumeration[i]);
                    if (i < enumeration.length - 1) {
                        str.append('|');
                    }
                }
                str.append(')');
                type = str.toString();
            }
            String value = (defaultValue == null) ? null : defaultValue.toString();
            fDeclHandler.attributeDecl(elementName, attributeName, type, defaultType, value);
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
	// attributeDecl(String,String,String,String[],String,XMLString, XMLString, Augmentations)
/**
     * An internal entity declaration.
     *
     * @param name The name of the entity. Parameter entity names start with
     *             '%', whereas the name of a general entity is just the
     *             entity name.
     * @param text The value of the entity.
     * @param nonNormalizedText The non-normalized value of the entity. This
     *             value contains the same sequence of characters that was in
     *             the internal entity declaration, without any entity
     *             references expanded.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void internalEntityDecl(String name, XMLString text, XMLString nonNormalizedText, Augmentations augs) throws XNIException {
    try {
        // SAX2 extensions
        if (fDeclHandler != null) {
            fDeclHandler.internalEntityDecl(name, text.toString());
        }
    } catch (SAXException e) {
        throw new XNIException(e);
    }
}
}