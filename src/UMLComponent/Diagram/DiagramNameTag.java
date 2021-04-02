package UMLComponent.Diagram;

import java.awt.Color;

import javax.swing.JTextField;

import Utilities.CommonUse;
/**
 * Not using for now
 * */
@SuppressWarnings("serial")
public class DiagramNameTag extends JTextField {
	public DiagramNameTag() {
	}
	
	public DiagramNameTag(String name) {
		super(name);
		
		setBackground(new Color(255, 255, 185));
		setFont(CommonUse.DIAGRAM_NAME_FONT);
		setHorizontalAlignment(JTextField.CENTER);
		setEditable(false);
		setOpaque(false);
	}
}
