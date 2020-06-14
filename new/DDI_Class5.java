public class Class5 {
	private Object length;
	private Object out;

	public Class5(Object length, Object out){
		this.length = length;
		this.out = out;
	}
	//
// Protected methods
//
/** Synchronizes the node's data. */
protected void synchronizeData() {
    // no need to sync in the future
    needsSyncData(false);
    // fluff up enough nodes to fill identifiers hash
    if (fIdElement != null) {
        // REVISIT: There has to be a more efficient way of
        //          doing this. But keep in mind that the
        //          tree can have been altered and re-ordered
        //          before all of the element nodes with ID
        //          attributes have been registered. For now
        //          this is reasonable and safe. -Ac
        IntVector path = new IntVector();
        for (int i = 0; i < fIdCount; i++) {
            // ignore if it's already been registered
            int elementNodeIndex = fIdElement[i];
            String idName = fIdName[i];
            if (idName == null) {
                continue;
            }
            // find path from this element to the root
            path.removeAllElements();
            int index = elementNodeIndex;
            do {
                path.addElement(index);
                int pchunk = index >> CHUNK_SHIFT;
                int pindex = index & CHUNK_MASK;
                index = getChunkIndex(fNodeParent, pchunk, pindex);
            } while (index != -1);
            // Traverse path (backwards), fluffing the elements
            // along the way. When this loop finishes, "place"
            // will contain the reference to the element node
            // we're interested in. -Ac
            Node place = this;
            for (int j = path.size() - 2; j >= 0; j--) {
                index = path.elementAt(j);
                Node child = place.getLastChild();
                while (child != null) {
                    if (child instanceof DeferredNode) {
                        int nodeIndex = ((DeferredNode) child).getNodeIndex();
                        if (nodeIndex == index) {
                            place = child;
                            break;
                        }
                    }
                    child = child.getPreviousSibling();
                }
            }
            // register the element
            Element element = (Element) place;
            putIdentifier0(idName, element);
            fIdName[i] = null;
            // see if there are more IDs on this element
            while (i + 1 < fIdCount && fIdElement[i + 1] == elementNodeIndex) {
                idName = fIdName[++i];
                if (idName == null) {
                    continue;
                }
                putIdentifier0(idName, element);
            }
        }
    }
// if identifiers
}
	/**
     * This version of putIdentifier is needed to avoid fluffing
     * all of the paths to ID attributes when a node object is
     * created that contains an ID attribute.
     */
private final void putIdentifier0(String idName, Element element) {
    if (DEBUG_IDS) {
        System.out.println("putIdentifier0(" + idName + ", " + element + ')');
    }
    // create hashtable
    if (identifiers == null) {
        identifiers = new java.util.Hashtable();
    }
    // save ID and its associated element
    identifiers.put(idName, element);
}
	/** Appends an element to the end of the vector. */
public void addElement(int element) {
    ensureCapacity(size + 1);
    data[size++] = element;
}
	// cloneNode(int,boolean):int
/** Appends a child to the specified parent in the table. */
public void appendChild(int parentIndex, int childIndex) {
    // append parent index
    int pchunk = parentIndex >> CHUNK_SHIFT;
    int pindex = parentIndex & CHUNK_MASK;
    int cchunk = childIndex >> CHUNK_SHIFT;
    int cindex = childIndex & CHUNK_MASK;
    setChunkIndex(fNodeParent, parentIndex, cchunk, cindex);
    // set previous sibling of new child
    int olast = getChunkIndex(fNodeLastChild, pchunk, pindex);
    setChunkIndex(fNodePrevSib, olast, cchunk, cindex);
    // update parent's last child
    setChunkIndex(fNodeLastChild, childIndex, pchunk, pindex);
}
	// insertBefore(int,int,int):int
/** Sets the last child of the parentIndex to childIndex. */
public void setAsLastChild(int parentIndex, int childIndex) {
    int pchunk = parentIndex >> CHUNK_SHIFT;
    int pindex = parentIndex & CHUNK_MASK;
    setChunkIndex(fNodeLastChild, childIndex, pchunk, pindex);
}
	// getNodeURI(int,int):String
// identifier maintenance
/** Registers an identifier name with a specified element node. */
public void putIdentifier(String name, int elementNodeIndex) {
    if (DEBUG_IDS) {
        System.out.println("putIdentifier(" + name + ", " + elementNodeIndex + ')' + " // " + getChunkValue(fNodeName, elementNodeIndex >> CHUNK_SHIFT, elementNodeIndex & CHUNK_MASK));
    }
    // initialize arrays
    if (fIdName == null) {
        fIdName = new String[64];
        fIdElement = new int[64];
    }
    // resize arrays
    if (fIdCount == fIdName.length) {
        String[] idName = new String[fIdCount * 2];
        System.arraycopy(fIdName, 0, idName, 0, fIdCount);
        fIdName = idName;
        int[] idElement = new int[idName.length];
        System.arraycopy(fIdElement, 0, idElement, 0, fIdCount);
        fIdElement = idElement;
    }
    // store identifier
    fIdName[fIdCount] = name;
    fIdElement[fIdCount] = elementNodeIndex;
    fIdCount++;
}
	// print()
//
// DeferredNode methods
//
/** Returns the node index. */
public int getNodeIndex() {
    return 0;
}
	/** Clears the vector. */
public void removeAllElements() {
    size = 0;
}
}