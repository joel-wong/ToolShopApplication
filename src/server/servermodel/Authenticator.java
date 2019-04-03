package server.servermodel;

class Authenticator {

    static String authenticate(String username, String password) {

        int NUM_USERS = 5;
        String[] usernameTable = new String[NUM_USERS];
        usernameTable[0] = "admin";
        usernameTable[1] = "joel";
        usernameTable[2] = "wendy";
        usernameTable[3] = "anik";
        usernameTable[4] = "mohammad";

        String[] passwordsTable = new String[NUM_USERS];
        passwordsTable[0] = "admin";
        passwordsTable[1] = "secure";
        passwordsTable[2] = "name";
        passwordsTable[3] = "write";
        passwordsTable[4] = "get";

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
                if(passwordsTable[i].equals(password)) {
                    return "true";
                }
                return "false";
            }
        }
        return "false";

    }
}