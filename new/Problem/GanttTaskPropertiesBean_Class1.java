public class Class1 {
	private Object GRAY;
	private Object insets;
	private Object PATTERN_LIST;
	private Object WEST;
	private Object gridx;
	private Object gridy;
	private Object top;
	private Object white;
	private Object weighty;
	private Object weightx;
	private Object BLACK;
	private Object gridwidth;
	private Object gridheight;
	private Object length;
	private Object tree;
	private Object TRAILING;
	private Object right;
	private Object selectedTask;
	private Object left;
	private Object anchor;

	public Class1(Object GRAY, Object insets, Object PATTERN_LIST, Object WEST, Object gridx, Object gridy, Object top, Object white, Object weighty, Object weightx, Object BLACK, Object gridwidth, Object gridheight, Object length, Object tree, Object TRAILING, Object right, Object selectedTask, Object left, Object anchor){
		this.GRAY = GRAY;
		this.insets = insets;
		this.PATTERN_LIST = PATTERN_LIST;
		this.WEST = WEST;
		this.gridx = gridx;
		this.gridy = gridy;
		this.top = top;
		this.white = white;
		this.weighty = weighty;
		this.weightx = weightx;
		this.BLACK = BLACK;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
		this.length = length;
		this.tree = tree;
		this.TRAILING = TRAILING;
		this.right = right;
		this.selectedTask = selectedTask;
		this.left = left;
		this.anchor = anchor;
	}
	/**set the first row in all the tabbed pane. thus give them a common look*/
private void setFirstRow(Container container, GridBagConstraints gbc, JLabel nameLabel, JTextField nameField, JLabel durationLabel, JTextField durationField) {
    container.setLayout(new GridBagLayout());
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets.right = 15;
    gbc.insets.left = 10;
    gbc.insets.top = 10;
    addUsingGBL(container, nameLabel, gbc, 0, 0, 1, 1);
    addUsingGBL(container, nameField, gbc, 1, 0, 1, 1);
    addUsingGBL(container, durationLabel, gbc, 2, 0, 1, 1);
    gbc.weightx = 1;
    addUsingGBL(container, durationField, gbc, 3, 0, 1, 1);
}
	/**construct the notes pannel*/
private void constructNotesPanel() {
    notesPanel = new JPanel(new GridBagLayout());
    //first row
    nameLabelNotes = new JLabel(language.getText("name") + ":");
    nameFieldNotes = new JTextField(20);
    durationLabelNotes = new JLabel(language.getText("length") + ":");
    durationFieldNotes = new JTextField(8);
    nameFieldNotes.setEditable(false);
    durationFieldNotes.setEditable(false);
    firstRowPanelNotes = new JPanel();
    setFirstRow(firstRowPanelNotes, gbc, nameLabelNotes, nameFieldNotes, durationLabelNotes, durationFieldNotes);
    secondRowPanelNotes = new JPanel();
    secondRowPanelNotes.setBorder(new TitledBorder(new EtchedBorder(), language.getText("notesTask") + ":"));
    noteAreaNotes = new JTextArea(8, 40);
    noteAreaNotes.setLineWrap(true);
    noteAreaNotes.setWrapStyleWord(true);
    noteAreaNotes.setBackground(new Color(1.0f, 1.0f, 1.0f));
    scrollPaneNotes = new JScrollPane(noteAreaNotes);
    secondRowPanelNotes.add(scrollPaneNotes);
    JButton bdate = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/clock_16.gif")));
    bdate.setToolTipText(GanttProject.getToolTip(language.getText("putDate")));
    bdate.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent evt) {
            noteAreaNotes.append("\n" + GanttCalendar.getDateAndTime() + "\n");
        }
    });
    secondRowPanelNotes.add(bdate);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets.right = 15;
    gbc.insets.left = 10;
    gbc.insets.top = 10;
    gbc.weighty = 0;
    addUsingGBL(notesPanel, firstRowPanelNotes, gbc, 0, 0, 1, 1);
    gbc.weighty = 1;
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    notesPanel.add(secondRowPanelNotes, gbc);
}
	/**add a component to container by using GridBagConstraints.*/
private void addUsingGBL(Container container, Component component, GridBagConstraints gbc, int x, int y, int w, int h) {
    gbc.gridx = x;
    gbc.gridy = y;
    gbc.gridwidth = w;
    gbc.gridheight = h;
    gbc.weighty = 0;
    container.add(component, gbc);
}
	/**Construct the predecessors tabbed pane*/
private void constructPredecessorsPanel() {
    myDependenciesPanel = new TaskDependenciesPanel(selectedTask);
    predecessorsPanel = myDependenciesPanel.getComponent();
}
	/**Construct the resources panel*/
private void constructResourcesPanel() {
    myAllocationsPanel = new TaskAllocationsPanel(selectedTask, myHumanResourceManager);
    resourcesPanel = myAllocationsPanel.getComponent();
}
	/**Construct the south panel*/
