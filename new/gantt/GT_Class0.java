public class Class0 {

	public Class0(){
	}
	/** Return the selected node */
public DefaultMutableTreeNode getSelectedNode() {
    TreePath currentSelection = tree.getSelectionPath();
    if (currentSelection == null) {
        return null;
    }
    DefaultMutableTreeNode dmtnselected = (DefaultMutableTreeNode) currentSelection.getLastPathComponent();
    return dmtnselected;
}
	/** Copy the current selected tree node */
public void copySelectedNode() {
    TreePath currentSelection = tree.getSelectionPath();
    if (currentSelection != null) {
        cpNode = (DefaultMutableTreeNode) (currentSelection.getLastPathComponent());
    }
}
	/** Cut the current selected tree node */
public void cutSelectedNode() {
    TreePath currentSelection = tree.getSelectionPath();
    if (currentSelection != null) {
        cpNode = (DefaultMutableTreeNode) (currentSelection.getLastPathComponent());
        //delete the current node
        DefaultMutableTreeNode father = getFatherNode(getSelectedNode());
        removeCurrentNode();
        GanttTask taskFather = (GanttTask) father.getUserObject();
        AdjustTaskBoundsAlgorithm alg = getTaskManager().getAlgorithmCollection().getAdjustTaskBoundsAlgorithm();
        alg.run(taskFather);
        //taskFather.refreshDateAndAdvancement(this);
        father.setUserObject(taskFather);
        area.repaint();
    }
}
	/** Remove the current node. */
public void removeCurrentNode() {
    TreePath currentSelection = tree.getSelectionPath();
    if (currentSelection != null) {
        DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection.getLastPathComponent());
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) (currentNode.getParent());
        if (parent != null) {
            treeModel.removeNodeFromParent(currentNode);
            forwardScheduling();
            nbTasks--;
            appli.refreshProjectInfos();
            return;
        }
    }
}
	/** Create a popup menu when mous click*/
public void createPopupMenu(int x, int y, boolean all) {
    JPopupMenu menu = new JPopupMenu();
    if (all) {
        boolean bOne = (tree.getSelectionCount() == 1);
        if (bOne)
            menu.add(appli.createNewItem(GanttProject.correctLabel(language.getText("propertiesTask")), "/icons/properties_16.gif"));
        menu.add(new NewTaskAction((IGanttProject) appli));
        menu.add(appli.createNewItem(GanttProject.correctLabel(language.getText("deleteTask")), "/icons/delete_16.gif"));
        menu.addSeparator();
        menu.add(appli.createNewItem(GanttProject.correctLabel(language.getText("indentTask")), "/icons/indent_16.gif"));
        menu.add(appli.createNewItem(GanttProject.correctLabel(language.getText("dedentTask")), "/icons/unindent_16.gif"));
        menu.add(appli.createNewItem(GanttProject.correctLabel(language.getText("upTask")), "/icons/up_16.gif"));
        menu.add(appli.createNewItem(GanttProject.correctLabel(language.getText("downTask")), "/icons/down_16.gif"));
        menu.addSeparator();
        menu.add(appli.createNewItem(GanttProject.correctLabel(language.getText("unlink")), "/icons/unlink_16.gif"));
        if (tree.getSelectionCount() >= 2)
            menu.add(appli.createNewItem(GanttProject.correctLabel(language.getText("link")), "/icons/link_16.gif"));
        menu.addSeparator();
    } else {
        menu.add(new NewTaskAction((IGanttProject) appli));
        menu.addSeparator();
    }
    menu.add(appli.createNewItem(GanttProject.correctLabel(language.getText("cut")), "/icons/cut_16.gif"));
    menu.add(appli.createNewItem(GanttProject.correctLabel(language.getText("copy")), "/icons/copy_16.gif"));
    menu.add(appli.createNewItem(GanttProject.correctLabel(language.getText("paste")), "/icons/paste_16.gif"));
    menu.applyComponentOrientation(language.getComponentOrientation());
    menu.show(this, x, y - area.getScrollBar());
}
	/** Return the array of expansion  */
/*public ArrayList getExpand() {
    return expand;
  }*/
/** Set the expand array and modify in consequence */
/*public void setExpand(ArrayList exp) {
    expand = exp;
    expandRefresh(rootNode);
  }*/
