package deliveryapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskHistory implements ActionListener {

    private static final String COMPLETED_TASKS_FILE_PATH = "programData\\accepted_tasks.txt";
    private static final String ORDER_FILE_PATH = "programData\\Order.txt";

    private JDialog taskHistoryDialog;

    @Override
    public void actionPerformed(ActionEvent e) {
        createTaskHistoryDialog();
        displayCompletedOrders();
    }

    private void createTaskHistoryDialog() {
        taskHistoryDialog = new JDialog();
        taskHistoryDialog.setTitle("Task History");
        taskHistoryDialog.setSize(600, 400);
        taskHistoryDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        taskHistoryDialog.setLocationRelativeTo(null);
    }

    private void displayCompletedOrders() {
        ArrayList<Order> completedOrders = readCompletedOrdersFromFile();

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(new JLabel("<html><b><font size=+2>Completed Task History</font></b></html>"), gbc);

        if (!completedOrders.isEmpty()) {
            for (int i = 0; i < completedOrders.size(); i++) {
                addOrderToPanel(panel, completedOrders.get(i), i);
            }
        } else {
            gbc.gridy = 1;
            gbc.insets = new Insets(20, 0, 10, 0);
            panel.add(new JLabel("No completed tasks."), gbc);
        }

        taskHistoryDialog.add(panel);
        taskHistoryDialog.setVisible(true);
    }

    private void addOrderToPanel(JPanel panel, Order order, int index) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 2 + index * 7; // Updated row count
        JLabel taskLabel = new JLabel("Task #" + (index + 1));
        panel.add(taskLabel, gbc);

        // Add a clickable link or button for each task
        JButton viewButton = new JButton("View Details");
        gbc.gridy = 3 + index * 7; // Updated row count
        panel.add(viewButton, gbc);

        // ActionListener for the viewButton
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTaskDetails(order);
            }
        });

        gbc.insets = new Insets(10, 0, 10, 0);
    }

    private void showTaskDetails(Order order) {
        JDialog detailsDialog = new JDialog();
        detailsDialog.setTitle("Task Details");
        detailsDialog.setSize(400, 300);
        detailsDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        detailsDialog.setLocationRelativeTo(null);

        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        detailsPanel.add(new JLabel("<html><b><font size=+1>Task Details</font></b></html>"), gbc);

        gbc.gridy = 1;
        detailsPanel.add(new JLabel("Order ID: " + order.getOrderId()), gbc);

        gbc.gridy = 2;
        detailsPanel.add(new JLabel("Customer: " + order.getCustomerName()), gbc);

        gbc.gridy = 3;
        detailsPanel.add(new JLabel("Vendor: " + order.getVendorName()), gbc); // Display vendor name

        gbc.gridy = 4;
        detailsPanel.add(new JLabel("Delivery Location: " + order.getDeliveryLocation()), gbc);

        gbc.gridy = 5;
        detailsPanel.add(new JLabel("Cart: " + order.getCart()), gbc);

        detailsDialog.add(detailsPanel);
        detailsDialog.setVisible(true);
    }

    private ArrayList<Order> readCompletedOrdersFromFile() {
        ArrayList<Order> completedOrdersList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(COMPLETED_TASKS_FILE_PATH));
             BufferedReader orderReader = new BufferedReader(new FileReader(ORDER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] orderData = line.split(";");
                if (orderData.length >= 5) {
                    String orderId = orderData[0];
                    String customerName = orderData[1];
                    String deliveryLocation = orderData[2];
                    String cart = orderData[3];

                    // Read vendor name from Order.txt
                    String vendorName = getVendorNameFromOrderFile(orderId);

                    Order order = new Order(orderId, customerName, deliveryLocation, cart, vendorName);
                    completedOrdersList.add(order);
                } else {
                    System.err.println("Invalid order format: " + line);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return completedOrdersList;
    }

    private String getVendorNameFromOrderFile(String orderId) throws IOException {
        try (BufferedReader orderReader = new BufferedReader(new FileReader(ORDER_FILE_PATH))) {
            String line;
            while ((line = orderReader.readLine()) != null) {
                String[] orderData = line.split(";");
                if (orderData.length >= 3 && orderData[2].equals(orderId)) {
                    // Assuming vendor name is in the second column of Order.txt
                    return orderData[1];
                }
            }
        }
        return "N/A"; // If vendor name is not found
    }

    private static class Order {
        private String customerName;
        private String orderId;
        private String cart;
        private String deliveryLocation;
        private String vendorName; // New field for vendor name

        public Order(String orderId, String customerName, String deliveryLocation, String cart, String vendorName) {
            this.customerName = customerName;
            this.orderId = orderId;
            this.cart = cart;
            this.deliveryLocation = deliveryLocation;
            this.vendorName = vendorName;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getCart() {
            return cart;
        }

        public String getDeliveryLocation() {
            return deliveryLocation;
        }

        public String getVendorName() {
            return vendorName;
        }
    }
}
