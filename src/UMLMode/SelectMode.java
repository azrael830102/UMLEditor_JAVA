package UMLMode;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.List;

import org.apache.log4j.Logger;

import UMLComponent.BasicObject;
import Utilities.MouseEventListener;

public class SelectMode extends MouseEventListener {
	private boolean dragable = false;
	private Point startPoint;
	Rectangle tmpSelectedArea = null;
	private List<BasicObject> basicObjList;
	Logger logger = Logger.getLogger(SelectMode.class);

	@Override
	public void mousePressed(MouseEvent e) {
		startPoint = e.getPoint();
		basicObjList = canvas.getBasicObjList();
		// reset
		if (tmpSelectedArea != null && !tmpSelectedArea.contains(startPoint)) {
			canvas.reset();
		}

		for (int i = basicObjList.size() - 1; i >= 0; i--) {
			if (checkSelected(startPoint, basicObjList.get(i))) {
				basicObjList.get(i).setSlected(true);
				break;
			}
		}
		dragable = false;

		for (BasicObject obj : basicObjList) {
			if (obj.isSlected()) {
				dragable = true;
			}
		}

		canvas.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int moveX = e.getX() - startPoint.x;
		int moveY = e.getY() - startPoint.y;
		if (dragable) {
			for (BasicObject obj : basicObjList) {
				if (obj.isSlected())
					obj.resetLocation(moveX, moveY);
			}
			canvas.selectedArea.setLocation((int) (canvas.selectedArea.getX() + moveX),
					(int) (canvas.selectedArea.getY() + moveY));

			startPoint.x = e.getX();
			startPoint.y = e.getY();
		} else {
			if (e.getX() > startPoint.x)
				canvas.selectedArea.setBounds(startPoint.x, startPoint.y, moveX, moveY);
			else
				canvas.selectedArea.setBounds(e.getX(), e.getY(), Math.abs(moveX), Math.abs(moveY));

			for (BasicObject obj : basicObjList) {
				if (checkSelectedArea(obj)) {
					obj.setSlected(true);
				} else {
					obj.setSlected(false);
				}
			}
		}
		canvas.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		
		if (dragable) {
			// TODO Line reconnect
		} else {
			canvas.selectedArea.setSize(Math.abs(e.getX() - startPoint.x), Math.abs(e.getY() - startPoint.y));
			tmpSelectedArea = resizeSelectedItemCoverage();
			canvas.selectedArea.setBounds(0, 0, 0, 0);
		}
		canvas.repaint();
	}

	private Rectangle resizeSelectedItemCoverage() {
		int leftX = Integer.MAX_VALUE, rightX = Integer.MIN_VALUE;
		int upY = Integer.MAX_VALUE, bottomY = Integer.MIN_VALUE;
		
		for (BasicObject obj : canvas.getBasicObjList()) {
			if (obj.isSlected()) {
				if (obj.getX1() < leftX) {
					leftX = obj.getX1();
				}
				if (obj.getX2() > rightX) {
					rightX = obj.getX2();
				}
				if (obj.getY1() < upY) {
					upY = obj.getY1();
				}
				if (obj.getY2() > bottomY) {
					bottomY = obj.getY2();
				}
			}
		}
		return new Rectangle(leftX, upY, Math.abs(leftX - rightX), Math.abs(upY - bottomY));
	}

	private boolean checkSelected(Point p, BasicObject obj) {
		int x1 = obj.getX1();
		int x2 = obj.getX2();
		int y1 = obj.getY1();
		int y2 = obj.getY2();
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

	private boolean checkSelectedArea(BasicObject obj) {
		Point upperleft = new Point(obj.getX1(), obj.getY1());
		Point lowerright = new Point(obj.getX2(), obj.getY2());
		/* show ports of selected objects */
		if (canvas.selectedArea.contains(upperleft) && canvas.selectedArea.contains(lowerright)) {
			return true;
		}
		return false;
	}
}
