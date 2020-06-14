public class Class4 {
	private Object Access;
	private Object password;
	private Object ERROR;
	private Object YES_OPTION;
	private Object FORMAT_JPG;
	private Object YES_NO_CANCEL_OPTION;
	private Object out;
	private Object err;
	private Object change;
	private Object CANCEL;
	private Object fileurl;
	private Object myFile;
	private Object myStorageOptions;
	private Object FORMAT_PNG;
	private Object area;
	private Object res;
	private Object WARNING;
	private Object myFormat;
	private Object FORMAT_PDF;
	private Object NO;
	private Object YES_NO_OPTION;
	private Object YES;
	private Object QUESTION;
	private Object FORMAT_CSV;
	private Object FORMAT_XFIG;
	private Object length;
	private Object APPROVE_OPTION;
	private Object userName;
	private Object FORMAT_HTML;

	public Class4(Object Access, Object password, Object ERROR, Object YES_OPTION, Object FORMAT_JPG, Object YES_NO_CANCEL_OPTION, Object out, Object err, Object change, Object CANCEL, Object fileurl, Object myFile, Object myStorageOptions, Object FORMAT_PNG, Object area, Object res, Object WARNING, Object myFormat, Object FORMAT_PDF, Object NO, Object YES_NO_OPTION, Object YES, Object QUESTION, Object FORMAT_CSV, Object FORMAT_XFIG, Object length, Object APPROVE_OPTION, Object userName, Object FORMAT_HTML){
		this.Access = Access;
		this.password = password;
		this.ERROR = ERROR;
		this.YES_OPTION = YES_OPTION;
		this.FORMAT_JPG = FORMAT_JPG;
		this.YES_NO_CANCEL_OPTION = YES_NO_CANCEL_OPTION;
		this.out = out;
		this.err = err;
		this.change = change;
		this.CANCEL = CANCEL;
		this.fileurl = fileurl;
		this.myFile = myFile;
		this.myStorageOptions = myStorageOptions;
		this.FORMAT_PNG = FORMAT_PNG;
		this.area = area;
		this.res = res;
		this.WARNING = WARNING;
		this.myFormat = myFormat;
		this.FORMAT_PDF = FORMAT_PDF;
		this.NO = NO;
		this.YES_NO_OPTION = YES_NO_OPTION;
		this.YES = YES;
		this.QUESTION = QUESTION;
		this.FORMAT_CSV = FORMAT_CSV;
		this.FORMAT_XFIG = FORMAT_XFIG;
		this.length = length;
		this.APPROVE_OPTION = APPROVE_OPTION;
		this.userName = userName;
		this.FORMAT_HTML = FORMAT_HTML;
	}
	/** A menu has been activate */
public void actionPerformed(ActionEvent evt) {
    if (evt.getSource() instanceof JMenuItem) {
        String arg = evt.getActionCommand();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("newProject")))) {
            newProject();
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("openProject")))) {
            try {
                if (checkCurrentProject()) {
                    openFile();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (isVisible()) {
                    GanttDialogInfo gdi = new GanttDialogInfo(this, GanttDialogInfo.ERROR, GanttDialogInfo.YES_OPTION, language.getText("msg8"), language.getText("error"));
                    gdi.show();
                } else
                    System.out.println("\n====" + language.getText("error") + "====\n" + language.getText("msg8") + "\n");
            }
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("openFromServer")))) {
            if (checkCurrentProject()) {
                openURL();
            }
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("saveProject")))) {
            try {
                saveProject();
            } catch (Exception e) {
                System.err.println(e);
            }
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("saveAsProject")))) {
            try {
                saveAsProject();
            } catch (Exception e) {
                System.err.println(e);
            }
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("saveToServer")))) {
            try {
                saveAsURLProject();
            } catch (Exception e) {
                System.err.println(e);
            }
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("export")))) {
            export();
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("import")))) {
            importcbk();
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("printProject")))) {
            printProject();
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("preview")))) {
            previewPrint();
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("quit")))) {
            quitApplication();
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("deleteTask")))) {
            deleteTasks();
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("propertiesTask")))) {
            propertiesTask();
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("upTask")))) {
            upDatas();
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("downTask")))) {
            downDatas();
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("indentTask")))) {
            tree.indentCurrentNodes();
            setAskForSave(true);
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("dedentTask")))) {
            tree.dedentCurrentNodes();
            setAskForSave(true);
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("unlink")))) {
            unlinkRelationships();
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("link")))) {
            linkRelationships();
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////			
        if (arg.equals(correctLabel(language.getText("propertiesHuman")))) {
            tabpane.setSelectedIndex(1);
            getResourcePanel().propertiesHuman(this);
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("editCalendars")))) {
            GanttDialogCalendar dialogCalendar = new GanttDialogCalendar(this);
            dialogCalendar.show();
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("projectCalendar")))) {
            System.out.println("Project calendar");
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("webPage")))) {
            try {
                openWebPage();
            } catch (Exception e) {
                System.err.println(e);
            }
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("tipsOfTheDay")))) {
            TipsDialog tips = new TipsDialog(this, options.getOpenTips());
            tips.show();
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("manual")))) {
            if (helpFrame == null) {
                try {
                    helpFrame = new net.sourceforge.helpgui.gui.MainFrame("/docs/help/", "eclipse");
                    helpFrame.setTitle("GanttProject Manual");
                    ImageIcon icon = new ImageIcon(getClass().getResource("/icons/ganttproject.png"));
                    helpFrame.setIconImage(icon.getImage());
                } catch (Exception e) {
                }
            }
            helpFrame.setVisible(true);
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("about")))) {
            //old dialog box
            /*AboutGanttProject agp = new AboutGanttProject(this);
				agp.show();*/
            //new dialog box
            AboutDialog agp = new AboutDialog(this);
            agp.show();
        } else ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("sendMail")))) {
            tabpane.setSelectedIndex(1);
            getResourcePanel().sendMail(this);
        } else ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("cut")))) {
            Task t = tree.getSelectedTask();
            if (t != null) {
                tree.cutSelectedNode();
                setAskForSave(true);
            }
        } else ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("copy")))) {
            tree.copySelectedNode();
        } else ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("paste")))) {
            tree.pasteNode();
            setAskForSave(true);
        } else ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (arg.equals(correctLabel(language.getText("settings")))) {
            launchOptionsDialog();
        }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Test if it's a file name
    } else if (evt.getSource() instanceof Document) {
        if (checkCurrentProject())
            openStartupDocument((Document) evt.getSource());
    } else //Test if it's buttons actions
    if (evt.getSource() instanceof JButton) {
        if (//new
        evt.getSource() == bNew)
            newProject();
        else if (evt.getSource() == bOpen) {
            //open
            try {
                if (checkCurrentProject())
                    openFile();
            } catch (Exception ex) {
                System.err.println(ex);
            }
        } else if (evt.getSource() == bSave) {
            //save
            try {
                saveProject();
            } catch (Exception ex) {
                System.err.println(ex);
            }
        } else if (evt.getSource() == bSaveAs) {
            //saveas
            try {
                saveAsProject();
            } catch (Exception ex) {
                System.err.println(ex);
            }
        } else if (evt.getSource() == bImport) {
            //import
            importcbk();
        } else if (evt.getSource() == bExport) {
            //export
            export();
        } else if (evt.getSource() == bPrint) {
            //print
            printProject();
        }
    }
}
	/** Open the web page */
