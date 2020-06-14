public class Class0 {
	private Object bExportTaskResources;
	private Object sSeparatedChar;
	private Object openTips;
	private Object TEXT;
	private Object bExportTaskWebLink;
	private Object bFixedSize;
	private Object INDENT;
	private Object bExportTaskStartDate;
	private Object ICONS_TEXT;
	private Object bExportTaskNotes;
	private Object bExportTaskPercent;
	private Object bExportResourceRole;
	private Object bExportResourceMail;
	private Object bExportResourcePhone;
	private Object bExportResourceID;
	private Object ICONS;
	private Object sSeparatedTextChar;
	private Object height;
	private Object bExportTaskDuration;
	private Object err;
	private Object bExportTaskName;
	private Object automatic;
	private Object xslFo;
	private Object bExportTaskEndDate;
	private Object bExportResourceName;
	private Object xslDir;
	private Object dragTime;
	private Object ENCODING;
	private Object x;
	private Object width;
	private Object y;
	private Object HORIZONTAL;
	private Object bExportTaskID;

	public Class0(Object bExportTaskResources, Object sSeparatedChar, Object openTips, Object TEXT, Object bExportTaskWebLink, Object bFixedSize, Object INDENT, Object bExportTaskStartDate, Object ICONS_TEXT, Object bExportTaskNotes, Object bExportTaskPercent, Object bExportResourceRole, Object bExportResourceMail, Object bExportResourcePhone, Object bExportResourceID, Object ICONS, Object sSeparatedTextChar, Object height, Object bExportTaskDuration, Object err, Object bExportTaskName, Object automatic, Object xslFo, Object bExportTaskEndDate, Object bExportResourceName, Object xslDir, Object dragTime, Object ENCODING, Object x, Object width, Object y, Object HORIZONTAL, Object bExportTaskID){
		this.bExportTaskResources = bExportTaskResources;
		this.sSeparatedChar = sSeparatedChar;
		this.openTips = openTips;
		this.TEXT = TEXT;
		this.bExportTaskWebLink = bExportTaskWebLink;
		this.bFixedSize = bFixedSize;
		this.INDENT = INDENT;
		this.bExportTaskStartDate = bExportTaskStartDate;
		this.ICONS_TEXT = ICONS_TEXT;
		this.bExportTaskNotes = bExportTaskNotes;
		this.bExportTaskPercent = bExportTaskPercent;
		this.bExportResourceRole = bExportResourceRole;
		this.bExportResourceMail = bExportResourceMail;
		this.bExportResourcePhone = bExportResourcePhone;
		this.bExportResourceID = bExportResourceID;
		this.ICONS = ICONS;
		this.sSeparatedTextChar = sSeparatedTextChar;
		this.height = height;
		this.bExportTaskDuration = bExportTaskDuration;
		this.err = err;
		this.bExportTaskName = bExportTaskName;
		this.automatic = automatic;
		this.xslFo = xslFo;
		this.bExportTaskEndDate = bExportTaskEndDate;
		this.bExportResourceName = bExportResourceName;
		this.xslDir = xslDir;
		this.dragTime = dragTime;
		this.ENCODING = ENCODING;
		this.x = x;
		this.width = width;
		this.y = y;
		this.HORIZONTAL = HORIZONTAL;
		this.bExportTaskID = bExportTaskID;
	}
	/** Constructor. 
  public GanttOptions(Color c, int x, int y, int width, int height,
                      GanttLookAndFeelInfo lookAndFeel,
                      boolean automatic, boolean dragTime,
                      String xslDir, String xslFo,String workingDir,
                      boolean tips, boolean redline, int lockDAVMinutes,
                      DocumentsMRU documentsMRU, UIConfiguration uiConfiguration, RoleManager roleManager) {
  myRoleManager = roleManager;
  color = c;
  this.x = x;
  this.y = y;
  this.width = width;
  this.height = height;
  this.lookAndFeel = lookAndFeel;
  this.automatic=automatic;
  this.dragTime=dragTime;
  this.openTips=tips;
  this.redline=redline;
  this.lockDAVMinutes=lockDAVMinutes;
  this.myUIConfig = uiConfiguration;

	if(xslFo!=null)
		this.xslFo=xslFo;
	
	if (xslDir != null)
        this.xslDir = xslDir;
	
	try {
		this.workingDir = System.getProperty("user.home");                     	
	} catch (AccessControlException e) {
		// This can happen when running in a sandbox (Java WebStart)
		System.err.println (e + ": " + e.getMessage());
	}
	if (workingDir != null && new File(workingDir).exists())
       this.workingDir = workingDir;

	this.documentsMRU = documentsMRU;	
  }
*/
private void startElement(String name, Attributes attrs, TransformerHandler handler) throws SAXException {
    handler.startElement("", name, name, attrs);
}
	/** Init the options by default. */
public void initByDefault() {
    automatic = false;
    dragTime = true;
    openTips = true;
    redline = false;
    lockDAVMinutes = 240;
    xslDir = GanttOptions.class.getResource("/xslt").toString();
    xslFo = GanttOptions.class.getResource("/xslfo/ganttproject.xsl").toString();
    sTaskNamePrefix = "";
    toolBarPosition = JToolBar.HORIZONTAL;
    bShowStatusBar = true;
    //must be 16 small, 24 for big (32 for extra big not directly include on UI)  
    iconSize = "16";
    buttonsshow = GanttOptions.ICONS;
    /** Export options. */
    bExportName = true;
    bExportComplete = true;
    bExportRelations = true;
    bExport3DBorders = false;
    /** CVS export options. */
    csvOptions = new CSVOptions();
}
	/** 
   * Save the options file */
public void save() {
    try {
        String sFileName = ".ganttproject";
        /*if(System.getProperty("os.name").startsWith("Windows") || 
      		System.getProperty("os.name").startsWith("Mac"))
      	sFileName = "ganttproject.ini";*/
        File file = new File(System.getProperty("user.home") + System.getProperty("file.separator") + sFileName);
        //DataOutputStream fout = new DataOutputStream(new FileOutputStream(file));
        TransformerHandler handler = ((SAXTransformerFactory) SAXTransformerFactory.newInstance()).newTransformerHandler();
        Transformer serializer = handler.getTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        handler.setResult(new StreamResult(file));
        handler.startDocument();
        //handler.startDTD("ganttproject.sourceforge.net", "-//GanttProject.org//DTD GanttProject-1.x//EN", "http://ganttproject.sourceforge.net/dtd/ganttproject.dtd");      
        //handler.endDTD();
        //
        AttributesImpl attrs = new AttributesImpl();
        handler.startElement("", "ganttproject-options", "ganttproject-options", attrs);
        //
        attrs.addAttribute("", "selection", "selection", "CDATA", language.getText("shortLanguage"));
        handler.startElement("", "language", "language", attrs);
        handler.endElement("", "language", "language");
        attrs.clear();
        //write the task Color
        /*Color color = getUIConfiguration().getTaskColor();
      attrs.addAttribute("", "red", "red", "CDATA", ""+color.getRed());
      attrs.addAttribute("", "green", "green", "CDATA", ""+color.getGreen());
      attrs.addAttribute("", "blue", "blue", "CDATA", ""+color.getBlue());
      handler.startElement("", "task-color", "task-color", attrs);
      handler.endElement("", "task-color", "task-color");
      attrs.clear();*/
        Color taskColor = myUIConfig.getTaskColor();
        if (taskColor != null)
            attrs.addAttribute("", "tasks", "tasks", "CDATA", "" + ColorConvertion.getColor(taskColor));
        Color resourceColor = myUIConfig.getResourceColor();
        if (resourceColor != null)
            attrs.addAttribute("", "resources", "resources", "CDATA", "" + ColorConvertion.getColor(resourceColor));
        Color resourceOverloadColor = myUIConfig.getResourceOverloadColor();
        if (resourceOverloadColor != null)
            attrs.addAttribute("", "resourcesOverload", "resourcesOverload", "CDATA", "" + ColorConvertion.getColor(resourceOverloadColor));
        handler.startElement("", "colors", "colors", attrs);
        handler.endElement("", "colors", "colors");
        attrs.clear();
        //Geometry of the window
        addAttribute("x", "" + x, attrs);
        addAttribute("y", "" + y, attrs);
        addAttribute("width", "" + width, attrs);
        addAttribute("height", "" + height, attrs);
        emptyElement("geometry", attrs, handler);
        //look'n'feel
        addAttribute("name", lookAndFeel.getName(), attrs);
        addAttribute("class", lookAndFeel.getClassName(), attrs);
        emptyElement("looknfeel", attrs, handler);
        //ToolBar position
        addAttribute("position", "" + toolBarPosition, attrs);
        addAttribute("icon-size", "" + iconSize, attrs);
        addAttribute("show", "" + bShowStatusBar, attrs);
        emptyElement("statusBar", attrs, handler);
        // Export options
        addAttribute("name", "" + bExportName, attrs);
        addAttribute("complete", "" + bExportComplete, attrs);
        addAttribute("border3d", "" + bExport3DBorders, attrs);
        addAttribute("relations", "" + bExportRelations, attrs);
        emptyElement("export", attrs, handler);
        //csv export options
        startElement("csv-export", attrs, handler);
        addAttribute("fixed", "" + csvOptions.bFixedSize, attrs);
        addAttribute("separatedChar", "" + csvOptions.sSeparatedChar, attrs);
        addAttribute("separatedTextChar", "" + csvOptions.sSeparatedTextChar, attrs);
        emptyElement("csv-general", attrs, handler);
        addAttribute("id", "" + csvOptions.bExportTaskID, attrs);
        addAttribute("name", "" + csvOptions.bExportTaskName, attrs);
        addAttribute("start-date", "" + csvOptions.bExportTaskStartDate, attrs);
        addAttribute("end-date", "" + csvOptions.bExportTaskEndDate, attrs);
        addAttribute("percent", "" + csvOptions.bExportTaskPercent, attrs);
        addAttribute("duration", "" + csvOptions.bExportTaskDuration, attrs);
        addAttribute("webLink", "" + csvOptions.bExportTaskWebLink, attrs);
        addAttribute("resources", "" + csvOptions.bExportTaskResources, attrs);
        addAttribute("notes", "" + csvOptions.bExportTaskNotes, attrs);
        emptyElement("csv-tasks", attrs, handler);
        addAttribute("id", "" + csvOptions.bExportResourceID, attrs);
        addAttribute("name", "" + csvOptions.bExportResourceName, attrs);
        addAttribute("mail", "" + csvOptions.bExportResourceMail, attrs);
        addAttribute("phone", "" + csvOptions.bExportResourcePhone, attrs);
        addAttribute("role", "" + csvOptions.bExportResourceRole, attrs);
        emptyElement("csv-resources", attrs, handler);
        endElement("csv-export", handler);
        //automatic popup launch
        addAttribute("value", "" + automatic, attrs);
        emptyElement("automatic-launch", attrs, handler);
        //automaticdrag time on the chart
        addAttribute("value", "" + dragTime, attrs);
        emptyElement("dragTime", attrs, handler);
        //automatic tips of the day launch
        addAttribute("value", "" + openTips, attrs);
        emptyElement("tips-on-startup", attrs, handler);
        //Show red line for today
        addAttribute("value", "" + myUIConfig.isRedlineOn(), attrs);
        emptyElement("redline", attrs, handler);
        //Should WebDAV resources be locked, when opening them?
        addAttribute("value", "" + lockDAVMinutes, attrs);
        emptyElement("lockdavminutes", attrs, handler);
        //write the xsl directory
        addAttribute("dir", xslDir, attrs);
        emptyElement("xsl-dir", attrs, handler);
        //write the xslfo directory
        addAttribute("file", xslFo, attrs);
        emptyElement("xsl-fo", attrs, handler);
        //write the working directory directory
        addAttribute("dir", workingDir, attrs);
        emptyElement("working-dir", attrs, handler);
        //write the task name prefix
        addAttribute("prefix", sTaskNamePrefix, attrs);
        emptyElement("task-name", attrs, handler);
        //The last opened files
        {
            startElement("files", attrs, handler);
            for (Iterator iterator = documentsMRU.iterator(); iterator.hasNext(); ) {
                Document document = (Document) iterator.next();
                addAttribute("path", document.getPath(), attrs);
                emptyElement("file", attrs, handler);
            }
            endElement("files", handler);
        }
        addAttribute("category", "menu", attrs);
        addAttribute("spec", getFontSpec(getUIConfiguration().getMenuFont()), attrs);
        emptyElement("font", attrs, handler);
        //
        addAttribute("category", "chart-main", attrs);
        addAttribute("spec", getFontSpec(getUIConfiguration().getChartMainFont()), attrs);
        emptyElement("font", attrs, handler);
        //
        saveRoleSets(handler);
        endElement("ganttproject-options", handler);
        //
        System.err.println("[GanttOptions] save(): finished!!");
        handler.endDocument();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
	/** Load the options file */
public boolean load() {
    // Use an instance of ourselves as the SAX event handler
    DefaultHandler handler = new GanttXMLOptionsParser();
    // Use the default (non-validating) parser
    SAXParserFactory factory = SAXParserFactory.newInstance();
    try {
        String sFileName = ".ganttproject";
        /*if(System.getProperty("os.name").startsWith("Windows") || 
        		System.getProperty("os.name").startsWith("Mac"))
        	sFileName = "ganttproject.ini";*/
        File file = new File(System.getProperty("user.home") + System.getProperty("file.separator") + sFileName);
        if (!file.exists()) {
            return false;
        }
        documentsMRU.clear();
        // Parse the input
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(file, handler);
        GanttLookAndFeelInfo info = GanttLookAndFeels.getGanttLookAndFeels().getInfoByClass(styleClass);
        if (null == info)
            info = GanttLookAndFeels.getGanttLookAndFeels().getInfoByName(styleName);
        if (null != info)
            lookAndFeel = info;
        if (null == lookAndFeel)
            lookAndFeel = GanttLookAndFeels.getGanttLookAndFeels().getDefaultInfo();
        loadRoleSets(file);
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    isloaded = true;
    return true;
}
	/** @return the default color for tasks. */
public Color getDefaultColor() {
    return getUIConfiguration().getTaskColor();
}
	/** @return the color for resources. */
public Color getResourceColor() {
    return getUIConfiguration().getResourceColor();
}
	/** @return the color for resources overload. */
public Color getResourceOverloadColor() {
    return getUIConfiguration().getResourceOverloadColor();
}
	/** @return the lock DAV Minutes. */
public int getLockDAVMinutes() {
    return lockDAVMinutes;
}
	/** @return the working directory. */
public String getWorkingDir() {
    return workingDir;
}
	/** @return the xsl directory. */
public String getXslDir() {
    return xslDir;
}
	/** @return the xsl-fo file. */
public String getXslFo() {
    return xslFo;
}
	/** @return if you want to open the tips at the start of GP. */
public boolean getOpenTips() {
    return openTips;
}
	/** @return if the mouse is used to drag on the chart.*/
public boolean getDragTime() {
    return dragTime;
}
	/** @return automatic launch properties box when create a new task.*/
public boolean getAutomatic() {
    return automatic;
}
	/** @return the lookAndFeel infos. */
public GanttLookAndFeelInfo getLnfInfos() {
    return lookAndFeel;
}
	/** @return true is options are loaded from the options file. */
public boolean isLoaded() {
    return isloaded;
}
	/** @return true if show the status bar. */
public boolean getShowStatusBar() {
    return bShowStatusBar;
}
	/** set show the status bar. */
public void setShowStatusBar(boolean showStatusBar) {
    bShowStatusBar = showStatusBar;
}
	/** @return the top left x position of the window. */
public int getX() {
    return x;
}
	/** @return the top left y position of the window. */
public int getY() {
    return y;
}
	/** @return the width of the window. */
public int getWidth() {
    return width;
}
	/** @return the height of the window. */
public int getHeight() {
    return height;
}
	/** @return the task name prefix. */
public String getTaskNamePrefix() {
    if (sTaskNamePrefix == null || sTaskNamePrefix.equals(""))
        return language.getText("newTask");
    return sTaskNamePrefix;
}
	/** @return the toolbar position. */
public int getToolBarPosition() {
    return toolBarPosition;
}
	/** @return the size of the icons on the toolbar. */
public String getIconSize() {
    return iconSize;
}
	/** @return true if you want to export the name of the task on the exported chart. */
public boolean getExportName() {
    return bExportName;
}
	/** @return true if you want to export the complete percent of the task on the exported chart. */
public boolean getExportComplete() {
    return bExportComplete;
}
	/** @return true if you want to export the relations of the task on the exported chart. */
public boolean getExportRelations() {
    return bExportRelations;
}
	/** @return the 3d borders export. */
public boolean getExport3dBorders() {
    return bExport3DBorders;
}
	/** @return the button show attribute
   * must be GanttOptions.ICONS
   *      or GanttOptions.TEXT
   *      ir GanttOptions.ICONS_TEXT
   */
public int getButtonShow() {
    return buttonsshow;
}
	/** set a new button show value
   * must be GanttOptions.ICONS
   *      or GanttOptions.TEXT
   *      ir GanttOptions.ICONS_TEXT
   */
public void setButtonShow(int buttonShow) {
    if (buttonShow != GanttOptions.ICONS && buttonShow != GanttOptions.TEXT && buttonShow != GanttOptions.ICONS_TEXT)
        buttonShow = GanttOptions.ICONS;
    buttonsshow = buttonShow;
}
	/** Set a new icon size. Must be 16, 24 (or 32 exceptionnally)*/
public void setIconSize(String sIconSize) {
    iconSize = sIconSize;
}
	/** set the toolbar position. */
public void setToolBarPosition(int iToolBarPosition) {
    toolBarPosition = iToolBarPosition;
}
	/** Set new window position (top left corner) */
public void setWindowPosition(int x, int y) {
    this.x = x;
    this.y = y;
}
	/** Set new window position (top left corner) */
public void setWindowSize(int width, int height) {
    this.width = width;
    this.height = height;
}
	/** Set new working directory value. */
public void setWorkingDirectory(String workingDirectory) {
    workingDir = workingDirectory;
}
	/** set a new value for web dav locking. */
public void setLockDAVMinutes(int minuteslock) {
    lockDAVMinutes = minuteslock;
}
	/** Set a new xsl directory for html export. */
public void setXslDir(String xslDir) {
    this.xslDir = xslDir;
}
	/** Set a new xsl-fo file for pdf export. */
public void setXslFo(String xslFo) {
    this.xslFo = xslFo;
}
	/** set new open tips value.*/
public void setOpenTips(boolean openTips) {
    this.openTips = openTips;
}
	/** set new automatic launch value.*/
public void setAutomatic(boolean automatic) {
    this.automatic = automatic;
}
	/** set new drag time with mouse value.*/
public void setDragTime(boolean dragTime) {
    this.dragTime = dragTime;
}
	/** @return the language. */
public GanttLanguage getLanguage() {
    return language;
}
	/** @return the cvsOptions. */
public CSVOptions getCSVOptions() {
    return csvOptions;
}
}