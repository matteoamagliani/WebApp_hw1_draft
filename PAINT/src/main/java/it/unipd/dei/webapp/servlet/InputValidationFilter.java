package it.unipd.dei.webapp.servlet;

import jakarta.servlet.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;
import java.util.regex.Pattern;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class InputValidationFilter implements Filter {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])[A-Za-z\\d@#$%^&+=!]{8,}$");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
    private static final Pattern GENERAL_STRING_PATTERN = Pattern.compile("^[a-zA-ZàèìòùÀÈÌÒÙ'\\s-]{1,50}$");
    private static final Pattern POSTAL_CODE_PATTERN = Pattern.compile("^[0-9]{4,10}$");
    private static final Pattern ADDRESS_PATTERN = Pattern.compile("^[a-zA-Z0-9àèìòùÀÈÌÒÙ'\\s,.-]{5,100}$");


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inizializzazione del filtro, se necessaria
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        // Validazione email
        String email = req.getParameter("email");
        if (email != null && !EMAIL_PATTERN.matcher(email).matches()) {
            email = email.trim().toLowerCase();
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email is not valid");
            return;
        }

        // Validazione password (min 8 caratteri, almeno una lettera e un numero)
        String password = req.getParameter("password");
        if (password != null && !PASSWORD_PATTERN.matcher(password).matches()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Password not valid. Insert at least 8 character, with at leas one upper letter, a number and a special character.");
            return;
        }

        // Validazione username (solo lettere, numeri e underscore, 3-20 caratteri)
        String username = req.getParameter("username");
        if (username != null && !USERNAME_PATTERN.matcher(username).matches()) {
            username = username.trim().toLowerCase();
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username no valid.");
            return;
        }

        // Validazione name
        String name = req.getParameter("name");
        if (name != null && !GENERAL_STRING_PATTERN.matcher(name).matches()) {
            name = name.trim().toLowerCase();
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Name not valid.");
            return;
        }

        String surname = req.getParameter("surname");
        if (surname != null && !GENERAL_STRING_PATTERN.matcher(surname).matches()) {
            surname = surname.trim().toLowerCase();
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Surname not valid.");
            return;
        }

        // Validazione ID di tipo UUID
        String id = req.getParameter("id");
        if (id != null) {
            id = id.trim().toLowerCase();
            try {
                UUID.fromString(id);
            } catch (IllegalArgumentException e) {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID not valid.");
                return;
            }
        }

        String brandName = req.getParameter("brandName");
        if (brandName != null) {
            brandName = brandName.trim().toLowerCase();
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Brand not valid.");
            return;
        }

        // Validazione data di nascita
        String birthDate = req.getParameter("birthDate");
        if (birthDate != null) {
            birthDate = birthDate.trim();
            try {
                LocalDate parsedDate = LocalDate.parse(birthDate);
                LocalDate today = LocalDate.now();
                LocalDate minDate = LocalDate.of(1900, 1, 1);

                if (parsedDate.isAfter(today) || parsedDate.isBefore(minDate)) {
                    res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Birth date not valid. It must be between 1900 and today.");
                    return;
                }
            } catch (DateTimeParseException e) {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Data format is not valid. The correct format is: YYYY-MM-DD.");
                return;
            }
        }

        // Validazione paese
        String locationCountry = req.getParameter("locationCountry");
        if (locationCountry != null) {
            locationCountry = locationCountry.trim();
            if (!GENERAL_STRING_PATTERN.matcher(locationCountry).matches()) {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Country not valid.");
                return;
            }
        }

        // Validazione città
        String locationCity = req.getParameter("locationCity");
        if (locationCity != null) {
            locationCity = locationCity.trim();
            if (!GENERAL_STRING_PATTERN.matcher(locationCity).matches()) {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "City not valid.");
                return;
            }
        }

        // Validazione codice postale
        String locationPostalCode = req.getParameter("locationPostalCode");
        if (locationPostalCode != null) {
            locationPostalCode = locationPostalCode.trim();
            if (!POSTAL_CODE_PATTERN.matcher(locationPostalCode).matches()) {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Postal code not valid.");
                return;
            }
        }

        // Validazione indirizzo
        String locationAddress = req.getParameter("locationAddress");
        if (locationAddress != null) {
            locationAddress = locationAddress.trim();
            if (!ADDRESS_PATTERN.matcher(locationAddress).matches()) {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Address not valid.");
                return;
            }
        }

        // Passa la richiesta alla catena di filtri successivi
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
