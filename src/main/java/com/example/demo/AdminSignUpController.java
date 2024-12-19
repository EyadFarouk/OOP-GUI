package com.example.demo;

import Project.Person.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.ResourceBundle;

import static Project.Person.Admin.adminList;

public class AdminSignUpController implements Initializable {

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
    private PasswordField password;

    @FXML
    private ChoiceBox<String> gender;

    private final String[] genders = {"Male","Female","Engineer"};

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
    private Label Password_label;

    @FXML
    private Label Small_label;

    @FXML
    private Label Capital_label;

    @FXML
    private Label Special_label;

    @FXML
    private Label Number_label;

    @FXML
    private Label Eight_label;

    @FXML
    private Label Space_label;

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
            Email_label.setFont(Font.font("System",FontWeight.BOLD,23));
        }else{
            if(email.getText().endsWith("@gmail.com")||email.getText().endsWith("@yahoo.com")||email.getText().endsWith("@outlook.com")||email.getText().endsWith("@email.com")){
                Email_label.setText("");
            }
            else {
                Email_label.setTextFill(Color.DARKORANGE);
                Email_label.setText("The email in invalid");
                Email_label.setFont(Font.font("System",FontWeight.BOLD,23));
            }
        }
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
                if(Integer.parseInt(age.getText())<9){
                    Age_label.setTextFill(Color.PINK);
                    Age_label.setText("OwO, wyd here baby?");
                    Age_label.setFont(Font.font("System",FontWeight.BOLD,20));
                }
                else if(Integer.parseInt(age.getText())>100){
                    Age_label.setTextFill(Color.BLUEVIOLET);
                    Age_label.setText("Q-Q, No granny's allowed");
                    Age_label.setFont(Font.font("System",FontWeight.BOLD,20));
                }else {
                    Age_label.setTextFill(Color.GREEN);
                    Age_label.setText("Nice age you got there UwU");
                    Age_label.setFont(Font.font("System",FontWeight.BOLD,20));
                }
            }else{
                Age_label.setTextFill(Color.RED);
                Age_label.setText("The age cannot contain non-numeric values");
                Age_label.setFont(Font.font("System",FontWeight.BOLD,14));
            }
        }
    }

    public void checkPassword(){
        if (password.getText().isEmpty()) {
            Password_label.setTextFill(Color.DARKORANGE);
            Password_label.setText("The password cannot be empty");
            Password_label.setFont(Font.font("System",FontWeight.BOLD,21));
            Eight_label.setTextFill(Color.RED);
            Number_label.setTextFill(Color.RED);
            Small_label.setTextFill(Color.RED);
            Capital_label.setTextFill(Color.RED);
            Special_label.setTextFill(Color.RED);
            Space_label.setTextFill(Color.RED);
        }else{
            Password_label.setTextFill(Color.RED);
            Password_label.setText("* The password should contain:");
            Password_label.setFont(Font.font("System",FontWeight.BOLD,21));
            if (password.getText().matches("^(?=.*[0-9])"       //Checks if the password has a number
                    + "(?=.*[a-z])"                         //Checks if the password has a small letter
                    + "(?=.*[A-Z])"                         //Checks if the password has a capital letter
                    + "(?=.*[!@#$%^&-+=()*])"                    //Checks if the password has a special character
                    + "(?=\\S+$).{8,20}$"))                //Checks if the password has at least 8 characters and has no white spaces
            {
                Password_label.setTextFill(Color.GREEN);
                Password_label.setText("Your password is sexy. UwU");
                Password_label.setFont(Font.font("System",FontWeight.BOLD,20));
            }else{
                if (password.getText().length() < 8)
                    Eight_label.setTextFill(Color.RED);
                else
                    Eight_label.setTextFill(Color.GREEN);
                if (!password.getText().matches(".*\\d.*")) {
                    Number_label.setTextFill(Color.RED);
                }else
                    Number_label.setTextFill(Color.GREEN);
                if (!password.getText().matches(".*[a-z].*")) {
                    Small_label.setTextFill(Color.RED);
                }else
                    Small_label.setTextFill(Color.GREEN);
                if (!password.getText().matches(".*[A-Z].*")) {
                    Capital_label.setTextFill(Color.RED);
                }else
                    Capital_label.setTextFill(Color.GREEN);
                if (!password.getText().matches("^.*[!@#$%^&-+=()*].*")) {
                    Special_label.setTextFill(Color.RED);
                }else
                    Special_label.setTextFill(Color.GREEN);
                if (password.getText().contains(" ")) {
                    Space_label.setTextFill(Color.RED);
                }else
                    Space_label.setTextFill(Color.GREEN);
            }
        }
    }
    private boolean checkEmailUnique(){
        boolean exists=false;
        for (Admin admin : adminList) {
            if (email.getText().equals(admin.getEmail())) {
                return false;
            }
        }
        return true;
    }

    public void validateInputAndSignUp(ActionEvent event) throws IOException {
        if(!checkEmailUnique()){
            Email_label.setTextFill(Color.RED);
            Email_label.setText("The email should be unique");
            Email_label.setFont(Font.font("System",FontWeight.BOLD,20));
            return;
        }
        if(Email_label.getTextFill()==Color.GREEN
                ||Password_label.getTextFill()==Color.GREEN
                ||Fname_label.getTextFill()==Color.GREEN
                ||Lname_label.getTextFill()==Color.GREEN
                ||Age_label.getTextFill()==Color.GREEN
                ||!(address.getText().isEmpty())
                ||Phone_label.getTextFill()==Color.GREEN){
            Admin admin = new Admin();
            admin.setFname(firstName.getText());
            admin.setLname(lastName.getText());
            admin.setEmail(email.getText());
            admin.setPhone(phone.getText());
            admin.setAge(Integer.parseInt(age.getText()));
            admin.setAddress(address.getText());
            admin.setPassword(password.getText());
            admin.setGender(gender.getValue());
            adminList.add(admin);
            adminList.getLast().displayUserInfo();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginAsAdmin.fxml"));
            root = fxmlLoader.load();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void switchSceneToadminLoginOrSignUp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminLoginOrSignUp.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.getItems().addAll(genders);
        gender.setValue("Choose your gender");
    }
}
