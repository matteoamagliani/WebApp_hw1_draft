package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Credentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class CreateCredentialsDAO {
    private static final String STATEMENT = String.format(
        "INSERT INTO paint.%s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
        Credentials.TABLE_NAME, 
        Credentials.USER_ID_NAME, 
        Credentials.EMAIL_NAME, 
        Credentials.PASSWORD_NAME, 
        Credentials.USERNAME_NAME
    );

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
