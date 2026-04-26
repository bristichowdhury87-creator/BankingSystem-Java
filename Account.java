
public class Account {
    private int accNumber;
    private String name;
    private double balance;

    public Account(int accNumber, String name, double balance) {
        this.accNumber = accNumber;
        this.name = name;
        this.balance = balance;
    }

    public int getAccNumber() {
        return accNumber;
    }
    
    public String getName() {
        return name;
    }
    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        if(amount > 0) {
            balance = balance + amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid amount. Must be > 0");
        }
    }
    public void withdraw(double amount) {
        if(amount > 0 && amount <= balance){
            balance = balance - amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount");
        }
    }
    public void displayAccount() {
        System.out.println("Acc No: " + accNumber + ", Name: " + name + ", Balance: " + balance);
    }
}
