package com.example.demo;

import Project.Resturants.Dish;
import Project.Resturants.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.Info.restaurants;


public class AddRestaurant {

    @FXML
    private TextField restaurantName;
    @FXML
    private TextField restaurantAddress;
    @FXML
    private TextField contactInfo;
    @FXML
    private TextField restaurantRating;
    @FXML
    private TextField URLLocation;
    @FXML
    private Button addButton;
    @FXML
    private Button addMenuButton;

    @FXML
    public void initialize() {
        // Set focus on the first input field when the controller is initialized
        restaurantName.requestFocus();
    }

    @FXML
    private void handleSubmit(ActionEvent event) throws IOException {
        validateAndAdd();
        switchSceneToadmin(event);
    }

    private boolean validateAllInputs() {
        // Validate name
        if (restaurantName.getText().trim().isEmpty()) {
            showAlert("Input Error", "Restaurant name cannot be empty.");
            return false;
        }

        // Validate address
        if (restaurantAddress.getText().trim().isEmpty()) {
            showAlert("Input Error", "Address cannot be empty.");
            return false;
        }

        // Validate contact information
        if (contactInfo.getText().trim().isEmpty()) {
            showAlert("Input Error", "Contact information cannot be empty.");
            return false;
        }

        // Validate rating
        if (!validateInput(restaurantRating, 0.0, 5.0, "Rating must be between 0 and 5.")) {
            return false;
        }

        // Validate URI
        if (URLLocation.getText().trim().isEmpty()) {
            showAlert("Input Error", "Location URL cannot be empty.");
            return false;
        }

        return true; // All inputs are valid
    }

    private boolean validateInput(TextField textField, double min, double max, String errorMessage) {
        String text = textField.getText();
        try {
            double value = Double.parseDouble(text);
            if (value < min || value > max) {
                showAlert("Input Error", errorMessage);
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert("Input Error", textField.getPromptText() + " must be a valid number.");
            return false;
        }
        return true;
    }
    public void switchSceneToadmin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homePageAdmin.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void AddMenuScene(ActionEvent event) throws IOException {
        validateAndAdd();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addMenu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void validateAndAdd(){
        // Validate all input fields
        if (!validateAllInputs()) {
            return; // If any input is invalid, stop processing
        }

        // Create a new Restaurant object
        Restaurant restaurant = new Restaurant();

        // Get values from input fields
        restaurant.name = restaurantName.getText();
        restaurant.address = restaurantAddress.getText();
        restaurant.contactInformation = contactInfo.getText();
        restaurant.rating = Double.parseDouble(restaurantRating.getText());
        restaurant.uri = URLLocation.getText();

        // Add the restaurant to the list
        restaurants.add(restaurant);
    }

    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}