package it.unipd.dei.webapp.servlet;

import java.io.IOException;
import java.time.LocalDate;

import it.unipd.dei.webapp.ID;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignUpPersonalInformations extends AbstractServlet {
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // TODO logger

        // Get parameters from form in "/signup_personalinformations"
        String name = req.getParameter(ID.NAME_ID);
        String surname = req.getParameter(ID.SURNAME_ID);
        String birthDateString = req.getParameter(ID.BIRTHDATE_ID);
        LocalDate birthDate = LocalDate.parse(birthDateString);

        // Get http session and save parameters
        HttpSession session = req.getSession();
        session.setAttribute(ID.NAME_ID, name);
        session.setAttribute(ID.SURNAME_ID, surname);
        session.setAttribute(ID.BIRTHDATE_ID, birthDate);

        // Redirect to next form
        res.sendRedirect("jsp/signup_addressinformations.jsp");
    }
}
