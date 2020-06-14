public class Class0 {
	private Object NOTATION_NODE;
	private Object ENTITY_NODE;

	public Class0(Object NOTATION_NODE, Object ENTITY_NODE){
		this.NOTATION_NODE = NOTATION_NODE;
		this.ENTITY_NODE = ENTITY_NODE;
	}
	/** Creates a notation in the table. */
public int createDeferredNotation(String notationName, String publicId, String systemId, String baseURI) {
    // create node
    int nodeIndex = createNode(Node.NOTATION_NODE);
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    // create extra data node
    int extraDataIndex = createNode(Node.NOTATION_NODE);
    int echunk = extraDataIndex >> CHUNK_SHIFT;
    int eindex = extraDataIndex & CHUNK_MASK;
    // save name, public id, system id, and notation name
    setChunkValue(fNodeName, notationName, chunk, index);
    setChunkValue(fNodeValue, publicId, chunk, index);
    setChunkValue(fNodeURI, systemId, chunk, index);
    // in extra data node set baseURI value
    setChunkIndex(fNodeExtra, extraDataIndex, chunk, index);
    setChunkValue(fNodeName, baseURI, echunk, eindex);
    // return node index
    return nodeIndex;
}
	// createDeferredNotation(String,String,String):int
/** Creates an entity in the table. */
public int createDeferredEntity(String entityName, String publicId, String systemId, String notationName, String baseURI) {
    // create node
    int nodeIndex = createNode(Node.ENTITY_NODE);
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    // create extra data node
    int extraDataIndex = createNode(Node.ENTITY_NODE);
    int echunk = extraDataIndex >> CHUNK_SHIFT;
    int eindex = extraDataIndex & CHUNK_MASK;
    // save name, public id, system id, and notation name
    setChunkValue(fNodeName, entityName, chunk, index);
    setChunkValue(fNodeValue, publicId, chunk, index);
    setChunkValue(fNodeURI, systemId, chunk, index);
    setChunkIndex(fNodeExtra, extraDataIndex, chunk, index);
    // set other values in the extra chunk
    // notation
    setChunkValue(fNodeName, notationName, echunk, eindex);
    // version  L3
    setChunkValue(fNodeValue, null, echunk, eindex);
    // encoding L3
    setChunkValue(fNodeURI, null, echunk, eindex);
    int extraDataIndex2 = createNode(Node.ENTITY_NODE);
    int echunk2 = extraDataIndex2 >> CHUNK_SHIFT;
    int eindex2 = extraDataIndex2 & CHUNK_MASK;
    setChunkIndex(fNodeExtra, extraDataIndex2, echunk, eindex);
    // baseURI
    setChunkValue(fNodeName, baseURI, echunk2, eindex2);
    // return node index
    return nodeIndex;
}
	/**
     * DOM Internal 
     *
     * An attribute specifying the actual encoding of this document. This is
     * <code>null</code> otherwise.
     * <br> This attribute represents the property [character encoding scheme]
     * defined in .
     */
public void setInputEncoding(int currentEntityDecl, String value) {
    // get first extra data chunk
    int nodeIndex = getNodeExtra(currentEntityDecl, false);
    // get second extra data chunk
    int extraDataIndex = getNodeExtra(nodeIndex, false);
    int echunk = extraDataIndex >> CHUNK_SHIFT;
    int eindex = extraDataIndex & CHUNK_MASK;
    setChunkValue(fNodeValue, value, echunk, eindex);
}
	// <init>(boolean,boolean)
//
// Public methods
//
/**
     * Retrieve information describing the abilities of this particular
     * DOM implementation. Intended to support applications that may be
     * using DOMs retrieved from several different sources, potentially
     * with different underlying representations.
     */
public DOMImplementation getImplementation() {
    // information anyway.
    return DeferredDOMImplementationImpl.getDOMImplementation();
}
	/** Returns the cached parser.getNamespaces() value.*/
boolean getNamespacesEnabled() {
    return fNamespacesEnabled;
}
	// DOM Level 3: setting encoding and version
public void setEntityInfo(int currentEntityDecl, String version, String encoding) {
    int eNodeIndex = getNodeExtra(currentEntityDecl, false);
    if (eNodeIndex != -1) {
        int echunk = eNodeIndex >> CHUNK_SHIFT;
        int eindex = eNodeIndex & CHUNK_MASK;
        setChunkValue(fNodeValue, version, echunk, eindex);
        setChunkValue(fNodeURI, encoding, echunk, eindex);
    }
}
	// ensureCapacity(int,int)
/** Creates a node of the specified type. */
protected int createNode(short nodeType) {
    // ensure tables are large enough
    int chunk = fNodeCount >> CHUNK_SHIFT;
    int index = fNodeCount & CHUNK_MASK;
    ensureCapacity(chunk);
    // initialize node
    setChunkIndex(fNodeType, nodeType, chunk, index);
    // return node index number
    return fNodeCount++;
}
	//
// Public methods
//
/** Returns the length of this vector. */
public int size() {
    return size;
}
}