package Project.Interfaces;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * An interface to check numbers if they are valid and in the desired range
 */
public interface checkNumberValid {

    /**
     * This method is used to check the number if it's a real number and check if it's in the range
     * @param LowerNumber The least number that the user can choose from
     * @param HighestNumber The highest number that the user can choose from
     * @param s The error message that should appear if the user didn't enter a real number
     * @return Returns an int number after checking
     */
    default int checkNumber(int LowerNumber,int HighestNumber, String s){
        Scanner scanner=new Scanner(System.in);
        while (true) {
            try {
                int x=scanner.nextInt();

                if(!(x>=LowerNumber) || !(x<=HighestNumber) && HighestNumber!=100)
                    System.out.print("Please enter a valid number between " + LowerNumber + " and " + HighestNumber + ": ");
                else return x;
            } catch (InputMismatchException e) {
                System.out.print(s);
            }
            scanner.nextLine(); // clears the buffer
        }
    }
    /**
     * This method is used to check the number if it's a real number and check if it's in the range
     * @param LowerNumber The least number that the user can choose from
     * @param HighestNumber The highest number that the user can choose from
     * @param s The error message that should appear if the user didn't enter a real number
     * @return Returns a double number after checking
     */
    default double checkNumber(double LowerNumber,double HighestNumber, String s){
        Scanner scanner=new Scanner(System.in);
        while (true) {
            try {
                double x=scanner.nextDouble();

                if(!(x>=LowerNumber) || !(x<=HighestNumber) && HighestNumber!=100.0)
                    System.out.print("Please enter a valid number between " + LowerNumber + " and " + HighestNumber + ": ");
                else return x;
            } catch (InputMismatchException e) {
                System.out.print(s);
            }
            scanner.nextLine(); // clears the buffer
        }
    }
}
