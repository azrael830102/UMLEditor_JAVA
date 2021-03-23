package UMLEditorBody;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import Utilities.CommonUse;
import Utilities.Tools;
/**
 * Tool box which contain {@link Tools}
 * 
 * @author BobLin
 * */
@SuppressWarnings("serial")
public class ToolBox extends JToolBar {
	int toolsCnt = Tools.values().length;
	private JButton selectedBtn = null;
	public ToolBox() {
		setLayout(new GridLayout(toolsCnt, 1, 2, 2));
		setRollover(true);
		
		for(int i=0;i<toolsCnt;i++) {
			add(new ToolBtn(Tools.values()[i]));
		}
	}

	private class ToolBtn extends JButton {
		Tools selectedTool;
		ToolBtn(Tools tool){
			selectedTool = tool;
			setToolTipText(tool.getToolsName());
			setIcon(tool.getImage());
			setFocusable(false);
			setBackground(CommonUse.backgroundColor);
			setBorderPainted(false);
			setRolloverEnabled(true);
			addActionListener(new ToolBtnListener());
		}
		class ToolBtnListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedBtn != null)
					selectedBtn.setBackground(CommonUse.backgroundColor);
				selectedBtn = (JButton) e.getSource();
				selectedBtn.setBackground(CommonUse.focusColor);
			}
		}
	}
}