public void openWebPage() throws IOException {
    if (!BrowserControl.displayURL("http://ganttproject.org/")) {
        GanttDialogInfo gdi = new GanttDialogInfo(this, GanttDialogInfo.ERROR, GanttDialogInfo.YES_OPTION, language.getText("msg4"), language.getText("error"));
        gdi.show();
        return;
    }
    statusBar.setFirstText(GanttLanguage.getInstance().getText("opening") + " www.ganttproject.org", 2000);
}
	/** Import function. */
public void importcbk() {
    //		Create a filechooser
    JFileChooser fc = new JFileChooser(options.getWorkingDir());
    FileFilter txtFilter = new GanttTXTFileFilter();
    FileFilter ganFilter = new GanttXMLFileFilter();
    fc.addChoosableFileFilter(ganFilter);
    fc.addChoosableFileFilter(txtFilter);
    //		Remove the possibility to use a file filter for all files
    FileFilter[] filefilters = fc.getChoosableFileFilters();
    for (int i = 0; i < filefilters.length; i++) {
        if (filefilters[i] != txtFilter && filefilters[i] != ganFilter)
            fc.removeChoosableFileFilter(filefilters[i]);
    }
    int returnVal = fc.showOpenDialog(GanttProject.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        //openDocument(new FileDocument(fc.getSelectedFile()));
        FileFilter selectedFilter = fc.getFileFilter();
        //Choose a list of tasks from a txtFile.
        if (selectedFilter.equals(txtFilter)) {
            GanttTXTOpen opener = new GanttTXTOpen(tree, this, area, getTaskManager());
            boolean bMerge = true;
            if (projectDocument != null || askForSave == true) {
                GanttDialogInfo gdi = new GanttDialogInfo(GanttProject.this, GanttDialogInfo.QUESTION, GanttDialogInfo.YES_NO_OPTION, getLanguage().getText("msg17"), getLanguage().getText("question"));
                gdi.show();
                bMerge = (gdi.res == GanttDialogInfo.YES);
            }
            if (bMerge || (!bMerge && checkCurrentProject())) {
                if (!bMerge)
                    closeProject();
                //		Open the tasks
                opener.load(fc.getSelectedFile());
                changeWorkingDirectory(fc.getSelectedFile().getParent());
                setAskForSave(true);
            }
        } else if (selectedFilter.equals(ganFilter)) {
            //import an entire project inside the current
            Document document = new FileDocument(fc.getSelectedFile());
            try {
                GanttXMLOpen opener = new GanttXMLOpen(tree, this, getResourcePanel(), area, getTaskManager(), true);
                ResourceManager hrManager = getHumanResourceManager();
                RoleManager roleManager = getRoleManager();
                ResourceTagHandler resourceHandler = new ResourceTagHandler(hrManager, roleManager);
                DependencyTagHandler dependencyHandler = new DependencyTagHandler(opener.getContext(), getTaskManager());
                AllocationTagHandler allocationHandler = new AllocationTagHandler(hrManager, getTaskManager());
                opener.addTagHandler(opener.getDefaultTagHandler());
                //opener.addTagHandler(resourceHandler);
                //opener.addTagHandler(dependencyHandler);
                //opener.addTagHandler(allocationHandler);
                //opener.addParsingListener(dependencyHandler);
                opener.load(document.getInputStream());
            } catch (IOException e) {
                GanttDialogInfo gdi = new GanttDialogInfo(this, GanttDialogInfo.ERROR, GanttDialogInfo.YES_OPTION, language.getText("msg2") + "\n" + document.getDescription(), language.getText("error"));
                gdi.show();
            }
        }
    }
}
	/**
	 * Open a XML stream that represent the file //By CL !@#
	 * 
	 * @param ins
	 */
