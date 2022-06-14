package service.operation;

import model.Account;

import java.math.BigDecimal;

public interface BalanceOperationService {

     void performOperation(Account account, BigDecimal amount);
}
