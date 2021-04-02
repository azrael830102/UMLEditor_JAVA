package UMLEditorBody.MenuBar;

import java.awt.event.KeyEvent;

public enum MenuFunctions {
	RENAME(0,"Rename",KeyEvent.VK_F2),
	GROUP(1,"Group",KeyEvent.VK_F3),
	UNGROUP(2,"Ungroup",KeyEvent.VK_F4);
	
	private int menuFuncCode;
	private String menuFuncName;
	private int menuFuncShortcut;
	
	MenuFunctions(int menuFuncCode, String menuFuncName, int menuFuncShortcut){
		this.menuFuncCode = menuFuncCode;
		this.menuFuncName = menuFuncName;
		this.menuFuncShortcut = menuFuncShortcut;
	}

	public int getMenuFuncCode() {
		return menuFuncCode;
	}

	public String getMenuFuncName() {
		return menuFuncName;
	}

	public int getMenuFuncShortcut() {
		return menuFuncShortcut;
	}
	
	
}
