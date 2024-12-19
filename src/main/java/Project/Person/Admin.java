package Project.Person;



import Project.Interfaces.checkNumberValid;
import Project.Interfaces.saveAndLoad;
import Project.Resturants.Dish;
import Project.Resturants.Restaurant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Admin class and the methods for the admin
 */
public class Admin extends User implements saveAndLoad, checkNumberValid {
    public static List<Restaurant>restaurants;
    public static List<Admin> adminList=new ArrayList<>();


    static public void instance(){
        Admin Eyad=new Admin();
        Eyad.email="AEyad";
        Eyad.password="AEyad";
        adminList.add(Eyad);
        for (int i = 0; i < 10; i++) {
            Admin admin=new Admin();
            admin.Fname="AFname"+i;
            admin.Lname="ALname"+i;
            admin.email="Aemail"+i+"@email.com";
            admin.phone="Aphone"+i;
            admin.age=1;
            admin.gender="Amale";
            admin.address="Aaddress"+i;
            admin.password="Apassword"+i;
            adminList.add(admin);
            admin.displayUserInfo();
        }
    }

    public  Restaurant getRestaurant(String name){
        try {
            for (Restaurant restaurant:restaurants){
                if (name.equals(restaurant.name)){
                    return restaurant;
                }
            }
        }catch (Exception e){
            System.out.println("Restaurant not found");
        }
        return null;
    }
    public  int getMenu(String name,Restaurant restaurant){
        int i;
        try {
            for (i=0;i<restaurant.menu.size();i++){
                if(name.equals(restaurant.menu.get(i).name)){
                    return i;
                }
            }
        }catch (Exception e){
            System.out.println("Dish not found");
        }
        return 10000;
    }
    public  void addRestaurant(){
        Restaurant restaurant=new Restaurant();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter name of restaurant : ");
        restaurant.name=scanner.nextLine();
        System.out.println("Please enter address of restaurant : ");
        restaurant.address=scanner.nextLine();
        System.out.println("Please enter contact information : ");
        restaurant.contactInformation=scanner.nextLine();
        System.out.println("Please enter rating of restaurant : ");
        restaurant.rating=checkNumber(0.0,5.0,"Please enter a valid rating: ");
        System.out.println("Please enter url of location of restaurant : ");
        restaurant.uri =scanner.nextLine();
        System.out.println("Please enter number of item in menu : ");
        int x=checkNumber(0,100,"Please enter a valid number of item: ");
        Dish dish=new Dish();
        for(int i=0;i<x;i++){
            AddDish(scanner, dish);
            restaurant.menu.add(dish);
            dish=new Dish();
        }
        restaurants.add(restaurants.size(), restaurant);

    }
    public  void deleteRestaurant(){
        Scanner scanner=new Scanner(System.in);
        String name;
        System.out.println("Please enter name of restaurant : ");
        name=scanner.nextLine();
        restaurants.remove(getRestaurant(name));

    }
    public  void addMenu( ){
        String name;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter name of restaurant : ");
        name=scanner.nextLine();
        System.out.println("Please enter number of item in menu : ");
        Restaurant res = getRestaurant(name);
        int x=checkNumber(1,100,"Please enter a valid number of item: ");
        Dish dish=new Dish();
        for(int i=0;i<x;i++){
            AddDish(scanner, dish);
            res.menu.add(dish);
            dish=new Dish();
        }
    }

    private void AddDish(Scanner scanner, Dish dish) {
        System.out.println("Please enter name of dish : ");
        dish.name=scanner.nextLine();
        System.out.println("Please enter description of dish : ");
        dish.description=scanner.nextLine();
        System.out.println("Please enter price of dish : ");
        dish.price=checkNumber(1.0,100.0,"Please enter a valid price: ");
        System.out.println("Please enter type of dish : ");
        dish.categories=scanner.nextLine();
        System.out.println("Please enter ration of dish : ");
        dish.rating=checkNumber(1.0,100.0,"Please enter a valid rating of the dish: ");
    }

