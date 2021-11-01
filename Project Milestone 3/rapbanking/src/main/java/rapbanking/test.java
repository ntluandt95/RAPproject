package rapbanking;

import dao.BankAccountDAOImpl;
import dao.TransactionDAOImpl;
import dao.UserDAOImpl;
import model.BankAccount;
import model.User;
import utilities.TableBuilderUtility;

public class test {

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
		System.out.println(TableBuilderUtility.transactionTableBuilder(transactionDAOImpl.getTransaction(2)));
	}

}
