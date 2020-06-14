public class Class0 {
	private Object VK_R;
	private Object area;
	private Object VK_S;
	private Object VK_L;
	private Object TEXT;
	private Object VK_N;
	private Object VK_O;
	private Object VK_P;
	private Object VK_Q;
	private Object nbTasks;
	private Object VK_D;
	private Object VK_E;
	private Object VK_G;
	private Object VK_I;
	private Object ICONS;

	public Class0(Object VK_R, Object area, Object VK_S, Object VK_L, Object TEXT, Object VK_N, Object VK_O, Object VK_P, Object VK_Q, Object nbTasks, Object VK_D, Object VK_E, Object VK_G, Object VK_I, Object ICONS){
		this.VK_R = VK_R;
		this.area = area;
		this.VK_S = VK_S;
		this.VK_L = VK_L;
		this.TEXT = TEXT;
		this.VK_N = VK_N;
		this.VK_O = VK_O;
		this.VK_P = VK_P;
		this.VK_Q = VK_Q;
		this.nbTasks = nbTasks;
		this.VK_D = VK_D;
		this.VK_E = VK_E;
		this.VK_G = VK_G;
		this.VK_I = VK_I;
		this.ICONS = ICONS;
	}
	/** @return the options of ganttproject. */
public GanttOptions getOptions() {
    return options;
}
	/** @return the status Bar of the main frame. */
public GanttStatusBar getStatusBar() {
    return statusBar;
}
	/** Create memonic for keyboard */
public void setMemonic() {
    final int MENU_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    //--NEW----------------------------------
    //miNew.setMnemonic(KeyEvent.VK_N);
    miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, MENU_MASK));
    //--OPEN----------------------------------
    miOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, MENU_MASK));
    //Open from the web
    //miOpenURL.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, MENU_MASK));
    //--SAVE----------------------------------
    miSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, MENU_MASK));
    //--EXPORT----------------------------------
    miExport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, MENU_MASK));
    //		--IMPORT----------------------------------
    miImport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, MENU_MASK));
    //--PRINT----------------------------------
    miPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, MENU_MASK));
    //--QUIT----------------------------------
    miQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, MENU_MASK));
    //--CUT----------------------------------
    //miCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
    // MENU_MASK));
    //--COPY----------------------------------
    //miCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
    // MENU_MASK));
    //--PASTE----------------------------------
    //miPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
    // MENU_MASK));
    //--OPTIONS----------------------------------
    miOptions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, MENU_MASK));
    //--NEW TASK----------------------------------
    //		miNewTask.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
    //				MENU_MASK));
    //--PROPERTIES TASK----------------------------------
    miPropertiesTask.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, MENU_MASK));
    //--DELETE TASK----------------------------------
    miDeleteTask.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, MENU_MASK));
    //--NEW HUMAN----------------------------------
    //miNewHuman.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, MENU_MASK));
    //miDelHuman.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, MENU_MASK));
    miPropHuman.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, MENU_MASK));
}
	/** Set the menus language after the user select a different language */
