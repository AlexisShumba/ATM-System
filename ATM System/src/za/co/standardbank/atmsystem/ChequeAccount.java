package za.co.standardbank.atmsystem;


/**
 * Class represents a Cheque account
 *
 */
public class ChequeAccount extends Account {

	/**
     * Constructor
     * @param accountNumber The account number
     * @param name The account name
     * @param pin The account pin
     * @param balance The account balance
     */
    public ChequeAccount(String accountNumber, String name, String pin, double balance) {
        super(accountNumber, name, pin, balance, "Cheque");
    }
    

    
}
