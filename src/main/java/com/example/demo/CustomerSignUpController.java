package com.example.demo;

import Project.Person.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Scanner;

public class CustomerSignUpController {

    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private TextField age;

    @FXML
    private TextField address;

    @FXML
    private TextField deliveryAddress;

    @FXML
    private PasswordField password;

    @FXML
    private ChoiceBox gender;

    @FXML
    private Label Fname_label;

    @FXML
    private Label Lname_label;

    @FXML
    private Label Email_label;

    @FXML
    private Label Phone_label;

    @FXML
    private Label Age_label;

    @FXML
    private Label Address_label;

    @FXML
    private Label Delievery_label;

    @FXML
    private Label Password_label;

    @FXML
    private Label Gender_label;


    public void checkIfFnameValid() {
        checkIfNameValid(firstName,Fname_label);
    }

    public void checkIfLnameValid() {
        checkIfNameValid(lastName,Lname_label);
    }

    private void checkIfNameValid(TextField Name, Label label) {
        if (Name.getText().isEmpty()) {
            label.setTextFill(Color.DARKORANGE);
            label.setText("The name cannot be empty");
            label.setFont(Font.font("System",FontWeight.BOLD,20));
        }else{
            label.setTextFill(Color.GREEN);
            label.setText("Nice name you got there UwU");
            label.setFont(Font.font("System",FontWeight.BOLD,20));
            if(Name.getText().matches(".*\\d.*") && Name.getText().matches("^.*[@#$%^&-+=()].*")) {
                label.setTextFill(Color.RED);
                label.setText("The name cannot contain numbers or special characters");
                label.setFont(Font.font("System",FontWeight.BOLD,12));
            }else if(Name.getText().matches(".*\\d.*")) {
                label.setTextFill(Color.RED);
                label.setText("The name cannot contain numbers");
                label.setFont(Font.font("System",FontWeight.BOLD,20));
            }else if(Name.getText().matches("^.*[@#$%^&-+=()].*")) {
                label.setTextFill(Color.RED);
                label.setText("The name cannot contain special characters");
                label.setFont(Font.font("System",FontWeight.BOLD,15));
            }
        }
    }

    public void checkEmailEmpty(){
     if (email.getText().isEmpty()) {
         Email_label.setTextFill(Color.DARKORANGE);
         Email_label.setText("The email cannot be empty");
         Email_label.setFont(Font.font("System",FontWeight.BOLD,20));
     }else
         Email_label.setText("");
    }

    public void checkPhoneEmpty(){
        if (phone.getText().isEmpty()) {
            Phone_label.setTextFill(Color.DARKORANGE);
            Phone_label.setText("The phone cannot be empty");
            Phone_label.setFont(Font.font("System",FontWeight.BOLD,20));
        }else{
            if(phone.getText().matches("^[0-9]*$")){
                Phone_label.setTextFill(Color.GREEN);
                Phone_label.setText("Nice Number you got there UwU");
                Phone_label.setFont(Font.font("System",FontWeight.BOLD,20));
            }else{
                Phone_label.setTextFill(Color.RED);
                Phone_label.setText("The phone cannot contain non-numeric values");
                Phone_label.setFont(Font.font("System",FontWeight.BOLD,14));
            }
        }
    }

    public void checkAgeEmpty(){
        if (age.getText().isEmpty()) {
            Age_label.setTextFill(Color.DARKORANGE);
            Age_label.setText("The age cannot be empty");
            Age_label.setFont(Font.font("System",FontWeight.BOLD,20));
        }else{
            if(age.getText().matches("^[0-9]*$")){
                Age_label.setTextFill(Color.GREEN);
                Age_label.setText("Nice age you got there UwU");
                Age_label.setFont(Font.font("System",FontWeight.BOLD,20));
            }else{
                Age_label.setTextFill(Color.RED);
                Age_label.setText("The age cannot contain non-numeric values");
                Age_label.setFont(Font.font("System",FontWeight.BOLD,14));
            }
        }
    }

    public void validateInputAndSignUp(ActionEvent event) throws IOException {
        Customer customer = new Customer();


    }
    public void switchSceneToCustomerLoginOrSignUp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerLoginOrSignUp.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
