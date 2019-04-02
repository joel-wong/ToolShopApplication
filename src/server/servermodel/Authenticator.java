package server.servermodel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Authenticator {

    public static String authenticate(String username, String hashedPassword) {
        byte[] hashedPasswordAsBytes = hashedPassword.getBytes();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");

            int NUM_USERS = 5;
            String[] usernameTable = new String[NUM_USERS];
            usernameTable[0] = "admin";
            usernameTable[1] = "joel";
            usernameTable[2] = "wendy";
            usernameTable[3] = "anik";
            usernameTable[4] = "mohammad";

            byte[][] hashedPasswordsTable = new byte[NUM_USERS][512];
            hashedPasswordsTable[0] = md.digest(new String("admin").getBytes());
            hashedPasswordsTable[1] = md.digest(new String("secure").getBytes());
            hashedPasswordsTable[2] = md.digest(new String("name").getBytes());
            hashedPasswordsTable[3] = md.digest(new String("write").getBytes());
            hashedPasswordsTable[4] = md.digest(new String("get").getBytes());

            /* Current username/password combos:
            username: admin
            password: admin

            username: joel
            password: secure

            username: wendy
            password: name

            username: anik
            password: write

            username: mohammad
            password: get

            */

            for (int i = 0; i < NUM_USERS; i++) {
                if (usernameTable[i].equals(username)) {
                    if(Arrays.equals(hashedPasswordsTable[i], hashedPasswordAsBytes)) {
                        return "true";
                    }
                    return "false";
                }
            }
            return "false";
        }
        catch (NoSuchAlgorithmException e) {
            System.err.println("Cannot use SHA-256, please talk to the developers for a fix");
            System.exit(-1);
        }
        return "ERROR!";
    }
}