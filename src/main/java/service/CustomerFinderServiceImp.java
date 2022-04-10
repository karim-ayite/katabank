package service;

import data.BankCustomerRepository;
import data.InMemoryBankCustomerRepository;
import model.Customer;

import java.util.Optional;

public class CustomerFinderServiceImp implements CustomerFinderService{

    private static CustomerFinderServiceImp instance;

    public static CustomerFinderServiceImp getInstance() {
        if (instance == null) {
            instance = new CustomerFinderServiceImp();
        }
        return instance;
    }

    @Override
    public Optional<Customer> findCustomerByUsername(String username) {
        BankCustomerRepository bankCustomerRepository = InMemoryBankCustomerRepository.getInstance();

        return bankCustomerRepository.findAll().stream()
                .filter(customer -> username.equals(customer.getUsername()))
                .findFirst();
    }
}
