package deliveryapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Order {
    
    public enum OrderStatus {
        PENDING,
        IN_PROGRESS,
        DELIVERED,
        CANCELED,
        UNKNOWN
    }
    
    private String orderId;
    private String customerUsername;
    private String deliveryLocation;
    private Date orderDate;
    private ArrayList<Integer> cart; // List of item IDs in the order
    private double totalAmount;
    private OrderStatus status;
    private static final String MENU_PATH = "programData\\menu.txt";

    public Order(String orderId, String customerUsername, String deliveryLocation, Date orderDate, ArrayList<Integer> cart) {
        this.orderId = orderId;
        this.customerUsername = customerUsername;
        this.deliveryLocation = deliveryLocation;
        this.orderDate = orderDate;
        this.cart = cart;
        this.totalAmount = calculateTotalAmount();
        this.status = OrderStatus.PENDING; // Initialize as pending
    }

    // Getters and Setters for order attributes

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public ArrayList<Integer> getCart() {
        return cart;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    // Method to calculate the total price of the order
    private double calculateTotalAmount() {
        double total = 0.0;
        // Calculate the total amount based on the item IDs in the cart
        // Get price list from menu and sum up everything
        for (int itemId : cart) {
            double itemPrice = getItemPrice(itemId);
            total += itemPrice;
        }
        return total;
    }

    private double getItemPrice(int itemId) {
        // Fetch the item price from file
        try {
            Scanner scanner = new Scanner(new File(MENU_PATH));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                for (int i = 1; i < parts.length; i++) {
                    String[] itemDetails = parts[i].split("\\|");
                    if (itemDetails.length == 4) {
                        int currentItemId = Integer.parseInt(itemDetails[0]);
                        if (currentItemId == itemId) {
                            return Double.parseDouble(itemDetails[3]);
                        }
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return 0.0; // Default value if item price is not found
    }

    // Update order status
    public void updateStatus(OrderStatus newStatus) {
        status = newStatus;
    }

    // Cancel order
    public void cancelOrder() {
        status = OrderStatus.CANCELED;
    }
}

