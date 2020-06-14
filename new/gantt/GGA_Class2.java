public class Class2 {
	private Object THREE_MONTH;
	private Object TWO_MONTH;
	private Object ONE_WEEK;
	private Object type;
	private Object red;
	private Object YEAR;
	private Object white;
	private Object PLAIN;
	private Object TWO_YEAR;
	private Object SIX_MONTH;
	private Object FOUR_MONTH;
	private Object x1;
	private Object x2;
	private Object x3;
	private Object FF;
	private Object DAY_OF_YEAR;
	private Object FS;
	private Object SF;
	private Object ONE_YEAR;
	private Object SS;
	private Object ONE_MONTH;
	private Object black;
	private Object length;
	private Object TWO_WEEK;
	private Object THREE_YEAR;
	private Object width;
	private Object y;

	public Class2(Object THREE_MONTH, Object TWO_MONTH, Object ONE_WEEK, Object type, Object red, Object YEAR, Object white, Object PLAIN, Object TWO_YEAR, Object SIX_MONTH, Object FOUR_MONTH, Object x1, Object x2, Object x3, Object FF, Object DAY_OF_YEAR, Object FS, Object SF, Object ONE_YEAR, Object SS, Object ONE_MONTH, Object black, Object length, Object TWO_WEEK, Object THREE_YEAR, Object width, Object y){
		this.THREE_MONTH = THREE_MONTH;
		this.TWO_MONTH = TWO_MONTH;
		this.ONE_WEEK = ONE_WEEK;
		this.type = type;
		this.red = red;
		this.YEAR = YEAR;
		this.white = white;
		this.PLAIN = PLAIN;
		this.TWO_YEAR = TWO_YEAR;
		this.SIX_MONTH = SIX_MONTH;
		this.FOUR_MONTH = FOUR_MONTH;
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.FF = FF;
		this.DAY_OF_YEAR = DAY_OF_YEAR;
		this.FS = FS;
		this.SF = SF;
		this.ONE_YEAR = ONE_YEAR;
		this.SS = SS;
		this.ONE_MONTH = ONE_MONTH;
		this.black = black;
		this.length = length;
		this.TWO_WEEK = TWO_WEEK;
		this.THREE_YEAR = THREE_YEAR;
		this.width = width;
		this.y = y;
	}
	/** Paint all tasks  */
public void paintTasks(Graphics g) {
    int sizex = getWidth();
    int sizey = getHeight();
    int headery = 45;
    float fgra = (float) sizex / (float) getGranit(true);
    g.setFont(myUIConfiguration.getChartMainFont());
    //Get all task
    //Probably optimised on next release
    listOfParam.clear();
    int y = 0;
    for (Iterator tasks = listOfTask.iterator(); tasks.hasNext(); ) {
        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tasks.next();
        GanttTask task = (GanttTask) treeNode.getUserObject();
        //Is the task is visible, the task could be draw
        if (isVisible(task)) {
            int x1 = -10, x2 = sizex + 10;
            //ecart entre la date de debut de la tache et la date du debut du calendrier
            int e1;
            int fois;
            int type = 2;
            y++;
            //difference between the start date of the task and the end
            e1 = date.diff(task.getStart());
            //Calcul start and end pixel of each task
            float fx1, fx2;
            if (task.isMilestone()) {
                fx1 = (float) e1 * fgra * ((date.compareTo(task.getStart()) == 1) ? -1 : 1);
                x1 = (int) fx1;
            } else {
                fx1 = (float) e1 * fgra * ((date.compareTo(task.getStart()) == 1) ? -1 : 1);
                fx2 = fx1 + (float) task.getLength() * fgra;
                x1 = (int) fx1;
                x2 = (int) fx2;
            }
            int percent = 0;
            //Meeting task
            if (task.isMilestone()) {
                paintATaskBilan(g, x1, y, task);
                x2 = x1 + (int) fgra;
                type = 0;
            } else //A mother task
            if (tree.getAllChildTask(treeNode).size() != 0) {
                //Compute percent-complete
                tree.computePercentComplete(treeNode);
                paintATaskFather(g, x1, x2, y, task);
                if (drawPercent) {
                    percent = paintAdvancement(g, x1, x2, y, task.getCompletionPercentage(), task.getShape(), task.getColor(), true);
                }
                type = 1;
            } else //A normal task
            {
                paintATaskChild(g, x1, x2, y, task);
                if (drawPercent) {
                    percent = paintAdvancement(g, x1, x2, y, task.getCompletionPercentage(), task.getShape(), task.getColor(), false);
                }
                type = 2;
            }
            //Add parameters on the array
            listOfParam.add(new GanttPaintParam(task.getName(), task.getTaskID(), x1, x2, percent, y, type));
        }
    }
}
	/** Draw a meeting task */
public void paintATaskBilan(Graphics g, int x1, int y, Task task) {
    int d = y;
    y = y * 20 + 32 - margY;
    if (y < 20 || y - 5 > getHeight()) {
        //Not draw if the task is not on the area
        return;
    }
    if (x1 > getWidth() || x1 < 0) {
        return;
    }
    //the granularity
    int gra = getWidth() / getGranit(false);
    float fgra = (float) getWidth() / (float) getGranit(true);
    int[] xPoints = new int[4];
    int[] yPoints = new int[4];
    xPoints[0] = (int) ((float) x1 + fgra / 2);
    xPoints[1] = (int) ((float) x1 + fgra / 2 - 5);
    xPoints[2] = (int) ((float) x1 + fgra / 2);
    xPoints[3] = (int) ((float) x1 + fgra / 2 + 5);
    yPoints[0] = y - 5;
    yPoints[1] = y;
    yPoints[2] = y + 5;
    yPoints[3] = y;
    if (drag == d - 1) {
        g.setColor(arrayColor[4]);
    } else {
        if (task.getColor().equals(myUIConfiguration.getTaskColor())) {
            g.setColor(Color.black);
        } else {
            g.setColor(task.getColor());
        }
    }
    g.fillPolygon(xPoints, yPoints, 4);
    g.setColor(arrayColor[8]);
    g.drawPolygon(xPoints, yPoints, 4);
    //Draw the resource list after the task
    paintResources(x1 + 16, y + 5, task, g);
}
	/** Draw a monther task */
public void paintATaskFather(Graphics g, int x1, int x2, int y, Task task) {
    int d = y;
    y = y * 20 + 35 - margY;
    if (y < 20 || y > getHeight()) {
        //Not draw if the task is not on the area
        return;
    }
    if ((x1 > getWidth() && x2 > getWidth()) || (x1 < 0 && x2 < 0)) {
        return;
    }
    //Black rectangle
    if (drag == d - 1) {
        g.setColor(arrayColor[4]);
    } else {
        if (task.getColor().equals(myUIConfiguration.getTaskColor())) {
            g.setColor(Color.black);
        } else {
            g.setColor(/*Color.black*/
            task.getColor());
        }
    }
    g.fillRect(x1, y, x2 - x1, 2);
    //Little triangle at begin and end
    int[] xPoints = new int[3];
    int[] yPoints = new int[3];
    xPoints[0] = x1;
    xPoints[1] = x1 + 5;
    xPoints[2] = x1;
    yPoints[0] = y + 2;
    yPoints[1] = y + 2;
    yPoints[2] = y + 6;
    g.fillPolygon(xPoints, yPoints, 3);
    xPoints[0] = x2;
    xPoints[1] = x2 - 5;
    xPoints[2] = x2;
    g.fillPolygon(xPoints, yPoints, 3);
    //Draw the resource list after the task
    paintResources(x2 + 40, y + 9, task, g);
}
	/** Return the color of the task */
public Color getTaskColor() {
    return myProjectLevelTaskColor == null ? myUIConfiguration.getTaskColor() : myProjectLevelTaskColor;
}
	/** Detect if the position of the mouse is on a special place (return -1 if nothing or the numer of the task*/
public int detectPosition(int mx, int my, boolean all) {
    for (int i = 0; i < listOfParam.size(); i++) {
        GanttPaintParam param = (GanttPaintParam) listOfParam.get(i);
        if (((param.type == 2 || param.type == 0) && !all) || all) {
            int y = param.y * 20 + 27 - margY;
            int x1 = param.x1;
            int x2 = param.x2;
            int x3 = param.x3;
            if ((my >= y && my <= y + 12)) {
                //The end of the task
                if (mx >= x2 && /*-2*/
                mx <= x2 + 2) {
                    typeSeletion = 0;
                    setTaskToMove(i);
                    GanttCalendar enddate = taskToMove.getEnd().newAdd(-1);
                    notes = new Notes(enddate.toString(), mx, y - 30);
                    arrow.setDraw(false);
                    return i;
                } else //the start of the task
                if (mx >= x1 - 2 && mx <= x1) /*+2*/
                {
                    typeSeletion = 1;
                    setTaskToMove(i);
                    notes = new Notes(taskToMove.getStart().toString(), mx, y - 30);
                    arrow.setDraw(false);
                    return i;
                } else //the percent length
                if (mx >= x3 - 2 && mx <= x3 + 2) {
                    typeSeletion = 3;
                    setTaskToMove(i);
                    notes = new Notes("  " + taskToMove.getCompletionPercentage() + "%", mx, y - 30);
                    arrow.setDraw(false);
                    return i;
                } else //A depend
                if (mx > x1 + 3 && mx < x2 - 3) {
                    typeSeletion = 2;
                    if (param.type != 0) {
                        arrow = new Arrow(mx, y + 6, mx, y + 6);
                        notes.setDraw(false);
                    } else {
                        setTaskToMove(i);
                        notes = new Notes(taskToMove.getStart().toString(), mx, y - 30);
                    }
                    return i;
                }
            }
        }
    }
    return -1;
}
	/** Is the Task visible on the JTree */
public boolean isVisible(Task thetask) {
    boolean res = true;
    //ArrayList expand = tree.getExpand();
    DefaultMutableTreeNode father = tree.getFatherNode(thetask);
    //The roor task is not visible
    if (father == null) {
        return false;
    }
    while (father != null) {
        //if (!expand.contains(new Integer(((Task)(father.getUserObject())).getTaskID()))) {
        Task taskFather = (Task) (father.getUserObject());
        if (!taskFather.getExpand())
            res = false;
        //Task t = (Task)father.getUserObject();
        //father = tree.getFatherNode(t);
        father = (DefaultMutableTreeNode) (father.getParent());
    }
    return res;
}
	/** Change the velue connected to the JTree's Scrollbar */
public void setScrollBar(int v) {
    margY = v;
}
	/** Return the number of day visible for each level of granularity */
public int getGranit(boolean day) {
    GanttCalendar cal;
    //by default the 7 days of the week
    int res = 7;
    switch(zoomValue) {
        case ONE_WEEK:
            res = 7;
            break;
        case TWO_WEEK:
            res = 14;
            break;
        case ONE_MONTH:
            res = date.getNumberOfDay();
            break;
        case TWO_MONTH:
            cal = date.Clone();
            res = cal.getNumberOfDay();
            cal.goNextMonth();
            res += cal.getNumberOfDay();
            break;
        case THREE_MONTH:
            cal = date.Clone();
            res = 0;
            for (int i = 0; i < 3; i++) {
                res += cal.getNumberOfDay();
                cal.goNextMonth();
            }
            break;
        case FOUR_MONTH:
            cal = date.Clone();
            res = 0;
            for (int i = 0; i < 4; i++) {
                res += cal.getNumberOfDay();
                cal.goNextMonth();
            }
            break;
        case SIX_MONTH:
            cal = date.Clone();
            res = 0;
            for (int i = 0; i < 6; i++) {
                res += cal.getNumberOfDay();
                cal.goNextMonth();
            }
            break;
        case ONE_YEAR:
            if (!day) {
                res = 12;
            } else {
                res = date.getActualMaximum(Calendar.DAY_OF_YEAR);
            }
            break;
        case TWO_YEAR:
            if (!day) {
                res = 12 * 2;
            } else {
                cal = date.Clone();
                res = 0;
                for (int i = 0; i < 2; i++) {
                    res += cal.getActualMaximum(Calendar.DAY_OF_YEAR);
                    cal.go(Calendar.YEAR, 1);
                }
            }
            break;
        case THREE_YEAR:
            if (!day) {
                res = 12 * 3;
            } else {
                cal = date.Clone();
                res = 0;
                for (int i = 0; i < 3; i++) {
                    res += cal.getActualMaximum(Calendar.DAY_OF_YEAR);
                    cal.go(Calendar.YEAR, 1);
                }
            }
            break;
    }
    return res;
}
	/** Draw the legend of the calendar */
public void paintCalendar2Old(Graphics g) {
    int sizex = getWidth();
    int sizey = getHeight();
    int headery = 45;
    int gra2 = getGranit(false);
    //the granularity
    float fgra = (float) (sizex) / (float) getGranit(false);
    g.setFont(new Font("SansSerif", Font.PLAIN, 12));
    //gray rectangle with nice borders
    g.setColor(arrayColor[0]);
    g.fillRect(0, 0, sizex, headery);
    g.setColor(arrayColor[4]);
    g.drawRect(0, 0, sizex - 1, headery / 2);
    g.drawRect(0, headery / 2, sizex - 1, headery / 2);
    /*if(draw3dBorders) {
    	g.setColor(arrayColor[5]);
    	g.drawLine(1, headery / 2 - 1, sizex - 2, headery / 2 - 1);
    	g.drawLine(0, headery - 2, sizex - 2, headery - 2);
    	g.setColor(Color.white);
    	g.drawLine(1, 1, sizex - 2, 1);
    	g.drawLine(0, headery / 2 + 1, sizex - 2, headery / 2 + 1);
    }*/
    g.setColor(Color.black);
    //Set the text at the top (differetns for each zaoom value)
    float posX;
    GanttCalendar cal;
    switch(zoomValue) {
        //The number of week and th month
        case ONE_WEEK:
            g.drawString(language.getText("week") + date.getWeek() + " - " + date.getdayMonth() + "  " + date.getYear(), 2, headery / 2 - 5);
            break;
        case TWO_WEEK:
            GanttCalendar date_2 = date.newAdd(7);
            g.drawString(language.getText("week") + (date.getWeek()) + " - " + date.getdayMonth() + "  " + date.getYear(), 2, headery / 2 - 5);
            g.drawString(language.getText("week") + (date_2.getWeek() + 1) + " - " + date_2.getdayMonth() + "  " + date_2.getYear(), (int) (fgra * 7) + 2, headery / 2 - 5);
            break;
        //The month and the yeay
        case ONE_MONTH:
            g.drawString(date.getdayMonth() + "  " + date.getYear(), 2, headery / 2 - 5);
            break;
        // Draw two months
        case TWO_MONTH:
            cal = date.Clone();
            posX = 0;
            for (int i = 0; i < 2; i++) {
                g.drawString(cal.getdayMonth() + "  " + cal.getYear(), (int) posX + 2, /*i*sizex/3+2*/
                headery / 2 - 5);
                posX += ((float) cal.getNumberOfDay() * fgra);
                cal.goNextMonth();
            }
            break;
        //Draw three months
        case THREE_MONTH:
            cal = date.Clone();
            posX = 0;
            for (int i = 0; i < 3; i++) {
                g.drawString(cal.getdayMonth() + "  " + cal.getYear(), (int) posX + 2, /*i*sizex/3+2*/
                headery / 2 - 5);
                posX += ((float) cal.getNumberOfDay() * fgra);
                cal.goNextMonth();
            }
            break;
        // draw 4 months
        case FOUR_MONTH:
            cal = date.Clone();
            posX = 0;
            for (int i = 0; i < 4; i++) {
                String dm = cal.getdayMonth();
                g.drawString(dm.substring(0, (sizex > 480) ? (dm.length() < 3 ? dm.length() : 3) : 1) + "  " + new Integer(cal.getYear()).toString().substring(2, 4), (int) posX + 2, /*i*sizex/3+2*/
                headery / 2 - 5);
                posX += ((float) cal.getNumberOfDay() * fgra);
                cal.goNextMonth();
            }
            break;
        // draw 6 months
        case SIX_MONTH:
            cal = date.Clone();
            posX = 0;
            for (int i = 0; i < 6; i++) {
                String dm = cal.getdayMonth();
                g.drawString(dm.substring(0, (sizex > 480) ? (dm.length() < 3 ? dm.length() : 3) : 1) + "  " + new Integer(cal.getYear()).toString().substring(2, 4), (int) posX + 2, /*i*sizex/3+2*/
                headery / 2 - 5);
                posX += ((float) cal.getNumberOfDay() * fgra);
                cal.goNextMonth();
            }
            break;
        //Draw One year
        case ONE_YEAR:
            g.drawString("" + date.getYear(), 2, headery / 2 - 5);
            break;
        // Draw two years
        case TWO_YEAR:
            int dy2 = date.getYear();
            for (int i = 0; i < 2; i++, dy2++) {
                g.setColor(Color.black);
                g.drawString("" + dy2, i * sizex / 2 + 2, headery / 2 - 5);
            }
            break;
        //draw thre years
        case THREE_YEAR:
            int dy = date.getYear();
            for (int i = 0; i < 3; i++, dy++) {
                g.setColor(Color.black);
                g.drawString("" + dy, i * sizex / 3 + 2, headery / 2 - 5);
            }
            break;
    }
    GanttCalendar tmpdate;
    if (zoomValue == ONE_WEEK || zoomValue == TWO_WEEK) {
        tmpdate = date.Clone();
    } else {
        tmpdate = new GanttCalendar(date.getYear(), date.getMonth(), 1);
    }
    g.setFont(new Font("SansSerif", Font.PLAIN, 10));
    //Draw each day or each month
    for (int i = 0; i < gra2; i++) {
        String sDay = tmpdate.getdayWeek();
        String sMonth = tmpdate.getdayMonth();
        g.setColor(Color.black);
        switch(zoomValue) {
            case ONE_WEEK:
                g.drawString(sDay.substring(0, (sizex > 300) ? (sDay.length() < 3 ? sDay.length() : 3) : 1) + " " + tmpdate.getDate(), (int) (fgra * i) + 2, headery - 7);
                g.setColor(arrayColor[4]);
                g.drawLine((int) (fgra * i), headery / 2, (int) (fgra * i), headery - 1);
                break;
            case TWO_WEEK:
                g.drawString(sDay.substring(0, 1) + " " + tmpdate.getDate(), (int) (fgra * i) + 2, headery - 7);
                g.setColor(arrayColor[4]);
                g.drawLine((int) (fgra * i), headery / 2, (int) (fgra * i), headery - 1);
                break;
            case ONE_MONTH:
                g.drawString("" + tmpdate.getDate(), (int) (fgra * i) + 3, headery - 7);
                g.setColor(arrayColor[4]);
                g.drawLine((int) (fgra * i), headery / 2, (int) (fgra * i), headery - 1);
                break;
            case TWO_MONTH:
            case THREE_MONTH:
            case FOUR_MONTH:
            case SIX_MONTH:
                if (sDay.equals(language.getDay(1))) {
                    g.drawString("" + tmpdate.getDate(), (int) (fgra * i) + 2, headery - 7);
                    g.setColor(arrayColor[4]);
                    g.drawLine((int) (fgra * i), headery / 2, (int) (fgra * i), headery - 1);
                }
                break;
            case ONE_YEAR:
                g.drawString(sMonth.substring(0, (sizex > 300) ? (sMonth.length() < 3 ? sMonth.length() : 3) : 1), (int) (fgra * i) + 2, headery - 7);
                g.setColor(arrayColor[4]);
                g.drawLine((int) (fgra * i), headery / 2, (int) (fgra * i), headery - 1);
                break;
            case TWO_YEAR:
            case THREE_YEAR:
                g.drawString(sMonth.substring(0, 1), (int) (fgra * i) + 2, headery - 7);
                g.setColor(arrayColor[4]);
                g.drawLine((int) (fgra * i), headery / 2, (int) (fgra * i), headery - 1);
                break;
        }
        //next date
        tmpdate.add(getFoot());
    }
}
	/** Paint the vertical bars */
public void paintCalendar1(Graphics g) {
    int sizex = getWidth();
    int sizey = getHeight();
    int headery = 45;
    //The granularity
    int gra = sizex / getGranit(false);
    int gra2 = getGranit(false);
    float fgra = (float) sizex / (float) getGranit(false);
    int drawDate = -1;
    GanttCalendar dateToPaint = new GanttCalendar();
    g.setFont(new Font("SansSerif", Font.PLAIN, 12));
    //Reset the background to white
    g.setColor(Color.white);
    g.fillRect(0, 0, sizex, sizey);
    if (!printRendering) {
        calcProjectBegAndEnd();
    }
    //end.add(1);
    //Draw Horizontal bar on tasks
    /*g.setColor(arrayColor[14]);
       for(int i=-margY-4;i<getHeight();i+=40)
       {
     g.fillRect(0,i,sizex,20);
       }*/
    GanttCalendar tmpdate;
    if (zoomValue == ONE_WEEK || zoomValue == TWO_WEEK) {
        tmpdate = date.Clone();
    } else {
        tmpdate = new GanttCalendar(date.getYear(), date.getMonth(), 1);
    }
    g.setFont(new Font("SansSerif", Font.PLAIN, 10));
    //Draw the vertical bars
    for (int i = 0; i < gra2; i++) {
        String sDay = tmpdate.getdayWeek();
        String sMonth = tmpdate.getdayMonth();
        //For each day
        if (zoomValue < 7) {
            if (sDay.equals(language.getDay(6))) {
                g.setColor(arrayColor[0]);
                g.fillRect((int) (fgra * i), headery, gra + 1, /*(int)(fgra+0.5)*/
                sizey);
                g.setColor(arrayColor[1]);
                if (draw3dBorders)
                    g.drawLine((int) (fgra * i), headery, (int) (fgra * i), sizey + headery);
            } else if (sDay.equals(language.getDay(0))) {
                g.setColor(arrayColor[0]);
                g.fillRect((int) (fgra * i), headery, gra + 1, /*(int)(fgra+0.5)*/
                sizey);
                if (zoomValue == ONE_WEEK || zoomValue == TWO_WEEK) {
                    g.setColor(arrayColor[1]);
                    if (draw3dBorders)
                        g.drawLine((int) (fgra * i), headery, (int) (fgra * i), sizey + headery);
                }
            }
        } else {
            if (sMonth.equals(language.getMonth(0))) {
                g.setColor(arrayColor[0]);
                g.fillRect((int) (fgra * i), headery, (int) fgra, sizey);
            }
            g.setColor(arrayColor[1]);
            if (draw3dBorders)
                g.drawLine((int) (fgra * i), headery, (int) (fgra * i), sizey + headery);
        }
        if (zoomValue < 7) {
            if (tmpdate.compareTo(new GanttCalendar()) == 0) {
                g.setColor(arrayColor[2]);
                g.fillRect((int) (fgra * i), headery, (int) fgra, sizey);
                g.setColor(arrayColor[3]);
                if (draw3dBorders)
                    g.drawLine((int) (fgra * i), headery, (int) (fgra * i), sizey + headery);
            }
            // stavrides - red line at all zooms
            if (myUIConfiguration.isRedlineOn()) {
                GanttCalendar today = new GanttCalendar();
                float s = fgra / (float) tmpdate.getNumberOfDay();
                if (tmpdate.equals(today)) {
                    g.setColor(Color.red);
                    g.drawLine((int) (fgra * i) + (int) (s * today.getDay()), headery, (int) (fgra * i) + (int) (s * today.getDay()), sizey + headery);
                }
            }
        } else if (myUIConfiguration.isRedlineOn()) {
            GanttCalendar today = new GanttCalendar();
            float s = fgra / (float) tmpdate.getNumberOfDay();
            if (tmpdate.getYear() == today.getYear() && tmpdate.getMonth() == today.getMonth()) {
                //stavrides - remove blue box in favor of line
                //Blue rectange on today
                //g.setColor(arrayColor[2]);
                //g.fillRect( (int) (fgra * i) + (int) (s * today.getDay()), headery,
                //           ( (s < 2) ? 2 : (int) s), sizey);
                //g.setColor(arrayColor[2]);
                g.setColor(Color.red);
                g.drawLine((int) (fgra * i) + (int) (s * today.getDay()), headery, (int) (fgra * i) + (int) (s * today.getDay()), sizey + headery);
            }
        }
        tmpdate.add(getFoot());
    }
    if (zoomValue == ONE_WEEK || zoomValue == TWO_WEEK) {
        tmpdate = date.Clone();
    } else {
        tmpdate = new GanttCalendar(date.getYear(), date.getMonth(), 1);
    //Draw Miscallenous bars
    }
    for (int i = 0; i < gra2; i++) {
        //Courant day in blue
        if (zoomValue < 7) {
            //A line on begin and end of project
            if (tmpdate.compareTo(beg) == 0 || tmpdate.compareTo(end) == 0) {
                g.setColor(arrayColor[12]);
                //g.drawLine((int)(fgra*i),headery,(int)(fgra*i),sizey+headery);
                drawVerticalLinedash(g, (int) (fgra * i), headery, sizey + headery, 5);
                if (tmpdate.compareTo(beg) == 0) {
                    drawDate = (int) (fgra * i) - 52;
                    dateToPaint = beg;
                } else {
                    drawDate = (int) (fgra * i) + 3;
                    dateToPaint = end.newAdd(-1);
                }
            }
        } else {
            float s = fgra / (float) tmpdate.getNumberOfDay();
            //blue dash line for begin
            if (tmpdate.getYear() == beg.getYear() && tmpdate.getMonth() == beg.getMonth()) {
                g.setColor(arrayColor[12]);
                //g.drawLine((int)(fgra*i)+(int)(s*beg.getDay()),headery,(int)(fgra*i)+(int)(s*beg.getDay()),sizey+headery);
                int xValue = (int) (fgra * i) + (int) (s * beg.getDay());
                drawVerticalLinedash(g, (int) xValue, headery, sizey + headery, 5);
                drawDate = xValue - 52;
                dateToPaint = beg;
            } else //blue dash line for end
            if (tmpdate.getYear() == end.getYear() && tmpdate.getMonth() == end.getMonth()) {
                g.setColor(arrayColor[12]);
                //g.drawLine((int)(fgra*i)+(int)(s*end.getDay()),headery,(int)(fgra*i)+(int)(s*end.getDay()),sizey+headery);
                int xValue = (int) (fgra * i) + (int) (s * end.getDay());
                drawVerticalLinedash(g, xValue, headery, sizey + headery, 5);
                drawDate = xValue;
                dateToPaint = end.newAdd(-1);
            }
        }
        //Draw the date in string on calendar
        if (drawDate >= 0) {
            g.setFont(new Font("SansSerif", Font.PLAIN, 9));
            g.setColor(arrayColor[9]);
            g.drawString(dateToPaint.toString(), drawDate, headery + 20);
            drawDate = -1;
        }
        tmpdate.add(getFoot());
    }
}
	/** Draw a normal task */
public void paintATaskChild(Graphics g, int x1, int x2, int y, Task task) {
    int d = y;
    y = y * 20 + 27 - margY;
    if (y < 20 || y > getHeight()) {
        //Not draw if the task is not on the area
        return;
    }
    if ((x1 > getWidth() && x2 > getWidth()) || (x1 < 0 && x2 < 0)) {
        return;
    }
    //Blue rectangle
    Color c = ((GanttTask) task).colorDefined() ? task.getColor() : myUIConfiguration.getTaskColor();
    g.setColor(c);
    g.fillRect(x1, y, (x2 - x1 - 1), 12);
    //Draw nice border
    //if(draw3dBorders)
    {
        if (drag == d - 1) {
            g.setColor(arrayColor[0]);
        } else {
            g.setColor(Color.black);
        }
        g.drawRect(x1, y, x2 - x1 - 1, 12);
    //AT
    //This code print a gray border around the task
    //BTW I've comment it for several reason
    //- It' increase the time of rendering
    //- when printing the char, this border isn't good
    //- for the rendering, it's only beautiful with the default color, but not with another one
    //- finally I found it nicer now :)
    /*g.setColor(arrayColor[7]);
    	g.drawLine(x1 + 1, y + 1, (x2 - 1) - 1, y + 1);
    	g.drawLine(x1 + 1, y + 1, x1 + 1, y + 11);
    	
    	g.setColor(arrayColor[8]);
    	g.drawLine(x1 + 2, y + 11, (x2 - 1) - 2, y + 11);
    	g.drawLine(x2 - 2, y + 2, x2 - 2, y + 11);*/
    }
    //Draw the resource list after the task
    paintResources(x2 + 40, y + 10, task, g);
}
	/** Paint the assigned resources after the task */
public void paintResources(int x, int y, Task task, Graphics g) {
    //Draw the assigned resources name
    //ArrayList users = task.getUsersList();
    ResourceAssignment[] assignments = task.getAssignments();
    if (assignments.length > 0) {
        String resourceList = "";
        for (int i = assignments.length - 1; i >= 0; i--) {
            ProjectResource next = assignments[i].getResource();
            resourceList += next.getName();
            if (i != 0) {
                resourceList += ", ";
            }
        }
        g.setColor(Color.black);
        g.drawString(resourceList, x, y);
    }
}
	/** Draw the arrows for depends */
public void paintDepend(Graphics g) {
    //for paint triangles
    int[] xPoints = new int[3];
    int[] yPoints = new int[3];
    //Set the color to black
    g.setColor(Color.black);
    //Parsing all tasks
    for (Iterator tasks = listOfTask.iterator(); tasks.hasNext(); ) {
        //Get the task
        GanttTask task = (GanttTask) (((DefaultMutableTreeNode) tasks.next()).getUserObject());
        //Only if the task is visible
        if (isVisible(task)) {
            //Get all sucessors for the task
            Vector successors = task.getSuccessorsOld();
            //Parsing the sucessors
            for (Iterator suc = successors.iterator(); suc.hasNext(); ) {
                //Get the relashionship
                GanttTaskRelationship relationship = (GanttTaskRelationship) suc.next();
                //Get the second task
                Task task2 = relationship.getSuccessorTask();
                //Only if the second task is visible
                if (this.isVisible(task2)) {
                    //Get the start index and end index for param values
                    int index1 = this.indexOf(listOfParam, task.getTaskID());
                    int index2 = this.indexOf(listOfParam, task2.getTaskID());
                    //System.out.println(task+"  "+task2+"  "+index1+" "+index2);
                    try {
                        //Y coords
                        int yt1 = ((GanttPaintParam) listOfParam.get(index1)).y;
                        int yt2 = ((GanttPaintParam) listOfParam.get(index2)).y;
                        yt1 = yt1 * 20 + 32 - margY;
                        yt2 = yt2 * 20 + 32 - margY;
                        //Start-Start relashion
                        if (relationship.getRelationshipType() == GanttTaskRelationship.SS) {
                            //Get x coord
                            int x1t1 = ((GanttPaintParam) listOfParam.get(index1)).x1;
                            int x1t2 = ((GanttPaintParam) listOfParam.get(index2)).x1;
                            int xa = (x1t1 < x1t2) ? x1t1 - 7 : x1t2 - 7;
                            //Draw Lines
                            g.drawLine(x1t1, yt1, xa, yt1);
                            g.drawLine(xa, yt1, xa, yt2);
                            g.drawLine(xa, yt2, x1t2, yt2);
                            //Traiangle for task 1
                            /*x1t1--;
						xPoints[0] = x1t1;
              			xPoints[1] = x1t1 - 3;
			            xPoints[2] = x1t1 - 3;
						yPoints[0] = yt1;
						yPoints[1] = yt1 - 4;
						yPoints[2] = yt1 + 4;
						g.fillPolygon(xPoints, yPoints, 3);*/
                            //Traiangle for task 2
                            x1t2--;
                            xPoints[0] = x1t2;
                            xPoints[1] = x1t2 - 3;
                            xPoints[2] = x1t2 - 3;
                            yPoints[0] = yt2;
                            yPoints[1] = yt2 - 4;
                            yPoints[2] = yt2 + 4;
                            g.fillPolygon(xPoints, yPoints, 3);
                        //Finish-Start relashion
                        } else if (relationship.getRelationshipType() == GanttTaskRelationship.FS) {
                            //Get x coord
                            int x2t1 = ((GanttPaintParam) listOfParam.get(index1)).x2;
                            int x1t2 = ((GanttPaintParam) listOfParam.get(index2)).x1;
                            x1t2 += 2;
                            xPoints[0] = x1t2;
                            xPoints[1] = x1t2 + 3;
                            xPoints[2] = x1t2 - 3;
                            if (yt1 > yt2) {
                                yt2 += 7;
                                yPoints[1] = yt2 + 4;
                                yPoints[2] = yt2 + 4;
                            } else {
                                yt2 -= 6;
                                yPoints[1] = yt2 - 4;
                                yPoints[2] = yt2 - 4;
                            }
                            yPoints[0] = yt2;
                            g.fillPolygon(xPoints, yPoints, 3);
                            g.drawLine(x2t1, yt1, x1t2, yt1);
                            g.drawLine(x1t2, yt1, x1t2, yt2);
                        //Finish-Finish relashion
                        } else if (relationship.getRelationshipType() == GanttTaskRelationship.FF) {
                            //Get x coord
                            int x2t1 = ((GanttPaintParam) listOfParam.get(index1)).x2;
                            int x2t2 = ((GanttPaintParam) listOfParam.get(index2)).x2;
                            int xa = (x2t1 > x2t2) ? x2t1 + 7 : x2t2 + 7;
                            //Draw Lines
                            g.drawLine(x2t1, yt1, xa, yt1);
                            g.drawLine(xa, yt1, xa, yt2);
                            g.drawLine(xa, yt2, x2t2, yt2);
                            //Traiangle for task 1
                            /*x2t1++;
						xPoints[0] = x2t1;
              			xPoints[1] = x2t1 + 3;
			            xPoints[2] = x2t1 + 3;
						yPoints[0] = yt1;
						yPoints[1] = yt1 - 4;
						yPoints[2] = yt1 + 4;
						g.fillPolygon(xPoints, yPoints, 3);*/
                            //Traiangle for task 2
                            x2t2++;
                            xPoints[0] = x2t2;
                            xPoints[1] = x2t2 + 3;
                            xPoints[2] = x2t2 + 3;
                            yPoints[0] = yt2;
                            yPoints[1] = yt2 - 4;
                            yPoints[2] = yt2 + 4;
                            g.fillPolygon(xPoints, yPoints, 3);
                        //Start-Finish relashion
                        } else if (relationship.getRelationshipType() == GanttTaskRelationship.SF) {
                            //Get x coord
                            int x1t1 = ((GanttPaintParam) listOfParam.get(index1)).x1;
                            int x2t2 = ((GanttPaintParam) listOfParam.get(index2)).x2;
                            x2t2 -= 3;
                            xPoints[0] = x2t2;
                            xPoints[1] = x2t2 + 3;
                            xPoints[2] = x2t2 - 3;
                            if (yt1 > yt2) {
                                yt2 += 7;
                                yPoints[1] = yt2 + 4;
                                yPoints[2] = yt2 + 4;
                            } else {
                                yt2 -= 6;
                                yPoints[1] = yt2 - 4;
                                yPoints[2] = yt2 - 4;
                            }
                            yPoints[0] = yt2;
                            g.fillPolygon(xPoints, yPoints, 3);
                            g.drawLine(x1t1, yt1, x2t2, yt1);
                            g.drawLine(x2t2, yt1, x2t2, yt2);
                        }
                    } catch (Exception e) {
                    }
                }
            //End of visible Task2			
            }
        //End of parsing the successors
        }
    //End of isVisible Task1
    }
//End of parsing the iterator
}
	//Enf of paintDepend function
/** Paint advance state of the task */
public int paintAdvancement(Graphics g, int x1, int x2, int y, int percent, ShapePaint shape, Color color, boolean justText) {
    //draw the value at the end of task
    if (percent > 0) {
        g.setColor(Color.black);
        g.drawString("[" + percent + "%]", x2 + 8, y * 20 + 36 - margY);
    }
    float fp = (float) (x2 - x1) * (float) percent / 100;
    //Paint the bar
    if (!justText) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(shape);
        g.fillRect(x1 + 1, y * 20 + 27 - margY + 1, ((x2 - x1 - 4) + /*- (int) fp*/
        1), 10);
        //To set a nice effets of gradient
        /*g2.setPaint(new GradientPaint(x1 + 2, y * 20 + 31 - margY + 2,
                                    arrayColor[10],
                                    x2 - 2, y * 20 + 31 - margY + 2,
                                    arrayColor[11]));
      g2.fill(new Rectangle(x1 + 2, y * 20 + 31 - margY, (x2 - x1) - 4, 4));*/
        g.setColor(Color.black);
        g.fillRect(x1 + 2, y * 20 + 31 - margY + 1, (x2 - x1) - 4, 3);
        g2.setPaint(shape);
        g.fillRect(x1 + 1 + (int) fp, y * 20 + 27 - margY + 1, ((x2 - x1 - 4) - (int) fp) + 1, 10);
    }
    return (int) (x1 + fp);
}
	/** Resizes the area to fit the whole project. Height is set according to the number of tasks.
   * Width is not changed, but zooming is set instead
   */
