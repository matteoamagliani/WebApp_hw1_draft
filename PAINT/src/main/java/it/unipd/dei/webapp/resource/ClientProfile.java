package it.unipd.dei.webapp.resource;

import java.util.UUID;

public class ClientProfile {

    public static final String TABLE_NAME = "ClientProfile";
    public static final String USER_ID_NAME = "userId";
    public static final String ROLE_NAME = "role";

    private final UUID userId;
    private final UserRole role;

    public ClientProfile(final UUID userId, final UserRole role) {
        this.userId = userId;
        this.role = role;
    }

    public UUID getUserId() {
        return userId;
    }

    public UserRole getRole() {
        return role;
    }
}