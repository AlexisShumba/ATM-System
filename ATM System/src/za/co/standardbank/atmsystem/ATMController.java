package za.co.standardbank.atmsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * ATM Controller class contains all the business logic
 *
 */
public class ATMController {
	/**
	 * Formatter for formatting the date
	 */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

    /**
     * List of all accounts
     */
    private ArrayList<Account> accounts;
    
    /**
     * List of all transactions
     */
    private ArrayList<Transaction> transactions;
    
    /**
     * The current logged in user
     */
    private Account currentUser;

    /**
     * Controller
     */
    public ATMController() {
        accounts = loadAccounts();
        transactions = loadTransactions();
    }

    /**
     * Loads all the accounts from the file
     * @return The list of all accounts loaded from the file
     */
    private ArrayList<Account> loadAccounts() {
        ArrayList<Account> accounts = new ArrayList<Account>();

        try (Scanner in = new Scanner(new File("accounts.txt"))) {
            String[] data;
            while (in.hasNext()) {
                data = in.nextLine().split(";");
                if (data[4].equals("Cheque")) {
                    accounts.add(new ChequeAccount(data[0], data[1], data[2], Double.parseDouble(data[3])));
                } else if (data[4].equals("Credit")) {
                    accounts.add(new CreditAccount(data[0], data[1], data[2], Double.parseDouble(data[3])));
                } else {
                    accounts.add(new SavingsAccount(data[0], data[1], data[2], Double.parseDouble(data[3])));
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        return accounts;
    }
    
    /**
     * Gets the list of transactions
     * @return the list of transactions
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    
    /**
     * Adds a transactions to the text file
     * @param description The description of the transaction
     */
    private void addTransaction(String description) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File("transactions.txt"), true))) {
            pw.println(sdf.format(new Date()) + ";" + description);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    } 

    /**
     * Loads all the transactions from the file
     * @return The list of all transactions from the file
     */
    private ArrayList<Transaction> loadTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        try (Scanner in = new Scanner(new File("transactions.txt"))) {
            String[] data;
            while (in.hasNext()) {
                data = in.nextLine().split(";");
                transactions.add(new Transaction(data[0], data[1]));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        return transactions;
    }
    
    /**
     * Authenticates the pin entered by the user
     * @param pin The user entered pin
     * @return true if the correct pin was entered otherwise false
     */
    public boolean authenticatePin(String pin) {
        for (Account a : accounts) {
            if (a.getPin().equals(pin) && a.getAccountType().equalsIgnoreCase("cheque")) {
                currentUser = a;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Withdraws the amount from the account
     * @param amount The amount to withdraw
     * @return true if the amount was successfully withdrawn otherwise false
     */
    public boolean withdraw(double amount) {
        if (currentUser != null && currentUser.getBalance() >= amount) {
            currentUser.setBalance(currentUser.getBalance() - amount);
            addTransaction(amount + " withdrawn from account " + currentUser.getAccountNumber());
            return true;
        }
        return false;
    }

    /**
     * Deposits the amount into the account
     * @param amount The amount to deposit
     * @param accountType The account type
     * @return true if the amount was deposited successfully
     */
    public boolean deposit(double amount, String accountType) {
        if (currentUser != null && currentUser.getAccountType().equalsIgnoreCase(accountType)) {
            currentUser.setBalance(currentUser.getBalance() + amount);
            addTransaction(amount + " deposited to account " + currentUser.getAccountNumber());
            return true;
        }
        return false;
    }

    /**
     * Gets the current account balance
     * @return The current account balance
     */
    public double getBalance() {
        return currentUser.getBalance();
    }

    /**
     * Changes the account pin
     * @param newPin The new pin
     */
    public void changePin(String newPin) {
        currentUser.setPin(newPin);
    }
    
    /**
     * Transfer the amount to a different account type
     * @param amount The amount to transfer
     * @param accountType The account type
     * @return true if the transfer was succesful otherwise false
     */
    public boolean transfer(double amount, String accountType) {
        if (withdraw(amount)) {
            for (Account a : accounts) {
                if (a.getPin().equals(currentUser.getPin()) && a.getAccountType().equalsIgnoreCase(accountType)) {
                    a.setBalance(a.getBalance() + amount);
                    addTransaction(amount + " transfered to " + accountType + " account " + currentUser.getAccountNumber());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Pays an amount to a beneficiary
     * @param accountNumber The beneficiary account number
     * @param name The beneficiary account name
     * @param amountValue The payment amount
     * @return true if the payment was succesful otherwise false
     */
    public boolean payBeneficiary(String accountNumber, String name, double amountValue) {
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(accountNumber) && a.getAccountType().equals("Cheque") && a.getName().equals(name)) {
                a.setBalance(a.getBalance() + amountValue);
                addTransaction(amountValue + " paid to beneficiary" + name + " with account number " + accountNumber);
                return true;
            }
        }
        return false;
    }
}
