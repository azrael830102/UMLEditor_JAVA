package UMLComponent.Diagram;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import UMLComponent.BasicObject;
import UMLComponent.Port;

@SuppressWarnings("serial")
public abstract class BasicDiagramObj extends JPanel implements BasicObject{
	protected ArrayList<Port> ports;
	private boolean isSlected;
	
	public BasicDiagramObj() {
		this.setSize(180, 180);
		this.setBackground(Color.ORANGE);
	}

	@Override
	public boolean isSlected() {
		return isSlected;
	}
	@Override
	public void setSlected(boolean isSelected) {
		this.isSlected = isSelected;
	}
	 
}
