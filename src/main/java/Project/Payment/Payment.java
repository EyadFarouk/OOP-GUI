package Project.Payment;



import Project.Interfaces.checkNumberValid;
import Project.Interfaces.saveAndLoad;
import Project.Orders.Order;

import java.util.Scanner;

public class Payment implements checkNumberValid, saveAndLoad {
    /**
     * Status of the payment ("Success", "Failed")
     */
    private String PaymentStatus;

    /**
     * Total price of the order
     */
    private double TotalPrice;

    /**
     * Scanner for user input
     */
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a Payment object with the specified order.
     *
     * @param order The order for which payment will be processed.
     */
    public Payment(Order order) {
        this.TotalPrice = order.getOrderPrice();  // Used to get the price
    }

    /**
     * Retrieves the current payment status.
     *
     * @return The payment status.
     */
    public String getPaymentStatus() {
        return PaymentStatus;
    }

    /**
     * Updates the payment status.
     *
     * @param paymentStatus The new payment status.
     */
    public void setPaymentStatus(String paymentStatus) {
        this.PaymentStatus = paymentStatus;
    }

    /**
     * Processes the checkout using a card.
     *
     * @param card The card to be used for payment.
     * @return A message indicating whether the payment was successful or failed.
     */
    public String CheckOut(Card card) {
        Card selectedCard = card.SelectCard();
        if (selectedCard == null) {
            setPaymentStatus("Failed");
            return "Payment Failed";
        }
        if (selectedCard.getMoneyAvailable() >= TotalPrice) {
            selectedCard.setMoneyAvailable(selectedCard.getMoneyAvailable() - TotalPrice);
            setPaymentStatus("Success");
            return "Payment Successful";
        } else {
            setPaymentStatus("Failed, please select a new card.");
            return "Payment Failed";
        }
    }

    /**
     * Handles cash payment for the order.
     * Outputs instructions for cash payment.
     */
    public void cashPayment() {
        System.out.println("Please pay " + TotalPrice + " in cash to the delivery person when they arrive.");
    }

    /**
     * Saves payment data to persistent storage (not implemented).
     */
    @Override
    public void saveData() {
    }

    /**
     * Loads payment data from persistent storage (not implemented).
     */
    @Override
    public void loadData() {
    }
}
