package it.unipd.dei.webapp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.unipd.dei.webapp.resource.ImageExtensions;
import it.unipd.dei.webapp.resource.UserProfile;
import it.unipd.dei.webapp.resource.Content;

//TODO: cambia tutto con UserProfile.
public class HomepageDAO {
    private final String url = "jdbc:postgresql://localhost:5432/paintdb";
    private final String user = "PAINT_owner";
    private final String password = "paint2425";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Ottiene informazioni complete dell'utente
    public UserProfile getUserProfile(UUID userId) throws SQLException {
        String sql = "SELECT u.id, u.ProfilePicture, u.PictureExtension, u.Name, u.Surname, u.BrandName, " +
                "u.BirthDate, u.RegistrationDate, u.LocationCountry, u.LocationCity, u.LocationPostalCode, u.LocationAddress " +
                "FROM paint.UserProfile u WHERE u.id = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UserProfile(
                            rs.getObject(UserProfile.ID_NAME, UUID.class),
                            rs.getBytes(UserProfile.PROFILE_PICTURE_NAME),
                            rs.getObject(UserProfile.PICTURE_EXTENSION_NAME, ImageExtensions.class),
                            rs.getString(UserProfile.NAME_NAME),
                            rs.getString(UserProfile.SURNAME_NAME),
                            rs.getString(UserProfile.BRAND_NAME_NAME),
                            rs.getDate(UserProfile.BIRTH_DATE_NAME).toLocalDate(),
                            rs.getDate(UserProfile.REGISTRATION_DATE_NAME).toLocalDate(),
                            rs.getString(UserProfile.LOCATION_COUNTRY_NAME),
                            rs.getString(UserProfile.LOCATION_CITY_NAME),
                            rs.getString(UserProfile.LOCATION_POSTAL_CODE_NAME),
                            rs.getString(UserProfile.LOCATION_ADDRESS_NAME)
                    );
                }
            }
        }
        return null;
    }

    // Ottiene ArtPieces ed Eventi dell'utente
    public List<Content> getUserContent(UUID userId) throws SQLException {
        List<Content> contents = new ArrayList<>();
        String sql = "SELECT 'ArtPiece' AS Type, ap.id, ap.Title, ap.Description, ap.UploadDate, img.ImageData, img.Extension " +
                "FROM paint.ArtPiece ap LEFT JOIN paint.ArtPieceImage img ON ap.id = img.ArtPieceId AND img.ImageIndex = 1 " +
                "WHERE ap.ArtisticUserId = ? " +
                "UNION " +
                "SELECT 'Event' AS Type, e.id, e.Title, e.Description, e.UploadDate, img.ImageData, img.Extension " +
                "FROM paint.Event e LEFT JOIN paint.EventImage img ON e.id = img.EventId AND img.ImageIndex = 1 " +
                "WHERE e.ArtisticUserId_Organizer = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, userId);
            stmt.setObject(2, userId);
            try (ResultSet rs = stmt.executeQuery()) {
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
            }
        }
        return contents;
    }

    // Nuovo metodo: ottiene 4 profili utenti con il maggior numero di nuovi follower nell'ultima settimana
    public List<UserProfile> getRecommendedUsers() throws SQLException {
        List<UserProfile> recommended = new ArrayList<>();
        String sql = "SELECT u.id, u.ProfilePicture, u.PictureExtension, u.Name, u.Surname " +
                "FROM paint.UserProfile u " +
                "JOIN artbase.Follows f ON u.id = f.ArtisticUserId_Followed " +
                "WHERE f.FollowDate >= CURRENT_DATE - INTERVAL '7 days' " +
                "GROUP BY u.id, u.ProfilePicture, u.PictureExtension, u.Name, u.Surname " +
                "ORDER BY COUNT(*) DESC LIMIT 4";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                recommended.add(new UserProfile(
                        rs.getObject("id", UUID.class),
                        rs.getBytes("ProfilePicture"),
                        rs.getObject(UserProfile.PICTURE_EXTENSION_NAME, ImageExtensions.class),
                        rs.getString("Name"),
                        rs.getString("Surname"),
                        null,          // BrandName non disponibile
                        null,          // BirthDate non disponibile
                        null,          // RegistrationDate non disponibile
                        null,          // LocationCountry non disponibile
                        null,          // LocationCity non disponibile
                        null,          // LocationPostalCode non disponibile
                        null           // LocationAddress non disponibile
                ));
            }
        }
        return recommended;
    }

    // Nuovo metodo: cerca profili utenti in base a una query
    public List<UserProfile> searchUserProfiles(String query) throws SQLException {
        List<UserProfile> users = new ArrayList<>();
        String sql = "SELECT u.id, u.ProfilePicture, u.PictureExtension, u.Name, u.Surname, c.Username " +
                "FROM paint.UserProfile u " +
                "JOIN paint.Credentials c ON u.id = c.UserId " +
                "WHERE c.Username ILIKE ? " +
                "ORDER BY c.Username";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + query + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(new UserProfile(
                            rs.getObject("id", UUID.class),
                            rs.getBytes("ProfilePicture"),
                            rs.getObject(UserProfile.PICTURE_EXTENSION_NAME, ImageExtensions.class),
                            rs.getString("Name"),
                            rs.getString("Surname"),
                            null,          // BrandName non disponibile
                            null,          // BirthDate non disponibile
                            null,          // RegistrationDate non disponibile
                            null,          // LocationCountry non disponibile
                            null,          // LocationCity non disponibile
                            null,          // LocationPostalCode non disponibile
                            null           // LocationAddress non disponibile
                    ));
                }
            }
        }
        return users;
    }
}