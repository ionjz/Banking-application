import java.io.Serializable;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class BankAccount implements Serializable {
    private String firstName;
    private String lastName;
    private String pin;
    private Deposit initalDeposit;
    private long balance;
    private int accountID;
    private ArrayList<Transaction> transactionList;

    public BankAccount (String firstName, String lastName, String pin, Deposit initalDeposit, long balance, ArrayList<Transaction> transactionList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = hashPin(pin);
        this.initalDeposit = initalDeposit;
        this.balance = balance;
        this.accountID = generateID();
        this.transactionList = transactionList;
    }

    public void addTransaction (Transaction transaction) {
        transactionList.add(transaction);
    }

    public ArrayList<Transaction> getTransactions () {
        return this.transactionList;
    }

    public long getBalance() {
        return this.balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getAccountID() {
        return this.accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
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
        return this.initalDeposit;
    }

    public void setInitalDeposit(Deposit initalDeposit) {
        this.initalDeposit = initalDeposit;
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public static int generateID() {
        SecureRandom secureRand = new SecureRandom();
        return 10000000 + secureRand.nextInt(90000000);
    }

    public static String hashPin(String pin) {
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


    public void deposit (int accountID, long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount can not be less than or equal to zero");
        }

        LocalDateTime depositTimeStamp = LocalDateTime.now();
        String type = "Deposit";
        Deposit deposit = new Deposit(amount, depositTimeStamp, type);

        this.setBalance(this.getBalance() + deposit.getAmount());
        this.addTransaction(deposit);
    }

    public void withdrawal (int accountID, long amount) {
        LocalDateTime depositTimeStamp = LocalDateTime.now();
        String type = "Withdrawal";
        Withdrawal withdrawal = new Withdrawal(amount, depositTimeStamp, type);

        if (withdrawal.getAmount() > this.getBalance() || amount <= 0) {
            throw new IllegalArgumentException("withdrawal has to be greater than 0 and less than or equal to you balance");
        }
        this.setBalance(this.getBalance() - withdrawal.getAmount());
        this.addTransaction(withdrawal);
    }
}
