package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Credentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class CreateCredentialsDAO {
    private static final String STATEMENT = "INSERT INTO PAINT.Credentials (UserId, Email, Password, Username) VALUES (?, ?, ?, ?)";

    private final Connection con;
    private final Credentials credentials;

    public CreateCredentialsDAO(Connection con, Credentials credentials) {
        if(credentials == null) {
            // TODO Logger
            throw new NullPointerException("The credentials cannot be null");
        }
        this.con = con;
        this.credentials = credentials;
    }

    public int createCredentials() throws SQLException {
        PreparedStatement stmnt = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setObject(1, credentials.getUserId());
            stmnt.setString(2, credentials.getEmail());
            stmnt.setString(3, credentials.getPassword());
            stmnt.setString(4, credentials.getUsername());

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
