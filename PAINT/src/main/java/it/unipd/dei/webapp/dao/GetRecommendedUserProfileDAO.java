package it.unipd.dei.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.unipd.dei.webapp.resource.Follows;
import it.unipd.dei.webapp.resource.ImageExtensions;
import it.unipd.dei.webapp.resource.UserProfile;

public class GetRecommendedUserProfileDAO {
    private static final String STATEMENT = String.format(
        "SELECT u.* FROM paint.%s u JOIN (SELECT f.%s AS user_id, COUNT(*) AS follower_count FROM paint.%s f WHERE f.%s >= CURRENT_DATE - INTERVAL '7 days' GROUP BY f.%s ORDER BY follower_count DESC LIMIT 4) AS top_users ON u.%s = top_users.user_id",
        UserProfile.TABLE_NAME,
        Follows.ARTISTIC_USER_ID_FOLLOWED_NAME,
        Follows.TABLE_NAME,
        Follows.FOLLOW_DATE_NAME,
        Follows.ARTISTIC_USER_ID_FOLLOWED_NAME,
        UserProfile.ID_NAME
    );

    private final Connection con;

    public GetRecommendedUserProfileDAO(Connection con) {
        this.con = con;
    }

    public List<UserProfile> getUserProfiles() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        List<UserProfile> userProfiles = new ArrayList<>();

        try {
            stmnt = con.prepareStatement(STATEMENT);

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
            }

            return userProfiles;

        } finally {
            if (rs != null) rs.close();
            if (stmnt != null) stmnt.close();
            con.close();
        }
    }
}
