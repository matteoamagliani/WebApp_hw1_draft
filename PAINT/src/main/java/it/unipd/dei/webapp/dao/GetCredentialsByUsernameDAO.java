package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Credentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
/**
 * Retrieves a {@link Credentials} object from the database based on the provided username.
 * <p>
 * This DAO performs a SELECT query on the {@code paint.credentials} table.
 * </p>
 *
 */
public class GetCredentialsByUsernameDAO {
    private final static Logger logger = LogManager.getLogger(GetCredentialsByUsernameDAO.class, StringFormatterMessageFactory.INSTANCE);

    private static final String STATEMENT = String.format(
        "SELECT * FROM paint.%s WHERE %s = ?",
        Credentials.TABLE_NAME, 
        Credentials.USERNAME_NAME
    );

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
                UUID id = rs.getObject(Credentials.USER_ID_NAME, UUID.class);
                String email = rs.getString(Credentials.EMAIL_NAME);
                String password = rs.getString(Credentials.PASSWORD_NAME_CLEAN);
                String username = rs.getString(Credentials.USERNAME_NAME);

                new_credentials = new Credentials(id, email, password, username);
                logger.info("Credentials found for the user.");
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
