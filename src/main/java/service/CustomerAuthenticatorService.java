package service;

import model.Customer;

public interface CustomerAuthenticatorService {



    boolean authenticate(Customer customer);
}
