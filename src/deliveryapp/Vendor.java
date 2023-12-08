package deliveryapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Shazin
 */
public class Vendor extends User {
    private static final String CUST_NOTIFICATIONS_PATH = "programData\\custNotifications.txt";
    
    public Vendor(String username, String password) {
        super(username, password, 3);
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
    
    
}
