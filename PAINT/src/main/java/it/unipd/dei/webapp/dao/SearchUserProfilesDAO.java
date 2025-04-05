package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.ImageExtensions;
import it.unipd.dei.webapp.resource.UserProfile;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

public class SearchUserProfilesDAO {
    private static final Logger logger = LogManager.getLogger(SearchUserProfilesDAO.class, StringFormatterMessageFactory.INSTANCE);
    private static final String STATEMENT =
        "SELECT u.id, u.ProfilePicture, u.PictureExtension, u.Name, u.Surname, c.Username " +
        "FROM paint.UserProfile u " +
        "JOIN paint.Credentials c ON u.id = c.UserId " +
        "WHERE c.Username ILIKE ? " +
        "ORDER BY c.Username";
    
    private final Connection con;
    private final String query;

    public SearchUserProfilesDAO(Connection con, String query) {
        this.con = con;
        this.query = query;
    }
    
    public List<UserProfile> searchUserProfiles() throws SQLException {
        List<UserProfile> users = new ArrayList<>();
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setString(1, "%" + query + "%");
            rs = stmnt.executeQuery();
            while (rs.next()) {
                users.add(new UserProfile(
                    rs.getObject("id", UUID.class),
                    rs.getBytes("ProfilePicture"),
                    rs.getObject(UserProfile.PICTURE_EXTENSION_NAME, ImageExtensions.class),
                    rs.getString("Name"),
                    rs.getString("Surname"),
                    null, null, null, null, null, null, null
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmnt != null) stmnt.close();
        }
        return users;
    }
}
