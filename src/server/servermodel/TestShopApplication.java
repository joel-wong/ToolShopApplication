package server.servermodel;

class TestShopApplication extends ShopApplication {

    /** A mock instance of a shopApplication */
    private ShopApplication shopApplicationInstance;

    /**
     * Constructor. Removes access/creation of databasse
     */
    TestShopApplication(){}

    /** Mocks the listOrders function
     *
     * @return "test0"
     */
    @Override
    String listOrders(){
        return "test0";
    }

    /** Mocks the listTools function
     *
     * @return "test1"
     */
    @Override
    String listTools(){
        return "test1";
    }

    /** Mocks the listSuppliers function
     *
     * @return "test2"
     */
    @Override
    String listSuppliers(){
        return "test2";
    }

    /** Mocks the searchInventoryByName function
     *
     * @return "test3" iff the input is "testToolName" as a string
     */
    @Override
    String searchInventoryByName(String toolName) {
        if (toolName.equals("testToolName")) {
            return "test3";
        }
        return "error";
    }

    /** Mocks the searchInventoryByID function
     *
     * @return "test4" iff the input is "testToolID" as a String
     */
    @Override
    String searchInventoryByID(int toolID) {
        if (toolID == 40) {
            return "test4";
        }
        return "error";
    }

    /** Mocks the checkToolQuantity function
     *
     * @param toolID is the ID of the specified Tool
     * @return "test5" if the input ID is 50
     */
    @Override
    String checkToolQuantity(int toolID) {
        if (toolID == 50) {
            return "test5";
        }
        return "error";
    }

    /** Mocks the decreaseToolQuantity function
     *
     * @param toolID is the ID of the specified Tool
     * @param amountRemoved is the amount to decrease the quantity by
     * @return "test6" iff the input toolID is 60 and amountRemoved is 120
     */
    @Override
    String decreaseToolQuantity(int toolID, int amountRemoved) {
        if ((toolID == 60) && (amountRemoved == 120)){
            return "test6";
        }
        return "error";
    }

    /** Mocks the increaseToolQuantity function
     *
     * @param toolID is the ID of the specified Tool
     * @param amountAdded is the amount to increase the quantity by
     * @return "test7" iff the input toolID is 70 and amountAdded is 140
     */
    @Override
    String increaseToolQuantity(int toolID, int amountAdded) {
        if ((toolID == 70) && (amountAdded == 140)){
            return "test7";
        }
        return "error";
    }

    /** Mocks the setNewDate function
     *
     * @param month is the new month
     * @param day is the new day
     * @param year is the new year
     * @return "test8" iff the input is "testMonth", 80, and 160
     */
    @Override
    String setNewDate(String month, int day, int year) {
        if (month.equals("testMonth") && (day == 80) && (year == 160)) {
            return "test8";
        }
        return "error";
    }

    /** Mocks the deleteTool function
     *
     * @param toolID is the ID of the specified Tool
     * @return "test9" iff the input toolID is 90
     */
    @Override
    String deleteTool(int toolID) {
        if (toolID == 90) {
            return "test9";
        }
        return "error";
    }

    /** Mocks the addNewTool function
     *
     * @param toolID is the specified Tool ID
     * @param toolName is the specified Tool name
     * @param quantity is the specified quantity
     * @param price is the specified price
     * @param supplierID is the specified SupplierID
     * @return "test10" iff the inputs are "testToolName", 200, 300.00, and 400
     */
    @Override
    String addNewToolToInventory(int toolID, String toolName, int quantity, double price, int supplierID) {
        if ((toolID == 100) && toolName.equals("testToolName") && (quantity == 200) && (299.99 < price) &&(price < 300.01) && (supplierID == 400)) {
            return "test10";
        }
        return "error";
    }

    /** Mocks the addNewSupplierFunction
     *
     * @param supplierID is the specified Supplier ID
     * @param companyName is the Supplier company name
     * @param address is the address of the Supplier
     * @param salesContact is the name of the Supplier sales contact
     * @return "test11" iff the inputs are 110, "testCompanyName", "testAddress", and "testSalesContact"
     */
    @Override
    String addNewSupplier(int supplierID, String companyName, String address, String salesContact) {
        if ((supplierID == 110) && companyName.equals("testCompanyName") && address.equals("testAddress") && salesContact.equals("testSalesContact")) {
            return "test11";
        }
        return "error";
    }

    /** Mocks the checkLogin function
     *
     * @param username is the given username
     * @param password is the given password
     * @return "test12" iff the inputs are "TestUsername" and "testPassword"
     */
    @Override
    String checkLogin(String username, String password) {
        if (username.equals("testUsername") && password.equals("testPassword")) {
            return "test12";
        }
        return "error";
    }
}