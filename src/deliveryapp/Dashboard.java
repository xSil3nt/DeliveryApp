package deliveryapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard implements ActionListener {

    private JFrame parentFrame; // Reference to the DeliveryRunnerFrame

    public Dashboard(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Call the showDashboard method when the "Dashboard" button is clicked
        showDashboard();
    }

    // Instance method to show the dashboard
    public void showDashboard() {
        JFrame dashboardFrame = new JFrame("Delivery Runner Dashboard");
        dashboardFrame.setSize(720, 720);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel dashboardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Use a valid image path for the login theme
                ImageIcon loginTheme = new ImageIcon("programData\\img\\dashboard.png");
                int width = getWidth();
                int height = getHeight();
                g.drawImage(loginTheme.getImage(), 0, 0, width, height, null);
            }
        };

        dashboardPanel.setLayout(new BoxLayout(dashboardPanel, BoxLayout.Y_AXIS));

        JButton analyticsButton = new JButton("Dashboard Analytics");
        JButton helpCenterButton = new JButton("Support/Help Center");
        JButton logoutButton = new JButton("Logout");
        JButton backButton = new JButton("Back");

        analyticsButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        helpCenterButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        logoutButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        backButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        analyticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show analytics when the analytics button is clicked
                Dashboard_Analytics.showAnalytics(dashboardFrame);
            }
        });

        helpCenterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the Help Center page
                SupportHelpCenter.showHelpCenter(dashboardFrame);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Logout button clicked");
                dashboardFrame.dispose();

                // Add code to return to the login screen
                returnToLoginScreen();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the DeliveryRunnerFrame
                parentFrame.setVisible(true);
                dashboardFrame.dispose();
            }
        });

        dashboardPanel.add(analyticsButton);
        dashboardPanel.add(Box.createVerticalStrut(10)); // Add spacing
        dashboardPanel.add(helpCenterButton);
        dashboardPanel.add(Box.createVerticalStrut(10)); // Add spacing
        dashboardPanel.add(logoutButton);
        dashboardPanel.add(Box.createVerticalStrut(10)); // Add spacing
        dashboardPanel.add(backButton);

        dashboardFrame.add(dashboardPanel);
        dashboardFrame.setVisible(true);

        // Close the parent frame (DeliveryRunnerFrame) when showing the dashboard
        if (parentFrame != null) {
            parentFrame.dispose();
        }
    }

    // Method to return to the login screen
    private void returnToLoginScreen() {
        SwingUtilities.invokeLater(() -> {
            // Create and show the login frame
            Login_Delivery_Runner loginRunner = new Login_Delivery_Runner();

            // Add a login success listener
            loginRunner.addLoginSuccessListener(() -> {
                // Once the login is successful, display the MyFrame
                DeliveryRunnerFrame frame = new DeliveryRunnerFrame(loginRunner.getUserEmail());
                frame.setVisible(true);
                frame.setupViewProfileListener(loginRunner.getUserEmail());
            });

            loginRunner.loginFrame();
        });
    }
}
