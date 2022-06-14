package service.operation;

import model.Account;
import model.BalanceOperationType;

import java.math.BigDecimal;
import java.time.Clock;

public class DepositService extends AbstractBalanceOperationService {

    public DepositService(Clock clock) {
        super(clock);
    }

    @Override
    protected BalanceOperationType getOperationType() {
        return BalanceOperationType.DEPOSIT;
    }

    @Override
    protected void updateAccountBalance(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
    }
}
