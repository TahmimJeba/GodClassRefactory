public class Class1 {
	private Object BACKSLASH;
	private Object length;
	private Object myTaskHierarchyItem;

	public Class1(Object BACKSLASH, Object length, Object myTaskHierarchyItem){
		this.BACKSLASH = BACKSLASH;
		this.length = length;
		this.myTaskHierarchyItem = myTaskHierarchyItem;
	}
	public void setNotes(String notes) {
    myNotes = notes;
}
	public void addNotes(String notes) {
    myNotes += notes;
}
	public TaskMutator createMutator() {
    return new MutatorImpl();
}
	public String getName() {
    return myName;
}
	public String getWebLink() {
    return myWebLink;
}
	public boolean isMilestone() {
    return isMilestone;
}
	public int getPriority() {
    return myPriority;
}
	public GanttCalendar getStart() {
    return myStart;
}
	public GanttCalendar getEnd() {
    if (myEnd == null) {
        myEnd = getStart().Clone();
        myEnd.add((int) getDuration().getLength());
    }
    return myEnd;
}
	public TaskLength getDuration() {
    //System.err.println("[TaskImp] this="+this+" duration="+myLength+" id="+myID);
    return myLength;
}
	public int getCompletionPercentage() {
    return myCompletionPercentage;
}
	public boolean isStartFixed() {
    return isStartFixed;
}
	public boolean getExpand() {
    return bExpand;
}
	public ShapePaint getShape() {
    return myShape == null ? new ShapePaint(ShapeConstants.BACKSLASH, getColor(), getColor()) : myShape;
}
	public Color getColor() {
    return myColor == null ? myManager.getConfig().getDefaultColor() : myColor;
}
	public String getNotes() {
    return myNotes;
}
	public GanttTaskRelationship[] getPredecessors() {
    //To change body of implemented methods use Options | File Templates.
    return new GanttTaskRelationship[0];
}
	public GanttTaskRelationship[] getSuccessors() {
    //To change body of implemented methods use Options | File Templates.
    return new GanttTaskRelationship[0];
}
	public ResourceAssignment[] getAssignments() {
    return myAssignments.getAssignments();
}
	public ResourceAssignmentCollection getAssignmentCollection() {
    return myAssignments;
}
	public Task[] getNestedTasks() {
    TaskHierarchyItem[] nestedItems = myTaskHierarchyItem.getNestedItems();
    Task[] result = new Task[nestedItems.length];
    for (int i = 0; i < nestedItems.length; i++) {
        result[i] = nestedItems[i].getTask();
    }
    return result;
}
	public void move(Task targetSupertask) {
    TaskImpl supertaskImpl = (TaskImpl) targetSupertask;
    TaskHierarchyItem targetItem = supertaskImpl.myTaskHierarchyItem;
    myTaskHierarchyItem.delete();
    targetItem.addNestedItem(myTaskHierarchyItem);
}
	public TaskDependencySlice getDependencies() {
    return myDependencySlice;
}
	public TaskDependencySlice getDependenciesAsDependant() {
    return myDependencySliceAsDependant;
}
	public TaskDependencySlice getDependenciesAsDependee() {
    return myDependencySliceAsDependee;
}
	public TaskManager getManager() {
    return myManager;
}
	protected TimeUnitManager getTimeUnitManager() {
    return myTimeUnitManager;
}
	public void commit() {
    for (int i = 0; i < myCommands.size(); i++) {
        Runnable next = (Runnable) myCommands.get(i);
        next.run();
    }
    myCommands.clear();
}
	public void setName(final String name) {
    myCommands.add(new Runnable() {

        public void run() {
            TaskImpl.this.setName(name);
        }
    });
}
	public void run() {
    TaskImpl.this.setName(name);
}
	public void setMilestone(final boolean milestone) {
    myCommands.add(new Runnable() {

        public void run() {
            TaskImpl.this.setMilestone(milestone);
        }
    });
}
	public void run() {
    TaskImpl.this.setMilestone(milestone);
}
	public void setPriority(final int priority) {
    myCommands.add(new Runnable() {

        public void run() {
            TaskImpl.this.setPriority(priority);
        }
    });
}
	public void run() {
    TaskImpl.this.setPriority(priority);
}
	public void setStart(final GanttCalendar start) {
    myCommands.add(new Runnable() {

        public void run() {
            TaskImpl.this.setStart(start);
        }
    });
}
	public void run() {
    TaskImpl.this.setStart(start);
}
	public void setEnd(final GanttCalendar end) {
    myCommands.add(new Runnable() {

        public void run() {
            TaskImpl.this.setEnd(end);
        }
    });
}
	public void run() {
    TaskImpl.this.setEnd(end);
}
	public void setDuration(final TaskLength length) {
    myCommands.add(new Runnable() {

        public void run() {
            TaskImpl.this.setDuration(length);
        }
    });
}
	public void run() {
    TaskImpl.this.setDuration(length);
}
	public void setExpand(final boolean expand) {
    myCommands.add(new Runnable() {

        public void run() {
            TaskImpl.this.setExpand(expand);
        }
    });
}
	public void run() {
    TaskImpl.this.setExpand(expand);
}
	public void setCompletionPercentage(final int percentage) {
    myCommands.add(new Runnable() {

        public void run() {
            TaskImpl.this.setCompletionPercentage(percentage);
        }
    });
}
	public void run() {
    TaskImpl.this.setCompletionPercentage(percentage);
}
	public void setStartFixed(final boolean isFixed) {
    myCommands.add(new Runnable() {

        public void run() {
            TaskImpl.this.setStartFixed(isFixed);
        }
    });
}
	public void run() {
    TaskImpl.this.setStartFixed(isFixed);
}
	public void setShape(final ShapePaint shape) {
    myCommands.add(new Runnable() {

        public void run() {
            TaskImpl.this.setShape(shape);
        }
    });
}
	public void run() {
    TaskImpl.this.setShape(shape);
}
	public void setColor(final Color color) {
    myCommands.add(new Runnable() {

        public void run() {
            TaskImpl.this.setColor(color);
        }
    });
}
	public void run() {
    TaskImpl.this.setColor(color);
}
	public void setNotes(final String notes) {
    myCommands.add(new Runnable() {

        public void run() {
            TaskImpl.this.setNotes(notes);
        }
    });
}
	public void run() {
    TaskImpl.this.setNotes(notes);
}
	public void addNotes(final String notes) {
    myCommands.add(new Runnable() {

        public void run() {
            TaskImpl.this.addNotes(notes);
        }
    });
}
	public void run() {
    TaskImpl.this.addNotes(notes);
}
	public void setName(String name) {
    myName = name;
}
	public void setWebLink(String webLink) {
    myWebLink = webLink;
}
	public void setMilestone(boolean milestone) {
    isMilestone = milestone;
}
	public void setPriority(int priority) {
    myPriority = priority;
}
	public void setStart(GanttCalendar start) {
    GanttCalendar oldStart = myStart == null ? null : myStart.Clone();
    myStart = start;
    if (areEventsEnabled()) {
        myManager.fireTaskScheduleChanged(this, oldStart, getEnd());
    }
}
	public void setEnd(GanttCalendar end) {
    GanttCalendar oldFinish = myEnd == null ? null : myEnd.Clone();
    myEnd = end;
    int length = myStart.diff(end);
    myLength = getManager().createLength(myLength.getTimeUnit(), length);
    if (areEventsEnabled()) {
        myManager.fireTaskScheduleChanged(this, myStart.Clone(), oldFinish);
    }
}
	public void setDuration(TaskLength length) {
    GanttCalendar oldFinish = myEnd == null ? null : myEnd.Clone();
    myLength = length;
    myEnd = myStart.newAdd((int) length.getLength());
    if (areEventsEnabled()) {
        myManager.fireTaskScheduleChanged(this, myStart.Clone(), oldFinish);
    }
}
	public void setCompletionPercentage(int percentage) {
    myCompletionPercentage = percentage;
}
	public void setStartFixed(boolean isFixed) {
    isStartFixed = isFixed;
}
	public void setShape(ShapePaint shape) {
    myShape = shape;
}
	public void setColor(Color color) {
    myColor = color;
}
	public void setExpand(boolean expand) {
    bExpand = expand;
}
	protected void enableEvents(boolean enabled) {
    myEventsEnabled = enabled;
}
	protected boolean areEventsEnabled() {
    return myEventsEnabled;
}
	/**
     * Allows to determine, if a special color is defined for this task.
     * @return true, if this task has its own color defined.
     */
public boolean colorDefined() {
    return (myColor != null);
}
}