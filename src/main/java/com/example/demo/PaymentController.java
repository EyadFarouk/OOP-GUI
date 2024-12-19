package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class PaymentController {
    @FXML
    private Button Cash;
    @FXML
    private Button card;
    @FXML
    private ImageView pic;
    @FXML
    private TextField title;
    @FXML
    private Button goBack;
    @FXML
    private ImageView pic2;
    @FXML
    private Button goBack2;
    @FXML
    private Button goBack3;
    @FXML
    private ImageView pic3;
    @FXML
    private TextField cardnum;
    @FXML
    private TextField cvv;
    @FXML
    private TextField expirationDate;
    @FXML
    private Label cardcheck;
    @FXML
    private Label cvvcheck;
    @FXML
    private Label expcheck;
    @FXML
    private Button goback4;

    private String Cvv, CardNum, ExpDate;

    public void submit(ActionEvent event) {
        try {
            CardNum = cardnum.getText();
            Cvv = cvv.getText();
            ExpDate = expirationDate.getText();

            if (CardNum != null && CardNum.length() >= 12 && CardNum.length() <= 16 && CardNum.matches("[0-9]+")) {
                cardcheck.setText("Valid card number");
                cardcheck.setTextFill(Color.GREEN);
            } else {
                cardcheck.setText("Invalid card number. Must be 12-16 digits.");
                cardcheck.setTextFill(Color.RED);
            }

            if (Cvv != null && Cvv.length() >= 3 && Cvv.length() <= 4 && Cvv.matches("[0-9]+")) {
                cvvcheck.setText("Valid CVV");
                cvvcheck.setTextFill(Color.GREEN);
            } else {
                cvvcheck.setText("Invalid CVV. Must be 3-4 digits.");
                cvvcheck.setTextFill(Color.RED);
            }

            if (ExpDate != null && ExpDate.length() == 5 && ExpDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                expcheck.setText("Valid expiration date");
                expcheck.setTextFill(Color.GREEN);
            } else {
                expcheck.setText("Invalid expiration date. Must be MM/YY.");
                expcheck.setTextFill(Color.RED);
            }
        } catch (Exception e) {
            cardcheck.setText("An error occurred. Please try again.");
            cardcheck.setTextFill(Color.RED);
        }
    }

    public void switchTopayment(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("paymentcon.fxml"));
        Object root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }
}
