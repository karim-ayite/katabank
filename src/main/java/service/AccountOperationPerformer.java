package service;

import model.Account;
import model.AccountOperation;
import model.CustomerAction;

public class AccountOperationPerformer implements AccountActionPerformer{

    private static AccountActionPerformer instance;

    public static AccountActionPerformer getInstance() {
        if (instance == null) {
            instance = new AccountOperationPerformer();
        }
        return instance;
    }

    @Override
    public void perform(Account accounts, AccountOperation operation) {
        if (CustomerAction.DEPOSIT == operation.getAction()) {
            accounts.setBalance(accounts.getBalance().add(operation.getAmount()));
        } else {
            accounts.setBalance(accounts.getBalance().subtract(operation.getAmount()));
        }
        operation.setCreationDate(DateService.getNow());
        operation.setCurrentAccountBalance(accounts.getBalance());
        accounts.addOperation(operation);
    }
}
