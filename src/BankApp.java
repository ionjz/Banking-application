import java.util.Scanner;

public class BankApp {
    public static void loginScreen () {
        System.out.println("[1] Create New Account\n" +
                "\n" +
                "[2] Login to Existing Account\n" +
                "\n" +
                "[3] Exit Application");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            loginScreen();
            int response = scanner.nextInt();
            switch (response) {
                case 1:
                    Bank.createAccount();
                    break;
                case 2:
                    // temporary: make it look clean so that no business logic is clogging up main method
                    System.out.println("Enter account number");
                    int accountID = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter account PIN");
                    String pin = scanner.nextLine();

                    boolean authentication = Bank.authenticate(pin, accountID);

                    if (authentication) {
                        Bank.dashboard(accountID);
                    }
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("invalid input");
                    break;
            }
        }
    }

}
