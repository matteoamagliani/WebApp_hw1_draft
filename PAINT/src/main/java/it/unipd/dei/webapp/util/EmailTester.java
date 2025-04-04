package it.unipd.dei.webapp.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EmailTester {

    // Regex per una email semplice
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static boolean isEmail(String email) {
        if (email == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
