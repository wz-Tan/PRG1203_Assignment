package BankSystem;

import java.util.ArrayList;

// Parent Class of Savings Account and Loan Repayment Account
public class BankAccount{
	private String accountNumber; 
	private double balance; 
	private final double interestRate = 0.01;  // Set interest rate at 1% per month as constant
	private ArrayList<Transaction> transactions; // List of transactions under this account
	
    public BankAccount(){};
    
    void calculateInterest() {
    	System.out.println("Calculate interest");
    }
}
