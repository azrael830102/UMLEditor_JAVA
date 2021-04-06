package UMLComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import UMLComponent.Line.BasicLineObj;
import Utilities.CommonUse;
import Utilities.MouseEventListener;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
	private static Canvas instance = null;
	protected MouseEventListener currentMode = null;
	
	public BasicLineObj tempLine = null;
	public Rectangle selectedArea = new Rectangle();
	private List<BasicObject> basicObjList = new ArrayList<BasicObject>();
	
	private Canvas() {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1920, 1080));
	}

	public static Canvas getInstance() {
		if (instance == null) {
			instance = new Canvas();
		}
		return instance;
	}

	public void reset() {
		selectedArea.setBounds(0, 0, 0, 0);
		for (BasicObject obj : basicObjList) {
			obj.setSlected(false);
		}
	}

	public MouseEventListener getCurrentTool() {
		return currentMode;
	}

	public void setCurrentTool(MouseEventListener mode) {
		removeMouseListener(currentMode);
		removeMouseMotionListener(currentMode);
		this.currentMode = mode;
		addMouseListener(currentMode);
		addMouseMotionListener(currentMode);
	}

	public void addObject(BasicObject obj) {
		this.basicObjList.add(obj);
	}

	public List<BasicObject> getBasicObjList() {
		return this.basicObjList;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		/* set canvas area */
		Dimension dim = getSize();
		g.setColor(CommonUse.BACKGROUND_COLOR);
		g.fillRect(0, 0, dim.width, dim.height);
		/* set painting color */
		g.setColor(Color.white);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		
		if(tempLine != null) {
			tempLine.paint(g);
		}
		if (!basicObjList.isEmpty()) {
			for (BasicObject obj : basicObjList) {
				obj.paint(g);
			}
		}
		if (!selectedArea.isEmpty()) {
			g.setColor(CommonUse.SELECTED_AREA_COLOR);
			Graphics2D g2d = (Graphics2D) g;
			float dash[] = { 5.0f };
			g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
			g2d.drawRect(selectedArea.x, selectedArea.y, selectedArea.width, selectedArea.height);
			g2d.setColor(Color.BLACK);
		}
	}

}
