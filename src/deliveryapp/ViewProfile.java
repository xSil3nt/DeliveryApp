package deliveryapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ViewProfile implements ActionListener {

    private JDialog profileDialog;
    private JPanel profilePanel;
    private JTextField emailTextField;
    private JTextField genderTextField;
    private JTextField phoneNumberTextField;
    private JTextField addressTextField;
    private JTextField ageTextField;

    public ViewProfile(String email) {
        this.emailTextField = new JTextField(email);
        this.emailTextField.setEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (profileDialog != null && profileDialog.isVisible()) {
            return;
        }

        JButton sourceButton = (JButton) e.getSource();
        Container container = sourceButton.getTopLevelAncestor();
        if (container instanceof JFrame) {
            JFrame parentFrame = (JFrame) container;
            parentFrame.dispose();
        }

        EventQueue.invokeLater(() -> {
            createProfileDialog();
            addComponentsToProfileDialog();
            populateFieldsFromUserInfo(emailTextField.getText());
            showProfileDialog();
        });
    }

    private void createProfileDialog() {
        profileDialog = new JDialog();
        profileDialog.setSize(800, 600); // Increased size
        profileDialog.setTitle("Profile - Delivery Runner");
        profileDialog.setResizable(false);
        profileDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        profileDialog.setLayout(new BorderLayout());

        profilePanel = new JPanel();
        profilePanel.setLayout(new GridBagLayout());

        
    }

    private void addComponentsToProfileDialog() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        createLabelAndTextField("Email:", emailTextField, gbc, 0, 0, 3, 1);
        createLabelAndTextFieldWithEditButton("Gender:", genderTextField = createTextField(), gbc, 0, 1, 3, 1);
        createLabelAndTextField("Phone Number:", phoneNumberTextField = createTextField(), gbc, 0, 2, 3, 1);
        createLabelAndTextFieldWithEditButton("Address:", addressTextField = createTextField(), gbc, 0, 3, 3, 1);
        createLabelAndTextFieldWithEditButton("Age:", ageTextField = createTextField(), gbc, 0, 4, 3, 1);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(evt -> saveUserInfo());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 0, 10, 0);
        profilePanel.add(saveButton, gbc);
    }

    private void createLabelAndTextField(String labelText, JTextField textField, GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheight) {
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setFont(new Font("Times New Roman", Font.BOLD, 16));
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.gridheight = gridheight;
        profilePanel.add(label, gbc);

        gbc.gridx = gridx + 1;
        gbc.gridy = gridy;
        gbc.gridwidth = 2; // Set the grid width to 2 for emailTextField
        gbc.gridheight = gridheight;
        profilePanel.add(textField, gbc);
    }

    private void createLabelAndTextFieldWithEditButton(String labelText, JTextField textField, GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheight) {
        createLabelAndTextField(labelText, textField, gbc, gridx, gridy, gridwidth, gridheight);

        if (!labelText.equals("Phone Number:")) {
            JButton editButton = new JButton("Edit");
            editButton.addActionListener(evt -> textField.setEditable(true));
            gbc.gridx = gridx + 3; // Adjust the gridx value
            gbc.gridy = gridy;
            gbc.gridwidth = 1;
            gbc.gridheight = gridheight;
            profilePanel.add(editButton, gbc);
        }
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(200, 30)); // Set preferred size
        return textField;
    }

    private void populateFieldsFromUserInfo(String username) {
        File userInfoFile = new File("programData\\UserInfo.txt");
        File runnerInfoFile = new File("programData\\Runner.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(userInfoFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5 && parts[0].equals(username)) {
                    emailTextField.setText(parts[0]);
                    genderTextField.setText(parts[1]);
                    addressTextField.setText(parts[4]);
                    ageTextField.setText(parts[2]);
                    break; // Stop reading after finding the matching username
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Fetch phone number from Runner.txt
        try (BufferedReader reader = new BufferedReader(new FileReader(runnerInfoFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3 && parts[0].equals(username)) {
                    // Assuming parts[2] contains the contact number
                    phoneNumberTextField.setText(parts[2]);
                    break; // Stop reading after finding the matching username
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUserInfo() {
        File userInfoFile = new File("programData\\UserInfo.txt");
        String username = emailTextField.getText();

        // Check if the user already exists in the file
        boolean userExists = false;
        StringBuilder updatedFileContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(userInfoFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5 && parts[0].equals(username)) {
                    // Update the information for the existing user
                    line = username + ";" + genderTextField.getText() + ";" +
                            ageTextField.getText() + ";" + phoneNumberTextField.getText() + ";" +
                            addressTextField.getText();
                    userExists = true;
                }
                updatedFileContent.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // If the user doesn't exist, add a new row
        if (!userExists) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(userInfoFile, true))) {
                writeUserInfoLine(writer, username, genderTextField.getText(), addressTextField.getText(), ageTextField.getText());
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(profileDialog, "Error saving information. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // If the user exists, update the file content
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(userInfoFile))) {
                writer.write(updatedFileContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(profileDialog, "Error saving information. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(profileDialog, "Your new information has been saved.", "Information Saved", JOptionPane.INFORMATION_MESSAGE);
    }


    private void writeUserInfoLine(BufferedWriter writer, String email, String gender, String address, String age) throws IOException {
        String line = email + ";" + gender + ";" + age + ";" + phoneNumberTextField.getText() + ";" + address;
        writer.write(line);
        writer.newLine();
    }

    private void showProfileDialog() {
        profileDialog.add(profilePanel);
        profileDialog.setVisible(true);

        profileDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                profileDialog = null;
            }
        });
    }
}