public void openXMLStream(InputStream ins, String path) {
    try {
        tree.clearTree();
        getResourcePanel().reset();
        getHumanResourceManager().clear();
        //GanttTask.resetMaxID();
        getTaskManager().clear();
        RoleManager.Access.getInstance().clear();
        GanttXMLOpen opener = new GanttXMLOpen(tree, this, getResourcePanel(), area, getTaskManager(), false);
        ResourceManager hrManager = getHumanResourceManager();
        RoleManager roleManager = getRoleManager();
        ResourceTagHandler resourceHandler = new ResourceTagHandler(hrManager, roleManager);
        DependencyTagHandler dependencyHandler = new DependencyTagHandler(opener.getContext(), getTaskManager());
        AllocationTagHandler allocationHandler = new AllocationTagHandler(hrManager, getTaskManager());
        opener.addTagHandler(opener.getDefaultTagHandler());
        opener.addTagHandler(resourceHandler);
        opener.addTagHandler(dependencyHandler);
        opener.addTagHandler(allocationHandler);
        opener.addParsingListener(dependencyHandler);
        opener.load(ins);
    //addProjectFileToLastOpen(projectfile);
    } catch (Exception ex) {
        GanttDialogInfo gdi = new GanttDialogInfo(this, GanttDialogInfo.ERROR, GanttDialogInfo.YES_OPTION, language.getText("msg2") + "\n" + path, language.getText("error"));
        gdi.show();
    }
    setAskForSave(false);
}
	/** Save the project to the given document, if possible */
