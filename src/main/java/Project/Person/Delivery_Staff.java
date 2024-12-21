package Project.Person;


import Project.Interfaces.saveAndLoad;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is the class Delivery Staff that displays the delivery man. It contains attributes:
 * 1- First Name
 * 2- last Name
 * 3- Location
 * 4- Rating
 */
public class Delivery_Staff extends User implements saveAndLoad {

    private double rating;               //kept private to be set by admin only
    String Location;
    public static List<Delivery_Staff> staffList=new ArrayList<>();

    /**
     * Constructor to instantiate delivery man
     * @param location shows the current location of the delivery man
     */

    public Delivery_Staff(String location) {
        this.Location = location;
    }

    /**
     * getter for rating
     * @return returns delivery man's rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * setter for rating
     * @param rating sets the rating of the delivery man (to be set by admin/customer)
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setFname(String fname){
        super.Fname=fname;
    }
    public String getFname(){return super.Fname;}

    /**
     * this method is used to save the data
     */
    @Override
    public void saveData() {
        FileWriter fw;
        while(true) {
            try {
                fw = new FileWriter("Data/DeliveryStaffData.csv");
                fw.write("FName,LName,Email,Phone,Age,Gender,Address,Password,Location\n");
                for (Delivery_Staff deliveryStaff : staffList) {
                    fw.append(deliveryStaff.toString());
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
     * this method is used to load the data
     */
    @Override
    public void loadData() {
        try {
            FileReader fr = new FileReader("Data/DeliveryStaffData.csv");
            BufferedReader br = new BufferedReader(fr);
//            int i=0;
            br.readLine();
            String line;
            while((line = br.readLine()) != null) {
//                System.out.println(line);
                Delivery_Staff deliveryStaff= new Delivery_Staff(line.split(",")[8]);
                deliveryStaff.Fname=line.split(",")[0];
                deliveryStaff.Lname=line.split(",")[1];
                deliveryStaff.email=line.split(",")[2];
                deliveryStaff.phone=line.split(",")[3];
                deliveryStaff.age=Integer.parseInt(line.split(",")[4]);
                deliveryStaff.gender=line.split(",")[5];
                deliveryStaff.address=line.split(",")[6];
                deliveryStaff.password=line.split(",")[7];
                staffList.add(deliveryStaff);
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

    @Override
    public User login() {
        System.out.println("Welcome in Log in page");
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter your email : ");
            this.email = scanner.nextLine();
            System.out.print("Please enter your password : ");
            this.password = scanner.nextLine();
            for (int i = 0; i < staffList.size(); i++) {
                if (this.email.equalsIgnoreCase(staffList.get(i).email)) {
                    if (this.password.equals(staffList.get(i).password)) {
                        System.out.println("log in success");
                        return staffList.get(i);
                    } else if (i == staffList.size() - 1) {
                        System.out.println("The email or password is not correct. Try again");
                    }
                } else if (i == staffList.size() - 1) {
                    System.out.println("The email could not be found. Try again");
                }
            }
        }while (true);
    }

    public String toString() {
        return this.Fname + ',' + this.Lname + ',' + this.email + ',' + this.phone + ',' + this.age + ',' + this.gender + ',' + this.address + ',' + this.password + ',' + this.Location+'\n';
    }

}
