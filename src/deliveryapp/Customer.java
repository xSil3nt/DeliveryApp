package deliveryapp;

import deliveryapp.Order.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Customer extends User {
    private String location;
    private double balance;
    private ArrayList<Integer> cart = new ArrayList<>(); // List of item IDs in the cart
    private ArrayList<Order> orderHistory = new ArrayList<>(); // List of orders
    private String phone;
    
    public Customer(String username, String password, double balance) {
        super(username, password, 2); // Usertype 2 is for customer
        this.balance = balance;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    

    public String getPhone() {
        return phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public void addToCart(int itemId) {
        cart.add(itemId);
    }
    
    public void clearCart() {
        cart.clear();
    }
    
    public ArrayList<Integer> getCart() {
        return cart;
    }

    public void removeFromCart(int itemId) {
        cart.remove(Integer.valueOf(itemId));
    }

    
    public void setLocation(String location) {
        this.location = location;
    }
     
    public String getLocation() {
        return location;
    }
    
    public String placeOrder() {
        String orderId = generateOrderId(); 
        ArrayList<Integer> tempCart = new ArrayList<Integer>(cart);
        Order newOrder = new Order(orderId, super.getUsername(), location, new Date(), tempCart);
        orderHistory.add(newOrder);
        cart.clear();
        return orderId;
    }
    
    
    private String generateOrderId() {
        // Use SimpleDateFormat to format the current date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());

        // Combine timestamp with customer username to create a unique order ID
        String orderId = timestamp + super.getUsername();
        orderId = calculateSHA256(orderId); //Calc SHA256 and use first 12 chars

        return orderId;
    }
    
    private static String calculateSHA256(String input) {
        try {
            // Create a MessageDigest object for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Update the digest with the input string
            md.update(input.getBytes());

            // Get the hash bytes
            byte[] hashBytes = md.digest();

            // Convert the bytes to a hexadecimal string
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                hexStringBuilder.append(String.format("%02x", b));
            }

            // Truncate the string to 12 characters
            String truncatedHash = hexStringBuilder.toString().substring(0, 12);

            return truncatedHash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public ArrayList<Order> viewOrders() {
        return orderHistory;
    }
    
    public Order findOrderById(String orderId) {
        for (Order order : orderHistory) {
            if (order.getOrderId().equals(orderId)) {
                return order; 
            }
        }
        return null;
    }

    
    public int reOrder(String orderId) {
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
    
    public OrderStatus orderStatus(String orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            return order.getStatus();
        } else {
            return OrderStatus.UNKNOWN; 
        }
    }
    
    
}
