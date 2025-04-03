package it.unipd.dei.webapp.resource;

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
