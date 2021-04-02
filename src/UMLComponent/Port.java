package UMLComponent;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import UMLComponent.Line.BasicLineObj;

@SuppressWarnings("serial")
public class Port extends Rectangle{
	private static final int OFFSET = 5;
	private List<BasicLineObj> lines = new ArrayList<BasicLineObj>(); 

	public void setPort(int center_x, int center_y) {
		int x = center_x - OFFSET;
		int y = center_y - OFFSET;
		int w = OFFSET * 2;
		int h = OFFSET * 2;
		setBounds(x, y, w, h);
	}
	public void addLine(BasicLineObj line) {
		lines.add(line);
	}
	
	public void removeLine(BasicLineObj line) {
		lines.remove(line);
	}
	
	public void resetLines() {
		// TODO reconnect line to obj
	}
}
