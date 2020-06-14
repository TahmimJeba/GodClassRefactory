public class Class4 {
	private Object length;
	private Object fCount;

	public Class4(Object length, Object fCount){
		this.length = length;
		this.fCount = fCount;
	}
	/**
	 * Clears the type info that is stored in the fNodeValue array
	 * @param nodeIndex
	 * @return Object - type information for the attribute/element node
	 */
public Object getTypeInfo(int nodeIndex) {
    if (nodeIndex == -1) {
        return null;
    }
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    Object value = fNodeValue[chunk] != null ? fNodeValue[chunk][index] : null;
    if (value != null) {
        fNodeValue[chunk][index] = null;
        RefCount c = (RefCount) fNodeValue[chunk][CHUNK_SIZE];
        c.fCount--;
        if (c.fCount == 0) {
            fNodeValue[chunk] = null;
        }
    }
    return value;
}
	private final String getNodeValue(int chunk, int index) {
    Object data = fNodeValue[chunk][index];
    if (data == null) {
        return null;
    } else if (data instanceof String) {
        return (String) data;
    } else {
        // type information
        return data.toString();
    }
}
	private final String getNodeValue(int chunk, int index) {
    Object data = fNodeValue[chunk][index];
    if (data == null) {
        return null;
    } else if (data instanceof String) {
        return (String) data;
    } else {
        // type information
        return data.toString();
    }
}
	/**
     * Returns the specified value in the given data at the chunk and index.
     */
private final int getChunkIndex(int[][] data, int chunk, int index) {
    return data[chunk] != null ? data[chunk][index] : -1;
}
	private final void createChunk(Object[][] data, int chunk) {
    data[chunk] = new Object[CHUNK_SIZE + 1];
    data[chunk][CHUNK_SIZE] = new RefCount();
}
	/**
     * Sets the specified value in the given of data at the chunk and index.
     *
     * @return Returns the old value.
     */
private final int setChunkIndex(int[][] data, int value, int chunk, int index) {
    if (value == -1) {
        return clearChunkIndex(data, chunk, index);
    }
    int ovalue = data[chunk][index];
    if (ovalue == -1) {
        data[chunk][CHUNK_SIZE]++;
    }
    data[chunk][index] = value;
    return ovalue;
}
	/**
     * Clears the specified value in the given data at the chunk and index.
     * Note that this method will clear the given chunk if the reference
     * count becomes zero.
     *
     * @return Returns the old value.
     */
private final int clearChunkIndex(int[][] data, int chunk, int index) {
    int value = data[chunk] != null ? data[chunk][index] : -1;
    if (value != -1) {
        data[chunk][CHUNK_SIZE]--;
        data[chunk][index] = -1;
        if (data[chunk][CHUNK_SIZE] == 0) {
            data[chunk] = null;
        }
    }
    return value;
}
	/** Creates the specified chunk in the given array of chunks. */
private final void createChunk(int[][] data, int chunk) {
    data[chunk] = new int[CHUNK_SIZE + 1];
    System.arraycopy(INIT_ARRAY, 0, data[chunk], 0, CHUNK_SIZE);
}
	// synchronizeChildren(ParentNode,int):void
// utility methods
/** Ensures that the internal tables are large enough. */
protected void ensureCapacity(int chunk) {
    if (fNodeType == null) {
        // create buffers
        fNodeType = new int[INITIAL_CHUNK_COUNT][];
        fNodeName = new Object[INITIAL_CHUNK_COUNT][];
        fNodeValue = new Object[INITIAL_CHUNK_COUNT][];
        fNodeParent = new int[INITIAL_CHUNK_COUNT][];
        fNodeLastChild = new int[INITIAL_CHUNK_COUNT][];
        fNodePrevSib = new int[INITIAL_CHUNK_COUNT][];
        fNodeURI = new Object[INITIAL_CHUNK_COUNT][];
        fNodeExtra = new int[INITIAL_CHUNK_COUNT][];
    } else if (fNodeType.length <= chunk) {
        // resize the tables
        int newsize = chunk * 2;
        int[][] newArray = new int[newsize][];
        System.arraycopy(fNodeType, 0, newArray, 0, chunk);
        fNodeType = newArray;
        Object[][] newStrArray = new Object[newsize][];
        System.arraycopy(fNodeName, 0, newStrArray, 0, chunk);
        fNodeName = newStrArray;
        newStrArray = new Object[newsize][];
        System.arraycopy(fNodeValue, 0, newStrArray, 0, chunk);
        fNodeValue = newStrArray;
        newArray = new int[newsize][];
        System.arraycopy(fNodeParent, 0, newArray, 0, chunk);
        fNodeParent = newArray;
        newArray = new int[newsize][];
        System.arraycopy(fNodeLastChild, 0, newArray, 0, chunk);
        fNodeLastChild = newArray;
        newArray = new int[newsize][];
        System.arraycopy(fNodePrevSib, 0, newArray, 0, chunk);
        fNodePrevSib = newArray;
        newStrArray = new Object[newsize][];
        System.arraycopy(fNodeURI, 0, newStrArray, 0, chunk);
        fNodeURI = newStrArray;
        newArray = new int[newsize][];
        System.arraycopy(fNodeExtra, 0, newArray, 0, chunk);
        fNodeExtra = newArray;
    } else if (fNodeType[chunk] != null) {
        // Done - there's sufficient capacity
        return;
    }
    // create new chunks
    createChunk(fNodeType, chunk);
    createChunk(fNodeName, chunk);
    createChunk(fNodeValue, chunk);
    createChunk(fNodeParent, chunk);
    createChunk(fNodeLastChild, chunk);
    createChunk(fNodePrevSib, chunk);
    createChunk(fNodeURI, chunk);
    createChunk(fNodeExtra, chunk);
    // Done
    return;
}
	// synchronizeChildren(ParentNode,int):void
// utility methods
/** Ensures that the internal tables are large enough. */
protected void ensureCapacity(int chunk) {
    if (fNodeType == null) {
        // create buffers
        fNodeType = new int[INITIAL_CHUNK_COUNT][];
        fNodeName = new Object[INITIAL_CHUNK_COUNT][];
        fNodeValue = new Object[INITIAL_CHUNK_COUNT][];
        fNodeParent = new int[INITIAL_CHUNK_COUNT][];
        fNodeLastChild = new int[INITIAL_CHUNK_COUNT][];
        fNodePrevSib = new int[INITIAL_CHUNK_COUNT][];
        fNodeURI = new Object[INITIAL_CHUNK_COUNT][];
        fNodeExtra = new int[INITIAL_CHUNK_COUNT][];
    } else if (fNodeType.length <= chunk) {
        // resize the tables
        int newsize = chunk * 2;
        int[][] newArray = new int[newsize][];
        System.arraycopy(fNodeType, 0, newArray, 0, chunk);
        fNodeType = newArray;
        Object[][] newStrArray = new Object[newsize][];
        System.arraycopy(fNodeName, 0, newStrArray, 0, chunk);
        fNodeName = newStrArray;
        newStrArray = new Object[newsize][];
        System.arraycopy(fNodeValue, 0, newStrArray, 0, chunk);
        fNodeValue = newStrArray;
        newArray = new int[newsize][];
        System.arraycopy(fNodeParent, 0, newArray, 0, chunk);
        fNodeParent = newArray;
        newArray = new int[newsize][];
        System.arraycopy(fNodeLastChild, 0, newArray, 0, chunk);
        fNodeLastChild = newArray;
        newArray = new int[newsize][];
        System.arraycopy(fNodePrevSib, 0, newArray, 0, chunk);
        fNodePrevSib = newArray;
        newStrArray = new Object[newsize][];
        System.arraycopy(fNodeURI, 0, newStrArray, 0, chunk);
        fNodeURI = newStrArray;
        newArray = new int[newsize][];
        System.arraycopy(fNodeExtra, 0, newArray, 0, chunk);
        fNodeExtra = newArray;
    } else if (fNodeType[chunk] != null) {
        // Done - there's sufficient capacity
        return;
    }
    // create new chunks
    createChunk(fNodeType, chunk);
    createChunk(fNodeName, chunk);
    createChunk(fNodeValue, chunk);
    createChunk(fNodeParent, chunk);
    createChunk(fNodeLastChild, chunk);
    createChunk(fNodePrevSib, chunk);
    createChunk(fNodeURI, chunk);
    createChunk(fNodeExtra, chunk);
    // Done
    return;
}
	//
// Private methods
//
/** Makes sure that there is enough storage. */
private void ensureCapacity(int newsize) {
    if (data == null) {
        data = new int[newsize + 15];
    } else if (newsize > data.length) {
        int[] newdata = new int[newsize + 15];
        System.arraycopy(data, 0, newdata, 0, data.length);
        data = newdata;
    }
}
	//
// Private methods
//
/** Makes sure that there is enough storage. */
private void ensureCapacity(int newsize) {
    if (data == null) {
        data = new int[newsize + 15];
    } else if (newsize > data.length) {
        int[] newdata = new int[newsize + 15];
        System.arraycopy(data, 0, newdata, 0, data.length);
        data = newdata;
    }
}
}