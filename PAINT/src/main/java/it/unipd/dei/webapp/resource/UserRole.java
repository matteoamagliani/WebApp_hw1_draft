package it.unipd.dei.webapp.resource;
/**
 * Enum that represents the different roles a user can have in the system.
 * <p>
 * The available roles are:
 * <ul>
 *   <li>{@link #artist} - Represents an artist user.</li>
 *   <li>{@link #artgallery} - Represents an art gallery user.</li>
 *   <li>{@link #genericuser} - Represents a generic user.</li>
 *   <li>{@link #businessuser} - Represents a business user.</li>
 * </ul>
 * </p>
 *
 */

 public enum UserRole {
    artist,
    artgallery,
    genericuser,
    businessuser;

    public static UserRole fromString(String role) {
        if (role == null) return null;
        return valueOf(role);
    }

    @Override
    public String toString() {
        return name();
    }
}