public void saveProject(Document document) throws IOException {
    if (null == document) {
        saveAsProject();
        return;
    }
    if (!document.canWrite()) {
        GanttDialogInfo gdi = new GanttDialogInfo(this, GanttDialogInfo.ERROR, GanttDialogInfo.YES_OPTION, language.getText("msg10"), language.getText("error"));
        gdi.show();
        if (document instanceof AbstractURLDocument) {
            saveAsURLProject(document);
        } else {
            saveAsProject();
        }
        return;
    }
    if (!document.acquireLock()) {
        GanttDialogInfo gdi = new GanttDialogInfo(this, GanttDialogInfo.ERROR, GanttDialogInfo.YES_OPTION, language.getText("msg14"), language.getText("error"));
        gdi.show();
        if (document instanceof AbstractURLDocument) {
            saveAsURLProject(document);
        } else {
            saveAsProject();
        }
        return;
    }
    if (document.getDescription().endsWith(".xml") || document.getDescription().endsWith(".gan")) {
        GanttXMLSaver saver = new GanttXMLSaver((IGanttProject) this, tree, getResourcePanel(), area);
        saver.save(document.getOutputStream(), version);
        statusBar.setFirstText(GanttLanguage.getInstance().getText("saving") + " " + document.getPath(), 2000);
        //Add this project to the last opened projects
        if (documentsMRU.add(document))
            updateMenuMRU();
        if (projectDocument != document) {
            if (projectDocument != null)
                projectDocument.releaseLock();
            projectDocument = document;
        }
    }
    //change title of the window
    this.setTitle(language.getText("appliTitle") + " [" + document.getDescription() + "]");
    String filepath = document.getFilePath();
    if (null != filepath) {
        changeWorkingDirectory(new File(filepath).getParent());
    }
    setAskForSave(false);
}
	/** Save the project to the given document, if possible */
public void saveProject(Document document) throws IOException {
    if (null == document) {
        saveAsProject();
        return;
    }
    if (!document.canWrite()) {
        GanttDialogInfo gdi = new GanttDialogInfo(this, GanttDialogInfo.ERROR, GanttDialogInfo.YES_OPTION, language.getText("msg10"), language.getText("error"));
        gdi.show();
        if (document instanceof AbstractURLDocument) {
            saveAsURLProject(document);
        } else {
            saveAsProject();
        }
        return;
    }
    if (!document.acquireLock()) {
        GanttDialogInfo gdi = new GanttDialogInfo(this, GanttDialogInfo.ERROR, GanttDialogInfo.YES_OPTION, language.getText("msg14"), language.getText("error"));
        gdi.show();
        if (document instanceof AbstractURLDocument) {
            saveAsURLProject(document);
        } else {
            saveAsProject();
        }
        return;
    }
    if (document.getDescription().endsWith(".xml") || document.getDescription().endsWith(".gan")) {
        GanttXMLSaver saver = new GanttXMLSaver((IGanttProject) this, tree, getResourcePanel(), area);
        saver.save(document.getOutputStream(), version);
        statusBar.setFirstText(GanttLanguage.getInstance().getText("saving") + " " + document.getPath(), 2000);
        //Add this project to the last opened projects
        if (documentsMRU.add(document))
            updateMenuMRU();
        if (projectDocument != document) {
            if (projectDocument != null)
                projectDocument.releaseLock();
            projectDocument = document;
        }
    }
    //change title of the window
    this.setTitle(language.getText("appliTitle") + " [" + document.getDescription() + "]");
    String filepath = document.getFilePath();
    if (null != filepath) {
        changeWorkingDirectory(new File(filepath).getParent());
    }
    setAskForSave(false);
}
	/** Exit the Application */
