package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.UserProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class GetUserProfileByEmailDAO {
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
                String pictureExtension = rs.getString("PictureExtension");
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
