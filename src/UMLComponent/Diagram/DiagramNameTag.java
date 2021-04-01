package UMLComponent.Diagram;

import java.awt.Color;

import javax.swing.JTextField;

import Utilities.CommonUse;

@SuppressWarnings("serial")
public class DiagramNameTag extends JTextField {
	public DiagramNameTag() {
		// TODO Auto-generated constructor stub
	}
	
	public DiagramNameTag(String name) {
		super(name);
		
		setBackground(new Color(255, 255, 185));
		setFont(CommonUse.DiagramNameTagFont);
		setHorizontalAlignment(JTextField.CENTER);
		setEditable(false);
		setOpaque(false);
	}
}