public void fitWholeProject(boolean resize) {
    GanttCalendar minStart = null;
    GanttCalendar maxFinish = null;
    int height = 20;
    for (Iterator tasks = getTree().getAllTasks().iterator(); tasks.hasNext(); ) {
        DefaultMutableTreeNode nextTreeNode = (DefaultMutableTreeNode) tasks.next();
        Task next = (Task) nextTreeNode.getUserObject();
        if ("None".equals(next.toString())) {
            continue;
        }
        if (isVisible(next)) {
            height += 20;
        }
        GanttCalendar start = next.getStart();
        GanttCalendar finish = next.getEnd();
        if (minStart == null) {
            minStart = start;
        }
        if (maxFinish == null) {
            maxFinish = finish;
        }
        if (minStart.compareTo(start) >= 0) {
            minStart = start;
        }
        if (maxFinish.compareTo(finish) <= 0) {
            maxFinish = finish;
        }
    }
    if (minStart == null || maxFinish == null) {
        return;
    }
    minStart = minStart.Clone();
    maxFinish = maxFinish.Clone();
    int projectLengthDays = minStart.diff(maxFinish);
    int projectLengthWeeks = projectLengthDays / 7;
    int zoom = GanttGraphicArea.ONE_MONTH;
    if (projectLengthWeeks < 1) {
        zoom = GanttGraphicArea.ONE_WEEK;
    } else if (projectLengthWeeks < 2) {
        zoom = GanttGraphicArea.TWO_WEEK;
    } else if (projectLengthWeeks < 4) {
        zoom = GanttGraphicArea.ONE_MONTH;
    } else if (projectLengthWeeks < 9) {
        zoom = GanttGraphicArea.TWO_MONTH;
    } else if (projectLengthWeeks < 13) {
        zoom = GanttGraphicArea.THREE_MONTH;
    } else if (projectLengthWeeks < 17) {
        zoom = GanttGraphicArea.FOUR_MONTH;
    } else if (projectLengthWeeks < 26) {
        zoom = GanttGraphicArea.SIX_MONTH;
    } else if (projectLengthWeeks < 52) {
        zoom = GanttGraphicArea.ONE_YEAR;
    } else if (projectLengthWeeks < 104) {
        zoom = GanttGraphicArea.TWO_YEAR;
    } else if (projectLengthWeeks < 156) {
        zoom = GanttGraphicArea.THREE_YEAR;
    }
    if (resize) {
        setSize(getSize().width, height + 40);
    }
    setZoom(zoom);
    setDate(minStart);
    zoomToBegin();
    setScrollBar(0);
}
}
