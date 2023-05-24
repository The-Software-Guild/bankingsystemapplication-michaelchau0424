package com.banking.controller;

import java.util.Scanner;


import com.banking.dto.BankAccount;
import com.banking.dto.FixedDepositAccount;
import com.banking.dto.SavingsAccount;
import com.banking.exception.CustomerNotFoundException;
import com.banking.exception.ExceededSavingsMinimumException;
import com.banking.exception.InvalidOptionException;
import com.banking.service.Service;
import com.banking.view.View;

//comment
public class Controller {
	private static final String menuItems = "1. Create New Customer Data\n" + 
			"2. Assign a Bank Account to a Customer\n" + 
			"3. Display balance or interest earned of a Customer\n" + 
			"4. Sort Customer Data\n" + 
			"5. Persist Customer Data\n" + 
			"6. Show All Customers\n" + 
			"7. Search Customers by Name\n" + 
			"8. Exit";
	
	private static final String[] promptCustomer = {"Name", "Age", "Mobile Number", "Passport Number", "Date of Birth"};
	private static final String[] promptSavingsAcc = {"Account Number","BSB","Balance", "Opening Date", "Is this a Salary Account? (true/false)"};
	private static final String[] promptFixedDepAcc = {"Account Number","BSB","Balance", "Opening Date", "Deposit Amount", "Tenure"};
	
	private static String userInput;
	private static String[] userPromptInput;
	
	
	
	public String[] requestDetails(String[] prompts, Scanner sc, View v) {
		String[] ans = new String[prompts.length];
		String input = "";
		
		for(int i = 0; i < prompts.length; i++) {
			v.printMessage(prompts[i] + ": ");
			input = sc.nextLine();
			ans[i] = input;
		}
		return ans;
	}
	
		
	public static void main(String[] args) {
		Controller controller = new Controller();
		Scanner sc = new Scanner(System.in);
		
		View view = new View();
		Service service = new Service();
		
		boolean isStopped = false;
		
		while(!isStopped) {
			view.printMessage("------------------------------------------------------------------\nPlease select an option: ");
			view.printMessage(menuItems);
			userInput = sc.nextLine();
			
			switch(userInput) {
				case "1":
					view.printMessage("\nCreate New Customer Data\nPlease enter new customer data in this format");
					userPromptInput = controller.requestDetails(Controller.promptCustomer, sc, view);
					if(service.isValidDate(userPromptInput[4])) {
						service.addNewCustomer(
								userPromptInput[0], 
								Integer.parseInt(userPromptInput[1]), 
								Integer.parseInt(userPromptInput[2]), 
								userPromptInput[3], 
								userPromptInput[4]);
						System.out.println("New customer successfully added");
						
					} else {
						System.out.println("Please enter a valid date");
					}
					
					break;
				case "2":
					view.printMessage("\nAssign a bank account to a customer\nPlease enter a Customer ID to assign a bank account to");
					userInput = sc.nextLine();
					int cust_id = Integer.parseInt(userInput);
					try {
						int indexCust = service.getIndexOfCustomer(cust_id);
						view.printMessage("1.Savings or 2.Fixed Deposit Account?");
						userInput = sc.nextLine();
						BankAccount ba = null;
						if (userInput.equals("1")) {
							userPromptInput = controller.requestDetails(Controller.promptSavingsAcc, sc, view);
							//Create Savings Account
							// if this is not a savings account and the initial balance is less than 100
							if (!Boolean.parseBoolean(userPromptInput[4]) && Integer.parseInt(userPromptInput[2]) < 100) {
								Exception esme = new ExceededSavingsMinimumException("Insufficient balance for Savings Account, Minimum balance should be $100");
								throw esme;
							}  else {
								ba = new SavingsAccount(
										Long.parseLong(userPromptInput[0]), 
										Long.parseLong(userPromptInput[1]), 
										Integer.parseInt(userPromptInput[2]), 
										userPromptInput[3], 
										Boolean.parseBoolean(userPromptInput[4]));
							}
							
							
							
						} else if(userInput.equals("2")) {
							userPromptInput = controller.requestDetails(Controller.promptFixedDepAcc, sc, view);
							
							//Create Fixed Deposit Account
							ba = new FixedDepositAccount(
									Long.parseLong(userPromptInput[0]), 
									Long.parseLong(userPromptInput[1]), 
									Integer.parseInt(userPromptInput[2]), 
									userPromptInput[3], 
									Integer.parseInt(userPromptInput[4]), 
									Integer.parseInt(userPromptInput[5]));
							
							
						}
						service.getCustomerAtIndex(indexCust).setAccount(ba);
						break;
						
					} catch(CustomerNotFoundException cnfe) {
						view.printMessage(cnfe.toString());
					} catch(ExceededSavingsMinimumException esme) {
						System.out.println(esme.toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				case "3":
					break;
				case "4":
					view.printMessage("Sort Customer Data\nHow would you like to sort the customers? 1.Name, 2.ID or 3.Balance");
					
					userInput = sc.nextLine();
					int option = Integer.parseInt(userInput);
					try {
						service.sortCustomersBy(option);
						System.out.println("Successfully sorted.\n");
					} catch (InvalidOptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					break;
				case "5":
					view.printMessage("Persist Customer Data\nHow would you like to persist the customer data?\n    1.File System\n    2.Database");
					userInput = sc.nextLine();
					try {
						switch(userInput) {
						case "1":
							service.fsCreateAllCustomers();
							break;
						case "2":
							
							service.dbCreateAllCustomers();
							break;
	
						default:
							InvalidOptionException ioe = new InvalidOptionException("This is not a valid option. Please try again");
							throw ioe;
						}
					} catch (InvalidOptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Successfully persisted customer data!\n");
					break;
				case "6":
					break;
				case "7":
					break;
				case "8":
					view.printMessage("Bye!");
					isStopped = true;
					sc.close();
					break;
				default:
					view.printMessage("That is not a valid option. Please try again.");
			}
		}
	}
	
}
