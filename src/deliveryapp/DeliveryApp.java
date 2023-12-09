package deliveryapp;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import adminPackage.Admin_Dashboard;

public class DeliveryApp {

    public static void main(String[] args) {
        String[] options = {"Admin", "Customer", "Vendor", "Delivery Runner"};

        int choice = JOptionPane.showOptionDialog(
            null,
            "Choose a login option:",
            "Login",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        switch (options[choice]) {
            case "Admin":
                new Admin_Dashboard().setVisible(true);
                break;
            case "Customer":
                new CustomerLoginGUI().setVisible(true);
                break;
            case "Vendor":
                new VendorLoginGUI().setVisible(true);
                break;
            case "Delivery Runner":
                SwingUtilities.invokeLater(() -> {
                    Login_Delivery_Runner loginRunner = new Login_Delivery_Runner();

                    loginRunner.addLoginSuccessListener(() -> {
                        DeliveryRunnerFrame frame = new DeliveryRunnerFrame(loginRunner.getUserEmail());
                        frame.setVisible(true);
                    });

                    loginRunner.loginFrame();
                });
                break;
            default:
                // Handle unexpected choices
                break;
        }
    }
}