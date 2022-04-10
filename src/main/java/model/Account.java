package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String accountNumber;
    private BigDecimal balance;

    public List<AccountOperation> getOperations() {
        return operations;
    }

    private List<AccountOperation> operations;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void addOperation(AccountOperation operation) {
        if (this.operations == null){
            this.operations = new ArrayList<>();
        }
        this.operations.add(operation);
    }
}
