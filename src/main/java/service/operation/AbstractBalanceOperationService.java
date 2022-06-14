package service.operation;

import model.Account;
import model.AccountOperation;
import model.BalanceOperationType;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public abstract class AbstractBalanceOperationService implements BalanceOperationService {


    protected AbstractBalanceOperationService(Clock clock) {
        this.clock = clock;
    }
    protected  Clock clock;

    @Override
    public void performOperation(Account account, BigDecimal amount) {

        updateAccountBalance(account,amount);

        AccountOperation operation = new AccountOperation();

        operation.setAmount(amount);

        operation.setCreationDate(LocalDateTime.now(clock));

        operation.setCurrentAccountBalance(account.getBalance());

        operation.setOperationType(getOperationType());

        account.addOperation(operation);
    }

    protected abstract BalanceOperationType getOperationType();

    protected abstract void updateAccountBalance(Account account, BigDecimal amount) ;
}
