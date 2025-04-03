package it.unipd.dei.webapp.resource;

import java.util.UUID;

public class Credentials {

    public static final String TABLE_NAME = "Credentials";
    public static final String USER_ID_NAME = "UserId";
    public static final String EMAIL_NAME = "Email";
    public static final String PASSWORD_NAME = "Password";
    public static final String USERNAME_NAME = "Username";

    private final UUID userId;
    private final String email;
    private final String password;
    private final String username;

    public Credentials(final UUID userId, final String email, final String password, final String username) {
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