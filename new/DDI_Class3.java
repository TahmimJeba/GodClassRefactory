public class Class3 {
	private Object NOTATION_NODE;
	private Object ENTITY_REFERENCE_NODE;
	private Object nextSibling;
	private Object firstChild;
	private Object previousSibling;
	private Object ELEMENT_DEFINITION_NODE;
	private Object CDATA_SECTION_NODE;
	private Object DOCUMENT_NODE;
	private Object ENTITY_NODE;
	private Object ELEMENT_NODE;
	private Object DOCUMENT_TYPE_NODE;
	private Object ATTRIBUTE_NODE;
	private Object COMMENT_NODE;
	private Object out;
	private Object TEXT_NODE;
	private Object value;
	private Object ownerNode;
	private Object PROCESSING_INSTRUCTION_NODE;

	public Class3(Object NOTATION_NODE, Object ENTITY_REFERENCE_NODE, Object nextSibling, Object firstChild, Object previousSibling, Object ELEMENT_DEFINITION_NODE, Object CDATA_SECTION_NODE, Object DOCUMENT_NODE, Object ENTITY_NODE, Object ELEMENT_NODE, Object DOCUMENT_TYPE_NODE, Object ATTRIBUTE_NODE, Object COMMENT_NODE, Object out, Object TEXT_NODE, Object value, Object ownerNode, Object PROCESSING_INSTRUCTION_NODE){
		this.NOTATION_NODE = NOTATION_NODE;
		this.ENTITY_REFERENCE_NODE = ENTITY_REFERENCE_NODE;
		this.nextSibling = nextSibling;
		this.firstChild = firstChild;
		this.previousSibling = previousSibling;
		this.ELEMENT_DEFINITION_NODE = ELEMENT_DEFINITION_NODE;
		this.CDATA_SECTION_NODE = CDATA_SECTION_NODE;
		this.DOCUMENT_NODE = DOCUMENT_NODE;
		this.ENTITY_NODE = ENTITY_NODE;
		this.ELEMENT_NODE = ELEMENT_NODE;
		this.DOCUMENT_TYPE_NODE = DOCUMENT_TYPE_NODE;
		this.ATTRIBUTE_NODE = ATTRIBUTE_NODE;
		this.COMMENT_NODE = COMMENT_NODE;
		this.out = out;
		this.TEXT_NODE = TEXT_NODE;
		this.value = value;
		this.ownerNode = ownerNode;
		this.PROCESSING_INSTRUCTION_NODE = PROCESSING_INSTRUCTION_NODE;
	}
	// lookupElementDefinition(String):int
/** Instantiates the requested node object. */
public DeferredNode getNodeObject(int nodeIndex) {
    // is there anything to do?
    if (nodeIndex == -1) {
        return null;
    }
    // get node type
    int chunk = nodeIndex >> CHUNK_SHIFT;
    int index = nodeIndex & CHUNK_MASK;
    int type = getChunkIndex(fNodeType, chunk, index);
    if (type != Node.TEXT_NODE && type != Node.CDATA_SECTION_NODE) {
        clearChunkIndex(fNodeType, chunk, index);
    }
    // create new node
    DeferredNode node = null;
    switch(type) {
        case Node.ATTRIBUTE_NODE:
            {
                if (fNamespacesEnabled) {
                    node = new DeferredAttrNSImpl(this, nodeIndex);
                } else {
                    node = new DeferredAttrImpl(this, nodeIndex);
                }
                break;
            }
        case Node.CDATA_SECTION_NODE:
            {
                node = new DeferredCDATASectionImpl(this, nodeIndex);
                break;
            }
        case Node.COMMENT_NODE:
            {
                node = new DeferredCommentImpl(this, nodeIndex);
                break;
            }
        // case Node.DOCUMENT_FRAGMENT_NODE: { break; }
        case Node.DOCUMENT_NODE:
            {
                // this node is never "fast"
                node = this;
                break;
            }
        case Node.DOCUMENT_TYPE_NODE:
            {
                node = new DeferredDocumentTypeImpl(this, nodeIndex);
                // save the doctype node
                docType = (DocumentTypeImpl) node;
                break;
            }
        case Node.ELEMENT_NODE:
            {
                if (DEBUG_IDS) {
                    System.out.println("getNodeObject(ELEMENT_NODE): " + nodeIndex);
                }
                // create node
                if (fNamespacesEnabled) {
                    node = new DeferredElementNSImpl(this, nodeIndex);
                } else {
                    node = new DeferredElementImpl(this, nodeIndex);
                }
                // save the document element node
                if (docElement == null) {
                    docElement = (ElementImpl) node;
                }
                // registered for its ID attributes
                if (fIdElement != null) {
                    int idIndex = binarySearch(fIdElement, 0, fIdCount - 1, nodeIndex);
                    while (idIndex != -1) {
                        if (DEBUG_IDS) {
                            System.out.println("  id index: " + idIndex);
                            System.out.println("  fIdName[" + idIndex + "]: " + fIdName[idIndex]);
                        }
                        // register ID
                        String name = fIdName[idIndex];
                        if (name != null) {
                            if (DEBUG_IDS) {
                                System.out.println("  name: " + name);
                                System.out.print("getNodeObject()#");
                            }
                            putIdentifier0(name, (Element) node);
                            fIdName[idIndex] = null;
                        }
                        // this element
                        if (idIndex + 1 < fIdCount && fIdElement[idIndex + 1] == nodeIndex) {
                            idIndex++;
                        } else {
                            idIndex = -1;
                        }
                    }
                }
                break;
            }
        case Node.ENTITY_NODE:
            {
                node = new DeferredEntityImpl(this, nodeIndex);
                break;
            }
        case Node.ENTITY_REFERENCE_NODE:
            {
                node = new DeferredEntityReferenceImpl(this, nodeIndex);
                break;
            }
        case Node.NOTATION_NODE:
            {
                node = new DeferredNotationImpl(this, nodeIndex);
                break;
            }
        case Node.PROCESSING_INSTRUCTION_NODE:
            {
                node = new DeferredProcessingInstructionImpl(this, nodeIndex);
                break;
            }
        case Node.TEXT_NODE:
            {
                node = new DeferredTextImpl(this, nodeIndex);
                break;
            }
        case NodeImpl.ELEMENT_DEFINITION_NODE:
            {
                node = new DeferredElementDefinitionImpl(this, nodeIndex);
                break;
            }
        default:
            {
                throw new IllegalArgumentException("type: " + type);
            }
    }
    // store and return
    if (node != null) {
        return node;
    }
    // error
    throw new IllegalArgumentException();
}
	// synchronizeData()
/**
     * Synchronizes the node's children with the internal structure.
     * Fluffing the children at once solves a lot of work to keep
     * the two structures in sync. The problem gets worse when
     * editing the tree -- this makes it a lot easier.
     */
protected void synchronizeChildren() {
    if (needsSyncData()) {
        synchronizeData();
        /*
             * when we have elements with IDs this method is being recursively
             * called from synchronizeData, in which case we've already gone
             * through the following and we can now simply stop here.
             */
        if (!needsSyncChildren()) {
            return;
        }
    }
    // we don't want to generate any event for this so turn them off
    boolean orig = mutationEvents;
    mutationEvents = false;
    // no need to sync in the future
    needsSyncChildren(false);
    getNodeType(0);
    // create children and link them as siblings
    ChildNode first = null;
    ChildNode last = null;
    for (int index = getLastChild(0); index != -1; index = getPrevSibling(index)) {
        ChildNode node = (ChildNode) getNodeObject(index);
        if (last == null) {
            last = node;
        } else {
            first.previousSibling = node;
        }
        node.ownerNode = this;
        node.isOwned(true);
        node.nextSibling = first;
        first = node;
        // save doctype and document type
        int type = node.getNodeType();
        if (type == Node.ELEMENT_NODE) {
            docElement = (ElementImpl) node;
        } else if (type == Node.DOCUMENT_TYPE_NODE) {
            docType = (DocumentTypeImpl) node;
        }
    }
    if (first != null) {
        firstChild = first;
        first.isFirstChild(true);
        lastChild(last);
    }
    // set mutation events flag back to its original value
    mutationEvents = orig;
}
	// synchronizeData()
/**
     * Synchronizes the node's children with the internal structure.
     * Fluffing the children at once solves a lot of work to keep
     * the two structures in sync. The problem gets worse when
     * editing the tree -- this makes it a lot easier.
     */
protected void synchronizeChildren() {
    if (needsSyncData()) {
        synchronizeData();
        /*
             * when we have elements with IDs this method is being recursively
             * called from synchronizeData, in which case we've already gone
             * through the following and we can now simply stop here.
             */
        if (!needsSyncChildren()) {
            return;
        }
    }
    // we don't want to generate any event for this so turn them off
    boolean orig = mutationEvents;
    mutationEvents = false;
    // no need to sync in the future
    needsSyncChildren(false);
    getNodeType(0);
    // create children and link them as siblings
    ChildNode first = null;
    ChildNode last = null;
    for (int index = getLastChild(0); index != -1; index = getPrevSibling(index)) {
        ChildNode node = (ChildNode) getNodeObject(index);
        if (last == null) {
            last = node;
        } else {
            first.previousSibling = node;
        }
        node.ownerNode = this;
        node.isOwned(true);
        node.nextSibling = first;
        first = node;
        // save doctype and document type
        int type = node.getNodeType();
        if (type == Node.ELEMENT_NODE) {
            docElement = (ElementImpl) node;
        } else if (type == Node.DOCUMENT_TYPE_NODE) {
            docType = (DocumentTypeImpl) node;
        }
    }
    if (first != null) {
        firstChild = first;
        first.isFirstChild(true);
        lastChild(last);
    }
    // set mutation events flag back to its original value
    mutationEvents = orig;
}
	// synchronizeData()
/**
     * Synchronizes the node's children with the internal structure.
     * Fluffing the children at once solves a lot of work to keep
     * the two structures in sync. The problem gets worse when
     * editing the tree -- this makes it a lot easier.
     */
protected void synchronizeChildren() {
    if (needsSyncData()) {
        synchronizeData();
        /*
             * when we have elements with IDs this method is being recursively
             * called from synchronizeData, in which case we've already gone
             * through the following and we can now simply stop here.
             */
        if (!needsSyncChildren()) {
            return;
        }
    }
    // we don't want to generate any event for this so turn them off
    boolean orig = mutationEvents;
    mutationEvents = false;
    // no need to sync in the future
    needsSyncChildren(false);
    getNodeType(0);
    // create children and link them as siblings
    ChildNode first = null;
    ChildNode last = null;
    for (int index = getLastChild(0); index != -1; index = getPrevSibling(index)) {
        ChildNode node = (ChildNode) getNodeObject(index);
        if (last == null) {
            last = node;
        } else {
            first.previousSibling = node;
        }
        node.ownerNode = this;
        node.isOwned(true);
        node.nextSibling = first;
        first = node;
        // save doctype and document type
        int type = node.getNodeType();
        if (type == Node.ELEMENT_NODE) {
            docElement = (ElementImpl) node;
        } else if (type == Node.DOCUMENT_TYPE_NODE) {
            docType = (DocumentTypeImpl) node;
        }
    }
    if (first != null) {
        firstChild = first;
        first.isFirstChild(true);
        lastChild(last);
    }
    // set mutation events flag back to its original value
    mutationEvents = orig;
}
	// synchronizeChildren()
/**
     * Synchronizes the node's children with the internal structure.
     * Fluffing the children at once solves a lot of work to keep
     * the two structures in sync. The problem gets worse when
     * editing the tree -- this makes it a lot easier.
     * This is not directly used in this class but this method is
     * here so that it can be shared by all deferred subclasses of AttrImpl.
     */
protected final void synchronizeChildren(AttrImpl a, int nodeIndex) {
    // we don't want to generate any event for this so turn them off
    boolean orig = getMutationEvents();
    setMutationEvents(false);
    // no need to sync in the future
    a.needsSyncChildren(false);
    // create children and link them as siblings or simply store the value
    // as a String if all we have is one piece of text
    int last = getLastChild(nodeIndex);
    int prev = getPrevSibling(last);
    if (prev == -1) {
        a.value = getNodeValueString(nodeIndex);
        a.hasStringValue(true);
    } else {
        ChildNode firstNode = null;
        ChildNode lastNode = null;
        for (int index = last; index != -1; index = getPrevSibling(index)) {
            ChildNode node = (ChildNode) getNodeObject(index);
            if (lastNode == null) {
                lastNode = node;
            } else {
                firstNode.previousSibling = node;
            }
            node.ownerNode = a;
            node.isOwned(true);
            node.nextSibling = firstNode;
            firstNode = node;
        }
        if (lastNode != null) {
            // firstChild = firstNode
            a.value = firstNode;
            firstNode.isFirstChild(true);
            a.lastChild(lastNode);
        }
        a.hasStringValue(false);
    }
    // set mutation events flag back to its original value
    setMutationEvents(orig);
}
	// synchronizeChildren()
/**
     * Synchronizes the node's children with the internal structure.
     * Fluffing the children at once solves a lot of work to keep
     * the two structures in sync. The problem gets worse when
     * editing the tree -- this makes it a lot easier.
     * This is not directly used in this class but this method is
     * here so that it can be shared by all deferred subclasses of AttrImpl.
     */
protected final void synchronizeChildren(AttrImpl a, int nodeIndex) {
    // we don't want to generate any event for this so turn them off
    boolean orig = getMutationEvents();
    setMutationEvents(false);
    // no need to sync in the future
    a.needsSyncChildren(false);
    // create children and link them as siblings or simply store the value
    // as a String if all we have is one piece of text
    int last = getLastChild(nodeIndex);
    int prev = getPrevSibling(last);
    if (prev == -1) {
        a.value = getNodeValueString(nodeIndex);
        a.hasStringValue(true);
    } else {
        ChildNode firstNode = null;
        ChildNode lastNode = null;
        for (int index = last; index != -1; index = getPrevSibling(index)) {
            ChildNode node = (ChildNode) getNodeObject(index);
            if (lastNode == null) {
                lastNode = node;
            } else {
                firstNode.previousSibling = node;
            }
            node.ownerNode = a;
            node.isOwned(true);
            node.nextSibling = firstNode;
            firstNode = node;
        }
        if (lastNode != null) {
            // firstChild = firstNode
            a.value = firstNode;
            firstNode.isFirstChild(true);
            a.lastChild(lastNode);
        }
        a.hasStringValue(false);
    }
    // set mutation events flag back to its original value
    setMutationEvents(orig);
}
	// synchronizeChildren()
/**
     * Synchronizes the node's children with the internal structure.
     * Fluffing the children at once solves a lot of work to keep
     * the two structures in sync. The problem gets worse when
     * editing the tree -- this makes it a lot easier.
     * This is not directly used in this class but this method is
     * here so that it can be shared by all deferred subclasses of AttrImpl.
     */
protected final void synchronizeChildren(AttrImpl a, int nodeIndex) {
    // we don't want to generate any event for this so turn them off
    boolean orig = getMutationEvents();
    setMutationEvents(false);
    // no need to sync in the future
    a.needsSyncChildren(false);
    // create children and link them as siblings or simply store the value
    // as a String if all we have is one piece of text
    int last = getLastChild(nodeIndex);
    int prev = getPrevSibling(last);
    if (prev == -1) {
        a.value = getNodeValueString(nodeIndex);
        a.hasStringValue(true);
    } else {
        ChildNode firstNode = null;
        ChildNode lastNode = null;
        for (int index = last; index != -1; index = getPrevSibling(index)) {
            ChildNode node = (ChildNode) getNodeObject(index);
            if (lastNode == null) {
                lastNode = node;
            } else {
                firstNode.previousSibling = node;
            }
            node.ownerNode = a;
            node.isOwned(true);
            node.nextSibling = firstNode;
            firstNode = node;
        }
        if (lastNode != null) {
            // firstChild = firstNode
            a.value = firstNode;
            firstNode.isFirstChild(true);
            a.lastChild(lastNode);
        }
        a.hasStringValue(false);
    }
    // set mutation events flag back to its original value
    setMutationEvents(orig);
}
	// synchronizeChildren(AttrImpl,int):void
/**
     * Synchronizes the node's children with the internal structure.
     * Fluffing the children at once solves a lot of work to keep
     * the two structures in sync. The problem gets worse when
     * editing the tree -- this makes it a lot easier.
     * This is not directly used in this class but this method is
     * here so that it can be shared by all deferred subclasses of ParentNode.
     */
protected final void synchronizeChildren(ParentNode p, int nodeIndex) {
    // we don't want to generate any event for this so turn them off
    boolean orig = getMutationEvents();
    setMutationEvents(false);
    // no need to sync in the future
    p.needsSyncChildren(false);
    // create children and link them as siblings
    ChildNode firstNode = null;
    ChildNode lastNode = null;
    for (int index = getLastChild(nodeIndex); index != -1; index = getPrevSibling(index)) {
        ChildNode node = (ChildNode) getNodeObject(index);
        if (lastNode == null) {
            lastNode = node;
        } else {
            firstNode.previousSibling = node;
        }
        node.ownerNode = p;
        node.isOwned(true);
        node.nextSibling = firstNode;
        firstNode = node;
    }
    if (lastNode != null) {
        p.firstChild = firstNode;
        firstNode.isFirstChild(true);
        p.lastChild(lastNode);
    }
    // set mutation events flag back to its original value
    setMutationEvents(orig);
}
	// synchronizeChildren(AttrImpl,int):void
/**
     * Synchronizes the node's children with the internal structure.
     * Fluffing the children at once solves a lot of work to keep
     * the two structures in sync. The problem gets worse when
     * editing the tree -- this makes it a lot easier.
     * This is not directly used in this class but this method is
     * here so that it can be shared by all deferred subclasses of ParentNode.
     */
protected final void synchronizeChildren(ParentNode p, int nodeIndex) {
    // we don't want to generate any event for this so turn them off
    boolean orig = getMutationEvents();
    setMutationEvents(false);
    // no need to sync in the future
    p.needsSyncChildren(false);
    // create children and link them as siblings
    ChildNode firstNode = null;
    ChildNode lastNode = null;
    for (int index = getLastChild(nodeIndex); index != -1; index = getPrevSibling(index)) {
        ChildNode node = (ChildNode) getNodeObject(index);
        if (lastNode == null) {
            lastNode = node;
        } else {
            firstNode.previousSibling = node;
        }
        node.ownerNode = p;
        node.isOwned(true);
        node.nextSibling = firstNode;
        firstNode = node;
    }
    if (lastNode != null) {
        p.firstChild = firstNode;
        firstNode.isFirstChild(true);
        p.lastChild(lastNode);
    }
    // set mutation events flag back to its original value
    setMutationEvents(orig);
}
	// synchronizeChildren(AttrImpl,int):void
/**
     * Synchronizes the node's children with the internal structure.
     * Fluffing the children at once solves a lot of work to keep
     * the two structures in sync. The problem gets worse when
     * editing the tree -- this makes it a lot easier.
     * This is not directly used in this class but this method is
     * here so that it can be shared by all deferred subclasses of ParentNode.
     */
protected final void synchronizeChildren(ParentNode p, int nodeIndex) {
    // we don't want to generate any event for this so turn them off
    boolean orig = getMutationEvents();
    setMutationEvents(false);
    // no need to sync in the future
    p.needsSyncChildren(false);
    // create children and link them as siblings
    ChildNode firstNode = null;
    ChildNode lastNode = null;
    for (int index = getLastChild(nodeIndex); index != -1; index = getPrevSibling(index)) {
        ChildNode node = (ChildNode) getNodeObject(index);
        if (lastNode == null) {
            lastNode = node;
        } else {
            firstNode.previousSibling = node;
        }
        node.ownerNode = p;
        node.isOwned(true);
        node.nextSibling = firstNode;
        firstNode = node;
    }
    if (lastNode != null) {
        p.firstChild = firstNode;
        firstNode.isFirstChild(true);
        p.lastChild(lastNode);
    }
    // set mutation events flag back to its original value
    setMutationEvents(orig);
}
	// createNode(short):int
/**
     * Performs a binary search for a target value in an array of
     * values. The array of values must be in ascending sorted order
     * before calling this method and all array values must be
     * non-negative.
     *
     * @param values  The array of values to search.
     * @param start   The starting offset of the search.
     * @param end     The ending offset of the search.
     * @param target  The target value.
     *
     * @return This function will return the <i>first</i> occurrence
     *         of the target value, or -1 if the target value cannot
     *         be found.
     */
protected static int binarySearch(final int[] values, int start, int end, int target) {
    if (DEBUG_IDS) {
        System.out.println("binarySearch(), target: " + target);
    }
    // look for target value
    while (start <= end) {
        // is this the one we're looking for?
        int middle = (start + end) / 2;
        int value = values[middle];
        if (DEBUG_IDS) {
            System.out.print("  value: " + value + ", target: " + target + " // ");
            print(values, start, end, middle, target);
        }
        if (value == target) {
            while (middle > 0 && values[middle - 1] == target) {
                middle--;
            }
            if (DEBUG_IDS) {
                System.out.println("FOUND AT " + middle);
            }
            return middle;
        }
        // is this point higher or lower?
        if (value > target) {
            end = middle - 1;
        } else {
            start = middle + 1;
        }
    }
    // not found
    if (DEBUG_IDS) {
        System.out.println("NOT FOUND!");
    }
    return -1;
}
	// putIdentifier0(String,Element)
/** Prints the ID array. */
private static void print(int[] values, int start, int end, int middle, int target) {
    if (DEBUG_IDS) {
        System.out.print(start);
        System.out.print(" [");
        for (int i = start; i < end; i++) {
            if (middle == i) {
                System.out.print("!");
            }
            System.out.print(values[i]);
            if (values[i] == target) {
                System.out.print("*");
            }
            if (i < end - 1) {
                System.out.print(" ");
            }
        }
        System.out.println("] " + end);
    }
}
	// putIdentifier0(String,Element)
/** Prints the ID array. */
private static void print(int[] values, int start, int end, int middle, int target) {
    if (DEBUG_IDS) {
        System.out.print(start);
        System.out.print(" [");
        for (int i = start; i < end; i++) {
            if (middle == i) {
                System.out.print("!");
            }
            System.out.print(values[i]);
            if (values[i] == target) {
                System.out.print("*");
            }
            if (i < end - 1) {
                System.out.print(" ");
            }
        }
        System.out.println("] " + end);
    }
}
}