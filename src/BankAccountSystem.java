import java.util.Scanner;

public class BankAccountSystem {
    private Account account;
    private Transaction transaction;

    public static void main(String[] args) {
        BankAccountSystem bankAccountSystem = new BankAccountSystem();
        bankAccountSystem.menu();
    }

    void menu() {
        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n--- BANK ACCOUNT SYSTEM ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show Balance");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    if (account != null) {
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = input.nextDouble();
                        transaction.deposit(depositAmount);
                    } else {
                        System.out.println("No account found. Create an account first.");
                    }
                    break;
                case 3:
                    if (account != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = input.nextDouble();
                        transaction.withdraw(withdrawAmount);
                    } else {
                        System.out.println("No account found. Create an account first.");
                    }
                    break;
                case 4:
                    if (account != null) {
                        System.out.println("Account Holder: " + account.getAccountName());
                        System.out.println("Account Number: " + account.getAccountNumber());
                        System.out.println("Balance: " + account.getBalance());
                    } else {
                        System.out.println("No account found.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice! Try again.");
            }
        }
    }

    void createAccount() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Your Name: ");
        String name = input.nextLine();
        System.out.print("Account Number: ");
        int accountNo = input.nextInt();
        System.out.print("Initial Deposit Amount: ");
        double amount = input.nextDouble();

        account = new Account(accountNo, name, amount);
        transaction = new Transaction(account);
        System.out.println("Account created successfully!");
    }
}


class Account {
    private int accountNumber;
    private String accountName;
    private double balance;

    Account(int accountNumber, String accountName, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
    }

    int getAccountNumber() {
        return accountNumber;
    }

    String getAccountName() {
        return accountName;
    }

    double getBalance() {
        return balance;
    }

    void setBalance(double balance) {
        this.balance = balance;
    }
}


class Transaction {
    private Account account;

    Transaction(Account account) {
        this.account = account;
    }

    void deposit(double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposited " + amount + " successfully!");
    }

    void withdraw(double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrawn " + amount + " successfully!");
        } else {
            System.out.println("Insufficient Balance! Available: " + account.getBalance());
        }
    }
}
