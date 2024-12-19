package Project.Resturants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * this class is make to use to admin set report for a restaurant
 * */
public class Report {
    Restaurant restaurant=new Restaurant();
    String report;
    /**
     * this method is make to set report
     * @param restaurants this is list of restaurant to select restaurant from
     * */
    public void setReport(List<Restaurant>restaurants){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter name of restaurant : ");
        String name=scanner.nextLine();
        for (Restaurant restaurant1:restaurants){
            if (name.equals(restaurant1.name)){
                restaurant=restaurant1;
                break;
            }
        }
        if (restaurant.name!=null){
            System.out.println("Please enter your report : ");
            report=scanner.nextLine();
        }else{
            System.out.println("Name is wrong try again");
        }
    }
    /**
     * this method to save data in file
     * @param reports to save element in file
     * */
    public void saveData(List<Report>reports){
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("Data/reports.txt"));
            for (Report report1:reports){
                bufferedWriter.write(report1.restaurant.name+'\n');
                bufferedWriter.write(report1.report+'\n');
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * this method to load data from file
     * */
    public List<Report>loadData(){
        List<Report>reports=new ArrayList<>();
        String line;
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader("Data/reports.txt"));
            while((line=bufferedReader.readLine())!=null){
                Report report1=new Report();
                report1.restaurant.name=line;
                report1.report=bufferedReader.readLine();
                reports.add(report1);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return reports;
    }
}
