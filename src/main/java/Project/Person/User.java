package Project.Person;

import Project.Interfaces.checkNumberValid;

import java.util.Scanner;

/**
 * Abstract user class and the methods for the admin and customer subclasses
 */
abstract public class User implements checkNumberValid {
    String Fname;
    String Lname;
     String email;
    String phone;
    int age;
    String gender;
    String address;
    String password;

    /**
     * This method is used to make the user login
     */
    abstract public User login();

    /**
     * This method is used to make the customer signup and make a new account
     */
    public void signup(){
        System.out.println("Welcome in sign up page");
        Scanner scanner=new Scanner(System.in);
        System.out.print("Please enter your first name : ");
        this.Fname= checkNoNumbers(scanner.nextLine());
        System.out.print("Please enter your last name : ");
        this.Lname=checkNoNumbers(scanner.nextLine());
        System.out.print("Please enter your email : ");
        this.email=checkEmailValid(scanner.nextLine()).toLowerCase();
        System.out.print("Please enter your phone number : ");
        this.phone=checkOnlyNumbers(scanner.nextLine());
        System.out.print("Please enter your age : ");
        this.age=checkNumber(0,120,"Please enter a valid age: ");
        System.out.print("Please enter your gender : ");
        this.gender=checkGender(scanner.nextLine());
        System.out.print("Please enter your address : ");
        this.address=scanner.nextLine();
        System.out.print("Please enter your password : ");
        this.password=checkPasswordValid(scanner.nextLine());
    }

    /**
     * This method is used to check if the password is valid and can be used
     * @param password The inputted password that should be checked
     * @return The password after checking
     */
    private String checkPasswordValid(String password){
        while (true) {
            if (password.matches("^(?=.*[0-9])"       //Checks if the password has a number
                    + "(?=.*[a-z])"                         //Checks if the password has a small letter
                    + "(?=.*[A-Z])"                         //Checks if the password has a capital letter
                    + "(?=.*[!@#$%^&-+=()*])"                    //Checks if the password has a special character
                    + "(?=\\S+$).{8,20}$"))                //Checks if the password has at least 8 characters and has no white spaces
            {
                return password;
            }else{
                if (password.length() < 8) {
                    System.err.println("Password must be at least 8 characters");
                }
                if (!password.matches(".*\\d.*")) {
                    System.err.println("Password must have at least one number");
                }
                if (!password.matches(".*[a-z].*")) {
                    System.err.println("Password must have at least one lower case letter");
                }
                if (!password.matches(".*[A-Z].*")) {
                    System.err.println("Password must have at least one upper case letter");
                }
                if (!password.matches("^.*[!@#$%^&-+=()*].*")) {
                    System.err.println("Password must have at least one special character");
                }
                if (password.contains(" ")) {
                    System.err.println("Password mustn't have spaces");
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {

                }
                System.out.print("Enter a valid password: ");
                Scanner scanner=new Scanner(System.in);
                password = scanner.nextLine();
            }
        }
    }

    /**
     * This method is used to check if the inputted gender is valid
     *
     * @param gender The inputted gender that should be checked
     * @return The gender after checking
     */
    private String checkGender(String gender){
        while (true) {
            if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")) {
                return "Male";
            } else if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("f")) {
                return "Female";
            } else
                System.out.print("Please enter a valid gender: ");
            Scanner scanner=new Scanner(System.in);
            gender = scanner.nextLine();
        }
    }

    /**
     * This method is used to check if the inputted email is valid
     *
     * @param email the input that should be checked
     * @return The email after checking if it's valid
     */
    private String checkEmailValid(String email){
        while (true) {
            if(email.contains("@gmail.com")||email.contains("@yahoo.com")||email.contains("@outlook.com")||email.contains("@email.com")){
                return email;
            }
            else {
                System.err.println("Email address is not valid");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {

                }
                System.out.print("Please enter a valid email address: ");
                Scanner scanner=new Scanner(System.in);
                email = scanner.nextLine();
            }

        }
    }
    /**
     * This method is used to check if the inputted name has no numbers nor special characters
     *
     * @param name The inputted name that should be checked
     * @return The name after checking
     */
    private String checkNoNumbers(String name){
        while(true){
            if(name.matches(".*\\d.*") || name.matches("^.*[@#$%^&-+=()].*"))
            {
                Scanner scanner=new Scanner(System.in);
                System.out.print("The name can't contain numbers or special characters: ");
                name = scanner.nextLine();
            }else
                return name;
        }
    }

    private String checkOnlyNumbers(String number){
        while(true){
            if(number.matches("^[0-9]*$") && number.length() == 11){
                return number;
            }else{
                Scanner scanner=new Scanner(System.in);
                if(!number.matches("^[0-9]*$"))
                    System.err.println("The number can't contain non-numeric characters");
                if(number.length()!=11)
                    System.err.println("The number should be 11 digits");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {

                }
                System.out.print("Please enter a valid number: ");
                number = scanner.nextLine();
            }
        }
    }

    /**
     * This method is used to display the details of the user
     */
    public void displayUserInfo(){
        System.out.println("Name of user is : "+this.Fname+" "+this.Lname);
        System.out.println("Email of user is : "+this.email);
        System.out.println("phone of user is : "+this.phone);
        System.out.println("age of user is : "+this.age);
        System.out.println("gender of user is : "+this.gender);
        System.out.println("address of user is : "+this.address);
        System.out.println("Password of user is : "+this.password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }
}
