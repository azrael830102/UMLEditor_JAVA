package UMLComponent.Diagram;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.JPanel;

import UMLComponent.BasicObject;
import UMLComponent.Port;
import Utilities.CommonUse;

@SuppressWarnings("serial")
public abstract class BasicDiagramObj extends JPanel implements BasicObject {

	private boolean isSlected = false;
	protected Port[] ports; //  north|east|south|west port (for now)

	public BasicDiagramObj() {
		this.setSize(180, 180);
		this.setBackground(Color.ORANGE);
	}

	protected void createPorts() {
		ports = Port.createPorts(this);
	}
	@Override
	public boolean isDiagram() {
		return true;
	}
	@Override
	public boolean isSlected() {
		return isSlected;
	}

	@Override
	public void setSlected(boolean isSelected) {
		this.isSlected = isSelected;
	}

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
	
	@Override
	public void resetLocation(int moveX, int moveY) {
		this.setLocation(getX() + moveX, getY() + moveY);
//		createPorts();
		for(Port port : ports) {
			port.x += moveX;
			port.y += moveY;
			port.resetLines();
		}
	}
	@Override
	public Port getPort(int portCode) {
		return ports[portCode];
	}
	public void rename(String newName) {
		setName(newName);
	}
	
	@Override
	public boolean checkSelected(Point p) {
		// check current mouse location is on this object or not
		int x1 = getX1();
		int x2 = getX2();
		int y1 = getY1();
		int y2 = getY2();
		Point center = new Point();
		center.x = (x1 + x2) / 2;
		center.y = (y1 + y2) / 2;
		Point[] points = { new Point(x1, y1), new Point(x2, y1), new Point(x2, y2), new Point(x1, y2) };

		for (int i = 0; i < points.length; i++) {
			Polygon t = new Polygon();
			// (0,1,center) (1,2,center) (2,3,center) (3,0,center)
			int secondIndex = ((i + 1) % 4);
			t.addPoint(points[i].x, points[i].y);
			t.addPoint(points[secondIndex].x, points[secondIndex].y);
			t.addPoint(center.x, center.y);
			if (t.contains(p)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Consider to the different Font effect, so the String can be at the center of the object
	 * */
	protected void drawCenteredString(Graphics g, String text, boolean showStringRect, Rectangle stringRect) {
		FontMetrics metrics = g.getFontMetrics(CommonUse.DIAGRAM_NAME_FONT);
		int x = stringRect.x + (stringRect.width - metrics.stringWidth(text)) / 2;
		int y = stringRect.y + ((stringRect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setFont(CommonUse.DIAGRAM_NAME_FONT);
		g.drawString(text, x, y);
		if(showStringRect) {
			g.drawRect(stringRect.x, stringRect.y, stringRect.width, stringRect.height);
		}
	}

	private void showPorts(Graphics g) {
		for (int i = 0; i < ports.length; i++) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(ports[i].x, ports[i].y, ports[i].width, ports[i].height);
		}
	}
}
