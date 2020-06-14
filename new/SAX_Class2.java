public class Class2 {
	private Object ATTRIBUTE_DECLARED;
	private Object TRUE;

	public Class2(Object ATTRIBUTE_DECLARED, Object TRUE){
		this.ATTRIBUTE_DECLARED = ATTRIBUTE_DECLARED;
		this.TRUE = TRUE;
	}
	// Attributes2 methods
// REVISIT: Localize exception messages. -- mrglavas
public boolean isDeclared(int index) {
    if (index < 0 || index >= fAttributes.getLength()) {
        throw new ArrayIndexOutOfBoundsException(index);
    }
    return Boolean.TRUE.equals(fAttributes.getAugmentations(index).getItem(Constants.ATTRIBUTE_DECLARED));
}
	public boolean isDeclared(String qName) {
    int index = getIndex(qName);
    if (index == -1) {
        throw new IllegalArgumentException(qName);
    }
    return Boolean.TRUE.equals(fAttributes.getAugmentations(index).getItem(Constants.ATTRIBUTE_DECLARED));
}
	public boolean isDeclared(String uri, String localName) {
    int index = getIndex(uri, localName);
    if (index == -1) {
        throw new IllegalArgumentException(localName);
    }
    return Boolean.TRUE.equals(fAttributes.getAugmentations(index).getItem(Constants.ATTRIBUTE_DECLARED));
}
}
