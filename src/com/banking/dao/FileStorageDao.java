package com.banking.dao;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.banking.dto.Customer;



public class FileStorageDao implements Idao {
	
	private static final String O_PATH = "C://C353//workspace//JavaBankingApp//outputs//customers.txt";
	
	private BufferedOutputStream bos;
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	
	public FileStorageDao() {
		try {
			this.fos = new FileOutputStream(O_PATH);
			this.bos = new BufferedOutputStream(this.fos);
			this.oos = new ObjectOutputStream(this.bos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void saveAllCustomers(List<Customer> customers) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Customer> retrieveAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}


	

}
