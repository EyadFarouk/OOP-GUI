package Project.Payment;

import Project.Interfaces.checkNumberValid;
import Project.Interfaces.saveAndLoad;

import java.io.*;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Card implements checkNumberValid, saveAndLoad {

    private String Regex = "[0-9]+";
    private String CardNum;
    private String Cvv;
    private String ExpirationDate;
    private double MoneyAvailable;

    static ArrayList<Card> cardList = new ArrayList<>();

    YearMonth currentDate = YearMonth.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM");
    String formattedDate = currentDate.format(formatter);
    Scanner scanner = new Scanner(System.in);

    public static void instance() {
        Card card = new Card();
        card.CardNum = "1234565431";
        card.Cvv = "968";
        card.ExpirationDate = "30/12";
        card.MoneyAvailable = 0.01;
        cardList.add(card);

        Card card2 = new Card();
        card2.CardNum = "12345678987653";
        card2.Cvv = "245";
        card2.ExpirationDate = "29/06";
        card2.MoneyAvailable = 0.01;
        cardList.add(card2);

        Card card3 = new Card();
        card3.CardNum = "123456789012234";
        card3.Cvv = "4132";
        card3.ExpirationDate = "28/07";
        card3.MoneyAvailable = 0.01;
        cardList.add(card3);
    }

    public String getCardNum() {
        return CardNum;
    }

    public void setCardNum(String CardNum) {
        this.CardNum = CardNum;
    }

    public String getCvv() {
        return Cvv;
    }

    public void setCvv(String Cvv) {
        this.Cvv = Cvv;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(String ExpirationDate) {
        this.ExpirationDate = ExpirationDate;
    }

    public double getMoneyAvailable() {
        return MoneyAvailable;
    }

    public void setMoneyAvailable(double MoneyAvailable) {
        this.MoneyAvailable = MoneyAvailable;
    }

    public void AddNewCard(Card card) {
        System.out.print("Please enter your Card number: ");
        while (true) {
            this.CardNum = scanner.nextLine();
            if (this.CardNum.length() >= 12 && this.CardNum.length() <= 16 && this.CardNum.matches(Regex)) {
                break;
            } else {
                System.out.println("Invalid number. Card number must be between 12 and 16 digits. Please try again:");
            }
        }

        System.out.print("Please enter your CVV number: ");
        while (true) {
            this.Cvv = scanner.nextLine();
            if (this.Cvv.length() >= 3 && this.Cvv.length() <= 4 && this.Cvv.matches(Regex)) {
                break;
            } else {
                System.out.println("Invalid number. CVV must be between 3 and 4 digits. Please try again:");
            }
        }

        System.out.print("Enter Expiration Date (YY/MM): ");
        while (true) {
            this.ExpirationDate = scanner.nextLine();
            if (formattedDate.compareTo(this.ExpirationDate) < 0 && this.ExpirationDate.matches("\\d{2}/\\d{2}")) {
                System.out.println("Please enter money available:");
                this.MoneyAvailable = scanner.nextDouble();
                scanner.nextLine();
                cardList.add(this);
                System.out.println("Card is valid and has been saved.");
                break;
            } else {
                System.out.println("Invalid or expired date. Please try again:");
            }
        }
    }

    public void removeCard(Card card) {
        for (int i = 0; i < cardList.size(); i++) {
            System.out.println("Enter the number of the Card:");
            this.CardNum = scanner.nextLine();
            if (cardList.get(i).getCardNum().equals(this.CardNum)) {
                cardList.remove(i);
                break;
            }
        }
    }

    public Card SelectCard() {
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
                        return Card;
                    } else {
                        System.out.println("Wrong expiration date. Try again.");
                    }
                } else {
                    System.out.println("Incorrect CVV. Try again.");
                }
            }
        }
        System.out.println("Item not found or incorrect details entered.");
        return null;
    }

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

    @Override
    public void loadData() {
        try {
            FileReader fr = new FileReader("Data/CardData.csv");
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
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

    @Override
    public String toString() {
        return this.CardNum + ',' + this.Cvv + ',' + this.ExpirationDate + ',' + this.MoneyAvailable + '\n';
    }
}
