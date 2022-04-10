package model;

public enum CustomerAction {

    DEPOSIT(1,"Deposit in my account"),
    WITHDRAWAL(2,"Withdrawal from my account"),
    SEE_HISTORY(3,"See the history");

    private int id;
    private String label;

    CustomerAction(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public static CustomerAction valueOfId(int actionNum) {
        if (actionNum == DEPOSIT.getId()){
            return DEPOSIT;
        } else if (actionNum == WITHDRAWAL.getId()){
            return WITHDRAWAL;
        }
        return SEE_HISTORY;
    }

    public  int getId() {
        return this.id;
    }

    public  String getLabel() {
        return this.label;
    }
}
