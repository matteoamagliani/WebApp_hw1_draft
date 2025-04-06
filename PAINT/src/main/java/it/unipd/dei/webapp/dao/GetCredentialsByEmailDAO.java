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
 * Retrieves a {@link Credentials} object from the database based on the provided email address.
 * <p>
 * This DAO performs a SELECT query on the {@code paint.credentials} table.
 * </p>
 */
public class GetCredentialsByEmailDAO {
    private final static Logger logger = LogManager.getLogger(GetCredentialsByEmailDAO.class, StringFormatterMessageFactory.INSTANCE);

    private static final String STATEMENT = String.format(
        "SELECT * FROM paint.%s WHERE %s = ?",
        Credentials.TABLE_NAME, 
        Credentials.EMAIL_NAME
    );

    private final Connection con;
    private final String email;

    public GetCredentialsByEmailDAO(Connection con, String email) {
        this.con = con;
        this.email = email;
    }

    public Credentials getCredentials() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setString(1, email);

            rs = stmnt.executeQuery();
            
            Credentials new_credentials = null;
            if(rs.next()){
                UUID id = rs.getObject(Credentials.USER_ID_NAME, UUID.class);
                String email = rs.getString(Credentials.EMAIL_NAME);
                String password = rs.getString(Credentials.PASSWORD_NAME_CLEAN);
                String username = rs.getString(Credentials.USERNAME_NAME);

                logger.info("Credentials retrieved for email: {} | ID: {} | Username: {}",
                        email, id, username);
                // TODO: remove this print statement in production code
                logger.info("Credentials retrieved for email: {} | ID: {} | Username: {}",
                        email, id, username);

                System.out.println("DAO GetCredentialsByEmailDAO");
                System.out.println("ID: " + id);
                System.out.println("Email: " + email);
                System.out.println("Password hash recuperata: " + password);
                new_credentials = new Credentials(id, email, password, username);
                logger.info("Credentials for email: {} | Password {}",email, password);
                // TODO: remove this print statement in production code
                logger.info("Credentials for email: {} | Password {}",email, password);
                System.out.println("Email: " + email);
                System.out.println("Password hash recuperata: " + password);
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
