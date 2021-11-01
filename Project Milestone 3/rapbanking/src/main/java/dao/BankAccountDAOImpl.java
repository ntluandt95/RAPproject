package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.BankAccount;
import model.Transaction;
import model.User;
import utilities.ConnectionUtility;

public class BankAccountDAOImpl implements BankAccountDAO{
	private static TransactionDAOImpl transactionDAOImpl = new TransactionDAOImpl();
	public List<BankAccount> getBankAccounts() {
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		try {
			Connection conn = ConnectionUtility.getConnection();
			String sql = "SELECT * FROM BankAccount order by BankAccountId";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				BankAccount bankAccount = new BankAccount(rs.getInt(1),rs.getDouble(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				bankAccounts.add(bankAccount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankAccounts;
	}

	public List<BankAccount> getPendingBankAccounts() {
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		try {
			Connection conn = ConnectionUtility.getConnection();
			String sql = "SELECT * FROM BankAccount where status='pending' or status='rejected' order by BankAccountId";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				BankAccount bankAccount = new BankAccount(rs.getInt(1),rs.getDouble(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				bankAccounts.add(bankAccount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankAccounts;
	}
	
	public List<BankAccount> getBankAccounts(String username) {
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		try {
			Connection conn = ConnectionUtility.getConnection();
			String sql = "SELECT * FROM BankAccount where status='approved' and (PrimaryCustomerUsername=? or SecondaryCustomerUsername=?) order by BankAccountId";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, username);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				BankAccount bankAccount = new BankAccount(rs.getInt(1),rs.getDouble(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				bankAccounts.add(bankAccount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankAccounts;
	}

	public void registerBankAccount(BankAccount bankAccount) {
		try {
			Connection conn = ConnectionUtility.getConnection();
			String sql = "insert into bankaccount values (default,0,?,'pending',?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bankAccount.getAccountType());
			ps.setString(2, bankAccount.getPrimaryCustomerUsername());
			ps.setString(3, bankAccount.getSecondaryCustomerUsername());

			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void approveBankAccount(int id, int choice) {

		try {
			Connection conn = ConnectionUtility.getConnection();
			String sql;
			if(choice==1) {
				sql = "UPDATE BankAccount SET status='approved' where BankAccountId=?";
			}else {
				sql = "UPDATE BankAccount SET status='rejected' where BankAccountId=?";
			}
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}

	public BankAccount getBankAccount(int id) {
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		try {
			Connection conn = ConnectionUtility.getConnection();
			String sql = "SELECT * FROM BankAccount where BankAccountId=? order by BankAccountId";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				BankAccount bankAccount = new BankAccount(rs.getInt(1),rs.getDouble(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				bankAccounts.add(bankAccount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankAccounts.get(0);
	}

	public boolean deposit(int id, double amount) {
		if(amount<0) return false;
		double balance = getBankAccount(id).getBalance();
		double newbalance = balance+amount;
		
		try {
			Connection conn = ConnectionUtility.getConnection();
			String sql;
			sql = "UPDATE BankAccount SET balance=? where BankAccountId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, newbalance);
			ps.setInt(2, id);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Transaction transaction = new Transaction("deposit",amount, "completed", id, id);
		transactionDAOImpl.addTransaction(transaction);
		
		return true;
	}

	public boolean withdraw(int id, double amount) {
		if(amount<0) return false;
		double balance = getBankAccount(id).getBalance();
		double newbalance = balance-amount;
		if(newbalance<0) return false;
		try {
			Connection conn = ConnectionUtility.getConnection();
			String sql;
			sql = "UPDATE BankAccount SET balance=? where BankAccountId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, newbalance);
			ps.setInt(2, id);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Transaction transaction = new Transaction("withdraw",amount, "completed", id, id);
		transactionDAOImpl.addTransaction(transaction);
		
		return true;
	}

	

}
