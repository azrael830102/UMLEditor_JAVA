package UMLComponent.Diagram;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;

public class UseCaseDiag extends BasicDiagramObj{
	
	private JTextField className;
	private Font displayFont = new Font("Comic Sans MS", Font.BOLD, 18);
	
	UseCaseDiag(){
		super();
		className = new JTextField("UseCase");
		this.setName(className.getText());
		className.setBackground(new Color(255, 255, 185));
		className.setFont(displayFont);
		className.setHorizontalAlignment(JTextField.CENTER);
		className.setEditable(false);
		className.setOpaque(false);
		
		this.setBackground(new Color(255, 255, 185));
		this.setLayout(null);
		this.add(className);
		this.setOpaque(false);
		this.setBorder(null);
		this.setSize(280, 100);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
