package deliveryapp;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    
    public enum OrderStatus {
        PENDING,
        IN_PROGRESS,
        DELIVERED,
        CANCELED,
        UNKNOWN
    }
    
    private int orderId;
    private String customerUsername;
    private String deliveryLocation;
    private Date orderDate;
    private ArrayList<Integer> cart; // List of item IDs in the order
    private double totalAmount;
    private OrderStatus status;

    public Order(int orderId, String customerUsername, String deliveryLocation, Date orderDate, ArrayList<Integer> cart) {
        this.orderId = orderId;
        this.customerUsername = customerUsername;
        this.deliveryLocation = deliveryLocation;
        this.orderDate = orderDate;
        this.cart = cart;
        this.totalAmount = calculateTotalAmount();
        this.status = OrderStatus.PENDING; // Initialize as pending
    }

    // Getters and Setters for order attributes

    public int getOrderId() {
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
        // Get price list from file/menu and sum up everything
        for (int itemId : cart) {
            //double itemPrice = getItemPriceFromDatabase(itemId);
            //total += itemPrice;
        }
        return total;
    }

    // Get price of item
    private double getItemPriceFromDatabase(int itemId) {
        // Fetch the item price from file 
        // Return the item price
        return 10.0; // PLACEHOLDER
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

