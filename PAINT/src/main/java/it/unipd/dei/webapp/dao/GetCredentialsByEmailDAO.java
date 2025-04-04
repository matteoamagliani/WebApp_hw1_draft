package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Credentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class GetCredentialsByEmailDAO {
    private final static Logger logger = LogManager.getLogger(GetCredentialsByEmailDAO.class);

    private static final String STATEMENT = "SELECT * FROM paint.Credentials WHERE Email = ?";

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
                UUID id = rs.getObject("id", UUID.class);
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                String username = rs.getString("Username");

                // TODO: remove this print statement in production code
                logger.info("Credentials retrieved for email: {} | ID: {} | Username: {}",
                        email, id, username);

                System.out.println("DAO GetCredentialsByEmailDAO");
                System.out.println("ID: " + id);
                System.out.println("Email: " + email);
                System.out.println("Password hash recuperata: " + password);
                new_credentials = new Credentials(id, email, password, username);
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
