package Project.Resturants;


import Project.Person.Delivery_Staff;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is made to get the feedback of the customer
 */
public class Review {
    double scoreRating;

    public Restaurant restaurant;

    public String note;
    public String nameOfDelivery;


    public  int number_of_reviewsR=0,number_of_reviewsDS=0;
    public Review(){
        restaurant=new Restaurant();
    }
    /**
     * this is it's constructor, and it takes a restaurant object
     *
     * @param restaurant restaurant object
     */
    public Review(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    /**
     * This method sets the restaurant's rating and calculate the average rating
     *
     * @param Rating the rating the user gives
     */
    public void setReviewForRestaurant(double Rating) {
        number_of_reviewsR++;
        this.scoreRating = Rating;
        restaurant.rating = Math.round((restaurant.rating*(number_of_reviewsR-1)+Rating) / number_of_reviewsR*100.0)/100.0;
    }

    /**
     * gets the score rating
     * @return returns score rating
     */
    public double getScoreRating() {
        return scoreRating;
    }

    /**
     * This method is used to set the review of the delivery staff member and calculate the average rating
     * @param deliveryStaff the deliveryman's first name
     * @param Rating the rating you want to give him
     */
    public void setReviewForDeliveryStaff(Delivery_Staff deliveryStaff , double Rating)
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("If you have any note please enter : ");
        note=scanner.nextLine();
        number_of_reviewsDS++;
        nameOfDelivery=deliveryStaff.getFname();
        this.scoreRating = Rating;
        deliveryStaff.setRating((deliveryStaff.getRating()*(number_of_reviewsDS-1)+Rating) / number_of_reviewsDS);
    }
    public void saveData(List<Review>reviews){
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("Data/reviews.txt"));
             for (Review review : reviews){
                 bufferedWriter.write(review.restaurant.name+'\n');
                 bufferedWriter.write(String.valueOf(review.scoreRating)+'\n');
                 bufferedWriter.write(String.valueOf(review.number_of_reviewsR)+'\n');
                 bufferedWriter.write(review.note+'\n');
             }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Review>loadDataReviewRestaurant(){
        List<Review>reviews=new ArrayList<>();
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader("Data/reviews.txt"));
            String line;

            Review review=new Review();
            while ((line=bufferedReader.readLine())!=null){
                review.restaurant.name=line;
                line=bufferedReader.readLine();
                review.scoreRating=Double.parseDouble(line);
                line=bufferedReader.readLine();
                review.number_of_reviewsR=Integer.parseInt(line);
                line=bufferedReader.readLine();
                review.note=line;
                reviews.add(review);
                review=new Review();
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }
    public void saveDataReviewDelivery(List<Review>reviews){
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("Data/reviewsDelivery.txt"));
            for (Review review : reviews){
                bufferedWriter.write(review.nameOfDelivery+'\n');
                bufferedWriter.write(String.valueOf(review.scoreRating)+'\n');
                bufferedWriter.write(String.valueOf(review.number_of_reviewsDS)+'\n');
                bufferedWriter.write(review.note+'\n');
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Review>loadDataReviewDelivery(){
        List<Review>reviews=new ArrayList<>();
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader("Data/reviewsDelivery.txt"));
            String line;
            Review review=new Review();
            while ((line=bufferedReader.readLine())!=null){
                review.nameOfDelivery=line;
                line=bufferedReader.readLine();
                review.scoreRating=Double.parseDouble(line);
                line=bufferedReader.readLine();
                review.number_of_reviewsDS=Integer.parseInt(line);
                line=bufferedReader.readLine();
                review.note=line;
                reviews.add(review);
                review=new Review();
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }
}
