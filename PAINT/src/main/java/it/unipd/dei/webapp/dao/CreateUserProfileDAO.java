package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.UserProfile;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public final class CreateUserProfileDAO {
    private static final String STATEMENT = "INSERT INTO paint.UserProfile (id, ProfilePicture, PictureExtensions, \"Name\", Surname, BrandName, BirthDate, RegistrationDate, LocationCountry, LocationCity, LocationPostalCode, LocationAddress) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final Connection con;
    private final UserProfile userProfile;

    public CreateUserProfileDAO(Connection con, UserProfile userProfile) {
        if(userProfile == null) {
            // TODO Logger
            throw new NullPointerException("The user profile cannot be null");
        }
        this.con = con;
        this.userProfile = userProfile;
    }

    public int createUserProfile() throws SQLException {
        PreparedStatement stmnt = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setObject(1, userProfile.getId());
            stmnt.setBytes(2, userProfile.getProfilePicture());
            stmnt.setString(3, userProfile.getPictureExtension());
            stmnt.setString(4, userProfile.getName());
            stmnt.setString(5, userProfile.getSurname());
            stmnt.setString(6, userProfile.getBrandName());
            // Converting LocalDate to java.sql.Date
            LocalDate birthDate = userProfile.getBirthDate();
            stmnt.setDate(7, Date.valueOf(birthDate));
            LocalDate registrationDate = userProfile.getRegistrationDate();
            stmnt.setDate(8, Date.valueOf(registrationDate));
            stmnt.setString(9, userProfile.getLocationCountry());
            stmnt.setString(10, userProfile.getLocationCity());
            stmnt.setString(11, userProfile.getLocationPostalCode());
            stmnt.setString(12, userProfile.getLocationAddress());

            int rs = stmnt.executeUpdate();
            if(rs == 1){
                return 1;
            }else{
                return 0;
            }
        } finally {

            if (stmnt != null) {
                stmnt.close();
            }

            con.close();
        }
    }
}