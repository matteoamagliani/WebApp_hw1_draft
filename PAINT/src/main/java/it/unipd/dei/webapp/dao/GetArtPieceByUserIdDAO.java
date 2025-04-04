package it.unipd.dei.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.unipd.dei.webapp.resource.ArtPiece;

public class GetArtPieceByUserIdDAO {
    private static final String STATEMENT = String.format(
        "SELECT * FROM paint.%s WHERE %s = ? ORDER BY %s DESC",
        ArtPiece.TABLE_NAME,
        ArtPiece.ARTISTIC_USER_ID_NAME,
        ArtPiece.UPLOAD_DATE_NAME
    );

    private final Connection con;
    private final UUID id;

    public GetArtPieceByUserIdDAO(Connection con, UUID id) {
        this.con = con;
        this.id = id;
    }

    public List<ArtPiece> getArtPieces() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        List<ArtPiece> artPieces = new ArrayList<>();

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setObject(1, id);

            rs = stmnt.executeQuery();

            while (rs.next()) {
                UUID id = rs.getObject(ArtPiece.ID_NAME, UUID.class);
                UUID artisticUserId = rs.getObject(ArtPiece.ARTISTIC_USER_ID_NAME, UUID.class);
                String description = rs.getString(ArtPiece.DESCRIPTION_NAME);
                double weight = rs.getDouble(ArtPiece.WEIGHT_NAME);
                String title = rs.getString(ArtPiece.TITLE_NAME);
                LocalDate creationDate = rs.getDate(ArtPiece.CREATION_DATE_NAME).toLocalDate();
                double dimensionWidth = rs.getDouble(ArtPiece.DIMENSION_WIDTH_NAME);
                double dimensionHeight = rs.getDouble(ArtPiece.DIMENSION_HEIGHT_NAME);
                double dimensionLength = rs.getDouble(ArtPiece.DIMENSION_LENGTH_NAME);
                LocalDate uploadDate = rs.getDate(ArtPiece.UPLOAD_DATE_NAME).toLocalDate();

                ArtPiece artPiece = new ArtPiece(id, artisticUserId, description, weight, title, creationDate, dimensionWidth, dimensionHeight, dimensionLength, uploadDate);

                artPieces.add(artPiece);
            }

            return artPieces;

        } finally {
            if (rs != null) rs.close();
            if (stmnt != null) stmnt.close();
            con.close();
        }
    }
}
