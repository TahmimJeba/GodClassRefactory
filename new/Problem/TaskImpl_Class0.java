public class Class0 {

	public Class0(){
	}
	//TODO: remove this hack. ID must never be changed
protected void setTaskIDHack(int taskID) {
    myID = taskID;
}
	/**
     * Allows to determine, if a special shape is defined for this task.
     * @return true, if this task has its own shape defined.
     */
public boolean shapeDefined() {
    return (myShape != null);
}
	// main properties
public int getTaskID() {
    return myID;
}
	//
public Task getSupertask() {
    TaskHierarchyItem container = myTaskHierarchyItem.getContainerItem();
    return container.getTask();
}
}