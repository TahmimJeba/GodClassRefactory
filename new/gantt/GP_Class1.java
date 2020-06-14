public class Class1 {
	private Object _sProjectName;

	public Class1(Object _sProjectName){
		this._sProjectName = _sProjectName;
	}
	/////////////////////////////////////////////////////////
// IGanttProject implementation
public String getProjectName() {
    return prjInfos._sProjectName;
}
	/////////////////////////////////////////////////////////////////
// ResourceView implementation
public void resourceAdded(ResourceEvent event) {
    tabpane.setSelectedIndex(1);
    getStatusBar().setFirstText(GanttProject.correctLabel(GanttLanguage.getInstance().getText("newHuman")), 2000);
    setAskForSave(true);
    refreshProjectInfos();
}
}
