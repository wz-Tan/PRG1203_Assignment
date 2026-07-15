package BankSystem;

import java.time.LocalDate;

public class LoanRepaymentAccount extends BankAccount{


	@Override
	boolean withdraw(double amount, LocalDate timestamp){
		throw new UnsupportedOperationException("Cannot withdraw from Loan Repayment Account");
	}


	@Override
	boolean withdraw(double amount, String note, LocalDate timestamp) {
		throw new UnsupportedOperationException("Cannot withdraw from Loan Repayment Account");
	}

}
