package dao;

import java.util.List;

import io.bretty.console.table.Table;
import model.BankAccount;

public interface BankAccountDAO {
	List<BankAccount> getBankAccounts();
	List<BankAccount> getBankAccounts(String username);
	BankAccount getBankAccount(int id);
	List<BankAccount> getPendingBankAccounts();
	void registerBankAccount(BankAccount bankAccount);
	void approveBankAccount(int id, int choice);
	boolean deposit(int id, double amount);
	boolean withdraw(int id, double amount);
	
}
