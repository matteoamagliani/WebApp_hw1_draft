package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Content;
import it.unipd.dei.webapp.resource.UserProfile;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

public class GetUserContentDAO {
    private static final Logger logger = LogManager.getLogger(GetUserContentDAO.class, StringFormatterMessageFactory.INSTANCE);
    private static final String STATEMENT =
        "SELECT 'ArtPiece' AS Type, ap.id, ap.Title, ap.Description, ap.UploadDate, img.ImageData, img.Extension " +
        "FROM paint.ArtPiece ap LEFT JOIN paint.ArtPieceImage img ON ap.id = img.ArtPieceId AND img.ImageIndex = 1 " +
        "WHERE ap.ArtisticUserId = ? " +
        "UNION " +
        "SELECT 'Event' AS Type, e.id, e.Title, e.Description, e.UploadDate, img.ImageData, img.Extension " +
        "FROM paint.Event e LEFT JOIN paint.EventImage img ON e.id = img.EventId AND img.ImageIndex = 1 " +
        "WHERE e.ArtisticUserId_Organizer = ?";

    private final Connection con;
    private final UUID userId;

    public GetUserContentDAO(Connection con, UUID userId) {
        this.con = con;
        this.userId = userId;
    }

    public List<Content> getUserContent() throws SQLException {
        List<Content> contents = new ArrayList<>();
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setObject(1, userId);
            stmnt.setObject(2, userId);
            rs = stmnt.executeQuery();
            while (rs.next()) {
                contents.add(new Content(
                    rs.getString("Type"),
                    rs.getObject(UserProfile.ID_NAME, UUID.class),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getDate("UploadDate").toLocalDate(),
                    rs.getBytes("ImageData"),
                    rs.getString("Extension")
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmnt != null) stmnt.close();
            con.close();
        }
        return contents;
    }
}