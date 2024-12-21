package com.example.demo;

import Project.Orders.OrderState;
import Project.Person.Admin;
import Project.Person.Customer;
import Project.Resturants.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.demo.Info.myOrders;
import static com.example.demo.Info.orders;

public class OrderController {
    private Scene scene;
    private Stage stage;
    private Parent root;


    int number=0;

    @FXML
    private Label CustomerName;

    @FXML
    private Label OrderLocation;

    @FXML
    private Label OrderID;

    @FXML
    private Label OrderStatus;

    @FXML
    private Label OrderPrice;

    @FXML
    private TextField Orderer;

    @FXML
    private Rectangle OrderRectangle;

    @FXML
    private Button OrderButton;

    public void initialize() {
        CustomerName.setText(Info.customer.getFname()+" "+ Info.customer.getLname());
        OrderID.setText("Order ID: "+myOrders.getFirst().getOrderId());
        OrderStatus.setText("Order status: "+myOrders.getFirst().getOrderState());
        OrderLocation.setText("Order location: "+myOrders.getFirst().getOrderLocation());
        OrderPrice.setText("Order price: "+myOrders.getFirst().getOrderPrice());
        disappearOrder();
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerLoginOrSignUp.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCustomerInterface(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerInterface.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void search(){
        if(OrderRectangle.getOpacity()==0.0) {
            OrderButton.setDisable(false);
            OrderRectangle.setDisable(false);
            Orderer.setDisable(false);
            OrderButton.setOpacity(1.0);
            OrderRectangle.setOpacity(1.0);
            Orderer.setOpacity(1.0);
            Orderer.setText("");
        }else{disappearOrder();}
    }
    private void disappearOrder(){
        OrderButton.setDisable(true);
        OrderRectangle.setDisable(true);
        Orderer.setDisable(true);
        OrderButton.setOpacity(0.0);
        OrderRectangle.setOpacity(0.0);
        Orderer.setOpacity(0.0);
    }

    public void setSearch(ActionEvent event) {
    }

    public void Previous(ActionEvent event) {
        if(number==0)
            number=myOrders.size()-1;
        else
            number--;
        OrderID.setText("Order ID: "+orders.get(number).getOrderId());
        OrderStatus.setText("Order status: "+orders.get(number).getOrderState());
        OrderLocation.setText("Order location: "+orders.get(number).getOrderLocation());
        OrderPrice.setText("Order price: "+orders.get(number).getOrderPrice());
    }

    public void Next(ActionEvent event) {
        if(number==myOrders.size()-1)
            number=0;
        else
            number++;
        OrderID.setText("Order ID: "+myOrders.get(number).getOrderId());
        OrderStatus.setText("Order status: "+myOrders.get(number).getOrderState());
        OrderLocation.setText("Order location: "+myOrders.get(number).getOrderLocation());
        OrderPrice.setText("Order price: "+myOrders.get(number).getOrderPrice());
    }

    public void changeCanceled(){
        myOrders.get(number).setOrderState(OrderState.Canceled);
        OrderStatus.setText("Order status: "+myOrders.get(number).getOrderState());
    }
}
