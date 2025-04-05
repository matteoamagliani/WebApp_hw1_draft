package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Creates a new {@link Location} entry in the database.
 * <p>
 * This DAO handles the insertion of a new location into the {@code paint.location} table
 * using the data provided by a {@link Location} resource object.
 * </p>
 *
 * The location includes country, city, postal code and address.
 *
 *
 */
public final class CreateLocationDAO {
    private final static Logger logger = LogManager.getLogger(CreateLocationDAO.class, StringFormatterMessageFactory.INSTANCE);

    private static final String STATEMENT = String.format(
        "INSERT INTO paint.%s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
        Location.TABLE_NAME, 
        Location.COUNTRY_NAME, 
        Location.CITY_NAME, 
        Location.POSTAL_CODE_NAME, 
        Location.ADDRESS_NAME
    );


    private final Connection con;
    private final Location location;

    public CreateLocationDAO(Connection con, Location location) {
        if(location == null) {
            logger.error("Location is null");
            throw new NullPointerException("The location cannot be null");
        }
        this.con = con;
        this.location = location;
    }

    public int createLocation() throws SQLException {
        PreparedStatement stmnt = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setString(1, location.getCountry());
            stmnt.setString(2, location.getCity());
            stmnt.setString(3, location.getPostalCode());
            stmnt.setString(4, location.getAddress());

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
