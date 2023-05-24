package com.banking.dto;

public class FixedDepositAccount extends BankAccount {
	protected int depositAmount;
	protected int tenure;
	protected double interestEarned;
	
	public FixedDepositAccount(long accountNo, long BSBcode, double balance, String openingDate, int depositAmount, int tenure) {
		super(accountNo, BSBcode, balance, openingDate);	
	}

	@Override
	public double calculateInterest() {
		// TODO Auto-generated method stub
		return 0.8*this.tenure*this.depositAmount;
	}

}
