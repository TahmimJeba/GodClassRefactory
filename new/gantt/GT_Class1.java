public class Class1 {

	public Class1(){
	}
	/** Return tru if the Project has tasks and false is no tasks on the project */
public boolean hasTasks() {
    Enumeration e = (rootNode).preorderEnumeration();
    while (e.hasMoreElements()) {
        if (rootNode != (DefaultMutableTreeNode) e.nextElement())
            return true;
    }
    return false;
}
	/** Returnan ArrayList with all tasks. */
public ArrayList getAllTasks() {
    ArrayList res = new ArrayList();
    Enumeration e = (rootNode).preorderEnumeration();
    while (e.hasMoreElements()) {
        res.add(e.nextElement());
    }
    return res;
}
	/** Return an ArrayList with String for all tasks */
public ArrayList getArryListTaskString(String except) {
    ArrayList l = getAllTasks();
    ArrayList res = new ArrayList();
    for (int i = 0; i < l.size(); i++) {
        if ((except != null && l.get(i).toString() != except) || except == null) {
            res.add(l.get(i).toString());
        }
    }
    return res;
}
	//static int count=0; //for Debug CL
///////////////////////////////////////////////////////////////////////
/*forward calculate the earliest start and finish of all the task*/
//-By CL 15-May-2003
/*
  private void _forwardScheduling() {
      //new Exception().printStackTrace();
    //System.out.println("forword scheduling: "+count++); //for Debug CL
    setAllTasksUnchecked(); //for the purpose to ckeck all the relationships
    /////////////////////////////////
    //setAllDependencies();
////Code should be deleted after the depend has been replaced by successors
    ////////////////////////////////
    ArrayList taskNodes = getAllTasks();
    for (int i = 0; i < taskNodes.size(); i++) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) taskNodes.get(i);
      if (node.getChildCount() == 0) { //it is not a mother task
        GanttTask task = (GanttTask) node.getUserObject();
        if (!task.isChecked()) {
          findEarliestStart(task);
        }
      }
    }
    //Treat the mother task. (the children have been scheduled.)
    //start date of mother task should be the earliest start date of its children
    //finish date of mother task is the last finish date of its children
    for (int i = 0; i < taskNodes.size(); i++) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) taskNodes.get(i);
      if (node.getChildCount() != 0) { //it is a mother task
        if (node.isRoot()) {
          continue;
        }
        Task task = (Task) node.getUserObject();
        GanttCalendar earliestStartDate = new GanttCalendar(2949, 10, 1);
        GanttCalendar earliestFinishDate = new GanttCalendar(1049, 10, 1);
        //find the earliest date of children's start dates
        //find the last finish date of children's finish dates
        Enumeration childNodes = node.children();
        while (childNodes.hasMoreElements()) {
          Task childTask = (Task) ( (DefaultMutableTreeNode)
                                             childNodes.nextElement()).
              getUserObject();
          if (earliestStartDate.compareTo(childTask.getStart()) > 0) {
            earliestStartDate = childTask.getStart().Clone();
          }
          if (earliestFinishDate.compareTo(childTask.getEnd()) < 0) {
            earliestFinishDate = childTask.getEnd().Clone();
          }
        }
        task.setStart(earliestStartDate);
        task.setEnd(earliestFinishDate);
      }
    }
  }
      */
//static int countFindEarliestStart=0;//for Debug CL
/*
  private void findEarliestStart(GanttTask task) {
    //System.out.println("I m in findEarliestStart for "+countFindEarliestStart++);//for Debug CL

    GanttCalendar earliestStart = new GanttCalendar(1099, 10, 1); //set the earliest start date to be some date impossible. at least where I don't care:)
    if (!task.isChecked()) {
      Vector predecessors = task.getPredecessorsOld();
      //for the task without predecessor, the start date is the earliest start date
      if (predecessors.size() == 0) {
        task.setChecked(true);
      }
      //If there are predecessors, the earliest date should be depended
      //on the relationship type and start or end date of each predecessor.
      for (int i = 0; i < predecessors.size(); i++) {
        GanttTask predecessorTask = ( (GanttTaskRelationship) predecessors.get(
            i)).getPredecessorTask();
        int relationshipType = ( (GanttTaskRelationship) predecessors.get(
            i)).getRelationshipType();
        if (relationshipType == GanttTaskRelationship.FS) {
          ////////////////////////////////////
          //FS realtionship: the earliest start date should be the
          //latest earliest finish date of all the predecessors
          ////////////////////////////////////
          if (!predecessorTask.isChecked()) { //If ther predecessor has not been checked, check it here. It is a recursive algorithm
	   				 findEarliestStart(predecessorTask);
          }
          if (predecessorTask.isChecked()) { //if checked, the start and end date are valid
            GanttCalendar temp = predecessorTask.getEnd().Clone();
            //temp.add(1); //should be one day behind the predecessor finish date.
            if (temp.compareTo(earliestStart) > 0) { //if the current earliest start is earlier than the end date of one of its prodecessor, it set equal to the end date of this predecessor
              earliestStart = temp;
            }
          }
        }
        else if (relationshipType == GanttTaskRelationship.FF) {
          ////////////////////////////////////
          //FF realtionship: As soon as the predecessor task finishes,
          //the successor task can finish
          ////////////////////////////////////
          if (!predecessorTask.isChecked()) {
            findEarliestStart(predecessorTask); //check the predecessor
          }
          if (predecessorTask.isChecked()) {
            GanttCalendar temp = predecessorTask.getEnd().Clone();
            GanttCalendar earliestFinish = earliestStart;
            earliestFinish.add(task.getLength());
            if (earliestFinish.compareTo(temp) < 0) { //if the earliest finish is earlier than the end date of its predecessor, it set equal to the end date of predecessor
              earliestFinish = temp.Clone();
              earliestStart = earliestFinish.Clone();
              earliestStart.add( -task.getLength());
            }
            else { //do nothing, if it is behind end date of predecessor

            }
          }
        }
        else if (relationshipType == GanttTaskRelationship.SF) {
          ////////////////////////////////////
          //SF realtionship: As soon as the predecessor task starts,
          //the successor task can finish.
          ////////////////////////////////////
          if (!predecessorTask.isChecked()) {
            findEarliestStart(predecessorTask); //if the predecessor has not been checked, check it here. it is a recursive algorithm
          }
          if (predecessorTask.isChecked()) {
            GanttCalendar temp = predecessorTask.getStart().Clone();
            GanttCalendar earliestFinish = earliestStart;
            earliestFinish.add(task.getLength());

            if (earliestFinish.compareTo(temp) < 0) { //if the earliest finish of the task is earlier than the start date of one of its predecessors, it set equal to the start date of the predecessor
              earliestFinish = temp.Clone();
              earliestStart = earliestFinish.Clone();
              earliestStart.add( -task.getLength());
            }
            else { //already satisfied the SF relationship, do nothing

            }
          }
        }
        else if (relationshipType == GanttTaskRelationship.SS) {
          ////////////////////////////////////
          //SS realtionship: As soon as the predecessor task starts,
          //the successor task can start.
          ////////////////////////////////////
          if (!predecessorTask.isChecked()) {
            findEarliestStart(predecessorTask); //if the predecessor has not been checked, check it here. it is a recursive algorithm
          }
          if (predecessorTask.isChecked()) {
            GanttCalendar temp = predecessorTask.getStart().Clone();

            if (earliestStart.compareTo(temp) < 0) { // if the start date of the task is earlier than the start date of its predecessor, it set equal to the start date of predecessor
              earliestStart = temp.Clone();
            }
            else { //already satisfied the SS relationship, do nothing.

            }
          }
        }
      }
      if (earliestStart.compareTo(task.getStart()) < 0) { //if the actual start is behind earliest start, don't need to do anything

      }
      else {
        task.setStart(earliestStart);
        GanttCalendar temp = earliestStart.Clone();
        temp.add(task.getLength());
        task.setEnd(temp);
      }
      task.setChecked(true);
    }
    return;
  }
      */
/** instead of returning a list of DefautMutableTreeNode it return
   * a list of GanttTask */
public ArrayList getAllGanttTasks() {
    ArrayList res = new ArrayList();
    Enumeration e = (rootNode).preorderEnumeration();
    while (e.hasMoreElements()) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
        res.add(node.getUserObject());
    }
    return res;
}
	/** Returne the task with the name name*/
