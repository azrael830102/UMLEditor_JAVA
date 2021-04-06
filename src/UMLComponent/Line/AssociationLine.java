package UMLComponent.Line;

import java.awt.Graphics;
import java.awt.Point;

public class AssociationLine extends BasicLineObj {

	public AssociationLine(Point startPoint, Point endPoint) {
		super(startPoint, endPoint);
	}

	public AssociationLine(int x1, int y1, int x2, int y2) {
		super(new Point(x1, y1), new Point(x2, y2));
	}

	@Override
	protected void drawArrowhead(Graphics g) {
		Point[] arrowheadVertices = getArrowheadVertices();
		for (Point p : arrowheadVertices) {
			g.drawLine(p.x, p.y, x2, y2);
		}
	}

}
