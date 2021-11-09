package com.revature.banking.dao;

import java.util.List;

import com.revature.banking.model.Transaction;

public interface TransactionDAO {
	List<Transaction> getTransaction(int id);
	void addTransaction(Transaction transaction);
}
