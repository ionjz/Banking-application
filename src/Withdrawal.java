import java.io.Serializable;
import java.time.LocalDateTime;

public class Withdrawal extends Transaction implements Serializable {

    public Withdrawal(long amount, LocalDateTime timeStamp, String type) {
        super(amount, timeStamp, type);
    }

    public boolean validate(long amount, int accountID) {
        if (amount <= 0) {
            return false;
        }

        BankAccount bankAccount = Bank.loadAccount(accountID);
        if (bankAccount != null) {
            return amount <= bankAccount.getBalance();
        }
        return false;
    }
}
