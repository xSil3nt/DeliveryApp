package deliveryapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateTaskStatus extends JDialog implements ActionListener {

    private JComboBox<String> taskComboBox;
    private JComboBox<String> statusComboBox;
    private List<Order> acceptedTasks;
    private final String acceptedTasksFilePath = "programData\\accepted_tasks.txt";

    private static final String[] STATUS_OPTIONS = {"On the Way", "Completed", "Canceled"};

    public UpdateTaskStatus(JFrame parent) {
        super(parent, "Update Task Status", true);
        setSize(400, 200);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(5, 1));

        JLabel taskLabel = new JLabel("Select Task:");
        taskComboBox = new JComboBox<>();
        JLabel statusLabel = new JLabel("Select New Status:");
        statusComboBox = new JComboBox<>(STATUS_OPTIONS);
        JButton updateButton = new JButton("Update Status");

        updateButton.addActionListener(this);

        mainPanel.add(taskLabel);
        mainPanel.add(taskComboBox);
        mainPanel.add(statusLabel);
        mainPanel.add(statusComboBox);
        mainPanel.add(updateButton);

        populateTaskComboBox();
        add(mainPanel, BorderLayout.CENTER);
    }

    private void populateTaskComboBox() {
        readAcceptedTasks();

        for (Order task : acceptedTasks) {
            taskComboBox.addItem(task.getOrderId() + " - " + task.getCustomerName());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            updateTaskStatus();
        } catch (IOException | ParseException ioException) {
            ioException.printStackTrace();
        }
    }

    private void updateTaskStatus() throws IOException, ParseException {
        int selectedTaskIndex = taskComboBox.getSelectedIndex();

        if (selectedTaskIndex != -1) {
            Order selectedTask = acceptedTasks.get(selectedTaskIndex);
            String newStatus = (String) statusComboBox.getSelectedItem();
            selectedTask.setStatus(newStatus);

            writeAcceptedTasks();
            JOptionPane.showMessageDialog(this, "Status updated successfully.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void readAcceptedTasks() {
        acceptedTasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(acceptedTasksFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskData = line.split(";");
                if (taskData.length >= 5) {
                    Date timestamp = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(taskData[3]);
                    Order order = new Order(taskData[0], taskData[1], taskData[2], timestamp, taskData[4]);
                    acceptedTasks.add(order);
                } else {
                    System.out.println("Invalid task format: " + line);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void writeAcceptedTasks() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(acceptedTasksFilePath))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

            for (Order order : acceptedTasks) {
                writer.write(order.getOrderId() + ";" +
                        order.getCustomerName() + ";" +
                        order.getDeliveryLocation() + ";" +
                        dateFormat.format(order.getTimestamp()) + ";" +
                        order.getStatus() + ";");
                writer.newLine();
            }
        }
    }

    private static class Order implements Comparable<Order> {
        private String orderId;
        private String customerName;
        private String deliveryLocation;
        private Date timestamp;
        private String status;

        public Order(String orderId, String customerName, String deliveryLocation, Date timestamp, String status) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.deliveryLocation = deliveryLocation;
            this.timestamp = timestamp;
            this.status = status;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getDeliveryLocation() {
            return deliveryLocation;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public int compareTo(Order other) {
            return this.timestamp.compareTo(other.timestamp);
        }
    }
}
