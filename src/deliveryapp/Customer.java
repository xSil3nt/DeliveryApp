package deliveryapp;

import deliveryapp.Order.*;
import java.util.ArrayList;
import java.util.Date;

public class Customer extends User {
    private String location;
    private double balance;
    private ArrayList<Integer> cart = new ArrayList<>(); // List of item IDs in the cart
    private ArrayList<Order> orderHistory = new ArrayList<>(); // List of orders
    private boolean loggedIn;
    
    public Customer(String username, String password, double balance) {
        super(username, password, 2); // Usertype 2 is for customer
        this.balance = balance;
    }
    
    public int customerLogin(String username, String password) {
        if ((super.checkUsername(username) == 0) && (super.checkPassword(password) == 0)) {
            loggedIn = true;
            return 0; // Success
        } else
            return 1; // Fail
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
        Order newOrder = new Order(orderId, super.getUsername(), location, new Date(), new ArrayList<>(cart));
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
    
    private Order findOrderById(int orderId) {
        for (Order order : orderHistory) {
            if (order.getOrderId() == orderId) {
                return order; // Return the order if found
            }
        }
        return null; // Return null to indicate that the order with the specified orderId was not found in orderHistory
    }
    
    public int reOrder(int orderId) {
        Order originalOrder = findOrderById(orderId);
        if (originalOrder != null) {
            // Duplicate the items from the original order
            cart = originalOrder.getCart();
            // Place a new order
            placeOrder();
            return 0;
        } else {
            // Return -1 to indicate error if order is not found
            return -1;
        }
    }
    
    public OrderStatus orderStatus(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            return order.getStatus();
        } else {
            return OrderStatus.UNKNOWN; 
        }
    }

    public void writeReview(int orderId, String review) {
        //Implement review object?
    }
    
    
}
