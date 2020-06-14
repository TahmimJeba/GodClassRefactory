public class Class0 {
	private Object date;
	private Object MONTH;
	private Object out;
	private Object BUTTON1;
	private Object WEEK_OF_YEAR;
	private Object YEAR;
	private Object taskID;
	private Object area;
	private Object W_RESIZE_CURSOR;
	private Object DEFAULT_CURSOR;
	private Object E_RESIZE_CURSOR;

	public Class0(Object date, Object MONTH, Object out, Object BUTTON1, Object WEEK_OF_YEAR, Object YEAR, Object taskID, Object area, Object W_RESIZE_CURSOR, Object DEFAULT_CURSOR, Object E_RESIZE_CURSOR){
		this.date = date;
		this.MONTH = MONTH;
		this.out = out;
		this.BUTTON1 = BUTTON1;
		this.WEEK_OF_YEAR = WEEK_OF_YEAR;
		this.YEAR = YEAR;
		this.taskID = taskID;
		this.area = area;
		this.W_RESIZE_CURSOR = W_RESIZE_CURSOR;
		this.DEFAULT_CURSOR = DEFAULT_CURSOR;
		this.E_RESIZE_CURSOR = E_RESIZE_CURSOR;
	}
	/** Change the zoom value */
public void setZoom(int z) {
    zoomValue = z;
}
	//Release the mouse button
public void mouseReleased(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        if (moveView) {
            int f = 1;
            int x = oldX - e.getX();
            if (x > 2) {
                f = -1;
            } else if (x < -2) {
                f = 1;
            } else {
                //If the move is too short, do nothing and return
                drag = -1;
                arrow.setDraw(false);
                notes.setDraw(false);
                repaint();
                return;
            }
            if (appli.getOptions().getDragTime()) {
                switch(zoomValue) {
                    case ONE_WEEK:
                    case TWO_WEEK:
                        date.go(Calendar.WEEK_OF_YEAR, 1 * f);
                        break;
                    case ONE_MONTH:
                    case TWO_MONTH:
                    case THREE_MONTH:
                    case FOUR_MONTH:
                    case SIX_MONTH:
                        date.go(Calendar.MONTH, 1 * f);
                        break;
                    case ONE_YEAR:
                    case TWO_YEAR:
                    case THREE_YEAR:
                        date.go(Calendar.YEAR, 1 * f);
                        break;
                }
                appli.getResourcePanel().area.date = date.Clone();
            }
        }
        if (arrow.getDraw() && typeSeletion == 2) {
            //Search for the second task to link
            int second = detectPosition(e.getX(), e.getY(), true);
            //if a task has been found
            if (second != -1) {
                GanttPaintParam param = (GanttPaintParam) listOfParam.get(second);
                //String taskName = param.name;
                //GanttTask secondTask1 = GanttTask.getTaskByTaskID(param.taskID);
                //.createTask();
                GanttTask secondTask = getTaskManager().getTask(param.taskID);
                if (taskToMove != null && secondTask != null) {
                    if (tree.checkDepend(taskToMove, secondTask)) {
                        //GanttTask secondTask = tree.getTask(taskName);
                        try {
                            getTaskManager().getDependencyCollection().createDependency(secondTask, taskToMove);
                        } catch (TaskDependencyException e1) {
                            //To change body of catch statement use File | Settings | File Templates.
                            e1.printStackTrace();
                        }
                        //                   taskToMove.addSuccessor(new GanttTaskRelationship(//
                        //	                    -1
                        //	                    //taskToMove.getTaskID()
                        //	                    , secondTask.getTaskID(), GanttTaskRelationship.FS, getTaskManager()));
                        //tree.forwardScheduling();
                        appli.setAskForSave(true);
                    }
                }
            }
        }
        arrow.setDraw(false);
        notes.setDraw(false);
        moveTask = -1;
        drag = -1;
        tree.forwardScheduling();
        repaint();
    }
}
	//Move the move on the area
public void mouseMoved(MouseEvent e) {
    int detect = detectPosition(e.getX(), e.getY(), false);
    if (detect == -1) {
        if (curs) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            curs = false;
        }
        arrow.setDraw(false);
        notes.setDraw(false);
    } else {
        if (typeSeletion == 0) {
            setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
            curs = true;
        } else //special cursor
        if (typeSeletion == 3) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            try {
                setCursor(toolkit.createCustomCursor(toolkit.getImage(getClass().getClassLoader().getResource("icons/cursorpercent.gif")), new Point(10, 5), "CursorPercent"));
            } catch (Exception exept) {
                setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
            }
        } else if (typeSeletion == 1) {
            setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
            curs = true;
        } else {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
}
	/** Change the color of the task */
public void setProjectLevelTaskColor(Color c) {
    myProjectLevelTaskColor = c;
}
	/** The te task we want to move */
public void setTaskToMove(int index) {
    GanttPaintParam param = (GanttPaintParam) listOfParam.get(index);
    taskToMove = getTaskManager().getTask(param.taskID);
/*for (Iterator it = listOfTask.iterator(); it.hasNext(); ) {
      DefaultMutableTreeNode nextTreeNode = (DefaultMutableTreeNode) it.next();
      GanttTask task = (GanttTask) nextTreeNode.getUserObject();

      //if(!task.toString().equals("None"))
      {
        int ID2 = task.getTaskID();

        if (ID2 == tID) {

          //taskToMove = GanttTask.getTaskByTaskID(ID2);
          taskToMove = task;
        }
      }
    }*/
}
	/** The size of the panel. */
