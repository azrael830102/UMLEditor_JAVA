package UMLMode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import org.apache.log4j.Logger;

import UMLComponent.BasicObject;
import Utilities.MouseEventListener;

public class SelectMode extends MouseEventListener {
	private Point startPoint;
	private List<BasicObject> basicObjList;
	Logger logger = Logger.getLogger(SelectMode.class);

	public void mousePressed(MouseEvent e) {
		startPoint = e.getPoint();
		basicObjList = canvas.getBasicObjList();

		// reset
		canvas.reset();

		canvas.repaint();
	}

	public void mouseDragged(MouseEvent e) {
		int moveX = e.getX() - startPoint.x;
		int moveY = e.getY() - startPoint.y;
		/* object selected */
//		if (canvas.selectedObj != null) {
//			// move Line object
//			if (judgeInside == "insideLine") {
//				selectedLine = (Line) canvas.selectedObj;
//				selectedLine.resetStartEnd(e.getPoint());
//				// canvas.tempLine = line;
//			}
//			else {
//				canvas.selectedObj.resetLocation(moveX, moveY);
//			}
//			startP.x = e.getX();
//			startP.y = e.getY();
//		}
		/* group area selected */
//		else {
		// ¥ª¤W¨ì¥k¤U
		if (e.getX() > startPoint.x)
			canvas.selectedArea.setBounds(startPoint.x, startPoint.y, moveX, moveY);
		else
			canvas.selectedArea.setBounds(e.getX(), e.getY(), Math.abs(moveX), Math.abs(moveY));
			
//		}
		canvas.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		canvas.selectedArea.setBounds(0, 0, 0, 0);

		canvas.repaint();
	}
}
