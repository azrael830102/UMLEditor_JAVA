package UMLEditorBody.MenuBar;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import UMLComponent.BasicObject;
import UMLComponent.BasicObject.ComponentType;
import UMLComponent.Canvas;
import UMLComponent.Diagram.BasicDiagramObj;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	private static Logger logger = Logger.getLogger(MenuBar.class);
	private Canvas canvas;

	public MenuBar() {
		canvas = Canvas.getInstance();
		JMenu option = new JMenu("Option");
		this.add(option);

		JMenuItem menuItem;
		for (MenuFunctions menuFunc : MenuFunctions.values()) {
			menuItem = new JMenuItem(menuFunc.getMenuFuncName());
			menuItem.setAccelerator(KeyStroke.getKeyStroke(menuFunc.getMenuFuncShortcut(), 0));
			menuItem.addActionListener(new MenuItemActionListener(menuFunc));
			option.add(menuItem);
		}
	}

	class MenuItemActionListener implements ActionListener {
		MenuFunctions _action;// 0:Rename, 1:Group, 2:Ungroup

		MenuItemActionListener(MenuFunctions menuFunc) {
			_action = menuFunc;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (_action) {
			case RENAME:
				rename();
				break;
			case GROUP:
				logger.debug("Group");
				canvas.grouping();
				break;
			case UNGROUP:
				logger.debug("Ungroup");
				canvas.unGrouping();
				break;
			}

		}

		private void rename() {
			BasicDiagramObj selectedObj = null;
			int selectedItemCnt = 0;
			for (BasicObject obj : canvas.getBasicObjList()) {
				if (obj.isSlected() && obj.getComponentType().equals(ComponentType.DIAGRAM)) {
					selectedItemCnt++;
					selectedObj = (BasicDiagramObj)obj;
				}
			}
			switch (selectedItemCnt) {
			case 0:
				new RenameAlertMsgBox("Please select a diagram!");
				break;
			case 1:
				new RenameMsgBox(selectedObj);
				break;
			default:
				new RenameAlertMsgBox("Multiple selected!");
			}

		}
	}

	class RenameMsgBox extends JFrame {
		public RenameMsgBox(BasicDiagramObj selectedObj) {
			super("Change Object Name");
			setSize(400, 100);
			getContentPane().setLayout(new GridLayout(0, 1));

			JPanel panel = null;
			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

			JTextField Text = new JTextField(selectedObj.getName());
			panel.add(Text);
			getContentPane().add(panel);

			panel = new JPanel();
			panel.setLayout(new BorderLayout());

			JButton confirm = new JButton("OK");
			panel.add(confirm, BorderLayout.WEST);

			JButton cancel = new JButton("Cancel");
			panel.add(cancel, BorderLayout.EAST);

			getContentPane().add(panel);
			setLocationRelativeTo(null);
			setVisible(true);
			confirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedObj.rename(Text.getText());
					canvas.repaint();
					dispose();
				}
			});

			cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
	}

	class RenameAlertMsgBox extends JFrame {
		public RenameAlertMsgBox(String alertMsg) {
			super("Warning");
			setSize(400, 100);
			getContentPane().setLayout(new GridLayout(0, 1));

			JPanel panel = null;
			panel = new JPanel();
			panel.setLayout(new BorderLayout());

			JLabel Text = new JLabel(alertMsg, SwingConstants.CENTER);
			panel.add(Text);
			getContentPane().add(panel, BorderLayout.CENTER);

			panel = new JPanel();
			panel.setLayout(new BorderLayout());

			JButton btn_ok = new JButton("OK");
			panel.add(btn_ok, BorderLayout.CENTER);
			getContentPane().add(panel);

			setLocationRelativeTo(null);
			setVisible(true);

			btn_ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
	}

}
