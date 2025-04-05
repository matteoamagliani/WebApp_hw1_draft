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

public class GetRecommendedUsersDAO {
    private static final Logger logger = LogManager.getLogger(GetRecommendedUsersDAO.class, StringFormatterMessageFactory.INSTANCE);
    private static final String STATEMENT =
        "SELECT u.id, u.ProfilePicture, u.PictureExtension, u.Name, u.Surname " +
        "FROM paint.UserProfile u " +
        "JOIN artbase.Follows f ON u.id = f.ArtisticUserId_Followed " +
        "WHERE f.FollowDate >= CURRENT_DATE - INTERVAL '7 days' " +
        "GROUP BY u.id, u.ProfilePicture, u.PictureExtension, u.Name, u.Surname " +
        "ORDER BY COUNT(*) DESC LIMIT 4";
    
    private final Connection con;

    public GetRecommendedUsersDAO(Connection con) {
        this.con = con;
    }
    
    public List<UserProfile> getRecommendedUsers() throws SQLException {
        List<UserProfile> recommended = new ArrayList<>();
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        try {
            stmnt = con.prepareStatement(STATEMENT);
            rs = stmnt.executeQuery();
            while (rs.next()) {
                recommended.add(new UserProfile(
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
            con.close();
        }
        return recommended;
    }
}
