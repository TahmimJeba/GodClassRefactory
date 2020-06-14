public class Class1 {
	private Object fRelatedNode;
	private Object ENTITY_REFERENCE_NODE;
	private Object fType;
	private Object fLocator;
	private Object DOM_DOMAIN;
	private Object fMessage;
	private Object length;
	private Object XML_DOMAIN;
	private Object fRelatedData;
	private Object SEVERITY_ERROR;
	private Object fSeverity;
	private Object SEVERITY_FATAL_ERROR;

	public Class1(Object fRelatedNode, Object ENTITY_REFERENCE_NODE, Object fType, Object fLocator, Object DOM_DOMAIN, Object fMessage, Object length, Object XML_DOMAIN, Object fRelatedData, Object SEVERITY_ERROR, Object fSeverity, Object SEVERITY_FATAL_ERROR){
		this.fRelatedNode = fRelatedNode;
		this.ENTITY_REFERENCE_NODE = ENTITY_REFERENCE_NODE;
		this.fType = fType;
		this.fLocator = fLocator;
		this.DOM_DOMAIN = DOM_DOMAIN;
		this.fMessage = fMessage;
		this.length = length;
		this.XML_DOMAIN = XML_DOMAIN;
		this.fRelatedData = fRelatedData;
		this.SEVERITY_ERROR = SEVERITY_ERROR;
		this.fSeverity = fSeverity;
		this.SEVERITY_FATAL_ERROR = SEVERITY_FATAL_ERROR;
	}
	//
// Methods for well-formness checking
//
/**
     * Check if CDATA section is well-formed
     * @param datavalue
     * @param isXML11Version = true if XML 1.1
     */
public static final void isCDataWF(DOMErrorHandler errorHandler, DOMErrorImpl error, DOMLocatorImpl locator, String datavalue, boolean isXML11Version) {
    if (datavalue == null || (datavalue.length() == 0)) {
        return;
    }
    char[] dataarray = datavalue.toCharArray();
    int datalength = dataarray.length;
    // version of the document is XML 1.1
    if (isXML11Version) {
        // we need to check all chracters as per production rules of XML11
        int i = 0;
        while (i < datalength) {
            char c = dataarray[i++];
            if (XML11Char.isXML11Invalid(c)) {
                // check if this is a supplemental character
                if (XMLChar.isHighSurrogate(c) && i < datalength) {
                    char c2 = dataarray[i++];
                    if (XMLChar.isLowSurrogate(c2) && XMLChar.isSupplemental(XMLChar.supplemental(c, c2))) {
                        continue;
                    }
                }
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.XML_DOMAIN, "InvalidCharInCDSect", new Object[] { Integer.toString(c, 16) });
                reportDOMError(errorHandler, error, locator, msg, DOMError.SEVERITY_ERROR, "wf-invalid-character");
            } else if (c == ']') {
                int count = i;
                if (count < datalength && dataarray[count] == ']') {
                    while (++count < datalength && dataarray[count] == ']') {
                    // do nothing
                    }
                    if (count < datalength && dataarray[count] == '>') {
                        // CDEndInContent
                        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.XML_DOMAIN, "CDEndInContent", null);
                        reportDOMError(errorHandler, error, locator, msg, DOMError.SEVERITY_ERROR, "wf-invalid-character");
                    }
                }
            }
        }
    } else // version of the document is XML 1.0
    {
        // we need to check all chracters as per production rules of XML 1.0
        int i = 0;
        while (i < datalength) {
            char c = dataarray[i++];
            if (XMLChar.isInvalid(c)) {
                // check if this is a supplemental character
                if (XMLChar.isHighSurrogate(c) && i < datalength) {
                    char c2 = dataarray[i++];
                    if (XMLChar.isLowSurrogate(c2) && XMLChar.isSupplemental(XMLChar.supplemental(c, c2))) {
                        continue;
                    }
                }
                // Note:  The key InvalidCharInCDSect from XMLMessages.properties
                // is being used to obtain the message and DOM error type
                // "wf-invalid-character" is used.  Also per DOM it is error but 
                // as per XML spec. it is fatal error
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.XML_DOMAIN, "InvalidCharInCDSect", new Object[] { Integer.toString(c, 16) });
                reportDOMError(errorHandler, error, locator, msg, DOMError.SEVERITY_ERROR, "wf-invalid-character");
            } else if (c == ']') {
                int count = i;
                if (count < datalength && dataarray[count] == ']') {
                    while (++count < datalength && dataarray[count] == ']') {
                    // do nothing
                    }
                    if (count < datalength && dataarray[count] == '>') {
                        String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.XML_DOMAIN, "CDEndInContent", null);
                        reportDOMError(errorHandler, error, locator, msg, DOMError.SEVERITY_ERROR, "wf-invalid-character");
                    }
                }
            }
        }
    }
