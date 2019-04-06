package server.servermodel;

import org.junit.*;
import static org.junit.Assert.*;

/** Test Suite for the ShopManager and Date classes
 *
 * @author Joel Wong
 * @version 1.0
 * @since April 5, 2019
 */
public class JUnitTests {

    /** Test whether listOrder function is correctly called */
    @Test
    public void testShopManagerListOrders () {
        TestShopApplication testShopApplication = new TestShopApplication();
        ShopManager shopManager = new ShopManager(testShopApplication);

        assertTrue(shopManager.serviceRequest("0").equals("test0"));
    }

    /** Test whether listTools function is correctly called */
    @Test
    public void testShopManagerListTools () {
        TestShopApplication testShopApplication = new TestShopApplication();
        ShopManager shopManager = new ShopManager(testShopApplication);

        assertTrue(shopManager.serviceRequest("1").equals("test1"));
    }

    /** Test whether listSuppliers function is correctly called */
    @Test
    public void testShopManagerListSuppliers () {
        TestShopApplication testShopApplication = new TestShopApplication();
        ShopManager shopManager = new ShopManager(testShopApplication);

        assertTrue(shopManager.serviceRequest("2").equals("test2"));
    }

    /** Test whether searchToolName function is correctly called */
    @Test
    public void testShopManagerSearchToolName () {
        TestShopApplication testShopApplication = new TestShopApplication();
        ShopManager shopManager = new ShopManager(testShopApplication);

        assertTrue(shopManager.serviceRequest("3\ntestToolName").equals("test3"));
    }

    /** Test whether searchToolID function is correctly called */
    @Test
    public void testShopManagerSearchToolID () {
        TestShopApplication testShopApplication = new TestShopApplication();
        ShopManager shopManager = new ShopManager(testShopApplication);

        assertTrue(shopManager.serviceRequest("4\n40").equals("test4"));
    }

    /** Test whether checkToolQuantity function is correctly called */
    @Test
    public void testShopManagerCheckToolQuantity () {
        TestShopApplication testShopApplication = new TestShopApplication();
        ShopManager shopManager = new ShopManager(testShopApplication);

        assertTrue(shopManager.serviceRequest("5\n50").equals("test5"));
    }

    /** Test whether decreaseToolQuantity function is correctly called */
    @Test
    public void testShopManagerDecreaseToolQuantity () {
        TestShopApplication testShopApplication = new TestShopApplication();
        ShopManager shopManager = new ShopManager(testShopApplication);

        assertTrue(shopManager.serviceRequest("6\n60\n120").equals("test6"));
    }

    /** Test whether increaseToolQuantity function is correctly called */
    @Test
    public void testShopManagerIncreaseToolQuantity () {
        TestShopApplication testShopApplication = new TestShopApplication();
        ShopManager shopManager = new ShopManager(testShopApplication);

        assertTrue(shopManager.serviceRequest("7\n70\n140").equals("test7"));
    }

    /** Test whether setNewDate function is correctly called */
    @Test
    public void testShopManagerSetNewDate () {
        TestShopApplication testShopApplication = new TestShopApplication();
        ShopManager shopManager = new ShopManager(testShopApplication);

        assertTrue(shopManager.serviceRequest("8\ntestMonth\n80\n160").equals("test8"));
    }

    /** Test whether deleteTool function is correctly called */
    @Test
    public void testShopManagerDeleteTool () {
        TestShopApplication testShopApplication = new TestShopApplication();
        ShopManager shopManager = new ShopManager(testShopApplication);

        assertTrue(shopManager.serviceRequest("9\n90").equals("test9"));
    }

    /** Test whether addNewTool function is correctly called */
    @Test
    public void testShopManagerAddNewTool () {
        TestShopApplication testShopApplication = new TestShopApplication();
        ShopManager shopManager = new ShopManager(testShopApplication);

        assertTrue(shopManager.serviceRequest("10\n100\ntestToolName\n200\n300\n400").equals("test10"));
    }

    /** Test whether addNewSupplier function is correctly called */
    @Test
    public void testShopManagerAddNewSupplier () {
        TestShopApplication testShopApplication = new TestShopApplication();
        ShopManager shopManager = new ShopManager(testShopApplication);

        assertTrue(shopManager.serviceRequest("11\n110\ntestCompanyName\ntestAddress\ntestSalesContact").equals("test11"));
    }

    /** Test whether checkLogin function is correctly called */
    @Test
    public void testShopManagerCheckLogin () {
        TestShopApplication testShopApplication = new TestShopApplication();
        ShopManager shopManager = new ShopManager(testShopApplication);

        assertTrue(shopManager.serviceRequest("12\ntestUsername\ntestPassword").equals("test12"));
    }

    /** Test whether Date correctly returns the day */
    @Test
    public void testDateGetDay () {
        Date date = new Date("testMonth", 2, 3);

        assertTrue(date.getDay() == 2);
    }

    /** Test whether Date correctly returns the month */
    @Test
    public void testDateGetMonth () {
        Date date = new Date("testMonth", 2, 3);

        assertTrue(date.getMonth().equals("testMonth"));
    }

    /** Test whether Date correctly returns the year */
    @Test
    public void testDateGetYear () {
        Date date = new Date("testMonth", 2, 3);

        assertTrue(date.getYear() == 3);
    }

    /** Test whether Date correctly sets the day */
    @Test
    public void testDateSetDay () {
        Date date = new Date("testMonth", 2, 3);

        date.setDay(4);
        assertTrue(date.getDay() == 4);
    }

    /** Test whether Date correctly sets the month */
    @Test
    public void testDateSetMonth () {
        Date date = new Date("testMonth", 2, 3);

        date.setMonth("testMonth2");
        assertTrue(date.getMonth().equals("testMonth2"));
    }

    /** Test whether Date correctly sets the year */
    @Test
    public void testDateSetYear () {
        Date date = new Date("testMonth", 2, 3);

        date.setYear(5);
        assertTrue(date.getYear() == 5);
    }
}