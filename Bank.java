import java.util.ArrayList;

public class Bank {
    // We store all accounts here. Why ArrayList? Because size not fixed.
    private ArrayList<Account> accounts = new ArrayList<>();
    
    // Your job: Create account method. I'll give structure
    public void createAccount(int accNumber, String name, double initialBalance) {
        Account newAcc = new Account(accNumber, name, initialBalance);
        accounts.add(newAcc);
        System.out.println("Account created successfully!");
    }
    
    // TODO by Bristi: write findAccount(int accNumber) method
    // Hint: Loop through accounts. If acc.getAccNumber() == accNumber, return that account. Else return null
    public Account findAccount(int accNumber) {
        for (int i = 0; i< accounts.size(); i++) {
            Account acc = accounts.get(i);
            if(acc.getAccNumber() == accNumber)
            return acc;
    } 
            return null; 
   }
  // TODO by Bristi: write displayAllAccounts() method
    // Hint: Loop through accounts and call acc.displayAccount() for each
public void displayAllAccounts(){
    for(int i = 0; i< accounts.size(); i++){
        Account acc = accounts.get(i);
        acc.displayAccount();
    }
}
}

