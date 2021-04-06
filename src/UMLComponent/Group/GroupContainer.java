package UMLComponent.Group;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import UMLComponent.BasicObject;
import UMLComponent.Port;
import Utilities.CommonUse;

public class GroupContainer implements BasicObject {
	private boolean isSlected = false;
	private List<BasicObject> basicObjectList = new ArrayList<BasicObject>();
	private Rectangle bounds = new Rectangle();

	@Override
	public void setSlected(boolean isSlected) {
		this.isSlected = isSlected;
	}

	@Override
	public boolean isSlected() {
		return isSlected;
	}

	@Override
	public int getX1() {
		return bounds.x;
	}

	@Override
	public int getX2() {
		return bounds.x + bounds.width;
	}

	@Override
	public int getY1() {
		return bounds.y;
	}

	@Override
	public int getY2() {
		return bounds.y + bounds.height;
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.GROUP_CONTAINER;
	}

	@Override
	public void resetLocation(int moveX, int moveY) {
		bounds.setLocation(getX1() + moveX, getY1() + moveY);
		for (BasicObject obj : basicObjectList) {
			obj.resetLocation(moveX, moveY);
		}
	}

	@Override
	public Port getPort(int portCode) {
		// not using
		return null;
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

	@Override
	public void paint(Graphics g) {
		for (BasicObject obj : basicObjectList) {
			obj.paint(g);
		}
		if (isSlected()) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(CommonUse.GROUP_CONTAINER_STROKE);
			g2d.setColor(CommonUse.LINE_FOCUS_COLOR);
			g2d.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
			g2d.setStroke(new BasicStroke());
			g2d.setColor(CommonUse.LINE_BLUR_COLOR);
		}
	}

	public void addItem(BasicObject item) {
		basicObjectList.add(item);
	}

	public List<BasicObject> getGroupItemList() {
		return basicObjectList;
	}

	public void setBounds() {
		int offset = 5;
		int leftX = Integer.MAX_VALUE, rightX = Integer.MIN_VALUE;
		int upY = Integer.MAX_VALUE, bottomY = Integer.MIN_VALUE;

		for (BasicObject obj : basicObjectList) {

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
		bounds = new Rectangle(leftX - offset, upY - offset, Math.abs(leftX - rightX) + offset * 2,
				Math.abs(upY - bottomY) + offset * 2);
	}
}
