package com.example.demo;

import Project.Person.Admin;
import Project.UI.Notification;
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
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static Project.Person.Admin.adminList;
import static com.example.demo.Info.restaurants;

public class AdminLoginController {
    private Scene scene;
    private Stage stage;
    private Parent root;

    static Admin admin;

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
        for (int i = 0; i < adminList.size(); i++) {
            if (email.equalsIgnoreCase(adminList.get(i).getEmail())) {
                if (password.equals(adminList.get(i).getPassword())) {
                    label.setText("Login successful, nigga");
                    label.setTextFill(Color.GREEN);
                    admin = adminList.get(i);
                    Info.admin = admin;
                    Random random=new Random();
                    int h=random.nextInt(restaurants.size());
                    Notification notification=new Notification(admin.getFname(),restaurants.get(h).name);
                    notification.start();
                    switchSceneToInterface(event);
                } else if (i == adminList.size() - 1) {
                    label.setText("The email or password is incorrect");
                    label.setTextFill(Color.RED);
                }
            } else if (i == adminList.size() - 1) {
                label.setText("The email could not be found");
                label.setTextFill(Color.RED);
            }
        }
    }

    public void switchSceneToAdminLoginOrSignUp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminLoginOrSignUp.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void switchSceneToInterface(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminInterface.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        Email.setText("EyAdAdMiN@gMaiL.com");
        Password.setText("Hello@test123");
    }
}
