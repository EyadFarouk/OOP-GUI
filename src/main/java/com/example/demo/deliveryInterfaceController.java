package com.example.demo;

import Project.Resturants.Review;
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
import static com.example.demo.Info.reviewsRestaurant;

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
        if(OrderRectangle.getOpacity()==0.0) {
            OrderButton.setDisable(false);
            OrderRectangle.setDisable(false);
            Orderer.setDisable(false);
            OrderButton.setOpacity(1.0);
            OrderRectangle.setOpacity(1.0);
            Orderer.setOpacity(1.0);
            Orderer.setText("");
        }else{
            OrderButton.setDisable(true);
            OrderRectangle.setDisable(true);
            Orderer.setDisable(true);
            OrderButton.setOpacity(0.0);
            OrderRectangle.setOpacity(0.0);
            Orderer.setOpacity(0.0);
        }
    }

    public void Previous(){
        if(number==0)
            number=restaurants.size()-1;
        else
            number--;
        OrderID.setText("Order ID: "+orders.getFirst().getOrderId());
        OrderStatus.setText("Order status: "+orders.getFirst().getOrderState());
        OrderLocation.setText("Order location: "+orders.getFirst().getOrderLocation());
        OrderPrice.setText("Order price: "+orders.getFirst().getOrderPrice());
    }

//    public void Next(){
//        if(number==restaurants.size()-1)
//            number=0;
//        else
//            number++;
//        Name.setText("Restaurant name: "+restaurants.get(number).name);
//        Address.setText("Address: "+restaurants.get(number).address);
//        Rating.setText("Rating of the restaurant: "+restaurants.get(number).rating);
//        Contact.setText("Contact information: "+restaurants.get(number).contactInformation);
//    }

//    public void setSearch(){
//        Review review=new Review(restaurants.get(number));
//        for (Review value : reviewsRestaurant) {
//            if (value.restaurant.name.equals(restaurants.get(number).name)) {
//                review.number_of_reviewsR++;
//            }
//        }
//        review.setReviewForRestaurant(Double.parseDouble(Reviewer.getText()));
//        reviewsRestaurant.add(review);
//        Rating.setText("Rating of the restaurant: "+restaurants.get(number).rating);
//        ReviewButton.setDisable(true);
//        ReviewRectangle.setDisable(true);
//        Reviewer.setDisable(true);
//        ReviewButton.setOpacity(0.0);
//        ReviewRectangle.setOpacity(0.0);
//        Reviewer.setOpacity(0.0);
//    }
}
