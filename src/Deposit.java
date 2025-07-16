import java.time.LocalDateTime;

public class Deposit extends Transaction {

    public Deposit(long amount, LocalDateTime timeStamp, String type) {
        super(amount, timeStamp, type);
    }

    @Override
    public boolean validate (long amount) {
        return amount > 0;
    }

}