private void changeLanguageOfMenu() {
    mProject = changeMenuLabel(mProject, language.getText("project"));
    mEdit = changeMenuLabel(mEdit, language.getText("edit"));
    mTask = changeMenuLabel(mTask, language.getText("task"));
    mHuman = changeMenuLabel(mHuman, language.getText("human"));
    mHelp = changeMenuLabel(mHelp, language.getText("help"));
    mCalendar = changeMenuLabel(mCalendar, language.getText("calendars"));
    miNew = changeMenuLabel(miNew, language.getText("newProject"));
    miOpen = changeMenuLabel(miOpen, language.getText("openProject"));
    mMRU = changeMenuLabel(mMRU, language.getText("lastOpen"));
    miSave = changeMenuLabel(miSave, language.getText("saveProject"));
    miSaveAs = changeMenuLabel(miSaveAs, language.getText("saveAsProject"));
    mServer = changeMenuLabel(mServer, language.getText("webServer"));
    miOpenURL = changeMenuLabel(miOpenURL, language.getText("openFromServer"));
    miSaveAsURL = changeMenuLabel(miSaveAsURL, language.getText("saveToServer"));
    miExport = changeMenuLabel(miExport, language.getText("export"));
    miImport = changeMenuLabel(miImport, language.getText("import"));
    miPrint = changeMenuLabel(miPrint, language.getText("printProject"));
    miPreview = changeMenuLabel(miPreview, language.getText("preview"));
    miQuit = changeMenuLabel(miQuit, language.getText("quit"));
    miCut = changeMenuLabel(miCut, language.getText("cut"));
    miCopy = changeMenuLabel(miCopy, language.getText("copy"));
    miPaste = changeMenuLabel(miPaste, language.getText("paste"));
    miOptions = changeMenuLabel(miOptions, language.getText("settings"));
    //miNewTask = changeMenuLabel(miNewTask, language.getText("createTask"));
    miDeleteTask = changeMenuLabel(miDeleteTask, language.getText("deleteTask"));
    miPropertiesTask = changeMenuLabel(miPropertiesTask, language.getText("propertiesTask"));
    mHuman.insert(changeMenuLabel(mHuman.getItem(0), language.getText("newHuman")), 0);
    miDelHuman = changeMenuLabel(miDelHuman, language.getText("deleteHuman"));
    miPropHuman = changeMenuLabel(miPropHuman, language.getText("propertiesHuman"));
    mHuman.insert(changeMenuLabel(mHuman.getItem(3), language.getText("importResources")), 3);
    miSendMailHuman = changeMenuLabel(miSendMailHuman, language.getText("sendMail"));
    miEditCalendar = changeMenuLabel(miEditCalendar, language.getText("editCalendars"));
    miPrjCal = changeMenuLabel(miPrjCal, language.getText("projectCalendar"));
    miWebPage = changeMenuLabel(miWebPage, language.getText("webPage"));
    miAbout = changeMenuLabel(miAbout, language.getText("about"));
    miTips = changeMenuLabel(miTips, language.getText("tipsOfTheDay"));
    miManual = changeMenuLabel(miManual, language.getText("manual"));
    ////////////////////////////////////////////
    bNew.setToolTipText(getToolTip(correctLabel(language.getText("newProject"))));
    bOpen.setToolTipText(getToolTip(correctLabel(language.getText("openProject"))));
    bSave.setToolTipText(getToolTip(correctLabel(language.getText("saveProject"))));
    bSaveAs.setToolTipText(getToolTip(correctLabel(language.getText("saveAsProject"))));
    bPrint.setToolTipText(getToolTip(correctLabel(language.getText("printProject"))));
    bExport.setToolTipText(getToolTip(correctLabel(language.getText("export"))));
    bImport.setToolTipText(getToolTip(correctLabel(language.getText("import"))));
    bNewTask.setToolTipText(getToolTip(correctLabel(language.getText("createTask"))));
    bDelete.setToolTipText(getToolTip(correctLabel(language.getText("deleteTask"))));
    bProperties.setToolTipText(getToolTip(correctLabel(language.getText("propertiesTask"))));
    bUnlink.setToolTipText(getToolTip(correctLabel(language.getText("unlink"))));
    bLink.setToolTipText(getToolTip(correctLabel(language.getText("link"))));
    bInd.setToolTipText(getToolTip(correctLabel(language.getText("indentTask"))));
    bUnind.setToolTipText(getToolTip(correctLabel(language.getText("dedentTask"))));
    bUp.setToolTipText(getToolTip(correctLabel(language.getText("upTask"))));
    bDown.setToolTipText(getToolTip(correctLabel(language.getText("downTask"))));
    bPrev.setToolTipText(getToolTip(correctLabel(language.getText("backDate"))));
    bNext.setToolTipText(getToolTip(correctLabel(language.getText("forwardDate"))));
    bZoomIn.setToolTipText(getToolTip(correctLabel(language.getText("zoomIn"))));
    bZoomOut.setToolTipText(getToolTip(correctLabel(language.getText("zoomOut"))));
    //bZoomFit.setToolTipText(getToolTip(language.zoomFit()));
    tabpane.setTitleAt(1, correctLabel(language.getText("human")));
    setButtonText();
}
	/** Return the tooltip in html (with yello bgcolor */
