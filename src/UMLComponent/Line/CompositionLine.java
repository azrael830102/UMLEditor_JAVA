package UMLComponent.Line;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class CompositionLine extends BasicLineObj {

	public CompositionLine(Point startPoint, Point endPoint) {
		super(startPoint, endPoint);
	}

	public CompositionLine(int x1, int y1, int x2, int y2) {
		super(new Point(x1, y1), new Point(x2, y2));
	}

	@Override
	protected void drawArrowhead(Graphics g) {

		g.setColor(Color.BLACK);
		int x_sum = 0, y_sum = 0;
		Point[] arrowheadVertices = getArrowheadVertices();
		Polygon polygon = new Polygon();
		for (Point p : arrowheadVertices) {
			polygon.addPoint(p.x, p.y);
			x_sum += p.x;
			y_sum += p.y;
		}
		polygon.addPoint(x2, y2);
		g.fillPolygon(polygon);
		
		polygon.reset();
		for (Point p : arrowheadVertices) {
			polygon.addPoint(p.x, p.y);
		}
		polygon.addPoint(x_sum - x2, y_sum - y2);
		g.fillPolygon(polygon);
	}

}
