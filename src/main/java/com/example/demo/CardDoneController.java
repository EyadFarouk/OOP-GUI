package com.example.demo;

import Project.Orders.Order;
import Project.Orders.OrderState;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CardDoneController {
    private Scene scene;
    private Stage stage;
    private Parent root;

    public void switchToInterface(ActionEvent event) throws IOException {
        Order order=new Order(Info.customer.getDeliveryAddress(), OrderState.Preparing);
        order.setTotalPrice(CustomerChoseRestaurantController.totalPrice);
        Info.orders.add(order);
        Info.cancel=true;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerInterface.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
