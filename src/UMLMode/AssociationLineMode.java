package UMLMode;

import java.awt.Point;
import java.awt.event.MouseEvent;

import org.apache.log4j.Logger;

import UMLComponent.Port.LinePortCode;
import UMLComponent.Line.AssociationLine;

public class AssociationLineMode extends BasicLineMode {
	Logger logger = Logger.getLogger(AssociationLineMode.class);

	@Override
	public void mouseDragged(MouseEvent e) {
		/* show dragged line */
		if (startPoint != null) {
			canvas.setTempLine(new AssociationLine(startPoint, e.getPoint()));  
			canvas.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Point endPoint = null;
		if (startPoint != null) {
			endPoint = findDiagramToConnect(e.getPoint(), LinePortCode.END_POINT);
			if (endPoint != null) {
				AssociationLine line = new AssociationLine(startPoint, endPoint);
				connectingLineAndDiagrams(line);
			}
			resetForMouseReleasedEvent();
		}
	}

}
