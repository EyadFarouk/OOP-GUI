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

public class DeleteMenuItemController {
    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private Label Restaurantlabel;

    @FXML
    private Label Menulabel;

    @FXML
    private TextField RestaurantText;

    @FXML
    private TextField MenuText;

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
            int menuIndex=checkMenu(Restaurantindex);
            if(menuIndex!=-1){
                restaurants.get(Restaurantindex).menu.remove(menuIndex);
                switchSceneHomePage(event);
            }
        }
    }
    public int checkRestaurant(){
        for(int i=0;i<restaurants.size();i++) {
            if (restaurants.get(i).name.equalsIgnoreCase(RestaurantText.getText())) {
                Restaurantlabel.setOpacity(0);
                Menulabel.setOpacity(0);
                return i;
            }
        }
        Restaurantlabel.setOpacity(1);
        Menulabel.setOpacity(0);
        return -1;
    }
    public int checkMenu(int num){
        for(int i=0;i<restaurants.get(num).menu.size();i++) {
            if (restaurants.get(num).menu.get(i).name.equalsIgnoreCase(MenuText.getText())) {
                Restaurantlabel.setOpacity(0);
                Menulabel.setOpacity(0);
                return i;
            }
        }
        Restaurantlabel.setOpacity(0);
        Menulabel.setOpacity(1);
        return -1;
    }
}
