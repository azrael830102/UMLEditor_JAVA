package UMLComponent.Diagram;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import Utilities.CommonUse;

@SuppressWarnings("serial")
public class UseCaseDiag extends BasicDiagramObj {
	public UseCaseDiag(Point p) {
		super();
		setLocation(p);

		setName("UseCase");

		this.setBackground(CommonUse.DIAGRAM_BACKGROUND_COLOR);
		this.setLayout(null);
		this.setOpaque(false);
		this.setBorder(null);
		this.setSize(180, 65);

		createPorts();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(CommonUse.DIAGRAM_BACKGROUND_COLOR);
		g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());

		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(CommonUse.USE_CASE_DIAG_STROKE);
		g2d.setColor(Color.black);
		g2d.drawOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);

		Rectangle tmpRect = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		drawCenteredString(g, getName(), false, tmpRect);
		g2d.setStroke(new BasicStroke());
	}
}
