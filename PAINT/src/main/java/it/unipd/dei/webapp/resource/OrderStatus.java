package it.unipd.dei.webapp.resource;

public enum OrderStatus {
    pending,
    awaiting_payment,
    awaiting_shipment,
    shipped,
    delivered,
    canceled,
    declined,
    disputed,
    refunded;

    public static OrderStatus fromString(String status) {
        if (status == null) return null;
        return valueOf(status);
    }

    @Override
    public String toString() {
        return name();
    }
}
