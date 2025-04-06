package it.unipd.dei.webapp.resource;
/**
 * Enum that represents the different possible statuses of an order.
 * <p>
 * This enum is used to track the various stages of an order's lifecycle, including whether it is pending, awaiting payment,
 * awaiting shipment, shipped, delivered, canceled, declined, disputed, or refunded.
 * </p>
 */

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
