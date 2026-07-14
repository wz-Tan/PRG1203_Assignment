package BankSystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.time.LocalDate;

public class SavingsAccount extends BankAccount{
	
	//declare min balance as rm100 using final and double due to decimal places in money
	private static final double minBalance = 100.00;
	
	
	//constructors
	public SavingsAccount () {
		
	}
	
	//double check if the balance inside savings account is enough
	boolean verifyMinBalance(double amount) {
		return ( balance - amount ) >= minBalance;
	}
	
	
	//withdraw from savings
	@Override
	 boolean withdraw(double amount, String note, LocalDate timestamp) {
	
		// deny invalid withdrawal amounts
		if( amount <= 0 ) {
			System.out.println("INVALID, PLEASE TRY AGAIN");
			return false;
		}
		
		//check if user is allowed to perform withdraw base on amount in acc
		if (!verifyMinBalance(amount)) {
			System.out.println("WITHDRAWAL DENIED. MINIMUM BALANCE OF RM100 IN ACCOUNT IS REQUIRED");
			return false;
		}
		
		//perform withdraw
			balance -= amount;
		
		//record the transaction performed
			transactions.add(new Transaction(Transaction.TransactionType.Withdrawal, amount, note, timestamp));
			
		return true;
		}

	}
