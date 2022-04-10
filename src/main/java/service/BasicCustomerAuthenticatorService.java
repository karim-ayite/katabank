package service;

import model.Customer;

public class BasicCustomerAuthenticatorService implements CustomerAuthenticatorService{

    private static BasicCustomerAuthenticatorService instance;

    public static CustomerAuthenticatorService getInstance() {
        if (instance == null) {
            instance = new BasicCustomerAuthenticatorService();
        }
        return instance;
    }

    @Override
    public boolean authenticate(Customer customerToAuthenticate) {
        CustomerFinderService customerFinderService = CustomerFinderServiceImp.getInstance();
        var optionalCustomer = customerFinderService.findCustomerByUsername(customerToAuthenticate.getUsername());
        return optionalCustomer.isPresent() && optionalCustomer.get().getPassword().equals(customerToAuthenticate.getPassword());
    }
}
