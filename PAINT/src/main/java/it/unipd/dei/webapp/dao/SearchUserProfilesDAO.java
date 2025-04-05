package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.ImageExtensions;
import it.unipd.dei.webapp.resource.UserProfile;
import it.unipd.dei.webapp.resource.Credentials;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

public class SearchUserProfilesDAO {
    private static final Logger logger = LogManager.getLogger(SearchUserProfilesDAO.class, StringFormatterMessageFactory.INSTANCE);
    private static final String STATEMENT = String.format(
        "SELECT u.%s, u.%s, u.%s, u.%s, u.%s, u.%s, c.%s " +
        "FROM paint.%s u " +
        "JOIN paint.Credentials c ON u.%s = c.UserId " +
        "WHERE c.Username ILIKE ? " +
        "ORDER BY c.Username",
        UserProfile.ID_NAME, 
        UserProfile.PROFILE_PICTURE_NAME, 
        UserProfile.PICTURE_EXTENSION_NAME,
        UserProfile.NAME_NAME,
        UserProfile.SURNAME_NAME,
        UserProfile.BRAND_NAME_NAME,
        Credentials.USERNAME_NAME,
        UserProfile.TABLE_NAME,
        UserProfile.ID_NAME
    );
    
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
                    rs.getObject(UserProfile.ID_NAME, UUID.class),
                    rs.getBytes(UserProfile.PROFILE_PICTURE_NAME),
                    rs.getObject(UserProfile.PICTURE_EXTENSION_NAME, ImageExtensions.class),
                    rs.getString(UserProfile.NAME_NAME),
                    rs.getString(UserProfile.SURNAME_NAME),
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
