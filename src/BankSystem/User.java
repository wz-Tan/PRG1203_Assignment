package BankSystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;
import java.time.LocalDate;

public class User {

    private String userId;
    private String username;
    private int passwordHash; // Compare hashed result
    private ArrayList<BankAccount> accounts = new ArrayList<>(); // ArrayList of User's Bank Accounts

    private LocalDate currentDate; // Current Date for User -> Used to log transactions and keep their timing separate
    private int monthsPassed;


    public User() {}

    public User(String username, String password) {
        // Generate Unique ID
        UUID randomId = UUID.randomUUID();
        this.userId = randomId.toString();

        // Assign Params as Fields
        this.username = username;
        this.passwordHash = password.hashCode();
        this.currentDate = LocalDate.now();
        this.monthsPassed = 0;
    }

    // Username Getter
    String getUsername(){
        return username;
    }

    // Account Getter and Adder (No Setter Since ArrayList is Mutable)
    ArrayList<BankAccount> getBankAccounts(){
        // Return A Copy (Not Original Reference)
        return new ArrayList<BankAccount>(accounts);
    }

    // Get Date for Transactions
    LocalDate getCurrentDate() {
    	return currentDate.plusMonths(monthsPassed);
    }

    // Adding New Account
    void addAccount(BankAccount newAccount){
        accounts.add(newAccount);
    }

    // Check if Hash Matches
    boolean verifyPassword(String password) {
        return password.hashCode() == passwordHash;
    }

    // Logic to Advance Month (Calculates Interest and Updates currentDate
    void advanceMonth() {
    	monthsPassed ++;

    	for (BankAccount account: accounts) {
    		account.calculateInterest(getCurrentDate());
    	}
    }

    void accountEnquiry(Scanner scanner) {
    	BankAccount chosenAccount = chooseAccount(scanner);
    	chosenAccount.printAccountInfo();
    }

    // Select an Account from All Accounts
    BankAccount chooseAccount(Scanner scanner) {
    	// Return back if no accounts available
    	if (accounts.size() == 0) {
    		System.out.println("No accounts available.");
    		return null;
    	}

    	// Prints available accounts
		int i = 1;
    	System.out.println("Which account would you like to choose: ");
    	for (BankAccount a : accounts) {
    		System.out.printf("%d. %s (%s Account)\n", i, a.getAccountNumber(), a.getAccountType());
    		i++;
    	}

    	try {
    		int choice = scanner.nextInt();
        	return accounts.get(choice-1);
    	}

    	catch(InputMismatchException e) {
    		System.out.println("Error: please input a number.");
    		scanner.nextLine();
    	}

    	catch(IllegalArgumentException e) {
    		System.out.println(e.getMessage());
    	}

    	catch(Exception e) {
    		System.out.println("Account does not exist.");
    	}

    	return null;
    }

    public void createSavingsAccount(Scanner scanner) {
    	try {
        	System.out.println("To open a savings account, you need to have at least RM100. How much money would you like to put in?");
    	    double initialDeposit = scanner.nextDouble();
    	    scanner.nextLine();

    	    SavingsAccount sa = new SavingsAccount(initialDeposit);
    	    // Add account to arrayList
    	    addAccount(sa);
    	    System.out.println("Savings account created successfully");
    	}
    	catch(InputMismatchException e) {
    		System.out.println("Error: please input a number.");
    	}
    	catch(IllegalArgumentException e) {
    		System.out.println(e.getMessage());
    	}
    }

    public void createLoanRepaymentAccount(Scanner scanner) {
		try {
    		System.out.print("Enter amount of loan you have (RM):  ");
    		double outstandingLoan = scanner.nextDouble();
    		scanner.nextLine();
    		
    		LoanRepaymentAccount lra = new LoanRepaymentAccount(outstandingLoan);
    		addAccount(lra);
        	System.out.printf("Loan Repayment Account created. Outstanding loan: RM %.2f%n", outstandingLoan);
		}
		catch(InputMismatchException e) {
    		System.out.println("Error: please input a number.");
    		scanner.nextLine();
    	}
    	catch(IllegalArgumentException e) {
    		System.out.println(e.getMessage());
    	}
    }

    public String toString(){
        return String.format("Username: %s , Password Hash: %s", username, passwordHash);
    }
}
