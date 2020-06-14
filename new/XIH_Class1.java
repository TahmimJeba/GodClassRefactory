public class Class1 {

	public Class1(){
	}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#endAttlist(org.apache.xerces.xni.Augmentations)
     */
public void endAttlist(Augmentations augmentations) throws XNIException {
    if (fDTDHandler != null) {
        fDTDHandler.endAttlist(augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#elementDecl(java.lang.String, java.lang.String, org.apache.xerces.xni.Augmentations)
     */
public void elementDecl(String name, String contentModel, Augmentations augmentations) throws XNIException {
    if (fDTDHandler != null) {
        fDTDHandler.elementDecl(name, contentModel, augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#endConditional(org.apache.xerces.xni.Augmentations)
     */
public void endConditional(Augmentations augmentations) throws XNIException {
    if (fDTDHandler != null) {
        fDTDHandler.endConditional(augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#endDTD(org.apache.xerces.xni.Augmentations)
     */
public void endDTD(Augmentations augmentations) throws XNIException {
    if (fDTDHandler != null) {
        fDTDHandler.endDTD(augmentations);
    }
    fInDTD = false;
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#endExternalSubset(org.apache.xerces.xni.Augmentations)
     */
public void endExternalSubset(Augmentations augmentations) throws XNIException {
    if (fDTDHandler != null) {
        fDTDHandler.endExternalSubset(augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#endParameterEntity(java.lang.String, org.apache.xerces.xni.Augmentations)
     */
public void endParameterEntity(String name, Augmentations augmentations) throws XNIException {
    if (fDTDHandler != null) {
        fDTDHandler.endParameterEntity(name, augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#externalEntityDecl(java.lang.String, org.apache.xerces.xni.XMLResourceIdentifier, org.apache.xerces.xni.Augmentations)
     */
public void externalEntityDecl(String name, XMLResourceIdentifier identifier, Augmentations augmentations) throws XNIException {
    if (fDTDHandler != null) {
        fDTDHandler.externalEntityDecl(name, identifier, augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#notationDecl(java.lang.String, org.apache.xerces.xni.XMLResourceIdentifier, org.apache.xerces.xni.Augmentations)
     */
public void notationDecl(String name, XMLResourceIdentifier identifier, Augmentations augmentations) throws XNIException {
    this.addNotation(name, identifier, augmentations);
    if (fDTDHandler != null) {
        fDTDHandler.notationDecl(name, identifier, augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#startExternalSubset(org.apache.xerces.xni.XMLResourceIdentifier, org.apache.xerces.xni.Augmentations)
     */
public void startExternalSubset(XMLResourceIdentifier identifier, Augmentations augmentations) throws XNIException {
    if (fDTDHandler != null) {
        fDTDHandler.startExternalSubset(identifier, augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#startParameterEntity(java.lang.String, org.apache.xerces.xni.XMLResourceIdentifier, java.lang.String, org.apache.xerces.xni.Augmentations)
     */
public void startParameterEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augmentations) throws XNIException {
    if (fDTDHandler != null) {
        fDTDHandler.startParameterEntity(name, identifier, encoding, augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#unparsedEntityDecl(java.lang.String, org.apache.xerces.xni.XMLResourceIdentifier, java.lang.String, org.apache.xerces.xni.Augmentations)
     */
public void unparsedEntityDecl(String name, XMLResourceIdentifier identifier, String notation, Augmentations augmentations) throws XNIException {
    this.addUnparsedEntity(name, identifier, notation, augmentations);
    if (fDTDHandler != null) {
        fDTDHandler.unparsedEntityDecl(name, identifier, notation, augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#ignoredCharacters(org.apache.xerces.xni.XMLString, org.apache.xerces.xni.Augmentations)
     */
public void ignoredCharacters(XMLString text, Augmentations augmentations) throws XNIException {
    if (fDTDHandler != null) {
        fDTDHandler.ignoredCharacters(text, augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#internalEntityDecl(java.lang.String, org.apache.xerces.xni.XMLString, org.apache.xerces.xni.XMLString, org.apache.xerces.xni.Augmentations)
     */
public void internalEntityDecl(String name, XMLString text, XMLString nonNormalizedText, Augmentations augmentations) throws XNIException {
    if (fDTDHandler != null) {
        fDTDHandler.internalEntityDecl(name, text, nonNormalizedText, augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#startAttlist(java.lang.String, org.apache.xerces.xni.Augmentations)
     */
public void startAttlist(String elementName, Augmentations augmentations) throws XNIException {
    if (fDTDHandler != null) {
        fDTDHandler.startAttlist(elementName, augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#startConditional(short, org.apache.xerces.xni.Augmentations)
     */
public void startConditional(short type, Augmentations augmentations) throws XNIException {
    if (fDTDHandler != null) {
        fDTDHandler.startConditional(type, augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#startDTD(org.apache.xerces.xni.XMLLocator, org.apache.xerces.xni.Augmentations)
     */
public void startDTD(XMLLocator locator, Augmentations augmentations) throws XNIException {
    fInDTD = true;
    if (fDTDHandler != null) {
        fDTDHandler.startDTD(locator, augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.parser.XMLDTDSource#setDTDHandler(org.apache.xerces.xni.XMLDTDHandler)
     */
public void setDTDHandler(XMLDTDHandler handler) {
    fDTDHandler = handler;
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#setDTDSource(org.apache.xerces.xni.parser.XMLDTDSource)
     */
public void setDTDSource(XMLDTDSource source) {
    fDTDSource = source;
}
	// DTDHandler methods
// We are only interested in the notation and unparsed entity declarations,
// the rest we just pass on
/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#attributeDecl(java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String, org.apache.xerces.xni.XMLString, org.apache.xerces.xni.XMLString, org.apache.xerces.xni.Augmentations)
     */
public void attributeDecl(String elementName, String attributeName, String type, String[] enumeration, String defaultType, XMLString defaultValue, XMLString nonNormalizedDefaultValue, Augmentations augmentations) throws XNIException {
    if (fDTDHandler != null) {
        fDTDHandler.attributeDecl(elementName, attributeName, type, enumeration, defaultType, defaultValue, nonNormalizedDefaultValue, augmentations);
    }
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.XMLDTDHandler#getDTDSource()
     */
public XMLDTDSource getDTDSource() {
    return fDTDSource;
}
	/* (non-Javadoc)
     * @see org.apache.xerces.xni.parser.XMLDTDSource#getDTDHandler()
     */
public XMLDTDHandler getDTDHandler() {
    return fDTDHandler;
}
}