package Project.Resturants;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * this class made to run function get location in another thread to be more easy
 * */
public class RestaurantLocation extends Thread{
    String uri;
    @Override
    public void run(){
        try {
            getLocation();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * this method to get location of restaurant on Google map
     * */
    public void getLocation() throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI(uri));
        }
    }
}
