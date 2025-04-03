package it.unipd.dei.webapp.validation;

public class Validator {
    public static String validateObject(Object toValidate) {
        if(toValidate == null) {
            return "Is null";
        } else {
            return "Valid";
        }
    }

    public static String validateString(String toValidate, int maxLength) {
        if(toValidate == null) {
            return "Is null";
        } else {
            if(toValidate.length() > maxLength) {
                return "Exceeds the allowed length (" + maxLength + ")"; 
            } else {
                return "Valid";
            }
        }
    }
}
