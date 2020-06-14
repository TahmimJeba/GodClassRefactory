public class Class1 {
	private Object beg;
	private Object YEAR;
	private Object white;
	private Object end;
	private Object PLAIN;
	private Object black;
	private Object TYPE_INT_RGB;
	private Object name;
	private Object width;
	private Object border3d;

	public Class1(Object beg, Object YEAR, Object white, Object end, Object PLAIN, Object black, Object TYPE_INT_RGB, Object name, Object width, Object border3d){
		this.beg = beg;
		this.YEAR = YEAR;
		this.white = white;
		this.end = end;
		this.PLAIN = PLAIN;
		this.black = black;
		this.TYPE_INT_RGB = TYPE_INT_RGB;
		this.name = name;
		this.width = width;
		this.border3d = border3d;
	}
	/** draw the panel */
public void paintComponent(Graphics g) {
    //Super paint component!!!!!!!!!!
    super.paintComponent(g);
    //Move if its in printing (for margin)
    /*if(printRendering)
            g.translate((int)upperLeft.getX(),(int)upperLeft.getY());*/
    //Vertical bars
    paintCalendar1(g);
    //The tasks
    paintLoads(g);
    //The depends
    //        if(drawdepend) paintDepend(g);
    //The part at top
    paintCalendar2(g);
    if (drawVersion) {
        drawGPVersion(g);
    }
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
            if (!day)
                res = 12;
            else
                res = (date.getYear() % 4 == 0) ? 366 : 365;
            break;
        case TWO_YEAR:
            if (!day)
                res = 12 * 2;
            else {
                cal = date.Clone();
                res = 0;
                for (int i = 0; i < 2; i++) {
                    if (cal.getYear() % 4 == 0)
                        res += 366;
                    else
                        res += 365;
                    cal.go(Calendar.YEAR, 1);
                }
            }
            break;
        case THREE_YEAR:
            if (!day)
                res = 12 * 3;
            else {
                cal = date.Clone();
                res = 0;
                for (int i = 0; i < 3; i++) {
                    if (cal.getYear() % 4 == 0)
                        res += 366;
                    else
                        res += 365;
                    cal.go(Calendar.YEAR, 1);
                }
            }
            break;
    }
    return res;
}
	/** Draw the legend of the calendar */
