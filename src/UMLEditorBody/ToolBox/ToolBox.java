package UMLEditorBody.ToolBox;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import UMLComponent.Canvas;
import Utilities.CommonUse;
import Utilities.MouseEventListener;

/**
 * Tool box which contain {@link Tools}
 * 
 * @author BobLin
 */
@SuppressWarnings("serial")
public class ToolBox extends JToolBar {
	private Canvas canvas;
	int toolsCnt = Tools.values().length;
	private JButton selectedBtn = null;

	public ToolBox() {
		canvas = Canvas.getInstance();
		setLayout(new GridLayout(toolsCnt, 1, 2, 2));
		setRollover(true);

		for (int i = 0; i < toolsCnt; i++) {
			add(new ToolBtn(Tools.values()[i]));
		}
	}

	private class ToolBtn extends JButton {
		MouseEventListener currentMode;

		ToolBtn(Tools tool) {
			this.currentMode = tool.getMode();
			setToolTipText(tool.getToolsName());
			setIcon(tool.getImage());
			setFocusable(false);
			setBackground(CommonUse.BACKGROUND_COLOR);
			setBorderPainted(false);
			setRolloverEnabled(true);
			addActionListener(new ToolBtnListener());
		}

		class ToolBtnListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedBtn != null) // set origin button not focus
					selectedBtn.setBackground(CommonUse.BACKGROUND_COLOR);
				selectedBtn = (JButton) e.getSource();
				selectedBtn.setBackground(CommonUse.FOCUS_COLOR);
				canvas.setCurrentTool(currentMode);
				canvas.reset();
				canvas.repaint();
			}
		}
	}
}
