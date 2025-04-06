package it.unipd.dei.webapp.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import it.unipd.dei.webapp.dao.CreateArtisticProfileDAO;
import it.unipd.dei.webapp.dao.CreateClientProfileDAO;
import it.unipd.dei.webapp.dao.CreateCredentialsDAO;
import it.unipd.dei.webapp.dao.CreateLocationDAO;
import it.unipd.dei.webapp.dao.CreateUserProfileDAO;

import it.unipd.dei.webapp.dao.GetLocationDAO;
import it.unipd.dei.webapp.resource.*;
import it.unipd.dei.webapp.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;


public class CreateUserProfileServlet extends AbstractDatabaseServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // TODO logger
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.CREATE_USERPROFILE);
        // Parameters for UserProfile
        UUID id;
        byte[] profilePicture = null;
        ImageExtensions pictureExtension = null;
        String name = null;
        String surname = null;
        String brandName = null;
        LocalDate birthDate = null;
        LocalDate registrationDate;

        // Parameters for Credentials
        String email = null;
        String password = null;
        String username = null;

        // Parameters for Location
        String country = null;
        String city = null;
        String postalCode = null;
        String address = null;

        //Parameters for ClientProfile/ArtisticProfile
        UserRole role;

        // Model
        UserProfile userProfile;
        Credentials credentials;
        Location location;
        ClientProfile clientProfile;
        ArtisticProfile artisticProfile;

        // HANDLE REQUEST

        // Get parameters from form in "/signup_accountinformations"
        username = req.getParameter(Credentials.USERNAME_NAME);
        email = req.getParameter(Credentials.EMAIL_NAME);
        password = req.getParameter(Credentials.PASSWORD_NAME_CLEAN);
        role = UserRole.fromString(req.getParameter(ArtisticProfile.ROLE_NAME_CLEAN));
        brandName = req.getParameter(UserProfile.BRAND_NAME_NAME);
        // Handle image
        try {
            Part filePart = req.getPart(UserProfile.PROFILE_PICTURE_NAME);
            if (filePart != null && filePart.getSize() > 0) {
                // Extract extension
                String fileName = filePart.getSubmittedFileName();
                if (fileName.contains(".")) {
                    String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
                    pictureExtension = ImageExtensions.fromString(extension);
                }
                // Convert file to byteArray
                try (InputStream fileContent = filePart.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileContent.read(buffer)) != -1) {
                        baos.write(buffer, 0, bytesRead);
                    }
                    profilePicture = baos.toByteArray();
                }
            }
        } catch (ServletException e) {
            // TODO logger errore (il form non contiene un campo per l'upload dell'immagine)
            logger.error("Wrong form type.", e);
        }

        // Get parameters from session
        HttpSession session = req.getSession();
        name = (String) session.getAttribute(UserProfile.NAME_NAME_CLEAN);
        surname = (String) session.getAttribute(UserProfile.SURNAME_NAME);
        birthDate = (LocalDate) session.getAttribute(UserProfile.BIRTH_DATE_NAME);
        country = (String) session.getAttribute(Location.COUNTRY_NAME);
        city = (String) session.getAttribute(Location.CITY_NAME);
        postalCode = (String) session.getAttribute(Location.POSTAL_CODE_NAME);
        address = (String) session.getAttribute(Location.ADDRESS_NAME);
        session.invalidate();

        // Generate remaining parameters
        id = UUID.randomUUID();
        registrationDate = LocalDate.now();
        String hashedPassword = PasswordUtil.hashPassword(password);

        try {
            location = new Location(country, city, postalCode, address);
            // Test if location exists
            if(new GetLocationDAO(getDataSource().getConnection(), location).getLocation() == null) {
                // Creation of Location in db
                new CreateLocationDAO(getDataSource().getConnection(), location).createLocation();
            }
            // Creation of UserProfile in db
            userProfile = new UserProfile(id, profilePicture, pictureExtension, name, surname, brandName, birthDate, registrationDate, country, city, postalCode, address);
            new CreateUserProfileDAO(getDataSource().getConnection(), userProfile).createUserProfile();

            // Check the selected role and create corresponding profile (Client or Artistic)
            switch (role) {
                case artist:
                    artisticProfile = new ArtisticProfile(id, role);
                    new CreateArtisticProfileDAO(getDataSource().getConnection(), artisticProfile).createArtisticProfile();
                    break;
                case artgallery:
                    artisticProfile = new ArtisticProfile(id, role);
                    new CreateArtisticProfileDAO(getDataSource().getConnection(), artisticProfile).createArtisticProfile();
                    break;
                case genericuser:
                    clientProfile = new ClientProfile(id, role);
                    new CreateClientProfileDAO(getDataSource().getConnection(), clientProfile).createClientProfile();
                    break;
                case businessuser:
                    clientProfile = new ClientProfile(id, role);
                    new CreateClientProfileDAO(getDataSource().getConnection(), clientProfile).createClientProfile();
                    break;
            }

            // Creation of Credentials in db
            credentials = new Credentials(id, email, hashedPassword, username);
            new CreateCredentialsDAO(getDataSource().getConnection(), credentials).createCredentials();
        } catch (SQLException e) {
            // TODO gestire errore del database
            logger.error("Database error", e);
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
            out.println("<p>Country: " + country + ", City: " + city + "</p>");
            out.println("<p>Address: " + address + ", Postal Code: " + postalCode + "</p>");

            out.println("</body></html>");

            out.flush();
            out.close();
        } finally {
            // TODO non so che fare qui ma serve un finally per ora
            //questo è messo qui come fermabuco
            logger.info("User creation process completed.");
        }
    }
    
}
