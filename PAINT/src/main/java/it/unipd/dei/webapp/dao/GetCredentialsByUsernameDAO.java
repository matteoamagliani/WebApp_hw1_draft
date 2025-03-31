package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Credentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class GetCredentialsByUsernameDAO {
    private static final String STATEMENT = "SELECT * FROM paint.Credentials WHERE Username = ?";

    private final Connection con;
    private final String username;

    public GetCredentialsByUsernameDAO(Connection con, String username) {
        this.con = con;
        this.username = username;
    }

    public Credentials getCredentials() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setString(1, username);

            rs = stmnt.executeQuery();
            
            Credentials new_credentials = null;
            if(rs.next()){
                UUID id = rs.getObject("id", UUID.class);
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                String username = rs.getString("Username");

                new_credentials = new Credentials(id, email, password, username);
            }
            return new_credentials;
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
