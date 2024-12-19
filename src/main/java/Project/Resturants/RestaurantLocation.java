package Project.Resturants;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
/**
 * class to find location of restaurant on Google map
 * */
public class RestaurantLocation {
    /**
     * method to get location in google map has @param url
     * */
    public void getLocation(String url) throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI(url));
        }
    }
}