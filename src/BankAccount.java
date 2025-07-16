import java.security.SecureRandom;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;


public class BankAccount {
    private String firstName;
    private String lastName;
    private String pin;
    private Deposit initalDeposit;
    private long balance;
    private int accountID;


    public long getBalance() {
        return this.balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public BankAccount (String firstName, String lastName, String pin, Deposit initalDeposit, long balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = hashPin(pin);
        this.initalDeposit = initalDeposit;
        this.balance = balance;
        this.accountID = generateID();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public Deposit getInitalDeposit() {
        return initalDeposit;
    }

    public void setInitalDeposit(Deposit initalDeposit) {
        this.initalDeposit = initalDeposit;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int generateID() {
        SecureRandom secureRand = new SecureRandom();
        return 10000000 + secureRand.nextInt(90000000);
    }

    public String hashPin(String pin) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pin.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

           return hexString.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not supported", e);
        }
    }

}
