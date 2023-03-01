package com.market.timedeal.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncrypter {

    public static String encrytp(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean isMatch(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
