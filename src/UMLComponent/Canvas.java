package UMLComponent;

import javax.swing.JPanel;

import UMLMode.Mode;



@SuppressWarnings("serial")
public class Canvas extends JPanel {
	private static Canvas instance = null;
	protected Mode currentMode = null;

	private Canvas() {
		// Exists only to defeat instantiation.
	}

	public static Canvas getInstance() {
		if (instance == null) {
			instance = new Canvas();
		}
		return instance;
	}
	
	public Mode getCurrentTool() {
		return currentMode;
	}

	public void setCurrentTool(Mode currentMode) {
		this.currentMode = currentMode;
	}
	
	
}