private void exitForm(java.awt.event.WindowEvent evt) {
    quitApplication();
}
	/**
	 * Updates the last open file menu items.
	 */
private void updateMenuMRU() {
    mMRU.removeAll();
    int index = 0;
    Iterator iterator = documentsMRU.iterator();
    while (iterator.hasNext()) {
        index++;
        Document document = (Document) iterator.next();
        JMenuItem mi = new JMenuItem(new OpenDocumentAction(index, document, this));
        mMRU.add(mi);
    }
}
	/** Move up the datas (resources or tasks) */
private void upDatas() {
    if (tabpane.getSelectedIndex() == 0) {
        //Gantt Chart
        tree.upCurrentNodes();
    } else if (tabpane.getSelectedIndex() == 1) {
        //Resource chart
        getResourcePanel().upResource();
        getResourcePanel().setPeople(getResourcePanel().getPeople());
        getResourcePanel().area.repaint();
    }
    setAskForSave(true);
}
	/** Move down datas (resources or tasks) */
private void downDatas() {
    if (tabpane.getSelectedIndex() == 0) {
        //Gantt Chart
        tree.downCurrentNodes();
    } else if (tabpane.getSelectedIndex() == 1) {
        //Resource chart
        getResourcePanel().downResource();
        getResourcePanel().setPeople(getResourcePanel().getPeople());
        getResourcePanel().area.repaint();
    }
    setAskForSave(true);
}
	/**
	 * Check if the project has been modified, before creating a new one or open
	 * another
	 */
private boolean checkCurrentProject() {
    GanttDialogInfo gdi = new GanttDialogInfo(this, GanttDialogInfo.WARNING, GanttDialogInfo.YES_NO_CANCEL_OPTION, language.getText("msg1"), language.getText("warning"));
    GanttDialogInfo gdiSaveError = new GanttDialogInfo(this, GanttDialogInfo.ERROR, GanttDialogInfo.YES_NO_CANCEL_OPTION, language.getText("msg12"), language.getText("error"));
    if (askForSave == true) {
        gdi.show();
        if (gdi.res == GanttDialogInfo.CANCEL)
            return false;
        if (gdi.res == GanttDialogInfo.YES) {
            boolean trySave = true;
            do {
                try {
                    trySave = false;
                    saveProject();
                } catch (Exception e) {
                    System.err.println(e);
                    gdiSaveError.show();
                    if (gdiSaveError.res == GanttDialogInfo.CANCEL)
                        return false;
                    trySave = (gdiSaveError.res == GanttDialogInfo.YES);
                }
            } while (trySave);
        }
    }
    return true;
}
	/** Launch the options dialog */
public void launchOptionsDialog() {
    // old options dialog box
    /*GanttDialogOptions dialogOptions = new GanttDialogOptions(this,
				myUIConfiguration);
		dialogOptions.show();
		if (dialogOptions.change) {
			setAskForSave(true);
		}*/
    // new options dialog box
    statusBar.setFirstText(language.getText("settingsPreferences"), 2000);
    SettingsDialog dialogOptions = new SettingsDialog(this);
    dialogOptions.show();
    area.repaint();
}
	/** Execute the export functions. */
