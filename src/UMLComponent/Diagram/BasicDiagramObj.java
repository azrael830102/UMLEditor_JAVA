package UMLComponent.Diagram;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import UMLComponent.BasicObject;
import UMLComponent.Port;

public abstract class BasicDiagramObj extends JPanel implements BasicObject{
	protected ArrayList<Port> ports;
	
	public BasicDiagramObj() {
		this.setSize(180, 180);
		this.setBackground(Color.ORANGE);
	}
	
}
