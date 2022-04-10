package data;

import model.Account;
import model.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InMemoryBankCustomerRepository implements BankCustomerRepository {

    public static final int NB_CUSTOMERS = 10;

    private static InMemoryBankCustomerRepository instance;

    public static BankCustomerRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryBankCustomerRepository();
        }
        return instance;
    }

    @Override
    public List<Customer> findAll() {


        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i< NB_CUSTOMERS; i++){
            Customer customer1 = new Customer();
            customer1.setUsername("username"+i);
            customer1.setPassword("password"+i);

            Account account = new Account();
            account.setAccountNumber("account"+i);
            account.setBalance(BigDecimal.ZERO);

            customer1.setAccounts(account);
            customers.add(customer1);
        }

        return customers;
    }

}
