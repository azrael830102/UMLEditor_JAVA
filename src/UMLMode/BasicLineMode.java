package UMLMode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import UMLComponent.BasicObject;
import UMLComponent.BasicObject.ComponentType;
import UMLComponent.Port;
import UMLComponent.Port.DiagPortCode;
import UMLComponent.Port.LinePortCode;
import UMLComponent.Line.BasicLineObj;
import Utilities.MouseEventListener;

public abstract class BasicLineMode extends MouseEventListener{
	protected Point startPoint = null;
	protected DiagPortCode diagObjPort_1st = null, diagObjPort_2nd = null;
	protected BasicObject diagObj_1st = null, diagObj_2nd = null;
	protected List<BasicObject> basicObjList;
	
	@Override
	public void mousePressed(MouseEvent e) {
		basicObjList = canvas.getBasicObjList();
		startPoint = findDiagramToConnect(e.getPoint(), LinePortCode.START_POINT);
	}
	
	/**
	 * Don't use it indiscriminately
	 * */
	protected void connectingLineAndDiagrams(BasicLineObj line) {
		Port diagObj_1st_Port = diagObj_1st.getPort(diagObjPort_1st.getPortCode());
		Port diagObj_2nd_Port = diagObj_2nd.getPort(diagObjPort_2nd.getPortCode());
		line.setPorts(diagObj_1st_Port, diagObj_2nd_Port);
		
		diagObj_1st_Port.addLine(line);
		diagObj_2nd_Port.addLine(line);
		
		canvas.addObject(line);
	}	
	/**
	 * Don't use it indiscriminately
	 * */
	protected void resetForMouseReleasedEvent() {
		canvas.setTempLine(null);
		canvas.repaint();
		startPoint = null;
	}
	
	protected Point findDiagramToConnect(Point p, LinePortCode linePort) {
		for (BasicObject basicObj : basicObjList) {
			if (basicObj.getComponentType().equals(ComponentType.DIAGRAM) && basicObj.checkSelected(p)) {
				if(linePort.equals(LinePortCode.START_POINT)) {
					diagObj_1st = basicObj;
					diagObjPort_1st = getTargetPortToConnect(p, basicObj);
					return diagObj_1st.getPort(diagObjPort_1st.getPortCode()).getCenterPoint();
				}else if(linePort.equals(LinePortCode.END_POINT)) {
					diagObj_2nd = basicObj;
					diagObjPort_2nd = getTargetPortToConnect(p, basicObj);
					return diagObj_2nd.getPort(diagObjPort_2nd.getPortCode()).getCenterPoint();
				}
			}
		}
		return null;
	}

	
}
