package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;



public class HomePageAdmin {
    @FXML
    private Button addRestaurant;
    @FXML
    private Button delRestaurant;
    @FXML
    private Button addMenu;
    @FXML
    private Button delMenu;
    @FXML
    private Button displayRestaurant;
    @FXML
    private Button addDeliveryStaff;
    @FXML
    private Button setReport;
    @FXML
    private Button logout;




    // Load CSS
    public void start(Stage stage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("./../../resources/com.example.demo/homePageAdmin.fxml"));
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("./../../resources/CSS/homePageAdmin.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
