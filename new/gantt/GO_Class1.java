public class Class1 {
	private Object bExportTaskDuration;
	private Object TAIWAN;
	private Object CHINA;
	private Object ITALY;
	private Object bExportTaskName;
	private Object bExportTaskResources;
	private Object sSeparatedChar;
	private Object FRANCE;
	private Object bExportTaskEndDate;
	private Object bExportTaskWebLink;
	private Object bExportResourceName;
	private Object bFixedSize;
	private Object bExportTaskStartDate;
	private Object GERMANY;
	private Object bExportTaskNotes;
	private Object bExportTaskPercent;
	private Object bExportResourceRole;
	private Object bExportResourceMail;
	private Object bExportResourcePhone;
	private Object bExportResourceID;
	private Object US;
	private Object bExportTaskID;
	private Object sSeparatedTextChar;

	public Class1(Object bExportTaskDuration, Object TAIWAN, Object CHINA, Object ITALY, Object bExportTaskName, Object bExportTaskResources, Object sSeparatedChar, Object FRANCE, Object bExportTaskEndDate, Object bExportTaskWebLink, Object bExportResourceName, Object bFixedSize, Object bExportTaskStartDate, Object GERMANY, Object bExportTaskNotes, Object bExportTaskPercent, Object bExportResourceRole, Object bExportResourceMail, Object bExportResourcePhone, Object bExportResourceID, Object US, Object bExportTaskID, Object sSeparatedTextChar){
		this.bExportTaskDuration = bExportTaskDuration;
		this.TAIWAN = TAIWAN;
		this.CHINA = CHINA;
		this.ITALY = ITALY;
		this.bExportTaskName = bExportTaskName;
		this.bExportTaskResources = bExportTaskResources;
		this.sSeparatedChar = sSeparatedChar;
		this.FRANCE = FRANCE;
		this.bExportTaskEndDate = bExportTaskEndDate;
		this.bExportTaskWebLink = bExportTaskWebLink;
		this.bExportResourceName = bExportResourceName;
		this.bFixedSize = bFixedSize;
		this.bExportTaskStartDate = bExportTaskStartDate;
		this.GERMANY = GERMANY;
		this.bExportTaskNotes = bExportTaskNotes;
		this.bExportTaskPercent = bExportTaskPercent;
		this.bExportResourceRole = bExportResourceRole;
		this.bExportResourceMail = bExportResourceMail;
		this.bExportResourcePhone = bExportResourcePhone;
		this.bExportResourceID = bExportResourceID;
		this.US = US;
		this.bExportTaskID = bExportTaskID;
		this.sSeparatedTextChar = sSeparatedTextChar;
	}
	/** set a new default tasks color. */
public void setDefaultTaskColor(Color color) {
    getUIConfiguration().setTaskColor(color);
}
	/** set a new default resources color. */
public void setResourceColor(Color color) {
    getUIConfiguration().setResourceColor(color);
}
	/** set a new resources overload tasks color. */
public void setResourceOverloadColor(Color color) {
    getUIConfiguration().setResourceOverloadColor(color);
}
	public void startElement(String namespaceURI, // simple name
String sName, // qualified name
String qName, Attributes attrs) throws SAXException {
    int r = 0, g = 0, b = 0;
    if (attrs != null) {
        for (int i = 0; i < attrs.getLength(); i++) {
            // Attr name
            String aName = attrs.getQName(i);
            if (qName.equals("language")) {
                if (aName == "selection") {
                    if (attrs.getValue(i).equals("English") || attrs.getValue(i).equals("en")) {
                        language.setLocale(Locale.US);
                    } else if (attrs.getValue(i).equals("Français") || attrs.getValue(i).equals("fr")) {
                        language.setLocale(Locale.FRANCE);
                    } else if (attrs.getValue(i).equals("Español") || attrs.getValue(i).equals("es")) {
                        language.setLocale(new Locale("es", "ES"));
                    } else if (attrs.getValue(i).equals("Portugues") || attrs.getValue(i).equals("pt")) {
                        language.setLocale(new Locale("pt", "PT"));
                    } else if (attrs.getValue(i).equals("Português do Brasil") || attrs.getValue(i).equals("pt_BR")) {
                        language.setLocale(new Locale("pt", "BR"));
                    } else if (attrs.getValue(i).equals("Deutsch") || attrs.getValue(i).equals("de")) {
                        language.setLocale(Locale.GERMANY);
                    } else if (attrs.getValue(i).equals("Norsk") || attrs.getValue(i).equals("no")) {
                        language.setLocale(new Locale("no", "NO"));
                    } else if (attrs.getValue(i).equals("Italiano") || attrs.getValue(i).equals("it")) {
                        language.setLocale(Locale.ITALY);
                    } else if (attrs.getValue(i).equals("Japanese") || attrs.getValue(i).equals("jpn")) {
                        language.setLocale(new Locale("ja", "JP"));
                    } else if (attrs.getValue(i).equals("Türkçe") || attrs.getValue(i).equals("tr")) {
                        language.setLocale(new Locale("tr", "TR"));
                    } else if (attrs.getValue(i).equals("Simplified Chinese") || attrs.getValue(i).equals("SIMPLIFIED_CHINESE") || attrs.getValue(i).equals("CHINA") || attrs.getValue(i).equals("zh_CN")) {
                        language.setLocale(Locale.CHINA);
                    } else if (attrs.getValue(i).equals("Traditional Chinese") || attrs.getValue(i).equals("TRADITIONAL_CHINESE") || attrs.getValue(i).equals("TAIWAN") || attrs.getValue(i).equals("zh_TW")) {
                        language.setLocale(Locale.TAIWAN);
                    } else if (attrs.getValue(i).equals("Polski") || attrs.getValue(i).equals("pl")) {
                        language.setLocale(new Locale("pl", "PL"));
                    } else if (attrs.getValue(i).equals("???????") || attrs.getValue(i).equals("ru")) {
                        language.setLocale(new Locale("ru", "RU"));
                    } else if (attrs.getValue(i).equals("Estonian") || attrs.getValue(i).equals("et")) {
                        language.setLocale(new Locale("et", "ET"));
                    } else if (attrs.getValue(i).equals("Hungarian") || attrs.getValue(i).equals("hu")) {
                        language.setLocale(new Locale("hu", "HU"));
                    } else if (attrs.getValue(i).equals("??????") || attrs.getValue(i).equals("iw")) {
                        language.setLocale(new Locale("iw", "IW"));
                    } else if (attrs.getValue(i).equals("Svenska") || attrs.getValue(i).equals("sv")) {
                        language.setLocale(new Locale("sv", "SV"));
                    } else if (attrs.getValue(i).equals("Nederlands") || attrs.getValue(i).equals("nl")) {
                        language.setLocale(new Locale("nl", "NL"));
                    } else if (attrs.getValue(i).equals("?esky") || attrs.getValue(i).equals("cz")) {
                        language.setLocale(new Locale("cz", "CZ"));
                    } else if (attrs.getValue(i).equals("Dansk") || attrs.getValue(i).equals("da")) {
                        language.setLocale(new Locale("da", "DK"));
                    }
                }
            } else if (qName.equals("task-color")) {
                if (aName.equals("red")) {
                    r = new Integer(attrs.getValue(i)).hashCode();
                } else if (aName.equals("green")) {
                    g = new Integer(attrs.getValue(i)).hashCode();
                } else if (aName.equals("blue")) {
                    b = new Integer(attrs.getValue(i)).hashCode();
                }
            } else if (qName.equals("geometry")) {
                if (aName.equals("x")) {
                    x = new Integer(attrs.getValue(i)).hashCode();
                }
                if (aName.equals("y")) {
                    y = new Integer(attrs.getValue(i)).hashCode();
                }
                if (aName.equals("width")) {
                    width = new Integer(attrs.getValue(i)).hashCode();
                }
                if (aName.equals("height")) {
                    height = new Integer(attrs.getValue(i)).hashCode();
                }
            } else if (qName.equals("looknfeel")) {
                if (aName.equals("name")) {
                    styleName = attrs.getValue(i);
                }
                if (aName.equals("class")) {
                    styleClass = attrs.getValue(i);
                }
            } else if (qName.equals("file")) {
                if (aName.equals("path")) {
                    documentsMRU.append(DocumentCreator.createDocument(attrs.getValue(i)));
                }
            } else if (qName.equals("automatic-launch")) {
                if (aName.equals("value")) {
                    automatic = (new Boolean(attrs.getValue(i))).booleanValue();
                }
            } else if (qName.equals("dragTime")) {
                if (aName.equals("value")) {
                    dragTime = (new Boolean(attrs.getValue(i))).booleanValue();
                }
            } else if (qName.equals("tips-on-startup")) {
                if (aName.equals("value")) {
                    openTips = (new Boolean(attrs.getValue(i))).booleanValue();
                }
            } else if (qName.equals("redline")) {
                if (aName.equals("value")) {
                    redline = (new Boolean(attrs.getValue(i))).booleanValue();
                }
            } else if (qName.equals("lockdavminutes")) {
                if (aName.equals("value")) {
                    lockDAVMinutes = (new Integer(attrs.getValue(i))).intValue();
                }
            } else if (qName.equals("xsl-dir")) {
                if (aName.equals("dir")) {
                    if (new File(attrs.getValue(i)).exists())
                        xslDir = attrs.getValue(i);
                }
            } else if (qName.equals("xsl-fo")) {
                if (aName.equals("file")) {
                    if (new File(attrs.getValue(i)).exists())
                        xslFo = attrs.getValue(i);
                }
            } else if (qName.equals("working-dir")) {
                if (aName.equals("dir")) {
                    if (new File(attrs.getValue(i)).exists())
                        workingDir = attrs.getValue(i);
                }
            } else if (qName.equals("task-name")) {
                if (aName.equals("prefix"))
                    sTaskNamePrefix = attrs.getValue(i);
            } else if (qName.equals("toolBar")) {
                if (aName.equals("position"))
                    toolBarPosition = (new Integer(attrs.getValue(i))).intValue();
                else if (aName.equals("icon-size"))
                    iconSize = attrs.getValue(i);
                else if (aName.equals("show"))
                    buttonsshow = (new Integer(attrs.getValue(i))).intValue();
            } else if (qName.equals("statusBar")) {
                if (aName.equals("show"))
                    bShowStatusBar = (new Boolean(attrs.getValue(i))).booleanValue();
            } else if (qName.equals("export")) {
                if (aName.equals("name"))
                    bExportName = (new Boolean(attrs.getValue(i))).booleanValue();
                else if (aName.equals("complete"))
                    bExportComplete = (new Boolean(attrs.getValue(i))).booleanValue();
                else if (aName.equals("relations"))
                    bExportRelations = (new Boolean(attrs.getValue(i))).booleanValue();
                else if (aName.equals("border3d"))
                    bExport3DBorders = (new Boolean(attrs.getValue(i))).booleanValue();
            } else if (qName.equals("colors")) {
                if (aName.equals("tasks")) {
                    Color colorT = ColorConvertion.determineColor(attrs.getValue(i));
                    if (colorT != null)
                        setDefaultTaskColor(colorT);
                } else if (aName.equals("resources")) {
                    Color colorR = ColorConvertion.determineColor(attrs.getValue(i));
                    if (colorR != null)
                        setResourceColor(colorR);
                } else if (aName.equals("resourcesOverload")) {
                    Color colorR = ColorConvertion.determineColor(attrs.getValue(i));
                    if (colorR != null)
                        setResourceOverloadColor(colorR);
                }
            } else if (qName.equals("csv-general")) {
                if (aName.equals("fixed"))
                    csvOptions.bFixedSize = (new Boolean(attrs.getValue(i))).booleanValue();
                if (aName.equals("separatedChar"))
                    csvOptions.sSeparatedChar = attrs.getValue(i);
                if (aName.equals("separatedTextChar"))
                    csvOptions.sSeparatedTextChar = attrs.getValue(i);
            } else if (qName.equals("csv-tasks")) {
                if (aName.equals("id"))
                    csvOptions.bExportTaskID = (new Boolean(attrs.getValue(i))).booleanValue();
                if (aName.equals("name"))
                    csvOptions.bExportTaskName = (new Boolean(attrs.getValue(i))).booleanValue();
                if (aName.equals("start-date"))
                    csvOptions.bExportTaskStartDate = (new Boolean(attrs.getValue(i))).booleanValue();
                if (aName.equals("end-date"))
                    csvOptions.bExportTaskEndDate = (new Boolean(attrs.getValue(i))).booleanValue();
                if (aName.equals("percent"))
                    csvOptions.bExportTaskPercent = (new Boolean(attrs.getValue(i))).booleanValue();
                if (aName.equals("duration"))
                    csvOptions.bExportTaskDuration = (new Boolean(attrs.getValue(i))).booleanValue();
                if (aName.equals("webLink"))
                    csvOptions.bExportTaskWebLink = (new Boolean(attrs.getValue(i))).booleanValue();
                if (aName.equals("resources"))
                    csvOptions.bExportTaskResources = (new Boolean(attrs.getValue(i))).booleanValue();
                if (aName.equals("notes"))
                    csvOptions.bExportTaskNotes = (new Boolean(attrs.getValue(i))).booleanValue();
            } else if (qName.equals("csv-resources")) {
                if (aName.equals("id"))
                    csvOptions.bExportResourceID = (new Boolean(attrs.getValue(i))).booleanValue();
                if (aName.equals("name"))
                    csvOptions.bExportResourceName = (new Boolean(attrs.getValue(i))).booleanValue();
                if (aName.equals("mail"))
                    csvOptions.bExportResourceMail = (new Boolean(attrs.getValue(i))).booleanValue();
                if (aName.equals("phone"))
                    csvOptions.bExportResourcePhone = (new Boolean(attrs.getValue(i))).booleanValue();
                if (aName.equals("role"))
                    csvOptions.bExportResourceRole = (new Boolean(attrs.getValue(i))).booleanValue();
            }
        }
    }
    //old version of the color version
    if (qName.equals("task-color")) {
        //Color color = new Color(r, g, b);
        //getUIConfiguration().setTaskColor(color);
        setDefaultTaskColor(new Color(r, g, b));
    }
    if (qName.equals("font")) {
        String category = attrs.getValue("category");
        if ("menu".equals(category)) {
            myMenuFont = Font.decode(attrs.getValue("spec"));
        } else if ("chart-main".equals(category)) {
            myChartMainFont = Font.decode(attrs.getValue("spec"));
        }
    }
}
}
