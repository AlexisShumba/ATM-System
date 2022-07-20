package za.co.standardbank.atmsystem;
/**
 * Class represents a transaction
 *
 */
public class Transaction {
	/**
	 * Transaction date
	 */
    private String date;
    
    /**
     * Transaction descrioption
     */
    private String description;

    /**
     * Constructor
     * @param date The transaction date
     * @param description The transaction description
     */
    public Transaction(String date, String description) {
        this.date = date;
        this.description = description;
    }

    /**
     * Gets the transaction date
     * @return The transaction date
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the transaction description 
     * @return The transaction descrption
     */
    public String getDescription() {
        return description;
    }

    
}
