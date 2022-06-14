package service.printer;

import model.Account;

public interface AccountPrinter {
    String printAccountAndBalance(Account customerAccount);

    String printHistory(Account customerAccount);
}
