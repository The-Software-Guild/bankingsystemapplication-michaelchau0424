package com.banking.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.banking.comparator.BalanceComparator;
import com.banking.comparator.IdComparator;
import com.banking.comparator.NameComparator;
import com.banking.dao.DatabaseStorageDao;
import com.banking.dao.FileStorageDao;
import com.banking.dao.Idao;
import com.banking.dto.Customer;
import com.banking.exception.CustomerNotFoundException;
import com.banking.exception.InvalidOptionException;


public class Service implements Iservice {
	
	private DatabaseStorageDao db_dao;
	private FileStorageDao fs_dao;
	
	private ArrayList<Customer> customerArr = new ArrayList<>();
	
	public Service() {
		this.db_dao = new DatabaseStorageDao();
		this.fs_dao = new FileStorageDao();
	}
	
	public ArrayList<Customer> getCustomerArr() {
		return customerArr;
	}
	
	public void addNewCustomer(String name, int age, int mobileNumber, String passportNumber, String DOB) {
		customerArr.add(new Customer(name, age, mobileNumber, passportNumber, DOB));
	}
	
	public Customer getCustomerAtIndex(int i) {
		return customerArr.get(i);
	}
	
	public void sortCustomersBy(int option) throws InvalidOptionException {
		Comparator<Customer> c = null;
		switch(option) {
			case 1:
				c = new NameComparator();
				break;
			case 2:
				c = new IdComparator();
				break;
			case 3:
				c = new BalanceComparator();
				break;
			default:
				InvalidOptionException ioe = new InvalidOptionException("This is not a valid option. Please try again");
				throw ioe;
		}
		Collections.sort(customerArr, c);
		
	}
	
	public int getIndexOfCustomer(int id) throws CustomerNotFoundException {
		//return index of customer in customerArr
		int i = 0;
		while(i < this.customerArr.size()) {
			if(this.customerArr.get(i).getId() == id) {
				return i;
			}
			i++;
		}
		CustomerNotFoundException cnfe = new CustomerNotFoundException("Customer ID does not exist. Please enter a valid ID.");
		throw cnfe;
	}
	
	public boolean isValidDate(String date) {
		try {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate.parse(date, format); //attempt to parse date
			
			
		} catch (DateTimeParseException dtpe){
			return false;
		}
		return true;
		
	}
	
	

	@Override
	public void dbCreateAllCustomers() {
		// TODO Auto-generated method stub
		db_dao.saveAllCustomers(customerArr);
		
	}

	@Override
	public void fsCreateAllCustomers() {
		// TODO Auto-generated method stub
		fs_dao.saveAllCustomers(customerArr);
	}

	@Override
	public List<Customer> dbRetrieveAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> fsRetrieveAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}


}
