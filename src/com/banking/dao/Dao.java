package com.banking.dao;

import java.util.List;

import com.banking.dto.Customer;
//comment
public interface Idao {
	void saveAllCustomers(List<Customer> customers);
	List<Customer> retrieveAllCustomers();
}

