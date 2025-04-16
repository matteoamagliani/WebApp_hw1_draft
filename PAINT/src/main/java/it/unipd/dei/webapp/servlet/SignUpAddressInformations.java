package it.unipd.dei.webapp.servlet;

import java.io.IOException;

import it.unipd.dei.webapp.ID;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignUpAddressInformations extends AbstractServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // TODO logger

        // Get parameters from form in "/signup_addressinformations"
        String country = req.getParameter(ID.COUNTRY_ID);
        String city = req.getParameter(ID.CITY_ID);
        String AUcode = req.getParameter(ID.AU_CODE_ID);
        String address = req.getParameter(ID.ADDRESS_ID);

        // Get http session and save parameters
        HttpSession session = req.getSession();
        session.setAttribute(ID.COUNTRY_ID, country);
        session.setAttribute(ID.CITY_ID, city);
        session.setAttribute(ID.AU_CODE_ID, AUcode);
        session.setAttribute(ID.ADDRESS_ID, address);

        // Redirect to next form
        res.sendRedirect("jsp/signup_accountinformations.jsp");
    }
}
