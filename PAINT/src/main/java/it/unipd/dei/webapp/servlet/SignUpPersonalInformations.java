package it.unipd.dei.webapp.servlet;

import java.io.IOException;
import java.time.LocalDate;

import it.unipd.dei.webapp.resource.UserProfile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignUpPersonalInformations extends AbstractServlet {
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // TODO logger

        // Get parameters from form in "/signup_personalinformations"
        String name = req.getParameter(UserProfile.NAME_NAME);
        String surname = req.getParameter(UserProfile.SURNAME_NAME);
        String birthDateString = req.getParameter(UserProfile.BIRTH_DATE_NAME);
        LocalDate birthDate = LocalDate.parse(birthDateString);

        // Get http session and save parameters
        HttpSession session = req.getSession();
        session.setAttribute(UserProfile.NAME_NAME, name);
        session.setAttribute(UserProfile.SURNAME_NAME, surname);
        session.setAttribute(UserProfile.BIRTH_DATE_NAME, birthDate);

        // Redirect to next form
        res.sendRedirect("jsp/signup_addressinformations.jsp");
    }
}
