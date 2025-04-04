package it.unipd.dei.webapp.validation;

public class Validator {
    public static final String IS_VALID = "Valid";

    public static String validateObject(Object toValidate) {
        if(toValidate == null) {
            return "Is null";
        } else {
            return IS_VALID;
        }
    }

    public static String validateString(String toValidate, int maxLength) {
        if(toValidate == null) {
            return "Is null";
        } else {
            if(toValidate.length() > maxLength) {
                return "Exceeds the allowed length (" + maxLength + ")"; 
            } else {
                return IS_VALID;
            }
        }
    }

    public static String validateEmail(String email) {
        if (email == null) {
            return "Is null";
        } else if (!email.matches("^[A-Za-z0-9.%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "Invalid email format";
        } else if (email.length() > 254) {
            return "Exceeds the allowed length (254)";
        } else {
            return IS_VALID;
        }
    }

    public static String validatePassword(String password) {
        if (password == null) {
            return "Is null";
        } else if (password.length() < 8) {
            return "Password too short (minimum 8 characters)";
        } else if (password.length() > 254) {
            return "Exceeds the allowed length (254)";
        } else {
            return IS_VALID;
        }
    }
        // TODO: testare se funziona è un ENUM ROLE. Metodo per validare il ruolo (userRole)
        public static String validateUserRole(String role) {
            if (role == null) {
                return "Is null";
            }
            if (!role.matches("^(artist|artgallery|genericuser|businessuser)$")) {
                return "Invalid role. Allowed values: artist, artgallery, genericuser, businessuser";
            }
            return IS_VALID;
        }
}
