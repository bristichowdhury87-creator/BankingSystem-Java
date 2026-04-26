import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        
        while(true) {
            System.out.println("\n--- Banking System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display All Accounts");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            
            int choice = sc.nextInt();
            
            if(choice == 1) {
                System.out.print("Enter Acc No: ");
                int accNo = sc.nextInt();
                sc.nextLine(); // consume newline
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Initial Balance: ");
                double bal = sc.nextDouble();
                bank.createAccount(accNo, name, bal);
            }
            else if(choice == 2){
            System.out.println("Enter Acc No: ");
            int accNo = sc.nextInt();
            System.out.println("Enter amount to be deposited: ");
            double amount = sc.nextDouble();
            Account acc = bank.findAccount(accNo);
            if(acc != null){
            acc.deposit(amount);}
            else{ 
                System.out.println("Account not found. First Create one");
            }
            }
            else if(choice == 3){
                System.out.println("Enter Acc No: ");
                int accNo = sc.nextInt();
                System.out.println("Enter amount to be withdrawn: ");
                double amount = sc.nextDouble();
               Account acc = bank.findAccount(accNo);
            if(acc != null){
            acc.withdraw(amount); }
            else{ 
                System.out.println("Account not found. First Create one");
            } 
            }
            else if(choice == 4){
                bank.displayAllAccounts();
            }
            else if(choice == 5) {
                System.out.println("Thank you!");
                break;
            }
            else {
                System.out.println("Invalid choice");
            }
        }
        sc.close();
    }
}
