package it.unipd.dei.webapp.resource;

import it.unipd.dei.webapp.validation.Validatable;
import it.unipd.dei.webapp.validation.ValidationHashMap;
import it.unipd.dei.webapp.validation.Validator;

public class Location implements Validatable {

    public static final String TABLE_NAME = "Location";
    public static final String COUNTRY_NAME = "Country";
    public static final String CITY_NAME = "City";
    public static final String POSTAL_CODE_NAME = "PostalCode";
    public static final String ADDRESS_NAME = "Address";

    private final String country;
    private final String city;
    private final String postalCode;
    private final String address;

    public Location(final String country, final String city, final String postalCode, final String address) {
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getAddress() {
        return address;
    }
    public ValidationHashMap validateFields() {
        ValidationHashMap output = new ValidationHashMap();

        // Country validation
        String result = Validator.validateString(country,30);
        output.put(COUNTRY_NAME, result);

        // City validation
        result = Validator.validateString(city,30);
        output.put(CITY_NAME, result);

        // Postal code validation
        result = Validator.validateString(postalCode,30);
        output.put(POSTAL_CODE_NAME, result);

        // Address validation
        result = Validator.validateString(address,254);
        output.put(ADDRESS_NAME, result);

        return output;
    }
}