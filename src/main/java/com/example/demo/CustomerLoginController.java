package com.example.demo;

import Project.Person.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Project.Person.Customer.userList;

public class CustomerLoginController implements Initializable {
    private Scene scene;
    private Stage stage;
    private Parent root;

    static Customer customer;

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
        for (int i = 0; i < userList.size(); i++) {
            if (email.equalsIgnoreCase(userList.get(i).getEmail())) {
                if (password.equals(userList.get(i).getPassword())) {
                    label.setText("Login successful, nigga");
                    label.setTextFill(Color.GREEN);
                    customer = userList.get(i);
                    UserInfo.customer = customer;
                    switchSceneToInterface(event);
                } else if (i == userList.size() - 1) {
                    label.setText("The email or password is incorrect");
                    label.setTextFill(Color.RED);
                }
            } else if (i == userList.size() - 1) {
                label.setText("The email could not be found");
                label.setTextFill(Color.RED);
            }
        }
    }

    public static Customer chosenAccount(){
        return customer;
    }

    public void switchSceneToCustomerLoginOrSignUp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerLoginOrSignUp.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void switchSceneToInterface(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerInterface.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
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
        Email.setText("eyad@gmail.com");
        Password.setText("Hello@test123");
    }
}