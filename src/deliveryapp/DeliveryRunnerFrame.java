package deliveryapp;

import javax.swing.*;
import java.awt.*;

public class DeliveryRunnerFrame extends JFrame {

    private JButton viewProfileButton; // Add this field

    public DeliveryRunnerFrame(String userEmail) {

        
        
        this.setSize(720, 720);
        this.setTitle("Delivery Runner");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel brownPanel = new JPanel();
        brownPanel.setLayout(null); // Use a layout manager or set layout constraints as needed
        brownPanel.setBackground(new Color(160, 82, 45));
        brownPanel.setBounds(0, 0, 720, 720);

        DeliveryRunnerLabel deliveryRunnerLabel = new DeliveryRunnerLabel();
        deliveryRunnerLabel.setBounds(0, 0, 720, 720);
        brownPanel.add(deliveryRunnerLabel);

        JButton viewTaskButton = new JButton("View Task");
        viewTaskButton.setBounds(10, 220, 150, 40);
        brownPanel.add(viewTaskButton);

        
        ViewTask viewTaskListener = new ViewTask();
        viewTaskButton.addActionListener(viewTaskListener);

        JButton taskHistoryButton = new JButton("Task History");
        taskHistoryButton.setBounds(10, 295, 150, 40);
        brownPanel.add(taskHistoryButton);

        TaskHistory TaskHistoryListener = new TaskHistory();
        taskHistoryButton.addActionListener(TaskHistoryListener);

        JButton updateTaskStatusButton = new JButton("Update Task Status");
        updateTaskStatusButton.setBounds(10, 370, 150, 40);
        brownPanel.add(updateTaskStatusButton);

        updateTaskStatusButton.addActionListener(e -> {
            UpdateTaskStatus updateTaskStatusDialog = new UpdateTaskStatus(this);
            updateTaskStatusDialog.setVisible(true);
        });


        JButton customerReviewButton = new JButton("Customer Review");
        customerReviewButton.setBounds(10, 445, 150, 40);
        brownPanel.add(customerReviewButton);

        CustomerReview CustomerReviewListener = new CustomerReview();
        customerReviewButton.addActionListener(CustomerReviewListener);

        JButton dashboardButton = new JButton("Dashboard");
        dashboardButton.setBounds(10, 520, 150, 40);
        brownPanel.add(dashboardButton);

        Dashboard dashboardListener = new Dashboard(this);
        dashboardButton.addActionListener(dashboardListener);
        

        JButton ViewProfile_Button = new JButton("View Profile");
        this.viewProfileButton = ViewProfile_Button;
        ViewProfile_Button.setBounds(530, 50, 150, 40);
        brownPanel.add(ViewProfile_Button);

        ImageIcon view_profile_Icon = new ImageIcon("programData\\img\\profile.png");
        Image scaledImage = view_profile_Icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaled_profile_icon = new ImageIcon(scaledImage);
        ViewProfile_Button.setIcon(scaled_profile_icon);

        ViewProfile viewProfile = new ViewProfile(userEmail);
        ViewProfile_Button.addActionListener(viewProfile);

        this.add(brownPanel);
    }

    public void setupViewProfileListener(String userEmail) {
        viewProfileButton.addActionListener(new ViewProfile(userEmail));
    }

    public JButton getViewProfileButton() {
        return viewProfileButton;
    }
}