public void paintCalendar2(Graphics g) {
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
    /*if(draw3dBorders)
        {
        	g.setColor(arrayColor[5]);        
        	g.drawLine(1,headery/2-1,sizex-2,headery/2-1);
        	g.drawLine(0,headery-2,sizex-2,headery-2);
        	g.setColor(Color.white);
        	g.drawLine(1,1,sizex-2,1);
        	g.drawLine(0,headery/2+1,sizex-2,headery/2+1);
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
            g.drawString(language.getText("week") + (date.getWeek()) + " - " + date.getdayMonth() + "  " + date.getYear(), 2, headery / 2 - 5);
            g.drawString(language.getText("week") + (date.getWeek() + 1) + " - " + date.getdayMonth() + "  " + date.getYear(), (int) (fgra * 7) + 2, headery / 2 - 5);
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
    if (zoomValue == ONE_WEEK || zoomValue == TWO_WEEK)
        tmpdate = date.Clone();
    else
        tmpdate = new GanttCalendar(date.getYear(), date.getMonth(), 1);
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
            //			break;
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
    beg = date.Clone();
    beg.add(getGranit(true));
    end = date.Clone();
    end.add(-1);
    GanttCalendar tmpdate;
    if (zoomValue == ONE_WEEK || zoomValue == TWO_WEEK)
        tmpdate = date.Clone();
    else
        tmpdate = new GanttCalendar(date.getYear(), date.getMonth(), 1);
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
                if (draw3dBorders)
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
        } else {
            GanttCalendar today = new GanttCalendar();
            float s = fgra / (float) tmpdate.getNumberOfDay();
            if (tmpdate.getYear() == today.getYear() && tmpdate.getMonth() == today.getMonth()) {
                //Blue rectange on today
                g.setColor(arrayColor[2]);
                g.fillRect((int) (fgra * i) + (int) (s * today.getDay()), headery, ((s < 2) ? 2 : (int) s), sizey);
                g.setColor(arrayColor[2]);
                if (draw3dBorders)
                    g.drawLine((int) (fgra * i) + (int) (s * today.getDay()), headery, (int) (fgra * i) + (int) (s * today.getDay()), sizey + headery);
            }
        }
        tmpdate.add(getFoot());
    }
    if (zoomValue == ONE_WEEK || zoomValue == TWO_WEEK)
        tmpdate = date.Clone();
    else
        tmpdate = new GanttCalendar(date.getYear(), date.getMonth(), 1);
    beg = appli.getArea().beg;
    end = appli.getArea().end;
    //Draw Miscallenous bars
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
                drawVerticalLinedash(g, (int) (fgra * i) + (int) (s * beg.getDay()), headery, sizey + headery, 5);
                drawDate = (int) (fgra * i) + (int) (s * beg.getDay()) - 52;
                dateToPaint = beg;
            } else //blue dash line for end
            if (tmpdate.getYear() == end.getYear() && tmpdate.getMonth() == end.getMonth()) {
                g.setColor(arrayColor[12]);
                //g.drawLine((int)(fgra*i)+(int)(s*end.getDay()),headery,(int)(fgra*i)+(int)(s*end.getDay()),sizey+headery);
                drawVerticalLinedash(g, (int) (fgra * i) + (int) (s * end.getDay()), headery, sizey + headery, 5);
                drawDate = (int) (fgra * i) + (int) (s * end.getDay()) + 3;
                dateToPaint = end.newAdd(-1);
            }
        }
        //Draw the date in string on calendar
        if (drawDate >= 0) {
            g.setFont(new Font("SansSerif", Font.PLAIN, 9));
            g.setColor(arrayColor[9]);
            g.drawString(dateToPaint.toString(), drawDate, headery + 10);
            drawDate = -1;
        }
        tmpdate.add(getFoot());
    }
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
	/** Paint all tasks  */
public void paintLoads(Graphics g) {
    int sizex = getWidth();
    int sizey = getHeight();
    int headery = 45;
    //pixels per day
    float fgra = (float) sizex / (float) getGranit(true);
    g.setFont(new Font("SansSerif", Font.PLAIN, 9));
    //Get all task
    ArrayList listOfTask = tree.getAllTasks();
    if (listOfTask.size() <= 1)
        return;
    calculateLoad(listOfTask);
    //Probably optimised on next release
    listOfParam.clear();
    GanttCalendar firstStart = new GanttCalendar(), lastEnd = new GanttCalendar();
    //lastEnd=new GanttCalendar();
    int duration = 0;
    firstStart = ((Task) ((DefaultMutableTreeNode) listOfTask.get(1)).getUserObject()).getStart();
    lastEnd = firstStart;
    //Calcul all parameters
    for (int i = 2; i < listOfTask.size(); i++) {
        Task task = (Task) ((DefaultMutableTreeNode) listOfTask.get(i)).getUserObject();
        if (firstStart.compareTo(task.getStart()) == 1)
            firstStart = task.getStart();
        if (lastEnd.compareTo(task.getEnd()) == -1)
            lastEnd = task.getEnd();
    }
    duration = firstStart.diff(lastEnd);
    int x1 = -10, x2 = sizex + 10;
    int e1;
    int fois;
    int type = 2;
    //difference between the start date of the task and the start of the display area
    e1 = date.diff(firstStart);
    //System.out.println ("date: "+date);
    //Calcul start and end pixel of each task
    float fx1, fx2;
    fx1 = 0;
    //                fx1 =1;
    fx2 = fx1 + (float) duration * fgra;
    x1 = (int) fx1;
    x2 = (int) fx2;
    int percent = 0, intLoad = -1;
    x2 = (int) ((float) getGranit(true) * fgra);
    x2 = 0;
    // hier brauche ich ein Mapping zwischen Usern und ihrer y Position        
    HumanResourceManager resMan = (HumanResourceManager) appli.getHumanResourceManager();
    ArrayList users = resMan.getResources();
    /** State for rendering each cell of the chart*/
    boolean[] state = new boolean[users.size() > 0 ? users.size() : 1];
    boolean[] oldState = new boolean[users.size() > 0 ? users.size() : 1];
    for (int i = 0; i < users.size(); i++) {
        state[i] = false;
        oldState[i] = false;
    }
    for (int i = 0; i < getGranit(true); i++) {
        Hashtable load = (Hashtable) loads.get(i);
        if (load != null) {
            for (int y = 0; y < users.size(); y++) {
                oldState[y] = state[y];
                String key = ((HumanResource) users.get(y)).toString();
                //System.out.println(key);
                if (load.get(key) != null) {
                    intLoad = ((Integer) load.get(key)).intValue();
                    state[y] = true;
                } else {
                    intLoad = -1;
                    state[y] = false;
                }
                if (intLoad > 100)
                    paintAResourceLoad(g, x1 + (int) (i * fgra), x2 + (int) ((i + 1) * fgra), y + 1, "xx", appli.getUIConfiguration().getResourceOverloadColor(), state[y], oldState[y]);
                else if (intLoad > 0) {
                    paintAResourceLoad(g, x1 + (int) (i * fgra), x2 + (int) ((i + 1) * fgra), y + 1, "xx", appli.getUIConfiguration().getResourceColor(), state[y], oldState[y]);
                }
            }
        }
    }
    type = 2;
}
	/** Draw a normal task */
public void paintAResourceLoad(Graphics g, int x1, int x2, int y, String taskName, Color color, boolean state, boolean oldState) {
    x2++;
    int d = y;
    y = y * 20 + 40 - margY;
    //Not draw if the task is not on the area
    if (y < 20 || y > getHeight())
        return;
    if ((x1 > getWidth() && x2 > getWidth()) || (x1 < 0 && x2 < 0))
        return;
    boolean first_cell = (state && !oldState);
    //boolean end_cell = (!state && oldState);
    int xd = x1;
    if (!first_cell)
        xd -= 2;
    //Blue rectangle
    g.setColor(color);
    g.fillRect(xd, y, (x2 - x1) + 2, 12);
    if (draw3dBorders) {
        g.setColor(Color.black);
        g.drawLine(xd, y, x2 - 1, y);
        g.drawLine(xd, y + 12, x2 - 1, y + 12);
        //draw the begin of the nice border
        if (first_cell) {
            g.setColor(Color.black);
            g.drawLine(x1, y, x1, y + 12);
            g.setColor(arrayColor[7]);
            g.drawLine(x1 + 1, y + 1, x1 + 1, y + 11);
        }
        //draw the end of the bar (not great algorithm because end is design each timt, but it's a first version ....:(
        g.setColor(Color.black);
        g.drawLine(x2, y, x2, y + 12);
        g.setColor(arrayColor[8]);
        g.drawLine(x2 - 1, y + 2, x2 - 1, y + 11);
    }
    if (drawName) {
        g.setColor(Color.black);
        g.drawString(taskName, x2 + 40, y + 10);
    }
}
	/** Return an image with the gantt chart*/
public BufferedImage getChart(GanttExportSettings settings) {
    appli.getArea().calcProjectBegAndEnd();
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
    draw3dBorders = settings.border3d;
    ResourceManager resMan = (HumanResourceManager) appli.getHumanResourceManager();
    ArrayList users = resMan.getResources();
    //Size of the content table for the resources list
    int sizeTOC = 0;
    BufferedImage tmpImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
    FontMetrics fmetric = tmpImage.getGraphics().getFontMetrics(new Font("SansSerif", Font.PLAIN, 10));
    for (Iterator user = users.iterator(); user.hasNext(); ) {
        String nameOfRes = ((HumanResource) user.next()).toString();
        int nbchar = fmetric.stringWidth(nameOfRes);
        if (nbchar > sizeTOC)
            sizeTOC = nbchar;
    }
    sizeTOC += 20;
    //If there is only the root task
    setSize(width, users.size() * 20 + 80);
    int calculateWidth = width * rendering;
    if (settings.name) {
        rendering++;
        calculateWidth += sizeTOC;
    }
    BufferedImage image = new BufferedImage(calculateWidth, getHeight(), BufferedImage.TYPE_INT_RGB);
    printRendering = true;
    start = new GanttCalendar(date);
    date = new GanttCalendar(beg);
    zoomToBegin();
    if (zoomValue == ONE_WEEK || zoomValue == TWO_WEEK)
        changeDate(false);
    int transx = 0, transx2;
    for (int i = 0; i < rendering; i++) {
        BufferedImage image2 = new BufferedImage(width, getHeight(), BufferedImage.TYPE_INT_RGB);
        if (i == 0 && settings.name) {
            rowCount = 0;
            image2 = null;
            image2 = new BufferedImage(sizeTOC, getHeight(), BufferedImage.TYPE_INT_RGB);
            setSize(sizeTOC, getHeight());
            printResources(image2.getGraphics());
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
    printRendering = false;
    date = new GanttCalendar(start);
    zoomToBegin();
    margY = oldMargY;
    //drawVersion = false;
    draw3dBorders = true;
    setSize(getSize().width, oldHeight);
    repaint();
    return image;
}
	/** Print the list of tasks */
private void printResources(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setColor(Color.black);
    g.setFont(new Font("SansSerif", Font.PLAIN, 10));
    ResourceManager resMan = (HumanResourceManager) appli.getHumanResourceManager();
    ArrayList users = resMan.getResources();
    int y = 55;
    for (Iterator user = users.iterator(); user.hasNext(); ) {
        String nameOfRes = ((HumanResource) user.next()).toString();
        if (rowCount % 2 == 1) {
            g.setColor(new Color((float) 0.933, (float) 0.933, (float) 0.933));
            g.fillRect(0, y, getWidth() - 10, 20);
        }
        g.setColor(Color.black);
        g.drawRect(0, y, getWidth() - 10, 20);
        g.drawString(nameOfRes, 5, y + 13);
        g.setColor(arrayColor[5]);
        if (draw3dBorders)
            g.drawLine(1, y + 19, getWidth() - 11, y + 19);
        y += 20;
        rowCount++;
    }
}
}
