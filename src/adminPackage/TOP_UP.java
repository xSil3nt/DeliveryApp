package adminPackage;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author zahra
 */
public class TOP_UP extends javax.swing.JFrame {
    private CustomerDAO customerDAO;
   

    /**
     * Creates new form TOP_UP
     */
    public TOP_UP() {
        initComponents();
        customerDAO = new CustomerDAO();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private String generateReceipt(Customer customer, double oldBalance, double topUpAmount) {
    String dateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
    return String.format("Date & Time: %s\nUsername: %s\nPrevious Amount: %.2f\nTop Up Amount: %.2f\nNew Balance: %.2f",
                            dateTime, customer.getUsername(), oldBalance, topUpAmount, customer.getAmount());
}

private void saveReceiptToFile(String username, String receipt) throws IOException {
    String filePath = "programData\\TOP_UP.txt";
    try (FileWriter fw = new FileWriter(filePath, true);
         BufferedWriter bw = new BufferedWriter(fw);
         PrintWriter out = new PrintWriter(bw)) {
        out.println(username + ";" + receipt.replace("\n", ";"));
    }
}
 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        amountRemainingTextField = new javax.swing.JPanel();
        usernameTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        topUpAmountTextField = new javax.swing.JTextField();
        topUpButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLayeredPane1.setLayout(new java.awt.CardLayout());

        amountRemainingTextField.setBackground(new java.awt.Color(255, 255, 255));

        usernameTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 0));
        jLabel1.setText("USERNAME:");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 0));
        jLabel5.setText("TOP_UP AMOUNT");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));

        topUpAmountTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        topUpButton.setBackground(new java.awt.Color(102, 102, 0));
        topUpButton.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        topUpButton.setForeground(new java.awt.Color(255, 255, 255));
        topUpButton.setText("TOP UP");
        topUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topUpButtonActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(102, 102, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("BACK TO ADMIN DASHBOARD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout amountRemainingTextFieldLayout = new javax.swing.GroupLayout(amountRemainingTextField);
        amountRemainingTextField.setLayout(amountRemainingTextFieldLayout);
        amountRemainingTextFieldLayout.setHorizontalGroup(
            amountRemainingTextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(amountRemainingTextFieldLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(topUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, amountRemainingTextFieldLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(amountRemainingTextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(73, 73, 73)
                .addGroup(amountRemainingTextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(topUpAmountTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                    .addComponent(usernameTextField))
                .addGap(551, 551, 551))
        );
        amountRemainingTextFieldLayout.setVerticalGroup(
            amountRemainingTextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(amountRemainingTextFieldLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(amountRemainingTextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(amountRemainingTextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(topUpAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(amountRemainingTextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(topUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(434, Short.MAX_VALUE))
        );

        jLayeredPane1.add(amountRemainingTextField, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void topUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topUpButtonActionPerformed
  String username = usernameTextField.getText().trim();
    String topUpAmountStr = topUpAmountTextField.getText().trim();
    
    if (username.isEmpty() || topUpAmountStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter both username and top-up amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        double amount = Double.parseDouble(topUpAmountStr);
        Customer customer = customerDAO.findCustomerByUsername(username);
        if (customer == null) {
            JOptionPane.showMessageDialog(this, "User not registered.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double oldBalance = customer.getAmount();
        customer.topUpAmount(amount);
        customerDAO.updateCustomer(customer);
        
      
        customerDAO.notifyCustomerOfTopUp(customer, amount);

        
        String receipt = generateReceipt(customer, oldBalance, amount);
        saveReceiptToFile(username, receipt);
        
        JOptionPane.showMessageDialog(this, receipt, "Receipt", JOptionPane.INFORMATION_MESSAGE);

        
        usernameTextField.setText("");
        topUpAmountTextField.setText("");
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "An error occurred while processing the top-up.", "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }

    }//GEN-LAST:event_topUpButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
         Admin_Main adminmainFrame = new Admin_Main();
            adminmainFrame.setVisible(true);
            adminmainFrame.setLocationRelativeTo(null); // To center the JFrame
            this.setVisible(false); // 
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TOP_UP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TOP_UP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TOP_UP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TOP_UP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TOP_UP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel amountRemainingTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JTextField topUpAmountTextField;
    private javax.swing.JButton topUpButton;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}