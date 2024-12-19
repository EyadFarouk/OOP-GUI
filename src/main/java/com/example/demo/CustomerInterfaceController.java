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

public class CustomerInterfaceController{
    private Scene scene;
    private Stage stage;
    private Parent root;

    List<Restaurant> restaurants;
    List<Review>reviewsRestaurant;

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
        Restaurant restaurant = new Restaurant();
        restaurants=restaurant.loadData();
        Review rev=new Review();
        reviewsRestaurant=rev.loadDataReviewRestaurant();
        Name.setText("Restaurant name: "+restaurants.getFirst().name);
        Address.setText("Address: "+restaurants.getFirst().address);
        Rating.setText("Rating of the restaurant: "+restaurants.getFirst().rating);
        Contact.setText("Contact information: "+restaurants.getFirst().contactInformation);
        ReviewButton.setDisable(true);
        ReviewRectangle.setDisable(true);
        Reviewer.setDisable(true);
        ReviewButton.setOpacity(0.0);
        ReviewRectangle.setOpacity(0.0);
        Reviewer.setOpacity(0.0);
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
        if(ReviewRectangle.getOpacity()==0.0) {
            ReviewButton.setDisable(false);
            ReviewRectangle.setDisable(false);
            Reviewer.setDisable(false);
            ReviewButton.setOpacity(1.0);
            ReviewRectangle.setOpacity(1.0);
            Reviewer.setOpacity(1.0);
            Reviewer.setText("");
        }else{
            ReviewButton.setDisable(true);
            ReviewRectangle.setDisable(true);
            Reviewer.setDisable(true);
            ReviewButton.setOpacity(0.0);
            ReviewRectangle.setOpacity(0.0);
            Reviewer.setOpacity(0.0);
        }
    }

    public void setReview(){
        Review review=new Review(restaurants.get(number));
        for (Review value : reviewsRestaurant) {
            if (value.restaurant.name.equals(restaurants.get(number).name)) {
                review.number_of_reviewsR++;
            }
        }
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
