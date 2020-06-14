public class Class6 {
	private Object expandedSystemId;
	private Object notation;
	private Object name;
	private Object publicId;

	public Class6(Object expandedSystemId, Object notation, Object name, Object publicId){
		this.expandedSystemId = expandedSystemId;
		this.notation = notation;
		this.name = name;
		this.publicId = publicId;
	}
	// equals() returns true if two Notations have the same name.
// Useful for searching Vectors for notations with the same name
public boolean equals(Object obj) {
    if (obj == null) {
        return false;
    }
    if (obj instanceof Notation) {
        Notation other = (Notation) obj;
        return name.equals(other.name);
    }
    return false;
}
	// equals() returns true if two Notations have the same name.
// Useful for searching Vectors for notations with the same name
public boolean equals(Object obj) {
    if (obj == null) {
        return false;
    }
    if (obj instanceof Notation) {
        Notation other = (Notation) obj;
        return name.equals(other.name);
    }
    return false;
}
	// from 4.5.2
// Notation items with the same [name], [system identifier],
// [public identifier], and [declaration base URI] are considered
// to be duplicate. An application may also be able to detect that 
// notations are duplicate through other means. For instance, the URI 
// resulting from combining the system identifier and the declaration 
// base URI is the same.
public boolean isDuplicate(Object obj) {
    if (obj != null && obj instanceof Notation) {
        Notation other = (Notation) obj;
        return name.equals(other.name) && isEqual(publicId, other.publicId) && isEqual(expandedSystemId, other.expandedSystemId);
    }
    return false;
}
	// from 4.5.2
// Notation items with the same [name], [system identifier],
// [public identifier], and [declaration base URI] are considered
// to be duplicate. An application may also be able to detect that 
// notations are duplicate through other means. For instance, the URI 
// resulting from combining the system identifier and the declaration 
// base URI is the same.
public boolean isDuplicate(Object obj) {
    if (obj != null && obj instanceof Notation) {
        Notation other = (Notation) obj;
        return name.equals(other.name) && isEqual(publicId, other.publicId) && isEqual(expandedSystemId, other.expandedSystemId);
    }
    return false;
}
	// equals() returns true if two UnparsedEntities have the same name.
// Useful for searching Vectors for entities with the same name
public boolean equals(Object obj) {
    if (obj == null) {
        return false;
    }
    if (obj instanceof UnparsedEntity) {
        UnparsedEntity other = (UnparsedEntity) obj;
        return name.equals(other.name);
    }
    return false;
}
	// equals() returns true if two UnparsedEntities have the same name.
// Useful for searching Vectors for entities with the same name
public boolean equals(Object obj) {
    if (obj == null) {
        return false;
    }
    if (obj instanceof UnparsedEntity) {
        UnparsedEntity other = (UnparsedEntity) obj;
        return name.equals(other.name);
    }
    return false;
}
	// from 4.5.1:
// Unparsed entity items with the same [name], [system identifier],
// [public identifier], [declaration base URI], [notation name], and
// [notation] are considered to be duplicate. An application may also 
// be able to detect that unparsed entities are duplicate through other 
// means. For instance, the URI resulting from combining the system 
// identifier and the declaration base URI is the same.
public boolean isDuplicate(Object obj) {
    if (obj != null && obj instanceof UnparsedEntity) {
        UnparsedEntity other = (UnparsedEntity) obj;
        return name.equals(other.name) && isEqual(publicId, other.publicId) && isEqual(expandedSystemId, other.expandedSystemId) && isEqual(notation, other.notation);
    }
    return false;
}
	// from 4.5.1:
// Unparsed entity items with the same [name], [system identifier],
// [public identifier], [declaration base URI], [notation name], and
// [notation] are considered to be duplicate. An application may also 
// be able to detect that unparsed entities are duplicate through other 
// means. For instance, the URI resulting from combining the system 
// identifier and the declaration base URI is the same.
public boolean isDuplicate(Object obj) {
    if (obj != null && obj instanceof UnparsedEntity) {
        UnparsedEntity other = (UnparsedEntity) obj;
        return name.equals(other.name) && isEqual(publicId, other.publicId) && isEqual(expandedSystemId, other.expandedSystemId) && isEqual(notation, other.notation);
    }
    return false;
}
	// getRecognizedProperties():String[]
/**
     * Sets the value of a property. This method is called by the component
     * manager any time after reset when a property changes value.
     * <p>
     * <strong>Note:</strong> Components should silently ignore properties
     * that do not affect the operation of the component.
     *
     * @param propertyId The property identifier.
     * @param value      The value of the property.
     *
     * @throws SAXNotRecognizedException The component should not throw
     *                                   this exception.
     * @throws SAXNotSupportedException The component should not throw
     *                                  this exception.
     */
public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
    if (propertyId.equals(SYMBOL_TABLE)) {
        fSymbolTable = (SymbolTable) value;
        if (fChildConfig != null) {
            fChildConfig.setProperty(propertyId, value);
        }
        return;
    }
    if (propertyId.equals(ERROR_REPORTER)) {
        setErrorReporter((XMLErrorReporter) value);
        if (fChildConfig != null) {
            fChildConfig.setProperty(propertyId, value);
        }
        return;
    }
    if (propertyId.equals(ENTITY_RESOLVER)) {
        fEntityResolver = (XMLEntityResolver) value;
        if (fChildConfig != null) {
            fChildConfig.setProperty(propertyId, value);
        }
        return;
    }
    if (propertyId.equals(SECURITY_MANAGER)) {
        fSecurityManager = (SecurityManager) value;
        if (fChildConfig != null) {
            fChildConfig.setProperty(propertyId, value);
        }
        return;
    }
    if (propertyId.equals(BUFFER_SIZE)) {
        Integer bufferSize = (Integer) value;
        if (fChildConfig != null) {
            fChildConfig.setProperty(propertyId, value);
        }
        if (bufferSize != null && bufferSize.intValue() > 0) {
            fBufferSize = bufferSize.intValue();
            // Reset XML 1.0 text reader.
            if (fXInclude10TextReader != null) {
                fXInclude10TextReader.setBufferSize(fBufferSize);
            }
            // Reset XML 1.1 text reader.
            if (fXInclude11TextReader != null) {
                fXInclude11TextReader.setBufferSize(fBufferSize);
            }
        }
        return;
    }
}
	/**
     * Checks if the file indicated by the given XMLLocator has already been included
     * in the current stack.
     * @param includedSource the source to check for inclusion
     * @return true if the source has already been included
     */
protected boolean searchForRecursiveIncludes(XMLLocator includedSource) {
    String includedSystemId = includedSource.getExpandedSystemId();
    if (includedSystemId == null) {
        try {
            includedSystemId = XMLEntityManager.expandSystemId(includedSource.getLiteralSystemId(), includedSource.getBaseSystemId(), false);
        } catch (MalformedURIException e) {
            reportFatalError("ExpandedSystemId");
        }
    }
    if (includedSystemId.equals(fCurrentBaseURI.getExpandedSystemId())) {
        return true;
    }
    if (fParentXIncludeHandler == null) {
        return false;
    }
    return fParentXIncludeHandler.searchForRecursiveIncludes(includedSource);
}
}