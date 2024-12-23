package com.example.demo;

import Project.Orders.AllOrders;
import Project.Orders.Order;
import Project.Orders.OrderState;
import Project.Payment.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.ArrayList;

import static Project.Payment.Card.cardList;

public class CardPaymentController {
    @FXML
    private Button submitButton;
    @FXML
    private TextField cardnum, cvv, expirationDate, addMoneyField;
    @FXML
    private Label cardcheck, cvvcheck, expcheck, moneycheck;

    private String Cvv, CardNum, ExpDate;
    private Scene scene;
    private Stage stage;
    private Parent root;

    private final String REGEX_DIGITS = "\\d+";

    YearMonth currentDate = YearMonth.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
    String formattedDate = currentDate.format(formatter);

    @FXML
    public void initialize() {
        cardnum.textProperty().addListener((observable, oldValue, newValue) -> validateInputs());
        cvv.textProperty().addListener((observable, oldValue, newValue) -> validateInputs());
        expirationDate.textProperty().addListener((observable, oldValue, newValue) -> validateInputs());
        addMoneyField.textProperty().addListener((observable, oldValue, newValue) -> validateInputs());
    }

    private void validateInputs() {
        try {
            CardNum = cardnum.getText();
            Cvv = cvv.getText();
            ExpDate = expirationDate.getText();
            String moneyInput = addMoneyField.getText();

            boolean isCardValid = CardNum.length() >= 12 && CardNum.length() <= 16 && CardNum.matches(REGEX_DIGITS);
            boolean isCvvValid = Cvv.length() >= 3 && Cvv.length() <= 4 && Cvv.matches(REGEX_DIGITS);
            boolean isExpDateValid = ExpDate.length() == 5
                    && ExpDate.matches("\\d{2}/(0[1-9]|1[0-2])")
                    && formattedDate.compareTo(ExpDate) <= 0;
            boolean isMoneyValid = false;

            try {
                double money = Double.parseDouble(moneyInput);
                if (money > 0) {
                    isMoneyValid = true;
                }
            } catch (NumberFormatException e) {
                isMoneyValid = false;
            }

            updateValidationLabels(isCardValid, isCvvValid, isExpDateValid, isMoneyValid);

            submitButton.setDisable(!(isCardValid && isCvvValid && isExpDateValid && isMoneyValid));
        } catch (Exception e) {
            cardcheck.setText("An error occurred. Please try again.");
            cardcheck.setTextFill(Color.RED);
        }
    }

    private void updateValidationLabels(boolean isCardValid, boolean isCvvValid, boolean isExpDateValid, boolean isMoneyValid) {
        if (isCardValid) {
            cardcheck.setText("Valid card number");
            cardcheck.setTextFill(Color.GREEN);
        } else {
            cardcheck.setText("Invalid card number. Must be 12-16 digits.");
            cardcheck.setTextFill(Color.RED);
        }

        if (isCvvValid) {
            cvvcheck.setText("Valid CVV");
            cvvcheck.setTextFill(Color.GREEN);
        } else {
            cvvcheck.setText("Invalid CVV. Must be 3-4 digits.");
            cvvcheck.setTextFill(Color.RED);
        }

        if (isExpDateValid) {
            expcheck.setText("Valid expiration date");
            expcheck.setTextFill(Color.GREEN);
        } else {
            expcheck.setText("Invalid expiration date. Must be MM/YY.");
            expcheck.setTextFill(Color.RED);
        }

        if (isMoneyValid) {
            moneycheck.setText("Valid amount");
            moneycheck.setTextFill(Color.GREEN);
        } else {
            moneycheck.setText("Invalid amount. Please enter a positive number.");
            moneycheck.setTextFill(Color.RED);
        }
    }

    public void submit(ActionEvent event) throws IOException {
        if (!submitButton.isDisabled()) {
            Card newCard = new Card();
            newCard.setCardNum(cardnum.getText());
            newCard.setCvv(cvv.getText());
            newCard.setExpirationDate(expirationDate.getText());
            newCard.setMoneyAvailable(Double.parseDouble(addMoneyField.getText()));
            cardList.add(newCard);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cardDone.fxml"));
            Parent root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void switchToInterface(ActionEvent event) throws IOException {
        Order order = new Order(Info.customer.getDeliveryAddress(), OrderState.Preparing, Info.customer.getEmail());
        order.setTotalPrice(CustomerChoseRestaurantController.totalPrice);
        Info.orders.add(order);
        AllOrders.orderList.add(order);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerInterface.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Cardremoveorselect(ActionEvent event) throws IOException {
        switchScene(event, "cardremoveorselect.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
