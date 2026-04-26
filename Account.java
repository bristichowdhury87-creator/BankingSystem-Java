
public class Account {
    // Your job: Decide what data an account needs. I'll start you off.
    private int accNumber;
    private String name;
    private double balance;

    // Constructor - Your job: fill this after I explain
    public Account(int accNumber, String name, double balance) {
        this.accNumber = accNumber;
        this.name = name;
        this.balance = balance;
    }

    // Your job: Make getters. I'll do 1, you do 2
    public int getAccNumber() {
        return accNumber;
    }
    
    // TODO by Bristi: write getName() and getBalance() same way
    public String getName() {
        return name;
    }
    public double getBalance() {
        return balance;
    }
    // Your job: Deposit method. I'll do this one fully
    public void deposit(double amount) {
        if(amount > 0) {
            balance = balance + amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid amount. Must be > 0");
        }
    }
    
    // TODO by Bristi: write withdraw(double amount) method
    // Hint: Check if amount > 0 AND amount <= balance before subtracting
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
