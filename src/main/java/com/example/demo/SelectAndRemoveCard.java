package com.example.demo;

import Project.Payment.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SelectAndRemoveCard {

    @FXML
    private TextField cardNumber;
    @FXML
    private CheckBox sure;
    private Stage stage;
    private Scene scene;
    private Parent root;

    static ArrayList<Card> cardList = new ArrayList<>();

    public void removeCard(ActionEvent event) {
        if(sure.isSelected()){
        String inputCardNum = cardNumber.getText();

        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getCardNum().equals(inputCardNum)) {
                cardList.remove(i);
                cardNumber.setText("Card removed successfully.");
                return;
            }
            }
        }

        cardNumber.setText("Card not found. Please check the card number and try again.");
    }

    public void selectCard(ActionEvent event) throws IOException {
        String inputCardNum = cardNumber.getText();
        boolean cardFound = false;

        for (Card card : cardList) {
            if (card.getCardNum().equals(inputCardNum)) {
                cardFound = true;
                break;
            }
        }

        if (cardFound) {
            switchScene(event, "carddone.fxml");
        } else {
            cardNumber.setText("Card not found or incorrect number entered.");
        }
    }


    public void cardPayment(ActionEvent event) throws IOException {
        switchScene(event, "cardPayment.fxml");
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
