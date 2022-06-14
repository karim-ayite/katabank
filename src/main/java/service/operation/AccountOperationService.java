package service.operation;

import model.Account;

import java.math.BigDecimal;

public interface AccountOperationService {

     void performOperation(Account account, BigDecimal amount);
}
