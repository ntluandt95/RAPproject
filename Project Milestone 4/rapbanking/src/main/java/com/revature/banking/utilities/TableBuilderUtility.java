package com.revature.banking.utilities;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.revature.banking.model.BankAccount;
import com.revature.banking.model.Transaction;
import com.revature.banking.model.User;

import io.bretty.console.table.Alignment;
import io.bretty.console.table.ColumnFormatter;
import io.bretty.console.table.Precision;
import io.bretty.console.table.Table;

public class TableBuilderUtility {
	public static Table bankAccountTableBuilder(List<BankAccount> data) {
		int length = data.size();
		Number[] accountIds= new Number[length];
		Double[] balances= new Double[length];
		String[] accountTypes = new String[length];
		String[] statuses = new String[length];
		String[] primaryCustomerUsernames = new String[length];
		String[] secondaryCustomerUsernames = new String[length];
		for (int i = 0; i < length; i++) {
			accountIds[i] = data.get(i).getBankAccountId();
			balances[i] = data.get(i).getBalance();
			accountTypes[i] = data.get(i).getAccountType();
			statuses[i] = data.get(i).getStatus();
			primaryCustomerUsernames[i] = data.get(i).getPrimaryCustomerUsername();
			if(data.get(i).getSecondaryCustomerUsername()==null) {
				secondaryCustomerUsernames[i] = "null";
			}else {
				secondaryCustomerUsernames[i] = data.get(i).getSecondaryCustomerUsername();
			}
			
		}
		
		ColumnFormatter<Number> bankAccountIdFormatter = ColumnFormatter.number(Alignment.CENTER, 3, Precision.ZERO);
		ColumnFormatter<Number> balanceFormatter = ColumnFormatter.number(Alignment.CENTER, 20, Precision.ONE);
		ColumnFormatter<String> textFormatter = ColumnFormatter.text(Alignment.CENTER, 20);

		Table.Builder builder = new Table.Builder("ID", accountIds, bankAccountIdFormatter);
//		builder.addColumn("Balance", balances, balanceFormatter);
		builder.addColumn("Type", accountTypes, textFormatter);
		builder.addColumn("Status", statuses, textFormatter);
		builder.addColumn("Primary Customer", primaryCustomerUsernames, textFormatter);
		builder.addColumn("Secondary Customer", secondaryCustomerUsernames, textFormatter);
		Table table = builder.build();
		return table;
	}
	
	public static Table customerBankAccountTableBuilder(List<BankAccount> data) {
		int length = data.size();
		Number[] accountIds= new Number[length];
		Double[] balances= new Double[length];
		String[] accountTypes = new String[length];
		String[] statuses = new String[length];
		String[] primaryCustomerUsernames = new String[length];
		String[] secondaryCustomerUsernames = new String[length];
		for (int i = 0; i < length; i++) {
			accountIds[i] = data.get(i).getBankAccountId();
			balances[i] = data.get(i).getBalance();
			accountTypes[i] = data.get(i).getAccountType();
			statuses[i] = data.get(i).getStatus();
			primaryCustomerUsernames[i] = data.get(i).getPrimaryCustomerUsername();
			if(data.get(i).getSecondaryCustomerUsername()==null) {
				secondaryCustomerUsernames[i] = "null";
			}else {
				secondaryCustomerUsernames[i] = data.get(i).getSecondaryCustomerUsername();
			}
			
		}
		
		ColumnFormatter<Number> bankAccountIdFormatter = ColumnFormatter.number(Alignment.CENTER, 3, Precision.ZERO);
		ColumnFormatter<Number> balanceFormatter = ColumnFormatter.number(Alignment.CENTER, 20, Precision.ONE);
		ColumnFormatter<String> textFormatter = ColumnFormatter.text(Alignment.CENTER, 20);

		Table.Builder builder = new Table.Builder("ID", accountIds, bankAccountIdFormatter);
		builder.addColumn("Balance", balances, balanceFormatter);
		builder.addColumn("Type", accountTypes, textFormatter);
		builder.addColumn("Status", statuses, textFormatter);
		builder.addColumn("Primary Customer", primaryCustomerUsernames, textFormatter);
		builder.addColumn("Secondary Customer", secondaryCustomerUsernames, textFormatter);
		Table table = builder.build();
		return table;
	}
	
	public static Table transactionTableBuilder(List<Transaction> data) {
		int length = data.size();
		Number[] transactionIds= new Number[length];
		Date[] transactionDays= new Date[length];
		String[] transactionTypes = new String[length];
		Double[] amounts = new Double[length];
		String[] statuses = new String[length];
		Integer[] fromAccounts = new Integer[length];
		Integer[] toAccount = new Integer[length];
		for (int i = 0; i < length; i++) {
			transactionIds[i] = data.get(i).getTransactionId();
			transactionDays[i] = data.get(i).getTransactionDay();
			transactionTypes[i] = data.get(i).getTransactionType();
			amounts[i] = data.get(i).getAmount();
			statuses[i] = data.get(i).getStatus();
			fromAccounts[i] = data.get(i).getFromAccount();
			toAccount[i] = data.get(i).getToAccount();
			
			
		}
		
		ColumnFormatter<Number> numberFormatter = ColumnFormatter.number(Alignment.CENTER, 7, Precision.ZERO);
		ColumnFormatter<Date> dateFormatter = ColumnFormatter.dateTime(Alignment.CENTER, 20, DateFormat.getDateInstance());
		ColumnFormatter<String> textFormatter = ColumnFormatter.text(Alignment.CENTER, 20);
		ColumnFormatter<Number> amountFormatter = ColumnFormatter.number(Alignment.CENTER, 20, Precision.TWO);
		Table.Builder builder = new Table.Builder("ID", transactionIds, numberFormatter);

		builder.addColumn("Day", transactionDays, dateFormatter);
		builder.addColumn("Type", transactionTypes, textFormatter);
		builder.addColumn("Amount", amounts, amountFormatter);
		builder.addColumn("Status", statuses, textFormatter);
		builder.addColumn("From", fromAccounts, numberFormatter);
		builder.addColumn("To", toAccount, numberFormatter);

		Table table = builder.build();
		return table;
	}
	public static Table customerTableBuilder(List<User> data) {
		int length = data.size();
		String[] usernames= new String[length];
		String[] lastnames = new String[length];
		String[] firstnames = new String[length];
		String[] roles = new String[length];
		Date[] dayBegins = new Date[length];
		
		for (int i = 0; i < length; i++) {
			usernames[i] = data.get(i).getUsername();
			lastnames[i] = data.get(i).getLastName();
			firstnames[i] = data.get(i).getFirstName();
			roles[i] = data.get(i).getUserRole();
			dayBegins[i] = data.get(i).getDayBegin();
			
		}
		
		ColumnFormatter<Number> numberFormatter = ColumnFormatter.number(Alignment.CENTER, 3, Precision.ZERO);
		ColumnFormatter<Date> dateFormatter = ColumnFormatter.dateTime(Alignment.CENTER, 20, DateFormat.getDateInstance());
		ColumnFormatter<String> textFormatter = ColumnFormatter.text(Alignment.CENTER, 20);

		Table.Builder builder = new Table.Builder("Username", usernames, textFormatter);

		builder.addColumn("Last Name", lastnames, textFormatter);
		builder.addColumn("First Name", firstnames, textFormatter);
		builder.addColumn("Role", roles, textFormatter);
		builder.addColumn("Day Begin", dayBegins, dateFormatter);

		Table table = builder.build();
		return table;
		
	}
}
