package BankSystem;

import java.time.LocalDate;

public class LoanRepaymentAccount extends BankAccount{
	
	public LoanRepaymentAccount() {
		
	}

	public LoanRepaymentAccount(double repaidloan) {
		balance = repaidloan;
		type = AccountType.LoanRepayment;
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
		System.out.printf("Account Number: %s\n", accountNumber);
		System.out.printf("Account Type: %s\n", type);
		System.out.printf("Outstanding Balance: %.2f\n", balance);
		System.out.printf("Transactions: \n");
		for (Transaction t : transactions) {
			System.out.printf("%s\n", t.toString());
		}
	}

}
