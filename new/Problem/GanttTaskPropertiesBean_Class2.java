public class Class2 {
	private Object YES_OPTION;
	private Object taskDefaultColor;
	private Object ERROR;
	private Object colorChooser;

	public Class2(Object YES_OPTION, Object taskDefaultColor, Object ERROR, Object colorChooser){
		this.YES_OPTION = YES_OPTION;
		this.taskDefaultColor = taskDefaultColor;
		this.ERROR = ERROR;
		this.colorChooser = colorChooser;
	}
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
	public void actionPerformed(ActionEvent e) {
    colorButton.setBackground(GanttDialogProperties.colorChooser.getColor());
}
	public void actionPerformed(ActionEvent e) {
// nothing to do for "Cancel"
}
	public void actionPerformed(ActionEvent e) {
    colorButton.setBackground(GanttGraphicArea.taskDefaultColor);
}
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
	public void actionPerformed(ActionEvent evt) {
    noteAreaNotes.append("\n" + GanttCalendar.getDateAndTime() + "\n");
}
}