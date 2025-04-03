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
import it.unipd.dei.webapp.ID;
import it.unipd.dei.webapp.resource.ArtisticProfile;
import it.unipd.dei.webapp.resource.ClientProfile;
import it.unipd.dei.webapp.resource.Credentials;
import it.unipd.dei.webapp.resource.Location;
import it.unipd.dei.webapp.resource.UserProfile;
import it.unipd.dei.webapp.resource.UserRole;
import it.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;


public class CreateUserProfileWithCredentialsServlet extends AbstractDatabaseServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // TODO logger

        // Parameters for UserProfile
        UUID id;
        byte[] profilePicture = null;
        String pictureExtension = null;
        String name = null;
        String surname = null;
        String brandName = null;
        LocalDate birthDate = null;
        LocalDate registrationDate;
        String locationCountry = null;
        String locationCity = null;
        String locationPostalCode = null;
        String locationAddress = null;

        // Parameters for Credentials
        String email = null;
        String password = null;
        String username = null;

        // Parameters for Location
        String country = null;
        String city = null;
        String AUcode = null;
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
        username = req.getParameter(ID.USERNAME_ID);
        email = req.getParameter(ID.EMAIL_ID);
        password = req.getParameter(ID.PASSWORD_ID);
        role = UserRole.fromString(req.getParameter(ID.ROLE_ID));
        brandName = req.getParameter(ID.BRAND_NAME_ID);
        // Handle image
        try {
                // TODO: da rimuovere alla fine, aggiunti per testare il servlet senza upload del file
                //profilePicture = new byte[0]; // Default empty byte array
                //pictureExtension = "jpg"; // Default extension
            Part filePart = req.getPart(ID.PROFILE_IMAGE_ID);
            if (filePart != null && filePart.getSize() > 0) {
                // Extract extension
                String fileName = filePart.getSubmittedFileName();
                if (fileName.contains(".")) {
                    pictureExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
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
        }

        // Get parameters from session
        HttpSession session = req.getSession();
        name = (String) session.getAttribute(ID.NAME_ID);
        surname = (String) session.getAttribute(ID.SURNAME_ID);
        birthDate = (LocalDate) session.getAttribute(ID.BIRTHDATE_ID);
        country = (String) session.getAttribute(ID.COUNTRY_ID);
        city = (String) session.getAttribute(ID.CITY_ID);
        AUcode = (String) session.getAttribute(ID.POSTAL_CODE_ID);
        address = (String) session.getAttribute(ID.ADDRESS_ID);
        session.invalidate();

        // Generate remaining parameters
        id = UUID.randomUUID();
        registrationDate = LocalDate.now();
        String hashedPassword = PasswordUtil.hashPassword(password);

        try {
            location = new Location(country, city, AUcode, address);
            // Test if location exists
            if(new GetLocationDAO(getDataSource().getConnection(), location).getLocation() == null) {
                // Creation of Location in db
                new CreateLocationDAO(getDataSource().getConnection(), location).createLocation();
            }
            // Creation of UserProfile in db
            userProfile = new UserProfile(id, profilePicture, pictureExtension, name, surname, brandName, birthDate, registrationDate, locationCountry, locationCity, locationPostalCode, locationAddress);
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
