package UMLComponent;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import UMLComponent.Diagram.BasicDiagramObj;
import UMLComponent.Line.BasicLineObj;

@SuppressWarnings("serial")
public class Port extends Rectangle {
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
		for(BasicLineObj line : lines) {
			line.resetLocation();
		}
	}

	public Point getCenterPoint() {
		return new Point((int)getCenterX(), (int)getCenterY());
	}

	/**
	 * Ports setting sequence : north -> east -> south -> west {@link DiagPortCode}
	 */
	public static Port[] createPorts(BasicDiagramObj basicDiagramObj) {
		Port[] ports = new Port[DiagPortCode.values().length];
		int[] xpoint = { basicDiagramObj.getX() + basicDiagramObj.getWidth() / 2, // North port
				basicDiagramObj.getX() + basicDiagramObj.getWidth(), // East port
				basicDiagramObj.getX() + basicDiagramObj.getWidth() / 2, // South port
				basicDiagramObj.getX() // West port
		};
		int[] ypoint = { basicDiagramObj.getY(), // North port
				basicDiagramObj.getY() + basicDiagramObj.getHeight() / 2, // East port
				basicDiagramObj.getY() + basicDiagramObj.getHeight(), // South port
				basicDiagramObj.getY() + basicDiagramObj.getHeight() / 2 // West port
		};

		for (DiagPortCode pcode : DiagPortCode.values()) {
			Port port = new Port();
			port.setPort(xpoint[pcode.getPortCode()], ypoint[pcode.getPortCode()]);
			ports[pcode.getPortCode()] = port;
		}
		return ports;
	}

	/**
	 * <pre>
	 *0 : NORTH_PORT
	 *1 : EAST_PORT
	 *2 : SOUTH_PORT 
	 *3 : WEST_PORT
	 * </pre>
	 */
	public enum DiagPortCode {
		NORTH_PORT(0), EAST_PORT(1), SOUTH_PORT(2), WEST_PORT(3);
		int port_code;

		DiagPortCode(int code) {
			port_code = code;
		}

		public int getPortCode() {
			return port_code;
		}

		public static DiagPortCode valueOf(int portCode) {
			return DiagPortCode.values()[portCode];
		}

	}

	/**
	 * <pre>
	 *0 : Start point port
	 *1 : End point port
	 * </pre>
	 */
	public enum LinePortCode {
		START_POINT(0), END_POINT(1);
		int port_code;

		LinePortCode(int code) {
			port_code = code;
		}

		public int getPortCode() {
			return port_code;
		}
	}

}
