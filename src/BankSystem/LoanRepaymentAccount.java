package BankSystem;

import java.time.LocalDate;

public class LoanRepaymentAccount extends BankAccount{

	public LoanRepaymentAccount() {
		this(0.0);
	}

	public LoanRepaymentAccount(double repaidloan) {
		super(repaidloan, AccountType.LoanRepayment);
	}

	@Override
	boolean allowsWithdrawal(){
		return false;
	}

	// A deposit into a loan account is a repayment, so it reduces the outstanding balance
	@Override
	boolean deposit(double amount, String note, LocalDate timestamp){
		// Reject non-positive amounts
		if (amount <= 0){
			System.out.println("Repayment amount must be greater than zero.");
			return false;
		}

		applyRepayment(amount, note, timestamp);
		return true;
	}

	@Override
	boolean withdraw(double amount, LocalDate timestamp){
		throw new UnsupportedOperationException("Cannot withdraw from Loan Repayment Account");
	}

	@Override
	boolean withdraw(double amount, String note, LocalDate timestamp) {
		throw new UnsupportedOperationException("Cannot withdraw from Loan Repayment Account");
	}

	@Override
	void printAccountInfo() {
		System.out.printf("Account Number     : %s\n", getAccountNumber());
		System.out.printf("Account Type       : %s\n", getAccountType());
		System.out.printf("Outstanding Balance: RM %.2f\n", getBalance());
		System.out.printf("Transactions       : \n");
		for (Transaction t : getTransactions()) {
			System.out.printf("%s\n", t.toString());
		}
	}

}
