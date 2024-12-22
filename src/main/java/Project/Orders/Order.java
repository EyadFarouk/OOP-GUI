package Project.Orders;

import Project.Interfaces.checkNumberValid;
import Project.Payment.Card;
import Project.Resturants.Dish;
import Project.Resturants.Restaurant;

import java.io.*;
import java.util.*;

/**
 * this class is responsible for the orders
 */
public class Order implements checkNumberValid {
    private String orderId;
    private Date orderDate;
    public   static List<Dish> foodItems=new ArrayList<>();
    public static List<Integer>quantites=new ArrayList<>();
    private double totalPrice;
    private String orderLocation;
   // state of the order (Pending, Completed, Canceled)
    private OrderState orderState;
    private String emailUser;
    /**
     * this is the constructor and it takes two parameters
     * @param orderLocation the location the order is headed to
     * @param orderState the current state of the order
     */
    public Order(String orderLocation, OrderState orderState,String emailUser) {
        this.orderId = generateRandomOrderId();
        this.orderDate = new Date();
        this.orderLocation = orderLocation;
        this.orderState = orderState;
        this.emailUser=emailUser;
    }

    /**
     * a default constructor
     */
    public Order(){}

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getEmailUser() {
        return emailUser;
    }
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
        Scanner scanner=new Scanner(System.in);
        foodItems.add(dish);
        System.out.println("Please enter how much you would like to order : ");
        int number= scanner.nextInt();
        quantites.add(number);
        totalPrice += (dish.price*number); // update total price
    }

    /**
     * this method uses the addFoodItem method to create new orders
     */
        public void makeOrder(){
        if (foodItems.isEmpty()){
            System.out.println("you should choose food first");
            return;
        }
        System.out.println("order includes : ");
        Restaurant restaurant=new Restaurant();
        restaurant.displayMenu(foodItems);
        Card card=new Card();
        System.out.println("if you want to pay with card please enter 1 : ");
        System.out.println("if you want to pay cash please enter 2 : ");
        int number=checkNumber(1,2,"number is wrong try again");
        if (number==1){
            card.SelectCard();
        }else if (number==2){
            System.out.println("total price is : "+totalPrice);
        }
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
    public  double getOrderPrice() { return totalPrice; }
    /**
     * gets the order location
     * @return returns order location
     */
    public String getOrderLocation() { return orderLocation; }
    /**
     * gets the order state
     * @return returns order state
     */
    public OrderState getOrderState() { return orderState; }
    public void setTotalPrice(double totalPrice){
        this.totalPrice=totalPrice;
    }

    /* === Setters ===*/

    /**
     * sets the order state
     * @param orderState takes the current state of the order
     */
    public void setOrderState(OrderState orderState) { this.orderState = orderState; }

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
                if (order.orderState== OrderState.Delivered||order.orderState==OrderState.Canceled){
                    continue;
                }
                bufferedWriter.write(order.emailUser+'\n');
                bufferedWriter.write(order.orderId+'\n');
                bufferedWriter.write(order.orderLocation+'\n');
                bufferedWriter.write(String.valueOf(order.orderState)+'\n');
                bufferedWriter.write(String.valueOf(order.totalPrice)+'\n');
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void setOrderId(String orderId){
        this.orderId=orderId;
    }
    public void setOrderLocation(String location){
        this.orderLocation=location;
    }
    public List<Order>loadData(){
        List <Order>orders=new ArrayList<>();
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader("Data/orders.txt"));
            String line;
            Order order=new Order();
            while ((line = bufferedReader.readLine())!=null){
                order.emailUser=line;
                line=bufferedReader.readLine();
                order.orderId=line;
                line= bufferedReader.readLine();
                order.orderLocation=line;
                line=bufferedReader.readLine();
                order.orderState= OrderState.valueOf(line);
                line=bufferedReader.readLine();
                order.totalPrice=Double.parseDouble(line);
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