// end-else fDocument.isXMLVersion()
}
	// isCDataWF
/**
     * NON-DOM: check for valid XML characters as per the XML version
     * @param datavalue
     * @param isXML11Version = true if XML 1.1
     */
public static final void isXMLCharWF(DOMErrorHandler errorHandler, DOMErrorImpl error, DOMLocatorImpl locator, String datavalue, boolean isXML11Version) {
    if (datavalue == null || (datavalue.length() == 0)) {
        return;
    }
    char[] dataarray = datavalue.toCharArray();
    int datalength = dataarray.length;
    // version of the document is XML 1.1
    if (isXML11Version) {
        //we need to check all characters as per production rules of XML11
        int i = 0;
        while (i < datalength) {
            if (XML11Char.isXML11Invalid(dataarray[i++])) {
                // check if this is a supplemental character
                char ch = dataarray[i - 1];
                if (XMLChar.isHighSurrogate(ch) && i < datalength) {
                    char ch2 = dataarray[i++];
                    if (XMLChar.isLowSurrogate(ch2) && XMLChar.isSupplemental(XMLChar.supplemental(ch, ch2))) {
                        continue;
                    }
                }
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "InvalidXMLCharInDOM", new Object[] { Integer.toString(dataarray[i - 1], 16) });
                reportDOMError(errorHandler, error, locator, msg, DOMError.SEVERITY_ERROR, "wf-invalid-character");
            }
        }
    } else // version of the document is XML 1.0
    {
        // we need to check all characters as per production rules of XML 1.0
        int i = 0;
        while (i < datalength) {
            if (XMLChar.isInvalid(dataarray[i++])) {
                // check if this is a supplemental character
                char ch = dataarray[i - 1];
                if (XMLChar.isHighSurrogate(ch) && i < datalength) {
                    char ch2 = dataarray[i++];
                    if (XMLChar.isLowSurrogate(ch2) && XMLChar.isSupplemental(XMLChar.supplemental(ch, ch2))) {
                        continue;
                    }
                }
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "InvalidXMLCharInDOM", new Object[] { Integer.toString(dataarray[i - 1], 16) });
                reportDOMError(errorHandler, error, locator, msg, DOMError.SEVERITY_ERROR, "wf-invalid-character");
            }
        }
    }
// end-else fDocument.isXMLVersion()
}
	// isXMLCharWF
/**
     * NON-DOM: check if value of the comment is well-formed
     * @param datavalue
     * @param isXML11Version = true if XML 1.1
     */
