package deliveryapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SupportHelpCenter {

    private static final String FILE_PATH = "programData\\ReportedDeliveryRunnerProblems.txt";

    public static void showHelpCenter(Component parentComponent) {
        JPanel helpCenterPanel = new JPanel() {
            {
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                setOpaque(false);
                setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

                // Background image
                ImageIcon backgroundImage = new ImageIcon("programData\\img\\dashboard.png");
                setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));

                // Title label
                JLabel titleLabel = new JLabel("Welcome to Support/Help Center");
                titleLabel.setForeground(Color.WHITE);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
                titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // UI components for questions
                JPanel questionPanel = new JPanel();
                questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.X_AXIS));
                questionPanel.setOpaque(false);
                questionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel questionLabel = new JLabel("Did you encounter a problem?");
                questionLabel.setForeground(Color.WHITE);
                questionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                JComboBox<String> answerComboBox = new JComboBox<>(new String[]{"Select", "Yes", "No"});
                answerComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
                answerComboBox.setMaximumSize(new Dimension(150, 30));

                questionPanel.add(questionLabel);
                questionPanel.add(Box.createRigidArea(new Dimension(10, 0)));
                questionPanel.add(answerComboBox);

                // UI components for problem description
                JLabel problemDescriptionLabel = new JLabel("Please enter the encountered problem:");
                problemDescriptionLabel.setForeground(Color.WHITE);
                problemDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                problemDescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JTextField problemDescriptionTextField = new JTextField();
                problemDescriptionTextField.setFont(new Font("Arial", Font.PLAIN, 14));
                problemDescriptionTextField.setMaximumSize(new Dimension(400, 30));

                // Add UI components to the panel
                add(titleLabel);
                add(Box.createVerticalStrut(20));
                add(questionPanel);
                add(Box.createVerticalStrut(20));
                add(problemDescriptionLabel);
                add(Box.createVerticalStrut(10));
                add(problemDescriptionTextField);
                add(Box.createVerticalStrut(20));

                JButton submitButton = new JButton("Submit");
                submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String userAnswer = (String) answerComboBox.getSelectedItem();
                        String problemDescription = problemDescriptionTextField.getText();

                        if ("Yes".equals(userAnswer)) {
                            if (problemDescription.isEmpty()) {
                                showMessageDialog(parentComponent, "Please enter the encountered problem.");
                            } else {
                                writeToFile(problemDescription);
                                showMessageDialog(parentComponent, "Your problem is reported.\nThank you for contacting the Help Center.");
                                returnToOriginalFrame(parentComponent);
                            }
                        } else if ("No".equals(userAnswer)) {
                            // Display a thank you message if the problem description is not empty
                            if (!problemDescription.isEmpty()) {
                                showMessageDialog(parentComponent, "Thank you for contacting the Help Center.");
                                returnToOriginalFrame(parentComponent);
                            } else {
                                // If the problem description is empty, show an additional message
                                showMessageDialog(parentComponent, "Thank you for contacting the Help Center.");
                            }
                        }
                    }
                });

                add(submitButton);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Paint the background image
                ImageIcon backgroundImage = new ImageIcon("programData\\img\\dashboard.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, this);
            }
        };

        // Center the components in the tab
        helpCenterPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (parentComponent instanceof JFrame) {
            JFrame parentFrame = (JFrame) parentComponent;
            parentFrame.setContentPane(new JPanel(new BorderLayout()));
            parentFrame.getContentPane().add(helpCenterPanel, BorderLayout.CENTER);
            parentFrame.getContentPane().setBackground(Color.WHITE);
            parentFrame.revalidate();
        }
    }

    private static void showMessageDialog(Component parentComponent, String message) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.windowForComponent(parentComponent), "Message", true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(parentComponent);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.setOpaque(false);

        contentPanel.add(label, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(contentPanel);
        dialog.setVisible(true);
    }

    private static void returnToOriginalFrame(Component parentComponent) {
        if (parentComponent instanceof JFrame) {
            ((JFrame) parentComponent).dispose(); // Close the Help Center frame
        }
    }

    private static void writeToFile(String problemDescription) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(problemDescription);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
