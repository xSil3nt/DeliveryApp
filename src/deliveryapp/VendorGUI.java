/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package deliveryapp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.*;
import javax.swing.table.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Shazin
 */
public class VendorGUI extends javax.swing.JFrame {
    private static final String MENU_PATH = "programData\\menu.txt";
    private static final String ORDERS_PATH = "programData\\orders.txt";
    private static final String REVIEWS_PATH = "programData\\reviews.txt";
    private static final String CUST_NOTIFICATIONS_PATH = "programData\\custNotifications.txt";
    
    private DefaultTableModel model = new DefaultTableModel();
    private String[] column = {"Order ID", "Date", "Customer", "Cart Items", "Item Names", "Order Total", "Location", "Status"};
    Vendor loggedIn;
    /**
     * Creates new form VendorGUI
     */
    public VendorGUI(String username, String password) {
        initComponents();
        initTable();
        loggedIn = new Vendor(username, password);
        lb_welcome.setText("Welcome "+ loggedIn.getUsername());

        parseOrders();
    }
    
    public VendorGUI() {
        String username = "Vendor1", password = "test";
        initComponents();
        initTable();
        loggedIn = new Vendor(username, password);
        lb_welcome.setText("Welcome "+ loggedIn.getUsername());
        model.setColumnIdentifiers(column);
        parseOrders();
    }
    
    private void initTable() {
        model.setColumnIdentifiers(column);
        tb_orders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tb_orders.setRowSelectionAllowed(true);
        tb_orders.setColumnSelectionAllowed(false);
        tb_orders.setDefaultEditor(Object.class, null);
    }