public Task getTask(String name) {
    DefaultMutableTreeNode base;
    base = (DefaultMutableTreeNode) tree.getModel().getRoot();
    Enumeration e = base.preorderEnumeration();
    while (e.hasMoreElements()) {
        base = (DefaultMutableTreeNode) e.nextElement();
        if (base.toString().equals(name)) {
            return (Task) (base.getUserObject());
        }
    }
    return null;
}
	/** Return the DefaultMutableTreeNodequi with the name name. */
public DefaultMutableTreeNode getNode(int id) /*String name*/
{
    DefaultMutableTreeNode res, base;
    base = (DefaultMutableTreeNode) tree.getModel().getRoot();
    Enumeration e = base.preorderEnumeration();
    while (e.hasMoreElements()) {
        res = ((DefaultMutableTreeNode) e.nextElement());
        if (((Task) (res.getUserObject())).getTaskID() == id) {
            return res;
        }
    }
    return null;
}
	/** Return all sub task for the tree node base */
public ArrayList getAllChildTask(Task task) {
    ArrayList res = new ArrayList();
    if (task == null)
        return null;
    DefaultMutableTreeNode base = (DefaultMutableTreeNode) getNode(task.getTaskID());
    if (base == null)
        return res;
    Enumeration e = base.children();
    while (e.hasMoreElements()) {
        res.add(e.nextElement());
    }
    return res;
}
	/** Return all sub task for the tree node base */
