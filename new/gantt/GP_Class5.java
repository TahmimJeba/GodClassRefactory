public class Class5 {
	private Object YES_NO_OPTION;
	private Object beg;
	private Object YES;
	private Object QUESTION;
	private Object area;
	private Object res;
	private Object change;
	private Object length;

	public Class5(Object YES_NO_OPTION, Object beg, Object YES, Object QUESTION, Object area, Object res, Object change, Object length){
		this.YES_NO_OPTION = YES_NO_OPTION;
		this.beg = beg;
		this.YES = YES;
		this.QUESTION = QUESTION;
		this.area = area;
		this.res = res;
		this.change = change;
		this.length = length;
	}
	/** Delete the currant task */
public void deleteTasks() {
    tabpane.setSelectedIndex(0);
    DefaultMutableTreeNode[] cdmtn = tree.getSelectedNodes();
    if (cdmtn == null || cdmtn.length == 0) {
        statusBar.setFirstText(language.getText("msg21"), 2000);
        return;
    }
    GanttDialogInfo gdi = new GanttDialogInfo(this, GanttDialogInfo.QUESTION, GanttDialogInfo.YES_NO_OPTION, language.getText("msg19"), language.getText("question"));
    gdi.show();
    if (gdi.res == GanttDialogInfo.YES) {
        for (int i = 0; i < cdmtn.length; i++) {
            if (cdmtn[i] != null) {
                Task ttask = (Task) (cdmtn[i].getUserObject());
                DefaultMutableTreeNode father = tree.getFatherNode(ttask);
                tree.removeCurrentNode();
                if (father != null) {
                    GanttTask taskFather = (GanttTask) father.getUserObject();
                    AdjustTaskBoundsAlgorithm alg = getTaskManager().getAlgorithmCollection().getAdjustTaskBoundsAlgorithm();
                    alg.run(taskFather);
                    //	taskFather.refreshDateAndAdvancement(tree);
                    father.setUserObject(taskFather);
                }
            }
        }
        refreshProjectInfos();
        area.repaint();
        getResourcePanel().area.repaint();
        setAskForSave(true);
    }
}
	/** Unlink the relationships of the selected task */
public void unlinkRelationships() {
    tabpane.setSelectedIndex(0);
    DefaultMutableTreeNode[] cdmtn = tree.getSelectedNodes();
    if (cdmtn == null) {
        statusBar.setFirstText(language.getText("msg21"), 2000);
        return;
    }
    for (int i = 0; i < cdmtn.length; i++) {
        if (cdmtn[i] != null && !cdmtn[i].isRoot()) {
            GanttTask t = (GanttTask) (cdmtn[i].getUserObject());
            t.unlink();
        }
    }
    area.repaint();
    setAskForSave(true);
}
	/** Link the selected Tasks */
public void linkRelationships() {
    tabpane.setSelectedIndex(0);
    DefaultMutableTreeNode[] cdmtn = tree.getSelectedNodes();
    if (cdmtn == null) {
        statusBar.setFirstText(language.getText("msg21"), 2000);
        return;
    }
    if (cdmtn.length < 2) {
        statusBar.setFirstText(language.getText("msg22"), 2000);
        return;
    }
    for (int i = 0; i < cdmtn.length - 1; i++) {
        if (cdmtn[i] != null && cdmtn[i + 1] != null) {
            GanttTask firstTask = (GanttTask) (cdmtn[i].getUserObject());
            ;
            GanttTask secondTask = (GanttTask) (cdmtn[i + 1].getUserObject());
            try {
                getTaskManager().getDependencyCollection().createDependency(secondTask, firstTask);
            } catch (TaskDependencyException e1) {
            //e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
    area.repaint();
    setAskForSave(true);
}
	/** Create the button on toolbar */
public void addButtons(JToolBar toolBar) {
    //toolBar.addSeparator(new Dimension(20,0));
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bNew = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/new_" + options.getIconSize() + ".gif")));
    bNew.addActionListener(this);
    toolBar.add(bNew);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bOpen = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/open_" + options.getIconSize() + ".gif")));
    bOpen.addActionListener(this);
    toolBar.add(bOpen);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bSave = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/save_" + options.getIconSize() + ".gif")));
    bSave.setEnabled(false);
    bSave.addActionListener(this);
    toolBar.add(bSave);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bSaveAs = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/saveas_" + options.getIconSize() + ".gif")));
    bSaveAs.addActionListener(this);
    toolBar.add(bSaveAs);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bImport = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/import_" + options.getIconSize() + ".gif")));
    bImport.addActionListener(this);
    toolBar.add(bImport);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bExport = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/export_" + options.getIconSize() + ".gif")));
    bExport.addActionListener(this);
    toolBar.add(bExport);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bPrint = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/print_" + options.getIconSize() + ".gif")));
    bPrint.addActionListener(this);
    toolBar.add(bPrint);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    toolBar.addSeparator(new Dimension(20, 0));
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //bNewTask = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/insert_"+options.getIconSize()+".gif")));
    myNewArtefactAction = new NewArtefactAction(new NewArtefactAction.ActiveActionProvider() {

        public AbstractAction getActiveAction() {
            return tabpane.getSelectedIndex() == 0 ? (AbstractAction) myNewTaskAction : (AbstractAction) myNewHumanAction;
        }
    }, options.getIconSize());
    bNewTask = new TestGanttRolloverButton(myNewArtefactAction);
    //		bNewTask.addActionListener(new ActionListener() {
    //			public void actionPerformed(ActionEvent e) {
    //				if (tabpane.getSelectedIndex() == 0) {//Gantt Chart
    //					newTask();
    //				} else if (tabpane.getSelectedIndex() == 1) { //Resource chart
    //					HumanResource people = new HumanResource();
    //					GanttDialogPerson dp = new GanttDialogPerson(
    //							GanttProject.this, getLanguage(), people);
    //					dp.show();
    //					if (dp.result()) {
    //						getHumanResourceManager().add(people);
    //						setAskForSave(true);
    //					}
    //				}
    //			}
    //		});
    toolBar.add(bNewTask);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bDelete = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/delete_" + options.getIconSize() + ".gif")));
    bDelete.addActionListener(new ActionListener() {

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
    });
    toolBar.add(bDelete);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bProperties = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/properties_" + options.getIconSize() + ".gif")));
    bProperties.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            if (tabpane.getSelectedIndex() == 0) {
                //Gantt Chart
                propertiesTask();
            } else if (tabpane.getSelectedIndex() == 1) {
                //Resource chart
                getResourcePanel().propertiesHuman(GanttProject.this);
            }
        }
    });
    toolBar.add(bProperties);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bUnlink = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/unlink_" + options.getIconSize() + ".gif")));
    bUnlink.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            if (tabpane.getSelectedIndex() == 0) {
                //Gantt Chart
                unlinkRelationships();
            }
        }
    });
    toolBar.add(bUnlink);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bLink = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/link_" + options.getIconSize() + ".gif")));
    bLink.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            if (tabpane.getSelectedIndex() == 0) {
                //Gantt Chart
                linkRelationships();
            }
        }
    });
    toolBar.add(bLink);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////		
    bInd = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/indent_" + options.getIconSize() + ".gif")));
    bInd.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            if (tabpane.getSelectedIndex() == 0) {
                //Gantt Chart
                //tree.indentCurrentNode();
                tree.indentCurrentNodes();
                setAskForSave(true);
            }
        }
    });
    toolBar.add(bInd);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bUnind = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/unindent_" + options.getIconSize() + ".gif")));
    bUnind.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            if (tabpane.getSelectedIndex() == 0) {
                //Gantt Chart
                //tree.dedentCurrentNode();
                tree.dedentCurrentNodes();
                setAskForSave(true);
            }
        }
    });
    toolBar.add(bUnind);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bUp = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/up_" + options.getIconSize() + ".gif")));
    bUp.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            upDatas();
        }
    });
    toolBar.add(bUp);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bDown = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/down_" + options.getIconSize() + ".gif")));
    bDown.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            downDatas();
        }
    });
    toolBar.add(bDown);
    toolBar.addSeparator(new Dimension(20, 0));
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bPrev = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/prev_" + options.getIconSize() + ".gif")));
    bPrev.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            area.changeDate(false);
            area.repaint();
            getResourcePanel().area.changeDate(false);
            getResourcePanel().area.repaint();
        }
    });
    toolBar.add(bPrev);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bNext = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/next_" + options.getIconSize() + ".gif")));
    bNext.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            area.changeDate(true);
            area.repaint();
            getResourcePanel().area.changeDate(true);
            getResourcePanel().area.repaint();
        }
    });
    toolBar.add(bNext);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bZoomOut = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/zoomm_" + options.getIconSize() + ".gif")));
    bZoomOut.addActionListener(new ActionListener() {

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
    });
    toolBar.add(bZoomOut);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    bZoomIn = new TestGanttRolloverButton(new ImageIcon(getClass().getResource("/icons/zoomp_" + options.getIconSize() + ".gif")));
    bZoomIn.addActionListener(new ActionListener() {

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
    });
    toolBar.add(bZoomIn);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
		 * bZoomFit = new JButton (new
		 * ImageIcon(getClass().getResource("/icons/zoomf.gif")));
		 * bZoomFit.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { area.fitWholeProject(false);
		 * area.repaint(); } }); toolBar.add(bZoomFit);
		 */
    applyButtonOptions();
}
	/** Create a new task */
