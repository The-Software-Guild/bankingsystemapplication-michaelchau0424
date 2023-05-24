package com.banking.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Customer {
	protected static int id_counter = 100;
	private int id;
	private String name;
	private int age;
	private int mobileNumber;
	private String passportNumber;
	private BankAccount account;
	private LocalDate dateOfBirth;
	
	
	public Customer(String name, int age, int mobileNumber, String passportNumber, String dateOfBirth) {
		this.id = Customer.id_counter++;
		this.name = name;
		this.age = age;
		this.mobileNumber = mobileNumber;
		this.passportNumber = passportNumber;
		this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
	}
	
	public String toString() {
		return "Details for " + this.name + ": \n    ID: " + this.id + "\n    Name: " + this.name + "\n    Age: " + this.age +"\n    Mobile No.: " + this.mobileNumber +"\n    Passport No.: " + this.passportNumber +"\n";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public BankAccount getAccount() {
		return account;
	}

	public void setAccount(BankAccount account) {
		this.account = account;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	
	
	
	
	
	
}
