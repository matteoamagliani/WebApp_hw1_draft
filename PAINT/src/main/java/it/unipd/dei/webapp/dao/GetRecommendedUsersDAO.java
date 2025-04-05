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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GetRecommendedUsersDAO {

    private static final Logger logger = LogManager.getLogger(GetRecommendedUsersDAO.class, StringFormatterMessageFactory.INSTANCE);

    private static final String STATEMENT = String.format(
            "SELECT u.%s, u.%s, u.%s, u.%s, u.%s, u.%s " +
            "FROM paint.%s u " +
            "JOIN paint.ArtisticProfile ap ON u.%s = ap.UserId " +
            "ORDER BY ap.followerCount DESC " +
            "LIMIT 5",
            UserProfile.ID_NAME, 
            UserProfile.PROFILE_PICTURE_NAME, 
            UserProfile.PICTURE_EXTENSION_NAME,
            UserProfile.NAME_NAME,
            UserProfile.SURNAME_NAME,
            UserProfile.BRAND_NAME_NAME,
            UserProfile.TABLE_NAME,
            UserProfile.ID_NAME
    );

    private final Connection con;

    public GetRecommendedUsersDAO(Connection con) {
        this.con = con;
    }

    public List<UserProfile> getRecommendedUsers() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        List<UserProfile> users = new ArrayList<>();

        try {
            stmnt = con.prepareStatement(STATEMENT);
            rs = stmnt.executeQuery();
            
            while (rs.next()) {
                UUID id = rs.getObject(UserProfile.ID_NAME, UUID.class);
                byte[] profilePicture = rs.getBytes(UserProfile.PROFILE_PICTURE_NAME);
                ImageExtensions pictureExtension = null;
                String extensionStr = rs.getString(UserProfile.PICTURE_EXTENSION_NAME);
                if (extensionStr != null) {
                    pictureExtension = ImageExtensions.fromString(extensionStr);
                }
                String name = rs.getString(UserProfile.NAME_NAME);
                String surname = rs.getString(UserProfile.SURNAME_NAME);
                String brandName = rs.getString(UserProfile.BRAND_NAME_NAME);

                users.add(new UserProfile(id, profilePicture, pictureExtension, name, surname, brandName));
            }
            
            logger.info("Found {} recommended users", users.size());
            return users;
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmnt != null) {
                stmnt.close();
            }
        }
    }
}
