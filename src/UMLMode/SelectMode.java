package UMLMode;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.List;

import org.apache.log4j.Logger;

import UMLComponent.BasicObject;
import UMLComponent.BasicObject.ComponentType;
import UMLComponent.Port;
import UMLComponent.Line.BasicLineObj;
import Utilities.MouseEventListener;

public class SelectMode extends MouseEventListener {
	private boolean draggable = false;
	private Point startPoint;
	Rectangle tmpSelectedArea = new Rectangle();
	private List<BasicObject> basicObjList;
	Logger logger = Logger.getLogger(SelectMode.class);

	@Override
	public void mousePressed(MouseEvent e) {
		startPoint = e.getPoint();
		basicObjList = canvas.getBasicObjList();
		// reset the canvas; If mouse is pressed on the selectArea, don't reset(for
		// dragging items)
		if (tmpSelectedArea != null && !tmpSelectedArea.contains(startPoint)) {
			canvas.reset();
			tmpSelectedArea = canvas.selectedArea.getBounds();
		}

		// see is any item is selected
		for (int i = basicObjList.size() - 1; i >= 0; i--) {
			if (basicObjList.get(i).checkSelected(startPoint)) {
				basicObjList.get(i).setSlected(true);
				break;
			}
		}

		// check is any item is selected draggable or not
		draggable = false;
		for (BasicObject obj : basicObjList) {
			if (obj.isSlected()) {
				draggable = true;
			}
		}

		// repaint the canvas
		canvas.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int moveX = e.getX() - startPoint.x;
		int moveY = e.getY() - startPoint.y;
		if (draggable) {
			// if draggable flag is on then it's drag mode.
			for (BasicObject obj : basicObjList) {
				if (obj.isSlected()) {
					if (tmpSelectedArea.isEmpty() && obj.getComponentType().equals(ComponentType.LINE)) {
						((BasicLineObj) obj).resetStartEnd(e.getPoint());
					} else {
						obj.resetLocation(moveX, moveY);
						tmpSelectedArea = getSelectedItemCoverage();
					}
				}
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
		if (draggable) {
			if (tmpSelectedArea.isEmpty()) {
				// reset the port relation which is pointed by the selected Line
				resetLinePortRelation(e.getPoint());
			}
		}

		// let select area invisible, so the canvas is clean
		canvas.selectedArea.setSize(Math.abs(e.getX() - startPoint.x), Math.abs(e.getY() - startPoint.y));
		if(!canvas.selectedArea.isEmpty()) {
			tmpSelectedArea = getSelectedItemCoverage();
			canvas.selectedArea.setBounds(0, 0, 0, 0);
		}
		canvas.repaint();
	}

	private void resetLinePortRelation(Point p) {
		for (BasicObject obj : basicObjList) {
			if (obj.isSlected() && obj.getComponentType().equals(ComponentType.LINE)) {
				// Line
				BasicLineObj basicLine = (BasicLineObj) obj;
				for (BasicObject basicObj : basicObjList) {
					if (basicObj.getComponentType().equals(ComponentType.DIAGRAM) && basicObj.checkSelected(p)) {
						Port targetPort = basicObj.getPort(getTargetPortToConnect(p, basicObj).getPortCode());
						basicLine.resetPort(targetPort);
						basicLine.resetLocation();
					}
				}
			}
		}
	}

	private Rectangle getSelectedItemCoverage() {
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

	private boolean checkSelectedArea(BasicObject obj) {
		// If basic object is in the select area, set the object selected
		Point upperleft = new Point(obj.getX1(), obj.getY1());
		Point lowerright = new Point(obj.getX2(), obj.getY2());
		/* show ports of selected objects */
		if (canvas.selectedArea.contains(upperleft) && canvas.selectedArea.contains(lowerright)) {
			return true;
		}
		return false;
	}
}