private void constructSouthPanel() {
    okButton = new JButton(language.getText("ok"));
    okButton.setName("ok");
    if (getRootPane() != null)
        //set ok the defuault button when press "enter"  --> check because getRootPane()==null !!!
        getRootPane().setDefaultButton(okButton);
    cancelButton = new JButton(language.getText("cancel"));
    cancelButton.setName("cancel");
    southPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 50, 10));
    southPanel.add(okButton);
    southPanel.add(cancelButton);
}
	public void stateChanged(ChangeEvent e) {
    changeNameOfTask();
    fireDurationChanged();
}
	//Input methods
/**as the name indicated, it will not replace the original GanttTask in the Tree. */
public Task getReturnTask() {
    myAllocationsPanel.getTableModel().commit();
    GanttTask returnTask = selectedTask;
    //returnTask.setTaskID(selectedTask.getTaskID());
    //getName()
    returnTask.setName(getTaskName());
    //getName()
    returnTask.setWebLink(getWebLink());
    returnTask.setMilestone(isBilan());
    returnTask.setChecked(false);
    returnTask.setStart(getStart());
    returnTask.setEnd(getEnd());
    returnTask.setLength(getLength());
    returnTask.setNotes(getNotes());
    returnTask.setCompletionPercentage(getPercentComplete());
    returnTask.setPriority(getPriority());
    returnTask.setStartFixed(isStartFixed);
    returnTask.setColor(colorButton.getBackground());
    if (//only if it's not the default shape
    shapeComboBox.getSelectedIndex() != 0)
        returnTask.setShape(new ShapePaint((ShapePaint) shapeComboBox.getSelectedPaint(), Color.white, colorButton.getBackground()));
    myDependenciesPanel.getTableModel().commit();
    return returnTask;
}
	/**as the name indicated*/
public void setSelectedTask(GanttTask selectedTask) {
    this.selectedTask = selectedTask;
    nameField1.setText(selectedTask.getName());
    //nameField2.setText(selectedTask.toString());
    nameFieldNotes.setText(selectedTask.toString());
    setName(selectedTask.toString());
    durationField1.setText(selectedTask.getLength() + "");
    //durationField2.setText(selectedTask.getLength() + "");
    durationFieldNotes.setText(selectedTask.getLength() + "");
    percentCompleteSlider.setValue(new Integer(selectedTask.getCompletionPercentage()));
    percentCompleteLabel1.setText(language.getText("advancement"));
    priorityComboBox.setSelectedIndex(selectedTask.getPriority());
    startDateField1.setText(selectedTask.getStart().toString());
    finishDateField1.setText(selectedTask.getEnd().toString());
    setStart(selectedTask.getStart().Clone(), true);
    setEnd(selectedTask.getEnd().Clone(), true);
    bilan = selectedTask.isMilestone();
    mileStoneCheckBox1.setSelected(bilan);
    tfWebLink.setText(selectedTask.getWebLink());
    if (selectedTask.shapeDefined()) {
        for (int i = 0; i < ShapeConstants.PATTERN_LIST.length; i++) {
            if (selectedTask.getShape().equals(ShapeConstants.PATTERN_LIST[i])) {
                shapeComboBox.setSelectedIndex(i);
                break;
            }
        }
    }
    noteAreaNotes.setText(selectedTask.getNotes());
    setStartFixed(selectedTask.isStartFixed());
}
	/**as the name indicated*/
public void setTree(GanttTree tree) {
    this.tree = tree;
}
	//Output methods
/**as the name indicated*/
public boolean isBilan() {
    bilan = mileStoneCheckBox1.isSelected();
    return bilan;
}
	/**as the name indicated*/
public GanttCalendar getEnd() {
    return end;
}
	/**as the name indicated*/
public int getLength() {
    length = Integer.parseInt(durationField1.getText().trim());
    return length;
}
	public void fireDurationChanged() {
    String value = durationField1.getText();
    try {
        int duration = Integer.parseInt(value);
        changeLength(duration);
    } catch (NumberFormatException e) {
    }
}
	/**as the name indicated*/
public String getNotes() {
    notes = noteAreaNotes.getText();
    return notes;
}
	/** Return the name of the task*/
public String getTaskName() {
    String text = nameField1.getText();
    return text == null ? "" : text.trim();
}
	/**as the name indicated*/
public int getPercentComplete() {
    percentComplete = ((Integer) percentCompleteSlider.getValue()).hashCode();
    return percentComplete;
}
	/** Return the priority level of the task */
public int getPriority() {
    priority = priorityComboBox.getSelectedIndex();
    return priority;
}
	public void setStartFixed(boolean startFixed) {
    isStartFixed = startFixed;
    startDateField1.setForeground(isStartFixed ? Color.BLACK : Color.GRAY);
}
}