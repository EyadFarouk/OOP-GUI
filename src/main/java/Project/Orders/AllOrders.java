package Project.Orders;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AllOrders {
    public static List<Order> orderList=new ArrayList<>();

    public void saveData(){
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("Data/all orders.txt"));
            for (Order order:orderList){
                bufferedWriter.write(order.getEmailUser()+'\n');
                bufferedWriter.write(order.getOrderId()+'\n');
                bufferedWriter.write(order.getOrderLocation()+'\n');
                bufferedWriter.write(String.valueOf(order.getOrderState())+'\n');
                bufferedWriter.write(String.valueOf(order.getOrderPrice())+'\n');
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public AllOrders loadData(){
        AllOrders allOrders=new AllOrders();
        if ( new File("Data/all orders.txt") .exists()){
          try {
                BufferedReader bufferedReader=new BufferedReader(new FileReader("Data/all orders.txt"));
                String line;
                List<Order>orderList1=new ArrayList<>();
                while((line=bufferedReader.readLine())!=null){
                  Order order=new Order();
                  order.setEmailUser(line);
                  line=bufferedReader.readLine();
                  order.setOrderId(line);
                  line= bufferedReader.readLine();
                  order.setOrderLocation(line);
                  line=bufferedReader.readLine();
                  order.setOrderState(OrderState.valueOf(line));
                  line=bufferedReader.readLine();
                  order.setTotalPrice(Double.parseDouble(line));
                  orderList1.add(order);
               }
                AllOrders.orderList=orderList1;
                bufferedReader.close();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
          return allOrders;
    }
}
