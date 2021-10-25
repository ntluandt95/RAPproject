package dao;

import java.util.List;

import io.bretty.console.table.Alignment;
import io.bretty.console.table.ColumnFormatter;
import io.bretty.console.table.Precision;
import io.bretty.console.table.Table;
import model.BankAccount;

public class BankService {
	public static Table bankAccountTableBuilder(List<BankAccount> bankAccounts) {
		int length = bankAccounts.size();
		Number[] accountIds= new Number[length];
		Double[] balances= new Double[length];
		String[] accountTypes = new String[length];
		String[] statuses = new String[length];
		String[] primaryCustomerUsernames = new String[length];
		String[] secondaryCustomerUsernames = new String[length];
		for (int i = 0; i < length; i++) {
			accountIds[i] = bankAccounts.get(i).getBankAccountId();
			balances[i] = bankAccounts.get(i).getBalance();
			accountTypes[i] = bankAccounts.get(i).getAccountType();
			statuses[i] = bankAccounts.get(i).getStatus();
			primaryCustomerUsernames[i] = bankAccounts.get(i).getPrimaryCustomerUsername();
			if(bankAccounts.get(i).getSecondaryCustomerUsername()==null) {
				secondaryCustomerUsernames[i] = "null";
			}else {
				secondaryCustomerUsernames[i] = bankAccounts.get(i).getSecondaryCustomerUsername();
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
}
