public class Class2 {
	private Object ENTITY_REFERENCE_NODE;
	private Object CDATA_SECTION_NODE;
	private Object ELEMENT_NODE;
	private Object ELEMENT_DEFINITION_NODE;
	private Object DOCUMENT_NODE;
	private Object ENTITY_NODE;
	private Object length;
	private Object TYPE_NODE;
	private Object ATTRIBUTE_NODE;
	private Object COMMENT_NODE;
	private Object out;
	private Object PROCESSING_INSTRUCTION_NODE;
	private Object DOCUMENT_TYPE_NODE;
	private Object TEXT_NODE;

	public Class2(Object ENTITY_REFERENCE_NODE, Object CDATA_SECTION_NODE, Object ELEMENT_NODE, Object ELEMENT_DEFINITION_NODE, Object DOCUMENT_NODE, Object ENTITY_NODE, Object length, Object TYPE_NODE, Object ATTRIBUTE_NODE, Object COMMENT_NODE, Object out, Object PROCESSING_INSTRUCTION_NODE, Object DOCUMENT_TYPE_NODE, Object TEXT_NODE){
		this.ENTITY_REFERENCE_NODE = ENTITY_REFERENCE_NODE;
		this.CDATA_SECTION_NODE = CDATA_SECTION_NODE;
		this.ELEMENT_NODE = ELEMENT_NODE;
		this.ELEMENT_DEFINITION_NODE = ELEMENT_DEFINITION_NODE;
		this.DOCUMENT_NODE = DOCUMENT_NODE;
		this.ENTITY_NODE = ENTITY_NODE;
		this.length = length;
		this.TYPE_NODE = TYPE_NODE;
		this.ATTRIBUTE_NODE = ATTRIBUTE_NODE;
		this.COMMENT_NODE = COMMENT_NODE;
		this.out = out;
		this.PROCESSING_INSTRUCTION_NODE = PROCESSING_INSTRUCTION_NODE;
		this.DOCUMENT_TYPE_NODE = DOCUMENT_TYPE_NODE;
		this.TEXT_NODE = TEXT_NODE;
	}
	// setAsLastChild(int,int)
/**
     * Returns the parent node of the given node.
     * <em>Calling this method does not free the parent index.</em>
     */
public int getParentNode(int nodeIndex) {
    return getParentNode(nodeIndex, false);
}
	// setAsLastChild(int,int)
/**
     * Returns the parent node of the given node.
     * <em>Calling this method does not free the parent index.</em>
     */
public int getParentNode(int nodeIndex) {
    return getParentNode(nodeIndex, false);
}
	/**
     * Returns the parent node of the given node.
     * @param free True to free parent node.
     */
public int getParentNode(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return -1;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? clearChunkIndex(fNodeParent, chunk, index) : getChunkIndex(fNodeParent, chunk, index);
}
	/**
     * Returns the parent node of the given node.
     * @param free True to free parent node.
     */
public int getParentNode(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return -1;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? clearChunkIndex(fNodeParent, chunk, index) : getChunkIndex(fNodeParent, chunk, index);
}
	// getParentNode(int):int
/** Returns the last child of the given node. */
public int getLastChild(int nodeIndex) {
    return getLastChild(nodeIndex, true);
}
	// getParentNode(int):int
/** Returns the last child of the given node. */
public int getLastChild(int nodeIndex) {
    return getLastChild(nodeIndex, true);
}
	/**
     * Returns the last child of the given node.
     * @param free True to free child index.
     */
public int getLastChild(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return -1;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? clearChunkIndex(fNodeLastChild, chunk, index) : getChunkIndex(fNodeLastChild, chunk, index);
}
	/**
     * Returns the last child of the given node.
     * @param free True to free child index.
     */
public int getLastChild(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return -1;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? clearChunkIndex(fNodeLastChild, chunk, index) : getChunkIndex(fNodeLastChild, chunk, index);
}
	// getLastChild(int,boolean):int
/**
     * Returns the prev sibling of the given node.
     * This is post-normalization of Text Nodes.
     */
public int getPrevSibling(int nodeIndex) {
    return getPrevSibling(nodeIndex, true);
}
	// getLastChild(int,boolean):int
/**
     * Returns the prev sibling of the given node.
     * This is post-normalization of Text Nodes.
     */
public int getPrevSibling(int nodeIndex) {
    return getPrevSibling(nodeIndex, true);
}
	/**
     * Returns the prev sibling of the given node.
     * @param free True to free sibling index.
     */
public int getPrevSibling(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return -1;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    int type = getChunkIndex(fNodeType, chunk, index);
    if (type == Node.TEXT_NODE) {
        do {
            nodeIndex = getChunkIndex(fNodePrevSib, chunk, index);
            if (nodeIndex == -1) {
                break;
            }
            chunk = nodeIndex >> CHUNK_SHIFT;
            index = nodeIndex & CHUNK_MASK;
            type = getChunkIndex(fNodeType, chunk, index);
        } while (type == Node.TEXT_NODE);
    } else {
        nodeIndex = getChunkIndex(fNodePrevSib, chunk, index);
    }
    return nodeIndex;
}
	/**
     * Returns the prev sibling of the given node.
     * @param free True to free sibling index.
     */
public int getPrevSibling(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return -1;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    int type = getChunkIndex(fNodeType, chunk, index);
    if (type == Node.TEXT_NODE) {
        do {
            nodeIndex = getChunkIndex(fNodePrevSib, chunk, index);
            if (nodeIndex == -1) {
                break;
            }
            chunk = nodeIndex >> CHUNK_SHIFT;
            index = nodeIndex & CHUNK_MASK;
            type = getChunkIndex(fNodeType, chunk, index);
        } while (type == Node.TEXT_NODE);
    } else {
        nodeIndex = getChunkIndex(fNodePrevSib, chunk, index);
    }
    return nodeIndex;
}
	/**
     * Returns the <i>real</i> prev sibling of the given node.
     * @param free True to free sibling index.
     */
public int getRealPrevSibling(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return -1;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? clearChunkIndex(fNodePrevSib, chunk, index) : getChunkIndex(fNodePrevSib, chunk, index);
}
	/**
     * Returns the <i>real</i> prev sibling of the given node.
     * @param free True to free sibling index.
     */
public int getRealPrevSibling(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return -1;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? clearChunkIndex(fNodePrevSib, chunk, index) : getChunkIndex(fNodePrevSib, chunk, index);
}
	// getPrevSibling(int,boolean):int
/**
     * Returns the <i>real</i> prev sibling of the given node,
     * directly from the data structures. Used by TextImpl#getNodeValue()
     * to normalize values.
     */
public int getRealPrevSibling(int nodeIndex) {
    return getRealPrevSibling(nodeIndex, true);
}
	// getPrevSibling(int,boolean):int
/**
     * Returns the <i>real</i> prev sibling of the given node,
     * directly from the data structures. Used by TextImpl#getNodeValue()
     * to normalize values.
     */
public int getRealPrevSibling(int nodeIndex) {
    return getRealPrevSibling(nodeIndex, true);
}
	// createNodeObject(int):Node
/** Returns the name of the given node. */
public String getNodeName(int nodeIndex) {
    return getNodeName(nodeIndex, true);
}
	// createNodeObject(int):Node
/** Returns the name of the given node. */
public String getNodeName(int nodeIndex) {
    return getNodeName(nodeIndex, true);
}
	// getNodeNameString(int):String
/**
     * Returns the name of the given node.
     * @param free True to free the string index.
     */
public String getNodeName(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return null;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? clearChunkValue(fNodeName, chunk, index) : getChunkValue(fNodeName, chunk, index);
}
	// getNodeNameString(int):String
/**
     * Returns the name of the given node.
     * @param free True to free the string index.
     */
public String getNodeName(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return null;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? clearChunkValue(fNodeName, chunk, index) : getChunkValue(fNodeName, chunk, index);
}
	// getNodeName(int,boolean):String
/** Returns the real value of the given node. */
public String getNodeValueString(int nodeIndex) {
    return getNodeValueString(nodeIndex, true);
}
	// getNodeName(int,boolean):String
/** Returns the real value of the given node. */
public String getNodeValueString(int nodeIndex) {
    return getNodeValueString(nodeIndex, true);
}
	// getNodeValueString(int,boolean):String
/**
     * Returns the value of the given node.
     */
public String getNodeValue(int nodeIndex) {
    return getNodeValue(nodeIndex, true);
}
	// getNodeValueString(int,boolean):String
/**
     * Returns the value of the given node.
     */
public String getNodeValue(int nodeIndex) {
    return getNodeValue(nodeIndex, true);
}
	/**
     * Returns the value of the given node.
     * @param free True to free the value index.
     */
public String getNodeValue(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return null;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? clearChunkValue(fNodeValue, chunk, index) : getChunkValue(fNodeValue, chunk, index);
}
	/**
     * Returns the value of the given node.
     * @param free True to free the value index.
     */
public String getNodeValue(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return null;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? clearChunkValue(fNodeValue, chunk, index) : getChunkValue(fNodeValue, chunk, index);
}
	// getNodeValue(int,boolean):String
/**
     * Returns the extra info of the given node.
     * Used by AttrImpl to store specified value (1 == true).
     */
public int getNodeExtra(int nodeIndex) {
    return getNodeExtra(nodeIndex, true);
}
	// getNodeValue(int,boolean):String
/**
     * Returns the extra info of the given node.
     * Used by AttrImpl to store specified value (1 == true).
     */
public int getNodeExtra(int nodeIndex) {
    return getNodeExtra(nodeIndex, true);
}
	/**
     * Returns the extra info of the given node.
     * @param free True to free the value index.
     */
public int getNodeExtra(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return -1;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? clearChunkIndex(fNodeExtra, chunk, index) : getChunkIndex(fNodeExtra, chunk, index);
}
	/**
     * Returns the extra info of the given node.
     * @param free True to free the value index.
     */
public int getNodeExtra(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return -1;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? clearChunkIndex(fNodeExtra, chunk, index) : getChunkIndex(fNodeExtra, chunk, index);
}
	// getNodeExtra(int,boolean):int
/** Returns the type of the given node. */
public short getNodeType(int nodeIndex) {
    return getNodeType(nodeIndex, true);
}
	// getNodeExtra(int,boolean):int
/** Returns the type of the given node. */
public short getNodeType(int nodeIndex) {
    return getNodeType(nodeIndex, true);
}
	/**
     * Returns the type of the given node.
     * @param free True to free type index.
     */
public short getNodeType(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return -1;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? (short) clearChunkIndex(fNodeType, chunk, index) : (short) getChunkIndex(fNodeType, chunk, index);
}
	/**
     * Returns the type of the given node.
     * @param free True to free type index.
     */
public short getNodeType(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return -1;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? (short) clearChunkIndex(fNodeType, chunk, index) : (short) getChunkIndex(fNodeType, chunk, index);
}
	/** Returns the URI of the given node. */
public String getNodeURI(int nodeIndex) {
    return getNodeURI(nodeIndex, true);
}
	/** Returns the URI of the given node. */
public String getNodeURI(int nodeIndex) {
    return getNodeURI(nodeIndex, true);
}
	/**
     * Returns the URI of the given node.
     * @param free True to free URI index.
     */
public String getNodeURI(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return null;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? clearChunkValue(fNodeURI, chunk, index) : getChunkValue(fNodeURI, chunk, index);
}
	/**
     * Returns the URI of the given node.
     * @param free True to free URI index.
     */
public String getNodeURI(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return null;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    return free ? clearChunkValue(fNodeURI, chunk, index) : getChunkValue(fNodeURI, chunk, index);
}
	// putIdentifier(String,int)
//
// DEBUG
//
/** Prints out the tables. */
public void print() {
    if (DEBUG_PRINT_REF_COUNTS) {
        System.out.print("num\t");
        System.out.print("type\t");
        System.out.print("name\t");
        System.out.print("val\t");
        System.out.print("par\t");
        System.out.print("lch\t");
        System.out.print("psib");
        System.out.println();
        for (int i = 0; i < fNodeType.length; i++) {
            if (fNodeType[i] != null) {
                // separator
                System.out.print("--------");
                System.out.print("--------");
                System.out.print("--------");
                System.out.print("--------");
                System.out.print("--------");
                System.out.print("--------");
                System.out.print("--------");
                System.out.println();
                // ref count
                System.out.print(i);
                System.out.print('\t');
                switch(fNodeType[i][CHUNK_SIZE]) {
                    case DocumentImpl.ELEMENT_DEFINITION_NODE:
                        {
                            System.out.print("EDef");
                            break;
                        }
                    case Node.DOCUMENT_NODE:
                        {
                            System.out.print("Doc");
                            break;
                        }
                    case Node.DOCUMENT_TYPE_NODE:
                        {
                            System.out.print("DType");
                            break;
                        }
                    case Node.COMMENT_NODE:
                        {
                            System.out.print("Com");
                            break;
                        }
                    case Node.PROCESSING_INSTRUCTION_NODE:
                        {
                            System.out.print("PI");
                            break;
                        }
                    case Node.ELEMENT_NODE:
                        {
                            System.out.print("Elem");
                            break;
                        }
                    case Node.ENTITY_NODE:
                        {
                            System.out.print("Ent");
                            break;
                        }
                    case Node.ENTITY_REFERENCE_NODE:
                        {
                            System.out.print("ERef");
                            break;
                        }
                    case Node.TEXT_NODE:
                        {
                            System.out.print("Text");
                            break;
                        }
                    case Node.ATTRIBUTE_NODE:
                        {
                            System.out.print("Attr");
                            break;
                        }
                    case DeferredNode.TYPE_NODE:
                        {
                            System.out.print("TypeInfo");
                            break;
                        }
                    default:
                        {
                            System.out.print("?" + fNodeType[i][CHUNK_SIZE]);
                        }
                }
                System.out.print('\t');
                System.out.print(fNodeName[i][CHUNK_SIZE]);
                System.out.print('\t');
                System.out.print(fNodeValue[i][CHUNK_SIZE]);
                System.out.print('\t');
                System.out.print(fNodeURI[i][CHUNK_SIZE]);
                System.out.print('\t');
                System.out.print(fNodeParent[i][CHUNK_SIZE]);
                System.out.print('\t');
                System.out.print(fNodeLastChild[i][CHUNK_SIZE]);
                System.out.print('\t');
                System.out.print(fNodePrevSib[i][CHUNK_SIZE]);
                System.out.print('\t');
                System.out.print(fNodeExtra[i][CHUNK_SIZE]);
                System.out.println();
            }
        }
    }
    if (DEBUG_PRINT_TABLES) {
        // This assumes that the document is small
        System.out.println("# start table");
        for (int i = 0; i < fNodeCount; i++) {
            int chunk = i >> CHUNK_SHIFT;
            int index = i & CHUNK_MASK;
            if (i % 10 == 0) {
                System.out.print("num\t");
                System.out.print("type\t");
                System.out.print("name\t");
                System.out.print("val\t");
                System.out.print("uri\t");
                System.out.print("par\t");
                System.out.print("lch\t");
                System.out.print("psib\t");
                System.out.print("xtra");
                System.out.println();
            }
            System.out.print(i);
            System.out.print('\t');
            switch(getChunkIndex(fNodeType, chunk, index)) {
                case DocumentImpl.ELEMENT_DEFINITION_NODE:
                    {
                        System.out.print("EDef");
                        break;
                    }
                case Node.DOCUMENT_NODE:
                    {
                        System.out.print("Doc");
                        break;
                    }
                case Node.DOCUMENT_TYPE_NODE:
                    {
                        System.out.print("DType");
                        break;
                    }
                case Node.COMMENT_NODE:
                    {
                        System.out.print("Com");
                        break;
                    }
                case Node.PROCESSING_INSTRUCTION_NODE:
                    {
                        System.out.print("PI");
                        break;
                    }
                case Node.ELEMENT_NODE:
                    {
                        System.out.print("Elem");
                        break;
                    }
                case Node.ENTITY_NODE:
                    {
                        System.out.print("Ent");
                        break;
                    }
                case Node.ENTITY_REFERENCE_NODE:
                    {
                        System.out.print("ERef");
                        break;
                    }
                case Node.TEXT_NODE:
                    {
                        System.out.print("Text");
                        break;
                    }
                case Node.ATTRIBUTE_NODE:
                    {
                        System.out.print("Attr");
                        break;
                    }
                case DeferredNode.TYPE_NODE:
                    {
                        System.out.print("TypeInfo");
                        break;
                    }
                default:
                    {
                        System.out.print("?" + getChunkIndex(fNodeType, chunk, index));
                    }
            }
            System.out.print('\t');
            System.out.print(getChunkValue(fNodeName, chunk, index));
            System.out.print('\t');
            System.out.print(getNodeValue(chunk, index));
            System.out.print('\t');
            System.out.print(getChunkValue(fNodeURI, chunk, index));
            System.out.print('\t');
            System.out.print(getChunkIndex(fNodeParent, chunk, index));
            System.out.print('\t');
            System.out.print(getChunkIndex(fNodeLastChild, chunk, index));
            System.out.print('\t');
            System.out.print(getChunkIndex(fNodePrevSib, chunk, index));
            System.out.print('\t');
            System.out.print(getChunkIndex(fNodeExtra, chunk, index));
            System.out.println();
        }
        System.out.println("# end table");
    }
}
	// putIdentifier(String,int)
//
// DEBUG
//
/** Prints out the tables. */
public void print() {
    if (DEBUG_PRINT_REF_COUNTS) {
        System.out.print("num\t");
        System.out.print("type\t");
        System.out.print("name\t");
        System.out.print("val\t");
        System.out.print("par\t");
        System.out.print("lch\t");
        System.out.print("psib");
        System.out.println();
        for (int i = 0; i < fNodeType.length; i++) {
            if (fNodeType[i] != null) {
                // separator
                System.out.print("--------");
                System.out.print("--------");
                System.out.print("--------");
                System.out.print("--------");
                System.out.print("--------");
                System.out.print("--------");
                System.out.print("--------");
                System.out.println();
                // ref count
                System.out.print(i);
                System.out.print('\t');
                switch(fNodeType[i][CHUNK_SIZE]) {
                    case DocumentImpl.ELEMENT_DEFINITION_NODE:
                        {
                            System.out.print("EDef");
                            break;
                        }
                    case Node.DOCUMENT_NODE:
                        {
                            System.out.print("Doc");
                            break;
                        }
                    case Node.DOCUMENT_TYPE_NODE:
                        {
                            System.out.print("DType");
                            break;
                        }
                    case Node.COMMENT_NODE:
                        {
                            System.out.print("Com");
                            break;
                        }
                    case Node.PROCESSING_INSTRUCTION_NODE:
                        {
                            System.out.print("PI");
                            break;
                        }
                    case Node.ELEMENT_NODE:
                        {
                            System.out.print("Elem");
                            break;
                        }
                    case Node.ENTITY_NODE:
                        {
                            System.out.print("Ent");
                            break;
                        }
                    case Node.ENTITY_REFERENCE_NODE:
                        {
                            System.out.print("ERef");
                            break;
                        }
                    case Node.TEXT_NODE:
                        {
                            System.out.print("Text");
                            break;
                        }
                    case Node.ATTRIBUTE_NODE:
                        {
                            System.out.print("Attr");
                            break;
                        }
                    case DeferredNode.TYPE_NODE:
                        {
                            System.out.print("TypeInfo");
                            break;
                        }
                    default:
                        {
                            System.out.print("?" + fNodeType[i][CHUNK_SIZE]);
                        }
                }
                System.out.print('\t');
                System.out.print(fNodeName[i][CHUNK_SIZE]);
                System.out.print('\t');
                System.out.print(fNodeValue[i][CHUNK_SIZE]);
                System.out.print('\t');
                System.out.print(fNodeURI[i][CHUNK_SIZE]);
                System.out.print('\t');
                System.out.print(fNodeParent[i][CHUNK_SIZE]);
                System.out.print('\t');
                System.out.print(fNodeLastChild[i][CHUNK_SIZE]);
                System.out.print('\t');
                System.out.print(fNodePrevSib[i][CHUNK_SIZE]);
                System.out.print('\t');
                System.out.print(fNodeExtra[i][CHUNK_SIZE]);
                System.out.println();
            }
        }
    }
    if (DEBUG_PRINT_TABLES) {
        // This assumes that the document is small
        System.out.println("# start table");
        for (int i = 0; i < fNodeCount; i++) {
            int chunk = i >> CHUNK_SHIFT;
            int index = i & CHUNK_MASK;
            if (i % 10 == 0) {
                System.out.print("num\t");
                System.out.print("type\t");
                System.out.print("name\t");
                System.out.print("val\t");
                System.out.print("uri\t");
                System.out.print("par\t");
                System.out.print("lch\t");
                System.out.print("psib\t");
                System.out.print("xtra");
                System.out.println();
            }
            System.out.print(i);
            System.out.print('\t');
            switch(getChunkIndex(fNodeType, chunk, index)) {
                case DocumentImpl.ELEMENT_DEFINITION_NODE:
                    {
                        System.out.print("EDef");
                        break;
                    }
                case Node.DOCUMENT_NODE:
                    {
                        System.out.print("Doc");
                        break;
                    }
                case Node.DOCUMENT_TYPE_NODE:
                    {
                        System.out.print("DType");
                        break;
                    }
                case Node.COMMENT_NODE:
                    {
                        System.out.print("Com");
                        break;
                    }
                case Node.PROCESSING_INSTRUCTION_NODE:
                    {
                        System.out.print("PI");
                        break;
                    }
                case Node.ELEMENT_NODE:
                    {
                        System.out.print("Elem");
                        break;
                    }
                case Node.ENTITY_NODE:
                    {
                        System.out.print("Ent");
                        break;
                    }
                case Node.ENTITY_REFERENCE_NODE:
                    {
                        System.out.print("ERef");
                        break;
                    }
                case Node.TEXT_NODE:
                    {
                        System.out.print("Text");
                        break;
                    }
                case Node.ATTRIBUTE_NODE:
                    {
                        System.out.print("Attr");
                        break;
                    }
                case DeferredNode.TYPE_NODE:
                    {
                        System.out.print("TypeInfo");
                        break;
                    }
                default:
                    {
                        System.out.print("?" + getChunkIndex(fNodeType, chunk, index));
                    }
            }
            System.out.print('\t');
            System.out.print(getChunkValue(fNodeName, chunk, index));
            System.out.print('\t');
            System.out.print(getNodeValue(chunk, index));
            System.out.print('\t');
            System.out.print(getChunkValue(fNodeURI, chunk, index));
            System.out.print('\t');
            System.out.print(getChunkIndex(fNodeParent, chunk, index));
            System.out.print('\t');
            System.out.print(getChunkIndex(fNodeLastChild, chunk, index));
            System.out.print('\t');
            System.out.print(getChunkIndex(fNodePrevSib, chunk, index));
            System.out.print('\t');
            System.out.print(getChunkIndex(fNodeExtra, chunk, index));
            System.out.println();
        }
        System.out.println("# end table");
    }
}
	/** Returns the element at the specified index. */
public int elementAt(int index) {
    return data[index];
}
	// internal factory methods
/** Creates a document node in the table. */
public int createDeferredDocument() {
    int nodeIndex = createNode(Node.DOCUMENT_NODE);
    return nodeIndex;
}
	/** Creates a doctype. */
public int createDeferredDocumentType(String rootElementName, String publicId, String systemId) {
    // create node
    int nodeIndex = createNode(Node.DOCUMENT_TYPE_NODE);
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    // save name, public id, system id
    setChunkValue(fNodeName, rootElementName, chunk, index);
    setChunkValue(fNodeValue, publicId, chunk, index);
    setChunkValue(fNodeURI, systemId, chunk, index);
    // return node index
    return nodeIndex;
}
	/** Creates an entity reference node in the table. */
public int createDeferredEntityReference(String name, String baseURI) {
    // create node
    int nodeIndex = createNode(Node.ENTITY_REFERENCE_NODE);
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    setChunkValue(fNodeName, name, chunk, index);
    setChunkValue(fNodeValue, baseURI, chunk, index);
    // return node index
    return nodeIndex;
}
	// setDeferredAttribute(int,String,String,String,boolean):int
/** Creates an attribute in the table. */
public int createDeferredAttribute(String attrName, String attrValue, boolean specified) {
    return createDeferredAttribute(attrName, null, attrValue, specified);
}
	// setDeferredAttribute(int,String,String,String,boolean):int
/** Creates an attribute in the table. */
public int createDeferredAttribute(String attrName, String attrValue, boolean specified) {
    return createDeferredAttribute(attrName, null, attrValue, specified);
}
	/** Creates an attribute with a URI in the table. */
public int createDeferredAttribute(String attrName, String attrURI, String attrValue, boolean specified) {
    // create node
    int nodeIndex = createNode(NodeImpl.ATTRIBUTE_NODE);
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    setChunkValue(fNodeName, attrName, chunk, index);
    setChunkValue(fNodeURI, attrURI, chunk, index);
    setChunkValue(fNodeValue, attrValue, chunk, index);
    int extra = specified ? SPECIFIED : 0;
    setChunkIndex(fNodeExtra, extra, chunk, index);
    // return node index
    return nodeIndex;
}
	/** Creates an attribute with a URI in the table. */
public int createDeferredAttribute(String attrName, String attrURI, String attrValue, boolean specified) {
    // create node
    int nodeIndex = createNode(NodeImpl.ATTRIBUTE_NODE);
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    setChunkValue(fNodeName, attrName, chunk, index);
    setChunkValue(fNodeURI, attrURI, chunk, index);
    setChunkValue(fNodeValue, attrValue, chunk, index);
    int extra = specified ? SPECIFIED : 0;
    setChunkIndex(fNodeExtra, extra, chunk, index);
    // return node index
    return nodeIndex;
}
	// createDeferredElementDefinition(String):int
/** Creates a text node in the table. */
public int createDeferredTextNode(String data, boolean ignorableWhitespace) {
    // create node
    int nodeIndex = createNode(Node.TEXT_NODE);
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    setChunkValue(fNodeValue, data, chunk, index);
    // use extra to store ignorableWhitespace info
    setChunkIndex(fNodeExtra, ignorableWhitespace ? 1 : 0, chunk, index);
    // return node index
    return nodeIndex;
}
	// createDeferredTextNode(String,boolean):int
/** Creates a CDATA section node in the table. */
public int createDeferredCDATASection(String data) {
    // create node
    int nodeIndex = createNode(Node.CDATA_SECTION_NODE);
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    setChunkValue(fNodeValue, data, chunk, index);
    // return node index
    return nodeIndex;
}
	// createDeferredCDATASection(String):int
/** Creates a processing instruction node in the table. */
public int createDeferredProcessingInstruction(String target, String data) {
    // create node
    int nodeIndex = createNode(Node.PROCESSING_INSTRUCTION_NODE);
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    setChunkValue(fNodeName, target, chunk, index);
    setChunkValue(fNodeValue, data, chunk, index);
    // return node index
    return nodeIndex;
}
	// createDeferredProcessingInstruction(String,String):int
/** Creates a comment node in the table. */
public int createDeferredComment(String data) {
    // create node
    int nodeIndex = createNode(Node.COMMENT_NODE);
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    setChunkValue(fNodeValue, data, chunk, index);
    // return node index
    return nodeIndex;
}
	// createDeferredComment(String):int
/** Creates a clone of the specified node. */
public int cloneNode(int nodeIndex, boolean deep) {
    // clone immediate node
    int nchunk = nodeIndex >> CHUNK_SHIFT;
    int nindex = nodeIndex & CHUNK_MASK;
    int nodeType = fNodeType[nchunk][nindex];
    int cloneIndex = createNode((short) nodeType);
    int cchunk = cloneIndex >> CHUNK_SHIFT;
    int cindex = cloneIndex & CHUNK_MASK;
    setChunkValue(fNodeName, fNodeName[nchunk][nindex], cchunk, cindex);
    setChunkValue(fNodeValue, fNodeValue[nchunk][nindex], cchunk, cindex);
    setChunkValue(fNodeURI, fNodeURI[nchunk][nindex], cchunk, cindex);
    int extraIndex = fNodeExtra[nchunk][nindex];
    if (extraIndex != -1) {
        if (nodeType != Node.ATTRIBUTE_NODE && nodeType != Node.TEXT_NODE) {
            extraIndex = cloneNode(extraIndex, false);
        }
        setChunkIndex(fNodeExtra, extraIndex, cchunk, cindex);
    }
    // clone and attach children
    if (deep) {
        int prevIndex = -1;
        int childIndex = getLastChild(nodeIndex, false);
        while (childIndex != -1) {
            int clonedChildIndex = cloneNode(childIndex, deep);
            insertBefore(cloneIndex, clonedChildIndex, prevIndex);
            prevIndex = clonedChildIndex;
            childIndex = getRealPrevSibling(childIndex, false);
        }
    }
    // return cloned node index
    return cloneIndex;
}
	/** Inserts a child before the specified node in the table. */
public int insertBefore(int parentIndex, int newChildIndex, int refChildIndex) {
    if (refChildIndex == -1) {
        appendChild(parentIndex, newChildIndex);
        return newChildIndex;
    }
    int nchunk = newChildIndex >> CHUNK_SHIFT;
    int nindex = newChildIndex & CHUNK_MASK;
    int rchunk = refChildIndex >> CHUNK_SHIFT;
    int rindex = refChildIndex & CHUNK_MASK;
    int previousIndex = getChunkIndex(fNodePrevSib, rchunk, rindex);
    setChunkIndex(fNodePrevSib, newChildIndex, rchunk, rindex);
    setChunkIndex(fNodePrevSib, previousIndex, nchunk, nindex);
    return newChildIndex;
}
	// getReadPrevSibling(int,boolean):int
/**
     * Returns the index of the element definition in the table
     * with the specified name index, or -1 if no such definition
     * exists.
     */
public int lookupElementDefinition(String elementName) {
    if (fNodeCount > 1) {
        // find doctype
        int docTypeIndex = -1;
        int nchunk = 0;
        int nindex = 0;
        for (int index = getChunkIndex(fNodeLastChild, nchunk, nindex); index != -1; index = getChunkIndex(fNodePrevSib, nchunk, nindex)) {
            nchunk = index >> CHUNK_SHIFT;
            nindex = index & CHUNK_MASK;
            if (getChunkIndex(fNodeType, nchunk, nindex) == Node.DOCUMENT_TYPE_NODE) {
                docTypeIndex = index;
                break;
            }
        }
        // find element definition
        if (docTypeIndex == -1) {
            return -1;
        }
        nchunk = docTypeIndex >> CHUNK_SHIFT;
        nindex = docTypeIndex & CHUNK_MASK;
        for (int index = getChunkIndex(fNodeLastChild, nchunk, nindex); index != -1; index = getChunkIndex(fNodePrevSib, nchunk, nindex)) {
            nchunk = index >> CHUNK_SHIFT;
            nindex = index & CHUNK_MASK;
            if (getChunkIndex(fNodeType, nchunk, nindex) == NodeImpl.ELEMENT_DEFINITION_NODE && getChunkValue(fNodeName, nchunk, nindex) == elementName) {
                return index;
            }
        }
    }
    return -1;
}
	// getNodeValueString(int):String
/**
     * Returns the real value of the given node.
     * @param free True to free the string index.
     */
public String getNodeValueString(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return null;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    String value = free ? clearChunkValue(fNodeValue, chunk, index) : getChunkValue(fNodeValue, chunk, index);
    if (value == null) {
        return null;
    }
    int type = getChunkIndex(fNodeType, chunk, index);
    if (type == Node.TEXT_NODE) {
        int prevSib = getRealPrevSibling(nodeIndex);
        if (prevSib != -1 && getNodeType(prevSib, false) == Node.TEXT_NODE) {
            // append data that is stored in fNodeValue
            // REVISIT: for text nodes it works differently than for CDATA
            //          nodes.
            fStrChunks.addElement(value);
            do {
                // go in reverse order: find last child, then
                // its previous sibling, etc
                chunk = prevSib >> CHUNK_SHIFT;
                index = prevSib & CHUNK_MASK;
                value = getChunkValue(fNodeValue, chunk, index);
                fStrChunks.addElement(value);
                prevSib = getChunkIndex(fNodePrevSib, chunk, index);
                if (prevSib == -1) {
                    break;
                }
            } while (getNodeType(prevSib, false) == Node.TEXT_NODE);
            int chunkCount = fStrChunks.size();
            // add to the buffer in the correct order.
            for (int i = chunkCount - 1; i >= 0; i--) {
                fBufferStr.append((String) fStrChunks.elementAt(i));
            }
            value = fBufferStr.toString();
            fStrChunks.removeAllElements();
            fBufferStr.setLength(0);
            return value;
        }
    } else if (type == Node.CDATA_SECTION_NODE) {
        // find if any other data stored in children
        int child = getLastChild(nodeIndex, false);
        if (child != -1) {
            // append data that is stored in fNodeValue
            fBufferStr.append(value);
            while (child != -1) {
                // go in reverse order: find last child, then
                // its previous sibling, etc
                chunk = child >> CHUNK_SHIFT;
                index = child & CHUNK_MASK;
                value = getChunkValue(fNodeValue, chunk, index);
                fStrChunks.addElement(value);
                child = getChunkIndex(fNodePrevSib, chunk, index);
            }
            // add to the buffer in the correct order.
            for (int i = fStrChunks.size() - 1; i >= 0; i--) {
                fBufferStr.append((String) fStrChunks.elementAt(i));
            }
            value = fBufferStr.toString();
            fStrChunks.setSize(0);
            fBufferStr.setLength(0);
            return value;
        }
    }
    return value;
}
	// getNodeValueString(int):String
/**
     * Returns the real value of the given node.
     * @param free True to free the string index.
     */
public String getNodeValueString(int nodeIndex, boolean free) {
    if (nodeIndex == -1) {
        return null;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    String value = free ? clearChunkValue(fNodeValue, chunk, index) : getChunkValue(fNodeValue, chunk, index);
    if (value == null) {
        return null;
    }
    int type = getChunkIndex(fNodeType, chunk, index);
    if (type == Node.TEXT_NODE) {
        int prevSib = getRealPrevSibling(nodeIndex);
        if (prevSib != -1 && getNodeType(prevSib, false) == Node.TEXT_NODE) {
            // append data that is stored in fNodeValue
            // REVISIT: for text nodes it works differently than for CDATA
            //          nodes.
            fStrChunks.addElement(value);
            do {
                // go in reverse order: find last child, then
                // its previous sibling, etc
                chunk = prevSib >> CHUNK_SHIFT;
                index = prevSib & CHUNK_MASK;
                value = getChunkValue(fNodeValue, chunk, index);
                fStrChunks.addElement(value);
                prevSib = getChunkIndex(fNodePrevSib, chunk, index);
                if (prevSib == -1) {
                    break;
                }
            } while (getNodeType(prevSib, false) == Node.TEXT_NODE);
            int chunkCount = fStrChunks.size();
            // add to the buffer in the correct order.
            for (int i = chunkCount - 1; i >= 0; i--) {
                fBufferStr.append((String) fStrChunks.elementAt(i));
            }
            value = fBufferStr.toString();
            fStrChunks.removeAllElements();
            fBufferStr.setLength(0);
            return value;
        }
    } else if (type == Node.CDATA_SECTION_NODE) {
        // find if any other data stored in children
        int child = getLastChild(nodeIndex, false);
        if (child != -1) {
            // append data that is stored in fNodeValue
            fBufferStr.append(value);
            while (child != -1) {
                // go in reverse order: find last child, then
                // its previous sibling, etc
                chunk = child >> CHUNK_SHIFT;
                index = child & CHUNK_MASK;
                value = getChunkValue(fNodeValue, chunk, index);
                fStrChunks.addElement(value);
                child = getChunkIndex(fNodePrevSib, chunk, index);
            }
            // add to the buffer in the correct order.
            for (int i = fStrChunks.size() - 1; i >= 0; i--) {
                fBufferStr.append((String) fStrChunks.elementAt(i));
            }
            value = fBufferStr.toString();
            fStrChunks.setSize(0);
            fBufferStr.setLength(0);
            return value;
        }
    }
    return value;
}
}