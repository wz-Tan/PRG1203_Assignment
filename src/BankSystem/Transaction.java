package BankSystem;

import java.time.LocalDate;

public class Transaction {
	//enumerator
	public enum TransactionType { Deposit, Withdrawal };

	//variables
	private TransactionType type;
	private double amount;	
	private String note;
	private LocalDate timestamp;
	
	public Transaction() {}
	
	// without note
	public Transaction(TransactionType type, double amount, LocalDate timestamp) {
			this(type, amount, null, timestamp);
	}

	// with note
	public Transaction(TransactionType type, double amount, String note, LocalDate timestamp) {
			this.type = type;
			this.amount = amount;
			this.note = note;
			this.timestamp = timestamp; 
	}

	//toString method
	@Override
	public String toString() {
		return String.format(
			"Type = %s, Amount = %.2f, Note = %s, Timestamp = %s",
			type, amount, note, timestamp
		);
	}
}
