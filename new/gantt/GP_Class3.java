public class Class3 {
	private Object area;
	private Object res;
	private Object YES_NO_OPTION;
	private Object YES;
	private Object length;
	private Object QUESTION;

	public Class3(Object area, Object res, Object YES_NO_OPTION, Object YES, Object length, Object QUESTION){
		this.area = area;
		this.res = res;
		this.YES_NO_OPTION = YES_NO_OPTION;
		this.YES = YES;
		this.length = length;
		this.QUESTION = QUESTION;
	}
	public void actionPerformed(ActionEvent e) {
    if (tabpane.getSelectedIndex() == 0) {
        //Gantt Chart
        propertiesTask();
    } else if (tabpane.getSelectedIndex() == 1) {
        //Resource chart
        getResourcePanel().propertiesHuman(GanttProject.this);
    }
}
	public void actionPerformed(ActionEvent e) {
    if (tabpane.getSelectedIndex() == 0) {
        //Gantt Chart
        unlinkRelationships();
    }
}
	public void actionPerformed(ActionEvent e) {
    if (tabpane.getSelectedIndex() == 0) {
        //Gantt Chart
        linkRelationships();
    }
}
	public void actionPerformed(ActionEvent e) {
    if (tabpane.getSelectedIndex() == 0) {
        //Gantt Chart
        //tree.indentCurrentNode();
        tree.indentCurrentNodes();
        setAskForSave(true);
    }
}
	public void actionPerformed(ActionEvent e) {
    if (tabpane.getSelectedIndex() == 0) {
        //Gantt Chart
        //tree.dedentCurrentNode();
        tree.dedentCurrentNodes();
        setAskForSave(true);
    }
}
	public void actionPerformed(ActionEvent e) {
    upDatas();
}
	public void actionPerformed(ActionEvent e) {
    downDatas();
}
	public void actionPerformed(ActionEvent e) {
    area.changeDate(false);
    area.repaint();
    getResourcePanel().area.changeDate(false);
    getResourcePanel().area.repaint();
}
	public void actionPerformed(ActionEvent e) {
    area.changeDate(true);
    area.repaint();
    getResourcePanel().area.changeDate(true);
    getResourcePanel().area.repaint();
}
	public void actionPerformed(ActionEvent e) {
    if (area.getZoom() < 9) {
        area.zoomMore();
        getResourcePanel().area.zoomMore();
    }
    area.zoomToBegin();
    getResourcePanel().area.zoomToBegin();
    area.repaint();
    getResourcePanel().area.repaint();
    bZoomIn.setEnabled(true);
    bZoomOut.setEnabled(true);
    if (area.getZoom() == 9) {
        bZoomOut.setEnabled(false);
    }
}
	public void actionPerformed(ActionEvent e) {
    if (area.getZoom() > 0) {
        area.zoomLess();
        getResourcePanel().area.zoomLess();
    }
    area.zoomToBegin();
    getResourcePanel().area.zoomToBegin();
    area.repaint();
    getResourcePanel().area.repaint();
    bZoomIn.setEnabled(true);
    bZoomOut.setEnabled(true);
    if (area.getZoom() == 0) {
        bZoomIn.setEnabled(false);
    }
}
	/** Function to change language of the project */
public void changeLanguage() {
    applyComponentOrientation(language.getComponentOrientation());
    changeLanguageOfMenu();
    area.repaint();
    getResourcePanel().area.repaint();
    getResourcePanel().refresh(language);
    applyComponentOrientation(language.getComponentOrientation());
}
	public void actionPerformed(ActionEvent e) {
    if (tabpane.getSelectedIndex() == 0) {
        //Gantt Chart
        //deleteTask();
        deleteTasks();
    } else if (tabpane.getSelectedIndex() == 1) {
        //Resource chart
        ProjectResource[] context = getResourcePanel().getContext().getResources();
        if (context.length > 0) {
            GanttDialogInfo gdi = new GanttDialogInfo(GanttProject.this, GanttDialogInfo.QUESTION, GanttDialogInfo.YES_NO_OPTION, getLanguage().getText("msg6") + getDisplayName(context) + "??", getLanguage().getText("question"));
            gdi.show();
            if (gdi.res == GanttDialogInfo.YES) {
                for (int i = 0; i < context.length; i++) {
                    getHumanResourceManager().remove(context[i]);
                    refreshProjectInfos();
                }
            }
        }
    }
}
	/** Preview the project before print */
public void previewPrint() {
    GanttPreviewPrint preview = null;
    if (tabpane.getSelectedIndex() == 0) {
        //Gantt Chart
        preview = new GanttPreviewPrint(this, area.getChart(new GanttExportSettings()));
    } else if (tabpane.getSelectedIndex() == 1) {
        //Resources Chart
        preview = new GanttPreviewPrint(this, getResourcePanel().area.getChart(new GanttExportSettings()));
    }
    if (preview != null)
        preview.setVisible(true);
}
}