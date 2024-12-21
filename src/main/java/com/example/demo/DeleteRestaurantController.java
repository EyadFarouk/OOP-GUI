package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.demo.Info.restaurants;

public class DeleteRestaurantController {
    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private Label Restaurantlabel;

    @FXML
    private TextField RestaurantText;

    public void switchSceneHomePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("homePageAdmin.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void delete(ActionEvent event) throws IOException {
        int Restaurantindex =checkRestaurant();
        if(Restaurantindex !=-1){
            restaurants.remove(Restaurantindex);
            switchSceneHomePage(event);
        }
    }

    public int checkRestaurant(){
        for(int i=0;i<restaurants.size();i++) {
            if (restaurants.get(i).name.equalsIgnoreCase(RestaurantText.getText())) {
                Restaurantlabel.setOpacity(0);
                return i;
            }
        }
        Restaurantlabel.setOpacity(1);
        return -1;
    }
}
