package adminPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAO {
    private static final String FILE_PATH = ("programData\\customerCreds.txt");
    private static final String TOP_UP_FILE_PATH = "programData\\TOP_UP.txt";

    public void addCustomer(Customer customer) throws IOException {
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(customer.getUsername() + ";" + customer.getPassword() + ";0.0;" + customer.getAddress() + ";" + customer.getPhoneNumber());
        }
    }


    public List<Customer> getAllCustomers() throws IOException {
        File file = new File(FILE_PATH);
        List<Customer> customers = new ArrayList<>();
        
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    customers.add(parseCustomer(line));
                }
            }
        }

        return customers;
    }

    public void updateCustomer(Customer customerToUpdate) throws IOException {
    List<Customer> customers = getAllCustomers();
    for (int i = 0; i < customers.size(); i++) {
        if (customers.get(i).getUsername().equalsIgnoreCase(customerToUpdate.getUsername())) {
            customers.set(i, customerToUpdate);
            break;
        }
    }
    saveAllCustomers(customers);
}


    public void deleteCustomer(String username) throws IOException {
    List<Customer> customers = getAllCustomers();
  
    customers.removeIf(customer -> customer != null && customer.getUsername().equals(username));
    saveAllCustomers(customers);
}


    
    public Customer findCustomerByUsername(String username) throws IOException {
    List<Customer> customers = getAllCustomers();
    for (Customer customer : customers) {
        if (customer.getUsername().equalsIgnoreCase(username)) {
            return customer;
        }
    }
    return null; 
}
   public void notifyCustomerOfTopUp(Customer customer, double topUpAmount) throws IOException {
    String notificationFilePath = "programData\\custNotifications.txt"; 

    List<String> existingNotifications = readNotificationsFromFile(notificationFilePath);
    List<String> filteredNotifications = new ArrayList<>();

    for (String notification : existingNotifications) {
        if (!notification.startsWith("Dear " + customer.getUsername())) {
            filteredNotifications.add(notification);
        }
    }

    String currentNotification = customer.getUsername() + ";" + "Dear " + customer.getUsername() + ", your account has been topped up with " + topUpAmount + ".";
    filteredNotifications.add(currentNotification);

    writeNotificationsToFile(notificationFilePath, filteredNotifications);
}

private List<String> readNotificationsFromFile(String filePath) throws IOException {
    List<String> notifications = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            notifications.add(line);
        }
    }
    return notifications;
}

private void writeNotificationsToFile(String filePath, List<String> notifications) throws IOException {
    try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
        for (String notification : notifications) {
            out.println(notification);
        }
        out.flush(); 
    }
}

 
   public List<String> getTopUpHistoryForUser(String username) throws IOException {
    List<String> userTopUps = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(TOP_UP_FILE_PATH))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";"); 
            if (parts.length > 5 && parts[2].trim().substring("Username: ".length()).equalsIgnoreCase(username)) {
                String dateTime = parts[1].trim().substring("Date & Time: ".length());
                String topUpAmount = parts[4].trim().substring("Top Up Amount: ".length());
                String newBalance = parts[5].trim().substring("New Balance: ".length());
                userTopUps.add(String.format("Date & Time: %s, Username: %s, Previous Amount: %s, Top Up Amount: %s, New Balance: %s", 
                                             dateTime, username, parts[3].trim().substring("Previous Amount: ".length()), 
                                             topUpAmount, newBalance));
            }
        }
    }
    return userTopUps;
}



    private void saveAllCustomers(List<Customer> customers) throws IOException {
    try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH, false))) { 
        for (Customer customer : customers) {
            if (customer != null) {
                out.println(customer.getUsername() + ";" + customer.getPassword() + ";" + customer.getAmount() + ";" + customer.getAddress() + ";" + customer.getPhoneNumber());
            }
        }
    }
}


    private Customer parseCustomer(String line) {
    String[] parts = line.split(";");
    if (parts.length != 5) return null;
    String username = parts[0];
    String password = parts[1];
   
    double amount = Double.parseDouble(parts[2]); 
    String address = parts[3];
    String phoneNumber = parts[4];
    
    Customer customer = new Customer(username, password, phoneNumber, address);
    customer.setAmount(amount);
    
    return customer;
}

}
