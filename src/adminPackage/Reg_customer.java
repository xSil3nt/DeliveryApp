package adminPackage;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author zahra
 */
public final class Reg_customer extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
  
    
    /**
     * Creates new form Reg_customer
     */
    public Reg_customer() {
        initComponents();
        String[] columnNames = {"USERNAME", "PASSWORD", "AMOUNT", "ADDRESS", "PHONE NUMBER"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tinfo.setModel(tableModel);
        loadExistingData();

        
    }
    private void loadExistingData() {
    
    CustomerDAO dao = new CustomerDAO();
    try {
        List<Customer> customers = dao.getAllCustomers();
        for (Customer c : customers) {
            if (c != null && c.getUsername() != null && c.getPassword() != null && c.getPhoneNumber() != null) {
                
                tableModel.addRow(new Object[]{
                    c.getUsername(),
                    c.getPassword(),
                    c.getAmount(),
                    c.getAddress(),
                    c.getPhoneNumber()
                });
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading existing customer data.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    

    @SuppressWarnings("unchecked")
  private void editCustomer(int row) {
    String username = (String) tinfo.getValueAt(row, 0);
    String password = (String) tinfo.getValueAt(row, 1);
    String address = (String) tinfo.getValueAt(row, 3);
    String phoneNumber = (String) tinfo.getValueAt(row, 4);

    
    usernameTextField.setText(username);
    passwordField.setText(password);
    addressTextField.setText(address);
    phoneNumberTextField.setText(phoneNumber);

   
    ((DefaultTableModel) tinfo.getModel()).removeRow(row);

   
    try {
        CustomerDAO dao = new CustomerDAO();
        dao.deleteCustomer(username);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error deleting customer from file.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

  

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        card3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        passwordField = new javax.swing.JTextField();
        addressTextField = new javax.swing.JTextField();
        phoneNumberTextField = new javax.swing.JTextField();
        usernameTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        addcustomer = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tinfo = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLayeredPane1.setLayout(new java.awt.CardLayout());

        card3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 0));
        jLabel3.setText("CONTACT NO");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 0));
        jLabel7.setText("ADDRESS");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 0));
        jLabel8.setText("USERNAME");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 0));
        jLabel9.setText("PASSWORD");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));

        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        passwordField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));

        addressTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addressTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));
        addressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressTextFieldActionPerformed(evt);
            }
        });

        phoneNumberTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        phoneNumberTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));

        usernameTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        usernameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 0));
        jLabel13.setText("USERNAME shoul be unique for every customer");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 0));
        jLabel14.setText("PASSWORD should be strong ");

        addcustomer.setBackground(new java.awt.Color(102, 102, 0));
        addcustomer.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        addcustomer.setForeground(new java.awt.Color(255, 255, 255));
        addcustomer.setText("ADD CUSTOMER");
        addcustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addcustomerActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 102, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("EDIT CUSTOMER");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(102, 102, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("DELETE CUSTOMER");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(102, 102, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("BACK TO ADMIN DASHBOARD");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tinfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tinfo);

        javax.swing.GroupLayout card3Layout = new javax.swing.GroupLayout(card3);
        card3.setLayout(card3Layout);
        card3Layout.setHorizontalGroup(
            card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card3Layout.createSequentialGroup()
                .addGroup(card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .addComponent(passwordField)
                    .addComponent(addressTextField)
                    .addComponent(phoneNumberTextField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, card3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(card3Layout.createSequentialGroup()
                        .addComponent(addcustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(80, 80, 80)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        card3Layout.setVerticalGroup(
            card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addcustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(318, 318, 318))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1126, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(card3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addcustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addcustomerActionPerformed
                                          
   
    resetTextFieldsBorder();

  
    String username = usernameTextField.getText().trim();
    String password = passwordField.getText().trim();
    String address = addressTextField.getText().trim();
    String phoneNumber = phoneNumberTextField.getText().trim();

    try {
        validateInput(username, password, address, phoneNumber); 
        
       
        CustomerDAO dao = new CustomerDAO();
        List<Customer> existingCustomers = dao.getAllCustomers();
        
      
        boolean isUniqueUsername = true;
        boolean isUniquePhone = true;
        for (Customer c : existingCustomers) {
            if (c == null) {
            continue; 
            }
            if (c.getUsername().equalsIgnoreCase(username)) {
                isUniqueUsername = false;
                break;
            }
            if (c.getPhoneNumber().equals(phoneNumber)) {
                isUniquePhone = false;
                break;
            }
        }

        if (!isUniqueUsername) {
        
            usernameTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
            JOptionPane.showMessageDialog(this, "Username must be unique.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isUniquePhone) {
        
            phoneNumberTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
            JOptionPane.showMessageDialog(this, "Phone number must be unique.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        Customer newCustomer = new Customer(username, password, phoneNumber, address);
        addCustomerToTable(newCustomer);
        saveCustomer(newCustomer);
        
       
        clearInputFields();

    } catch (IllegalArgumentException e) {
       
        JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
    } catch (IOException e) {
       
        JOptionPane.showMessageDialog(this, "Error saving customer data.", "File Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}


private void resetTextFieldsBorder() {
    Color defaultColor = new java.awt.Color(102, 102, 0); 
    usernameTextField.setBorder(BorderFactory.createLineBorder(defaultColor));
    passwordField.setBorder(BorderFactory.createLineBorder(defaultColor));
    addressTextField.setBorder(BorderFactory.createLineBorder(defaultColor));
    phoneNumberTextField.setBorder(BorderFactory.createLineBorder(defaultColor));
}

private void validateInput(String username, String password, String address, String phoneNumber) {
    // This function throws IllegalArgumentException if validation fails
    if (username.isEmpty()) {
        usernameTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
        throw new IllegalArgumentException("Username cannot be empty.");
    }
    if (password.isEmpty()) {
        passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
        throw new IllegalArgumentException("Password cannot be empty.");
    }
    if (address.isEmpty()) {
        addressTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
        throw new IllegalArgumentException("Address cannot be empty.");
    }
    if (!phoneNumber.matches("^\\+?[1-9]\\d{1,14}$")) {
        phoneNumberTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
        throw new IllegalArgumentException("Invalid phone number format. It must be an international format starting with '+' followed by up to 14 digits.");
    }
}

private void addCustomerToTable(Customer customer) {
    DefaultTableModel model = (DefaultTableModel) tinfo.getModel();
    model.addRow(new Object[]{
        customer.getUsername(),
        customer.getPassword(),
        customer.getAmount(),
        customer.getAddress(),
        customer.getPhoneNumber()
    });
    
}

private void saveCustomer(Customer customer) throws IOException {
    CustomerDAO dao = new CustomerDAO();
    dao.addCustomer(customer);
    JOptionPane.showMessageDialog(this, "Customer added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
}

private void clearInputFields() {
    usernameTextField.setText("");
    passwordField.setText("");
    addressTextField.setText("");
    phoneNumberTextField.setText("");

    }//GEN-LAST:event_addcustomerActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
   int row = tinfo.getSelectedRow();
    if(row == -1) {
        JOptionPane.showMessageDialog(this, "Please select a customer to delete.", "No Customer Selected", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    int confirmation = JOptionPane.showConfirmDialog(this, "Do you really want to delete this customer?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
    if(confirmation == JOptionPane.YES_OPTION) {
        String username = (String) tinfo.getValueAt(row, 0); // Assuming username is in the first column
        try {
            new CustomerDAO().deleteCustomer(username);
            ((DefaultTableModel)tinfo.getModel()).removeRow(row);
            JOptionPane.showMessageDialog(this, "Customer deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch(IOException e) {
            JOptionPane.showMessageDialog(this, "Error deleting customer data.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    int row = tinfo.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Please select a customer to edit.", "No Customer Selected", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirmation = JOptionPane.showConfirmDialog(this, "Do you want to edit this customer?", "Confirm Edit", JOptionPane.YES_NO_OPTION);
    if (confirmation == JOptionPane.YES_OPTION) {
        // Proceed with editing
        editCustomer(row);
    }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    Admin_Main adminmainFrame = new Admin_Main();
            adminmainFrame.setVisible(true);
            adminmainFrame.setLocationRelativeTo(null);
            this.setVisible(false); // 
    }//GEN-LAST:event_jButton4ActionPerformed

    private void addressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressTextFieldActionPerformed
       
    }//GEN-LAST:event_addressTextFieldActionPerformed
     public static void main(String[] args) {
     java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new Reg_customer().setVisible(true);
        }
    });
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addcustomer;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JPanel card3;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField passwordField;
    private javax.swing.JTextField phoneNumberTextField;
    private javax.swing.JTable tinfo;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables

}
