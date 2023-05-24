package com.banking.dto;

public class SavingsAccount extends BankAccount {
	protected final boolean isSalaryAccount;
	protected int minBalance;
	protected double interestEarned;
	
	
	public SavingsAccount(long accountNo, long BSBcode, double balance, String openingDate, boolean isSalaryAccount) {
		super(accountNo, BSBcode, balance, openingDate);	
		
		
		this.isSalaryAccount = isSalaryAccount;
		if(!this.isSalaryAccount) {
			this.minBalance = 100;
		} else {
			this.minBalance = 0;
		}
	}


	@Override
	public double calculateInterest() {
		// TODO Auto-generated method stub
		return 0.4*this.balance;
	}
	
	public void deposit(int amount) {
		this.balance += amount;
	}
	
	public void withdraw(int amount) {
		this.balance -= amount;
	}
	
	
	
	
}
