package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.dao.GetCredentialsByEmailDAO;
import it.unipd.dei.webapp.dao.GetCredentialsByUsernameDAO;
import it.unipd.dei.webapp.resource.Credentials;
import it.unipd.dei.webapp.util.EmailTester;
import it.unipd.dei.webapp.util.PasswordUtil;
import it.unipd.dei.webapp.util.TokenManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class LoginServlet extends AbstractDatabaseServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // TODO logger

        // Parameters for Credentials
        String email_username = null;
        String password = null;

        // Model
        Credentials credentials;

        // HANDLE REQUEST

        // Get parameters from form in "/login"
        email_username = req.getParameter(Credentials.EMAIL_NAME);
        password = req.getParameter(Credentials.PASSWORD_NAME_CLEAN);
    
        try {
            // Get Credentials from database
            if(EmailTester.isEmail(email_username)) {
                credentials = new GetCredentialsByEmailDAO(getDataSource().getConnection(), email_username).getCredentials();
            } else {
                credentials = new GetCredentialsByUsernameDAO(getDataSource().getConnection(), email_username).getCredentials(); 
            }
    
            if (credentials != null && PasswordUtil.verifyPassword(password, credentials.getPassword())) {
                // Generate authentication token
                String token = TokenManager.generateToken(credentials.getEmail());
                // Generate authentication cookie
                Cookie tokenCookie = new Cookie("auth_token", token);
                tokenCookie.setHttpOnly(true);
                tokenCookie.setSecure(true);
                tokenCookie.setPath("/user");
                tokenCookie.setMaxAge((int)(TokenManager.EXPIRATION_TIME / 1000));
                res.addCookie(tokenCookie);
    
                // Reindirizza alla pagina principale
                res.sendRedirect("user/home.jsp");
            } else {
                // TODO gestire il login errato
            }
        } catch (SQLException e) {
            // TODO gestire errore del database
        }
    }
}
