package service;

import model.Customer;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CustomerFinderService {

    Optional<Customer> findCustomerByUsername(String username);

}
