package service;

import model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasicCustomerAuthenticatorServiceImplTest {

        @Test
        public void test_authentication_success(){

            Customer customer = new Customer();
            customer.setUsername("username2");
            customer.setPassword("password2");

            CustomerAuthenticatorService customerAuthenticatorService = BasicCustomerAuthenticatorService.getInstance();

            Assertions.assertEquals(true,customerAuthenticatorService.authenticate(customer));
        }

    @Test
    public void test_authentication_failed_for_bad_username(){

        Customer customer = new Customer();
        customer.setUsername("xx");
        customer.setPassword("password2");

        CustomerAuthenticatorService customerAuthenticatorService = BasicCustomerAuthenticatorService.getInstance();

        Assertions.assertEquals(false,customerAuthenticatorService.authenticate(customer));
    }

    @Test
    public void test_authentication_failed_for_bad_password(){

        Customer customer = new Customer();
        customer.setUsername("username2");
        customer.setPassword("xxx");

        CustomerAuthenticatorService customerAuthenticatorService = BasicCustomerAuthenticatorService.getInstance();

        Assertions.assertEquals(false,customerAuthenticatorService.authenticate(customer));
    }

    @Test
    public void test_authentication_failed_for_bad_password_and_username(){

        Customer customer = new Customer();
        customer.setUsername("xxx");
        customer.setPassword("xxx");

        CustomerAuthenticatorService customerAuthenticatorService = BasicCustomerAuthenticatorService.getInstance();

        Assertions.assertEquals(false,customerAuthenticatorService.authenticate(customer));
    }

}