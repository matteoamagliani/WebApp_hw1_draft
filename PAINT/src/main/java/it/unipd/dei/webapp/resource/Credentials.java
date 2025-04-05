package it.unipd.dei.webapp.resource;

import java.util.UUID;

import it.unipd.dei.webapp.validation.Validatable;
import it.unipd.dei.webapp.validation.ValidationHashMap;
import it.unipd.dei.webapp.validation.Validator;
/**
 * Represents the credentials of a user, including their user ID, email, password, and username.
 * <p>
 * The {@link Credentials} class encapsulates the information necessary for user authentication and account management.
 * It provides methods for retrieving user credentials and validating fields such as email, password, and username.
 * </p>
 *
 */

 public class Credentials implements Validatable {

    public static final String TABLE_NAME = "Credentials";
    public static final String USER_ID_NAME = "UserId";
    public static final String EMAIL_NAME = "Email";
    public static final String PASSWORD_NAME = "\"Password\"";
    public static final String PASSWORD_NAME_CLEAN = "Password";
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

    public ValidationHashMap validateFields() {
        ValidationHashMap output = new ValidationHashMap();

        // Email validation //TODO: check if email is UNIQIUE in the database
        String result = Validator.validateEmail(email);
        output.put(EMAIL_NAME, result);

        // Password validation
        result = Validator.validatePassword(password);
        if (password.length() < 8) {
            result = "Password too short (minimum 8 characters)";
        }
        output.put(PASSWORD_NAME_CLEAN, result);

        // Username validation
        result = Validator.validateString(username, 30);
        output.put(USERNAME_NAME, result);

        return output;
    }
}