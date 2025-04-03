package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class CreateLocationDAO {
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
            // TODO Logger
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
