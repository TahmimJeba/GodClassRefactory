public class Class2 {
	private Object length;

	public Class2(Object length){
		this.length = length;
	}
	/** Function to put up the selected tasks */
public void upCurrentNodes() {
    DefaultMutableTreeNode[] cdmtn = getSelectedNodes();
    if (cdmtn == null) {
        appli.getStatusBar().setFirstText(language.getText("msg21"), 2000);
        return;
    }
    TreePath[] selectedPaths = new TreePath[cdmtn.length];
    for (int i = 0; i < cdmtn.length; i++) {
        DefaultMutableTreeNode father = this.getFatherNode(cdmtn[i]);
        int index = father.getIndex((TreeNode) cdmtn[i]);
        if (index != 0) {
            cdmtn[i].removeFromParent();
            treeModel.nodesWereRemoved(father, new int[] { index }, new Object[] { cdmtn });
            //	New position
            index--;
            treeModel.insertNodeInto((MutableTreeNode) cdmtn[i], (MutableTreeNode) father, index);
            //	Select tagain this node
            TreeNode[] treepath = cdmtn[i].getPath();
            TreePath path = new TreePath(treepath);
            //tree.setSelectionPath(path);
            selectedPaths[i] = path;
            expandRefresh(cdmtn[i]);
            forwardScheduling();
        //refreshAllId(father);
        }
    }
    tree.setSelectionPaths(selectedPaths);
    area.repaint();
}
	/** Function to put down the selected tasks */
public void downCurrentNodes() {
    DefaultMutableTreeNode[] cdmtn = getSelectedNodes();
    if (cdmtn == null) {
        appli.getStatusBar().setFirstText(language.getText("msg21"), 2000);
        return;
    }
    TreePath[] selectedPaths = new TreePath[cdmtn.length];
    //Parse in reverse mode because tasks are sorted from top to bottom.
    for (int i = cdmtn.length - 1; i >= 0; i--) {
        DefaultMutableTreeNode father = this.getFatherNode(cdmtn[i]);
        int index = father.getIndex((TreeNode) cdmtn[i]);
        index++;
        //New position
        if ((index < father.getChildCount())) {
            cdmtn[i].removeFromParent();
            treeModel.nodesWereRemoved(father, new int[] { index - 1 }, new Object[] { cdmtn });
            treeModel.insertNodeInto((MutableTreeNode) cdmtn[i], (MutableTreeNode) father, index);
            //Select tagain this node
            TreeNode[] treepath = cdmtn[i].getPath();
            TreePath path = new TreePath(treepath);
            //tree.setSelectionPath(path);
            selectedPaths[i] = path;
            expandRefresh(cdmtn[i]);
            forwardScheduling();
        //refreshAllId(father)
        }
    }
    tree.setSelectionPaths(selectedPaths);
    area.repaint();
}
	/** Function to indent selected task this will change
   *  the parent child relationship.
   *  This Code is based on the UP/DOWN Coder I found in here
   *  barmeier
   */
/** Indent several nodes that are selected. 
   *  Based on the IndentCurrentNode method. */
public void indentCurrentNodes() {
    DefaultMutableTreeNode[] cdmtn = getSelectedNodes();
    if (cdmtn == null) {
        appli.getStatusBar().setFirstText(language.getText("msg21"), 2000);
        return;
    }
    TreePath[] selectedPaths = new TreePath[cdmtn.length];
    for (int i = 0; i < cdmtn.length; i++) {
        DefaultMutableTreeNode father = this.getFatherNode(cdmtn[i]);
        // Where is my nearest sibling in ascending order ?
        DefaultMutableTreeNode newFather = cdmtn[i].getPreviousSibling();
        // If there is no more indentation possible we must stop
        if (newFather == null) {
            return;
        }
        ((Task) newFather.getUserObject()).setMilestone(false);
        int oldIndex = father.getIndex((TreeNode) cdmtn[i]);
        cdmtn[i].removeFromParent();
        treeModel.nodesWereRemoved(father, new int[] { oldIndex }, new Object[] { cdmtn });
        treeModel.insertNodeInto((MutableTreeNode) cdmtn[i], (MutableTreeNode) newFather, newFather.getChildCount());
        //Select tagain this node
        TreeNode[] treepath = cdmtn[i].getPath();
        TreePath path = new TreePath(treepath);
        //tree.setSelectionPath(path);
        selectedPaths[i] = path;
        //refresh the father date
        Task current = (Task) (cdmtn[i].getUserObject());
        refreshAllFather(current.toString());
        expandRefresh(cdmtn[i]);
        forwardScheduling();
    }
    tree.setSelectionPaths(selectedPaths);
    //refresh the graphic area
    area.repaint();
}
	/** Function to dedent selected task this will change
   *  the parent child relationship.
   *  This Code is based on the UP/DOWN Coder I found in here
   *  barmeier
   */
/** Unindent the selected nodes. */
public void dedentCurrentNodes() {
    DefaultMutableTreeNode[] cdmtn = getSelectedNodes();
    if (cdmtn == null) {
        appli.getStatusBar().setFirstText(language.getText("msg21"), 2000);
        return;
    }
    TreePath[] selectedPaths = new TreePath[cdmtn.length];
    for (int i = 0; i < cdmtn.length; i++) {
        DefaultMutableTreeNode father = this.getFatherNode(cdmtn[i]);
        // Getting the fathers father !? The grandpa I think :)
        DefaultMutableTreeNode newFather = this.getFatherNode(father);
        // If no grandpa is available we must stop.
        if (newFather == null) {
            return;
        }
        int oldIndex = father.getIndex((TreeNode) cdmtn[i]);
        cdmtn[i].removeFromParent();
        treeModel.nodesWereRemoved(father, new int[] { oldIndex }, new Object[] { cdmtn });
        treeModel.insertNodeInto((MutableTreeNode) cdmtn[i], (MutableTreeNode) newFather, newFather.getIndex((TreeNode) father) + 1);
        //Select tagain this node
        TreeNode[] treepath = cdmtn[i].getPath();
        TreePath path = new TreePath(treepath);
        //tree.setSelectionPath(path);
        selectedPaths[i] = path;
        //	refresh the father date
        Task current = (Task) (cdmtn[i].getUserObject());
        refreshAllFather(current.toString());
        expandRefresh(cdmtn[i]);
        forwardScheduling();
    }
    tree.setSelectionPaths(selectedPaths);
    area.repaint();
}
	/** Return the selected task */
public GanttTask getSelectedTask() {
    DefaultMutableTreeNode node = getSelectedNode();
    if (node == null)
        return null;
    return (GanttTask) (node.getUserObject());
}
	/** Returne the mother task.*/
public DefaultMutableTreeNode getFatherNode(DefaultMutableTreeNode node) {
    if (node == null) {
        return null;
    }
    return (DefaultMutableTreeNode) node.getParent();
}
	/** Returne the mother task.*/
public DefaultMutableTreeNode getFatherNode(DefaultMutableTreeNode node) {
    if (node == null) {
        return null;
    }
    return (DefaultMutableTreeNode) node.getParent();
}
	/** @return the list of the selected nodes. */
public DefaultMutableTreeNode[] getSelectedNodes() {
    TreePath[] currentSelection = tree.getSelectionPaths();
    if (//no elements are selectionned
    currentSelection == null || currentSelection.length == 0)
        return null;
    DefaultMutableTreeNode[] dmtnselected = new DefaultMutableTreeNode[currentSelection.length];
    for (int i = 0; i < currentSelection.length; i++) dmtnselected[i] = (DefaultMutableTreeNode) currentSelection[i].getLastPathComponent();
    return dmtnselected;
}
	/** Returne the mother task.*/
public DefaultMutableTreeNode getFatherNode(Task node) {
    if (node == null) {
        return null;
    }
    DefaultMutableTreeNode tmp = (DefaultMutableTreeNode) getNode(node.getTaskID());
    if (tmp == null) {
        return null;
    }
    return (DefaultMutableTreeNode) tmp.getParent();
}
	/** Returne the mother task.*/
public DefaultMutableTreeNode getFatherNode(Task node) {
    if (node == null) {
        return null;
    }
    DefaultMutableTreeNode tmp = (DefaultMutableTreeNode) getNode(node.getTaskID());
    if (tmp == null) {
        return null;
    }
    return (DefaultMutableTreeNode) tmp.getParent();
}
	//!@#
/**
   * Check if task1 can be the successor of task2
   * @param task1 successor
   * @param task2 predecessor
   * @return true if task1 can be the successor of task2.
   */
public boolean checkDepend(Task task1, GanttTask task2) {
    if (task1 == null || task2 == null) {
        return false;
    }
    if (task1.getTaskID() == task2.getTaskID()) {
        return false;
    }
    if (getAllChildTask(task2).size() != 0) {
        //question of CL
        return false;
    }
    Vector successors = task2.getSuccessorsOld();
    for (int i = 0; i < successors.size(); i++) {
        int tempTaskID = ((GanttTaskRelationship) successors.get(i)).getSuccessorTaskID();
        GanttTask tempTask = getTaskManager().getTask(tempTaskID);
        if (!checkDepend(task1, tempTask)) {
            return false;
        }
    }
    return true;
}
	//update 21/03/2003
/** Refresh the exapntion (recursive function) */
public void expandRefresh(DefaultMutableTreeNode moved) {
    Task movedTask = (Task) moved.getUserObject();
    //if (expand.contains(new Integer(movedTask.getTaskID()))) {
    if (movedTask.getExpand())
        tree.expandPath(new TreePath(moved.getPath()));
    Enumeration children = moved.children();
    while (children.hasMoreElements()) {
        expandRefresh((DefaultMutableTreeNode) children.nextElement());
    }
}
	/** Refresh al fathter to set all duration */
public void refreshAllFather(String taskToMove) {
    //new GanttTask("__Bidon__", null, 0);
    Task taskFather = null;
    Task tmpTask = new GanttTask(taskToMove, new GanttCalendar(), 0, getTaskManager());
    DefaultMutableTreeNode father = getFatherNode(tmpTask);
    if (father == null) {
        return;
    }
    throw new RuntimeException("You will never be here!");
//    while (getNode(tmpTask.getTaskID()).isRoot() == false) {
//      father = getFatherNode(tmpTask);
//      taskFather = (GanttTask) father.getUserObject();
//      taskFather.refreshDateAndAdvancement(this);
//      father.setUserObject(taskFather);
//      tmpTask = taskFather;
//    }
}
}