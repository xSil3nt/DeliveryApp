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
public class Reg_runner extends javax.swing.JFrame {
    private DefaultTableModel tableModel;

    /**
     * Creates new form Reg_runner
     */
    public Reg_runner() {
        initComponents();
        String[] columnNames = {"USERNAME", "PASSWORD", "PHONE NUMBER"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tinfo.setModel(tableModel);
        loadExistingData();
    }

    private void loadExistingData() {
    RunnerDAO dao = new RunnerDAO();
    try {
        List<Runner> runners = dao.getAllRunners();
        System.out.println("Runners loaded: " + runners.size());
        for (Runner r : runners) {
            if (r != null && r.getUsername() != null && r.getPassword() != null && r.getPhoneNumber() != null) {
                System.out.println("Adding runner: " + r.getUsername()); 
                tableModel.addRow(new Object[]{
                    r.getUsername(),
                    r.getPassword(),
                    r.getPhoneNumber()
                });
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading existing runner data.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    

  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        phoneNumberTextField = new javax.swing.JTextField();
        usernameTextField = new javax.swing.JTextField();
        passwordField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tinfo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLayeredPane1.setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 0));
        jLabel8.setText("USERNAME");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));

        phoneNumberTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        phoneNumberTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));

        usernameTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        usernameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));

        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        passwordField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 0));
        jLabel10.setText("CONTACT NO should have 10 digits");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 0));
        jLabel13.setText("USERNAME shoul be unique for every customer");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 0));
        jLabel14.setText("PASSWORD should be strong ");

        jButton1.setBackground(new java.awt.Color(102, 102, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ADD RUNNER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 102, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("EDIT RUNNER");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(102, 102, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("DELETE RUNNER");
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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 0));
        jLabel3.setText("CONTACT NO");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 0));
        jLabel12.setText("PASSWORD");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameTextField)
                            .addComponent(passwordField))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addGap(251, 251, 251))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(phoneNumberTextField)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(116, 116, 116))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLayeredPane1.add(jPanel1, "card2");

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  resetTextFieldsBorder();

    String username = usernameTextField.getText().trim();
    String password = passwordField.getText().trim();
    String phoneNumber = phoneNumberTextField.getText().trim();

    try {
        validateInput(username, password, phoneNumber);
        
        RunnerDAO dao = new RunnerDAO();
        
        List<Runner> existingRunners = dao.getAllRunners();
        boolean isUniqueUsername = existingRunners.stream()
            .noneMatch(r -> r.getUsername().equalsIgnoreCase(username));

        if (!isUniqueUsername) {
            usernameTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
            JOptionPane.showMessageDialog(this, "Username must be unique.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Runner newRunner = new Runner(username, password, phoneNumber);
        dao.addRunner(newRunner); 
        addRunnerToTable(newRunner); 
       clearInputFields();
        
        JOptionPane.showMessageDialog(this, "Runner added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
    } catch (IOException e) {
       
        JOptionPane.showMessageDialog(this, "Error saving Runner data.", "File Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}



private void resetTextFieldsBorder() {
    Color defaultColor = new java.awt.Color(102, 102, 0); 
    usernameTextField.setBorder(BorderFactory.createLineBorder(defaultColor));
    passwordField.setBorder(BorderFactory.createLineBorder(defaultColor));
    phoneNumberTextField.setBorder(BorderFactory.createLineBorder(defaultColor));
}

private void validateInput(String username, String password, String phoneNumber) {
  
    if (username.isEmpty()) {
        usernameTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
        throw new IllegalArgumentException("Username cannot be empty.");
    }
    if (password.isEmpty()) {
        passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
        throw new IllegalArgumentException("Password cannot be empty.");
    }
    if (!phoneNumber.matches("^\\+?[1-9]\\d{1,14}$")) {
        phoneNumberTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
        throw new IllegalArgumentException("Invalid phone number format. It must be an international format starting with '+' followed by up to 14 digits.");
    }
    
}

private void addRunnerToTable(Runner runner) {
    DefaultTableModel model = (DefaultTableModel) tinfo.getModel();
    model.addRow(new Object[]{
        runner.getUsername(),
        runner.getPassword(),
        runner.getPhoneNumber()
    });
    
}

private void clearInputFields() {
    usernameTextField.setText("");
    passwordField.setText("");
    phoneNumberTextField.setText("");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   int row = tinfo.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Please select a runner to edit.", "No Runner Selected", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirmation = JOptionPane.showConfirmDialog(this, "Do you want to edit this runner?", "Confirm Edit", JOptionPane.YES_NO_OPTION);
    if (confirmation == JOptionPane.YES_OPTION) {
        editRunner(row);
    }
}

private void editRunner(int row) {
    
    String username = (String) tinfo.getValueAt(row, 0);
    String password = (String) tinfo.getValueAt(row, 1);
    String phoneNumber = (String) tinfo.getValueAt(row, 2);

    usernameTextField.setText(username);
    passwordField.setText(password);
    phoneNumberTextField.setText(phoneNumber);

    ((DefaultTableModel) tinfo.getModel()).removeRow(row);

    try {
        RunnerDAO dao = new RunnerDAO();
        dao.deleteRunner(username);
        JOptionPane.showMessageDialog(this, "Runner information loaded for editing. Please make changes and save.", "Edit Runner", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error deleting runner from file.", "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row = tinfo.getSelectedRow();
    if(row == -1) {
        JOptionPane.showMessageDialog(this, "Please select a Runner to delete.", "No Customer Selected", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    int confirmation = JOptionPane.showConfirmDialog(this, "Do you really want to delete this Runner?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
    if(confirmation == JOptionPane.YES_OPTION) {
        String username = (String) tinfo.getValueAt(row, 0);
        try {
            new RunnerDAO().deleteRunner(username);
            ((DefaultTableModel)tinfo.getModel()).removeRow(row);
            JOptionPane.showMessageDialog(this, "Runner deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch(IOException e) {
            JOptionPane.showMessageDialog(this, "Runner deleting customer data.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

               
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Admin_Main adminmainFrame = new Admin_Main();
        adminmainFrame.setVisible(true);
        adminmainFrame.setLocationRelativeTo(null); 
        this.setVisible(false); 
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Reg_runner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reg_runner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reg_runner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reg_runner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reg_runner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField passwordField;
    private javax.swing.JTextField phoneNumberTextField;
    private javax.swing.JTable tinfo;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
