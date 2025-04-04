package it.unipd.dei.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import it.unipd.dei.webapp.resource.ImageExtensions;
import it.unipd.dei.webapp.resource.UserProfile;

public class GetUserProfileByIDDAO {
    private final static Logger logger = LogManager.getLogger(GetUserProfileByIDDAO.class, StringFormatterMessageFactory.INSTANCE);

    private static final String STATEMENT = String.format(
        "SELECT * FROM paint.%s WHERE %s = ?",
        UserProfile.TABLE_NAME,
        UserProfile.ID_NAME
    );

    private final Connection con;
    private final UUID id;

    public GetUserProfileByIDDAO(Connection con, UUID id) {
        this.con = con;
        this.id = id;
    }

    public UserProfile getUserProfile() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setObject(1, id);

            rs = stmnt.executeQuery();
            
            UserProfile new_userProfile = null;
            if(rs.next()){
                UUID id = rs.getObject( UserProfile.ID_NAME, UUID.class);
                byte[] profilePicture = rs.getBytes(UserProfile.PROFILE_PICTURE_NAME);
                ImageExtensions pictureExtension = ImageExtensions.fromString(rs.getString(UserProfile.PICTURE_EXTENSION_NAME));
                String name = rs.getString(UserProfile.NAME_NAME);
                String surname = rs.getString(UserProfile.SURNAME_NAME);
                String brandName = rs.getString(UserProfile.BRAND_NAME_NAME);
                LocalDate birthDate = rs.getDate(UserProfile.BIRTH_DATE_NAME).toLocalDate();
                LocalDate registrationDate = rs.getDate(UserProfile.REGISTRATION_DATE_NAME).toLocalDate();
                String locationCountry = rs.getString(UserProfile.LOCATION_COUNTRY_NAME);
                String locationCity = rs.getString(UserProfile.LOCATION_CITY_NAME);
                String locationPostalCode = rs.getString(UserProfile.LOCATION_POSTAL_CODE_NAME);
                String locationAddress = rs.getString(UserProfile.LOCATION_ADDRESS_NAME);

                new_userProfile = new UserProfile(id, profilePicture, pictureExtension, name, surname, brandName, birthDate, registrationDate, locationCountry, locationCity, locationPostalCode, locationAddress);
                logger.info("User profile found for the user.");
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
