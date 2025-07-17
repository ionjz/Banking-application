import java.io.*;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank implements Serializable {
    private ArrayList<BankAccount> bankAccounts;


    public static BankAccount createAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter your last name");
        String lastName = scanner.nextLine();
        System.out.println("Enter an account PIN");
        String pin = scanner.nextLine();

        System.out.println("Would you like to deposit? Y/N");
        String yesOrNo = scanner.nextLine();

        if (yesOrNo.equals("Y") || yesOrNo.equals("y")) {
            ArrayList<Transaction> transactions = new ArrayList<>();
            System.out.println("Enter amount to deposit");
            long depositAmount = scanner.nextLong();
            scanner.nextLine();
            LocalDateTime timeStamp = LocalDateTime.now();
            Deposit deposit = new Deposit(depositAmount, timeStamp, "Deposit");
            transactions.add(deposit);
            long balance = deposit.getAmount();
            BankAccount bankAccount = new BankAccount(firstName, lastName, pin, deposit, balance, transactions);
            saveAccount(bankAccount);
            System.out.println("Your account number is: " + bankAccount.getAccountID());
            return bankAccount;
        }

        Deposit deposit = null;
        long balance = 0;
        BankAccount bankAccount = new BankAccount(firstName, lastName, pin, deposit, balance, new ArrayList<>());
        saveAccount(bankAccount);
        System.out.println("Your account number is: " + bankAccount.getAccountID());
        return bankAccount;
    }

    public static boolean authenticate (String pin, int accountID) {
        BankAccount bankAccount = loadAccount(accountID);
        if (bankAccount == null) {
            System.out.println("Account not found" + "\n");
            return false;
        }

        String storedHash = bankAccount.getPin();
        String inputHash = BankAccount.hashPin(pin);

        return MessageDigest.isEqual(storedHash.getBytes(), inputHash.getBytes());
    }

    public static void saveAccount(BankAccount bankAccount) {
        try {
            FileOutputStream outputFile = new FileOutputStream("data/" + bankAccount.getAccountID() + ".dat");
            ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);
            outputStream.writeObject(bankAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BankAccount loadAccount(int accountID) {
        try {
            FileInputStream file = new FileInputStream("data/" + accountID + ".dat");
            ObjectInputStream inputStream = new ObjectInputStream(file);
            return (BankAccount) inputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e ) {
            return null;
        }
    }

    public static void dashboard (int accountID) {
        Scanner scanner = new Scanner(System.in);

        BankAccount bankAccount = loadAccount(accountID);

        while (true) {
            dashbaordScreen();
            int userInput = scanner.nextInt();
            switch (userInput) {
                case 1:
                    System.out.println(bankAccount.getBalance());
                    break;
                case 2:
                    System.out.println("Enter an amount to deposit");
                    long depositAmount = scanner.nextLong();
                    System.out.println(depositAmount);
                    bankAccount.deposit(accountID, depositAmount);
                    break;
                case 3:
                    System.out.println("Enter an amount to withdrawal");
                    long withdrawalAmount = scanner.nextLong();
                    bankAccount.withdrawal(accountID, withdrawalAmount);
                    break;
                case 4:
                    System.out.println(bankAccount.getTransactions());
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("invalid input");
                    break;
            }
        }
    }

    public static void dashbaordScreen () {
        System.out.println("[1] View Account Balance\n" +
                "\n" +
                "[2] Deposit Money\n" +
                "\n" +
                "[3] Withdraw Money\n" +
                "\n" +
                "[4] View Transaction History\n" +
                "\n" +
                "[5] Logout");
    }


}
