package UMLComponent.Line;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class GeneralizationLine extends BasicLineObj {

	public GeneralizationLine(Point startPoint, Point endPoint) {
		super(startPoint, endPoint);
	}

	public GeneralizationLine(int x1, int y1, int x2, int y2) {
		super(new Point(x1, y1), new Point(x2, y2));
	}

	@Override
	public void paint(Graphics g) {
		setLineColor(g);
		drawBasicLine(g);
		Point[] arrowheadVertices = getArrowheadVertices();
		Polygon polygon = new Polygon();
		for (Point p : arrowheadVertices) {
			polygon.addPoint(p.x, p.y);
		}
		polygon.addPoint(x2, y2);
		g.setColor(Color.WHITE);
		g.fillPolygon(polygon);
		g.setColor(Color.BLACK);
		g.drawPolygon(polygon);
	}

}
