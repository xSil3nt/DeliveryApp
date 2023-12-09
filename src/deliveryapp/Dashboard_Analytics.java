package deliveryapp;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dashboard_Analytics {

    private static final String COMPLETED_TASKS_FILE_PATH = "programData\\accepted_tasks.txt";

    public static void showAnalytics(JFrame parentFrame) {
        JFrame analyticsFrame = new JFrame("Analytics");
        analyticsFrame.setSize(600, 400);
        analyticsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        analyticsFrame.setLocationRelativeTo(parentFrame);

        JPanel analyticsPanel = new JPanel(new BorderLayout());
        analyticsPanel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = createStyledLabel("Analytics Dashboard", Font.BOLD, 24, Color.black);

        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        infoPanel.setBackground(new Color(255, 255, 255));

        int numberOfCompletedOrders = getNumberOfCompletedOrders();
        double revenue = calculateRevenue(numberOfCompletedOrders);

        JLabel ordersLabel = createStyledLabel("Number of Completed Orders:", Font.PLAIN, 18, Color.BLACK);
        JLabel ordersValueLabel = createStyledLabel(String.valueOf(numberOfCompletedOrders), Font.PLAIN, 18, Color.BLACK);

        JLabel revenueLabel = createStyledLabel("Total Revenue:", Font.PLAIN, 18, Color.BLACK);
        JLabel revenueValueLabel = createStyledLabel(String.format("%.2f rm", revenue), Font.PLAIN, 18, Color.BLACK);

        addComponentsToPanel(infoPanel, ordersLabel, ordersValueLabel, revenueLabel, revenueValueLabel);

        analyticsPanel.add(titleLabel, BorderLayout.NORTH);
        analyticsPanel.add(infoPanel, BorderLayout.CENTER);

        analyticsFrame.add(analyticsPanel);
        analyticsFrame.setVisible(true);
    }

    private static JLabel createStyledLabel(String text, int style, int fontSize, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", style, fontSize));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(color);
        return label;
    }

    private static void addComponentsToPanel(JPanel panel, JComponent... components) {
        for (JComponent component : components) {
            panel.add(component);
        }
    }

    private static int getNumberOfCompletedOrders() {
        int numberOfCompletedOrders = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(COMPLETED_TASKS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] orderData = line.split(";");
                if (orderData.length >= 5 && orderData[4].equalsIgnoreCase("completed")) {
                    numberOfCompletedOrders++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numberOfCompletedOrders;
    }

    private static double calculateRevenue(int numberOfCompletedOrders) {
        return numberOfCompletedOrders * 2.0; // Example value: $2 per completed order
    }
}
