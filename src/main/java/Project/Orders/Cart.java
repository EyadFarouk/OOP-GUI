package Project.Orders;


import Project.Resturants.Dish;

import java.util.List;

public class Cart {
    public static List<Dish> menus;
    public   static List<Integer> quantities;
    public static List<String> adds;
    public static void displayCart(){
        for (int i=0;i<Cart.menus.size();i++){
            System.out.println("Name of dish "+(i+1)+" : "+Cart.menus.get(i).name);
            System.out.println("Name of quantities "+(i+1)+" : "+Cart.quantities.get(i));
        }
    }
    public static void deleteItem(int numberOfItem){
        menus.remove(numberOfItem-1);
        quantities.remove(numberOfItem-1);
        adds.remove(numberOfItem-1);
    }
    public static void addItem(Dish menu, int quantities, String add){
        menus.set(menus.size(),menu);
        Cart.quantities.set(Cart.quantities.size(),quantities);
        adds.set(adds.size(),add);
    }
    public static void modifyQuantitie(int numberOfItem,int newQuantitie){
        quantities.set(numberOfItem,newQuantitie);
    }
}
