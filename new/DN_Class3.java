public class Class3 {
	private Object features;
	private Object ATTRIBUTE_PSVI;
	private Object localpart;
	private Object DTNORMALIZATION;
	private Object uri;
	private Object out;
	private Object ELEMENT_PSVI;

	public Class3(Object features, Object ATTRIBUTE_PSVI, Object localpart, Object DTNORMALIZATION, Object uri, Object out, Object ELEMENT_PSVI){
		this.features = features;
		this.ATTRIBUTE_PSVI = ATTRIBUTE_PSVI;
		this.localpart = localpart;
		this.DTNORMALIZATION = DTNORMALIZATION;
		this.uri = uri;
		this.out = out;
		this.ELEMENT_PSVI = ELEMENT_PSVI;
	}
	/**
     * The start of an element.
     * 
     * @param element    The name of the element.
     * @param attributes The element attributes.
     * @param augs       Additional information that may include infoset augmentations
     *                   
     * @exception XNIException
     *                   Thrown by handler to signal an error.
     */
public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
    Element currentElement = (Element) fCurrentNode;
    int attrCount = attributes.getLength();
    if (DEBUG_EVENTS) {
        System.out.println("==>startElement: " + element + " attrs.length=" + attrCount);
    }
    for (int i = 0; i < attrCount; i++) {
        attributes.getName(i, fAttrQName);
        Attr attr = null;
        attr = currentElement.getAttributeNodeNS(fAttrQName.uri, fAttrQName.localpart);
        AttributePSVI attrPSVI = (AttributePSVI) attributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_PSVI);
        if (attrPSVI != null) {
            //REVISIT: instead we should be using augmentations:
            // to set/retrieve Id attributes
            XSTypeDefinition decl = attrPSVI.getMemberTypeDefinition();
            boolean id = false;
            if (decl != null) {
                id = ((XSSimpleType) decl).isIDType();
            } else {
                decl = attrPSVI.getTypeDefinition();
                if (decl != null) {
                    id = ((XSSimpleType) decl).isIDType();
                }
            }
            if (id) {
                ((ElementImpl) currentElement).setIdAttributeNode(attr, true);
            }
            if (fPSVI) {
                ((PSVIAttrNSImpl) attr).setPSVI(attrPSVI);
            }
            if ((fConfiguration.features & DOMConfigurationImpl.DTNORMALIZATION) != 0) {
                // datatype-normalization
                // NOTE: The specified value MUST be set after we set
                //       the node value because that turns the "specified"
                //       flag to "true" which may overwrite a "false"
                //       value from the attribute list.
                boolean specified = attr.getSpecified();
                attr.setValue(attrPSVI.getSchemaNormalizedValue());
                if (!specified) {
                    ((AttrImpl) attr).setSpecified(specified);
                }
            }
        }
    }
}
	/**
     * An empty element.
     * 
     * @param element    The name of the element.
     * @param attributes The element attributes.
     * @param augs       Additional information that may include infoset augmentations
     *                   
     * @exception XNIException
     *                   Thrown by handler to signal an error.
     */
public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>emptyElement: " + element);
    }
    startElement(element, attributes, augs);
    endElement(element, augs);
}
	/**
     * The end of an element.
     * 
     * @param element The name of the element.
     * @param augs    Additional information that may include infoset augmentations
     *                
     * @exception XNIException
     *                   Thrown by handler to signal an error.
     */
public void endElement(QName element, Augmentations augs) throws XNIException {
    if (DEBUG_EVENTS) {
        System.out.println("==>endElement: " + element);
    }
    if (augs != null) {
        ElementPSVI elementPSVI = (ElementPSVI) augs.getItem(Constants.ELEMENT_PSVI);
        if (elementPSVI != null) {
            ElementImpl elementNode = (ElementImpl) fCurrentNode;
            if (fPSVI) {
                ((PSVIElementNSImpl) fCurrentNode).setPSVI(elementPSVI);
            }
            // include element default content (if one is available)
            String normalizedValue = elementPSVI.getSchemaNormalizedValue();
            if ((fConfiguration.features & DOMConfigurationImpl.DTNORMALIZATION) != 0) {
                if (normalizedValue != null)
                    elementNode.setTextContent(normalizedValue);
            } else {
                // NOTE: this is a hack: it is possible that DOM had an empty element
                // and validator sent default value using characters(), which we don't 
                // implement. Thus, here we attempt to add the default value.
                String text = elementNode.getTextContent();
                if (text.length() == 0) {
                    // default content could be provided
                    if (normalizedValue != null)
                        elementNode.setTextContent(normalizedValue);
                }
            }
        }
    }
}
}