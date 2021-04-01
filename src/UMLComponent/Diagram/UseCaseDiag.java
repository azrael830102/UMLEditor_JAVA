package UMLComponent.Diagram;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


@SuppressWarnings("serial")
public class UseCaseDiag extends BasicDiagramObj {
	
	private DiagramNameTag useCaseDiagName;
	
	
	public UseCaseDiag(){
		super();
		useCaseDiagName = new DiagramNameTag("UseCase");

		this.setName(useCaseDiagName.getText());
		this.setBackground(new Color(255, 255, 185));
		this.setLayout(null);
		this.add(useCaseDiagName);
		this.setOpaque(false);
		this.setBorder(null);
		this.setSize(280, 100);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(255, 255, 185));
		g.fillOval(0, 0, this.getWidth(), this.getHeight());

		Graphics2D g2d = (Graphics2D) g;
		float dash[] = { 10.0f };
		g2d.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
		g2d.setColor(Color.ORANGE);
		g2d.drawOval(0, 0, this.getWidth(), this.getHeight());
		useCaseDiagName.setBounds(40, this.getHeight() / 2 - 50 / 2,
				this.getWidth() - 40 * 2, 50);
		super.paint(g);
	}

	@Override
	public void draw(Graphics g) {
		paint(g);
	}

}
