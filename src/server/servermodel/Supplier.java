package server.servermodel;

import java.util.ArrayList;

/**
 * Provides data fields and methods to create a Java data-type, representing a Supplier
 * in the Tool Shop application.
 *
 * @author Wenjia Yang
 * @version 1.0
 * @since February 6, 2019
 */
public class Supplier {
    /**
     * ID number of the Supplier
     */
    private int supplierID;
    /**
     * Name of the Supplier company
     */
    private String companyName;
    /**
     * Address of the Supplier
     */
    private String address;
    /**
     * The name of the sales contact for the Supplier
     */
    private String salesContact;
    /**
     * List of Tools supplied by the Supplier
     */
    private ArrayList<Tool> tools;

    /**
     * Constructs a Supplier object with the specified values for the supplier ID, company name,
     * address, and sales contact.
     *
     * @param supplierID   is the ID number for the Supplier
     * @param companyName  is the name of the Supplier company
     * @param address      is the address for the Supplier
     * @param salesContact is the name of the sales contact for the supplier
     */
    public Supplier(int supplierID, String companyName, String address, String salesContact) {
        this.supplierID = supplierID;
        this.companyName = companyName;
        this.address = address;
        this.salesContact = salesContact;
        //creating an empty list of Tools
        tools = new ArrayList<Tool>();
    }

    /**
     * Converts the Supplier object to a String that contains the Supplier details in a
     * specific format.
     */
    public String toString() {
        String s =
                "Supplier ID: " + supplierID +
                        "\nCompany Name: " + companyName +
                        "\nAddress: " + address +
                        "\nSales Contact: " + salesContact +
                        "\nTools Supplied: ";
        //Listing out the tools supplied
        for (Tool t : tools) {
            s += "-" + t.getToolName() + " ";
        }
        return s;

    }

    /**
     * Adds a new tool to the list of Tools that the Supplier supplies.
     *
     * @param tool is the new Tool to be added
     */
    public void addTool(Tool tool) {
        tools.add(tool);
    }

    /**
     * Gets the ID number of the Supplier.
     *
     * @return the Supplier ID number
     */
    public int getSupplierID() {
        return supplierID;
    }

    /**
     * Gets the Supplier company name.
     *
     * @return the Supplier company name
     */
    public String getCompanyName() {
        return companyName;
    }
}
