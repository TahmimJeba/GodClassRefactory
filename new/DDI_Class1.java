public class Class1 {
	private Object ELEMENT_NODE;
	private Object TYPE_NODE;
	private Object ELEMENT_DEFINITION_NODE;

	public Class1(Object ELEMENT_NODE, Object TYPE_NODE, Object ELEMENT_DEFINITION_NODE){
		this.ELEMENT_NODE = ELEMENT_NODE;
		this.TYPE_NODE = TYPE_NODE;
		this.ELEMENT_DEFINITION_NODE = ELEMENT_DEFINITION_NODE;
	}
	// createDeferredEntityReference(String):int
/** Creates an element node with a URI in the table and type information. */
public int createDeferredElement(String elementURI, String elementName, Object type) {
    // create node
    int elementNodeIndex = createNode(Node.ELEMENT_NODE);
    int elementChunk = elementNodeIndex >> CHUNK_SHIFT;
    int elementIndex = elementNodeIndex & CHUNK_MASK;
    setChunkValue(fNodeName, elementName, elementChunk, elementIndex);
    setChunkValue(fNodeURI, elementURI, elementChunk, elementIndex);
    setChunkValue(fNodeValue, type, elementChunk, elementIndex);
    // return node index
    return elementNodeIndex;
}
	// createDeferredEntityReference(String):int
/** Creates an element node with a URI in the table and type information. */
public int createDeferredElement(String elementURI, String elementName, Object type) {
    // create node
    int elementNodeIndex = createNode(Node.ELEMENT_NODE);
    int elementChunk = elementNodeIndex >> CHUNK_SHIFT;
    int elementIndex = elementNodeIndex & CHUNK_MASK;
    setChunkValue(fNodeName, elementName, elementChunk, elementIndex);
    setChunkValue(fNodeURI, elementURI, elementChunk, elementIndex);
    setChunkValue(fNodeValue, type, elementChunk, elementIndex);
    // return node index
    return elementNodeIndex;
}
	// createDeferredEntityReference(String):int
/** Creates an element node with a URI in the table and type information. */
public int createDeferredElement(String elementURI, String elementName, Object type) {
    // create node
    int elementNodeIndex = createNode(Node.ELEMENT_NODE);
    int elementChunk = elementNodeIndex >> CHUNK_SHIFT;
    int elementIndex = elementNodeIndex & CHUNK_MASK;
    setChunkValue(fNodeName, elementName, elementChunk, elementIndex);
    setChunkValue(fNodeURI, elementURI, elementChunk, elementIndex);
    setChunkValue(fNodeValue, type, elementChunk, elementIndex);
    // return node index
    return elementNodeIndex;
}
	// createDeferredElement(String,String):int
/** @deprecated. Creates an element node in the table. */
public int createDeferredElement(String elementName) {
    return createDeferredElement(null, elementName);
}
	// createDeferredElement(String,String):int
/** @deprecated. Creates an element node in the table. */
public int createDeferredElement(String elementName) {
    return createDeferredElement(null, elementName);
}
	// createDeferredElement(String,String):int
/** @deprecated. Creates an element node in the table. */
public int createDeferredElement(String elementName) {
    return createDeferredElement(null, elementName);
}
	/** @deprecated. Creates an element node with a URI in the table. */
public int createDeferredElement(String elementURI, String elementName) {
    // create node
    int elementNodeIndex = createNode(Node.ELEMENT_NODE);
    int elementChunk = elementNodeIndex >> CHUNK_SHIFT;
    int elementIndex = elementNodeIndex & CHUNK_MASK;
    setChunkValue(fNodeName, elementName, elementChunk, elementIndex);
    setChunkValue(fNodeURI, elementURI, elementChunk, elementIndex);
    // return node index
    return elementNodeIndex;
}
	/** @deprecated. Creates an element node with a URI in the table. */
public int createDeferredElement(String elementURI, String elementName) {
    // create node
    int elementNodeIndex = createNode(Node.ELEMENT_NODE);
    int elementChunk = elementNodeIndex >> CHUNK_SHIFT;
    int elementIndex = elementNodeIndex & CHUNK_MASK;
    setChunkValue(fNodeName, elementName, elementChunk, elementIndex);
    setChunkValue(fNodeURI, elementURI, elementChunk, elementIndex);
    // return node index
    return elementNodeIndex;
}
	/** @deprecated. Creates an element node with a URI in the table. */
public int createDeferredElement(String elementURI, String elementName) {
    // create node
    int elementNodeIndex = createNode(Node.ELEMENT_NODE);
    int elementChunk = elementNodeIndex >> CHUNK_SHIFT;
    int elementIndex = elementNodeIndex & CHUNK_MASK;
    setChunkValue(fNodeName, elementName, elementChunk, elementIndex);
    setChunkValue(fNodeURI, elementURI, elementChunk, elementIndex);
    // return node index
    return elementNodeIndex;
}
	/** @deprecated. Sets an attribute on an element node.*/
public int setDeferredAttribute(int elementNodeIndex, String attrName, String attrURI, String attrValue, boolean specified) {
    // create attribute
    int attrNodeIndex = createDeferredAttribute(attrName, attrURI, attrValue, specified);
    int attrChunk = attrNodeIndex >> CHUNK_SHIFT;
    int attrIndex = attrNodeIndex & CHUNK_MASK;
    // set attribute's parent to element
    setChunkIndex(fNodeParent, elementNodeIndex, attrChunk, attrIndex);
    int elementChunk = elementNodeIndex >> CHUNK_SHIFT;
    int elementIndex = elementNodeIndex & CHUNK_MASK;
    // get element's last attribute
    int lastAttrNodeIndex = getChunkIndex(fNodeExtra, elementChunk, elementIndex);
    if (lastAttrNodeIndex != 0) {
        // add link from new attribute to last attribute
        setChunkIndex(fNodePrevSib, lastAttrNodeIndex, attrChunk, attrIndex);
    }
    // add link from element to new last attribute
    setChunkIndex(fNodeExtra, attrNodeIndex, elementChunk, elementIndex);
    // return node index
    return attrNodeIndex;
}
	/** @deprecated. Sets an attribute on an element node.*/
public int setDeferredAttribute(int elementNodeIndex, String attrName, String attrURI, String attrValue, boolean specified) {
    // create attribute
    int attrNodeIndex = createDeferredAttribute(attrName, attrURI, attrValue, specified);
    int attrChunk = attrNodeIndex >> CHUNK_SHIFT;
    int attrIndex = attrNodeIndex & CHUNK_MASK;
    // set attribute's parent to element
    setChunkIndex(fNodeParent, elementNodeIndex, attrChunk, attrIndex);
    int elementChunk = elementNodeIndex >> CHUNK_SHIFT;
    int elementIndex = elementNodeIndex & CHUNK_MASK;
    // get element's last attribute
    int lastAttrNodeIndex = getChunkIndex(fNodeExtra, elementChunk, elementIndex);
    if (lastAttrNodeIndex != 0) {
        // add link from new attribute to last attribute
        setChunkIndex(fNodePrevSib, lastAttrNodeIndex, attrChunk, attrIndex);
    }
    // add link from element to new last attribute
    setChunkIndex(fNodeExtra, attrNodeIndex, elementChunk, elementIndex);
    // return node index
    return attrNodeIndex;
}
	// createDeferredElement(String,String):int
/**
	 * This method is used by the DOMParser to create attributes.
	 * @param elementNodeIndex
	 * @param attrName
	 * @param attrURI
	 * @param attrValue
	 * @param specified
	 * @param id
	 * @param type
	 * @return int
	 */
public int setDeferredAttribute(int elementNodeIndex, String attrName, String attrURI, String attrValue, boolean specified, boolean id, Object type) {
    // create attribute
    int attrNodeIndex = createDeferredAttribute(attrName, attrURI, attrValue, specified);
    int attrChunk = attrNodeIndex >> CHUNK_SHIFT;
    int attrIndex = attrNodeIndex & CHUNK_MASK;
    // set attribute's parent to element
    setChunkIndex(fNodeParent, elementNodeIndex, attrChunk, attrIndex);
    int elementChunk = elementNodeIndex >> CHUNK_SHIFT;
    int elementIndex = elementNodeIndex & CHUNK_MASK;
    // get element's last attribute
    int lastAttrNodeIndex = getChunkIndex(fNodeExtra, elementChunk, elementIndex);
    if (lastAttrNodeIndex != 0) {
        int lastAttrChunk = lastAttrNodeIndex >> CHUNK_SHIFT;
        int lastAttrIndex = lastAttrNodeIndex & CHUNK_MASK;
        // add link from new attribute to last attribute
        setChunkIndex(fNodePrevSib, lastAttrNodeIndex, attrChunk, attrIndex);
    }
    // add link from element to new last attribute
    setChunkIndex(fNodeExtra, attrNodeIndex, elementChunk, elementIndex);
    int extra = getChunkIndex(fNodeExtra, attrChunk, attrIndex);
    if (id) {
        extra = extra | ID;
        setChunkIndex(fNodeExtra, extra, attrChunk, attrIndex);
        String value = getChunkValue(fNodeValue, attrChunk, attrIndex);
        putIdentifier(value, elementNodeIndex);
    }
    // store type information
    if (type != null) {
        int extraDataIndex = createNode(DeferredNode.TYPE_NODE);
        int echunk = extraDataIndex >> CHUNK_SHIFT;
        int eindex = extraDataIndex & CHUNK_MASK;
        setChunkIndex(fNodeLastChild, extraDataIndex, attrChunk, attrIndex);
        setChunkValue(fNodeValue, type, echunk, eindex);
    }
    // return node index
    return attrNodeIndex;
}
	// createDeferredElement(String,String):int
/**
	 * This method is used by the DOMParser to create attributes.
	 * @param elementNodeIndex
	 * @param attrName
	 * @param attrURI
	 * @param attrValue
	 * @param specified
	 * @param id
	 * @param type
	 * @return int
	 */
public int setDeferredAttribute(int elementNodeIndex, String attrName, String attrURI, String attrValue, boolean specified, boolean id, Object type) {
    // create attribute
    int attrNodeIndex = createDeferredAttribute(attrName, attrURI, attrValue, specified);
    int attrChunk = attrNodeIndex >> CHUNK_SHIFT;
    int attrIndex = attrNodeIndex & CHUNK_MASK;
    // set attribute's parent to element
    setChunkIndex(fNodeParent, elementNodeIndex, attrChunk, attrIndex);
    int elementChunk = elementNodeIndex >> CHUNK_SHIFT;
    int elementIndex = elementNodeIndex & CHUNK_MASK;
    // get element's last attribute
    int lastAttrNodeIndex = getChunkIndex(fNodeExtra, elementChunk, elementIndex);
    if (lastAttrNodeIndex != 0) {
        int lastAttrChunk = lastAttrNodeIndex >> CHUNK_SHIFT;
        int lastAttrIndex = lastAttrNodeIndex & CHUNK_MASK;
        // add link from new attribute to last attribute
        setChunkIndex(fNodePrevSib, lastAttrNodeIndex, attrChunk, attrIndex);
    }
    // add link from element to new last attribute
    setChunkIndex(fNodeExtra, attrNodeIndex, elementChunk, elementIndex);
    int extra = getChunkIndex(fNodeExtra, attrChunk, attrIndex);
    if (id) {
        extra = extra | ID;
        setChunkIndex(fNodeExtra, extra, attrChunk, attrIndex);
        String value = getChunkValue(fNodeValue, attrChunk, attrIndex);
        putIdentifier(value, elementNodeIndex);
    }
    // store type information
    if (type != null) {
        int extraDataIndex = createNode(DeferredNode.TYPE_NODE);
        int echunk = extraDataIndex >> CHUNK_SHIFT;
        int eindex = extraDataIndex & CHUNK_MASK;
        setChunkIndex(fNodeLastChild, extraDataIndex, attrChunk, attrIndex);
        setChunkValue(fNodeValue, type, echunk, eindex);
    }
    // return node index
    return attrNodeIndex;
}
	// setAttributeNode(int,int):int
/** Adds an attribute node to the specified element. */
public void setIdAttributeNode(int elemIndex, int attrIndex) {
    int chunk = attrIndex >> CHUNK_SHIFT;
    int index = attrIndex & CHUNK_MASK;
    int extra = getChunkIndex(fNodeExtra, chunk, index);
    extra = extra | ID;
    setChunkIndex(fNodeExtra, extra, chunk, index);
    String value = getChunkValue(fNodeValue, chunk, index);
    putIdentifier(value, elemIndex);
}
	/** Sets type of attribute */
public void setIdAttribute(int attrIndex) {
    int chunk = attrIndex >> CHUNK_SHIFT;
    int index = attrIndex & CHUNK_MASK;
    int extra = getChunkIndex(fNodeExtra, chunk, index);
    extra = extra | ID;
    setChunkIndex(fNodeExtra, extra, chunk, index);
}
	// getNodeType(int):int
/** Returns the attribute value of the given name. */
public String getAttribute(int elemIndex, String name) {
    if (elemIndex == -1 || name == null) {
        return null;
    }
    int echunk = elemIndex >> CHUNK_SHIFT;
    int eindex = elemIndex & CHUNK_MASK;
    int attrIndex = getChunkIndex(fNodeExtra, echunk, eindex);
    while (attrIndex != -1) {
        int achunk = attrIndex >> CHUNK_SHIFT;
        int aindex = attrIndex & CHUNK_MASK;
        if (getChunkValue(fNodeName, achunk, aindex) == name) {
            return getChunkValue(fNodeValue, achunk, aindex);
        }
        attrIndex = getChunkIndex(fNodePrevSib, achunk, aindex);
    }
    return null;
}
	// createDeferredAttribute(String,String,String,boolean):int
/** Creates an element definition in the table.*/
public int createDeferredElementDefinition(String elementName) {
    // create node
    int nodeIndex = createNode(NodeImpl.ELEMENT_DEFINITION_NODE);
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    setChunkValue(fNodeName, elementName, chunk, index);
    // return node index
    return nodeIndex;
}
	// appendChild(int,int)
/** Adds an attribute node to the specified element. */
public int setAttributeNode(int elemIndex, int attrIndex) {
    int echunk = elemIndex >> CHUNK_SHIFT;
    int eindex = elemIndex & CHUNK_MASK;
    int achunk = attrIndex >> CHUNK_SHIFT;
    int aindex = attrIndex & CHUNK_MASK;
    // see if this attribute is already here
    String attrName = getChunkValue(fNodeName, achunk, aindex);
    int oldAttrIndex = getChunkIndex(fNodeExtra, echunk, eindex);
    int nextIndex = -1;
    int oachunk = -1;
    int oaindex = -1;
    while (oldAttrIndex != -1) {
        oachunk = oldAttrIndex >> CHUNK_SHIFT;
        oaindex = oldAttrIndex & CHUNK_MASK;
        String oldAttrName = getChunkValue(fNodeName, oachunk, oaindex);
        if (oldAttrName.equals(attrName)) {
            break;
        }
        nextIndex = oldAttrIndex;
        oldAttrIndex = getChunkIndex(fNodePrevSib, oachunk, oaindex);
    }
    // remove old attribute
    if (oldAttrIndex != -1) {
        // patch links
        int prevIndex = getChunkIndex(fNodePrevSib, oachunk, oaindex);
        if (nextIndex == -1) {
            setChunkIndex(fNodeExtra, prevIndex, echunk, eindex);
        } else {
            int pchunk = nextIndex >> CHUNK_SHIFT;
            int pindex = nextIndex & CHUNK_MASK;
            setChunkIndex(fNodePrevSib, prevIndex, pchunk, pindex);
        }
        // remove connections to siblings
        clearChunkIndex(fNodeType, oachunk, oaindex);
        clearChunkValue(fNodeName, oachunk, oaindex);
        clearChunkValue(fNodeValue, oachunk, oaindex);
        clearChunkIndex(fNodeParent, oachunk, oaindex);
        clearChunkIndex(fNodePrevSib, oachunk, oaindex);
        int attrTextIndex = clearChunkIndex(fNodeLastChild, oachunk, oaindex);
        int atchunk = attrTextIndex >> CHUNK_SHIFT;
        int atindex = attrTextIndex & CHUNK_MASK;
        clearChunkIndex(fNodeType, atchunk, atindex);
        clearChunkValue(fNodeValue, atchunk, atindex);
        clearChunkIndex(fNodeParent, atchunk, atindex);
        clearChunkIndex(fNodeLastChild, atchunk, atindex);
    }
    // add new attribute
    int prevIndex = getChunkIndex(fNodeExtra, echunk, eindex);
    setChunkIndex(fNodeExtra, attrIndex, echunk, eindex);
    setChunkIndex(fNodePrevSib, prevIndex, achunk, aindex);
    // return
    return oldAttrIndex;
}
}
