package deliveryapp;

public class User {
    private String username;
    private String password;
    
    private int userType;
    /*
        1: Admin
        2: Customer
        3: Vendor
        4: Deliver Runner
    */
    
    public User(String username, String password, int userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
}
