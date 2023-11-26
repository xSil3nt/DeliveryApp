/*
// * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package deliveryapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.table.*;
import javax.swing.*;

        
/**
 *
 * @author Shazin
 */
public class CustomerGUI extends javax.swing.JFrame {
    private DefaultTableModel model = new DefaultTableModel();
    private String[] column = {"Vendor", "ID", "Item Name", "Description", "Price"};
    Customer loggedIn;
    private static final String MENU_PATH = "C:\\Users\\Shazin\\OneDrive - Asia Pacific University\\University\\Year 2\\Java\\Assignment\\DeliveryApp\\src\\deliveryapp\\data\\menu.txt";
    
    /**
     * Creates new form CustomerGUI
     */
    public CustomerGUI(String username, String password, int balance, String location) {
        initComponents();
        loggedIn = new Customer(username, password, balance);
        loggedIn.setLocation(location);
        lb_welcome.setText("Welcome "+username);
        
        model.setColumnIdentifiers(column);
        parseMenu();
        
        
    }
    
    //Testing
    private CustomerGUI() {
        initComponents();
        model.setColumnIdentifiers(column);
        loggedIn = new Customer("Shazin","123",100);
        adjustColumnWidths();
        parseMenu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_welcome = new javax.swing.JLabel();
        bt_cart = new javax.swing.JButton();
        bt_orders = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_menu = new javax.swing.JTable();
        bt_reviews = new javax.swing.JButton();
        bt_addToCart = new javax.swing.JButton();
        bt_placeOrder = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lb_welcome.setText("Welcome Customer");

        bt_cart.setText("View cart");
        bt_cart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cartActionPerformed(evt);
            }
        });

        bt_orders.setText("Orders");

        tb_menu.setModel(model);
        tb_menu.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tb_menu);
        tb_menu.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        bt_reviews.setText("Reviews");

        bt_addToCart.setText("Add to cart");
        bt_addToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addToCartActionPerformed(evt);
            }
        });

        bt_placeOrder.setText("Place Order");
        bt_placeOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_placeOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_orders, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(252, 252, 252)
                        .addComponent(lb_welcome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 25, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bt_cart, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(bt_placeOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(bt_addToCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_reviews, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_orders)
                    .addComponent(lb_welcome)
                    .addComponent(bt_cart))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(41, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(bt_reviews)
                        .addGap(18, 18, 18)
                        .addComponent(bt_addToCart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_placeOrder)
                        .addGap(86, 86, 86))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void adjustColumnWidths() {
        TableColumnModel columnModel = tb_menu.getColumnModel();

        // Set the preferred width for specific columns
        columnModel.getColumn(0).setPreferredWidth(100); // Vendor column
        columnModel.getColumn(1).setPreferredWidth(30); // ID column
        columnModel.getColumn(2).setPreferredWidth(150); // Name column
        columnModel.getColumn(3).setPreferredWidth(250); // Desc column
        columnModel.getColumn(4).setPreferredWidth(30); // Price column
    }
    private void parseMenu() {
                try {
            Scanner scanner = new Scanner(new File(MENU_PATH));
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                
                // The first part is always the vendor name
                String vendorName = parts[0].trim();
                
                // Subsequent parts are menu items
                for (int i = 1; i < parts.length; i++) {
                    String[] itemDetails = parts[i].split("\\|");
                    
                    if (itemDetails.length == 4) {
                        int itemId = Integer.parseInt(itemDetails[0]);
                        String itemName = itemDetails[1].trim();
                        String description = itemDetails[2].trim();
                        int price = Integer.parseInt(itemDetails[3]);
                        
                        model.addRow(new Object[]{vendorName, itemId, itemName, description, price});
                        
                    } 
                }
            }
            
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    private void bt_cartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cartActionPerformed
        // Create a new JDialog to display the cart contents
        JDialog viewCartDialog = new JDialog(this, "View Cart", true);

        // Create a table to display the cart items
        DefaultTableModel cartTableModel = new DefaultTableModel();
        cartTableModel.setColumnIdentifiers(new String[]{"Item ID", "Vendor", "Item Name", "Description", "Price", "Remove"});

        // Read item details from the menu.txt file and populate the table based on the item IDs in the customer's cart
        try {
            Scanner scanner = new Scanner(new File(MENU_PATH));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                String vendorName = parts[0].trim();

                for (int i = 1; i < parts.length; i++) {
                    String[] itemDetails = parts[i].split("\\|");

                    if (itemDetails.length == 4) {
                        int itemId = Integer.parseInt(itemDetails[0]);

                        // Check if the item ID is in the customer's cart
                        if (loggedIn.getCart().contains(itemId)) {
                            String itemName = itemDetails[1].trim();
                            String description = itemDetails[2].trim();
                            int price = Integer.parseInt(itemDetails[3]);

                            // Add a row for each occurrence of the item in the cart
                            for (int cartItemId : loggedIn.getCart()) {
                                if (cartItemId == itemId) {
                                    cartTableModel.addRow(new Object[]{itemId, vendorName, itemName, description, price, "Remove"});
                                }
                            }
                        }
                    }
                }
            }

            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        JTable cartTable = new JTable(cartTableModel);

        // Add a MouseListener to handle clicks on the "Remove" column
        cartTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int column = cartTable.getColumnModel().getColumnIndex("Remove");
                int row = cartTable.rowAtPoint(evt.getPoint());

                if (column == cartTable.columnAtPoint(evt.getPoint()) && row != -1) {
                    // Handle the button click for the "Remove" column
                    int itemId = (int) cartTable.getValueAt(row, 0);
                    loggedIn.removeFromCart(itemId);
                    cartTableModel.removeRow(row);
                }
            }
        });

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(cartTable);

        // Add the scroll pane to the dialog
        viewCartDialog.add(scrollPane);

        // Set the size of the dialog
        viewCartDialog.setSize(500, 300);

        // Make the dialog visible
        viewCartDialog.setVisible(true);
    }//GEN-LAST:event_bt_cartActionPerformed

    private void bt_addToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addToCartActionPerformed
        // Get the selected row index
        int selectedRow = tb_menu.getSelectedRow();

        // Check if a row is selected
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to add to the cart.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get the item ID and vendor from the selected row
        int itemId = (int) model.getValueAt(selectedRow, 1);
        String selectedVendor = (String) model.getValueAt(selectedRow, 0);

        // Check if the cart is empty or the item is from the same vendor
        if (loggedIn.getCart().isEmpty() || isItemFromSameVendor(selectedVendor)) {
            // Add the item to the customer's cart
            loggedIn.addToCart(itemId);

            // Optionally, you can display a message to confirm that the item has been added to the cart
            JOptionPane.showMessageDialog(this, "Item added to the cart.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "You can only add items from the same vendor to the cart.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bt_addToCartActionPerformed

    private boolean isItemFromSameVendor(String selectedVendor) {
        // Check if the items in the cart are from the same vendor
        for (int itemId : loggedIn.getCart()) {
            String vendorForCartItem = getVendorForItem(itemId);
            if (!selectedVendor.equals(vendorForCartItem)) {
                return false;
            }
        }
        return true;
    }
    
    private String getVendorForItem(int itemId) {
        // Iterate through the rows in the table to find the vendor for the given item ID
        for (int row = 0; row < model.getRowCount(); row++) {
            if ((int) model.getValueAt(row, 1) == itemId) {
                // Found the row with the matching item ID, retrieve the vendor
                return (String) model.getValueAt(row, 0);
            }
        }
        return null;        
    }

    private void bt_placeOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_placeOrderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_placeOrderActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addToCart;
    private javax.swing.JButton bt_cart;
    private javax.swing.JButton bt_orders;
    private javax.swing.JButton bt_placeOrder;
    private javax.swing.JButton bt_reviews;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_welcome;
    private javax.swing.JTable tb_menu;
    // End of variables declaration//GEN-END:variables
}
