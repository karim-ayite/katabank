package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountOperation {

    private BalanceOperationType balanceOperationType;



    private BigDecimal amount;
    private LocalDateTime creationDate;
    private BigDecimal currentAccountBalance;

    public BigDecimal getCurrentAccountBalance() {
        return currentAccountBalance;
    }

    public void setCurrentAccountBalance(BigDecimal currentAccountBalance) {
        this.currentAccountBalance = currentAccountBalance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public BalanceOperationType getOperationType() {
        return balanceOperationType;
    }

    public void setOperationType(BalanceOperationType balanceOperationType) {
        this.balanceOperationType = balanceOperationType;
    }
}
