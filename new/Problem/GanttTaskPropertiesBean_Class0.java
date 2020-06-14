public class Class0 {
	private Object PATTERN_LIST;
	private Object insets;
	private Object WEST;
	private Object colorChooser;
	private Object CENTER;
	private Object gridx;
	private Object gridy;
	private Object top;
	private Object RELATIVE;
	private Object weighty;
	private Object taskDefaultColor;
	private Object ERROR;
	private Object end;
	private Object SOUTH;
	private Object EAST;
	private Object gridwidth;
	private Object gridheight;
	private Object YES_OPTION;
	private Object start;
	private Object right;
	private Object left;
	private Object anchor;

	public Class0(Object PATTERN_LIST, Object insets, Object WEST, Object colorChooser, Object CENTER, Object gridx, Object gridy, Object top, Object RELATIVE, Object weighty, Object taskDefaultColor, Object ERROR, Object end, Object SOUTH, Object EAST, Object gridwidth, Object gridheight, Object YES_OPTION, Object start, Object right, Object left, Object anchor){
		this.PATTERN_LIST = PATTERN_LIST;
		this.insets = insets;
		this.WEST = WEST;
		this.colorChooser = colorChooser;
		this.CENTER = CENTER;
		this.gridx = gridx;
		this.gridy = gridy;
		this.top = top;
		this.RELATIVE = RELATIVE;
		this.weighty = weighty;
		this.taskDefaultColor = taskDefaultColor;
		this.ERROR = ERROR;
		this.end = end;
		this.SOUTH = SOUTH;
		this.EAST = EAST;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
		this.YES_OPTION = YES_OPTION;
		this.start = start;
		this.right = right;
		this.left = left;
		this.anchor = anchor;
	}
	/**Construct the general panel*/
private void constructGeneralPanel() {
    generalPanel = new JPanel(new GridBagLayout());
    //first row
    nameLabel1 = new JLabel(language.getText("name") + ":");
    nameField1 = new JTextField(20);
    nameField1.setName("name_of_task");
    durationLabel1 = new JLabel(language.getText("length") + ":");
    durationField1 = new JTextField(8);
    durationField1.setName("length");
    firstRowPanel1 = new JPanel(flowL);
    setFirstRow(firstRowPanel1, gbc, nameLabel1, nameField1, durationLabel1, durationField1);
    //second row
    //Progress
    percentCompleteLabel1 = new JLabel(language.getText("advancement"));
    SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
    percentCompleteSlider = new JSpinner(spinnerModel);
    secondRowPanel1 = new JPanel(flowL);
    secondRowPanel1.add(percentCompleteLabel1);
    //secondRowPanel1.add(percentCompleteField1);
    secondRowPanel1.add(percentCompleteSlider);
    priorityLabel1 = new JLabel(language.getText("priority"));
    secondRowPanel1.add(priorityLabel1);
    priorityComboBox = new JComboBox();
    priorityComboBox.addItem(language.getText("low"));
    priorityComboBox.addItem(language.getText("normal"));
    priorityComboBox.addItem(language.getText("hight"));
    priorityComboBox.setEditable(false);
    secondRowPanel1.add(priorityComboBox);
    //third row
    startDateLabel1 = new JLabel(language.getText("dateOfBegining") + ":");
    startDateField1 = new JTextField(12);
    startDateField1.setEditable(false);
    finishDateLabel1 = new JLabel(language.getText("dateOfEnd") + ":");
    finishDateField1 = new JTextField(12);
    finishDateField1.setEditable(false);
    ImageIcon icon = new ImageIcon(getClass().getResource("/icons/calendar_16.gif"));
    startDateButton1 = new TestGanttRolloverButton(icon);
    startDateButton1.setName("start");
    startDateButton1.setToolTipText(GanttProject.getToolTip(language.getText("chooseDate")));
    finishDateButton1 = new TestGanttRolloverButton(icon);
    finishDateButton1.setName("finish");
    finishDateButton1.setToolTipText(GanttProject.getToolTip(language.getText("chooseDate")));
    thirdRowPanel1 = new JPanel(flowL);
    thirdRowPanel1.setBorder(new TitledBorder(new EtchedBorder(), language.getText("date")));
    thirdRowPanel1.add(startDateLabel1);
    thirdRowPanel1.add(startDateField1);
    thirdRowPanel1.add(startDateButton1);
    thirdRowPanel1.add(finishDateLabel1);
    thirdRowPanel1.add(finishDateField1);
    thirdRowPanel1.add(finishDateButton1);
    //fourth row
    //Milestone
    mileStoneCheckBox1 = new JCheckBox(language.getText("meetingPoint"));
    lastRowPanel1 = new JPanel(flowL);
    lastRowPanel1.add(mileStoneCheckBox1);
    shapeComboBox = new JPaintCombo(ShapeConstants.PATTERN_LIST);
    JPanel shapePanel = new JPanel();
    shapePanel.setLayout(new BorderLayout());
    JLabel lshape = new JLabel("  " + language.getText("shape") + " ");
    shapeComboBox = new JPaintCombo(ShapeConstants.PATTERN_LIST);
    shapePanel.add(lshape, BorderLayout.WEST);
    shapePanel.add(shapeComboBox, BorderLayout.CENTER);
    colorButton = new JButton(language.getText("colorButton"));
    colorButton.setBackground(selectedTask.getColor());
    final String colorChooserTitle = language.getText("selectColor");
    colorButton.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            JDialog dialog;
            dialog = JColorChooser.createDialog(parent, colorChooserTitle, true, GanttDialogProperties.colorChooser, new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    colorButton.setBackground(GanttDialogProperties.colorChooser.getColor());
                }
            }, new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                // nothing to do for "Cancel"
                }
            });
            /*AbstractColorChooserPanel[] panels = GanttDialogProperties.colorChooser.getChooserPanels();
        GanttDialogProperties.colorChooser.removeChooserPanel(panels[0]);
        GanttDialogProperties.colorChooser.addChooserPanel(panels[0]);*/
            GanttDialogProperties.colorChooser.setColor(colorButton.getBackground());
            dialog.show();
        }
    });
    colorSpace = new JButton(language.getText("defaultColor"));
    colorSpace.setBackground(GanttGraphicArea.taskDefaultColor);
    colorSpace.setToolTipText(GanttProject.getToolTip(language.getText("resetColor")));
    colorSpace.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            colorButton.setBackground(GanttGraphicArea.taskDefaultColor);
        }
    });
    colorPanel = new JPanel();
    colorPanel.setLayout(new BorderLayout());
    colorPanel.add(colorButton, "West");
    colorPanel.add(colorSpace, "Center");
    colorPanel.add(shapePanel, BorderLayout.EAST);
    lastRowPanel1.add(colorPanel);
    //---Set GridBagConstraints constant
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets.right = 15;
    gbc.insets.left = 10;
    gbc.insets.top = 10;
    addUsingGBL(generalPanel, firstRowPanel1, gbc, 0, 0, 1, 1);
    addUsingGBL(generalPanel, secondRowPanel1, gbc, 0, 1, 1, 1);
    addUsingGBL(generalPanel, thirdRowPanel1, gbc, 0, 2, 1, 1);
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = GridBagConstraints.RELATIVE;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.weighty = 1;
    generalPanel.add(lastRowPanel1, gbc);
    //The panel for the web link
    webLinkPanel = new JPanel(flowL);
    lblWebLink = new JLabel(language.getText("webLink"));
    webLinkPanel.add(lblWebLink);
    tfWebLink = new JTextField(30);
    webLinkPanel.add(tfWebLink);
    bWebLink = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/web_16.gif")));
    bWebLink.setToolTipText(GanttProject.getToolTip(language.getText("openWebLink")));
    webLinkPanel.add(bWebLink);
    bWebLink.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            //link to open the web link
            try {
                if (!BrowserControl.displayURL(tfWebLink.getText())) {
                    GanttDialogInfo gdi = new GanttDialogInfo(null, GanttDialogInfo.ERROR, GanttDialogInfo.YES_OPTION, language.getText("msg4"), language.getText("error"));
                    gdi.show();
                }
            } catch (Exception ex) {
            }
        }
    });
    gbc.gridy = 4;
    generalPanel.add(webLinkPanel, gbc);
}
	/** Change the name of the task on all textfiled of task name */
