package com.example.demo;

import Project.Resturants.Restaurant;
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
import java.net.URISyntaxException;
import java.util.List;

import static com.example.demo.Info.*;
import static com.example.demo.Info.restaurants;

public class CustomerSearchLocationController {
    private Scene scene;
    private Stage stage;
    private Parent root;

    int number=0;

    @FXML
    private Label CustomerName;

    @FXML
    private Label Name;

    @FXML
    private Label Address;

    @FXML
    private Label Rating;

    @FXML
    private Label Contact;

    @FXML
    private TextField Reviewer;

    @FXML
    private Rectangle ReviewRectangle;

    @FXML
    private Button ReviewButton;

    @FXML
    private TextField Locationer;

    @FXML
    private Rectangle LocationRectangle;

    @FXML
    private Button LocationButton;


    @FXML
    private Label LocationLabel;

    @FXML
    private Button CancelOrder;

    public void switchToLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerLoginOrSignUp.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCustomerChoseRestaurant(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerChoseRestaurant.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        CustomerName.setText(Info.customer.getFname()+" "+ Info.customer.getLname());
        Name.setText("Restaurant name: "+restaurantsLocation.getFirst().name);
        Address.setText("Address: "+restaurantsLocation.getFirst().address);
        Rating.setText("Rating of the restaurant: "+restaurantsLocation.getFirst().rating);
        Contact.setText("Contact information: "+restaurantsLocation.getFirst().contactInformation);
        locationDisappear();
        reviewDisappear();
    }

    public void CancelOrder(){
        orders.removeLast();
        CancelOrder.setDisable(true);
        CancelOrder.setOpacity(0.0);
    }

    public void chooseRestaurant(ActionEvent event) throws IOException {
        Info.restaurant=restaurantsLocation.get(number);
        switchToCustomerChoseRestaurant(event);
    }

    public void Previous(){
        if(number==0)
            number=restaurantsLocation.size()-1;
        else
            number--;
        Name.setText("Restaurant name: "+restaurantsLocation.get(number).name);
        Address.setText("Address: "+restaurantsLocation.get(number).address);
        Rating.setText("Rating of the restaurant: "+restaurantsLocation.get(number).rating);
        Contact.setText("Contact information: "+restaurantsLocation.get(number).contactInformation);
    }

    public void Next(){
        if(number==restaurantsLocation.size()-1)
            number=0;
        else
            number++;
        Name.setText("Restaurant name: "+restaurantsLocation.get(number).name);
        Address.setText("Address: "+restaurantsLocation.get(number).address);
        Rating.setText("Rating of the restaurant: "+restaurantsLocation.get(number).rating);
        Contact.setText("Contact information: "+restaurantsLocation.get(number).contactInformation);
    }

    public void getLocation() throws URISyntaxException, IOException {
        System.out.println(restaurantsLocation.get(number).name);
        restaurantsLocation.get(number).getLocation();
    }

    public void review(){
        if(ReviewRectangle.getOpacity()==0.0||LocationRectangle.getOpacity()==1.0) {
            ReviewButton.setDisable(false);
            ReviewRectangle.setDisable(false);
            Reviewer.setDisable(false);
            ReviewButton.setOpacity(1.0);
            ReviewRectangle.setOpacity(1.0);
            Reviewer.setOpacity(1.0);
            Reviewer.setText("");
            locationDisappear();
        }else{reviewDisappear();}
    }

    public void searchLocation(){
        if(LocationRectangle.getOpacity()==0.0) {
            LocationButton.setDisable(false);
            LocationRectangle.setDisable(false);
            Locationer.setDisable(false);
            LocationLabel.setDisable(false);
            LocationLabel.setOpacity(0.0);
            LocationButton.setOpacity(1.0);
            LocationRectangle.setOpacity(1.0);
            Locationer.setOpacity(1.0);
            Locationer.setText("");
            reviewDisappear();
        }else{locationDisappear();}
    }

    public void setSearch(){
        System.out.println("Please enter the address you want to search in : ");
        List<Restaurant> restaurantList=restaurantsLocation
                .stream()
                .filter(restaurant1 -> restaurant1.address.equals(Locationer.getText()))
                .toList();
        if(!restaurantList.isEmpty()){
            LocationLabel.setOpacity(0.0);

        }else{
            LocationLabel.setOpacity(1.0);
        }
    }

    private void reviewDisappear(){
        ReviewButton.setDisable(true);
        ReviewRectangle.setDisable(true);
        Reviewer.setDisable(true);
        ReviewButton.setOpacity(0.0);
        ReviewRectangle.setOpacity(0.0);
        Reviewer.setOpacity(0.0);
    }

    private void locationDisappear(){
        LocationButton.setDisable(true);
        LocationRectangle.setDisable(true);
        Locationer.setDisable(true);
        LocationLabel.setDisable(true);
        LocationLabel.setOpacity(0.0);
        LocationButton.setOpacity(0.0);
        LocationRectangle.setOpacity(0.0);
        Locationer.setOpacity(0.0);
    }

    public void setReview(){
        Review review=new Review(restaurantsLocation.get(number));
        for (Review value : reviewsRestaurant) {
            if (value.restaurant.name.equals(restaurantsLocation.get(number).name)) {
                review.number_of_reviewsR++;
            }
        }
        review.setReviewForRestaurant(Double.parseDouble(Reviewer.getText()));
        reviewsRestaurant.add(review);
        Rating.setText("Rating of the restaurant: "+restaurantsLocation.get(number).rating);
        ReviewButton.setDisable(true);
        ReviewRectangle.setDisable(true);
        Reviewer.setDisable(true);
        ReviewButton.setOpacity(0.0);
        ReviewRectangle.setOpacity(0.0);
        Reviewer.setOpacity(0.0);
    }
}
