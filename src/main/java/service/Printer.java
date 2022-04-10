package service;

import model.Account;
import model.AccountOperation;
import model.CustomerAction;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;

public class Printer {

    public String printMenu() {
        return Arrays.stream(CustomerAction.values())
                .toList()
                .stream()
                .map(this::printAction)
                .reduce("", (action1, action2) -> action1 + action2);

    }

    private String printAction(CustomerAction customerAction) {
        return customerAction.getId() + " : " + customerAction.getLabel();
    }

    public String printAccount(Account customerAccount) {
        return "Account number : " + customerAccount.getAccountNumber() + " | Balance = " + getBalanceAsString(customerAccount.getBalance());
    }

    public String printAccountHistory(Account customerAccount) {
        var balanceByOperation = new BigDecimal(customerAccount.getBalance().intValue());
        return customerAccount.getOperations()
                .stream()
                .sorted(Comparator.comparing(AccountOperation::getCreationDate))
                .map(accountOperation -> printOperation(accountOperation))
                .reduce("", (operation1, operation2) -> operation1 + operation2);
    }

    private String printOperation(AccountOperation accountOperation) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
//        if (accountOperation.getAction() == CustomerAction.DEPOSIT){
//            int nbCaracToPad = CustomerAction.WITHDRAWAL.name().length() - CustomerAction.DEPOSIT.name().length();
//            return StringUtils.rightPad(CustomerAction.DEPOSIT.name(), nbCaracToPad,"-");
//        }
        return "\n Operation : "+accountOperation.getAction().name();
    }

    private String getAmountAsString(AccountOperation accountOperation) {
         var amountAsString = accountOperation.getAction() == CustomerAction.DEPOSIT ? getBalanceAsString(accountOperation.getAmount()) : getBalanceAsString(accountOperation.getAmount().multiply(new BigDecimal(-1)));
        return "\n Amount : "+amountAsString;
    }


}