private void doExport(ExportFileInfo info) {
    switch(info.myFormat) {
        case ExportFileInfo.FORMAT_HTML:
            {
                statusBar.setFirstText(language.getText("htmlexport"), 2000);
                GanttHTMLExport.save(info.myFile, prjInfos, this, tree, area, getResourcePanel().area, info.myStorageOptions);
                break;
            }
        case ExportFileInfo.FORMAT_PNG:
            {
                statusBar.setFirstText(language.getText("pnglexport"), 2000);
                String filename = info.myFile.toString();
                if (!filename.toUpperCase().endsWith(".PNG"))
                    filename += ".png";
                if (tabpane.getSelectedIndex() == 0) {
                    //Gantt Chart
                    //area.fitWholeProject(true);
                    GanttExportSettings bool = info.myStorageOptions;
                    area.export(new File(filename), bool, "png");
                } else if (tabpane.getSelectedIndex() == 1) {
                    //Resources
                    // Chart
                    GanttExportSettings bool = info.myStorageOptions;
                    getResourcePanel().area.export(new File(filename), "png", bool);
                }
                break;
            }
        case ExportFileInfo.FORMAT_JPG:
            {
                statusBar.setFirstText(language.getText("jpgexport"), 2000);
                String filename = info.myFile.toString();
                if (!filename.toUpperCase().endsWith(".JPG") && !filename.toUpperCase().endsWith(".JPEG"))
                    filename += ".jpg";
                if (tabpane.getSelectedIndex() == 0) {
                    //Gantt Chart
                    //area.fitWholeProject(true);
                    GanttExportSettings bool = info.myStorageOptions;
                    area.export(new File(filename), bool, "jpg");
                } else if (tabpane.getSelectedIndex() == 1) {
                    //Resources
                    // Chart
                    GanttExportSettings bool = info.myStorageOptions;
                    getResourcePanel().area.export(new File(filename), "jpg", bool);
                }
                break;
            }
        case ExportFileInfo.FORMAT_PDF:
            {
                try {
                    DeprecatedProjectExportData exportData = new DeprecatedProjectExportData(info.myFile.toString(), this, tree, area, getResourcePanel().area, info.myStorageOptions, getXslFo());
                    ProjectExportProcessor processor = new PDFExportProcessor();
                    processor.doExport(exportData);
                } catch (Exception e) {
                    String exceptionReport = getExceptionReport(e);
                    //If not run in console mode
                    if (isVisible()) {
                        GanttDialogInfo gdi = new GanttDialogInfo(this, GanttDialogInfo.ERROR, GanttDialogInfo.YES_OPTION, exceptionReport, language.getText("error"));
                        gdi.show();
                    } else
                        //Just show the message into console
                        System.out.println("\n====" + language.getText("error") + "====\n" + exceptionReport + "\n");
                }
                break;
            }
        case ExportFileInfo.FORMAT_XFIG:
            {
                //				show a message  on the status bar
                statusBar.setFirstText(language.getText("xfigexport"), 2000);
                String filename = info.myFile.toString();
                if (!filename.toUpperCase().endsWith(".FIG"))
                    filename += ".fig";
                GanttXFIGSaver saver = new GanttXFIGSaver(prjInfos, tree, getResourcePanel(), area);
                try {
                    saver.save(new FileOutputStream(new File(filename)), version);
                //saver.save(System.out,version); //temporary write the file on standard output
                } catch (Exception e) {
                }
                break;
            }
        case ExportFileInfo.FORMAT_CSV:
            {
                //show a message  on the status bar
                statusBar.setFirstText(language.getText("csvexport"), 2000);
                String filename = info.myFile.toString();
                if (!filename.toUpperCase().endsWith(".CSV"))
                    filename += ".csv";
                GanttCSVExport saver = new GanttCSVExport(prjInfos, tree, getResourcePanel(), options.getCSVOptions());
                try {
                    saver.save(new FileOutputStream(new File(filename)));
                //saver.save(System.out);
                } catch (Exception e) {
                }
                break;
            }
    }
}
	/** Create a new project */
public void newProject() {
    if (checkCurrentProject()) {
        closeProject();
        statusBar.setFirstText(language.getText("newProject2"), 1500);
        refreshProjectInfos();
    }
    prjInfos = new PrjInfos();
    showNewProjectWizard();
}
	/**
	 * Closes a project. Make sure you have already called checkCurrentProject()
	 * before.
	 * 
	 * @see #checkCurrentProject()
	 */
