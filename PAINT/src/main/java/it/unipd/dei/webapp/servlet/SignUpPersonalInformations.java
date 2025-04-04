package it.unipd.dei.webapp.servlet;

import java.io.IOException;
import java.time.LocalDate;

import it.unipd.dei.webapp.resource.Actions;
import it.unipd.dei.webapp.resource.LogContext;
import it.unipd.dei.webapp.resource.UserProfile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignUpPersonalInformations extends AbstractServlet {
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // TODO logger
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.SINGUP_PERSONAL_INFORMATION);

        // Get parameters from form in "/signup_personalinformations"
        String name = req.getParameter(UserProfile.NAME_NAME_CLEAN);
        String surname = req.getParameter(UserProfile.SURNAME_NAME);
        String birthDateString = req.getParameter(UserProfile.BIRTH_DATE_NAME);
        LocalDate birthDate = LocalDate.parse(birthDateString);

        // Get http session and save parameters
        HttpSession session = req.getSession();
        session.setAttribute(UserProfile.NAME_NAME_CLEAN, name);
        session.setAttribute(UserProfile.SURNAME_NAME, surname);
        session.setAttribute(UserProfile.BIRTH_DATE_NAME, birthDate);

        logger.info("Saved data info in session. Redirecting to address information form.");

        // Redirect to next form
        res.sendRedirect("jsp/signup_addressinformations.jsp");
    }
}
