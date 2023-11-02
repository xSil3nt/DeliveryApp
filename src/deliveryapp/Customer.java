package deliveryapp;

import java.util.ArrayList;
import java.util.Date;

public class Customer extends User {
    private String location;
    private double balance;
    private ArrayList<Integer> cart = new ArrayList<>(); // List of item IDs in the cart
    private ArrayList<Order> orderHistory = new ArrayList<>(); // List of orders
    private int customerId;
    
    public Customer(String username, String password, int customerId, double balance) {
        super(username, password, 2); // Assuming 2 represents the user type for customers
        this.balance = balance;
        this.customerId = customerId;
    }
    
    public int getCustomerId() {
        return customerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public String viewReviews(int vendorId) {
        // Implement this method to view reviews for a vendor
        return "reviews";
    }
    
    public String selectVendor(int vendorId) {
        // Implement this method to select a vendor and view their menu
        return "menu";
    }
    
    public void addToCart(int itemId) {
        cart.add(itemId);
    }
    
    public void clearCart() {
        cart.clear();
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
     
    public String getLocation() {
        return location;
    }
    
    public int placeOrder() {
        int orderId = 0; //Generate an orderid, something like item ids + customer id + date and b64 encode? may change this to string
        Order newOrder = new Order(orderId, getCustomerId(), location, new Date(), new ArrayList<>(cart));
        orderHistory.add(newOrder);
        cart.clear();
        return orderId;
    }
    
    public void cancelOrder(int orderId) {
        // Implement this method to cancel a specific order
    }
    
    public ArrayList<Order> viewOrders() {
        return orderHistory;
    }
    
    
}
