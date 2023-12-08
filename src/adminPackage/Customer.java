package adminPackage;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String address;
    private double amount; 
    private List<String> topUpHistory; 

    // Constructor
    public Customer(String username, String password, String phoneNumber, String address) {
        super(username, password, phoneNumber);
        this.address = address;
        this.amount = 0; 
        this.topUpHistory = new ArrayList<>(); 
    }

    // Method to top up the customer's balance
    public void topUpAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Top-up amount must be greater than zero.");
        }
        this.amount += amount; 
        recordTopUp(amount);
    }

    // Record a top-up transaction
    private void recordTopUp(double amount) {
        
        String receipt = "Top-up of " + amount + " on " + new java.util.Date();
        this.topUpHistory.add(receipt);
    }

    // Getters and Setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
    this.amount = amount;
}


    public List<String> getTopUpHistory() {
        return new ArrayList<>(topUpHistory); 
    }

    @Override
    public String toString() {
        return super.toString() + ";" + getAddress() + ";" + getAmount();
    }
}
