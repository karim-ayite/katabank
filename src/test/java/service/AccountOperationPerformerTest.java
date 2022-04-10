package service;

import model.Account;
import model.AccountOperation;
import model.CustomerAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AccountOperationPerformerTest {

    @Test
    void test_deposit_success() {
        AccountActionPerformer accountDepositActionPerformer = AccountOperationPerformer.getInstance();
        Account account = new Account();
        account.setAccountNumber("1");
        account.setBalance(BigDecimal.ZERO);

        AccountOperation operation = new AccountOperation();
        operation.setAmount(new BigDecimal(1000));
        operation.setAction(CustomerAction.DEPOSIT);

        accountDepositActionPerformer.perform(account,operation);

        Assertions.assertEquals(new BigDecimal(1000),account.getBalance());
    }

    @Test
    void test_withdrawl_success() {
        AccountActionPerformer accountDepositActionPerformer = AccountOperationPerformer.getInstance();
        Account account = new Account();
        account.setAccountNumber("1");
        account.setBalance(BigDecimal.ZERO);

        AccountOperation operation = new AccountOperation();
        operation.setAmount(new BigDecimal(1000));
        operation.setAction(CustomerAction.WITHDRAWAL);

        accountDepositActionPerformer.perform(account,operation);

        Assertions.assertEquals(new BigDecimal(-1000),account.getBalance());
    }

    @Test
    void test_deposit_and_withdrawl_success() {
        AccountActionPerformer accountDepositActionPerformer = AccountOperationPerformer.getInstance();
        Account account = new Account();
        account.setAccountNumber("1");
        account.setBalance(BigDecimal.ZERO);

        AccountOperation operation = new AccountOperation();
        operation.setAmount(new BigDecimal(1000));
        operation.setAction(CustomerAction.WITHDRAWAL);

        accountDepositActionPerformer.perform(account,operation);

        Assertions.assertEquals(new BigDecimal(-1000),account.getBalance());

        operation = new AccountOperation();
        operation.setAmount(new BigDecimal(2000));
        operation.setAction(CustomerAction.DEPOSIT);

        accountDepositActionPerformer.perform(account,operation);

        Assertions.assertEquals(new BigDecimal(1000),account.getBalance());
    }

    @Test
    void test_operation_are_added_to_account_success() {
        AccountActionPerformer accountDepositActionPerformer = AccountOperationPerformer.getInstance();
        Account account = new Account();
        account.setAccountNumber("1");
        account.setBalance(BigDecimal.ZERO);

        AccountOperation operation = new AccountOperation();
        operation.setAmount(new BigDecimal(1000));
        operation.setAction(CustomerAction.WITHDRAWAL);

        accountDepositActionPerformer.perform(account,operation);


        operation = new AccountOperation();
        operation.setAmount(new BigDecimal(2000));
        operation.setAction(CustomerAction.DEPOSIT);

        accountDepositActionPerformer.perform(account,operation);



        var withdrawalOperation = account.getOperations().stream()
                .filter(accountOperation -> accountOperation.getAction() == CustomerAction.WITHDRAWAL && new BigDecimal(1000).equals(accountOperation.getAmount()))
                .findFirst();



        var depositOperation = account.getOperations().stream()
                .filter(accountOperation -> accountOperation.getAction() == CustomerAction.DEPOSIT && new BigDecimal(2000).equals(accountOperation.getAmount()))
                .findFirst();

        Assertions.assertEquals(2,account.getOperations().size());

        Assertions.assertEquals(true,withdrawalOperation.isPresent());
//        Assertions.assertEquals(true,withdrawalOperation.get().getAmount() != null);
//        Assertions.assertEquals(true,withdrawalOperation.get().getAction() != null);
//        Assertions.assertEquals(true,withdrawalOperation.get(). != null);

        Assertions.assertEquals(true,depositOperation.isPresent());
    }

    @Test
    void test_operations_added_are_valid_success() {
        AccountActionPerformer accountDepositActionPerformer = AccountOperationPerformer.getInstance();
        Account account = new Account();
        account.setAccountNumber("1");
        account.setBalance(BigDecimal.ZERO);

        AccountOperation operation = new AccountOperation();
        operation.setAmount(new BigDecimal(1000));
        operation.setAction(CustomerAction.WITHDRAWAL);

        accountDepositActionPerformer.perform(account,operation);

        var withdrawalOperation = account.getOperations().stream()
                .filter(accountOperation -> accountOperation.getAction() == CustomerAction.WITHDRAWAL && new BigDecimal(1000).equals(accountOperation.getAmount()))
                .findFirst();

        Assertions.assertEquals(true,withdrawalOperation.get().getCreationDate() != null);
    }
}