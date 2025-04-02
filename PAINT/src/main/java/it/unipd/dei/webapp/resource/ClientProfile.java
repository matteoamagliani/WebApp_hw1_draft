package it.unipd.dei.webapp.resource;

import java.util.UUID;

public class ClientProfile {
    private final UUID userId;
    private final String role;

    public ClientProfile(){
        this.userId = null;
        this.role = null;
    }

    public ClientProfile(final UUID userId, final String role) {
        this.userId = userId;
        this.role = role;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }
}