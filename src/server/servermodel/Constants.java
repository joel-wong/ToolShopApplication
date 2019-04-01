package server.servermodel;

/**
 * Interface for a Tool Shop Application.
 * 
 * @author Wenjia Yang
 * @version 1.0
 * @since February 6, 2019
 *
 */

public interface Constants {
	/**
	 * The threshold for the number of items in the inventory before
	 * an order is automatically generated for the item
	 */
	static final int itemQuantityMinimum = 40;
	
	/**
	 * The default quantity ordered for each item is this value subtract
	 * the number of existing items
	 */
	static final int itemQuantityMaximum = 50;
	
	/**
	 * The name of the text file that the order is written to at the end of each day
	 */
	static final String ordersFile = "orders.txt";

	/**
	 * Used for formatting - outputs a line of stars
	 */
	static final String lineOfStars = "********************************************************************************************************";

	/**
	 * Used for formatting - outputs a line of equals signs
	 */
	static final String lineOfEquals = "========================================================================================================";

	/**
	 * Used for formatting - outputs a line of equals signs with the word MENU in the middle
	 */
	static final String lineOfEqualsWithMenuInCenter = "==================================================MENU==================================================";

}