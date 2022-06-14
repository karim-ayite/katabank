package service.operation;

import model.Account;
import model.BalanceOperationType;

import java.math.BigDecimal;
import java.time.Clock;

public class WithdrawalService extends AbstractBalanceOperationService {

    public WithdrawalService(Clock clock) {
        super(clock);
    }

    @Override
    protected BalanceOperationType getOperationType() {
        return BalanceOperationType.WITHDRAWAL;
    }

    @Override
    protected void updateAccountBalance(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().subtract(amount));
    }
}
