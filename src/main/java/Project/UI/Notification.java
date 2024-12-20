package Project.UI;

import java.awt.*;

public class Notification extends Thread{
    String name;
    String nameRestaurant;
    public Notification(String name, String nameRestaurant){
        this.name=name;
        this.nameRestaurant=nameRestaurant;
    }
    @Override
    public void run(){
        try {
            showNotification();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
    public void showNotification() throws AWTException {
        if (SystemTray.isSupported()) {//check if pc support system tray
            displayTray();
        } else {
            System.err.println("System tray not supported!");
        }
    }

    public  void displayTray() throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();//take object from system tray
        Image image = Toolkit.getDefaultToolkit().createImage("");//set default image to tray icon
        TrayIcon trayIcon = new TrayIcon(image, "");//take object from tray icon and send image
        trayIcon.setImageAutoSize(true);//make pc select size
        trayIcon.setToolTip("");
        tray.add(trayIcon);//add tray icon to system tray
        trayIcon.displayMessage("Hello "+name, "wow hurry a restaurant "+nameRestaurant+" make a nice discount", TrayIcon.MessageType.INFO);
        //to make body of not and display
    }

}
