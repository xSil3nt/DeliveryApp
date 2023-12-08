package adminPackage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
        new Admin_Dashboard().setVisible(true);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Admin Dashboard");
        Admin_Dashboard adminDashboardPanel = new Admin_Dashboard();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(adminDashboardPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
