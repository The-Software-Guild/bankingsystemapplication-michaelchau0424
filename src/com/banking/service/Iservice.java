package com.banking.service;

import java.util.List;

import com.banking.dao.Idao;
import com.banking.dto.Customer;

public interface Iservice {
	void dbCreateAllCustomers();
	void fsCreateAllCustomers();
	List<Customer> dbRetrieveAllCustomers();
	List<Customer> fsRetrieveAllCustomers();
}
