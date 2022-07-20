package za.co.standardbank.atmsystem;


/**
 * Class represents a Credit account
 *
 */
public class CreditAccount extends Account {

	/**
     * Constructor
     * @param accountNumber The account number
     * @param name The account name
     * @param pin The account pin
     * @param balance The account balance
     * @param accountType The account type
     */
    public CreditAccount(String accountNumber, String name, String pin, double balance) {
        super(accountNumber, name, pin, balance, "Credit");
    }
      
}
