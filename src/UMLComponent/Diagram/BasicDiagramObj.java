package UMLComponent.Diagram;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import UMLComponent.BasicObject;
import UMLComponent.Port;
import Utilities.CommonUse;

@SuppressWarnings("serial")
public abstract class BasicDiagramObj extends JPanel implements BasicObject {

	private boolean isSlected = false;
//	private String diagName;
	protected Port[] ports = new Port[4]; // Up, Down, Left, Right

	public BasicDiagramObj() {
		this.setSize(180, 180);
		this.setBackground(Color.ORANGE);
	}

	protected void createPorts() {
		int[] xpoint = { getX() + getWidth() / 2, // Up
				getX() + getWidth(), // Right
				getX() + getWidth() / 2, // Down
				getX() // Left
		};
		int[] ypoint = { getY(), // Up
				getY() + getHeight() / 2, // Right
				getY() + getHeight(), // Down
				getY() + getHeight() / 2 // Left
		};

		for (int i = 0; i < ports.length; i++) {
			Port port = new Port();
			port.setPort(xpoint[i], ypoint[i]);
			ports[i] = port;
		}
	}

	@Override
	public boolean isSlected() {
		return isSlected;
	}

	@Override
	public void setSlected(boolean isSelected) {
		this.isSlected = isSelected;
	}

//	public String getDiagName() {
//		return diagName;
//	}
//
//	public void setDiagName(String diagName) {
//		this.diagName = diagName;
//		setName(getDiagName());
//	}

	@Override
	public int getX1() {
		return this.getX();
	}

	@Override
	public int getX2() {
		return this.getX() + this.getWidth();
	}

	@Override
	public int getY1() {
		return this.getY();
	}

	@Override
	public int getY2() {
		return this.getY() + this.getHeight();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(isSlected()) {
			showPorts(g);
		}
	}
	
	public void resetLocation(int moveX, int moveY) {
		this.setLocation(getX() + moveX, getY() + moveY);
		createPorts();
	}
	
	public void rename(String newName) {
		setName(newName);
		//setDiagName(newName);
	}
	protected void drawCenteredString(Graphics g, String text, Rectangle rect) {
		FontMetrics metrics = g.getFontMetrics(CommonUse.DIAGRAM_NAME_FONT);
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setFont(CommonUse.DIAGRAM_NAME_FONT);
		g.drawString(text, x, y);
	}

	private void showPorts(Graphics g) {
		for (int i = 0; i < ports.length; i++) {
			g.fillRect(ports[i].x, ports[i].y, ports[i].width, ports[i].height);
		}
	}
}
