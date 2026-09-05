import java.util.ArrayList;

public class Account {

    private int accNumber;
    private String name;
    private double balance;
    private ArrayList<String> transactions = new ArrayList<String>();
    
    public ArrayList<String> getTransactions() {
    return transactions;
}

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
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
            transactions.add("Deposited: " + amount + " " + "New Balance: " + balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
            transactions.add("Withdrawn: " + amount + "New Balance: " + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient Balance");
        } else {
            System.out.println("Invalid!");
        }
    }

    public void displayAccount() {
        System.out.println("Acc No: " + accNumber + ", Name: " + name + ", Balance: " + balance);
    }

    public void displayTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transaction history available.");
        } else {
            for (int i = 0; i < transactions.size(); i++) {
                System.out.println((i + 1) + "." + transactions.get(i));
            }
        }
    }
}