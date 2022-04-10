package data;

import model.Customer;

import java.util.List;

public interface BankCustomerRepository {

    List<Customer> findAll();

}
