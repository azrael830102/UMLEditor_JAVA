package UMLEditorBody.ToolBox;

import javax.swing.ImageIcon;

import UMLMode.SelectMode;
import UMLMode.UseCaseMode;
import Utilities.MouseEventListener;

/**
 * This class is enum types of tools in tool box,
 * manage tool's attribute here 
 * 
 * @author BobLin
 */
public enum Tools {
	SELECT(0, "Select"), 
	ASSOCIATION(1, "Association Line"), 
	GENERALIZATION(2, "Generalization Line"),
	COMPOSITION(3, "Composition Line"), 
	CLASS(4, "Class Diagram"), 
	USE_CASE(5, "Use Case Diagram");
	
	Tools(int toolsCode, String toolsName) {
		this.toolsCode = toolsCode;
		this.toolsName = toolsName;
	}
	
	private final int toolsCode;
    private final String toolsName;
    
    public int getToolsCode() {
    	return toolsCode;
    }
    public String getToolsName() {
    	return toolsName;
    }
    
    public ImageIcon getImage() {
    	switch(this) {
		case ASSOCIATION:
			return new ImageIcon("img/Association.png");
		case CLASS:
			return new ImageIcon("img/Class.png");
		case COMPOSITION:
			return new ImageIcon("img/Composition.png");
		case GENERALIZATION:
			return new ImageIcon("img/Inheritance.png");
		case SELECT:
			return new ImageIcon("img/Select.png");
		case USE_CASE:
			return new ImageIcon("img/Usecase.png");
		default:
			return null;
    	}
    }
    
    public MouseEventListener getMode() {
    	switch(this) {
    	case SELECT:
			return new SelectMode();
		// Lines
		case ASSOCIATION:
			return null;
		case COMPOSITION:
			return null;
		case GENERALIZATION:
			return null;
			
		// Diagrams	
		case CLASS:
			return null;
		case USE_CASE:
			return new UseCaseMode();
			
		default:
			return null;
    	}
    }
    
}