public ArrayList getAllChildTask(Task task) {
    ArrayList res = new ArrayList();
    if (task == null)
        return null;
    DefaultMutableTreeNode base = (DefaultMutableTreeNode) getNode(task.getTaskID());
    if (base == null)
        return res;
    Enumeration e = base.children();
    while (e.hasMoreElements()) {
        res.add(e.nextElement());
    }
    return res;
}
	/** Return all sub task for the tree node base */
public ArrayList getAllChildTask(DefaultMutableTreeNode base) {
    ArrayList res = new ArrayList();
    if (base == null)
        return res;
    Enumeration e = base.children();
    while (e.hasMoreElements()) {
        res.add(e.nextElement());
    }
    return res;
}
	/** Return all sub task for the tree node base */
public ArrayList getAllChildTask(DefaultMutableTreeNode base) {
    ArrayList res = new ArrayList();
    if (base == null)
        return res;
    Enumeration e = base.children();
    while (e.hasMoreElements()) {
        res.add(e.nextElement());
    }
    return res;
}
	/** Compute the percent complete for a parent task and return the value*/
public void computePercentComplete(DefaultMutableTreeNode parent) {
    float totallength = 0;
    float totalcomplete = 0;
    float taskcomplete = 0;
    Enumeration e = parent.preorderEnumeration();
    DefaultMutableTreeNode node;
    Task task = (Task) parent.getUserObject();
    //skip the current parent task
    e.nextElement();
    while (e.hasMoreElements()) {
        node = (DefaultMutableTreeNode) e.nextElement();
        GanttTask temptask = (GanttTask) node.getUserObject();
        totallength = totallength + temptask.getLength();
        float tasklength = temptask.getLength();
        float taskpct = temptask.getCompletionPercentage();
        taskcomplete = (tasklength * (taskpct / 100));
        totalcomplete = totalcomplete + taskcomplete;
    }
    task.setCompletionPercentage((int) ((totalcomplete / totallength) * 100));
}
	/** Return all tasks on an array */
public Object[] getAllTaskArray() {
    ArrayList al = getAllTasks();
    return al.toArray();
}
	/** Return all task exept the task in parameter */
public String[] getAllTaskString(String except) {
    ArrayList l = getAllTasks();
    String[] res;
    if (except == null) {
        res = new String[l.size()];
    } else {
        res = new String[l.size() - 1];
    }
    int i = 0, j = 0;
    for (; i < l.size(); i++) {
        if (except == null || (l.get(i).toString() != except)) {
            Array.set(res, j, l.get(i).toString());
            j++;
        }
    }
    return res;
}
	/** Expansion */
public void treeExpanded(TreeExpansionEvent e) {
    if (area != null) {
        area.repaint();
    }
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) (e.getPath().getLastPathComponent());
    Task task = (Task) node.getUserObject();
    /*if(!expand.contains(new Integer(task.getTaskID()))) {
		 expand.add(new Integer(task.getTaskID()));
		 appli.setAskForSave(true);
      }*/
    task.setExpand(true);
    appli.setAskForSave(true);
}
	/** Collapse */
public void treeCollapsed(TreeExpansionEvent e) {
    if (area != null) {
        area.repaint();
    }
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) (e.getPath().getLastPathComponent());
    Task task = (Task) node.getUserObject();
    /*int index = expand.indexOf(new Integer(task.getTaskID()));
      if (index >= 0) {
        expand.remove(index);
        appli.setAskForSave(true);
      } */
    task.setExpand(false);
    appli.setAskForSave(true);
}
	/**set all the earliestStart and earliestFinish to be null before scheduling */
private void setAllTasksUnchecked() {
    ArrayList tasks = getAllGanttTasks();
    for (Iterator it = tasks.iterator(); it.hasNext(); ) {
        GanttTask task = (GanttTask) it.next();
        task.setChecked(false);
    }
//for (int i = 0; i < tasks.size(); i++) {
//( (GanttTask) tasks.get(i)).setChecked(false);
//( (GanttTask) tasks.get(i)).getPredecessorsOld().clear();
//( (GanttTask) tasks.get(i)).getSuccessorsOld().clear();
//}
}
}