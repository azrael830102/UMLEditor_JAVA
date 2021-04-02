package UMLEditorBody;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import org.apache.log4j.Logger;

import UMLComponent.Canvas;
import UMLEditorBody.MenuBar.MenuBar;
import UMLEditorBody.ToolBox.ToolBox;
import Utilities.CommonUse;

@SuppressWarnings("serial")
public class Editor extends JFrame {

	private static Logger logger = Logger.getLogger(Editor.class);
	private JToolBar toolBar;
	private Canvas canvas ;
	public Editor(boolean isVisible) {
		getContentPane().setBackground(CommonUse.BACKGROUND_COLOR);
		initialize(isVisible);
	}

	private void initialize(boolean isVisible) {
		logger.info("Initializing...");
		setBounds(100, 100, 1200, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		toolBar = new ToolBox();
		canvas  = Canvas.getInstance();
		getContentPane().add(new MenuBar(), BorderLayout.NORTH);
		getContentPane().add(toolBar, BorderLayout.WEST);
		getContentPane().add(canvas , BorderLayout.CENTER);

		setVisible(isVisible);
	}

}
