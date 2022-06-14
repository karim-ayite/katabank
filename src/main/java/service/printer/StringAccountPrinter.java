package service.printer;

import model.Account;
import model.AccountOperation;
import model.OperationType;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class StringAccountPrinter implements AccountPrinter {

    public static final String ACCOUNT_NUMBER_LABEL = "Account number : ";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public String printAccountAndBalance(Account customerAccount) {
        return ACCOUNT_NUMBER_LABEL + customerAccount.getAccountNumber() + " | Balance = " + getBalanceAsString(customerAccount.getBalance());
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
        String balance = "\n Balance : "+getBalanceAsString(accountOperation.getCurrentAccountBalance());
        return  actionLabel  + creationDate  + amount  + balance + "\n ------------------------------------ ";
    }

    private String getBalanceAsString(BigDecimal accountOperation) {
        return accountOperation.toPlainString();
    }

    private String getCreationDateAsString(AccountOperation accountOperation, DateTimeFormatter formatter) {
        return "\n Creation date : "+accountOperation.getCreationDate().format(formatter);
    }

    private String getActionAsString(AccountOperation accountOperation) {
        return "\n Operation : "+accountOperation.getOperationType().name();
    }

    private String getAmountAsString(AccountOperation accountOperation) {
         var amountAsString = accountOperation.getOperationType() == OperationType.DEPOSIT ? getBalanceAsString(accountOperation.getAmount()) : getBalanceAsString(accountOperation.getAmount().multiply(new BigDecimal(-1)));
        return "\n Amount : "+amountAsString;
    }


}
