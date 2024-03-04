import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String userID;
    private String userPIN;
    private double accountBalance;

    public User(String userID, String userPIN, double accountBalance) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.accountBalance = accountBalance;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPIN() {
        return userPIN;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}

class ATM {
    private Map<String, User> users;
    private User currentUser;

    public ATM() {
        users = new HashMap<>();
        // You can add some sample users for testing
        users.put("123456", new User("123456", "1234", 1000.0));
        users.put("789012", new User("789012", "5678", 500.0));
    }

    public void start() {
        System.out.println("Welcome to the ATM");
        authenticateUser();
        displayMenu();
    }

    private void authenticateUser() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        User user = users.get(userID);
        if (user != null && user.getUserPIN().equals(pin)) {
            currentUser = user;
            System.out.println("Authentication successful. Welcome, " + currentUser.getUserID() + "!");
        } else {
            System.out.println("Authentication failed. Invalid User ID or PIN. Exiting.");
            System.exit(0);
        }
    }

    private void displayMenu() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private void checkBalance() {
        System.out.println("Your account balance is: $" + currentUser.getAccountBalance());
    }

    private void withdrawMoney() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= currentUser.getAccountBalance()) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
            System.out.println("Withdrawal successful. Remaining balance: $" + currentUser.getAccountBalance());
        } else {
            System.out.println("Invalid amount or insufficient funds. Please try again.");
        }
    }

    private void depositMoney() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() + amount);
            System.out.println("Deposit successful. New balance: $" + currentUser.getAccountBalance());
        } else {
            System.out.println("Invalid amount. Please try again.");
        }
    }
}
/*
public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
*/