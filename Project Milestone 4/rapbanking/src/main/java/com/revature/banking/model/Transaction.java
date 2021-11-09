package com.revature.banking.model;

import java.util.Date;

public class Transaction {
	private int transactionId;
	private Date transactionDay;
	private String transactionType;
	private double amount;
	private String status;
	private int fromAccount;
	private int toAccount;
	public Transaction(int transactionId, Date transactionDay, String transactionType, double amount, String status,
			int fromAccount, int toAccount) {
		this.transactionId = transactionId;
		this.transactionDay = transactionDay;
		this.transactionType = transactionType;
		this.amount = amount;
		this.status = status;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
	}
	public Transaction(String transactionType, double amount, String status, int fromAccount, int toAccount) {
		this.transactionType = transactionType;
		this.amount = amount;
		this.status = status;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public Date getTransactionDay() {
		return transactionDay;
	}
	public void setTransactionDay(Date transactionDay) {
		this.transactionDay = transactionDay;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}
	public int getToAccount() {
		return toAccount;
	}
	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}
	
	
	
}
