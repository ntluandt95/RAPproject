package com.revature.banking.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.banking.dao.BankAccountDAOImpl;
import com.revature.banking.dao.TransactionDAOImpl;
import com.revature.banking.dao.UserDAOImpl;
import com.revature.banking.model.BankAccount;
import com.revature.banking.model.User;
import com.revature.banking.utilities.TableBuilderUtility;

public class test {
	static Logger logger = LogManager.getLogger();
	public static void main(String[] args) {
		UserDAOImpl user = new UserDAOImpl();
		BankAccountDAOImpl bankAccount = new BankAccountDAOImpl();
		TransactionDAOImpl transactionDAOImpl = new TransactionDAOImpl();
		//user.addUser(new User("Apple", "Pie"));
		//user.deleteUser(2);
		//System.out.println(user.login("customer1", "12345"));
//		user.addUser(new User("customer2", "12345", "Sum", "Sam", "customer"));
//		System.out.println(user.getUsers());
//		bankAccount.registerBankAccount(new BankAccount("saving", "customer1", null));
		//System.out.println(BankService.tableBuilder(bankAccount.getBankAccounts()));
		//System.out.println(TableBuilderUtility.transactionTableBuilder(transactionDAOImpl.getTransaction(2)));
		//System.out.println(bankAccount.getBankAccount(100));
		
		logger.trace("hahahahaha");
		
	}

}
