package com.example.demo;

import Project.Orders.OrderState;
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

import static com.example.demo.Info.*;

public class deliveryInterfaceController {
    private Scene scene;
    private Stage stage;
    private Parent root;

    int number=0;

    @FXML
    private Label DeliveryName;

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

    @FXML
    private Button Completed;

    @FXML
    private Button Preparing;

    @FXML
    private Button InDelivery;

    @FXML
    private Button Canceled;

    @FXML
    private Button Delivered;

    @FXML
    private Rectangle State;

    public void initialize() {
        DeliveryName.setText(Info.delivery_Staff.getFname()+" "+ Info.delivery_Staff.getLname());
        OrderID.setText("Order ID: "+orders.getFirst().getOrderId());
        OrderStatus.setText("Order status: "+orders.getFirst().getOrderState());
        OrderLocation.setText("Order location: "+orders.getFirst().getOrderLocation());
        OrderPrice.setText("Order price: "+orders.getFirst().getOrderPrice());
        OrderButton.setDisable(true);
        OrderRectangle.setDisable(true);
        Orderer.setDisable(true);
        OrderButton.setOpacity(0.0);
        OrderRectangle.setOpacity(0.0);
        Orderer.setOpacity(0.0);
        disappearStatus();

    }

    public void switchSceneToWelcome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Welcome.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void search(){
        if(OrderRectangle.getOpacity()==0.0||Canceled.getOpacity()==1.0) {
            OrderButton.setDisable(false);
            OrderRectangle.setDisable(false);
            Orderer.setDisable(false);
            OrderButton.setOpacity(1.0);
            OrderRectangle.setOpacity(1.0);
            Orderer.setOpacity(1.0);
            Orderer.setText("");
        }else{disappearOrder();}
        disappearStatus();
    }

    public void Previous(){
        if(number==0)
            number=orders.size()-1;
        else
            number--;
        OrderID.setText("Order ID: "+orders.get(number).getOrderId());
        OrderStatus.setText("Order status: "+orders.get(number).getOrderState());
        OrderLocation.setText("Order location: "+orders.get(number).getOrderLocation());
        OrderPrice.setText("Order price: "+orders.get(number).getOrderPrice());
    }

    public void Next(){
        if(number==orders.size()-1)
            number=0;
        else
            number++;
        OrderID.setText("Order ID: "+orders.get(number).getOrderId());
        OrderStatus.setText("Order status: "+orders.get(number).getOrderState());
        OrderLocation.setText("Order location: "+orders.get(number).getOrderLocation());
        OrderPrice.setText("Order price: "+orders.get(number).getOrderPrice());
    }

    public void setSearch(){
        String s = Orderer.getText();
        for(int i=0;i<restaurants.size();i++){
            if(s.equals(orders.get(i).getOrderId())){
                number=i;
                OrderID.setText("Order ID: "+orders.get(i).getOrderId());
                OrderStatus.setText("Order status: "+orders.get(i).getOrderState());
                OrderLocation.setText("Order location: "+orders.get(i).getOrderLocation());
                OrderPrice.setText("Order price: "+orders.get(i).getOrderPrice());
            }
        }
    }

    public void changeStatus(){
        if(Canceled.getOpacity()==0.0||OrderRectangle.getOpacity()==1.0) {
            Canceled.setDisable(false);
            Delivered.setDisable(false);
            Completed.setDisable(false);
            Preparing.setDisable(false);
            InDelivery.setDisable(false);
            State.setDisable(false);

            State.setOpacity(1.0);
            Completed.setOpacity(1.0);
            Preparing.setOpacity(1.0);
            InDelivery.setOpacity(1.0);
            Canceled.setOpacity(1.0);
            Delivered.setOpacity(1.0);
        }else {disappearStatus();}
        disappearOrder();
    }

    public void changePreparing(){
            orders.get(number).setOrderState(OrderState.Preparing);
            disappearStatus();
    }
    public void changeCanceled(){
        orders.get(number).setOrderState(OrderState.Canceled);
        disappearStatus();
    }
    public void changeCompleted(){
            orders.get(number).setOrderState(OrderState.Complete);
            disappearStatus();
    }
    public void changeInDelivery(){
            orders.get(number).setOrderState(OrderState.In_Delivery);
            disappearStatus();
    }
    public void changeDelivered(){
            orders.get(number).setOrderState(OrderState.Delivered);
            disappearStatus();
    }

    private void disappearStatus(){
        Completed.setOpacity(0.0);
        InDelivery.setOpacity(0.0);
        Canceled.setOpacity(0.0);
        Delivered.setOpacity(0.0);
        Preparing.setOpacity(0.0);
        State.setOpacity(0.0);
        Completed.setDisable(true);
        InDelivery.setDisable(true);
        Canceled.setDisable(true);
        Delivered.setDisable(true);
        Preparing.setDisable(true);
        State.setDisable(true);
        OrderStatus.setText("Order status: "+orders.get(number).getOrderState());
    }

    private void disappearOrder(){
        OrderButton.setDisable(true);
        OrderRectangle.setDisable(true);
        Orderer.setDisable(true);
        OrderButton.setOpacity(0.0);
        OrderRectangle.setOpacity(0.0);
        Orderer.setOpacity(0.0);
    }
}
