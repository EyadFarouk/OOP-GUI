package Project.Orders;



import Project.Payment.Card;
import Project.Resturants.Dish;
import Project.Resturants.Restaurant;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * this class is responsible for the orders
 */
public class Order {
    private String orderId;
    private Date orderDate;
    private  static List<Dish> foodItems=new ArrayList<>();
    private double totalPrice;
    private String orderLocation;
    private String orderState; // state of the order (Pending, Completed, Canceled)

    /**
     * this is the constructor and it takes two parameters
     * @param orderLocation the location the order is headed to
     * @param orderState the current state of the order
     */
    public Order(String orderLocation, String orderState) {
        this.orderId = generateRandomOrderId();
        this.orderDate = new Date();
        //foodItems = new ArrayList<>();
       // this.totalPrice = 0.0;
        this.orderLocation = orderLocation;
        this.orderState = orderState;
    }

    /**
     * a default constructor
     */
    public Order(){}

    // Generate a random order ID using UUID

    /**
     * this method is used to generate a random order ID
     * @return and it returns the ID as a String
     */
    private String generateRandomOrderId() {
        return UUID.randomUUID().toString();
    }

    // For adding one Food Instance

    /**
     * this method is used for adding food  items to the order
     * @param dish it takes a dish object
     */
    public void addFoodItem(Dish dish) {
        foodItems.add(dish);
        totalPrice += dish.price; // update total price
    }

    /**
     * this method uses the addFoodItem method to create new orders
     */
        public void makeOrder(){
        if (foodItems.isEmpty()){
            System.out.println("you should choose food first");
            return;
        }
        System.out.println("order include : ");
        Restaurant restaurant=new Restaurant();
        restaurant.displayMenu(foodItems);
        Card card=new Card();
       // card.SelectCard();
    }

    /* === Getters === */

    /**
     * gets the order ID
     * @return returns order ID
     */
    public String getOrderId() { return orderId; }
    /**
     * gets the order date
     * @return returns order date
     */
    public Date getOrderDate() { return orderDate; }
    /**
     * gets the order's total price
     * @return returns order total price
     */
    public double getOrderPrice() { return totalPrice; }
    /**
     * gets the order location
     * @return returns order location
     */
    public String getOrderLocation() { return orderLocation; }
    /**
     * gets the order state
     * @return returns order state
     */
    public String getOrderState() { return orderState; }

    /* === Setters ===*/

    /**
     * sets the order state
     * @param orderState takes the current state of the order
     */
    public void setOrderState(String orderState) { this.orderState = orderState; }

    // override the toString() method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Id: ").append(orderId)
                .append("\nOrder Date: ").append(orderDate);

        // Loop throw the food list to print each food item separately
        for (Dish food : foodItems)
            sb.append("\n- ").append(food.toString());

        // The rest of the properties
        sb.append("\nTotal Order Price: ").append(totalPrice)
                .append("\nOrder Location: ").append(orderLocation)
                .append("\nOrder State: ").append(orderState);
        return sb.toString();
    }
    public void saveData(List<Order>orders){
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("Data/orders.txt"));
            for (Order order:orders){
                if (order.orderState.equals("delivered")){
                    continue;
                }
                bufferedWriter.write(order.orderId+'\n');
                bufferedWriter.write(order.orderLocation+'\n');
                bufferedWriter.write(order.orderState+'\n');
                bufferedWriter.write(String.valueOf(order.totalPrice)+'\n');
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Order>loadData(){
        List <Order>orders=new ArrayList<>();
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader("Data/orders.txt"));
            String line;
            Order order=new Order();
            while ((line=bufferedReader.readLine())!=null){
                order.orderId=line;
                line= bufferedReader.readLine();
                order.orderLocation=line;
                line=bufferedReader.readLine();
                order.orderState=line;
                line=bufferedReader.readLine();
                order.totalPrice=Double.valueOf(line);
                orders.add(order);
                order=new Order();
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }
}