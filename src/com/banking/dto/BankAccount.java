package com.banking.dto;

public abstract class BankAccount {
	protected long accountNo;
	protected long BSBCode;
	protected static final String bankName = "Bank of Michael";
	protected double balance;
	protected String openingDate;
	
	
	public BankAccount(long accountNo, long BSBcode, double balance, String openingDate) {
		this.accountNo = accountNo;
		this.BSBCode = BSBcode;
		this.balance = balance;
		this.openingDate = openingDate;
	}
	
	public abstract double calculateInterest();

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}


	public long getAccountNo() {
		return accountNo;
	}

	public long getBSBCode() {
		return BSBCode;
	}

	public String getOpeningDate() {
		return openingDate;
	}
	
	

}
