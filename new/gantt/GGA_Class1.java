public class Class1 {
	private Object PLAIN;
	private Object depend;
	private Object black;
	private Object percent;
	private Object white;
	private Object TYPE_INT_RGB;
	private Object name;
	private Object width;
	private Object border3d;

	public Class1(Object PLAIN, Object depend, Object black, Object percent, Object white, Object TYPE_INT_RGB, Object name, Object width, Object border3d){
		this.PLAIN = PLAIN;
		this.depend = depend;
		this.black = black;
		this.percent = percent;
		this.white = white;
		this.TYPE_INT_RGB = TYPE_INT_RGB;
		this.name = name;
		this.width = width;
		this.border3d = border3d;
	}
	/** draw the panel */
public void paintComponent(Graphics g) {
    //Super paint component!!!!!!!!!!
    super.paintComponent(g);
    //Get all task
    listOfTask = tree.getAllTasks();
    //Vertical bars
    paintCalendar1(g);
    //The tasks
    paintTasks(g);
    //The depends
    if (drawdepend) {
        paintDepend(g);
    //The part at top
    }
    paintCalendar2(g);
    arrow.paint(g);
    notes.paint(g);
    if (drawVersion) {
        drawGPVersion(g);
    }
}
	/** Print the list of tasks */
private void printTasks(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setColor(Color.black);
    g.setFont(myUIConfiguration.getChartMainFont());
    printTask(g, 5, 42, getTree().getAllChildTask(getTree().getRoot()));
}
	/** Draw a vertical dash line from point (x1, y1) to (x2, y2) */
public void drawVerticalLinedash(Graphics g, int x, int y1, int y2, int size) {
    int i = y1;
    int end = y2;
    if (y1 > y2) {
        i = y2;
        end = y1;
    }
    int size2 = size + size;
    while (i < end) {
        g.drawLine(x, i, x, i + size);
        i = i + size2;
    }
}
	/** Return an image with the gantt chart*/
public BufferedImage getChart(GanttExportSettings settings) {
    calcProjectBegAndEnd();
    int rendering = 0;
    GanttCalendar date2 = new GanttCalendar(date);
    date = new GanttCalendar(beg);
    zoomToBegin();
    GanttCalendar start = new GanttCalendar(date);
    date = new GanttCalendar(date2);
    while (start.compareTo(end) <= 0) {
        changeDate2(start);
        rendering++;
    }
    //Make save of parameters
    int oldMargY = margY;
    int oldHeight = getHeight();
    int height = 20;
    int width = getWidth();
    margY = 0;
    drawdepend = settings.depend;
    drawPercent = settings.percent;
    drawName = settings.name;
    draw3dBorders = settings.border3d;
    //change the Height of the panel
    //Size of the content table for the tasks list
    int sizeTOC = 0;
    BufferedImage tmpImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
    FontMetrics fmetric = tmpImage.getGraphics().getFontMetrics(new Font("SansSerif", Font.PLAIN, 10));
    ArrayList lot = getTree().getAllTasks();
    for (Iterator tasks = lot.iterator(); tasks.hasNext(); ) {
        DefaultMutableTreeNode nextTreeNode = (DefaultMutableTreeNode) tasks.next();
        Task next = (Task) nextTreeNode.getUserObject();
        if ("None".equals(next.toString())) {
            continue;
        }
        if (isVisible(next)) {
            height += 20;
            int nbchar = fmetric.stringWidth(next.getName());
            if (nbchar > sizeTOC)
                sizeTOC = nbchar;
        }
    }
    //for the indentation of the tasks, but I fiw 50 pixel correcpond to 7 sub-taks indentation
    sizeTOC += 50;
    //If there is only the root task
    if (lot.size() == 1) {
        setSize(width, oldHeight);
        rendering = 1;
    } else
        setSize(width, height + 40);
    int calculateWidth = width * rendering;
    if (drawName) {
        rendering++;
        calculateWidth += sizeTOC;
    }
    BufferedImage image = new BufferedImage(calculateWidth, getHeight(), BufferedImage.TYPE_INT_RGB);
    start = new GanttCalendar(date);
    date = new GanttCalendar(beg);
    zoomToBegin();
    if (zoomValue == ONE_WEEK || zoomValue == TWO_WEEK)
        changeDate(false);
    int transx = 0, transx2;
    for (int i = 0; i < rendering; i++) {
        BufferedImage image2 = new BufferedImage(width, getHeight(), BufferedImage.TYPE_INT_RGB);
        if (i == 0 && drawName) {
            rowCount = 0;
            image2 = null;
            image2 = new BufferedImage(sizeTOC, getHeight(), BufferedImage.TYPE_INT_RGB);
            setSize(sizeTOC, getHeight());
            printTasks(image2.getGraphics());
            setSize(width, getHeight());
            transx2 = sizeTOC;
        } else {
            paintComponent(image2.getGraphics());
            changeDate2(date);
            transx2 = width;
        }
        Graphics g = image.getGraphics();
        g.translate(transx, 0);
        g.drawImage(image2, 0, 0, null);
        image2 = null;
        transx += transx2;
    }
    upperLeft = new Point(0, 0);
    drawGPVersion(image.getGraphics());
    date = new GanttCalendar(start);
    zoomToBegin();
    margY = oldMargY;
    drawdepend = true;
    drawPercent = true;
    drawName = false;
    draw3dBorders = true;
    setSize(getSize().width, oldHeight);
    repaint();
    return image;
}
}