package service;

import model.Account;
import model.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.operation.AccountOperationService;
import service.operation.DepositService;
import service.operation.WithdrawalService;
import service.printer.StringAccountPrinter;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertTrue;


class BankAppTest {

    private final Clock clock = Clock.fixed(Instant.parse("2022-01-01T00:00:00Z"), ZoneId.of("UTC"));

    private final AccountOperationService withdrawalOperationService = new WithdrawalService(clock);

    private final AccountOperationService depositOperationService = new DepositService(clock);

    @Test
    @DisplayName("US 1 - should make a deposit")
    void shouldMakeADeposit() {

        var customerAccount = new Account();
        customerAccount.setAccountNumber("1");
        customerAccount.setBalance(BigDecimal.ZERO);
      
        depositOperationService.performOperation(customerAccount, new BigDecimal(1000));

        var printer = new StringAccountPrinter();

        Assertions.assertEquals("Account number : 1 | Balance = 1000", printer.printAccountAndBalance(customerAccount));

    }

    @Test
    @DisplayName("US 2 - should make a withdrawal")
    void shouldMakeAWithdraw() {

        var customerAccount = new Account();
        customerAccount.setAccountNumber("7");
        customerAccount.setBalance(BigDecimal.ZERO);

        withdrawalOperationService.performOperation(customerAccount, new BigDecimal(1000));

        var printer = new StringAccountPrinter();

        Assertions.assertEquals("Account number : 7 | Balance = -1000", printer.printAccountAndBalance(customerAccount));

    }

    @Test
    @DisplayName("US 3 - should print a history")
    void shouldPrintAHistory() {
     
        var customerAccount = new Account();
        customerAccount.setAccountNumber("1");
        customerAccount.setBalance(BigDecimal.ZERO);
        

        withdrawalOperationService.performOperation(customerAccount, new BigDecimal(1000));

        withdrawalOperationService.performOperation(customerAccount, new BigDecimal(1000));

        depositOperationService.performOperation(customerAccount, new BigDecimal(4000));

        var printer = new StringAccountPrinter();

        String expectedHistory = """
                Account number : 1

                 Operation : WITHDRAWAL
                 Creation date : 2022-01-01 00:00:00
                 Amount : -1000
                 Balance : -1000
                 ------------------------------------\s
                 Operation : WITHDRAWAL
                 Creation date : 2022-01-01 00:00:00
                 Amount : -1000
                 Balance : -2000
                 ------------------------------------\s
                 Operation : DEPOSIT
                 Creation date : 2022-01-01 00:00:00
                 Amount : 4000
                 Balance : 2000
                 ------------------------------------\s""";
        Assertions.assertEquals(expectedHistory, printer.printHistory(customerAccount));
    }

    @Test
    void shouldAddAndSubtractDepositAndWithdrawAmountFromAccountBalance() {

        Account account = new Account();
        account.setAccountNumber("1");
        account.setBalance(BigDecimal.ZERO);

        withdrawalOperationService.performOperation(account, new BigDecimal(1000));

        Assertions.assertEquals(new BigDecimal(-1000),account.getBalance());

        depositOperationService.performOperation(account, new BigDecimal(2000));

        Assertions.assertEquals(new BigDecimal(1000),account.getBalance());
    }

    @Test
    void shouldAddOperationsToAccount() {

        Account account = new Account();
        account.setAccountNumber("1");
        account.setBalance(BigDecimal.ZERO);

        withdrawalOperationService.performOperation(account, new BigDecimal(1000));

        depositOperationService.performOperation(account, new BigDecimal(2000));

        var withdrawalOperation = account.getOperations().stream()
                .filter(accountOperation -> accountOperation.getOperationType() == OperationType.WITHDRAWAL && new BigDecimal(1000).equals(accountOperation.getAmount()))
                .findFirst();

        var depositOperation = account.getOperations().stream()
                .filter(accountOperation -> accountOperation.getOperationType() == OperationType.DEPOSIT && new BigDecimal(2000).equals(accountOperation.getAmount()))
                .findFirst();

        Assertions.assertEquals(2,account.getOperations().size());

        assertTrue(withdrawalOperation.isPresent());


        assertTrue(depositOperation.isPresent());
    }

    @Test
    void shouldHaveOperationsAddedValid() {

        Account account = new Account();
        account.setAccountNumber("1");
        account.setBalance(BigDecimal.ZERO);

        withdrawalOperationService.performOperation(account, new BigDecimal(1000));

        var withdrawalOperation = account.getOperations().stream()
                .filter(accountOperation -> accountOperation.getOperationType() == OperationType.WITHDRAWAL && new BigDecimal(1000).equals(accountOperation.getAmount()))
                .findFirst();

        assertTrue(withdrawalOperation.isPresent());

        withdrawalOperation.ifPresent(accountOperation -> Assertions.assertNotNull(accountOperation.getCreationDate()));
    }

}
