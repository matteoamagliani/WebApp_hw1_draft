package it.unipd.dei.webapp.servlet;

import java.io.IOException;

import it.unipd.dei.webapp.resource.Actions;
import it.unipd.dei.webapp.resource.Location;
import it.unipd.dei.webapp.resource.LogContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignUpAddressInformations extends AbstractServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // TODO logger
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.SINGUP_ADDRESS_INFORMATION);

        // Get parameters from form in "/signup_addressinformations"
        String country = req.getParameter(Location.COUNTRY_NAME);
        String city = req.getParameter(Location.CITY_NAME);
        String postalCode = req.getParameter(Location.POSTAL_CODE_NAME);
        String address = req.getParameter(Location.ADDRESS_NAME);

        logger.info("Collected addess data.");

        // Get http session and save parameters
        HttpSession session = req.getSession();
        session.setAttribute(Location.COUNTRY_NAME, country);
        session.setAttribute(Location.CITY_NAME, city);
        session.setAttribute(Location.POSTAL_CODE_NAME, postalCode);
        session.setAttribute(Location.ADDRESS_NAME, address);

        logger.info("Saved address info in session. Redirecting to account information form.");

        // Redirect to next form
        res.sendRedirect("jsp/signup_accountinformations.jsp");
    }
}
