
package oops;
interface BankingService {
 void deposit(double amount);
 void withdraw(double amount);
}

class Account implements BankingService {
 private double balance;

 public Account() {
     this.balance = 0.0;
 }

 @Override
 public void deposit(double amount) {
     if (amount > 0) {
         balance += amount;
         System.out.println("Deposited: ₹" + amount);
     } else {
         System.out.println("Invalid deposit amount.");
     }
 }

 @Override
 public void withdraw(double amount) {
     if (amount <= 0) {
         System.out.println("Invalid withdrawal amount.");
     } else if (amount > balance) {
         System.out.println("Insufficient funds.");
     } else {
         balance -= amount;
         System.out.println("Withdrawn: ₹" + amount);
     }
 }

 public double getBalance() {
     return balance;
 }
}

class Transaction {
 public void log(String message) {
     System.out.println("Transaction Log: " + message);
 }
}

public class BankingSystem {
 public static void main(String[] args) {
     BankingService account = new Account();
     Transaction transaction = new Transaction();
     
     System.out.println("Janise Deepthi YP");
     System.out.println("2117240070124\n");

    

     // Test Case 5: Log transaction
     transaction.log("Deposited ₹1000, Withdrawn ₹500");
 }
}

