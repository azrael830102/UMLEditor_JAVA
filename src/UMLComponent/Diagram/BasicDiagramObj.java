package UMLComponent.Diagram;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import UMLComponent.Port;

public abstract class BasicDiagramObj extends JPanel{
	protected ArrayList<Port> ports;
	
	public BasicDiagramObj() {
		this.setSize(180, 180);
		this.setBackground(Color.ORANGE);
	}
	
}