public static final void isCommentWF(DOMErrorHandler errorHandler, DOMErrorImpl error, DOMLocatorImpl locator, String datavalue, boolean isXML11Version) {
    if (datavalue == null || (datavalue.length() == 0)) {
        return;
    }
    char[] dataarray = datavalue.toCharArray();
    int datalength = dataarray.length;
    // version of the document is XML 1.1
    if (isXML11Version) {
        // we need to check all chracters as per production rules of XML11
        int i = 0;
        while (i < datalength) {
            char c = dataarray[i++];
            if (XML11Char.isXML11Invalid(c)) {
                // check if this is a supplemental character
                if (XMLChar.isHighSurrogate(c) && i < datalength) {
                    char c2 = dataarray[i++];
                    if (XMLChar.isLowSurrogate(c2) && XMLChar.isSupplemental(XMLChar.supplemental(c, c2))) {
                        continue;
                    }
                }
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.XML_DOMAIN, "InvalidCharInComment", new Object[] { Integer.toString(dataarray[i - 1], 16) });
                reportDOMError(errorHandler, error, locator, msg, DOMError.SEVERITY_ERROR, "wf-invalid-character");
            } else if (c == '-' && i < datalength && dataarray[i] == '-') {
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.XML_DOMAIN, "DashDashInComment", null);
                // invalid: '--' in comment                   
                reportDOMError(errorHandler, error, locator, msg, DOMError.SEVERITY_ERROR, "wf-invalid-character");
            }
        }
    } else // version of the document is XML 1.0
    {
        // we need to check all chracters as per production rules of XML 1.0
        int i = 0;
        while (i < datalength) {
            char c = dataarray[i++];
            if (XMLChar.isInvalid(c)) {
                // check if this is a supplemental character
                if (XMLChar.isHighSurrogate(c) && i < datalength) {
                    char c2 = dataarray[i++];
                    if (XMLChar.isLowSurrogate(c2) && XMLChar.isSupplemental(XMLChar.supplemental(c, c2))) {
                        continue;
                    }
                }
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.XML_DOMAIN, "InvalidCharInComment", new Object[] { Integer.toString(dataarray[i - 1], 16) });
                reportDOMError(errorHandler, error, locator, msg, DOMError.SEVERITY_ERROR, "wf-invalid-character");
            } else if (c == '-' && i < datalength && dataarray[i] == '-') {
                String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.XML_DOMAIN, "DashDashInComment", null);
                // invalid: '--' in comment                   
                reportDOMError(errorHandler, error, locator, msg, DOMError.SEVERITY_ERROR, "wf-invalid-character");
            }
        }
    }
// end-else fDocument.isXMLVersion()
}
	// isCommentWF
/** NON-DOM: check if attribute value is well-formed
     * @param attributes
     * @param a
     * @param value
     */
public static final void isAttrValueWF(DOMErrorHandler errorHandler, DOMErrorImpl error, DOMLocatorImpl locator, NamedNodeMap attributes, Attr a, String value, boolean xml11Version) {
    if (a instanceof AttrImpl && ((AttrImpl) a).hasStringValue()) {
        isXMLCharWF(errorHandler, error, locator, value, xml11Version);
    } else {
        NodeList children = a.getChildNodes();
        //check each child node of the attribute's value
        for (int j = 0; j < children.getLength(); j++) {
            Node child = children.item(j);
            //If the attribute's child is an entity refernce
            if (child.getNodeType() == Node.ENTITY_REFERENCE_NODE) {
                Document owner = a.getOwnerDocument();
                Entity ent = null;
                //of the attribute's ownerDocument
                if (owner != null) {
                    DocumentType docType = owner.getDoctype();
                    if (docType != null) {
                        NamedNodeMap entities = docType.getEntities();
                        ent = (Entity) entities.getNamedItemNS("*", child.getNodeName());
                    }
                }
                //If the entity was not found issue a fatal error
                if (ent == null) {
                    String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "UndeclaredEntRefInAttrValue", new Object[] { a.getNodeName() });
                    reportDOMError(errorHandler, error, locator, msg, DOMError.SEVERITY_ERROR, "UndeclaredEntRefInAttrValue");
                }
            } else {
                // Text node
                isXMLCharWF(errorHandler, error, locator, child.getNodeValue(), xml11Version);
            }
        }
    }
}
	/**
     * Reports a DOM error to the user handler.
     * 
     * If the error is fatal, the processing will be always aborted.
     */
public static final void reportDOMError(DOMErrorHandler errorHandler, DOMErrorImpl error, DOMLocatorImpl locator, String message, short severity, String type) {
    if (errorHandler != null) {
        error.reset();
        error.fMessage = message;
        error.fSeverity = severity;
        error.fLocator = locator;
        error.fType = type;
        error.fRelatedData = locator.fRelatedNode;
        if (!errorHandler.handleError(error))
            throw abort;
    }
    if (severity == DOMError.SEVERITY_FATAL_ERROR)
        throw abort;
}
}