public class Class2 {
	private Object PREFIX_XMLNS;

	public Class2(Object PREFIX_XMLNS){
		this.PREFIX_XMLNS = PREFIX_XMLNS;
	}
	/**
     * The start of an element. If the document specifies the start element
     * by using an empty tag, then the startElement method will immediately
     * be followed by the endElement method, with no intervening methods.
     * Overriding the parent to handle DOM_NAMESPACE_DECLARATIONS=false.
     *
     * @param element    The name of the element.
     * @param attributes The element attributes.
     * @param augs     Additional information that may include infoset augmentations
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
public void startElement(QName element, XMLAttributes attributes, Augmentations augs) {
    // namespace declarations parameter has no effect if namespaces is false.
    if (!fNamespaceDeclarations && fNamespaceAware) {
        int len = attributes.getLength();
        for (int i = len - 1; i >= 0; --i) {
            if (XMLSymbols.PREFIX_XMLNS == attributes.getPrefix(i) || XMLSymbols.PREFIX_XMLNS == attributes.getQName(i)) {
                attributes.removeAttributeAt(i);
            }
        }
    }
    super.startElement(element, attributes, augs);
}
	public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
    throw abort;
}
}
