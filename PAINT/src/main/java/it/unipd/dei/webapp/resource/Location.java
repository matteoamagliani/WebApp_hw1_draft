package it.unipd.dei.webapp.resource;

public class Location {

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
}