public Task newTask() {
    tabpane.setSelectedIndex(0);
    GanttTask current = tree.getSelectedTask();
    GanttCalendar cal = new GanttCalendar();
    if (tree.hasTasks())
        cal = new GanttCalendar(area.beg);
    DefaultMutableTreeNode node = null;
    GanttLanguage lang = GanttLanguage.getInstance();
    //language.getText("newTask");
    String nameOfTask = options.getTaskNamePrefix();
    if (current != null) {
        current.setMilestone(false);
        node = tree.getSelectedNode();
        cal = current.getStart();
        nameOfTask = current.toString();
    }
    GanttTask task = getTaskManager().createTask();
    task.setStart(cal);
    task.setLength(1);
    //create a new task in the tab
    getTaskManager().registerTask(task);
    // paneneed to register it
    task.setName(nameOfTask + "_" + task.getTaskID());
    task.setColor(area.getTaskColor());
    if (current != null) {
        if (current.colorDefined()) {
            task.setColor(current.getColor());
        }
        if (current.shapeDefined())
            task.setShape(current.getShape());
    }
    DefaultMutableTreeNode taskNode = tree.addObject(task, node);
    AdjustTaskBoundsAlgorithm alg = getTaskManager().getAlgorithmCollection().getAdjustTaskBoundsAlgorithm();
    alg.run(task);
    RecalculateTaskCompletionPercentageAlgorithm alg2 = getTaskManager().getAlgorithmCollection().getRecalculateTaskCompletionPercentageAlgorithm();
    alg2.run(task);
    //refresh the differents tasks
    if (current != null) {
        tree.refreshAllChild(nameOfTask);
    //      DefaultMutableTreeNode father = tree.getSelectedNode();
    //      GanttTask taskFather = null;
    //For refresh all the parent task
    //      while (tree.getNode(task.getTaskID()).isRoot() == false) {
    //        father = tree.getFatherNode(task);
    //        tree.refreshAllChild(father.toString());
    //        taskFather = (GanttTask) father.getUserObject();
    //        taskFather.refreshDateAndAdvancement(tree);
    //        father.setUserObject(taskFather);
    //        task = taskFather;
    //      }
    }
    area.repaint();
    setAskForSave(true);
    statusBar.setFirstText(language.getText("createNewTask"), 1000);
    if (options.getAutomatic()) {
        propertiesTask(taskNode);
    }
    return task;
}
	/** Edit task parameters */
public void propertiesTask(DefaultMutableTreeNode node) {
    if (node == null || node.isRoot()) {
        statusBar.setFirstText(language.getText("msg21"), 2000);
        return;
    } else {
        statusBar.setFirstText(language.getText("editingParameters"), 2000);
        GanttTask t = (GanttTask) (node.getUserObject());
        GanttDialogProperties pd = new GanttDialogProperties(this, tree, managerHash, t, area);
        pd.show();
        if (pd.change) {
            setAskForSave(true);
        }
    }
}
	/** Edit task parameters */
public void propertiesTask(DefaultMutableTreeNode node) {
    if (node == null || node.isRoot()) {
        statusBar.setFirstText(language.getText("msg21"), 2000);
        return;
    } else {
        statusBar.setFirstText(language.getText("editingParameters"), 2000);
        GanttTask t = (GanttTask) (node.getUserObject());
        GanttDialogProperties pd = new GanttDialogProperties(this, tree, managerHash, t, area);
        pd.show();
        if (pd.change) {
            setAskForSave(true);
        }
    }
}
}
