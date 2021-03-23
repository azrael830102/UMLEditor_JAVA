package Utilities;
import java.awt.EventQueue;
import java.net.URL;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import UMLEditorBody.Editor;

public class Demo {

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		URL config = ClassLoader.getSystemResource("log4j.properties");
		PropertyConfigurator.configure(config);

		Logger logger = Logger.getLogger(Demo.class);
		logger.debug("Start up UML Editor");
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Editor mainWindow = new Editor(true);
					mainWindow.setTitle("UML editor");
					mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					mainWindow.setLocationRelativeTo(null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
