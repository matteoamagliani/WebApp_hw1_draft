package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.ImageExtensions;
import it.unipd.dei.webapp.resource.UserProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class GetUserProfileByEmailDAO {
    private static final Logger logger = LogManager.getLogger(GetUserProfileByEmailDAO.class, StringFormatterMessageFactory.INSTANCE);

    private static final String STATEMENT = "SELECT up.* FROM paint.UserProfile up JOIN paint.Credentials c ON up.\"id\" = c.UserId WHERE c.Email = ?";

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
                logger.info("User profile found for the user given the email.");
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
