package it.unipd.dei.webapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import org.apache.logging.log4j.message.Message;

import it.unipd.dei.webapp.dao.CreateCredentialsDAO;
import it.unipd.dei.webapp.dao.CreateUserProfileDAO;
import it.unipd.dei.webapp.dao.LocationDAO;
import it.unipd.dei.webapp.resource.Credentials;
import it.unipd.dei.webapp.resource.UserProfile;
import it.util.PasswordUtil; // Importa la classe PasswordUtil
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreateUserProfileWithCredentialsServlet extends AbstractDatabaseServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // TODO logger

        // Parameterse for UserProfile
        UUID id = null;
        byte[] profilePicture = null;
        String pictureExtension = null;
        String name = null;
        String surname = null;
        String brandName = null;
        LocalDate birthDate = null;
        LocalDate registrationDate = null;
        String locationCountry = null;
        String locationCity = null;
        String locationPostalCode = null;
        String locationAddress = null;

        // Parameters for Credentials
        String email = null;
        String password = null;
        String username = null;

        // Model
        UserProfile userProfile = null;
        Credentials credentials = null;
        Message m = null;

        // HANDLE REQUEST
        try {
            // Extracting parameters from the form
            // TODO gestione upload file
            // profilePicture = req.getParameter("profilePicture");
            // pictureExtension = req.getParameter("pictureExtension");

            // TODO: da rimuovere alla fine, aggiunti per testare il servlet senza upload del file
            profilePicture = new byte[0]; // Default empty byte array
            pictureExtension = "jpg"; // Default extension

            name = req.getParameter("name");
            surname = req.getParameter("surname");
            brandName = req.getParameter("brandName");
            String birthDateString = req.getParameter("birthDate");
            birthDate = LocalDate.parse(birthDateString);
            locationCountry = req.getParameter("locationCountry");
            locationCity = req.getParameter("locationCity");
            locationPostalCode = req.getParameter("locationPostalCode");
            locationAddress = req.getParameter("locationAddress");
            email = req.getParameter("email");
            password = req.getParameter("password");
            username = req.getParameter("username");

            // Ensure the location exists in the database
            new LocationDAO(getDataSource().getConnection())
                .insertLocationIfNotExists(locationCountry, locationCity, locationPostalCode, locationAddress);

            // Generating remaining parameters
            id = UUID.randomUUID();
            registrationDate = LocalDate.now();

            // Hash the password
            String hashedPassword = PasswordUtil.hashPassword(password);

            // Creation of UserProfile in db
            userProfile = new UserProfile(id, profilePicture, pictureExtension, username, surname, brandName, birthDate, registrationDate, locationCountry, locationCity, locationPostalCode, locationAddress);
            new CreateUserProfileDAO(getDataSource().getConnection(), userProfile).createUserProfile();
            
            // Creation of Credentials in db
            credentials = new Credentials(id, email, hashedPassword, username);
            new CreateCredentialsDAO(getDataSource().getConnection(), credentials).createCredentials();

        } catch (SQLException ex) {
            // TODO gestire eccezioni decentemente con logger
            if (ex.getSQLState().equals("23505")) {
                //m = new Message(String.format("Cannot create the user profile: user profile %d already exists.", id), "E300", ex.getMessage());
                //LOGGER.error(new StringFormattedMessage("Cannot create the user profile: user profile %d already exists.", id), ex);
            } else {
                //m = new Message("Cannot create the employee: unexpected error while accessing the database.", "E200", ex.getMessage());
                //LOGGER.error("Cannot create the employee: unexpected error while accessing the database.", ex);
            }
        }
        
        // GENERATE RESPONSE
        try {
            res.setContentType("text/html; charset=utf-8");
            PrintWriter out = res.getWriter();
            out.println("<html><body>");

            out.println("<h3>Account Credentials</h3>");
            out.println("<p>Email: " + email + "</p>");
            out.println("<p>Username: " + username + "</p>");
            out.println("<p>Password: (hidden for security reasons)</p>");

            out.println("<h3>Personal Information</h3>");
            out.println("<p>Name: " + name + " " + surname + "</p>");
            out.println("<p>Birth Date: " + birthDate + "</p>");
            out.println("<p>Country: " + locationCountry + ", City: " + locationCity + "</p>");
            out.println("<p>Address: " + locationAddress + ", Postal Code: " + locationPostalCode + "</p>");

            out.println("</body></html>");

            out.flush();
            out.close();
        } finally {
            // TODO non so che fare qui ma serve un finally per ora
        }
    }
    
}
