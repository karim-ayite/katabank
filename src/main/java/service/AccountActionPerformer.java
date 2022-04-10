package service;

import model.Account;
import model.AccountOperation;

public interface AccountActionPerformer {
    void perform(Account accounts, AccountOperation amount) ;
}
