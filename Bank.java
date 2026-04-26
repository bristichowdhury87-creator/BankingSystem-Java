import java.util.ArrayList;

public class Bank {
    private ArrayList<Account> accounts = new ArrayList<>();
    
    // Your job: Create account method. I'll give structure
    public void createAccount(int accNumber, String name, double initialBalance) {
        Account newAcc = new Account(accNumber, name, initialBalance);
        accounts.add(newAcc);
        System.out.println("Account created successfully!");
    }
    
    public Account findAccount(int accNumber) {
        for (int i = 0; i< accounts.size(); i++) {
            Account acc = accounts.get(i);
            if(acc.getAccNumber() == accNumber)
            return acc;
    } 
            return null; 
   }

public void displayAllAccounts(){
    for(int i = 0; i< accounts.size(); i++){
        Account acc = accounts.get(i);
        acc.displayAccount();
    }
}
}

