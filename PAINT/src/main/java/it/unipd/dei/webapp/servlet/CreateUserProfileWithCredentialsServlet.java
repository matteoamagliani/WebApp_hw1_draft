package it.unipd.dei.webapp.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.logging.log4j.message.Message;

import it.unipd.dei.webapp.dao.CreateCredentialsDAO;
import it.unipd.dei.webapp.dao.CreateUserProfileDAO;
import it.unipd.dei.webapp.ID;
import it.unipd.dei.webapp.resource.Credentials;
import it.unipd.dei.webapp.resource.UserProfile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem; // libreria da verificare
import org.apache.commons.fileupload.disk.DiskFileItemFactory; // libreria da verificare
import org.apache.commons.fileupload.servlet.ServletFileUpload; // libreria da verificare


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

        // Model
        UserProfile userProfile;
        Credentials credentials;
        //Message m = null;

        // HANDLE REQUEST
        try {
            // Extracting parameters from the form
            // TODO gestione upload file
            // profilePicture = req.getParameter("profilePicture");
            // pictureExtension = req.getParameter("pictureExtension");
//            name = req.getParameter("name");
//            surname = req.getParameter("surname");
//            brandName = req.getParameter("brandName");
//            String birthDateString = req.getParameter("birthDate");
//            birthDate = LocalDate.parse(birthDateString);
//            locationCountry = req.getParameter("locationCountry");
//            locationCity = req.getParameter("locationCity");
//            locationPostalCode = req.getParameter("locationPostalCode");
//            locationAddress = req.getParameter("locationAddress");
//            email = req.getParameter("email");
//            password = req.getParameter("password");
//            username = req.getParameter("username");
//            Part pp=req.getPart("profilePicture");
//            profilePicture=pp.getInputStream().readAllBytes();


            if (ServletFileUpload.isMultipartContent((RequestContext) req)) {
                ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
                Collection<FileItem> items = upload.parseRequest((RequestContext) req);

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        switch (item.getFieldName()) {
                            case ID.NAME_ID: name = item.getString(); break;
                            case ID.SURNAME_ID: surname = item.getString(); break;
                            case ID.BRAND_NAME_ID: brandName = item.getString(); break;
                            case ID.BIRTHDATE_ID: birthDate = LocalDate.parse(item.getString()); break;
                            case ID.COUNTRY_ID: locationCountry = item.getString(); break;
                            case ID.CITY_ID: locationCity = item.getString(); break;
                            case ID.AU_CODE_ID: locationPostalCode = item.getString(); break;
                            case ID.ADDRESS_ID: locationAddress = item.getString(); break;
                            case ID.EMAIL_ID: email = item.getString(); break;
                            case ID.PASSWORD_ID: password = item.getString(); break;
                            case ID.USERNAME_ID: username = item.getString(); break;
                        }
                    } else {
                        pictureExtension = Files.probeContentType(Paths.get(item.getName()));
                        try (InputStream inputStream = item.getInputStream()) {
                            profilePicture = inputStream.readAllBytes();
                        }
                    }
                }
            }
            // Generating remaining parameters
            id = UUID.randomUUID();
            registrationDate = LocalDate.now();

            // Creation of UserProfile in db
            userProfile = new UserProfile(id, profilePicture, pictureExtension, username, surname, brandName, birthDate, registrationDate, locationCountry, locationCity, locationPostalCode, locationAddress);
            new CreateUserProfileDAO(getDataSource().getConnection(), userProfile).createUserProfile();
            // Creation of Credentials in db
            credentials = new Credentials(id, email, password, username);
            new CreateCredentialsDAO(getDataSource().getConnection(), credentials);

        } catch (SQLException ex) {
            // TODO gestire eccezioni decentemente con logger
            if (ex.getSQLState().equals("23505")) {
                //m = new Message(String.format("Cannot create the user profile: user profile %d already exists.", id), "E300", ex.getMessage());
                //LOGGER.error(new StringFormattedMessage("Cannot create the user profile: user profile %d already exists.", id), ex);
            } else {
                //m = new Message("Cannot create the employee: unexpected error while accessing the database.", "E200", ex.getMessage());
                //LOGGER.error("Cannot create the employee: unexpected error while accessing the database.", ex);
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
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
