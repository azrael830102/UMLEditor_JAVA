package UMLMode;

import java.awt.Point;
import java.awt.event.MouseEvent;

import org.apache.log4j.Logger;

import UMLComponent.Diagram.BasicDiagramObj;
import UMLComponent.Diagram.ClassDiag;
import Utilities.MouseEventListener;

public class ClassDiagMode extends MouseEventListener{
	Logger logger = Logger.getLogger(UseCaseMode.class);
	@Override
	public void mousePressed(MouseEvent e) {
		Object source = e.getSource();
		if (source == canvas) {
			Point p = e.getPoint();
			BasicDiagramObj us = new ClassDiag(p);

			canvas.addObject(us);
			canvas.add(us);
			canvas.revalidate();
			canvas.repaint();
		}
	}

}
