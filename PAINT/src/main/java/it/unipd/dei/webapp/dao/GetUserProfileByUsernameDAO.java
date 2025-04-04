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

public class GetUserProfileByUsernameDAO {
    private static final String STATEMENT = String.format(
        "SELECT up.* FROM paint.%s up JOIN paint.%s c ON up.%s = c.%s WHERE c.%s = ?",
        UserProfile.TABLE_NAME,
        Credentials.TABLE_NAME,
        UserProfile.ID_NAME,
        Credentials.USER_ID_NAME,
        Credentials.USERNAME_NAME
    );

    private final Connection con;
    private final String username;

    public GetUserProfileByUsernameDAO(Connection con, String username) {
        this.con = con;
        this.username = username;
    }

    public UserProfile getUserProfile() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setString(1, username);

            rs = stmnt.executeQuery();
            
            UserProfile new_userProfile = null;
            if(rs.next()){
                UUID id = rs.getObject("id", UUID.class);
                byte[] profilePicture = rs.getBytes("ProfilePicture");
                ImageExtensions pictureExtension = ImageExtensions.fromString(rs.getString("PictureExtension"));
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                String brandName = rs.getString("BrandName");
                LocalDate birthDate = rs.getDate("BirthDate").toLocalDate();
                LocalDate registrationDate = rs.getDate("RegistrationDate").toLocalDate();
                String locationCountry = rs.getString("LocationCountry");
                String locationCity = rs.getString("LocationCity");
                String locationPostalCode = rs.getString("LocationPostalCode");
                String locationAddress = rs.getString("LocationAddress");

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
