package service;

import model.AccountOperation;
import model.Customer;
import model.CustomerAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;


@ExtendWith(MockitoExtension.class)
public class BankAppTest {

    @Test
    public void test_US1() {
        Customer aCustomer = new Customer();
        aCustomer.setUsername("username6");
        aCustomer.setPassword("password6");

        CustomerAuthenticatorService customerAuthenticatorService = BasicCustomerAuthenticatorService.getInstance();

        var isCustomerAuthenticate = customerAuthenticatorService.authenticate(aCustomer);

        Assertions.assertEquals(true, isCustomerAuthenticate);

        CustomerFinderService customerFinderService = CustomerFinderServiceImp.getInstance();
        var optionalCustomer = customerFinderService.findCustomerByUsername(aCustomer.getUsername());

        if (optionalCustomer.isPresent()) {
            aCustomer = optionalCustomer.get();

            AccountOperation accountOperation = new AccountOperation();
            accountOperation.setAction(CustomerAction.DEPOSIT);
            accountOperation.setAmount(new BigDecimal(1000));

            AccountOperationPerformer.getInstance().perform(aCustomer.getAccount(), accountOperation);

            var printer = new Printer();

            Assertions.assertEquals("Account number : account6 | Balance = 1000", printer.printAccount(aCustomer.getAccount()));
        }

    }

    @Test
    public void test_US2() {

        Customer aCustomer = new Customer();
        aCustomer.setUsername("username7");
        aCustomer.setPassword("password7");

        var isCustomerAuthenticate = BasicCustomerAuthenticatorService.getInstance().authenticate(aCustomer);

        Assertions.assertEquals(true, isCustomerAuthenticate);

        var optionalCustomer = ((CustomerFinderService) CustomerFinderServiceImp.getInstance()).findCustomerByUsername(aCustomer.getUsername());

        if (optionalCustomer.isPresent()) {
            aCustomer = optionalCustomer.get();

            AccountOperation accountOperation = new AccountOperation();
            accountOperation.setAction(CustomerAction.WITHDRAWAL);
            accountOperation.setAmount(new BigDecimal(1000));

            AccountOperationPerformer.getInstance().perform(aCustomer.getAccount(), accountOperation);

            var printer = new Printer();

            Assertions.assertEquals("Account number : account7 | Balance = -1000", printer.printAccount(aCustomer.getAccount()));
        }
    }

    @Test
    public void test_US3() {
        Customer aCustomer = new Customer();
        aCustomer.setUsername("username3");
        aCustomer.setPassword("password3");

        CustomerAuthenticatorService customerAuthenticatorService = BasicCustomerAuthenticatorService.getInstance();

        var isCustomerAuthenticate = customerAuthenticatorService.authenticate(aCustomer);

        Assertions.assertEquals(true, isCustomerAuthenticate);

        CustomerFinderService customerFinderService = CustomerFinderServiceImp.getInstance();
        var optionalCustomer = customerFinderService.findCustomerByUsername(aCustomer.getUsername());

        if (optionalCustomer.isPresent()) {
            aCustomer = optionalCustomer.get();

            AccountOperation accountOperation = new AccountOperation();
            accountOperation.setAction(CustomerAction.WITHDRAWAL);
            accountOperation.setAmount(new BigDecimal(1000));
            AccountOperationPerformer.getInstance().perform(aCustomer.getAccount(), accountOperation);

            accountOperation = new AccountOperation();
            accountOperation.setAction(CustomerAction.WITHDRAWAL);
            accountOperation.setAmount(new BigDecimal(1000));
            AccountOperationPerformer.getInstance().perform(aCustomer.getAccount(), accountOperation);

            accountOperation = new AccountOperation();
            accountOperation.setAction(CustomerAction.DEPOSIT);
            accountOperation.setAmount(new BigDecimal(4000));
            AccountOperationPerformer.getInstance().perform(aCustomer.getAccount(), accountOperation);

            var printer = new Printer();

            String expectedHistory = "\n Operation : WITHDRAWAL\n" +
                    " Creation date : 2022-01-01 00:00:00\n" +
                    " Amount : -1000\n" +
                    " Balance : -1000\n" +
                    " ------------------------------------ \n" +
                    " Operation : WITHDRAWAL\n" +
                    " Creation date : 2022-01-01 00:00:00\n" +
                    " Amount : -1000\n" +
                    " Balance : -2000\n" +
                    " ------------------------------------ \n" +
                    " Operation : DEPOSIT\n" +
                    " Creation date : 2022-01-01 00:00:00\n" +
                    " Amount : 4000\n" +
                    " Balance : 2000\n" +
                    " ------------------------------------ ";

            Assertions.assertEquals(expectedHistory, printer.printAccountHistory(aCustomer.getAccount()));
        }
    }

}
