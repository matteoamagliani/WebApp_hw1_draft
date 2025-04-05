package it.unipd.dei.webapp.resource;

import java.util.UUID;
import java.time.LocalDate;
/**
 * Represents the details of an order, including information about shipping and delivery locations,
 * shipping price, status, and any additional notes.
 * <p>
 * This class provides a structure for storing order-related details in a database.
 * It includes the order's unique identifier, shipping and delivery locations, and other order-related information.
 * </p>
 */
public class OrderDetails {
    
    public static final String TABLE_NAME = "OrderDetails";
    public static final String ID_NAME = "id";
    public static final String SHIPPING_PRICE_NAME = "shippingPrice";
    public static final String STATUS_NAME = "status";
    public static final String NOTE_NAME = "note";
    public static final String CREATION_DATE_NAME = "creationDate";
    public static final String LOCATION_COUNTRY_SHIPMENT_NAME = "locationCountryShipment";
    public static final String LOCATION_CITY_SHIPMENT_NAME = "locationCityShipment";
    public static final String LOCATION_POSTAL_CODE_SHIPMENT_NAME = "locationPostalCodeShipment";
    public static final String LOCATION_ADDRESS_SHIPMENT_NAME = "locationAddressShipment";
    public static final String LOCATION_COUNTRY_DELIVERY_NAME = "locationCountryDelivery";
    public static final String LOCATION_CITY_DELIVERY_NAME = "locationCityDelivery";
    public static final String LOCATION_POSTAL_CODE_DELIVERY_NAME = "locationPostalCodeDelivery";
    public static final String LOCATION_ADDRESS_DELIVERY_NAME = "locationAddressDelivery";

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