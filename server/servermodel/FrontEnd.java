import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

package server.servermodel;

/**
 * Implements the front end of the Tool Shop application.
 *
 * @author Wenjia Yang
 * @version 1.0
 * @since February 6, 2019
 */
public class FrontEnd {
    /**
     * Executes the Tool Shop Application.
     *
     * @param args is not used
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ArrayList<Tool> toolList = new ArrayList<Tool>();
        Inventory inventory = new Inventory(toolList);
        ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
        Order order = new Order();
        Date currentDate = new Date("February", 6, 2019);

        ShopApplication shop = new ShopApplication(inventory, supplierList, order, currentDate);

        //loading in the supplier and item information from the text files into the Application
        shop.loadSuppliers("suppliers.txt");
        shop.loadInventory("items.txt");

        //Menu for the shop application
        shop.menu();


    }
}
