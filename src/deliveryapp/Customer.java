package deliveryapp;

public class Customer extends User {
    private String cart, orderHistory;
    private double balance;
    
    
    public Customer(String username, String password, double balance) {
        super(username, password, 2);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
}
