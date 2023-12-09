package deliveryapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Login_Delivery_Runner {
    private List<Runnable> loginSuccessListeners = new ArrayList<>();
    private JTextField usernameField;
    private JPasswordField passwordField;
    private String userEmail;

    public void addLoginSuccessListener(Runnable listener) {
        loginSuccessListeners.add(listener);
    }

    public String getUserEmail() {
        return userEmail;
    }
    

    public void loginFrame() {
        SwingUtilities.invokeLater(() -> {
            JFrame loginFrame = new JFrame();
            loginFrame.setSize(720, 480);
            loginFrame.setTitle("Login Delivery Runner");
            loginFrame.setResizable(false);
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel loginPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon loginTheme = new ImageIcon("C:\\Mana\\Object Oriented with Java\\Login_theme.png");
                    int width = getWidth();
                    int height = getHeight();
                    g.drawImage(loginTheme.getImage(), 0, 0, width, height, null);
                }
            };

            loginPanel.setLayout(null);

            ImageIcon image = new ImageIcon("C:\\Mana\\Object Oriented with Java\\Restaurant.png");
            loginFrame.setIconImage(image.getImage());

            JLabel loginLabel = new JLabel("Welcome to Food Delivery App");
            loginLabel.setHorizontalAlignment(JLabel.CENTER);
            loginLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
            loginLabel.setBounds(50, 50, 600, 40);

            usernameField = createTextField("Username", 250, 150, 200);
            passwordField = createPasswordField("Password", 250, 200, 200);

            JButton loginButton = new JButton("Login");
            loginButton.setBounds(290, 250, 120, 40);

            loginPanel.add(loginLabel);
            loginPanel.add(usernameField);
            loginPanel.add(passwordField);
            loginPanel.add(loginButton);

            loginFrame.add(loginPanel);
            loginFrame.setVisible(true);

            loginButton.addActionListener(e -> {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                String passwordStr = new String(password);

                if (isValidLogin(username, passwordStr)) {
                    if (isEmailAlreadyLoggedIn(username)) {
                        JOptionPane.showMessageDialog(loginFrame, "Login Successful", "Login Success", JOptionPane.INFORMATION_MESSAGE);
                        loginFrame.dispose();
                        userEmail = username;

                        for (Runnable listener : loginSuccessListeners) {
                            listener.run();
                        }
                    } else {
                        saveLoginToFile(username, passwordStr);
                    }
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            });
        });
    }

    private JTextField createTextField(String placeholder, int x, int y, int width) {
        JTextField textField = new JTextField(placeholder);
        textField.setBounds(x, y, width, 30);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                }
            }
        });
        return textField;
    }

    private JPasswordField createPasswordField(String placeholder, int x, int y, int width) {
        JPasswordField passwordField = new JPasswordField(placeholder);
        passwordField.setBounds(x, y, width, 30);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(placeholder);
                }
            }
        });
        return passwordField;
    }

    private boolean isValidLogin(String username, String password) {
        String filePath = "programData\\Runner.txt";

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isEmailAlreadyLoggedIn(String username) {
        String filePath = "programData\\Runner.txt";

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length >= 1 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void saveLoginToFile(String username, String password) {
        String filePath = "programData\\Runner.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(username + ";" + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
