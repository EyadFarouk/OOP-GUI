package com.example.demo;

import Project.Orders.Order;
import Project.Payment.Card;
import Project.Person.Admin;
import Project.Person.Customer;
import Project.Person.Delivery_Staff;
import Project.Resturants.Restaurant;
import Project.Resturants.Review;

import java.util.List;

class Info {
    static Customer customer;
    static Restaurant restaurant;
    static Delivery_Staff delivery_Staff;

    static List<Restaurant> restaurants;
    static List<Review>reviewsRestaurant;
    static List<Order> orders;

    public static void loadData(){
        Restaurant restaurant = new Restaurant();
        restaurants = restaurant.loadData();

        Review rev=new Review();
        reviewsRestaurant=rev.loadDataReviewRestaurant();

        Customer customer = new Customer();
        customer.loadData();

        Admin admin = new Admin();
        admin.loadData();


        Card card = new Card();
        card.loadData();

        Delivery_Staff deliveryStaff=new Delivery_Staff("shubra");
        deliveryStaff.loadData();

        Order order1=new Order();
        orders=order1.loadData();
    }

    public static void saveData(){
        Restaurant restaurant = new Restaurant();
        restaurant.saveData(restaurants);

        Review rev=new Review();
        rev.saveData(reviewsRestaurant);

        Customer customer = new Customer();
        customer.saveData();

        Admin admin = new Admin();
        admin.saveData();


        Card card = new Card();
        card.saveData();

        Delivery_Staff deliveryStaff=new Delivery_Staff("shubra");
        deliveryStaff.saveData();

        Order order1=new Order();
        order1.saveData(orders);
    }
}
