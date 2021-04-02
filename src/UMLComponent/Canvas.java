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

import Utilities.CommonUse;
import Utilities.MouseEventListener;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
	private static Canvas instance = null;
	protected MouseEventListener currentMode = null;

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

		if (!basicObjList.isEmpty()) {
			for (BasicObject obj : basicObjList) {
				obj.paint(g);
			}
		}
		if (!selectedArea.isEmpty()) {
			int alpha = 85; // 33% transparent
			g.setColor(new Color(37, 148, 216, alpha));
			g.fillRect(selectedArea.x, selectedArea.y, selectedArea.width, selectedArea.height);
		}
	}

}
