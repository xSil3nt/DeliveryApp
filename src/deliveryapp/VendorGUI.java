/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package deliveryapp;

import java.io.*;
import javax.swing.table.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Shazin
 */
public class VendorGUI extends javax.swing.JFrame {
    private static final String MENU_PATH = "programData\\menu.txt";
    private static final String ORDERS_PATH = "programData\\orders.txt";
    private static final String REVIEWS_PATH = "programData\\reviews.txt";
    
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

        bt_decline.setText("Decline Order");
        bt_decline.setEnabled(false);

        bt_ready.setText("Order Ready");
        bt_ready.setEnabled(false);

        tb_orders.setModel(model);
        tb_orders.getTableHeader().setReorderingAllowed(false);
        tb_orders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_ordersMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tb_orders);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
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
                    .addComponent(bt_menuOptions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(bt_ready))
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
    }//GEN-LAST:event_tb_ordersMouseReleased

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_title;
    private javax.swing.JLabel lb_welcome;
    private javax.swing.JTable tb_orders;
    // End of variables declaration//GEN-END:variables
}
