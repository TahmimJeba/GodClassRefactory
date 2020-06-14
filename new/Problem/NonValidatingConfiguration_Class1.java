public class Class1 {

	public Class1(){
	}
	// checkProperty(String)
// factory methods
/** Creates an entity manager. */
protected XMLEntityManager createEntityManager() {
    return new XMLEntityManager();
}
	// createEntityManager():XMLEntityManager
/** Creates an error reporter. */
protected XMLErrorReporter createErrorReporter() {
    return new XMLErrorReporter();
}
}