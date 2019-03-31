package server.servermodel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Decodes strings sent from the client, calls the appropriate model functionality, and returns the expected values.
 *
 * @author Wenjia Yang
 * @version 1.0
 * @since February 6, 2019
 */
public class ShopInterface {

    /**
     * Makes function calls to the shopApplicationInstance based on input
     */
    ShopApplication shopApplicationInstance;

    /**
     *
     * @param shopApplicationInstance The shop application
     */
    public ShopInterface(ShopApplication shopApplicationInstance){
        this.shopApplicationInstance = shopApplicationInstance;
    }

    public synchronized String serviceRequest(String input) {
        return "There will be a call to ShopApplication after some decoding of the message here";
    }

}
