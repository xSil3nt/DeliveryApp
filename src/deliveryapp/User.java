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
    
    public int checkUsername(String username) {
        if (username == this.username){
            return 0;
        } else {
            return 1;
        }
    }
    
    public int checkPassword(String password) {
        if (password == this.password){
            return 0;
        } else {
            return 1;
        }
    }
}
