package it.unipd.dei.webapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import it.unipd.dei.webapp.resource.ArtisticProfile;

public class CreateArtisticProfileDAO {
    private static final String STATEMENT = String.format(
        "INSERT INTO paint.%s (%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?)",
        ArtisticProfile.TABLE_NAME,
        ArtisticProfile.USER_ID_NAME,
        ArtisticProfile.ROLE_NAME,
        ArtisticProfile.BIOGRAPHY_NAME,
        ArtisticProfile.FOLLOWER_COUNT_NAME,
        ArtisticProfile.NUM_PUBLISHED_ART_PIECES_NAME,
        ArtisticProfile.NUM_SOLD_ART_PIECES_NAME,
        ArtisticProfile.LAST_PUB_DATE_NAME
    );

    private final Connection con;
    private final ArtisticProfile artisticProfile;

    public CreateArtisticProfileDAO(Connection con, ArtisticProfile artisticProfile) {
        if(artisticProfile == null) {
            // TODO Logger
            throw new NullPointerException("The artistic profile cannot be null");
        }
        this.con = con;
        this.artisticProfile = artisticProfile;
    }

    public int createArtisticProfile() throws SQLException {
        PreparedStatement stmnt = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setObject(1, artisticProfile.getUserId());
            stmnt.setString(2, artisticProfile.getRole().toString());
            stmnt.setString(3, artisticProfile.getBiography());
            stmnt.setInt(4, artisticProfile.getFollowerCount());
            stmnt.setInt(5, artisticProfile.getNumPublishedArtPieces());
            stmnt.setInt(6, artisticProfile.getNumSoldArtPieces());
            LocalDate lastPublishedDate = artisticProfile.getLastPubDate();
            stmnt.setObject(7, Date.valueOf(lastPublishedDate));

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