/** add an object with the expand information */
public DefaultMutableTreeNode addObjectWithExpand(Object child, DefaultMutableTreeNode parent) {
    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
    if (parent == null)
        parent = rootNode;
    treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
    forwardScheduling();
    Task task = (Task) (childNode.getUserObject());
    boolean res = true;
    if (parent == null)
        res = false;
    //test for expantion
    while (parent != null) {
        Task taskFather = (Task) (parent.getUserObject());
        if (!taskFather.getExpand())
            res = false;
        parent = (DefaultMutableTreeNode) (parent.getParent());
    }
    if (res)
        tree.scrollPathToVisible(new TreePath(childNode.getPath()));
    else
        task.setExpand(false);
    nbTasks++;
    appli.refreshProjectInfos();
    return childNode;
}
	/** Add a sub task. */
public DefaultMutableTreeNode addObject(Object child, DefaultMutableTreeNode parent) {
    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
    if (parent == null)
        parent = rootNode;
    //GanttTask tmpTask = (GanttTask)(childNode.getUserObject());
    //tmpTask.indentID((String)(((GanttTask)(parent.getUserObject())).getID()));
    treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
    forwardScheduling();
    tree.scrollPathToVisible(new TreePath(childNode.getPath()));
    nbTasks++;
    appli.refreshProjectInfos();
    return childNode;
}
	/** Return the last default tree node */
public DefaultMutableTreeNode getLastNode() {
    return rootNode.getLastLeaf();
}
	/** Select the row of the tree */
public void selectTreeRow(int row) {
    tree.setSelectionRow(row);
}
	/** Return the JTree. */
public JTree getJTree() {
    return tree;
}
	/** Return the root node */
public DefaultMutableTreeNode getRoot() {
    return rootNode;
}
	/**
   * In elder version, this function refresh all the related tasks to the
       * taskToMove. In the new version, this function is same as forwardScheduling().
   * It will refresh all the tasks.
   * @param taskToMove
   */
public void refreshAllChild(String taskToMove) {
    forwardScheduling();
}
	// Transferable interface methods...
public DataFlavor[] getTransferDataFlavors() {
    return _flavors;
}
	/** Paste the node and its child node to current selected position*/
public void pasteNode() {
    if (cpNode != null) {
        DefaultMutableTreeNode current = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (current == null) {
            current = rootNode;
        }
        insertClonedNode(current, cpNode, 0, true);
    }
}
	/** Insert the cloned node and its children*/
private void insertClonedNode(DefaultMutableTreeNode parent, DefaultMutableTreeNode child, int location, boolean first) {
    if (parent == null) {
        //it is the root node
        return;
    }
    if (first) {
        GanttTask _t = (GanttTask) (parent.getUserObject());
        if (_t.isMilestone()) {
            _t.setMilestone(false);
            GanttTask _c = (GanttTask) (child.getUserObject());
            _t.setLength(_c.getLength());
            _t.setStart(_c.getStart());
        }
    }
    GanttTask originalTask = (GanttTask) child.getUserObject();
    GanttTask newTask = originalTask.Clone();
    newTask.setName((first ? language.getText("copy2") + "_" : "") + newTask.toString());
    TaskManagerImpl tmi = (TaskManagerImpl) getTaskManager();
    newTask.setTaskID(tmi.getMaxID() + 1);
    getTaskManager().registerTask(newTask);
    DefaultMutableTreeNode cloneChildNode = new DefaultMutableTreeNode(newTask);
    for (int i = 0; i < child.getChildCount(); i++) {
        insertClonedNode(cloneChildNode, (DefaultMutableTreeNode) child.getChildAt(i), i, false);
    }
    treeModel.insertNodeInto(cloneChildNode, parent, location);
    tree.scrollPathToVisible(new TreePath(cloneChildNode.getPath()));
    //Remove the node from the expand list
    /*int index = expand.indexOf(new Integer(newTask.getTaskID())cloneChildNode.toString());
    if (index >= 0) 
      expand.remove(index);
    */
    newTask.setExpand(false);
}
	//overwrite to return a GanttTask object when editing tree node
public Object getCellEditorValue() {
    DefaultMutableTreeNode tmpMutableTreeNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
    if (tmpMutableTreeNode == null)
        return null;
    Object userObject = tmpMutableTreeNode.getUserObject();
    appli.setAskForSave(true);
    if (userObject instanceof Task) {
        Task ganttTask = (Task) userObject;
        /*if (expand.contains(new Integer(ganttTask.getTaskID()))) {
			expand.remove(expand.indexOf(new Integer(ganttTask.getTaskID())));
			expand.add(new Integer(ganttTask.getTaskID()));
		}*/
        //?????
        ganttTask.setName(textField.getText());
        return ganttTask;
    } else
        return null;
}
}