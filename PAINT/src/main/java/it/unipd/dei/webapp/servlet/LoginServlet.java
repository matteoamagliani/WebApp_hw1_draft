package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.dao.GetCredentialsByEmailDAO;
import it.unipd.dei.webapp.resource.Credentials;
import it.unipd.dei.webapp.resource.UserProfile;
import it.unipd.dei.webapp.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginServlet extends AbstractDatabaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter(Credentials.EMAIL_NAME);
        String password = req.getParameter(Credentials.PASSWORD_NAME_CLEAN);
    
        try {
            // Recupera le credenziali dell'utente dal database
            GetCredentialsByEmailDAO credentialsDao = new GetCredentialsByEmailDAO(getDataSource().getConnection(), email);
            Credentials credentials = credentialsDao.getCredentials();
    
            if (credentials != null && PasswordUtil.verifyPassword(password, credentials.getPassword())) {
                // Login riuscito: crea una sessione
                HttpSession session = req.getSession();
                session.setAttribute("user", credentials);
    
                // Reindirizza alla pagina principale
                res.sendRedirect("home.jsp");
            } else {
                // Credenziali non valide
                req.setAttribute("errorMessage", "Invalid email or password.");
                req.getRequestDispatcher("jsp/login.jsp").forward(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the login.");
        }
    }
}
