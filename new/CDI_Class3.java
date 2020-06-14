public class Class3 {
	private Object length;
	private Object fData;
	private Object fHandler;

	public Class3(Object length, Object fData, Object fHandler){
		this.length = length;
		this.fData = fData;
		this.fHandler = fHandler;
	}
	// createAttribute(String):Attr
/**
     * Factory method; creates a CDATASection having this Document as
     * its OwnerDoc.
     *
     * @param data The initial contents of the CDATA
     *
     * @throws DOMException(NOT_SUPPORTED_ERR) for HTML documents. (HTML
     * not yet implemented.)
     */
public CDATASection createCDATASection(String data) throws DOMException {
    return new CDATASectionImpl(this, data);
}
	/**
     * Factory method; creates a Comment having this Document as its
     * OwnerDoc.
     *
     * @param data The initial contents of the Comment. */
public Comment createComment(String data) {
    return new CommentImpl(this, data);
}
	// createProcessingInstruction(String,String):ProcessingInstruction
/**
     * Factory method; creates a Text node having this Document as its
     * OwnerDoc.
     *
     * @param data The initial contents of the Text.
     */
public Text createTextNode(String data) {
    return new TextImpl(this, data);
}
	/**
     * NON-DOM: kept for backward compatibility
     * Store user data related to a given node
     * This is a place where we could use weak references! Indeed, the node
     * here won't be GC'ed as long as some user data is attached to it, since
     * the userData table will have a reference to the node.
     */
protected void setUserData(NodeImpl n, Object data) {
    setUserData(n, "XERCES1DOMUSERDATA", data, null);
}
	/**
     * NON-DOM: kept for backward compatibility
     * Store user data related to a given node
     * This is a place where we could use weak references! Indeed, the node
     * here won't be GC'ed as long as some user data is attached to it, since
     * the userData table will have a reference to the node.
     */
protected void setUserData(NodeImpl n, Object data) {
    setUserData(n, "XERCES1DOMUSERDATA", data, null);
}
	/**
     * Associate an object to a key on this node. The object can later be
     * retrieved from this node by calling <code>getUserData</code> with the
     * same key.
     * @param n The node to associate the object to.
     * @param key The key to associate the object to.
     * @param data The object to associate to the given key, or
     *   <code>null</code> to remove any existing association to that key.
     * @param handler The handler to associate to that key, or
     *   <code>null</code>.
     * @return Returns the <code>DOMObject</code> previously associated to
     *   the given key on this node, or <code>null</code> if there was none.
     * @since DOM Level 3
     *
     * REVISIT: we could use a free list of UserDataRecord here
     */
public Object setUserData(Node n, String key, Object data, UserDataHandler handler) {
    if (data == null) {
        if (userData != null) {
            Hashtable t = (Hashtable) userData.get(n);
            if (t != null) {
                Object o = t.remove(key);
                if (o != null) {
                    UserDataRecord r = (UserDataRecord) o;
                    return r.fData;
                }
            }
        }
        return null;
    } else {
        Hashtable t;
        if (userData == null) {
            userData = new Hashtable();
            t = new Hashtable();
            userData.put(n, t);
        } else {
            t = (Hashtable) userData.get(n);
            if (t == null) {
                t = new Hashtable();
                userData.put(n, t);
            }
        }
        Object o = t.put(key, new UserDataRecord(data, handler));
        if (o != null) {
            UserDataRecord r = (UserDataRecord) o;
            return r.fData;
        }
        return null;
    }
}
	/**
     * Associate an object to a key on this node. The object can later be
     * retrieved from this node by calling <code>getUserData</code> with the
     * same key.
     * @param n The node to associate the object to.
     * @param key The key to associate the object to.
     * @param data The object to associate to the given key, or
     *   <code>null</code> to remove any existing association to that key.
     * @param handler The handler to associate to that key, or
     *   <code>null</code>.
     * @return Returns the <code>DOMObject</code> previously associated to
     *   the given key on this node, or <code>null</code> if there was none.
     * @since DOM Level 3
     *
     * REVISIT: we could use a free list of UserDataRecord here
     */
public Object setUserData(Node n, String key, Object data, UserDataHandler handler) {
    if (data == null) {
        if (userData != null) {
            Hashtable t = (Hashtable) userData.get(n);
            if (t != null) {
                Object o = t.remove(key);
                if (o != null) {
                    UserDataRecord r = (UserDataRecord) o;
                    return r.fData;
                }
            }
        }
        return null;
    } else {
        Hashtable t;
        if (userData == null) {
            userData = new Hashtable();
            t = new Hashtable();
            userData.put(n, t);
        } else {
            t = (Hashtable) userData.get(n);
            if (t == null) {
                t = new Hashtable();
                userData.put(n, t);
            }
        }
        Object o = t.put(key, new UserDataRecord(data, handler));
        if (o != null) {
            UserDataRecord r = (UserDataRecord) o;
            return r.fData;
        }
        return null;
    }
}
	/**
     * Retrieves the object associated to a key on a this node. The object
     * must first have been set to this node by calling
     * <code>setUserData</code> with the same key.
     * @param n The node the object is associated to.
     * @param key The key the object is associated to.
     * @return Returns the <code>DOMObject</code> associated to the given key
     *   on this node, or <code>null</code> if there was none.
     * @since DOM Level 3
     */
public Object getUserData(Node n, String key) {
    if (userData == null) {
        return null;
    }
    Hashtable t = (Hashtable) userData.get(n);
    if (t == null) {
        return null;
    }
    Object o = t.get(key);
    if (o != null) {
        UserDataRecord r = (UserDataRecord) o;
        return r.fData;
    }
    return null;
}
	/**
     * Retrieves the object associated to a key on a this node. The object
     * must first have been set to this node by calling
     * <code>setUserData</code> with the same key.
     * @param n The node the object is associated to.
     * @param key The key the object is associated to.
     * @return Returns the <code>DOMObject</code> associated to the given key
     *   on this node, or <code>null</code> if there was none.
     * @since DOM Level 3
     */
public Object getUserData(Node n, String key) {
    if (userData == null) {
        return null;
    }
    Hashtable t = (Hashtable) userData.get(n);
    if (t == null) {
        return null;
    }
    Object o = t.get(key);
    if (o != null) {
        UserDataRecord r = (UserDataRecord) o;
        return r.fData;
    }
    return null;
}
	/**
     * Set user data table for the given node.
     * @param n The node this operation applies to.
     * @param data The user data table.
     */
void setUserDataTable(Node n, Hashtable data) {
    if (userData == null)
        userData = new Hashtable();
    if (data != null) {
        userData.put(n, data);
    }
}
	/**
     * Call user data handlers when a node is deleted (finalized)
     * @param n The node this operation applies to.
     * @param c The copy node or null.
     * @param operation The operation - import, clone, or delete.
	 * @param handlers Data associated with n.
	*/
void callUserDataHandlers(Node n, Node c, short operation, Hashtable userData) {
    if (userData == null || userData.isEmpty()) {
        return;
    }
    Enumeration keys = userData.keys();
    while (keys.hasMoreElements()) {
        String key = (String) keys.nextElement();
        UserDataRecord r = (UserDataRecord) userData.get(key);
        if (r.fHandler != null) {
            r.fHandler.handle(operation, key, r.fData, n, c);
        }
    }
}
	/**
     * Call user data handlers when a node is deleted (finalized)
     * @param n The node this operation applies to.
     * @param c The copy node or null.
     * @param operation The operation - import, clone, or delete.
	 * @param handlers Data associated with n.
	*/
void callUserDataHandlers(Node n, Node c, short operation, Hashtable userData) {
    if (userData == null || userData.isEmpty()) {
        return;
    }
    Enumeration keys = userData.keys();
    while (keys.hasMoreElements()) {
        String key = (String) keys.nextElement();
        UserDataRecord r = (UserDataRecord) userData.get(key);
        if (r.fHandler != null) {
            r.fHandler.handle(operation, key, r.fData, n, c);
        }
    }
}
	/**
     * @since DOM Level 3
     */
public Object getFeature(String feature, String version) {
    boolean anyVersion = version == null || version.length() == 0;
    // considered.
    if ((feature.equalsIgnoreCase("+XPath")) && (anyVersion || version.equals("3.0"))) {
        // return it otherwise create a new one.
        if (fXPathEvaluator != null) {
            return fXPathEvaluator;
        }
        try {
            Class xpathClass = ObjectFactory.findProviderClass("org.apache.xpath.domapi.XPathEvaluatorImpl", ObjectFactory.findClassLoader(), true);
            Constructor xpathClassConstr = xpathClass.getConstructor(new Class[] { Document.class });
            // Check if the DOM XPath implementation implements
            // the interface org.w3c.dom.XPathEvaluator
            Class[] interfaces = xpathClass.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                if (interfaces[i].getName().equals("org.w3c.dom.xpath.XPathEvaluator")) {
                    fXPathEvaluator = xpathClassConstr.newInstance(new Object[] { this });
                    return fXPathEvaluator;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    return super.getFeature(feature, version);
}
	/** NON-DOM:  Get a number associated with a node created with respect
     * to this document.   Needed for compareDocumentPosition when nodes
     * are disconnected.  This is only used on demand.
     */
protected int getNodeNumber(Node node) {
    // Check if the node is already in the hash
    // If so, retrieve the node number
    // If not, assign a number to the node
    // Node numbers are negative, from -1 to -n
    int num;
    if (nodeTable == null) {
        nodeTable = new Hashtable();
        num = --nodeCounter;
        nodeTable.put(node, new Integer(num));
    } else {
        Integer n = (Integer) nodeTable.get(node);
        if (n == null) {
            num = --nodeCounter;
            nodeTable.put(node, new Integer(num));
        } else
            num = n.intValue();
    }
    return num;
}
	/** NON-DOM:  Get a number associated with a node created with respect
     * to this document.   Needed for compareDocumentPosition when nodes
     * are disconnected.  This is only used on demand.
     */
protected int getNodeNumber(Node node) {
    // Check if the node is already in the hash
    // If so, retrieve the node number
    // If not, assign a number to the node
    // Node numbers are negative, from -1 to -n
    int num;
    if (nodeTable == null) {
        nodeTable = new Hashtable();
        num = --nodeCounter;
        nodeTable.put(node, new Integer(num));
    } else {
        Integer n = (Integer) nodeTable.get(node);
        if (n == null) {
            num = --nodeCounter;
            nodeTable.put(node, new Integer(num));
        } else
            num = n.intValue();
    }
    return num;
}
}
