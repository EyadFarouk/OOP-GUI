package com.example.demo;

import Project.Person.Customer;
import Project.Resturants.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerInterfaceController implements Initializable {
    private Scene scene;
    private Stage stage;
    private Parent root;

    List<Restaurant> restaurants;

    int number=0;

    @FXML
    private Label CustomerName;

    @FXML
    private Label Name;

    @FXML
    private Label Address;

    @FXML
    private Label Rating;

    @FXML
    private Label Contact;

    public void switchToLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerLoginOrSignUp.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerName.setText(UserInfo.customer.getFname()+" "+UserInfo.customer.getLname());
        Restaurant restaurant = new Restaurant();
        restaurants=restaurant.loadData();
        Name.setText("Restaurant name: "+restaurants.getFirst().name);
        Address.setText("Address: "+restaurants.getFirst().address);
        Rating.setText("Rating of the restaurant: "+restaurants.getFirst().rating);
        Contact.setText("Contact information: "+restaurants.getFirst().contactInformation);
    }

    public void Previous(){
        if(number==0)
            number=restaurants.size()-1;
        else
            number--;
        Name.setText("Restaurant name: "+restaurants.get(number).name);
        Address.setText("Address: "+restaurants.get(number).address);
        Rating.setText("Rating of the restaurant: "+restaurants.get(number).rating);
        Contact.setText("Contact information: "+restaurants.get(number).contactInformation);
    }

    public void Next(){
        if(number==restaurants.size()-1)
            number=0;
        else
            number++;
        Name.setText("Restaurant name: "+restaurants.get(number).name);
        Address.setText("Address: "+restaurants.get(number).address);
        Rating.setText("Rating of the restaurant: "+restaurants.get(number).rating);
        Contact.setText("Contact information: "+restaurants.get(number).contactInformation);
    }

    public void getLocation() throws URISyntaxException, IOException {
        restaurants.get(number).getLocation();
    }
}
