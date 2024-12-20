package com.example.demo;

import Project.Person.Customer;
import Project.Resturants.Dish;
import Project.Resturants.Menu;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerChoseRestaurantController {
    private Scene scene;
    private Stage stage;
    private Parent root;

    private List<Dish> foodItems=new ArrayList<>();
    private List<Integer>quantites=new ArrayList<>();
    private List<Dish> dishList;
    public static double totalPrice;

    int number=0;

    @FXML
    private Label Restaurant;

    @FXML
    private Label CustomerName;

    @FXML
    private Label Name;

    @FXML
    private Label Category;

    @FXML
    private Label Price;

    @FXML
    private Label Rating;

    @FXML
    private Label Description;

    @FXML
    private Button OrderButton;

    @FXML
    private TextField Orderer;

    @FXML
    private Rectangle OrderRectangle;

    @FXML
    private Button Order;

    @FXML
    private Label Warning;

    public void initialize(){
        dishList = Info.restaurant.menu;
        Restaurant.setText(Info.restaurant.name);
        CustomerName.setText(Info.customer.getFname()+" "+ Info.customer.getLname());
        Category.setText("Category: "+dishList.getFirst().categories);
        Name.setText("Dish name: "+dishList.getFirst().name);
        Price.setText("Price: "+dishList.getFirst().price);
        Rating.setText("Rating of the dish: "+dishList.getFirst().rating);
        Description.setText("Description: "+dishList.getFirst().description);
        OrderButton.setDisable(true);
        OrderRectangle.setDisable(true);
        Orderer.setDisable(true);
        Order.setDisable(true);
        OrderButton.setOpacity(0.0);
        OrderRectangle.setOpacity(0.0);
        Orderer.setOpacity(0.0);
        Order.setOpacity(0.0);
    }

    public void Previous(){
        if(number==0)
            number=dishList.size()-1;
        else
            number--;
        Name.setText("Dish name: "+dishList.get(number).name);
        Category.setText("Category: "+dishList.get(number).categories);
        Rating.setText("Rating of the dish: "+dishList.get(number).rating);
        Price.setText("Price: "+dishList.get(number).price);
        Description.setText("Description: "+dishList.get(number).description);
        disappearAndEmpty();
    }

    public void Next(){
        if(number==dishList.size()-1)
            number=0;
        else
            number++;
        Name.setText("Dish name: "+dishList.get(number).name);
        Category.setText("Category: "+dishList.get(number).categories);
        Rating.setText("Rating of the dish: "+dishList.get(number).rating);
        Price.setText("Contact information: "+dishList.get(number).price);
        Description.setText("Description: "+dishList.get(number).description);
        disappearAndEmpty();
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        if(!dishList.isEmpty())
            dishList.clear();
        if(!quantites.isEmpty())
            quantites.clear();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerLoginOrSignUp.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCustomerInterface(ActionEvent event) throws IOException {
        if(!dishList.isEmpty())
            dishList.clear();
        if(!quantites.isEmpty())
            quantites.clear();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerInterface.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Order(){
        if(OrderRectangle.getOpacity()==0.0) {
            OrderButton.setDisable(false);
            OrderRectangle.setDisable(false);
            Orderer.setDisable(false);
            OrderButton.setOpacity(1.0);
            OrderRectangle.setOpacity(1.0);
            Orderer.setOpacity(1.0);
            if(foodItems.contains(dishList.get(number))){
                for (int i = 0; i < foodItems.size(); i++)
                    if (foodItems.get(i) == dishList.get(number)) {
                        Orderer.setText(String.valueOf(quantites.get(i)));
                        break;
                    }
            }
        }else{
            disappearAndEmpty();
        }
    }

    public void setOrder(){
        if(Warning.getOpacity()==0.0 && !Orderer.getText().isEmpty()) {
            if(Orderer.getText().equals("0")){
                if(foodItems.contains(dishList.get(number))){
                    for (int i = 0; i < foodItems.size(); i++)
                        if (foodItems.get(i) == dishList.get(number)) {
                            foodItems.remove(i);
                            quantites.remove(i);
                            break;
                        }
                }
            }
            else{
                if(foodItems.contains(dishList.get(number))){
                    for (int i = 0; i < foodItems.size(); i++)
                        if (foodItems.get(i) == dishList.get(number)) {
                            quantites.set(i, Integer.valueOf(Orderer.getText()));
                            break;
                        }
                }
                foodItems.add(dishList.get(number));
                quantites.add(Integer.valueOf(Orderer.getText()));

                Order.setDisable(false);
                Order.setOpacity(1.0);
            }
        }
        if(foodItems.isEmpty()) {
            Order.setDisable(true);
            Order.setOpacity(0.0);
        }
        disappearAndEmpty();
    }

    public void makeOrder(ActionEvent event) throws IOException {
        totalPrice=0;
        for (int i = 0; i < foodItems.size(); i++) {
            totalPrice = foodItems.get(i).price * quantites.get(i) + totalPrice;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("payment.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void checkNumbers(){
        if(Orderer.getText().matches("^[0-9]*$")){
            Warning.setOpacity(0.0);
        }else{
            Warning.setOpacity(1.0);
        }
    }

    private void disappearAndEmpty(){
        OrderButton.setDisable(true);
        OrderRectangle.setDisable(true);
        Orderer.setDisable(true);
        OrderButton.setOpacity(0.0);
        OrderRectangle.setOpacity(0.0);
        Orderer.setOpacity(0.0);
        Orderer.setText("");
    }
}
