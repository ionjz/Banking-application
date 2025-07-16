import java.time.LocalDateTime;

public abstract class Transaction {
    private long amount;
    private LocalDateTime timeStamp;
    private String type;

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

    public Transaction(long amount, LocalDateTime timeStamp, String type) {
        this.amount = amount;
        this.timeStamp = timeStamp;
        this.type = type;
    }

    public abstract boolean validate (long amount);



}
