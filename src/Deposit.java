import java.io.Serializable;
import java.time.LocalDateTime;

public class Deposit extends Transaction implements Serializable {

    public Deposit(long amount, LocalDateTime timeStamp, String type) {
        super(amount, timeStamp, type);
    }

    public boolean validate (long amount) {
        return amount > 0;
    }

}
