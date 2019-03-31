import java.util.ArrayList;

package server.servermodel;

/**
 * Provides data fields and methods to create a Java data-type, representing an order
 * in the Tool Shop application.
 *
 * @author Wenjia Yang
 * @version 1.0
 * @since February 6, 2019
 */
public class Order {

    /**
     * Randomly generated 5-digit Order ID
     */
    private int orderID;
    /**
     * Date of the Order
     */
    private Date orderDate;
    /**
     * List of OrderLines in the Order
     */
    private ArrayList<OrderLine> orderLine;
    //NOTE: The relationship between OrderLine and Order is composition which is different from
    //the class diagram that Moshirpour provided.

    /**
     * Changes the Order to a new Order for the specified date.
     * The order ID is also generated and an empty list of OrderLines is created.
     *
     * @param month is the month of the Order
     * @param day   is the day of the Order
     * @param year  is the year of the Order
     */
    public void newOrder(String month, int day, int year) {
        orderID = generateOrderID();
        orderDate = new Date(month, day, year);
        orderLine = new ArrayList<OrderLine>();
    }

    /**
     * Generates a random 5 digit integer.
     *
     * @return the random 5 digit integer
     */
    public int generateOrderID() {
        int randomID = (int) (Math.random() * 9999 + 10000);
        return randomID;
    }

    /**
     * Adds a new OrderLine to the list of orderlines in the Order with a specified
     * Tool and quatity for the orderline.
     *
     * @param tool     is the specified Tool to be ordered
     * @param quantity is the specified quantity of Tool to be ordered
     */
    public void addOrderLine(Tool tool, int quantity) {
        orderLine.add(new OrderLine(tool, quantity));
    }

    /**
     * Converts the Order object to a String that contains the Order details in a
     * specific format.
     */
    public String toString() {
        String s =
                "ORDER ID: \t\t" + orderID +
                        "\nDate Ordered: \t\t" + orderDate;
        s += "\n\n";
        //List of orderlines in the order
        for (OrderLine ol : orderLine) {
            s += ol + "\n\n";
        }
        s += "*******************************************************************";
        return s;
    }

}