private void closeProject() {
    //Clear the jtree
    //refresh graphic area
    area.repaint();
    area.setProjectLevelTaskColor(null);
    getResourcePanel().area.repaint();
    //reset people
    getResourcePanel().reset();
    getHumanResourceManager().clear();
    prjInfos = new PrjInfos();
    //GanttTask.resetMaxID();
    RoleManager.Access.getInstance().clear();
    if (null != projectDocument)
        projectDocument.releaseLock();
    projectDocument = null;
    //change title of the frame
    this.setTitle(language.getText("appliTitle"));
    setAskForSave(false);
    getTaskManager().clear();
    tree.clearTree();
}
	/** Open a local project file with dialog box (JFileChooser) */
public void openFile() throws IOException {
    //Create a filechooser
    JFileChooser fc = new JFileChooser(options.getWorkingDir());
    FileFilter ganttFilter = new GanttXMLFileFilter();
    fc.addChoosableFileFilter(ganttFilter);
    //Remove the possibility to use a file filter for all files
    FileFilter[] filefilters = fc.getChoosableFileFilters();
    for (int i = 0; i < filefilters.length; i++) {
        if (filefilters[i] != ganttFilter)
            System.out.println(fc.removeChoosableFileFilter(filefilters[i]));
    }
    int returnVal = fc.showOpenDialog(GanttProject.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        openDocument(new FileDocument(fc.getSelectedFile()));
        changeWorkingDirectory(fc.getSelectedFile().getParent());
    }
}
	/** Save the project as (with a dialog file chooser) */
public boolean saveAsProject() throws IOException {
    JFileChooser fc = new JFileChooser(options.getWorkingDir());
    FileFilter ganttFilter = new GanttXMLFileFilter();
    fc.addChoosableFileFilter(ganttFilter);
    //Remove the possibility to use a file filter for all files
    FileFilter[] filefilters = fc.getChoosableFileFilters();
    for (int i = 0; i < filefilters.length; i++) {
        if (filefilters[i] != ganttFilter)
            fc.removeChoosableFileFilter(filefilters[i]);
    }
    int returnVal = fc.showSaveDialog(GanttProject.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        String projectfile = fc.getSelectedFile().toString();
        if (!fc.getFileFilter().accept(new File(projectfile))) {
            if (fc.getFileFilter().accept(new File(projectfile + ".gan"))) {
                projectfile += ".gan";
            }
        }
        //if( the file exists, ask for overwriting
        if (new File(projectfile).exists()) {
            GanttDialogInfo gdi = new GanttDialogInfo(this, GanttDialogInfo.WARNING, GanttDialogInfo.YES_NO_OPTION, projectfile + "\n" + language.getText("msg18"), language.getText("warning"));
            gdi.show();
            if (gdi.res == GanttDialogInfo.NO)
                saveAsProject();
        }
        saveProject(new FileDocument(new File(projectfile)));
        return true;
    }
    return false;
}
	/** Open a remote project file with dialog box (GanttURLChooser) */
public void openURL(Document lastDocument) {
    GanttURLChooser uc = new GanttURLChooser(this, true, (null != lastDocument) ? lastDocument.getURLPath() : null, (null != lastDocument) ? lastDocument.getUsername() : null, (null != lastDocument) ? lastDocument.getPassword() : null);
    uc.show();
    if (uc.change) {
        Document openDoc;
        if ((lastDocument instanceof AbstractURLDocument) && uc.fileurl.equals(lastDocument.getURLPath())) {
            lastDocument.setUserInfo(uc.userName, uc.password);
            openDoc = lastDocument;
        } else {
            openDoc = DocumentCreator.createDocument(uc.fileurl, uc.userName, uc.password);
        }
        try {
            openDocument(openDoc);
        } catch (IOException e) {
            if (isVisible()) {
                GanttDialogInfo gdi = new GanttDialogInfo(this, GanttDialogInfo.ERROR, GanttDialogInfo.YES_OPTION, language.getText("msg11"), language.getText("error"));
                gdi.show();
            } else
                System.out.println("\n====" + language.getText("error") + "====\n" + language.getText("msg11") + "\n");
            openURL(openDoc);
        }
    }
}
	/** Open a remote project file with dialog box (GanttURLChooser) */
