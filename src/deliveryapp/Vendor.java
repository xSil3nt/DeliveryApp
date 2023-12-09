package deliveryapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
        }        
    }

    public Date parseTimestamp(String timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
        try {
            dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Singapore")); 
            return dateFormat.parse(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; 
        }
    }

    public boolean isLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        return date.compareTo(yesterday) >= 0;
    }

    public boolean isLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date lastMonth = calendar.getTime();
        return date.compareTo(lastMonth) >= 0;
    }

    public boolean isLastQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        Date lastQuarter = calendar.getTime();
        return date.compareTo(lastQuarter) >= 0;
    }    
    
    
}
