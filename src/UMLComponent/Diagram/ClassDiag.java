package UMLComponent.Diagram;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import Utilities.CommonUse;

@SuppressWarnings("serial")
public class ClassDiag extends BasicDiagramObj {

	public ClassDiag(Point p) {
		super();
		setLocation(p);

		setName("New Class");

		this.setBackground(CommonUse.DIAGRAM_BACKGROUND_COLOR);
		this.setLayout(null);
		this.setOpaque(false);
		this.setBorder(null);
		this.setSize(160, 180);

		createPorts();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(CommonUse.DIAGRAM_BACKGROUND_COLOR);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke());
		g2d.setColor(Color.BLACK);
		g2d.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

		Rectangle tmpRect = new Rectangle(this.getX(), this.getY(), this.getWidth(), (int) (this.getHeight() * 0.2));
		drawCenteredString(g, getName(), true, tmpRect);
	}

}
