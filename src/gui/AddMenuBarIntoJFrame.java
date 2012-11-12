package gui;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.swing.JFrame;

import javax.swing.SwingConstants;

public class AddMenuBarIntoJFrame {
	public static JFrame frame;

	AddMenuBarIntoJFrame(JFrame frameForW2D) {
		frame = frameForW2D;
	}

	public void createMenu() {
		// Create a menu bar
		JMenuBar frameMenuBar = new JMenuBar();

		// Create first menu in menubar
		JMenu menu1 = new JMenu("Menu1");

		// Create second menu in menubar
		JMenu menu2 = new JMenu("Menu2");

		// Create third menu in menubar
		JMenu menu3 = new JMenu("Menu3");

		// Create menu that will be add into third menu
		JMenu menu3_internalMenu1 = new JMenu("Menu3_Menu1");

		// Create first menu item for first menu
		JMenuItem menuItem1 = new JMenuItem("Menu1_MenuItem1");

		// Create first menu item for second menu
		JMenuItem menuItem2 = new JMenuItem("Menu2_MenuItem1");

		// Create second menu item for second menu
		JMenuItem menuItem3 = new JMenuItem("Menu2_MenuItem2");

		// Create first menu item for third menu
		JMenuItem menuItem4 = new JMenuItem("Menu3_MenuItem1");

		// Create menu item that will be add into internal menu in third menu
		JMenuItem menuItem5 = new JMenuItem("Menu3_Menu1_MenuItem1");

		// add first menu item into first menu
		menu1.add(menuItem1);

		// add first menu item into second menu
		menu2.add(menuItem2);

		// add second menu item into second menu
		menu2.add(menuItem3);

		// add first menu item into third menu
		menu3.add(menuItem4);

		// add internal menu into third menu
		menu3.add(menu3_internalMenu1);

		// add menu item into internal menu
		menu3_internalMenu1.add(menuItem5);

		// add first menu into menu bar
		frameMenuBar.add(menu1);

		// add second menu into menu bar
		frameMenuBar.add(menu2);

		// add third menu into menu bar
		frameMenuBar.add(menu3);
		

		// Create a JFrame with title ( JFrame with menu bar )

		// Set menu bar for JFrame
		frame.setJMenuBar(frameMenuBar);

		// Set default close operation for JFrame
		// Make JFrame visible. So we can see it.
	}
}
