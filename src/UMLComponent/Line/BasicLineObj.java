package UMLComponent.Line;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;

import UMLComponent.BasicObject;
import UMLComponent.Port;
import UMLComponent.Port.LinePortCode;
import Utilities.CommonUse;

public class BasicLineObj implements BasicObject {
	private boolean isSlected = false;
	protected Port[] ports = new Port[LinePortCode.values().length]; // start|end point port (for now)
	protected int x1, y1, x2, y2;
	private LinePortCode selectedPoint;

	public BasicLineObj(Point startPoint, Point endPoint){
		this.x1 = startPoint.x;
		this.y1 = startPoint.y;
		this.x2 = endPoint.x;
		this.y2 = endPoint.y;

	}
	@Override
	public boolean isDiagram() {
		return false;
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
		return this.x1;
	}

	@Override
	public int getX2() {
		return this.x2;
	}

	@Override
	public int getY1() {
		return this.y1;
	}

	@Override
	public int getY2() {
		return this.y2;
	}

	@Override
	public boolean checkSelected(Point p) {
		int tolerance = 5;
		if (distance(p) < tolerance) {
			double distToStart = Math.sqrt(Math.pow((p.x - x1), 2) + Math.pow((p.y - y1), 2));
			double distToEnd = Math.sqrt(Math.pow((p.x - x2), 2) + Math.pow((p.y - y2), 2));
			if (distToStart < distToEnd) {
				selectedPoint = LinePortCode.START_POINT;
			} else {
				selectedPoint = LinePortCode.END_POINT;
			}
			return true;
		} else
			return false;
	}

	@Override
	public void resetLocation(int moveX, int moveY) {
		resetLocation();
	}
	public void resetLocation() {
		this.x1 = (int) ports[LinePortCode.START_POINT.getPortCode()].getCenterX();
		this.y1 = (int) ports[LinePortCode.START_POINT.getPortCode()].getCenterY();
		this.x2 = (int) ports[LinePortCode.END_POINT.getPortCode()].getCenterX();
		this.y2 = (int) ports[LinePortCode.END_POINT.getPortCode()].getCenterY();
	}
	@Override
	public Port getPort(int portCode) {
		return ports[portCode];
	}
	
	public void setPorts(Port diagObj_1st_Port, Port diagObj_2nd_Port) {
		ports[LinePortCode.START_POINT.getPortCode()] = diagObj_1st_Port;
		ports[LinePortCode.END_POINT.getPortCode()] = diagObj_2nd_Port;
	}
	
	public void resetStartEnd(Point p) {
		if (selectedPoint.equals(LinePortCode.START_POINT)) {
			this.x1 = p.x;
			this.y1 = p.y;
		} else if (selectedPoint.equals(LinePortCode.END_POINT)) {
			this.x2 = p.x;
			this.y2 = p.y;
		}
	}

	public void resetPort(Port port) {
		port.addLine(this);
		this.ports[selectedPoint.getPortCode()].removeLine(this);
		this.ports[selectedPoint.getPortCode()] = port;
	}

	private double distance(Point p) {
		Line2D line = new Line2D.Double(x1, y1, x2, y2);
		return line.ptLineDist(p.getX(), p.getY());
	}

	protected void drawBasicLine(Graphics g) {
		g.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void paint(Graphics g) {
		setLineColor(g);
		drawBasicLine(g);
		drawArrowhead(g);
	}
	
	/**
	 * Need to be implemented to fit the arrow style
	 * */
	protected void drawArrowhead(Graphics g) {};
	
	/**
	 * <pre>
	 * arrowheadVertices[0] : the vertex above line
	 * arrowheadVertices[1] : the vertex below line
	 * </pre>
	 * */
	protected Point[] getArrowheadVertices() {
		Point[] arrowheadVertices = new Point[2];
		double phi = Math.toRadians(30);
		int barb = 20; 
		double dy = y1 - y2;
		double dx = x1 - x2;
		double theta = Math.atan2(dy, dx);
		double x, y, rho = theta + phi;
		
		for (int j = 0; j < 2; j++) {
			x = x2 + barb * Math.cos(rho);
			y = y2 + barb * Math.sin(rho);
			arrowheadVertices[j] = new Point((int)x, (int)y);
			rho = theta - phi;
		}
		return arrowheadVertices;
	}
	
	protected void setLineColor(Graphics g) {
		if(isSlected()) {
			g.setColor(CommonUse.LINE_FOCUS_COLOR);
		}else {
			g.setColor(CommonUse.LINE_BLUR_COLOR);
		}
	}
}
