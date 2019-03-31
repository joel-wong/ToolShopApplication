package server.servermodel;

/**
 * Provides data fields and methods to create a Java data-type, representing an orderline
 * in the Tool Shop application.
 *
 * @author Wenjia Yang
 * @version 1.0
 * @since February 6, 2019
 */
public class OrderLine {

    /**
     * Item description for the orderline
     */
    private String itemDescription;

    /**
     * Amount ordered in the orderline
     */
    private int amountOrdered;

    /**
     * Supplier name for the orderline
     */
    private String supplierName;

    /**
     * Tool object that the orderline is associated with
     */
    private Tool tool;

    /**
     * Total price for the orderline
     */
    private double price;

    /**
     * Constructs an OrderLine object for the specified Tool and amount to be ordered.
     *
     * @param tool   is the Tool to be ordered
     * @param amount is the number of the specified Tool to be ordered
     */
    public OrderLine(Tool tool, int amount) {
        this.tool = tool;
        itemDescription = tool.getToolName();
        amountOrdered = amount;
        supplierName = tool.getSupplier().getCompanyName();
        //calculating the total price of the orderline = price of the individual item x amount ordered
        price = tool.getPrice() * amount;
    }

    /**
     * Converts the OrderLine object to a String that contains the OrderLine details in a
     * specific format.
     */
    public String toString() {
        String s =
                "Item Description: \t" + itemDescription +
                        "\nAmount Ordered : \t" + amountOrdered +
                        "\nSupplier Name: \t\t" + supplierName;
        return s;
    }

    /**
     * Gets the orderline item description.
     *
     * @return the item description
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Gets the amount ordered in the OrderLine.
     *
     * @return the amount ordered
     */
    public int getAmountOrdered() {
        return amountOrdered;
    }

    /**
     * Gets the Supplier name in the OrderLine
     *
     * @return the Supplier name
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Gets the total price for the OrderLine
     *
     * @return the total orderline price
     */
    public double getPrice() {
        return price;
    }

}
