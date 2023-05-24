package com.banking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.List;
import com.banking.dto.Customer;


public class DatabaseStorageDao implements Idao {
	
	//open connection
	public Connection openConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); //type4 driver, use .cj for mysql 8.0
			System.out.println("MySQL Driver registered with DriverManager");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/bankingdb", "root", "root");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	//close connection
	public void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void saveAllCustomers(List<Customer> customers) {
		// TODO Auto-generated method stub
		Connection con = openConnection();
		for (Customer c : customers) {
			String sql = "INSERT INTO customer (id, custName, age, mobileNumber, passportNumber, accountNumber, dateOfBirth) VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			try {
				PreparedStatement pstatement = con.prepareStatement(sql);
				pstatement.setInt(1, c.getId());
				pstatement.setString(2, c.getName());
				pstatement.setInt(3, c.getAge());
				pstatement.setInt(4, c.getMobileNumber());
				pstatement.setString(5, c.getPassportNumber());
				
				if(c.getAccount() != null) {
					pstatement.setLong(6, c.getAccount().getAccountNo());
				} else {
					pstatement.setNull(6, java.sql.Types.LONGVARBINARY);
				}
				
				pstatement.setObject(7, c.getDateOfBirth());
				pstatement.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		closeConnection(con);
		
	}

	@Override
	public List<Customer> retrieveAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
