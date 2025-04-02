package it.unipd.dei.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocationDAO {
    private static final String INSERT_LOCATION_IF_NOT_EXISTS = 
        "INSERT INTO paint.Location (Country, City, PostalCode, Address) " +
        "SELECT ?, ?, ?, ? " +
        "WHERE NOT EXISTS (" +
        "    SELECT 1 FROM paint.Location " +
        "    WHERE Country = ? AND City = ? AND PostalCode = ? AND Address = ?" +
        ")";

    private final Connection con;

    public LocationDAO(Connection con) {
        this.con = con;
    }

    public void insertLocationIfNotExists(String country, String city, String postalCode, String address) throws SQLException {
        PreparedStatement stmnt = null;

        try {
            stmnt = con.prepareStatement(INSERT_LOCATION_IF_NOT_EXISTS);
            stmnt.setString(1, country);
            stmnt.setString(2, city);
            stmnt.setString(3, postalCode);
            stmnt.setString(4, address);
            stmnt.setString(5, country);
            stmnt.setString(6, city);
            stmnt.setString(7, postalCode);
            stmnt.setString(8, address);

            stmnt.executeUpdate();
        } finally {
            if (stmnt != null) {
                stmnt.close();
            }
        }
    }
}
