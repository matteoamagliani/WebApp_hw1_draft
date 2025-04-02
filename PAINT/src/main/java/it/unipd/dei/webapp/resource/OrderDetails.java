package it.unipd.dei.webapp.resource;

import java.util.UUID;
import java.time.LocalDate;

public class OrderDetails {
    private final UUID id;
    private final double shippingPrice;
    private final String status;
    private final String note;
    private final LocalDate creationDate;
    private final String locationCountryShipment;
    private final String locationCityShipment;
    private final String locationPostalCodeShipment;
    private final String locationAddressShipment;
    private final String locationCountryDelivery;
    private final String locationCityDelivery;
    private final String locationPostalCodeDelivery;
    private final String locationAddressDelivery;

    public OrderDetails(){
        this.id = null;
        this.shippingPrice = 0;
        this.status = null;
        this.note = null;
        this.creationDate = null;
        this.locationCountryShipment = null;
        this.locationCityShipment = null;
        this.locationPostalCodeShipment = null;
        this.locationAddressShipment = null;
        this.locationCountryDelivery = null;
        this.locationCityDelivery = null;
        this.locationPostalCodeDelivery = null;
        this.locationAddressDelivery = null;
    }

    public OrderDetails(final UUID id, final double shippingPrice, final String status, final String note, final LocalDate creationDate,
                        final String locationCountryShipment, final String locationCityShipment, final String locationPostalCodeShipment,
                        final String locationAddressShipment, final String locationCountryDelivery, final String locationCityDelivery,
                        final String locationPostalCodeDelivery, final String locationAddressDelivery) {
        this.id = id;
        this.shippingPrice = shippingPrice;
        this.status = status;
        this.note = note;
        this.creationDate = creationDate;
        this.locationCountryShipment = locationCountryShipment;
        this.locationCityShipment = locationCityShipment;
        this.locationPostalCodeShipment = locationPostalCodeShipment;
        this.locationAddressShipment = locationAddressShipment;
        this.locationCountryDelivery = locationCountryDelivery;
        this.locationCityDelivery = locationCityDelivery;
        this.locationPostalCodeDelivery = locationPostalCodeDelivery;
        this.locationAddressDelivery = locationAddressDelivery;
    }

    public UUID getId() {
        return id;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public String getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getLocationCountryShipment() {
        return locationCountryShipment;
    }

    public String getLocationCityShipment() {
        return locationCityShipment;
    }

    public String getLocationPostalCodeShipment() {
        return locationPostalCodeShipment;
    }

    public String getLocationAddressShipment() {
        return locationAddressShipment;
    }

    public String getLocationCountryDelivery() {
        return locationCountryDelivery;
    }

    public String getLocationCityDelivery() {
        return locationCityDelivery;
    }

    public String getLocationPostalCodeDelivery() {
        return locationPostalCodeDelivery;
    }

    public String getLocationAddressDelivery() {
        return locationAddressDelivery;
    }
}