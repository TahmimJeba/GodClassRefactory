public class Class3 {

	public Class3(){
	}
	/** modify a node */
public void treeNodesChanged(TreeModelEvent e) {
    if (area != null) {
        area.repaint();
    }
}
	/** Delete a node. */
public void treeNodesRemoved(TreeModelEvent e) {
    if (area != null) {
        area.repaint();
    }
}
	/** Insert a new node. */
public void treeNodesInserted(TreeModelEvent e) {
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());
    Task task = (Task) node.getUserObject();
    if (area != null)
        area.repaint();
}
}
