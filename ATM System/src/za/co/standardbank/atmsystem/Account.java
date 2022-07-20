package za.co.standardbank.atmsystem;


/**
 * Class represents an Account
 *
 */
public abstract class Account {
	/**
	 * Account number
	 */
    private String accountNumber;
    
    /**
     * Account name
     */
    private String name;
    
    /**
     * Account pin
     */
    private String pin;
    
    /**
     * Account balance
     */
    private double balance;
    
    /**
     * Account type
     */
    private String accountType;

    /**
     * Constructor
     * @param accountNumber The account number
     * @param name The account name
     * @param pin The account pin
     * @param balance The account balance
     * @param accountType The account type
     */
    public Account(String accountNumber, String name, String pin, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.accountType = accountType;
    }
    
    /**
     * Gets the account number
     * @return The account name
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets the account name
     * @return The account name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the account pin 
     * @return The account pin
     */
    public String getPin() {
        return pin;
    }

    /**
     * Sets the account pin
     * @param pin The account pin
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /*
     * Gets the account balance 
     * @return The account balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the account balance
     * @param balance The account balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    /**
     * Gets the account type
     * @return The account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the account type
     * @param accountType The account type
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
}
