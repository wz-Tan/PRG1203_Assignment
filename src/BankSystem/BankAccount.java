package BankSystem;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.UUID;

// Abstract Parent Class of SavingsAccount and LoanRepaymentAccount
public abstract class BankAccount{
	private String accountNumber;
	protected double balance;
	protected final double interestRate = 0.01;
	protected ArrayList<Transaction> transactions;

	// Constructor
	public BankAccount(){
		this.accountNumber = UUID.randomUUID().toString();
		this.balance = 0.0;
		this.transactions = new ArrayList<Transaction>();
	}

	// Account Number Getter
	String getAccountNumber(){
		return accountNumber;
	}

	// Balance Getter
	double getBalance(){
		return balance;
	}

	// Transactions Getter 
	ArrayList<Transaction> getTransactions(){
		return new ArrayList<Transaction>(transactions);
	}

	// Deposit Funds 
	boolean deposit(double amount, String note, LocalDate timestamp){
		// Reject Non-Positive Amounts
		if (amount <= 0){
			System.out.println("Deposit amount must be greater than zero.");
			return false;
		}

		balance += amount;
		transactions.add(new Transaction(Transaction.TransactionType.Deposit, amount, note, timestamp));
		return true;
	}

	// Deposit Without a Note
	boolean deposit(double amount,  LocalDate timestamp){
		return deposit(amount, null);
	}

	// Withdraw Funds (Abstract)
	abstract boolean withdraw(double amount, String note);

	// Withdraw Without a Note (Abstract)
	boolean withdraw(double amount){
		return withdraw(amount, null);
	}

	// Calculate and Apply Monthly Interest to the Balance
	void calculateInterest(LocalDate timestamp){
		double interest;
		
		interest = balance * interestRate;
		balance += interest;
		transactions.add(new Transaction(Transaction.TransactionType.Deposit, interest, "Monthly Interest", timestamp));
		System.out.printf("Interest of %.2f applied to account %s. New balance: %.2f%n", interest, accountNumber, balance);
	}
	
	void getAccountInfo() {
		System.out.printf("Account Number: %s\n", accountNumber);
		System.out.printf("Balance       : %.2f\n", balance);
		System.out.printf("Transactions: \n");
		for (Transaction t : transactions) {
			System.out.printf("%s\n", t.toString());
		}
	}
}
