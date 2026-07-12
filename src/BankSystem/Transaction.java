package BankSystem;

import java.sql.Timestamp;

public class Transaction {
	//enumerator
	public enum TransactionType { Deposit, Withdrawal };

	//variables
	private TransactionType type;
	private double amount;	
	private String note;
	private Timestamp localDate;
	
	//<<constructors>>
	// without note
	public Transaction(TransactionType type, double amount) {
			this(type, amount, null);
	}

	// with note
	public Transaction(TransactionType type, double amount, String note) {
			this.type = type;
			this.amount = amount;
			this.note = note;
			this.localDate = new Timestamp(System.currentTimeMillis());
	}

	//toString method
	@Override
	public String toString() {
		return String.format(
			"type = %s, amount = %.2f, note = %s, LocalDate = %s",
			type, amount, note, localDate
		);
	}
}