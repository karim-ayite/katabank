package service.printer;

import model.Account;

public interface AccountPrinterService {
    String printAccountAndBalance(Account customerAccount);

    String printHistory(Account customerAccount);
}
