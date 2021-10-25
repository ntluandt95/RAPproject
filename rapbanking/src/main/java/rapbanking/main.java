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
import dao.BankService;
import dao.ConnectionUtility;
import dao.UserDAOImpl;
import model.BankAccount;
import model.User;

public class main {
	static Scanner sc = new Scanner(System.in);
	static UserDAOImpl userDAOImpl = new UserDAOImpl();
	static BankAccountDAOImpl bankAccountDAOImpl = new BankAccountDAOImpl();
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
				System.out.println("2. Choose your existing bank account");
				System.out.println("3. Quit");
				System.out.print("Enter Your Choice : ");
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					applyNewBankAccount();
					break;
				case 2:
					System.out.println("Choose your existing bank account");
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
					System.out.println("View a customer's bank accounts");
					break;
				case 3:
					System.exit(0);
				default:
					System.out.println("Wrong Entry");
				}
			}
		}
		
	}

	private static void approveAccount() {
		System.out.println("Table of pending Customer Bank Account:");
		System.out.println(BankService.bankAccountTableBuilder(bankAccountDAOImpl.getPendingBankAccounts()));
		int id;
		while(true) {
			try {
				System.out.println("Enter the ID to select an account:");
				id = sc.nextInt();
				sc.nextLine();
				List<BankAccount> bankAccount = new ArrayList<BankAccount>();
				bankAccount.add(bankAccountDAOImpl.getBankAccount(id));
				System.out.println(BankService.bankAccountTableBuilder(bankAccount));
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
