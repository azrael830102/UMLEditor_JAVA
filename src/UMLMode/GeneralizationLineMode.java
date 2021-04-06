package UMLMode;

import java.awt.Point;
import java.awt.event.MouseEvent;

import org.apache.log4j.Logger;

import UMLComponent.Port.LinePortCode;
import UMLComponent.Line.GeneralizationLine;

public class GeneralizationLineMode extends BasicLineMode {
	Logger logger = Logger.getLogger(GeneralizationLineMode.class);

	@Override
	public void mouseDragged(MouseEvent e) {
		/* show dragged line */
		if (startPoint != null) {
			canvas.tempLine = new GeneralizationLine(startPoint, e.getPoint());
			canvas.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Point endPoint = null;
		if (startPoint != null) {
			endPoint = findDiagramToConnect(e.getPoint(), LinePortCode.END_POINT);
			if (endPoint != null) {
				GeneralizationLine line = new GeneralizationLine(startPoint, endPoint);
				connectingLineAndDiagrams(line);
			}
			resetForMouseReleasedEvent();
		}
	}
}
