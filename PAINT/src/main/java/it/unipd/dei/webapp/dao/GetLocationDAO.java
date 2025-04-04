package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetLocationDAO {
    private static final Logger logger = LogManager.getLogger(GetLocationDAO.class, StringFormatterMessageFactory.INSTANCE );

    private static final String STATEMENT = String.format(
        "SELECT * FROM paint.%s WHERE %s = ? AND %s = ? AND %s = ? AND %s = ?",
        Location.TABLE_NAME, 
        Location.COUNTRY_NAME, 
        Location.CITY_NAME, 
        Location.POSTAL_CODE_NAME, 
        Location.ADDRESS_NAME
    );

    private final Connection con;
    private final Location location;

    public GetLocationDAO(Connection con, Location location) {
        this.con = con;
        this.location = location;
    }

    public Location getLocation() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setString(1, location.getCountry());
            stmnt.setString(2, location.getCity());
            stmnt.setString(3, location.getPostalCode());
            stmnt.setString(4, location.getAddress());

            rs = stmnt.executeQuery();
            
            Location new_location = null;
            if(rs.next()){
                String country = rs.getString(Location.COUNTRY_NAME);
                String city = rs.getString(Location.CITY_NAME);
                String postalCode = rs.getString(Location.POSTAL_CODE_NAME);
                String address = rs.getString(Location.ADDRESS_NAME);

                new_location = new Location(country, city, postalCode, address);
                logger.info("Location found.");
            }
            return new_location;
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmnt != null) {
                stmnt.close();
            }

            con.close();
        }
    }
}