    private void parseOrders() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(ORDERS_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                String customer = data[0];
                String vendor = data[1];
                String orderID = data[2];
                String longDate = data[3];
                String cartItems = data[4];
                cartItems = cartItems.replaceAll("[\\[\\]]", "");
                String location = data[5];
                double orderTotal = Double.parseDouble(data[6]);
                String status = data[7];

                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                Date temp_date = dateFormat.parse(longDate);
                SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm dd/MM/yyyy");
                String date = outputFormat.format(temp_date);

                // Get item names based on item IDs in cartItems
                String[] cartItemIDs = cartItems.split(",");
                StringBuilder itemNamesBuilder = new StringBuilder();
                for (String itemID : cartItemIDs) {
                    int itemIdInt = Integer.parseInt(itemID.trim());
                    String itemName = getItemName(itemIdInt);
                    itemNamesBuilder.append(itemName).append(", ");
                }
                // Remove the trailing comma and space
                String itemNames = itemNamesBuilder.toString().replaceAll(", $", "");

                // Check if the vendor matches the logged-in vendor
                if (vendor.equals(loggedIn.getUsername())) {
                    // Add the parsed data to the table model
                    model.addRow(new Object[]{orderID, date, customer, cartItems, itemNames, orderTotal, location, status});
                }
            }
            reader.close();
        } catch (IOException e) {
            // Handle the exception (e.g., show an error message)
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(VendorGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        private String getItemName(int itemId) {
       try {
            Scanner scanner = new Scanner(new File(MENU_PATH));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                for (int i = 1; i < parts.length; i++) {
                    String[] itemDetails = parts[i].split("\\|");
                    if (itemDetails.length == 4) {
                        int currentItemId = Integer.parseInt(itemDetails[0]);
                        if (currentItemId == itemId) {
                            return itemDetails[1];
                        }
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_title = new javax.swing.JLabel();
        lb_welcome = new javax.swing.JLabel();
        bt_menuOptions = new javax.swing.JButton();
        bt_accept = new javax.swing.JButton();
        bt_decline = new javax.swing.JButton();
        bt_ready = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_orders = new javax.swing.JTable();
        bt_reviews = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lb_title.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lb_title.setText("Food Delivery System - Vendor");

        lb_welcome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_welcome.setText("Welcome Vendor!");

        bt_menuOptions.setText("Menu Options");
        bt_menuOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_menuOptionsActionPerformed(evt);
            }
        });

        bt_accept.setText("Accept Order");
        bt_accept.setEnabled(false);
        bt_accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_acceptActionPerformed(evt);
            }
        });

        bt_decline.setText("Decline/Cancel Order");
        bt_decline.setEnabled(false);
        bt_decline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_declineActionPerformed(evt);
            }
        });

        bt_ready.setText("Order Ready");
        bt_ready.setEnabled(false);
        bt_ready.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_readyActionPerformed(evt);
            }
        });

        tb_orders.setModel(model);
        tb_orders.getTableHeader().setReorderingAllowed(false);
        tb_orders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_ordersMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tb_orders);

        bt_reviews.setText("View Reviews");
        bt_reviews.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_reviewsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_title))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_ready, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_decline, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_accept, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_menuOptions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_reviews, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_title, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(bt_menuOptions)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_accept)
                        .addGap(18, 18, 18)
                        .addComponent(bt_decline)
                        .addGap(18, 18, 18)
                        .addComponent(bt_ready)
                        .addGap(18, 18, 18)
                        .addComponent(bt_reviews))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_menuOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_menuOptionsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_menuOptionsActionPerformed

    private void tb_ordersMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ordersMouseReleased
        int selectedRow = tb_orders.getSelectedRow();
        String selectedOrderStatus = (String) tb_orders.getValueAt(selectedRow, 7);
        System.out.println(selectedOrderStatus);
        if (selectedOrderStatus.equals("PENDING")) {
            bt_accept.setEnabled(true);
            bt_decline.setEnabled(true);
        } else {
            bt_accept.setEnabled(false);
            bt_decline.setEnabled(false);
        }
        if (selectedOrderStatus.equals("IN PROGRESS")) {
            bt_ready.setEnabled(true);
            bt_decline.setEnabled(true);
        } else {
            bt_ready.setEnabled(false);
            bt_decline.setEnabled(false);
        }
        
        
        
    }//GEN-LAST:event_tb_ordersMouseReleased

    private void bt_readyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_readyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_readyActionPerformed

    private void bt_reviewsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_reviewsActionPerformed
        // Create the table model
        DefaultTableModel reviewTableModel = new DefaultTableModel(new Object[]{"Customer", "Vendor", "Date", "Items", "Review"}, 0);

        // Parse and add reviews to the table model
        parseReviews(reviewTableModel);

        // Create the table
        JTable reviewsTable = new JTable(reviewTableModel);

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(reviewsTable);

        // Create a new window to display the reviews
        JFrame reviewsFrame = new JFrame("Reviews");
        reviewsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reviewsFrame.setLayout(new BorderLayout());

        // Add the scroll pane to the frame
        reviewsFrame.add(scrollPane, BorderLayout.CENTER);

        // Pack and center the JFrame
        reviewsFrame.setPreferredSize(new Dimension(reviewsFrame.getWidth() + 1000, reviewsFrame.getHeight()+500));
        reviewsTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        reviewsTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        reviewsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        reviewsTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        reviewsTable.getColumnModel().getColumn(4).setPreferredWidth(400);
        reviewsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reviewsTable.setDefaultEditor(Object.class, null);
        reviewsFrame.pack();
        reviewsFrame.setLocationRelativeTo(null);

        // Make the frame visible
        reviewsFrame.setVisible(true);
    }//GEN-LAST:event_bt_reviewsActionPerformed

    private void bt_acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_acceptActionPerformed
        int selectedRow = tb_orders.getSelectedRow();
        String newStatus = "IN PROGRESS";
        tb_orders.setValueAt(newStatus, selectedRow, 7);
        
        String selectedOrderId = (String) tb_orders.getValueAt(selectedRow, 0);
        updateOrderStatus(selectedOrderId, newStatus);
        
        //Notify customer about order upadte
        String customer = (String) tb_orders.getValueAt(selectedRow, 2);
        notifyCustomer(customer, "Order "+ selectedOrderId + " has been accepted by vendor.");
    }//GEN-LAST:event_bt_acceptActionPerformed

    private void bt_declineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_declineActionPerformed
        int selectedRow = tb_orders.getSelectedRow();
        String newStatus = "CANCELLED";
        tb_orders.setValueAt(newStatus, selectedRow, 7);
        
        String selectedOrderId = (String) tb_orders.getValueAt(selectedRow, 0);
        updateOrderStatus(selectedOrderId, newStatus);
        
        //Notify customer about order upadte
        String customer = (String) tb_orders.getValueAt(selectedRow, 2);
        notifyCustomer(customer, "Order "+ selectedOrderId + " has been declined/cancelled by vendor.");
    }//GEN-LAST:event_bt_declineActionPerformed

    private void updateOrderStatus(String orderId, String newStatus) {
        try {
            File file = new File(ORDERS_PATH);
            Scanner scanner = new Scanner(file);
            List<String> lines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(orderId)) {
                    // Assuming the status is the 8th column (index 7)
                    String[] parts = line.split(";");
                    parts[7] = newStatus;
                    line = String.join(";", parts);
                }
                lines.add(line);
            }

            scanner.close();

            FileWriter writer = new FileWriter(file);
            for (String updatedLine : lines) {
                writer.write(updatedLine + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }
    
    public void notifyCustomer(String customer, String notification) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUST_NOTIFICATIONS_PATH, true))) {
            String entry = customer + ";" + notification;
            writer.write(entry);
            writer.newLine(); 
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    
    private void parseReviews(DefaultTableModel reviewTableModel) {
        try {
            Scanner scanner = new Scanner(new File(REVIEWS_PATH));

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(";");

                    // Extract details from the review
                    String customer = parts[1].trim();
                    String vendor = parts[2].trim();
                    String date = parts[3].trim();
                    String items = parts[4].trim();
                    String review = parts[5].trim();

                    if (loggedIn.getUsername().equals(vendor)) {
                        reviewTableModel.addRow(new Object[]{customer, vendor, date, items, review});
                    }
                }
                scanner.close();
            } catch (FileNotFoundException ex) {
            Logger.getLogger(VendorGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

            
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VendorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VendorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VendorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VendorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VendorGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_accept;
    private javax.swing.JButton bt_decline;
    private javax.swing.JButton bt_menuOptions;
    private javax.swing.JButton bt_ready;
    private javax.swing.JButton bt_reviews;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_title;
    private javax.swing.JLabel lb_welcome;
    private javax.swing.JTable tb_orders;
    // End of variables declaration//GEN-END:variables
}
