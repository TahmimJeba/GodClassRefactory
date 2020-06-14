public class Class0 {
	private Object MONTH;
	private Object language;
	private Object out;
	private Object WEEK_OF_YEAR;
	private Object YEAR;

	public Class0(Object MONTH, Object language, Object out, Object WEEK_OF_YEAR, Object YEAR){
		this.MONTH = MONTH;
		this.language = language;
		this.out = out;
		this.WEEK_OF_YEAR = WEEK_OF_YEAR;
		this.YEAR = YEAR;
	}
	/** Change the zoom value */
public void setZoom(int z) {
    zoomValue = z;
}
	/** The size of the panel. */
public Dimension getPreferredSize() {
    return new Dimension(465, 600);
}
	/** Method to change the date */
public void changeDate(boolean next) {
    int f = 1;
    if (!next)
        f = -1;
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
public int indexOf(ArrayList listOfParam, String coef) {
    for (int i = 0; i < listOfParam.size(); i++) if (coef == listOfParam.get(i).toString())
        return i;
    return -1;
}
	/** Return the value of the JTree's Scrollbar */
public int getScrollBar() {
    return margY;
}
	/** Add a zoom*/
public void zoomMore() {
    if (zoomValue == 5)
        olddate = date.Clone();
    zoomValue++;
}
	/**Less a zoom*/
public void zoomLess() {
    if (zoomValue == 6 && date.getYear() == olddate.getYear())
        date = olddate.Clone();
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
	/** Change language */
public void setLanguage(GanttLanguage language) {
    this.language = language;
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
    BufferedImage image = getChart(settings);
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
public void export(File file, String format, GanttExportSettings settings) {
    BufferedImage image = getChart(settings);
    try {
        if (!ImageIO.write(image, format, file)) {
            System.out.println("Impossible de sauvegarder dans ce format");
        }
    } catch (Exception e) {
        System.out.println(e);
    }
}
}