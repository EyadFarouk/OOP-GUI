package com.example.demo;

import Project.Person.Delivery_Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import static Project.Person.Delivery_Staff.staffList;

public class deliveryStaffLoginController {
    private Scene scene;
    private Stage stage;
    private Parent root;

    static Delivery_Staff deliveryStaff;

    @FXML
    private TextField Email;

    @FXML
    private TextField Password;

    @FXML
    private Label label;

    @FXML
    protected void checkIfValid(ActionEvent event) throws IOException {
        String email= Email.getText();
        String password= Password.getText();
        for (int i = 0; i < staffList.size(); i++) {
            if (email.equalsIgnoreCase(staffList.get(i).getEmail())) {
                if (password.equals(staffList.get(i).getPassword())) {
                    label.setText("Login successful, nigga");
                    label.setTextFill(Color.GREEN);
                    deliveryStaff = staffList.get(i);
                    Info.delivery_Staff = deliveryStaff;
                    switchSceneToInterface(event);
                } else if (i == staffList.size() - 1) {
                    label.setText("The email or password is incorrect");
                    label.setTextFill(Color.RED);
                }
            } else if (i == staffList.size() - 1) {
                label.setText("The email could not be found");
                label.setTextFill(Color.RED);
            }
        }
    }

    public void switchSceneToWelcome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Welcome.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void switchSceneToInterface(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("deliveryInterface.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        Email.setText("email1@email.com");
        Password.setText("password1");
    }
}