public static String getToolTip(String msg) {
    return "<html><body bgcolor=#EAEAEA>" + msg + "</body></html>";
}
	/** Set the text on the buttons.*/
public void setButtonText() {
    if (options.getButtonShow() == GanttOptions.ICONS) {
        bNew.setText("");
        bOpen.setText("");
        bSave.setText("");
        bSaveAs.setText("");
        bNewTask.setText("");
        bImport.setText("");
        bExport.setText("");
        bPrint.setText("");
        bDelete.setText("");
        bProperties.setText("");
        bUnlink.setText("");
        bLink.setText("");
        bInd.setText("");
        bUnind.setText("");
        bUp.setText("");
        bDown.setText("");
        bPrev.setText("");
        bNext.setText("");
        bZoomOut.setText("");
        bZoomIn.setText("");
    } else {
        bNew.setText(correctLabel(language.getText("newProject")));
        bOpen.setText(correctLabel(language.getText("openProject")));
        bSave.setText(correctLabel(language.getText("saveProject")));
        bSaveAs.setText(correctLabel(language.getText("saveAsProject")));
        bImport.setText(correctLabel(language.getText("import")));
        bExport.setText(correctLabel(language.getText("export")));
        bPrint.setText(correctLabel(language.getText("printProject")));
        bNewTask.setText(correctLabel(language.getText(tabpane.getSelectedIndex() == 0 ? "createTask" : "newHuman")));
        bDelete.setText(correctLabel(language.getText(tabpane.getSelectedIndex() == 0 ? "deleteTask" : "deleteHuman")));
        bProperties.setText(correctLabel(language.getText(tabpane.getSelectedIndex() == 0 ? "propertiesTask" : "propertiesHuman")));
        bUnlink.setText(correctLabel(language.getText("unlink")));
        bLink.setText(correctLabel(language.getText("link")));
        bInd.setText(correctLabel(language.getText("indentTask")));
        bUnind.setText(correctLabel(language.getText("dedentTask")));
        bUp.setText(correctLabel(language.getText("upTask")));
        bDown.setText(correctLabel(language.getText("downTask")));
        bPrev.setText(correctLabel(language.getText("backDate")));
        bNext.setText(correctLabel(language.getText("forwardDate")));
        bZoomOut.setText(correctLabel(language.getText("zoomOut")));
        bZoomIn.setText(correctLabel(language.getText("zoomIn")));
    }
}
	/** Apply Buttons options. */
public void applyButtonOptions() {
    setButtonText();
    if (options.getButtonShow() == GanttOptions.TEXT) {
        //remove the icons
        bNew.setDefaultIcon(null);
        bOpen.setDefaultIcon(null);
        bSave.setDefaultIcon(null);
        bSaveAs.setDefaultIcon(null);
        bImport.setDefaultIcon(null);
        bExport.setDefaultIcon(null);
        bPrint.setDefaultIcon(null);
        bNewTask.setDefaultIcon(null);
        bDelete.setDefaultIcon(null);
        bProperties.setDefaultIcon(null);
        bUnlink.setDefaultIcon(null);
        bLink.setDefaultIcon(null);
        bInd.setDefaultIcon(null);
        bUnind.setDefaultIcon(null);
        bUp.setDefaultIcon(null);
        bDown.setDefaultIcon(null);
        bPrint.setDefaultIcon(null);
        bPrev.setDefaultIcon(null);
        bNext.setDefaultIcon(null);
        bZoomOut.setDefaultIcon(null);
        bZoomIn.setDefaultIcon(null);
    } else {
        //set the approrpiate icons
        bNew.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/new_" + options.getIconSize() + ".gif")));
        bOpen.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/open_" + options.getIconSize() + ".gif")));
        bSave.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/save_" + options.getIconSize() + ".gif")));
        bSaveAs.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/saveas_" + options.getIconSize() + ".gif")));
        bImport.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/import_" + options.getIconSize() + ".gif")));
        bExport.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/export_" + options.getIconSize() + ".gif")));
        bPrint.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/print_" + options.getIconSize() + ".gif")));
        bNewTask.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/insert_" + options.getIconSize() + ".gif")));
        bDelete.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/delete_" + options.getIconSize() + ".gif")));
        bProperties.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/properties_" + options.getIconSize() + ".gif")));
        bUnlink.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/unlink_" + options.getIconSize() + ".gif")));
        bLink.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/link_" + options.getIconSize() + ".gif")));
        bInd.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/indent_" + options.getIconSize() + ".gif")));
        bUnind.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/unindent_" + options.getIconSize() + ".gif")));
        bUp.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/up_" + options.getIconSize() + ".gif")));
        bDown.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/down_" + options.getIconSize() + ".gif")));
        bPrev.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/prev_" + options.getIconSize() + ".gif")));
        bNext.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/next_" + options.getIconSize() + ".gif")));
        bZoomOut.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/zoomm_" + options.getIconSize() + ".gif")));
        bZoomIn.setDefaultIcon(new ImageIcon(getClass().getResource("/icons/zoomp_" + options.getIconSize() + ".gif")));
    }
}
	/** Edit task parameters */
