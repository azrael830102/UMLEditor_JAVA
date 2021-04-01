package UMLComponent;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import UMLComponent.Line.BasicLineObj;

@SuppressWarnings("serial")
public class Port extends Rectangle{
	private List<BasicLineObj> lines = new ArrayList<BasicLineObj>(); 

	public void setPort(int center_x, int center_y, int offset) {
		int x = center_x - offset;
		int y = center_y - offset;
		int w = offset * 2;
		int h = offset * 2;
		setBounds(x, y, w, h);
	}
	public void addLine(BasicLineObj line) {
		lines.add(line);
	}
	
	public void removeLine(BasicLineObj line) {
		lines.remove(line);
	}
	
	public void resetLines() {
		
	}
}
