package UMLMode;

import java.awt.Point;
import java.awt.event.MouseEvent;

import org.apache.log4j.Logger;

import UMLComponent.Diagram.BasicDiagramObj;
import UMLComponent.Diagram.UseCaseDiag;
import Utilities.MouseEventListener;


public class UseCaseMode extends MouseEventListener{
	Logger logger = Logger.getLogger(UseCaseMode.class);
	
	@Override
	public void mousePressed(MouseEvent e) {
		Object source = e.getSource();
		if (source == canvas) {
			Point p = e.getPoint();
			BasicDiagramObj us = new UseCaseDiag(p);

			
			canvas.addObject(us);
			canvas.add(us);
			
			canvas.revalidate();
			canvas.repaint();
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}
