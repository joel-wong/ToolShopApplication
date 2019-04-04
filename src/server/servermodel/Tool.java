package server.servermodel;

/**
 * Provides data fields and methods to create a Java data-type, representing a Tool item
 * in the Tool Shop application.
 *
 * @author Wenjia Yang
 * @version 1.0
 * @since February 6, 2019
 */
public class Tool {
    /**
     * The ID number associated with the Tool
     */
    private int toolID;
    /**
     * The name of the Tool
     */
    private String toolName;
    /**
     * The quantity of this Tool available in stock
     */
    private int quantityInStock;
    /**
     * The price of the Tool
     */
    private double price;
    /**
     * The Supplier object that supplies this Tool
     */
    private Supplier supplier;
    /**
     * Indicates if there is a pending order for this Tool
     */
    private boolean alreadyPendingOrder;

    /**
     * Constructs a Tool object with the specified values for toolID,
     * toolName, quantity, and price. The values of the data fields are
     * supplied by the given parameters.
     *
     * @param toolID   is the ID number of the Tool object
     * @param toolName is the name of the Tool
     * @param quantity is the quantity of the Tool in stock
     * @param price    is the price of the Tool
     */
    public Tool(int toolID, String toolName, int quantity, double price) {
        this.toolID = toolID;
        this.toolName = toolName;
        this.quantityInStock = quantity;
        this.price = price;
        //There is no order for this Tool since it has just been constructed.
        alreadyPendingOrder = false;
    }

    /**
     * Assigns the Supplier object associated with this Tool.
     * The Supplier supplies this Tool item.
     *
     * @param supplier is the associated Supplier object
     */
    void assignSupplier(Supplier supplier) {
        this.supplier = supplier;

        //adds this Tool to the list of tools sold by the Supplier object
        supplier.addTool(this);
    }

    /**
     * Converts the Tool object to a String that contains the Tool details in a
     * specific format.
     */
    public String toString() {
        return
                "\nTool ID: " + toolID +
                        "\nTool name: " + toolName +
                        "\nQuantity in stock: " + quantityInStock +
                        "\nPrice: " + price +
                        "\nSupplier ID: " + supplier.getSupplierID();
    }

    /**
     * Gets the Tool ID number.
     *
     * @return the Tool ID number
     */
    int getToolID() {
        return toolID;
    }

    /**
     * Gets the Tool name.
     *
     * @return the Tool name
     */
    String getToolName() {
        return toolName;
    }

    /**
     * Gets the Tool quantity.
     *
     * @return the Tool quantity
     */
    int getToolQuantity() {
        return quantityInStock;
    }

    /**
     * Gets the Supplier associated with the Tool.
     *
     * @return the associated Supplier
     */
    Supplier getSupplier() {
        return supplier;
    }

    /**
     * Gets the price of the Tool.
     *
     * @return the Tool price
     */
    double getPrice() {
        return price;
    }

    /**
     * Checks if there is a pending order for the Tool.
     *
     * @return true if there is a pending order, other returns false.
     */
    boolean checkAlreadyPendingOrder() {
        return alreadyPendingOrder;
    }

    /**
     * Changes the order pending status of the Tool.
     *
     * @param b is the new order pending status of the Tool
     */
    void setPendingOrder(boolean b) {
        alreadyPendingOrder = b;
    }

    /**
     * Increases the quantity of the Tool by the specified amount.
     *
     * @param n is the amount to increase the quantity by
     */
    void increaseQuantity(int n) {
        quantityInStock += n;
    }

    /**
     * Decreases the quantity of the Tool by the specified amount.
     * If there is not enough quantity available to decrease it by the specified amount,
     * then the current Tool quantity is not changed.
     *
     * @param n is the amount to decrease the quantity by
     */
    void decreaseQuantity(int n) {
        if (n > quantityInStock)
            return;

        quantityInStock -= n;
    }
}
