public class Class6 {
	private Object FORMAT_PNG;
	private Object lookAndFeel;
	private Object area;
	private Object FORMAT_JPG;
	private Object FORMAT_PDF;
	private Object out;
	private Object FORMAT_CSV;
	private Object FORMAT_XFIG;
	private Object EMPTY;
	private Object err;
	private Object length;
	private Object FORMAT_HTML;

	public Class6(Object FORMAT_PNG, Object lookAndFeel, Object area, Object FORMAT_JPG, Object FORMAT_PDF, Object out, Object FORMAT_CSV, Object FORMAT_XFIG, Object EMPTY, Object err, Object length, Object FORMAT_HTML){
		this.FORMAT_PNG = FORMAT_PNG;
		this.lookAndFeel = lookAndFeel;
		this.area = area;
		this.FORMAT_JPG = FORMAT_JPG;
		this.FORMAT_PDF = FORMAT_PDF;
		this.out = out;
		this.FORMAT_CSV = FORMAT_CSV;
		this.FORMAT_XFIG = FORMAT_XFIG;
		this.EMPTY = EMPTY;
		this.err = err;
		this.length = length;
		this.FORMAT_HTML = FORMAT_HTML;
	}
	/** The main */
public static void main(String[] arg) {
    if (arg.length > 0 && ("-h".equals(arg[0]) || "--help".equals(arg[0]))) {
        //Help for the command line
        usage();
        System.exit(0);
    }
    /*
		 * If -xsl-dir with directory has been provided, use the xsls in this
		 * dir instead of the ones from .ganttproject Can be used in both
		 * interacive and -html/-export modes From Pawel Lipinski.
		 * And set the new path as default.
		 */
    GanttOptions options = new GanttOptions(null);
    for (int i = 0; i < arg.length; i++) {
        if (arg[i].equals("-xsl-dir") && (arg.length > i + 1)) {
            options.setXslDir(arg[i + 1]);
            break;
        }
    }
    for (int i = 0; i < arg.length; i++) {
        if (arg[i].equals("-xsl-fo") && (arg.length > i + 1)) {
            options.setXslFo(arg[i + 1]);
            break;
        }
    }
    if (arg.length > 0 && ("-html".equals(arg[0]) || "-htm".equals(arg[0]) || "-export".equals(arg[0]))) {
        if (checkProjectFile(arg))
            exportProject(arg);
        System.exit(0);
    } else if (arg.length > 0 && "-pdf".equals(arg[0])) {
        if (checkProjectFile(arg))
            exportPDF(arg);
        System.exit(0);
    } else if (arg.length > 0 && "-png".equals(arg[0])) {
        if (checkProjectFile(arg))
            exportPNG(arg);
        System.exit(0);
    } else if (arg.length > 0 && "-jpg".equals(arg[0])) {
        if (checkProjectFile(arg))
            exportJPG(arg);
        System.exit(0);
    } else if (arg.length > 0 && ("-fig".equals(arg[0]) || "-xfig".equals(arg[0]))) {
        if (checkProjectFile(arg))
            exportXFIG(arg);
        System.exit(0);
    } else if (arg.length > 0 && ("-csv".equals(arg[0]))) {
        if (checkProjectFile(arg))
            exportCSV(arg);
        System.exit(0);
    }
    /* Splash image */
    GanttSplash splash = new GanttSplash();
    splash.setVisible(true);
    /** Create main frame */
    GanttProject ganttFrame = new GanttProject(null);
    ganttFrame.setVisible(true);
    if (arg.length > 0) {
        ganttFrame.openStartupDocument(arg[0]);
    } else {
    //ganttFrame.showNewProjectWizard();
    }
    splash.close();
}
	/**
	 * Possibility to export the project into HTML directly by command Line From
	 * Dmitry Barashev
	 */
private static void exportProject(String[] args) {
    try {
        byCommandLine = true;
        GanttProject project = new GanttProject(args[1]);
        String exportPath;
        if (args.length > 2)
            exportPath = args[2];
        else
            exportPath = "htmlExport";
        File targetDir = new File(exportPath);
        if (!targetDir.exists()) {
            targetDir.mkdir();
        } else {
            if (!targetDir.isDirectory()) {
                throw new RuntimeException("File " + args[2] + " must be directory");
            }
        }
        System.out.println(targetDir.getAbsolutePath());
        String index = project.getProjectName();
        if (index == null || index.length() == 0) {
            index = new String("ganttproject");
        } else {
            index = project.getProjectName().toLowerCase();
        }
        File targetFile = new File(targetDir, index + ".html");
        System.err.println(targetFile.getAbsolutePath());
        if (!targetFile.exists()) {
            targetFile.createNewFile();
        }
        ExportFileInfo info = new ExportFileInfo(targetFile, ExportFileInfo.FORMAT_HTML, new GanttExportSettings());
        project.doExport(info);
    } catch (IOException e) {
        throw new RuntimeException("IO error", e);
    }
}
	/** Possibility to export the project into PDF directly by command Line */
private static void exportPDF(String[] args) {
    byCommandLine = true;
    GanttProject project = new GanttProject(args[1]);
    String exportFile;
    if (args.length > 2)
        exportFile = args[2];
    else
        exportFile = "ganttproject.pdf";
    File targetFile = new File(exportFile);
    ExportFileInfo info = new ExportFileInfo(targetFile, ExportFileInfo.FORMAT_PDF, new GanttExportSettings());
    project.doExport(info);
}
	/** Export directly in PNG image */
private static void exportPNG(String[] args) {
    byCommandLine = true;
    GanttProject project = new GanttProject(args[1]);
    File targetFile = null;
    if (args.length > 2)
        targetFile = new File(args[2]);
    ExportFileInfo info = new ExportFileInfo(targetFile, ExportFileInfo.FORMAT_PNG, new GanttExportSettings());
    project.doExport(info);
}
	/** Export directly in XFIG image */
private static void exportXFIG(String[] args) {
    byCommandLine = true;
    GanttProject project = new GanttProject(args[1]);
    File targetFile = null;
    if (args.length > 2)
        targetFile = new File(args[2]);
    ExportFileInfo info = new ExportFileInfo(targetFile, ExportFileInfo.FORMAT_XFIG, new GanttExportSettings());
    project.doExport(info);
}
	/** Export directly in CSV image */
private static void exportCSV(String[] args) {
    byCommandLine = true;
    GanttProject project = new GanttProject(args[1]);
    File targetFile = null;
    if (args.length > 2)
        targetFile = new File(args[2]);
    ExportFileInfo info = new ExportFileInfo(targetFile, ExportFileInfo.FORMAT_CSV, new GanttExportSettings());
    project.doExport(info);
}
	/** Export directly in JPG image */
private static void exportJPG(String[] args) {
    byCommandLine = true;
    GanttProject project = new GanttProject(args[1]);
    File targetFile = null;
    if (args.length > 2)
        targetFile = new File(args[2]);
    ExportFileInfo info = new ExportFileInfo(targetFile, ExportFileInfo.FORMAT_JPG, new GanttExportSettings());
    project.doExport(info);
}
	/** Change the style of the application */
public void changeLookAndFeel(GanttLookAndFeelInfo lookAndFeel) {
    try {
        UIManager.setLookAndFeel(lookAndFeel.getClassName());
        SwingUtilities.updateComponentTreeUI(this);
        this.lookAndFeel = lookAndFeel;
    } catch (Exception e) {
        GanttLookAndFeelInfo info = GanttLookAndFeels.getGanttLookAndFeels().getDefaultInfo();
        System.out.println("Can't find the LookAndFeel\n" + lookAndFeel.getClassName() + "\n" + lookAndFeel.getName() + "\nSetting the default Look'n'Feel" + info.getName());
        try {
            UIManager.setLookAndFeel(info.getClassName());
            SwingUtilities.updateComponentTreeUI(this);
            this.lookAndFeel = info;
        } catch (Exception ex) {
        }
    }
    //MetalLookAndFeel.setCurrentTheme(new GanttMetalTheme());
    //must force to do that instead of the task on tree are not in
    // continuity of the calendar
    tree.getJTree().setRowHeight(20);
}
	/** Export the calendar on a png file */
public void export() {
    ExportFileInfo info = selectExportFile(null);
    if (!info.equals(ExportFileInfo.EMPTY)) {
        doExport(info);
    }
}
	/** Print the project */
public void printProject() {
    if (tabpane.getSelectedIndex() == 0) {
        //Gantt Chart
        GanttExportSettings bool = new GanttExportSettings();
        //GanttDialogExport gde = new GanttDialogExport(this, bool, language);
        //gde.show();
        //if (bool.ok) 
        //	area.printProject(bool.name, bool.percent, bool.depend);
        area.printProject(options.getExportSettings());
    } else if (tabpane.getSelectedIndex() == 1) {
        //Resources Chart
        getResourcePanel().area.printProject(options.getExportSettings());
    }
}
	/** Print the help for ganttproject on the system.out */
private static void usage() {
    System.out.println();
    System.out.println("GanttProject usage : java -jar ganttproject-(VERSION).jar <OPTIONS>");
    System.out.println();
    System.out.println("  Here are the possible options:");
    System.out.println("    -h, --help : Print this message");
    System.out.println("    [project_file_name] a XML file based on ganttproject format to directly open (project.xml or project.gan)");
    System.out.println("    -html [project_file_name] [export_directory_name], export directly a ganttproject file to web pages");
    System.out.println("         -xsl-dir [xsl_directory]                        localisation of the xsl directory for html export");
    System.out.println("    -pdf  [project_file_name] [pdf_file_name],         export directly a ganttproject file to web pages");
    System.out.println("         -xsl-fo [xsl_fo_file]                           localisation of the xsl-fo file for pdf export");
    System.out.println("    -csv  [project_file_name] [csv_image_filename],    export directly a ganttproject file to csv document compatible with spreadsheets");
    System.out.println("    -png  [project_file_name] [png_image_filename],    export directly a ganttproject file to png image");
    System.out.println("    -jpg  [project_file_name] [jpg_image_filename],    export directly a ganttproject file to jpg image");
    System.out.println("    -fig/-xfig  [project_file_name] [fig_image_filename],    export directly a ganttproject file to xfig image");
    System.out.println();
    System.out.println("    In all these cases the project_file_name can either be a file on local disk or an URL.");
    System.out.println("    If the URL is password-protected, you can give credentials this way:");
    System.out.println("      http://username:password@example.com/filename");
    System.out.println(" ");
}
}
