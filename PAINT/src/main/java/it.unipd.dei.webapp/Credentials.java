import java.util.UUID;

public class Credentials {
    private final UUID userId;
    private final String email;
    private final String password;
    private final String username;

    public Credentials(UUID userId, String email, String password, String username) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}