package Project.Payment;

import Project.Interfaces.checkNumberValid;
import Project.Interfaces.saveAndLoad;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Card implements checkNumberValid, saveAndLoad {

    private String CardNum;
    private String Cvv;
    private String ExpirationDate;
    private double MoneyAvailable;

    public static ArrayList<Card> cardList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    /**
     * Retrieves the card number.
     * @return The card number.
     */
    public String getCardNum() {
        return CardNum;
    }

    /**
     * Retrieves the CVV.
     * @return The CVV.
     */
    public String getCvv() {
        return Cvv;
    }

    /**
     * Retrieves the expiration date.
     * @return The expiration date.
     */
    public String getExpirationDate() {
        return ExpirationDate;
    }

    /**
     * Allows the user to select a card by entering its details.
     */
    public void SelectCard() {
        System.out.println("Please enter the number of the Card you wish to use: ");
        String inputCardNum = scanner.nextLine();
        for (Card Card : cardList) {
            if (Card.getCardNum().equals(inputCardNum)) {
                System.out.println("Item found: " + inputCardNum);
                System.out.println("Please enter the CVV of the Card you wish to use: ");
                String inputCvv = scanner.nextLine();
                if (Card.getCvv().equals(inputCvv)) {
                    System.out.println("CVV is correct.");
                    System.out.println("Please enter the expiration date of the Card (YY/MM): ");
                    String inputExpDate = scanner.nextLine();
                    if (Card.getExpirationDate().equals(inputExpDate)) {
                        System.out.println("Expiration date is correct.");
                    } else {
                        System.out.println("Wrong expiration date. Try again.");
                    }
                } else {
                    System.out.println("Incorrect CVV. Try again.");
                }
            }
        }
        System.out.println("Item not found or incorrect details entered.");
    }

    /**
     * Saves card data to a CSV file.
     */
    @Override
    public void saveData() {
        FileWriter fw;
        while (true) {
            try {
                fw = new FileWriter("Data/CardData.csv");
                fw.write("Card Number,CVV,Expiration date,Money available\n");
                for (Card card : cardList) {
                    fw.append(card.toString());
                }
                fw.close();
                break;
            } catch (FileNotFoundException e) {
                File file = new File("Data/");
                if (!file.exists()) {
                    file.mkdir();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Something went wrong with saving the data");
                break;
            }
        }
    }
    /**
     * Loads card data from a CSV file.
     */
    @Override
    public void loadData() {
        try {
            FileReader fr = new FileReader("Data/CardData.csv");
            BufferedReader br = new BufferedReader(fr);
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                Card card = new Card();
                String[] data = line.split(",");
                card.CardNum = data[0];
                card.Cvv = data[1];
                card.ExpirationDate = data[2];
                card.MoneyAvailable = Double.parseDouble(data[3]);
                cardList.add(card);
            }
            fr.close();
        } catch (FileNotFoundException e) {
            File file = new File("Data/");
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Converts the card details to a string format for saving.
     * @return A comma-separated string of card details.
     */
    @Override
    public String toString() {
        return this.CardNum + ',' + this.Cvv + ',' + this.ExpirationDate + ',' + this.MoneyAvailable + '\n';
    }

    public void setCvv(String cvv) {
        Cvv = cvv;
    }

    public void setCardNum(String cardNum) {
        CardNum = cardNum;
    }

    public void setExpirationDate(String expirationDate) {
        ExpirationDate = expirationDate;
    }

    public void setMoneyAvailable(double moneyAvailable) {
        MoneyAvailable = moneyAvailable;
    }
}