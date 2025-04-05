package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.ArtisticProfile;
import it.unipd.dei.webapp.resource.ImageExtensions;
import it.unipd.dei.webapp.resource.UserProfile;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

public class SearchArtistsAndGalleriesDAO {
    private static final Logger logger = LogManager.getLogger(SearchArtistsAndGalleriesDAO.class, StringFormatterMessageFactory.INSTANCE);
    
    private static final String STATEMENT = String.format(
        "SELECT u.%s, u.%s, u.%s, u.%s, u.%s, u.%s " +
        "FROM paint.%s u " +
        "JOIN paint.ArtisticProfile ap ON u.%s = ap.%s " +
        "WHERE LOWER(u.%s) LIKE LOWER(?) OR LOWER(u.%s) LIKE LOWER(?) " +
        "OR (u.%s IS NOT NULL AND LOWER(u.%s) LIKE LOWER(?)) " +
        "ORDER BY u.%s, u.%s",
        UserProfile.ID_NAME,
        UserProfile.PROFILE_PICTURE_NAME,
        UserProfile.PICTURE_EXTENSION_NAME,
        UserProfile.NAME_NAME,
        UserProfile.SURNAME_NAME,
        UserProfile.BRAND_NAME_NAME,
        UserProfile.TABLE_NAME,
        UserProfile.ID_NAME,
        ArtisticProfile.USER_ID_NAME,
        UserProfile.NAME_NAME,
        UserProfile.SURNAME_NAME,
        UserProfile.BRAND_NAME_NAME,
        UserProfile.BRAND_NAME_NAME,
        UserProfile.NAME_NAME,
        UserProfile.SURNAME_NAME
    );
    
    private final Connection con;
    private final String query;
    

    public SearchArtistsAndGalleriesDAO(final Connection con, final String query) {
        this.con = con;
        this.query = "%" + query + "%";
    }
    
    public List<UserProfile> searchUsers() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final List<UserProfile> users = new ArrayList<>();
        
        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, query);
            pstmt.setString(2, query);
            pstmt.setString(3, query);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String pictureExtensionStr = rs.getString(UserProfile.PICTURE_EXTENSION_NAME);
                ImageExtensions pictureExtension = null;
                if (pictureExtensionStr != null) {
                    pictureExtension = ImageExtensions.fromString(pictureExtensionStr);
                }
                
                users.add(new UserProfile(
                    rs.getObject(UserProfile.ID_NAME, UUID.class),
                    rs.getBytes(UserProfile.PROFILE_PICTURE_NAME),
                    pictureExtension,
                    rs.getString(UserProfile.NAME_NAME),
                    rs.getString(UserProfile.SURNAME_NAME),
                    rs.getString(UserProfile.BRAND_NAME_NAME)
                ));
            }
            
            logger.info("Found {} users matching query '{}'", users.size(), query);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        
        return users;
    }
}