package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.dao.SearchArtistsAndGalleriesDAO;
import it.unipd.dei.webapp.resource.UserProfile;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchUserServlet extends AbstractDatabaseServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String query = request.getParameter("query");
        
        if (query == null || query.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/jsp/user/home-page.jsp");
            return;
        }
        
        List<UserProfile> users = new ArrayList<>();
        
        try (Connection con = getDataSource().getConnection()) {
            SearchArtistsAndGalleriesDAO dao = new SearchArtistsAndGalleriesDAO(con, query);
            users = dao.searchUsers();
            
            request.setAttribute("users", users);
            request.setAttribute("query", query);
            
            request.getRequestDispatcher("/jsp/user/search-results.jsp").forward(request, response);
            
        } catch (SQLException e) {
            logger.error("Database error during search", e);
            
            request.setAttribute("error", "Error during search: " + e.getMessage());
            request.getRequestDispatcher("/jsp/user/search-results.jsp").forward(request, response);
        }
    }
}