package it.unipd.dei.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.unipd.dei.webapp.resource.Credentials;
import it.unipd.dei.webapp.resource.ImageExtensions;
import it.unipd.dei.webapp.resource.UserProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;
/**
 * Retrieves a list of user profiles whose username partially matches the given input.
 * <p>
 * This DAO queries the {@code paint.user_profile} table and joins it with the {@code paint.credentials} table
 * to find the user profiles whose usernames are similar to the input (using the ILIKE operator for case-insensitive matching).
 * </p>
 *
 * <p>The result is a list of {@link UserProfile} objects containing the user's profile details.</p>
 *
 */
public class GetUserProfileLikeIdDAO {
    private static final Logger logger = LogManager.getLogger(GetUserProfileLikeIdDAO.class, StringFormatterMessageFactory.INSTANCE);

    private static final String STATEMENT = String.format(
        "SELECT up.*, c.%s FROM paint.%s up JOIN paint.%s c ON up.%s = c.%s WHERE c.%s ILIKE ? ORDER BY c.%s",
        Credentials.USERNAME_NAME,
        UserProfile.TABLE_NAME,
        Credentials.TABLE_NAME,
        UserProfile.ID_NAME,
        Credentials.USER_ID_NAME,
        Credentials.USERNAME_NAME,
        Credentials.USERNAME_NAME
    );

    private final Connection con;
    private final String username;

    public GetUserProfileLikeIdDAO(Connection con, String username) {
        this.con = con;
        this.username = username;
    }

    public List<UserProfile> getUserProfiles() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        List<UserProfile> userProfiles = new ArrayList<>();

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setString(1, username);

            rs = stmnt.executeQuery();

            while (rs.next()) {
                UUID id = rs.getObject(UserProfile.ID_NAME, UUID.class);
                byte[] profilePicture = rs.getBytes(UserProfile.PROFILE_PICTURE_NAME);
                ImageExtensions pictureExtension = ImageExtensions.fromString(rs.getString(UserProfile.PICTURE_EXTENSION_NAME));
                String name = rs.getString(UserProfile.NAME_NAME_CLEAN);
                String surname = rs.getString(UserProfile.SURNAME_NAME);
                String brandName = rs.getString(UserProfile.BRAND_NAME_NAME);
                LocalDate birthDate = rs.getDate(UserProfile.BIRTH_DATE_NAME).toLocalDate();
                LocalDate registrationDate = rs.getDate(UserProfile.REGISTRATION_DATE_NAME).toLocalDate();
                String locationCountry = rs.getString(UserProfile.LOCATION_COUNTRY_NAME);
                String locationCity = rs.getString(UserProfile.LOCATION_CITY_NAME);
                String locationPostalCode = rs.getString(UserProfile.LOCATION_POSTAL_CODE_NAME);
                String locationAddress = rs.getString(UserProfile.LOCATION_ADDRESS_NAME);

                UserProfile profile = new UserProfile(
                    id,
                    profilePicture,
                    pictureExtension,
                    name,
                    surname,
                    brandName,
                    birthDate,
                    registrationDate,
                    locationCountry,
                    locationCity,
                    locationPostalCode,
                    locationAddress
                );

                userProfiles.add(profile);
                logger.info("User profile found.");
            }

            return userProfiles;

        } finally {
            if (rs != null) rs.close();
            if (stmnt != null) stmnt.close();
            con.close();
        }
    }
}
