package com.example.demo;

import Project.Resturants.Dish;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.demo.Info.restaurants;


public class AddMenu {
    private Scene scene;
    private Stage stage;
    private Parent root;

    private int numberOfItems;
    private int currentDishIndex = 0;
    private int restaurantIdx = -1;

    @FXML
    private TextField restaurantNameField;
    @FXML
    private TextField numberOfItemsField;
    @FXML
    private Text currentDishIndexLabel;

    @FXML
    private VBox addMenuVBox;
    @FXML
    private VBox addDishVBox;

    @FXML
    private TextField dishNameField;
    @FXML
    private TextField dishDescriptionField;
    @FXML
    private TextField dishPriceField;
    @FXML
    private TextField dishTypeField;
    @FXML
    private TextField dishRatingField;

    @FXML
    private Button nextButton;
    @FXML
    private Button addDishButton;


    @FXML
    private void handleAddMenuButtonAction(ActionEvent event) throws IOException {
        String restaurantName = restaurantNameField.getText();
        String numberOfItemsText = numberOfItemsField.getText();

        // Validate number of items
        if (!validateInput(numberOfItemsField, 1, 100, "Number of items must be between 1 and 100.")) {
            return;
        }

        // Parse the number of items after validation
        numberOfItems = Integer.parseInt(numberOfItemsText);

        // Find the restaurant object
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).name.equalsIgnoreCase(restaurantName)) {
                restaurantIdx = i;
                break;
            }
        }

        if (restaurantIdx == -1) {
            showAlert("Error", "Restaurant not found.");
            return;
        }

        // Add dishes to the restaurant's menu
        for (int i = 0; i < numberOfItems; i++) {
            addDishVBox.setVisible(true);
            handleAddDishButtonAction(event);
        }

        showAlert("Success", "Menu created successfully for " + restaurantName + " with " + numberOfItems + " items.");
    }


    @FXML
    private void handleAddDishButtonAction(ActionEvent event) throws IOException {
        // Validate dish inputs
        if (dishNameField.getText().isEmpty() || dishDescriptionField.getText().isEmpty() ||
                dishTypeField.getText().isEmpty() || !validateInput(dishPriceField, 1.0, 100.0, "Price must be between 1.0 and 100.0.") ||
                !validateInput(dishRatingField, 1.0, 5.0, "Rating must be between 1.0 and 5.0.")) {
            return;
        }

        // Create a new Dish object and populate it
        Dish dish = new Dish();
        dish.name = dishNameField.getText();
        dish.description = dishDescriptionField.getText();
        dish.price = Double.parseDouble(dishPriceField.getText());
        dish.categories = dishTypeField.getText();
        dish.rating = Double.parseDouble(dishRatingField.getText());

        // Add the dish to the restaurant's menu
        restaurants.get(restaurantIdx).menu.add(dish);

        // Reset dish input fields
        resetDishInputs();
        currentDishIndex++;

        currentDishIndexLabel.setText("#" + (currentDishIndex + 1));

        // Check if all dishes have been added
        if (currentDishIndex >= numberOfItems) {
            HomePageAdminScene(event);
        }
    }

    public void HomePageAdminScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("homePageAdmin.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void resetDishInputs() {
        // Clear all dish input fields
        dishNameField.clear();
        dishDescriptionField.clear();
        dishPriceField.clear();
        dishTypeField.clear();
        dishRatingField.clear();
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
