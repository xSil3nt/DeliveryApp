package deliveryapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ViewTask implements ActionListener {

    private ArrayList<Order> orders;
    private int currentOrderIndex = -1;
    private final String ordersFilePath = "programData\\Order.txt";
    private final String acceptedTasksFilePath = "programData\\accepted_tasks.txt";
    private final String declinedTasksFilePath = "programData\\Declined_Tasks.txt";

    private JDialog taskDetailsDialog;

    public ViewTask() {
        this.orders = readOrdersFromFile();
    }

    private ArrayList<Order> readOrdersFromFile() {
        ArrayList<Order> ordersList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ordersFilePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String[] orderData = line.split(";");
                if (orderData.length >= 8) {
                    String orderStatus = orderData[7].toUpperCase();

                    if (orderStatus.equals("DELIVERY_PENDING")) {
                        Order order = new Order(orderData[0], orderData[2], orderData[4], orderData[5]);
                        ordersList.add(order);
                    }
                } else {
                    System.out.println("Invalid order format at line " + lineNumber + ": " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ordersList;
    }

    private boolean isTaskAlreadyProcessed(String orderId) {
        // Check if the task has already been accepted or declined
        return isOrderIdInFile(orderId, acceptedTasksFilePath) || isOrderIdInFile(orderId, declinedTasksFilePath);
    }

    private boolean isOrderIdInFile(String orderId, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] orderData = line.split(";");
                if (orderData.length >= 1 && orderData[0].equals(orderId)) {
                    return true; // Order ID found in the file
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Order ID not found in the file
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!orders.isEmpty()) {
            showNextOrder();
        } else {
            JOptionPane.showMessageDialog(null, "No tasks available.", "No Tasks", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showNextOrder() {
        if (currentOrderIndex < orders.size() - 1) {
            currentOrderIndex++;

            // Check if the task has already been accepted or declined
            while (isTaskAlreadyProcessed(orders.get(currentOrderIndex).getOrderId())) {
                currentOrderIndex++;
                if (currentOrderIndex >= orders.size()) {
                    JOptionPane.showMessageDialog(null, "No more tasks available.", "No Tasks", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

            createTaskDetailsDialog();
            displayOrderDetails();
        } else {
            JOptionPane.showMessageDialog(null, "No more tasks available.", "No Tasks", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void createTaskDetailsDialog() {
        taskDetailsDialog = new JDialog();
        taskDetailsDialog.setTitle("Task Details");
        taskDetailsDialog.setSize(600, 400);
        taskDetailsDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        taskDetailsDialog.setLocationRelativeTo(null);
    }

    private void displayOrderDetails() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(new JLabel("<html><b><font size=+2>Task Details</font></b></html>"), gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 10, 0);
        panel.add(new JLabel("Order ID: " + orders.get(currentOrderIndex).getOrderId()), gbc);

        gbc.gridy = 2;
        panel.add(new JLabel("Customer: " + orders.get(currentOrderIndex).getCustomerName()), gbc);

        gbc.gridy = 3;
        panel.add(new JLabel("Delivery Location: " + orders.get(currentOrderIndex).getDeliveryLocation()), gbc);

        gbc.gridy = 4;
        panel.add(new JLabel("Cart: " + orders.get(currentOrderIndex).getCart()), gbc);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsPanel.setBackground(new Color(255, 255, 255));

        JButton acceptButton = createStyledButton("Accept");
        JButton declineButton = createStyledButton("Decline");

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOrderAction("Accepted By Delivery Runner");
            }
        });

        declineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOrderAction("Declined By Delivery Runner");
            }
        });

        addComponentsToButtonsPanel(buttonsPanel, acceptButton, declineButton);
        addComponentsToMainPanel(panel, buttonsPanel);

        taskDetailsDialog.add(panel);
        taskDetailsDialog.setVisible(true);
    }

    private void addComponentsToMainPanel(JPanel panel, JPanel buttonsPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonsPanel, gbc);
    }

    private void addComponentsToButtonsPanel(JPanel buttonsPanel, JButton acceptButton, JButton declineButton) {
        buttonsPanel.add(acceptButton);
        buttonsPanel.add(declineButton);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(51, 153, 255));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(120, 40));
        return button;
    }

    private void handleOrderAction(String status) {
        Order currentOrder = orders.get(currentOrderIndex);
    
        if (status.equalsIgnoreCase("Accepted By Delivery Runner")) {
            saveAcceptedOrderInformation(currentOrder, status);
        } else {
            saveDeclinedOrderInformation(currentOrder, status);
        }
    
        // Remove the processed order from the list
        orders.remove(currentOrderIndex);
    
        // Update the "Order.txt" file with the modified list
        updateOrderFile();
    
        String message = (status.equalsIgnoreCase("Accepted By Delivery Runner"))
                ? "Task accepted successfully!"
                : "Task declined.";
        JOptionPane.showMessageDialog(null, message, "Action Confirmation", JOptionPane.INFORMATION_MESSAGE);
        taskDetailsDialog.dispose();
        showNextOrder();
    }
    
    // Assuming you are inside the ViewTask class
    private void saveAcceptedOrderInformation(ViewTask.Order order, String status) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(acceptedTasksFilePath, true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            writer.write(order.getOrderId() + ";" +
                    order.getCustomerName() + ";" +
                    order.getDeliveryLocation() + ";" +
                    dateFormat.format(new Date()) + ";" +
                    status + ";");
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void updateOrderFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ordersFilePath))) {
            for (Order order : orders) {
                // Write each order to the file
                writer.write(order.getOrderId() + ";" +
                        order.getCustomerName() + ";" +
                        order.getCart() + ";" +
                        order.getDeliveryLocation() + ";");
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    

    private void saveDeclinedOrderInformation(Order order, String status) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(declinedTasksFilePath, true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            writer.write(order.getOrderId() + ";" +
                    order.getCustomerName() + ";" +
                    order.getDeliveryLocation() + ";" +
                    dateFormat.format(new Date()) + ";" +
                    status + ";");
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static class Order {
        private String customerName;
        private String orderId;
        private String cart;
        private String deliveryLocation;

        public Order(String customerName, String orderId, String cart, String deliveryLocation) {
            this.customerName = customerName;
            this.orderId = orderId;
            this.cart = cart;
            this.deliveryLocation = deliveryLocation;
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
    }
}