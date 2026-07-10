package BankSystem;

import java.util.ArrayList;
import java.util.UUID;

public class User {

    private String userId;
    private String username;
    private int passwordHash; // Compare hashed result
    private ArrayList<BankAccount> accounts = new ArrayList<>(); // ArrayList of User's Bank Accounts

    public User() {}

    public User(String username, String password) {
        // Generate Unique ID
        UUID randomId = UUID.randomUUID();
        this.userId = randomId.toString();

        // Assign Params as Fields
        this.username = username;
        this.passwordHash = password.hashCode();
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

    // Adding New Account
    void addAccount(BankAccount newAccount){
        accounts.add(newAccount);
    }

    // Check if Hash Matches
    boolean verifyPassword(String password) {
        return password.hashCode() == passwordHash;
    }


    public String toString(){
        return String.format("Username: %s , Password Hash: %s", username, passwordHash);
    }
}

