# BankingSystem-Java
**Author:** Bristi Chowdhury  
**Tech Stack:** Java, OOP, ArrayList, Scanner

## Features
- Create accounts with account number, name, balance
- Deposit & Withdraw with insufficient balance validation
- Display all accounts
- NullPointerException handling for invalid account numbers

## How to Run
```bash
javac *.java
java Main
```
## Concepts Applied
-Encapsulation using private fields + public getters
-ArrayList for dynamic object storage
-Linear search algorithm in findAccount()
-Exception handling & null safety
-Scanner nextInt() + nextLine() bug fix

## Key Learning
Fixed NullPointerException crashes by implementing null checks before object operations. 
Debugged Scanner input buffer issues using sc.nextLine() after nextInt()
