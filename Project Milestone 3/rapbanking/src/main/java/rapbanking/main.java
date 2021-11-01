package rapbanking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import dao.BankAccountDAOImpl;
import dao.TransactionDAOImpl;
import dao.UserDAOImpl;
import model.BankAccount;
import model.User;
import utilities.TableBuilderUtility;
import utilities.ConnectionUtility;

public class main {
	static Scanner sc = new Scanner(System.in);
	static UserDAOImpl userDAOImpl = new UserDAOImpl();
	static BankAccountDAOImpl bankAccountDAOImpl = new BankAccountDAOImpl();
	static TransactionDAOImpl transactionDAOImpl = new TransactionDAOImpl();
	static User loginUsername;

	public static void mainMenu() {

		int choice;
		while (true) {
			if(loginUsername==null) {
				System.out.println("Welcome to Main Menu!!!");
				System.out.println("1. Login");
				System.out.println("2. Register");
				System.out.println("3. Quit");
				System.out.print("Enter Your Choice : ");
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					Login();
					break;
				case 2:
					Register();
					break;
				case 3:
					System.exit(0);
				default:
					System.out.println("Wrong Entry");
				}
			}else if(loginUsername.getUserRole().equalsIgnoreCase("customer")){
				System.out.println("Hello Customer "+ loginUsername.getFirstName() +" "+ loginUsername.getLastName() +"! Welcome to Main Menu!!!");
				System.out.println("1. Apply for a new bank account");
				System.out.println("2. View all your bank account");
				System.out.println("3. Quit");
				System.out.print("Enter Your Choice : ");
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					applyNewBankAccount();
					break;
				case 2:
					viewBankAccount();
					break;
				case 3:
					System.exit(0);
				default:
					System.out.println("Wrong Entry");
				}
			}else if(loginUsername.getUserRole().equalsIgnoreCase("employee")){
				System.out.println("Hello Employee "+ loginUsername.getFirstName() +" "+ loginUsername.getLastName() +"! Welcome to Main Menu!!!");
				System.out.println("1. Approve or Reject an account");
				System.out.println("2. View a customer's bank accounts");
				System.out.println("3. Quit");
				System.out.print("Enter Your Choice : ");
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					approveAccount();
					break;
				case 2:
					viewAllCustomer();
					break;
				case 3:
					System.exit(0);
				default:
					System.out.println("Wrong Entry");
				}
			}
		}
		
	}

	private static void viewAllCustomer() {
		System.out.println("List of All Customer:");
		System.out.println(TableBuilderUtility.customerTableBuilder(userDAOImpl.getCustomers()));
		String username;
		while(true) {
			try {		
				while (true) {
					System.out.println("Enter a Username to select a Customer:");
					username = sc.nextLine();
					if(userDAOImpl.getUser(username)!=null) {
						break;
					}else {
						System.out.println("Invalid Username. Please try again!");
					}
				}
				
				
				
				List<User> customer = new ArrayList<User>();
				customer.add(userDAOImpl.getUser(username));
				System.out.println(TableBuilderUtility.customerTableBuilder(customer));
				break;
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid Username, try again!");
			}
		}
		viewCustomerBankAccounts(username);
	}

	private static void viewCustomerBankAccounts(String username) {
		System.out.println("List of "+username+ " Bank Account:");
		List<BankAccount> bankAccounts = bankAccountDAOImpl.getBankAccounts(username);
		System.out.println(TableBuilderUtility.customerBankAccountTableBuilder(bankAccounts));
		System.out.println("All of "+username+" Transactions");
		for (BankAccount bankAccount : bankAccounts) {
			int id = bankAccount.getBankAccountId();
			if(transactionDAOImpl.getTransaction(id).size()>0) {
				System.out.println("Account Id"+id+" Transaction History");
				System.out.println(TableBuilderUtility.transactionTableBuilder(transactionDAOImpl.getTransaction(id)));
			}
		}
	}

	private static void viewBankAccount() {
		System.out.println("List of your Bank Account:");
		System.out.println(TableBuilderUtility.bankAccountTableBuilder(bankAccountDAOImpl.getBankAccounts(loginUsername.getUsername())));
		int id;
		while(true) {
			try {
				System.out.println("Enter the ID to select an account:");
				id = sc.nextInt();
				sc.nextLine();
				List<BankAccount> bankAccount = new ArrayList<BankAccount>();
				bankAccount.add(bankAccountDAOImpl.getBankAccount(id));
				System.out.println(TableBuilderUtility.bankAccountTableBuilder(bankAccount));
				break;
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid ID, try again!");
			}
		}
		
		bankATM(id);
		
		
	}

	private static void bankATM(int id) {
		System.out.println("1. View Balance");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. View Transaction History");
		System.out.println("5. Money Transfer");
		System.out.println("6. Return to list of your Bank Account");
		System.out.println("7. Return to Main Menu");
		System.out.print("Enter Your Choice : ");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			System.out.println("Balance = "+bankAccountDAOImpl.getBankAccount(id).getBalance());
			bankATM(id);
			return;
		case 2:
			while (true) {
				System.out.println("Enter the amount of money to deposit:");
				double amount = sc.nextDouble();
				sc.nextLine();
				if(!bankAccountDAOImpl.deposit(id, amount)) {
					System.out.println("Please Enter a POSITIVE amount of money");
				}else {
					System.out.println("New balance = "+bankAccountDAOImpl.getBankAccount(id).getBalance());
					break;
				}
				
				
			}
			bankATM(id);
			return;
		case 3:
			while (true) {
				System.out.println("Enter the amount of money to withdraw:");
				double amount = sc.nextDouble();
				sc.nextLine();
				if(amount<0) {
					System.out.println("Please Enter a POSITIVE amount of money");
				}
				else if(!bankAccountDAOImpl.withdraw(id, amount)) {
					System.out.println("Withdrawal result in a negative balance. Please Enter a SMALLER amount of money");
				}else {
					System.out.println("New balance = "+bankAccountDAOImpl.getBankAccount(id).getBalance());
					break;
				}
				
				
			}
			bankATM(id);
			return;
		case 4:
			viewTransactionHistory(id);
			bankATM(id);
			return;
		case 5:
			System.out.println("Money Transfer");
			bankATM(id);
			return;
		case 6:
			viewBankAccount();
			return;
		case 7:
			mainMenu();
			return;
		default:
			System.out.println("Wrong Entry");
		}
	}
	
	private static void viewTransactionHistory(int id) {
		System.out.println("Transaction History");
		System.out.println(TableBuilderUtility.transactionTableBuilder(transactionDAOImpl.getTransaction(id)));
		bankATM(id);
		
	}

	private static void approveAccount() {
		System.out.println("Table of pending Customer Bank Account:");
		System.out.println(TableBuilderUtility.bankAccountTableBuilder(bankAccountDAOImpl.getPendingBankAccounts()));
		int id;
		while(true) {
			try {
				System.out.println("Enter the ID to select an account:");
				id = sc.nextInt();
				sc.nextLine();
				List<BankAccount> bankAccount = new ArrayList<BankAccount>();
				bankAccount.add(bankAccountDAOImpl.getBankAccount(id));
				System.out.println(TableBuilderUtility.bankAccountTableBuilder(bankAccount));
				break;
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid ID, try again!");
			}
		}
		System.out.println("1. Approve this bank account");
		System.out.println("2. Reject this bank account");
		System.out.println("3. Select other account");
		System.out.println("4. Return to main menu");
		System.out.print("Enter Your Choice : ");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			bankAccountDAOImpl.approveBankAccount(id, choice);
			approveAccount();
			return;
		case 2:
			bankAccountDAOImpl.approveBankAccount(id, choice);
			approveAccount();
			return;
		case 3:
			
			approveAccount();
			return;
		case 4:
		
			return;
		default:
			System.out.println("Wrong Entry");
		}
		
	}

	private static void applyNewBankAccount() {
		System.out.println("Welcome to Apply New Bank Account form");
		String accountType;
		while (true) {
			System.out.println("Enter accountType: (enter 'saving' or 'checking')");
			accountType = sc.nextLine().toLowerCase();
			if(accountType.equalsIgnoreCase("saving") || accountType.equalsIgnoreCase("checking")) {
				break;
			}else {
				System.out.println("please enter 'saving' or 'checking'");
			}
		}
		String secondaryCustomerUsername;
		while (true) {
			System.out.println("Enter Secondary Customer Username: (enter 'no' if there is no joint applicant)");
			secondaryCustomerUsername = sc.nextLine();
			if(secondaryCustomerUsername.equalsIgnoreCase("no")) {
				secondaryCustomerUsername=null;
				break;
			}else if(userDAOImpl.getUser(secondaryCustomerUsername)!=null) {
				break;
			}else {
				System.out.println("Username doesn't exists. Please choose a different username!");
			}
		}
		
		BankAccount bankAccount = new BankAccount(accountType,loginUsername.getUsername(),secondaryCustomerUsername);
		bankAccountDAOImpl.registerBankAccount(bankAccount);
		System.out.println("Apply New Bank Account successful. Please wait for approval from our employees");

		
		
	}

	public static void Login() {
		while (true) {
			System.out.println("Welcome to Login form");
			System.out.println("Enter Username:");
			String username = sc.nextLine();
			System.out.println("Enter Password:");
			String password = sc.nextLine();
			User user = userDAOImpl.login(username, password);
			if (user != null) {
				System.out.println("Hello " + username);
				loginUsername = user;
				break;
			} else {
				System.out.println("Wrong username or password!");
				int choice;

				System.out.println("1. Try again");
				System.out.println("2. Return to main menu");
				System.out.println("3. Quit");
				System.out.print("Enter Your Choice : ");
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:

					break;
				case 2:
					return;

				case 3:
					System.exit(0);
				default:
					System.out.println("Wrong Entry");
				}

			}
		}
	}

	public static void Register() {
		System.out.println("Welcome to Register form");
		String username;
		while (true) {
			System.out.println("Enter Username:");
			username = sc.nextLine();
			if(userDAOImpl.getUser(username)==null) {
				break;
			}else {
				System.out.println("Username already exists. Please choose a different username!");
			}
		}
		
		System.out.println("Enter Password:");
		String password = sc.nextLine();
		System.out.println("Enter Last Name:");
		String lastName = sc.nextLine();
		System.out.println("Enter First Name:");
		String firstName = sc.nextLine();
		User user = new User(username, password, lastName, firstName, "customer");
		userDAOImpl.register(user);
		System.out.println("Register successful");

		
	}

	public static void main(String[] args) {
		mainMenu();

	}

}