    public  void deleteMenu(){
        Scanner scanner=new Scanner(System.in);
        String name;
        System.out.println("Please enter name of restaurant : ");
        name=scanner.nextLine();
        if (getRestaurant(name)==null){
            System.out.println("Restaurant not found");
        }else{
            getRestaurant(name).menu=new ArrayList<>();
        }


    }

    /**
     * This method is used to make the Admin remove
     */
    public  void deleteDish(String nameOfRestaurant,String nameOfDish){
        Restaurant restaurant=getRestaurant(nameOfRestaurant);
        if (restaurant==null){
            System.out.println("Restaurant is not found");
        }
        else{
            int index=getMenu(nameOfDish,restaurant);
            if (index==10000){
                System.out.println("Dish not found");
            }
            else{
                restaurant.menu.remove(index);
            }

        }
    }

    /**
     * This method is used to make the admin signup and make a new account
     */
    @Override
    public void signup() {
        super.signup();
        checkEmailUnique();
        System.out.println("Sign up succeful");
        adminList.add(this);
    }

    /**
     * This method is used to check if the email hasn't been used in another account
     */
    private void checkEmailUnique(){
        Scanner scanner=new Scanner(System.in);
        while (true) {
            boolean exists=false;
            for (Admin admin:adminList) {
                if (this.email.equals(admin.email)) {
                    System.err.print("I'm sorry email must be unique. Please enter another Email: ");
                    exists=true;
                }
            }
            if (!exists) {
                break;
            }
            this.email=scanner.nextLine();
        }
    }

    /**
     * This method is used make the admin login to the system
     */
    @Override
    public Admin login() {
        System.out.println("Welcome in Log in page");
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter your email : ");
            this.email = scanner.nextLine();
            System.out.print("Please enter your password : ");
            this.password = scanner.nextLine();
            for (int i = 0; i < adminList.size(); i++) {
                if (this.email.equalsIgnoreCase(adminList.get(i).email)) {
                    if (this.password.equals(adminList.get(i).password)) {
                        System.out.println("log in success");
                        return adminList.get(i);
                    } else if (i == adminList.size() - 1) {
                        System.out.println("The email or password is not correct. Try again");
                    }
                } else if (i == adminList.size() - 1) {
                    System.out.println("The email could not be found. Try again");
                }
            }
        }while (true);
    }

    /**
     * This method is used to save the data in the AdminData.csv file
     */
    @Override
    public void saveData() {
        FileWriter fw;
        while (true) {
            try {
                fw = new FileWriter("Data/AdminData.csv");
                fw.write("FName,LName,Email,Phone,Age,Gender,Address,Password\n");
                for (Admin admin : adminList) {
                    fw.append(admin.toString());
                }
                fw.close();
                break;
            } catch (FileNotFoundException e) {
                File file = new File("Data/");
                if (!file.exists()) {file.mkdir();}
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Something went wrong with saving the data");
                break;
            }
        }
    }

    /**
     * This method is used to load the data from the AdminData.csv file
     */
    @Override
    public void loadData() {
        try {
            FileReader fr = new FileReader("Data/AdminData.csv");
            BufferedReader br = new BufferedReader(fr);
//            int i=0;
            br.readLine();
            String line;
            while((line = br.readLine()) != null) {
//                System.out.println(line);
                Admin admin= new Admin();
                admin.Fname=line.split(",")[0];
                admin.Lname=line.split(",")[1];
                admin.email=line.split(",")[2];
                admin.phone=line.split(",")[3];
                admin.age=Integer.parseInt(line.split(",")[4]);
                admin.gender=line.split(",")[5];
                admin.address=line.split(",")[6];
                admin.password=line.split(",")[7];
                adminList.add(admin);
//                System.out.println(userList.get(i).toString());
//                i++;
            }
            fr.close();
        }catch (FileNotFoundException e) {
            File file = new File("Data/");
            if (!file.exists()) {file.mkdir();}
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is used to turn the class values into a string
     * @return A string consisting of the data of the customer
     */
    public String toString() {
        return this.Fname + ',' + this.Lname + ',' + this.email + ',' + this.phone + ',' + this.age + ',' + this.gender + ',' + this.address + ',' + this.password +'\n';
    }
}
