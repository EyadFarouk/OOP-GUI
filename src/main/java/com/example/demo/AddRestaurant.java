package com.example.demo;

import Project.Resturants.Dish;
import Project.Resturants.Restaurant;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

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
    private TextField menuItems;
    @FXML
    private Button addButton;

    @FXML
    public void initialize() {
        // Set focus on the first input field when the controller is initialized
        restaurantName.requestFocus();
    }

    @FXML
    private void handleSubmit() {
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

        // Get menu items from the last input field
        String menuItemsText = menuItems.getText();
        String[] menuItems = menuItemsText.split(",");

        // Check if the number of menu items is less than 100
        if (menuItems.length > 100) {
            showAlert("Input Error", "The number of menu items must be less than 100.");
            return;
        }

        // Create a list to hold Dish objects
        List<Dish> menu = new ArrayList<>();
        for (String item : menuItems) {
            Dish dish = new Dish();
            dish.name = item.trim(); // Trim whitespace
            menu.add(dish);
        }
        restaurant.menu = menu;

        // Add the restaurant to the list
        restaurants.add(restaurant);
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

        // Validate menu items
        String menuItemsText = menuItems.getText();
        String[] menuItems = menuItemsText.split(",");
        if (menuItems.length > 100) {
            showAlert("Input Error", "The number of menu items must be less than 100.");
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}