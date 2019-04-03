package server.servermodel;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

// Test Suite for the Method SHufflepuff.sHuffle

public class JUnitTests {

    /** Test whether the Supplier toString() method returns the desired results. Raise an AssertionError if not. */
    @Test
    public void testSupplierToString () {
        Supplier testSupplier = new Supplier(1000, "Test Supplier", "Test Address", "Test Sales Contact");
        Tool testTool1 = new Tool(2000, "Test Tool 1", 10, 12.34);
        testTool1.assignSupplier(testSupplier);
        Tool testTool2 = new Tool(2001, "Test Tool 2", 20, 56.78);
        testTool2.assignSupplier(testSupplier);

        assertTrue(testSupplier.toString().equals("Supplier ID: 1000\n" +
                                                  "Company Name: Test Supplier\n" +
                                                  "Address: Test Address\n" +
                                                  "Sales Contact: Test Sales Contact\n" +
                                                  "Tools Supplied: -Test Tool 1 -Test Tool 2 "));
    }


    /** Test whether the Supplier getSupplierID() method returns the desired results. Raise an AssertionError if not. */
    @Test
    public void testSupplierGetSupplierID(){
        Supplier testSupplier = new Supplier(1000, "Test Supplier", "Test Address", "Test Sales Contact");
        Tool testTool1 = new Tool(2000, "Test Tool 1", 10, 12.34);
        testTool1.assignSupplier(testSupplier);
        Tool testTool2 = new Tool(2001, "Test Tool 2", 20, 56.78);
        testTool2.assignSupplier(testSupplier);

        assertTrue(testSupplier.getSupplierID() == 1000);
    }


    /** Test whether the Supplier getCompanyName() method returns the desired results. Raise an AssertionError if not. */
    @Test
    public void testSupplierGetCompanyName() {
        Supplier testSupplier = new Supplier(1000, "Test Supplier", "Test Address", "Test Sales Contact");
        Tool testTool1 = new Tool(2000, "Test Tool 1", 10, 12.34);
        testTool1.assignSupplier(testSupplier);
        Tool testTool2 = new Tool(2001, "Test Tool 2", 20, 56.78);
        testTool2.assignSupplier(testSupplier);

        assertTrue(testSupplier.getCompanyName().equals("Test Supplier"));
    }


    /** Test whether the OrderLine toString() method returns the desired results. Raise an AssertionError if not. */
    @Test
    public void testOrderLineToString () {
        Supplier testSupplier = new Supplier(1000, "Test Supplier", "Test Address", "Test Sales Contact");
        Tool testTool1 = new Tool(2000, "Test Tool 1", 10, 12.34);
        testTool1.assignSupplier(testSupplier);
        Tool testTool2 = new Tool(2001, "Test Tool 2", 20, 56.78);
        testTool2.assignSupplier(testSupplier);
        OrderLine testOrderLine = new OrderLine(testTool1, 40);

        assertTrue(testOrderLine.toString().equals("Item Description: \tTest Tool 1\n" +
                                                   "Amount Ordered: \t40\n" +
                                                   "Supplier Name: \t\tTest Supplier"));
    }

    /** Test whether the OrderLine getItemDescription() method returns the desired results. Raise an AssertionError if not. */
    @Test
    public void testOrderLineGetItemDescription () {
        Supplier testSupplier = new Supplier(1000, "Test Supplier", "Test Address", "Test Sales Contact");
        Tool testTool1 = new Tool(2000, "Test Tool 1", 10, 12.34);
        testTool1.assignSupplier(testSupplier);
        Tool testTool2 = new Tool(2001, "Test Tool 2", 20, 56.78);
        testTool2.assignSupplier(testSupplier);
        OrderLine testOrderLine = new OrderLine(testTool1, 40);

        assertTrue(testOrderLine.getItemDescription().equals("Test Tool 1"));
    }

    /** Test whether the OrderLine getAmountOrdered method returns the desired results. Raise an AssertionError if not. */
    @Test
    public void testOrderLineGetAmountOrdered() {
        Supplier testSupplier = new Supplier(1000, "Test Supplier", "Test Address", "Test Sales Contact");
        Tool testTool1 = new Tool(2000, "Test Tool 1", 10, 12.34);
        testTool1.assignSupplier(testSupplier);
        Tool testTool2 = new Tool(2001, "Test Tool 2", 20, 56.78);
        testTool2.assignSupplier(testSupplier);
        OrderLine testOrderLine = new OrderLine(testTool1, 40);

        assertTrue(testOrderLine.getAmountOrdered() == 40);
    }

    /** Test whether the OrderLine getSupplierName() method returns the desired results. Raise an AssertionError if not. */
    @Test
    public void testOrderLineGetSupplierName () {
        Supplier testSupplier = new Supplier(1000, "Test Supplier", "Test Address", "Test Sales Contact");
        Tool testTool1 = new Tool(2000, "Test Tool 1", 10, 12.34);
        testTool1.assignSupplier(testSupplier);
        Tool testTool2 = new Tool(2001, "Test Tool 2", 20, 56.78);
        testTool2.assignSupplier(testSupplier);
        OrderLine testOrderLine = new OrderLine(testTool1, 40);

        assertTrue(testOrderLine.getSupplierName().equals("Test Supplier"));
    }

    /** Test whether the OrderLine getPrice() method returns the desired results. Raise an AssertionError if not. */
    @Test
    public void testOrderLineGetPrice () {
        Supplier testSupplier = new Supplier(1000, "Test Supplier", "Test Address", "Test Sales Contact");
        Tool testTool1 = new Tool(2000, "Test Tool 1", 10, 12.34);
        testTool1.assignSupplier(testSupplier);
        Tool testTool2 = new Tool(2001, "Test Tool 2", 20, 56.78);
        testTool2.assignSupplier(testSupplier);
        OrderLine testOrderLine = new OrderLine(testTool1, 40);

        assertTrue(testOrderLine.getPrice() == 493.60);
    }

}