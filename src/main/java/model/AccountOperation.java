package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountOperation {
    private CustomerAction action;
    private BigDecimal amount;
    private LocalDateTime creationDate;
    private BigDecimal currentAccountBalance;

    public BigDecimal getCurrentAccountBalance() {
        return currentAccountBalance;
    }

    public void setCurrentAccountBalance(BigDecimal currentAccountBalance) {
        this.currentAccountBalance = currentAccountBalance;
    }

    public CustomerAction getAction() {
        return action;
    }

    public void setAction(CustomerAction action) {
        this.action = action;
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
}
