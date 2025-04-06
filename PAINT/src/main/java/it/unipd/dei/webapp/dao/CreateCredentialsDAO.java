package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Credentials;
import it.unipd.dei.webapp.validation.ValidationHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Creates new {@link Credentials} in the database.
 * <p>
 * This DAO is responsible for inserting a new record into the {@code paint.credentials} table,
 * based on the data provided in a {@link Credentials} resource object.
 * </p>
 *
 */
 public final class CreateCredentialsDAO {
    private final static Logger logger = LogManager.getLogger(CreateCredentialsDAO.class, StringFormatterMessageFactory.INSTANCE);

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
            logger.error("Credentials is null");
            throw new NullPointerException("The credentials cannot be null");
        }
        this.con = con;
        this.credentials = credentials;
    }
    
    // TODO: CHECK VALIDATION delle credenziali prima della insert nel db
    /*public ValidationHashMap validateCredentials() {
        return credentials.validateFields();
    }*/
    
    public int createCredentials() throws SQLException {
        PreparedStatement stmnt = null;
        //TODO: printare ERRORI DI VALIDAZIONE se NON SONO NULLI
        /*
        ValidationHashMap validationResult = validateCredentials();
        if (!validationResult.isEmpty()) {
            // Log validation errors
            logger.error("Validation errors for creadentials: "+ validationResult.toString());
            System.out.println("Validation errors for credentials:");
            System.out.println(validationResult.toString());
            throw new IllegalArgumentException("Invalid credentials: " + validationResult.toString());
        }
        */
        
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
