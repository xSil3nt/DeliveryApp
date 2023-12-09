package deliveryapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CustomerReview implements ActionListener {

    private JFrame reviewFrame;
    private JTextArea textArea;
    private JComboBox<String> commentSelector;
    private JLabel selectedCommentLabel;

    public CustomerReview() {
        reviewFrame = new JFrame("Customer Reviews");
        reviewFrame.setSize(800, 600);
        reviewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);

        commentSelector = new JComboBox<>();
        commentSelector.setFont(new Font("Arial", Font.PLAIN, 14));
        commentSelector.addActionListener(this::selectComment);

        selectedCommentLabel = new JLabel("Selected Comment: ");
        selectedCommentLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(51, 153, 255));
        JLabel headerLabel = new JLabel("Customer Reviews");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        JPanel selectorPanel = new JPanel();
        selectorPanel.add(new JLabel("Select Comment:"));
        selectorPanel.add(commentSelector);

        JPanel labelPanel = new JPanel();
        labelPanel.add(selectedCommentLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(selectorPanel, BorderLayout.WEST);
        mainPanel.add(labelPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        reviewFrame.add(mainPanel);

        reviewFrame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String reviewsFilePath = "programData\\reviews.txt";
        populateCommentSelector(reviewsFilePath);

        // Display the first comment by default
        String selectedComment = commentSelector.getItemAt(0);
        selectComment(selectedComment);

        reviewFrame.setVisible(true);
    }

    private void selectComment(String selectedComment) {
        // Retrieve customer review information from the file
        String reviewsFilePath = "programData\\reviews.txt";
        String customerReviewInfo = getReviewInfo(selectedComment, reviewsFilePath);

        selectedCommentLabel.setText("Selected Comment: " + selectedComment);
        textArea.setText(customerReviewInfo);
    }

    private void populateCommentSelector(String filePath) {
        commentSelector.removeAllItems();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming the first part of the line is the Comment ID
                String commentId = line.split(";")[0].trim();
                commentSelector.addItem("Comment " + commentId);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading comments from file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getReviewInfo(String selectedComment, String filePath) {
        // Retrieve customer review information based on the selected comment
        // This assumes each line in the file corresponds to a customer review
        StringBuilder reviewInfo = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line using the separator (;)
                String[] parts = line.split(";");
                // Assuming the first part is the Comment ID
                String commentId = parts[0].trim();
                if (("Comment " + commentId).equals(selectedComment)) {
                    // Display all information for the selected comment
                    for (int i = 0; i < parts.length; i++) {
                        String title = getTitleByIndex(i);
                        reviewInfo.append(title).append(": ").append(parts[i].trim()).append("\n");
                    }
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading reviews from file", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return reviewInfo.toString();
    }

    private void selectComment(ActionEvent event) {
        String selectedComment = (String) commentSelector.getSelectedItem();
        selectComment(selectedComment);
    }

    private String getTitleByIndex(int index) {
        // Assuming the order of titles is as follows
        String[] titles = {"Order Id", "Customer Name", "Vendor Name", "Date & Time", "Cart", "Customer Review"};
        if (index >= 0 && index < titles.length) {
            return titles[index];
        } else {
            return "Unknown Title";
        }
    }
}
