import java.time.LocalDateTime;

public class Withdrawal extends Transaction {

    public Withdrawal(long amount, LocalDateTime timeStamp, String type) {
        super(amount, timeStamp, type);
    }

    @Override
    public boolean validate(long amount) {
        if (amount < 0) {
            return false;
        }
        return true;
    }
}