public void changeNameOfTask() {
    if (nameField1 != null && nameFieldNotes != null) {
        String nameOfTask = nameField1.getText().trim();
        nameField1.setText(nameOfTask);
        myDependenciesPanel.nameChanged(nameOfTask);
        myAllocationsPanel.nameChanged(nameOfTask);
        nameFieldNotes.setText(nameOfTask);
    }
}
	/** Set the duration of the task */
public void changeLength(int _length) {
    if (_length <= 0) {
        _length = 1;
    }
    durationField1.setText(_length + "");
    myDependenciesPanel.durationChanged(_length);
    myAllocationsPanel.durationChanged(_length);
    durationFieldNotes.setText(_length + "");
    length = _length;
    //change the end date
    GanttCalendar _end = start.newAdd(length);
    this.end = _end;
    finishDateField1.setText(_end.toString());
}
	/** @return the web link of the task. */
public String getWebLink() {
    String text = tfWebLink.getText();
    return text == null ? "" : text.trim();
}
	/** Return the start date of the task */
public GanttCalendar getStart() {
    start.setFixed(isStartFixed);
    return start;
}
	/** Change the start date of the task */
public void setStart(GanttCalendar dstart, boolean test) {
    if (test == true) {
        startDateField1.setText(dstart.toString());
        this.start = dstart;
        return;
    }
    startDateField1.setText(dstart.toString());
    this.start = dstart;
    this.setStartFixed(dstart.isFixed());
    if (this.start.compareTo(this.end) < 0)
        length = start.diff(end);
    else {
        GanttCalendar _end = start.newAdd(length);
        this.end = _end;
        finishDateField1.setText(_end.toString());
    }
    durationField1.setText("" + length);
    myAllocationsPanel.durationChanged(length);
    //durationField2.setText(""+length);
    //durationField3.setText("" + length);
    durationFieldNotes.setText("" + length);
}
	/** Change the end date of the task */
