package adminPackage;

public class User {
    private String username;
    private String password;
    private String phoneNumber;
  
    // Constructor
    public User(String username, String password, String phoneNumber) {
        
        if (isUsernameRegistered(username)) {
            throw new IllegalArgumentException("Username already taken");
        }
        

        if (isPhoneNumberRegistered(phoneNumber)) {
            throw new IllegalArgumentException("Phone number already registered");
        }

        this.username = username;
        this.setPassword(password);       
        this.setPhoneNumber(phoneNumber); 
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password. Password must be 8-15 characters long and include numbers and letters.");
        }
        this.password = password; 
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        this.phoneNumber = phoneNumber;
    }
    
    // Validation Methods
    private static boolean isUsernameRegistered(String username) {
        return false; 
    }
    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,15}$");
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
    return phoneNumber.matches("^\\+?[1-9]\\d{1,14}$");
}
    private static boolean isPhoneNumberRegistered(String phoneNumber) {
        return false; 
    }
}
