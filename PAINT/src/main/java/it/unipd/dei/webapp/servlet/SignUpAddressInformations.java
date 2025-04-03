package it.unipd.dei.webapp.servlet;

import java.io.IOException;

import it.unipd.dei.webapp.resource.Location;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignUpAddressInformations extends AbstractServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // TODO logger

        // Get parameters from form in "/signup_addressinformations"
        String country = req.getParameter(Location.COUNTRY_NAME);
        String city = req.getParameter(Location.CITY_NAME);
        String postalCode = req.getParameter(Location.POSTAL_CODE_NAME);
        String address = req.getParameter(Location.ADDRESS_NAME);

        // Get http session and save parameters
        HttpSession session = req.getSession();
        session.setAttribute(Location.COUNTRY_NAME, country);
        session.setAttribute(Location.CITY_NAME, city);
        session.setAttribute(Location.POSTAL_CODE_NAME, postalCode);
        session.setAttribute(Location.ADDRESS_NAME, address);

        // Redirect to next form
        res.sendRedirect("jsp/signup_accountinformations.jsp");
    }
}
