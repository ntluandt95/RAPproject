package com.revature.banking.model;

public class BankAccount {
	private int BankAccountId;
	private double Balance;
	private String accountType;
	private String Status;
	private String PrimaryCustomerUsername;
	private String SecondaryCustomerUsername;
	
	
	public BankAccount(int bankAccountId, double balance, String accountType, String status,
			String primaryCustomerUsername, String secondaryCustomerUsername) {
		
		BankAccountId = bankAccountId;
		Balance = balance;
		this.accountType = accountType;
		Status = status;
		PrimaryCustomerUsername = primaryCustomerUsername;
		SecondaryCustomerUsername = secondaryCustomerUsername;
	}
	
	public BankAccount(String accountType, String primaryCustomerUsername, String secondaryCustomerUsername) {

		this.accountType = accountType;
		PrimaryCustomerUsername = primaryCustomerUsername;
		SecondaryCustomerUsername = secondaryCustomerUsername;
	}

	public int getBankAccountId() {
		return BankAccountId;
	}
	public void setBankAccountId(int bankAccountId) {
		BankAccountId = bankAccountId;
	}
	public double getBalance() {
		return Balance;
	}
	public void setBalance(double balance) {
		Balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getPrimaryCustomerUsername() {
		return PrimaryCustomerUsername;
	}
	public void setPrimaryCustomerUsername(String primaryCustomerUsername) {
		PrimaryCustomerUsername = primaryCustomerUsername;
	}
	public String getSecondaryCustomerUsername() {
		return SecondaryCustomerUsername;
	}
	public void setSecondaryCustomerUsername(String secondaryCustomerUsername) {
		SecondaryCustomerUsername = secondaryCustomerUsername;
	}

	@Override
	public String toString() {
		return "BankAccount [BankAccountId=" + BankAccountId + ", Balance=" + Balance + ", accountType=" + accountType
				+ ", Status=" + Status + ", PrimaryCustomerUsername=" + PrimaryCustomerUsername
				+ ", SecondaryCustomerUsername=" + SecondaryCustomerUsername + "]";
	}
	
	
}
