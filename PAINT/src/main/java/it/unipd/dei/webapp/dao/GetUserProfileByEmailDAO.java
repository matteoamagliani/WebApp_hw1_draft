package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Credentials;
import it.unipd.dei.webapp.resource.ImageExtensions;
import it.unipd.dei.webapp.resource.UserProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class GetUserProfileByEmailDAO {
    private static final String STATEMENT = String.format(
        "SELECT up.* FROM paint.%s up JOIN paint.%s c ON up.%s = c.%s WHERE c.%s = ?",
        UserProfile.TABLE_NAME,
        Credentials.TABLE_NAME,
        UserProfile.ID_NAME,
        Credentials.USER_ID_NAME,
        Credentials.EMAIL_NAME
    );

    private final Connection con;
    private final String email;

    public GetUserProfileByEmailDAO(Connection con, String email) {
        this.con = con;
        this.email = email;
    }

    public UserProfile getUserProfile() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setString(1, email);

            rs = stmnt.executeQuery();
            
            UserProfile new_userProfile = null;
            if(rs.next()){
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

                new_userProfile = new UserProfile(id, profilePicture, pictureExtension, name, surname, brandName, birthDate, registrationDate, locationCountry, locationCity, locationPostalCode, locationAddress);
            }
            return new_userProfile;
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmnt != null) {
                stmnt.close();
            }

            con.close();
        }
    }
}
