package com.example.demo;

import Project.Person.Customer;
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

public class CustomerInterfaceController{
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
    private Label ReviewLabel;

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
        Name.setText("Restaurant name: "+restaurants.getFirst().name);
        Address.setText("Address: "+restaurants.getFirst().address);
        Rating.setText("Rating of the restaurant: "+restaurants.getFirst().rating);
        Contact.setText("Contact information: "+restaurants.getFirst().contactInformation);
        locationDisappear();
        reviewDisappear();
        if(cancel){
            CancelOrder.setDisable(false);
            CancelOrder.setOpacity(1.0);
        }else{
            CancelOrder.setDisable(true);
            CancelOrder.setOpacity(0.0);
        }
    }

    public void CancelOrder(){
        orders.removeLast();
        CancelOrder.setDisable(true);
        CancelOrder.setOpacity(0.0);
    }

    public void chooseRestaurant(ActionEvent event) throws IOException {
        Info.restaurant=restaurants.get(number);
        switchToCustomerChoseRestaurant(event);
    }

    public void Previous(){
        if(number==0)
            number=restaurants.size()-1;
        else
            number--;
        Name.setText("Restaurant name: "+restaurants.get(number).name);
        Address.setText("Address: "+restaurants.get(number).address);
        Rating.setText("Rating of the restaurant: "+restaurants.get(number).rating);
        Contact.setText("Contact information: "+restaurants.get(number).contactInformation);
    }

    public void Next(){
        if(number==restaurants.size()-1)
            number=0;
        else
            number++;
        Name.setText("Restaurant name: "+restaurants.get(number).name);
        Address.setText("Address: "+restaurants.get(number).address);
        Rating.setText("Rating of the restaurant: "+restaurants.get(number).rating);
        Contact.setText("Contact information: "+restaurants.get(number).contactInformation);
    }

    public void getLocation() throws URISyntaxException, IOException {
        System.out.println(restaurants.get(number).name);
        restaurants.get(number).getLocation();
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

    public void setSearch(ActionEvent event) throws IOException {
        System.out.println("Please enter the address you want to search in : ");
        restaurantsLocation=restaurants
                .stream()
                .filter(restaurant1 -> restaurant1.address.equalsIgnoreCase(Locationer.getText()))
                .toList();
        if(!restaurantsLocation.isEmpty()){
            LocationLabel.setOpacity(0.0);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerSearchLocation.fxml"));
            root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            LocationLabel.setOpacity(1.0);
        }
    }

    private void reviewDisappear(){
        ReviewButton.setDisable(true);
        ReviewRectangle.setDisable(true);
        Reviewer.setDisable(true);
        ReviewLabel.setDisable(true);
        ReviewLabel.setOpacity(0.0);
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
        if(Double.parseDouble(Reviewer.getText())>5.0||Double.parseDouble(Reviewer.getText())<0.0){
            ReviewLabel.setDisable(false);
            ReviewLabel.setOpacity(1.0);
        }else{
            Review review=new Review(restaurants.get(number));
            for (Review value : reviewsRestaurant) {
                if (value.restaurant.name.equals(restaurants.get(number).name)) {
                    review.number_of_reviewsR++;
                }
            }
            ReviewLabel.setDisable(true);
            ReviewLabel.setOpacity(0.0);
            review.setReviewForRestaurant(Double.parseDouble(Reviewer.getText()));
            reviewsRestaurant.add(review);
            Rating.setText("Rating of the restaurant: "+restaurants.get(number).rating);
            ReviewButton.setDisable(true);
            ReviewRectangle.setDisable(true);
            Reviewer.setDisable(true);
            ReviewButton.setOpacity(0.0);
            ReviewRectangle.setOpacity(0.0);
            Reviewer.setOpacity(0.0);
        }
    }
}
