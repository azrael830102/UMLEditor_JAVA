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
		float dash[] = { 10.0f };
		g2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
		g2d.setColor(Color.black);
		g2d.drawOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
		g.setColor(Color.BLACK);
		
		
		Rectangle tmpRect = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		drawCenteredString(g, getName(), false, tmpRect);
	}
}
