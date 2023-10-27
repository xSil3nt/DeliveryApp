package deliveryapp;

public class User {
    private String username;
    private String password;
    private int userType;
    
    public User(String username, String password, int userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
}