public void openURL(Document lastDocument) {
    GanttURLChooser uc = new GanttURLChooser(this, true, (null != lastDocument) ? lastDocument.getURLPath() : null, (null != lastDocument) ? lastDocument.getUsername() : null, (null != lastDocument) ? lastDocument.getPassword() : null);
    uc.show();
    if (uc.change) {
        Document openDoc;
        if ((lastDocument instanceof AbstractURLDocument) && uc.fileurl.equals(lastDocument.getURLPath())) {
            lastDocument.setUserInfo(uc.userName, uc.password);
            openDoc = lastDocument;
        } else {
            openDoc = DocumentCreator.createDocument(uc.fileurl, uc.userName, uc.password);
        }
        try {
            openDocument(openDoc);
        } catch (IOException e) {
            if (isVisible()) {
                GanttDialogInfo gdi = new GanttDialogInfo(this, GanttDialogInfo.ERROR, GanttDialogInfo.YES_OPTION, language.getText("msg11"), language.getText("error"));
                gdi.show();
            } else
                System.out.println("\n====" + language.getText("error") + "====\n" + language.getText("msg11") + "\n");
            openURL(openDoc);
        }
    }
}
	/** Save the project on a server (with a GanttURLChooser) */
public boolean saveAsURLProject(Document document) throws IOException {
    GanttURLChooser uc = new GanttURLChooser(this, false, (null != document) ? document.getURLPath() : null, (null != document) ? document.getUsername() : null, (null != document) ? document.getPassword() : null);
    uc.show();
    if (uc.change) {
        Document saveDocument = null;
        if (null != document)
            if (uc.fileurl.equals(document.getURLPath()) && uc.userName.equals(document.getUsername()) && uc.password.equals(document.getPassword()))
                saveDocument = document;
        if (null == saveDocument)
            saveDocument = DocumentCreator.createDocument(uc.fileurl, uc.userName, uc.password);
        saveProject(saveDocument);
        return true;
    }
    return false;
/*ServerDialog sd = new ServerDialog(this);
		sd.show();		
		return false;*/
}
	/** Save the project on a server (with a GanttURLChooser) */
public boolean saveAsURLProject(Document document) throws IOException {
    GanttURLChooser uc = new GanttURLChooser(this, false, (null != document) ? document.getURLPath() : null, (null != document) ? document.getUsername() : null, (null != document) ? document.getPassword() : null);
    uc.show();
    if (uc.change) {
        Document saveDocument = null;
        if (null != document)
            if (uc.fileurl.equals(document.getURLPath()) && uc.userName.equals(document.getUsername()) && uc.password.equals(document.getPassword()))
                saveDocument = document;
        if (null == saveDocument)
            saveDocument = DocumentCreator.createDocument(uc.fileurl, uc.userName, uc.password);
        saveProject(saveDocument);
        return true;
    }
    return false;
/*ServerDialog sd = new ServerDialog(this);
		sd.show();		
		return false;*/
}
	//change by G. Herrmann
public void setAskForSave(boolean afs) {
    String title = getTitle();
    //String last = title.substring(title.length() - 11, title.length());
    bSave.setEnabled(afs);
    miSave.setEnabled(afs);
    askForSave = afs;
    try {
        if (System.getProperty("mrj.version") != null) {
            rootPane.putClientProperty("windowModified", Boolean.valueOf(afs));
        // see http://developer.apple.com/qa/qa2001/qa1146.html
        } else {
            if (askForSave) {
                if (!title.endsWith(" *")) {
                    setTitle(getTitle() + " *");
                }
            }
        }
    } catch (AccessControlException e) {
        // This can happen when running in a sandbox (Java WebStart)
        System.err.println(e + ": " + e.getMessage());
    }
}
}