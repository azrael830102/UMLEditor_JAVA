package UMLEditorBody;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	private static Logger logger = Logger.getLogger(MenuBar.class);

	public MenuBar() {
		JMenu option = new JMenu("Option");
		this.add(option);

		JMenuItem menuItem = new JMenuItem("Rename");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		menuItem.addActionListener(new MenuItemActionListener(0));
		option.add(menuItem);

		menuItem = new JMenuItem("Group");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		menuItem.addActionListener(new MenuItemActionListener(1));
		option.add(menuItem);

		menuItem = new JMenuItem("Ungroup");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		menuItem.addActionListener(new MenuItemActionListener(2));
		option.add(menuItem);
	}

	class MenuItemActionListener implements ActionListener {
		int _action;// 0:Rename, 1:Group, 2:Ungroup

		MenuItemActionListener(int action) {
			_action = action;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (_action) {
			case 0:
				logger.debug("Rename");
				break;
			case 1:
				logger.debug("Group");
				break;
			case 2:
				logger.debug("Ungroup");
				break;
			}

		}
	}

}
