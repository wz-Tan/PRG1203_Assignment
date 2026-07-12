package BankSystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.time.LocalDate;

public class User {

    private String userId;
    private String username;
    private int passwordHash; // Compare hashed result
    private LocalDate currentDate; // Current Date for User -> Used to log transactions and keep their timing separate
    private ArrayList<BankAccount> accounts = new ArrayList<>(); // ArrayList of User's Bank Accounts

    public User() {}

    public User(String username, String password) {
        // Generate Unique ID
        UUID randomId = UUID.randomUUID();
        this.userId = randomId.toString();

        // Assign Params as Fields
        this.username = username;
        this.passwordHash = password.hashCode();
        this.currentDate = LocalDate.now(); 
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
    	return currentDate;
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
    	currentDate = currentDate.plusMonths(1);
    	
    	for (BankAccount account: accounts) {
    		account.calculateInterest();
    	}
    }

    void accountEnquiry() {
    	// Return back if no accounts available
    	if (accounts.size() == 0) {
    		System.out.println("No accounts available.");
    		return;
    	}
    	
    	// Prints available accounts
    	Scanner userInput = new Scanner(System.in);
    	System.out.println("Which account would you like to enquire: ");
    	for (BankAccount a : accounts) {
    		int i = 1;
    		System.out.printf("%d. %s\n", i, a.getAccountNumber());
    		i++;
    	}
    	int choice = userInput.nextInt();
    }

    public String toString(){
        return String.format("Username: %s , Password Hash: %s", username, passwordHash);
    }
}