public Dimension getPreferredSize() {
    return new Dimension(465, 600);
}
	/** Method to change the date */
public void changeDate(boolean next) {
    int f = 1;
    if (!next) {
        f = -1;
    }
    switch(zoomValue) {
        case ONE_WEEK:
        case TWO_WEEK:
            date.go(Calendar.WEEK_OF_YEAR, 1 * f);
            break;
        case ONE_MONTH:
        case TWO_MONTH:
        case THREE_MONTH:
        case FOUR_MONTH:
        case SIX_MONTH:
            date.go(Calendar.MONTH, 1 * f);
            break;
        case ONE_YEAR:
        case TWO_YEAR:
        case THREE_YEAR:
            date.go(Calendar.YEAR, 1 * f);
            break;
    }
}
	/** Method to change the date */
public void changeDate2(GanttCalendar gc) {
    switch(zoomValue) {
        case ONE_WEEK:
            gc.go(Calendar.WEEK_OF_YEAR, 1);
            break;
        case TWO_WEEK:
            gc.go(Calendar.WEEK_OF_YEAR, 2);
            break;
        case ONE_MONTH:
            gc.go(Calendar.MONTH, 1);
            break;
        case TWO_MONTH:
            gc.go(Calendar.MONTH, 2);
            break;
        case THREE_MONTH:
            gc.go(Calendar.MONTH, 3);
            break;
        case FOUR_MONTH:
            gc.go(Calendar.MONTH, 4);
            break;
        case SIX_MONTH:
            gc.go(Calendar.MONTH, 6);
            break;
        case ONE_YEAR:
            gc.go(Calendar.YEAR, 1);
            break;
        case TWO_YEAR:
            gc.go(Calendar.YEAR, 2);
            break;
        case THREE_YEAR:
            gc.go(Calendar.YEAR, 3);
            break;
    }
}
	/** Method when zoomin, to set at the begin for each value */
public void zoomToBegin() {
    switch(zoomValue) {
        case ONE_WEEK:
        case TWO_WEEK:
            String d = language.getDay(date.getDayWeek());
            while (!d.equals(language.getDay(2))) {
                date.add(1);
                d = language.getDay(date.getDayWeek());
            }
            break;
        case ONE_MONTH:
        case TWO_MONTH:
        case THREE_MONTH:
        case FOUR_MONTH:
        case SIX_MONTH:
            date.setDay(1);
            break;
        case ONE_YEAR:
        case TWO_YEAR:
        case THREE_YEAR:
            date.setMonth(0);
            date.setDay(1);
            break;
    }
}
	/** Search for a coef on the arraylist */
public int indexOf(ArrayList listOfParam, int id) {
    //String coef)
    int i = 0;
    for (Iterator it = listOfParam.iterator(); it.hasNext(); ++i) {
        if (id == ((GanttPaintParam) it.next()).taskID) {
            return i;
        }
    }
    return -1;
}
	/** Return the value of the JTree's Scrollbar */
public int getScrollBar() {
    return margY;
}
	/** Add a zoom*/
public void zoomMore() {
    if (zoomValue == 5) {
        olddate = date.Clone();
    }
    zoomValue++;
}
	/**Less a zoom*/
public void zoomLess() {
    if (zoomValue == 6 && date.getYear() == olddate.getYear()) {
        date = olddate.Clone();
    }
    zoomValue--;
}
	/** Return  the zoom value */
public int getZoom() {
    return zoomValue;
}
	/** Change the date of the begin to paint */
public void setDate(GanttCalendar d) {
    date = d;
}
	/** Return the date */
public GanttCalendar getDate() {
    return date;
}
	/** Return the advance foot  */
public int getFoot() {
    int res = 1;
    switch(zoomValue) {
        case ONE_YEAR:
        case TWO_YEAR:
        case THREE_YEAR:
            res = date.getNumberOfDay();
            break;
    }
    return res;
}
	/** Print the project */
public void printProject(GanttExportSettings settings) {
    //For printing the project, begin to create temporary BufferedImage for the entire graphics
    //Then use a class to print it 
    printRendering = true;
    BufferedImage image = getChart(settings);
    printRendering = false;
    PrinterJob printJob = PrinterJob.getPrinterJob();
    printJob.setPrintable(new GanttPrintable(image));
    if (printJob.printDialog()) {
        try {
            printJob.print();
        } catch (Exception PrintException) {
            System.out.println("Print Error" + PrintException);
            PrintException.printStackTrace();
        }
    }
}
	/** Function able to export in PNG format the graphic area */
public void export(File file, GanttExportSettings settings, String format) {
    /*BufferedImage image = new BufferedImage(getWidth(), getHeight(),
                                            BufferedImage.TYPE_INT_RGB);

    drawdepend = depend;
    drawPercent = percent;
    drawName = name;
    drawVersion = true;
    paintComponent(image.getGraphics());
    drawdepend = true;
    drawPercent = true;
    drawName = false;
    drawVersion = false;*/
    BufferedImage image = getChart(settings);
    try {
        if (file == null)
            ImageIO.write(image, format, System.out);
        else
            ImageIO.write(image, format, file);
    } catch (Exception e) {
        System.out.println(e);
    }
}
}