public void propertiesTask() {
    tabpane.setSelectedIndex(0);
    propertiesTask(tree.getSelectedNode());
}
	/** Edit task parameters */
public void propertiesTask() {
    tabpane.setSelectedIndex(0);
    propertiesTask(tree.getSelectedNode());
}
	/** Refresh the informations of the project on the status bar. */
public void refreshProjectInfos() {
    if (tree.nbTasks == 0 && resp.nbPeople() == 0)
        statusBar.setSecondText("");
    else
        statusBar.setSecondText(correctLabel(language.getText("task")) + " : " + tree.nbTasks + "  " + correctLabel(language.getText("resources")) + " " + resp.nbPeople());
}
	/** Copy GanttGraphicArea parameters to Resource panel area */
public void setResourcePanelToGraphicArea() {
    getResourcePanel().area.setZoom(area.getZoom());
    getResourcePanel().area.setDate(area.getDate());
    getResourcePanel().area.repaint();
}
	/** Open a remote project file with dialog box (GanttURLChooser) */
public void openURL() {
    openURL(projectDocument);
}
	/** Open a remote project file with dialog box (GanttURLChooser) */
public void openURL() {
    openURL(projectDocument);
}
	/** Save the project on a server (with a GanttURLChooser) */
public boolean saveAsURLProject() throws IOException {
    return saveAsURLProject(projectDocument);
}
	/** Save the project on a server (with a GanttURLChooser) */
public boolean saveAsURLProject() throws IOException {
    return saveAsURLProject(projectDocument);
}
	/** Save the project on a file */
public void saveProject() throws IOException {
    saveProject(projectDocument);
}
	/** Save the project on a file */
public void saveProject() throws IOException {
    saveProject(projectDocument);
}
	/** @return the uiconfiguration. */
public UIConfiguration getUIConfiguration() {
    return myUIConfiguration;
}
	/** Function that launch the dialog to edit project properties */
/*
	 * public void editSettings() { GanttDialogSettings ds = new
	 * GanttDialogSettings(this, language); ds.show(); if (ds.change) {
	 * setAskForSave(ds.change); } }
	 */
/** Quit the application */
public void quitApplication() {
    options.setWindowPosition(getX(), getY());
    options.setWindowSize(getWidth(), getHeight());
    options.setUIConfiguration(myUIConfiguration);
    options.setDocumentsMRU(documentsMRU);
    options.setLookAndFeel(lookAndFeel);
    options.setToolBarPosition(toolBar.getOrientation());
    options.save();
    if (checkCurrentProject()) {
        closeProject();
        setVisible(false);
        dispose();
        System.exit(0);
    } else {
        setVisible(true);
    }
}
	/* (non-Javadoc)
	 * @see net.sourceforge.ganttproject.IGanttProject#getI18n()
	 */
public GanttLanguage getI18n() {
    return getLanguage();
}
}