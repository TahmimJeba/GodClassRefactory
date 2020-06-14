public class Class2 {

	public Class2(){
	}
	/** Create an item with a label */
public JMenuItem createNewItemText(String label) {
    JMenuItem item = new JMenuItem(label);
    item.addActionListener(this);
    return item;
}
	/** Create an item with a label and an icon */
public JMenuItem createNewItem(String label, String icon) {
    JMenuItem item = new JMenuItem(label, new ImageIcon(getClass().getResource(icon)));
    item.addActionListener(this);
    return item;
}
	/** Create an item with a label and an icon */
public JMenuItem createNewItem(String label, String icon) {
    JMenuItem item = new JMenuItem(label, new ImageIcon(getClass().getResource(icon)));
    item.addActionListener(this);
    return item;
}
	/** Create an item with an icon */
public JMenuItem createNewItem(String icon) {
    JMenuItem item = new JMenuItem(new ImageIcon(getClass().getResource(icon)));
    item.addActionListener(this);
    return item;
}
	/** Create an item with an icon */
public JMenuItem createNewItem(String icon) {
    JMenuItem item = new JMenuItem(new ImageIcon(getClass().getResource(icon)));
    item.addActionListener(this);
    return item;
}
	//Correct the label of menu without '$' character
public static String correctLabel(String label) {
    int index = label.indexOf('$');
    if (index != -1 && label.length() - index > 1)
        label = label.substring(0, index).concat(label.substring(++index));
    return label;
}
	/** Change the label for menu, in fact check in the label contains a mnemonic */
public JMenu changeMenuLabel(JMenu menu, String label) {
    int index = label.indexOf('$');
    if (index != -1 && label.length() - index > 1) {
        menu.setText(label.substring(0, index).concat(label.substring(++index)));
        menu.setMnemonic(Character.toLowerCase(label.charAt(index)));
    } else {
        menu.setText(label);
    //menu.setMnemonic('');
    }
    return menu;
}
	/** Change the label for menu, in fact check in the label contains a mnemonic */
public JMenu changeMenuLabel(JMenu menu, String label) {
    int index = label.indexOf('$');
    if (index != -1 && label.length() - index > 1) {
        menu.setText(label.substring(0, index).concat(label.substring(++index)));
        menu.setMnemonic(Character.toLowerCase(label.charAt(index)));
    } else {
        menu.setText(label);
    //menu.setMnemonic('');
    }
    return menu;
}
	/** Change the label for menuItem, in fact check in the label contains a mnemonic */
public JMenuItem changeMenuLabel(JMenuItem menu, String label) {
    int index = label.indexOf('$');
    if (index != -1 && label.length() - index > 1) {
        menu.setText(label.substring(0, index).concat(label.substring(++index)));
        menu.setMnemonic(Character.toLowerCase(label.charAt(index)));
    } else {
        menu.setText(label);
    //menu.setMnemonic('');
    }
    return menu;
}
	/** Change the label for menuItem, in fact check in the label contains a mnemonic */
public JMenuItem changeMenuLabel(JMenuItem menu, String label) {
    int index = label.indexOf('$');
    if (index != -1 && label.length() - index > 1) {
        menu.setText(label.substring(0, index).concat(label.substring(++index)));
        menu.setMnemonic(Character.toLowerCase(label.charAt(index)));
    } else {
        menu.setText(label);
    //menu.setMnemonic('');
    }
    return menu;
}
}