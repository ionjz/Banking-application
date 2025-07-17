import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Transaction implements Serializable {
    private long amount;
    private LocalDateTime timeStamp;
    private String type;

    public Transaction(long amount, LocalDateTime timeStamp, String type) {
        this.amount = amount;
        this.timeStamp = timeStamp;
        this.type = type;
        timeFormatter(timeStamp);
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static void timeFormatter (LocalDateTime timeStamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        timeStamp.format(formatter);
    }

    @Override
    public String toString() {
        return "Transaction" +
                "amount=" + amount +
                ", timeStamp=" + timeStamp +
                ", type='" + type + '\'' +
                '}';
    }
}
