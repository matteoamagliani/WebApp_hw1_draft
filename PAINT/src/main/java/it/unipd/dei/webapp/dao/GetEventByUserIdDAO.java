package it.unipd.dei.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.unipd.dei.webapp.resource.Event;

public class GetEventByUserIdDAO {
    private static final String STATEMENT = String.format(
        "SELECT * FROM paint.%s WHERE %s = ? ORDER BY %s DESC",
        Event.TABLE_NAME,
        Event.ARTISTIC_USER_ID_ORGANIZER_NAME,
        Event.UPLOAD_DATE_NAME
    );

    private final Connection con;
    private final UUID id;

    public GetEventByUserIdDAO(Connection con, UUID id) {
        this.con = con;
        this.id = id;
    }

    public List<Event> getArtPieces() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        List<Event> events = new ArrayList<>();

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setObject(1, id);

            rs = stmnt.executeQuery();

            while (rs.next()) {
                UUID id = rs.getObject(Event.ID_NAME, UUID.class);
                UUID artisticUserIdOrganizer = rs.getObject(Event.ARTISTIC_USER_ID_ORGANIZER_NAME, UUID.class);
                LocalDate startDate = rs.getDate(Event.START_DATE_NAME).toLocalDate();
                LocalDate endDate = rs.getDate(Event.END_DATE_NAME).toLocalDate();
                String title = rs.getString(Event.TITLE_NAME);
                String description = rs.getString(Event.DESCRIPTION_NAME);
                LocalDate uploadDate = rs.getDate(Event.UPLOAD_DATE_NAME).toLocalDate();

                Event event = new Event(id, artisticUserIdOrganizer, startDate, endDate, title, description, uploadDate);

                events.add(event);
            }

            return events;

        } finally {
            if (rs != null) rs.close();
            if (stmnt != null) stmnt.close();
            con.close();
        }
    }
}
