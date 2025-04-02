package it.unipd.dei.webapp.resource;

public class Location {
    private final String country;
    private final String city;
    private final String postalCode;
    private final String address;

    public Location(){
        this.country = null;
        this.city = null;
        this.postalCode = null;
        this.address = null;
    }

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