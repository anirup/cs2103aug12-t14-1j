package gui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;


public class SystemTrayIntegration {

	private static boolean isOpen;
	private static TrayIcon trayIcon;
	private static SystemTray tray;
	private static JFrame W2DFrame;
	
	SystemTrayIntegration(JFrame frameForW2D){
		W2DFrame = frameForW2D;
	}
	
	public void createSystemTray(){
		
		isOpen = true;
		
		JIntellitype.getInstance();
		if (JIntellitype.checkInstanceAlreadyRunning("bleh")) {
			   System.exit(0);
			}
		
		HotkeyListener hotKey = new HotkeyListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onHotKey(int Identifier) {
				if(Identifier == 1){
					if(isOpen==true){
						W2DFrame.hide();
						trayIcon.displayMessage("Minimized", "W2D is running in the background", TrayIcon.MessageType.INFO);
						isOpen = false;
					}
					else{
						W2DFrame.show();
						isOpen = true;
					}
				}
				
			}
		};
		JIntellitype.getInstance().registerHotKey(1, JIntellitype.MOD_CONTROL, (int)'W');
		JIntellitype.getInstance().addHotKeyListener(hotKey);
		
		Image image = null;
		try {
			image = ImageIO.read(getClass().getResource("/logo.jpg"));
		} catch (IOException ex) {
		}
		
		
		ActionListener exitListener=new ActionListener() {
            @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
            	JOptionPane pane = new JOptionPane("Are you sure you want to exit W2D?\n");
            	Object[] options = new String[] { "Yes", "No" };
            	pane.setOptions(options);
            	JDialog dialog = pane.createDialog(new JFrame(), "Exiting...");
            	dialog.setAlwaysOnTop(true);
            	dialog.show();
            	 Object obj = pane.getValue();
            	 if(options[0].equals(obj)){
            		 JIntellitype.getInstance().cleanUp();
            		 tray.remove(trayIcon);
            		 System.exit(0);
            	 }
            	 else
            		 dialog.hide();
            }
        };
        ActionListener hideListener=new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                W2DFrame.setVisible(false);
            }
        };
		PopupMenu popup=new PopupMenu();
        MenuItem defaultItem=new MenuItem("Exit");
        defaultItem.addActionListener(exitListener);
        popup.add(defaultItem);
        defaultItem=new MenuItem("Open");
        defaultItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                W2DFrame.setVisible(true);
                W2DFrame.setExtendedState(JFrame.NORMAL);
            }
        });
        popup.add(defaultItem);
        defaultItem=new MenuItem("Hide");
        defaultItem.addActionListener(hideListener);
        popup.add(defaultItem);
		  trayIcon = new TrayIcon(image, "W2D",popup);

		  
		    if (SystemTray.isSupported()) {
		      tray = SystemTray.getSystemTray();

		      trayIcon.setImageAutoSize(true);
		      trayIcon.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		          //System.out.println("In here");
		          trayIcon.displayMessage("W2D", "W2D is running!", TrayIcon.MessageType.INFO);
		        }
		      });

		      try {
		        tray.add(trayIcon);
		      } catch (AWTException e) {
		        System.err.println("We were unable to add a TrayIcon.");
		      }
		    }
	}

}
