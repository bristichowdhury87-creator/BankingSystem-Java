import java.util.HashMap;

public class Bank {
    private HashMap<Integer, Account> accounts = new HashMap<>();

    public void createAccount(int accNumber, String name, double initialBalance) {
        if(accounts.containsKey(accNumber)){
            return;
        }

        Account newAcc = new Account(accNumber, name, initialBalance);
        accounts.put(accNumber, newAcc);
        System.out.println(" Account created successfully!");
    }

    public Account findAccount(int accNumber) {
        if(accounts.containsKey(accNumber)){
            return accounts.get(accNumber);
        }
        else{

            return null;
        }
    }
}