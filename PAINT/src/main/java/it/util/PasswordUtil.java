package it.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {

    // Metodo per hashare una password
    public static String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    // Metodo per verificare una password
    public static boolean verifyPassword(String password, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
        return result.verified;
    }
}
