package BankSystem;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.UUID;

import BankSystem.Transaction.TransactionType;

// Abstract Parent Class of SavingsAccount and LoanRepaymentAccount
public abstract class BankAccount{
	public enum AccountType { Savings, LoanRepayment };
	private final String accountNumber;
	private double balance;
	private static final double INTEREST_RATE = 0.01;
	private final ArrayList<Transaction> transactions;
	private final AccountType type;

	// Constructor. Subclasses supply the starting balance and their account type.
	protected BankAccount(double initialBalance, AccountType type){
		this.accountNumber = UUID.randomUUID().toString();
		this.balance = initialBalance;
		this.transactions = new ArrayList<Transaction>();
		this.type = type;
	}

	// Account number getter
	String getAccountNumber(){
		return accountNumber;
	}

	// Balance getter
	double getBalance(){
		return balance;
	}

	AccountType getAccountType() {
		return type;
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

	// If account allows withdrawal
	boolean allowsWithdrawal(){
		return true; // Original is true
	}

	// Used by child class to change balance attribute
	protected void applyWithdrawal(double amount, String note, LocalDate timestamp){
		balance -= amount;
		transactions.add(new Transaction(Transaction.TransactionType.Withdrawal, amount, note, timestamp));
	}

	// Reduce the balance while recording the entry as a deposit.
	// Used where a deposit pays something down (e.g. repaying a loan).
	protected void applyRepayment(double amount, String note, LocalDate timestamp){
		balance -= amount;
		transactions.add(new Transaction(Transaction.TransactionType.Deposit, amount, note, timestamp));
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
