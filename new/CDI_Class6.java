public class Class6 {

	public Class6(){
	}
	/**
     * Registers an identifier name with a specified element node.
     * If the identifier is already registered, the new element
     * node replaces the previous node. If the specified element
     * node is null, removeIdentifier() is called.
     *
     * @see #getIdentifier
     * @see #removeIdentifier
     */
public void putIdentifier(String idName, Element element) {
    if (element == null) {
        removeIdentifier(idName);
        return;
    }
    if (needsSyncData()) {
        synchronizeData();
    }
    if (identifiers == null) {
        identifiers = new Hashtable();
    }
    identifiers.put(idName, element);
}
	// getIdentifier(String):Element
/**
     * Removes a previously registered element with the specified
     * identifier name.
     *
     * @see #putIdentifier
     * @see #getIdentifier
     */
public void removeIdentifier(String idName) {
    if (needsSyncData()) {
        synchronizeData();
    }
    if (identifiers == null) {
        return;
    }
    identifiers.remove(idName);
}
	// removeIdentifier(String)
/** Returns an enumeration registered of identifier names. */
public Enumeration getIdentifiers() {
    if (needsSyncData()) {
        synchronizeData();
    }
    if (identifiers == null) {
        identifiers = new Hashtable();
    }
    return identifiers.keys();
}
	/**
     * A method to be called when an element has been renamed
     */
void renamedElement(Element oldEl, Element newEl) {
}
	// putIdentifier(String,Element)
/**
     * Returns a previously registered element with the specified
     * identifier name, or null if no element is registered.
     *
     * @see #putIdentifier
     * @see #removeIdentifier
     */
public Element getIdentifier(String idName) {
    if (needsSyncData()) {
        synchronizeData();
    }
    if (identifiers == null) {
        return null;
    }
    Element elem = (Element) identifiers.get(idName);
    if (elem != null) {
        // check that the element is in the tree
        Node parent = elem.getParentNode();
        while (parent != null) {
            if (parent == this) {
                return elem;
            }
            parent = parent.getParentNode();
        }
    }
    return null;
}
}
