import java.util.UUID;

public class ClientProfile {
    private final UUID userId;
    private final String role;

    public ClientProfile(UUID userId, String role) {
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