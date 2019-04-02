package client.clientcontroller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashHelper {
    public static String hashPassword(String password) {

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            String hashedPassword = new String(bytes);
            return hashedPassword;
        }
        catch (NoSuchAlgorithmException e) {
            System.err.println("Cannot use SHA-256, please talk to the developers for a fix");
            System.exit(-1);
        }
        return "ERROR!";
    }
}