package it.unipd.dei.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.unipd.dei.webapp.resource.ClientProfile;

public class CreateClientProfileDAO {
    private static final String STATEMENT = String.format(
        "INSERT INTO paint.%s (%s, %s) VALUES (?, ?)",
        ClientProfile.TABLE_NAME,
        ClientProfile.USER_ID_NAME,
        ClientProfile.ROLE_NAME
    );

    private final Connection con;
    private final ClientProfile clientProfile;

    public CreateClientProfileDAO(Connection con, ClientProfile clientProfile) {
        if(clientProfile == null) {
            // TODO Logger
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
