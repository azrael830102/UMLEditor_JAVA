package Utilities;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import UMLComponent.BasicObject;
import UMLComponent.Canvas;
import UMLComponent.Port.DiagPortCode;

public abstract class MouseEventListener implements MouseListener, MouseMotionListener {
	protected Canvas canvas = Canvas.getInstance();   // Canvas is singleton 
	
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	protected DiagPortCode getTargetPortToConnect(Point p, BasicObject basicObj) {
		// check current mouse location is on this object or not
		int x1 = basicObj.getX1();
		int x2 = basicObj.getX2();
		int y1 = basicObj.getY1();
		int y2 = basicObj.getY2();
		Point center = new Point();
		center.x = (x1 + x2) / 2;
		center.y = (y1 + y2) / 2;
		Point[] points = { new Point(x1, y1), new Point(x2, y1), new Point(x2, y2), new Point(x1, y2) };

		for (int i = 0; i < points.length; i++) {
			Polygon t = new Polygon();
			// (0,1,center) -> (1,2,center) -> (2,3,center) -> (3,0,center)
			// is NORTH_PORT? -> is EAST_PORT? -> is SORTH_PORT? -> is WEST_PORT?
			t.addPoint(points[i].x, points[i].y);
			t.addPoint(points[(i + 1) % 4].x, points[(i + 1) % 4].y);
			t.addPoint(center.x, center.y);
			if (t.contains(p)) {
				return DiagPortCode.valueOf(i);
			}
		}
		return null;
	}
}