public void setEnd(GanttCalendar dend, boolean test) {
    if (test == true) {
        finishDateField1.setText(dend.toString());
        this.end = dend;
        return;
    }
    finishDateField1.setText(dend.toString());
    this.end = dend;
    if (this.start.compareTo(this.end) < 0)
        length = this.start.diff(this.end);
    else {
        GanttCalendar _start = this.end.newAdd(-length);
        this.start = _start;
        startDateField1.setText(_start.toString());
    }
    durationField1.setText("" + length);
    //durationField2.setText(""+length);
    myAllocationsPanel.durationChanged(length);
    durationFieldNotes.setText("" + length);
}
	/** Init the widgets */
public void init() {
    tabbedPane = new JTabbedPane();
    tabbedPane.getModel().addChangeListener(new ChangeListener() {

        public void stateChanged(ChangeEvent e) {
            changeNameOfTask();
            fireDurationChanged();
        }
    });
    constructGeneralPanel();
    tabbedPane.addTab(language.getText("general"), new ImageIcon(getClass().getResource("/icons/properties_16.gif")), generalPanel);
    constructPredecessorsPanel();
    tabbedPane.addTab(language.getText("predecessors"), new ImageIcon(getClass().getResource("/icons/relashion.gif")), predecessorsPanel);
    constructResourcesPanel();
    tabbedPane.addTab(GanttProject.correctLabel(language.getText("human")), new ImageIcon(getClass().getResource("/icons/res_16.gif")), resourcesPanel);
    constructNotesPanel();
    tabbedPane.addTab(language.getText("notesTask"), new ImageIcon(getClass().getResource("/icons/note_16.gif")), notesPanel);
    setLayout(new BorderLayout());
    add(tabbedPane, BorderLayout.CENTER);
    constructSouthPanel();
    add(southPanel, BorderLayout.SOUTH);
}
	/** Add the differents action listener on the differents widgets */
public void addActionListener(ActionListener l) {
    nameField1.addActionListener(l);
    startDateButton1.addActionListener(l);
    finishDateButton1.addActionListener(l);
    okButton.addActionListener(l);
    cancelButton.addActionListener(l);
    durationField1.addActionListener(l);
}
}