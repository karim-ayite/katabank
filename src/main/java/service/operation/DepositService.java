package service.operation;

import model.Account;
import model.OperationType;

import java.math.BigDecimal;
import java.time.Clock;

public class DepositService extends AbstractBalanceOperationService {

    public DepositService(Clock clock) {
        super(clock);
    }

    @Override
    protected OperationType getOperationType() {
        return OperationType.DEPOSIT;
    }

    @Override
    protected void updateAccountBalance(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
    }
}
