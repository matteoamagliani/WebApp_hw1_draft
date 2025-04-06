package it.unipd.dei.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.unipd.dei.webapp.resource.ClientProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;
/**
 * Creates a new {@link ClientProfile} in the database.
 * <p>
 * This DAO handles the insertion of a new client profile into the {@code paint.clientProfile} table
 * using the data provided by a {@link ClientProfile} resource object.
 * </p>
 *
 */
public class CreateClientProfileDAO {
    private final static Logger logger = LogManager.getLogger(CreateClientProfileDAO.class, StringFormatterMessageFactory.INSTANCE);
    private static final String STATEMENT = String.format(
        "INSERT INTO paint.%s (%s, %s) VALUES (?, ?::paint.userRole)",
        ClientProfile.TABLE_NAME,
        ClientProfile.USER_ID_NAME,
        ClientProfile.ROLE_NAME
    );

    private final Connection con;
    private final ClientProfile clientProfile;

    public CreateClientProfileDAO(Connection con, ClientProfile clientProfile) {
        if(clientProfile == null) {
            logger.error("Client profile is null");
            throw new NullPointerException("The client profile cannot be null");
        }
        this.con = con;
        this.clientProfile = clientProfile;
    }

    public int createClientProfile() throws SQLException {
        PreparedStatement stmnt = null;

        try {
            stmnt = con.prepareStatement(STATEMENT);
            stmnt.setObject(1, clientProfile.getUserId());
            stmnt.setString(2, clientProfile.getRole().toString());
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
