package BankSystem;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.UUID;

import BankSystem.Transaction.TransactionType;

// Abstract Parent Class of SavingsAccount and LoanRepaymentAccount
public abstract class BankAccount{
	protected enum AccountType { Savings, LoanRepayment };
	protected String accountNumber;
	protected double balance;
	protected static final double INTEREST_RATE = 0.01;
	protected ArrayList<Transaction> transactions;
	protected AccountType type;

	// Constructor
	public BankAccount(){
		this.accountNumber = UUID.randomUUID().toString();
		this.balance = 0.0;
		this.transactions = new ArrayList<Transaction>();
	}

	// Account number getter
	String getAccountNumber(){
		return accountNumber;
	}

	// Balance getter
	double getBalance(){
		return balance;
	}

	// Transactions getter 
	ArrayList<Transaction> getTransactions(){
		return new ArrayList<Transaction>(transactions);
	}

	// Deposit funds 
	boolean deposit(double amount, String note, LocalDate timestamp){
		// Reject non-positive amounts
		if (amount <= 0){
			System.out.println("Deposit amount must be greater than zero.");
			return false;
		}

		balance += amount;
		transactions.add(new Transaction(Transaction.TransactionType.Deposit, amount, note, timestamp));
		return true;
	}

	// Deposit without a note
	boolean deposit(double amount,  LocalDate timestamp){
		return deposit(amount, null, timestamp);
	}

	// Withdraw funds (abstract)
	abstract boolean withdraw(double amount, String note,  LocalDate timestamp);

	// Withdraw without a note (abstract)
	boolean withdraw(double amount,  LocalDate timestamp){
		return withdraw(amount, null, timestamp);
	}

	// Calculate and Apply Monthly Interest to the Balance
	void calculateInterest(LocalDate timestamp){
		double interest;
		
		interest = balance * INTEREST_RATE;
		balance += interest;
		transactions.add(new Transaction(Transaction.TransactionType.Interest, interest, "Monthly Interest", timestamp));
		System.out.printf("Interest of %.2f applied to account %s. New balance: %.2f%n", interest, accountNumber, balance);
	}

	abstract void printAccountInfo();
}
