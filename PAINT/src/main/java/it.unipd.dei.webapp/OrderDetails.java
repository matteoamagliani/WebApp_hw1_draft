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

    public OrderDetails(UUID id, double shippingPrice, String status, String note, LocalDate creationDate,
                        String locationCountryShipment, String locationCityShipment, String locationPostalCodeShipment,
                        String locationAddressShipment, String locationCountryDelivery, String locationCityDelivery,
                        String locationPostalCodeDelivery, String locationAddressDelivery) {
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