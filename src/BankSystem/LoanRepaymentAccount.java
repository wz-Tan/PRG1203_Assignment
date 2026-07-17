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
