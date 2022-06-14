package service.printer;

import model.Account;
import model.AccountOperation;
import model.BalanceOperationType;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class StringAccountPrinterService implements AccountPrinterService {

    public static final String ACCOUNT_NUMBER_LABEL = "Account number : ";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String BALANCE_LABEL = "Balance";
    public static final String CREATION_DATE_LABEL = "Creation date";
    public static final String OPERATION_LABEL = "Operation";
    public static final String AMOUNT_LABEL = "Amount";

    @Override
    public String printAccountAndBalance(Account customerAccount) {
        return ACCOUNT_NUMBER_LABEL + customerAccount.getAccountNumber() + " | " + BALANCE_LABEL + " = " + getBalanceAsString(customerAccount.getBalance());
    }

    @Override
    public String printHistory(Account customerAccount) {

        return ACCOUNT_NUMBER_LABEL +customerAccount.getAccountNumber()+"\n"+ printOperationsHistory(customerAccount);
    }

    private String printOperationsHistory(Account customerAccount) {
        return customerAccount.getOperations()
                .stream()
                .sorted(Comparator.comparing(AccountOperation::getCreationDate))
                .map(this::printOperation)
                .reduce("", (operation1, operation2) -> operation1 + operation2);
    }

    private String printOperation(AccountOperation accountOperation) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String actionLabel = getActionAsString(accountOperation);
        String creationDate = getCreationDateAsString(accountOperation, formatter);
        String amount = getAmountAsString(accountOperation);
        String balance = "\n " + BALANCE_LABEL + " : " +getBalanceAsString(accountOperation.getCurrentAccountBalance());
        return  actionLabel  + creationDate  + amount  + balance + "\n ------------------------------------ ";
    }

    private String getBalanceAsString(BigDecimal accountOperation) {
        return accountOperation.toPlainString();
    }

    private String getCreationDateAsString(AccountOperation accountOperation, DateTimeFormatter formatter) {
        return "\n " + CREATION_DATE_LABEL + " : " +accountOperation.getCreationDate().format(formatter);
    }

    private String getActionAsString(AccountOperation accountOperation) {
        return "\n " + OPERATION_LABEL + " : " +accountOperation.getOperationType().name();
    }

    private String getAmountAsString(AccountOperation accountOperation) {
         var amountAsString = accountOperation.getOperationType() == BalanceOperationType.DEPOSIT ? getBalanceAsString(accountOperation.getAmount()) : getBalanceAsString(accountOperation.getAmount().multiply(new BigDecimal(-1)));
        return "\n " + AMOUNT_LABEL + " : " +amountAsString;
    }


}
