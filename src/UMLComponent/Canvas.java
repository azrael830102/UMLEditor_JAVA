package UMLComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.JPanel;

import UMLMode.Mode;
import Utilities.CommonUse;



@SuppressWarnings("serial")
public class Canvas extends JPanel {
	private static Canvas instance = null;
	protected Mode currentMode = null;
	
	public Rectangle selectedArea = new Rectangle();
	private List<BasicObject> basicObjList = new ArrayList<BasicObject>();

	private Canvas() {
		// Exists only to defeat instantiation.
	}

	public static Canvas getInstance() {
		if (instance == null) {
			instance = new Canvas();
		}
		return instance;
	}
	
	public void reset() {
		selectedArea.setBounds(0, 0, 0, 0);
	}
	public Mode getCurrentTool() {
		return currentMode;
	}

	public void setCurrentTool(Mode mode) {
		removeMouseListener((MouseListener) currentMode);
		removeMouseMotionListener((MouseMotionListener) currentMode);
		this.currentMode = mode;
		addMouseListener((MouseListener) currentMode);
		addMouseMotionListener((MouseMotionListener) currentMode);		
	}
	
	public void addObject(BasicObject obj) {
		this.basicObjList.add(obj);
	}
	public List<BasicObject> getBasicObjList(){
		return this.basicObjList;
	}
	
	public void paint(Graphics g) {
		/* set canvas area */
		Dimension dim = getSize();
		g.setColor(CommonUse.backgroundColor);
		g.fillRect(0, 0, dim.width, dim.height);
		/* set painting color */
		g.setColor(Color.white);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		
		if (!selectedArea.isEmpty()) {
			int alpha = 85; // 33% transparent
			g.setColor(new Color(37, 148, 216, alpha));
			g.fillRect(selectedArea.x, selectedArea.y, selectedArea.width, selectedArea.height);
		}
	}
	
}
