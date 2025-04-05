package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.dao.GetRecommendedUsersDAO;
import it.unipd.dei.webapp.dao.GetUserContentDAO;
import it.unipd.dei.webapp.resource.Content;
import it.unipd.dei.webapp.resource.UserProfile;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeContentServlet extends AbstractDatabaseServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String userIdStr = (String) session.getAttribute("userID");
        
        List<Content> contents = new ArrayList<>();
        List<UserProfile> recommendedUsers = new ArrayList<>();
        
        if (userIdStr != null) {
            try (Connection con = getDataSource().getConnection()) {
                UUID userId = UUID.fromString(userIdStr);
                
                GetUserContentDAO dao = new GetUserContentDAO(con, userId);
                contents = dao.getUserContent();
                

                request.setAttribute("contents", contents);
                
            } catch (SQLException e) {
                logger.error("Error retrieving content for user", e);
                request.setAttribute("errorContents", "Error retrieving contents: " + e.getMessage());
            }
        }
        
        try (Connection con = getDataSource().getConnection()) {
            GetRecommendedUsersDAO dao = new GetRecommendedUsersDAO(con);
            recommendedUsers = dao.getRecommendedUsers();
            
            request.setAttribute("recommendedUsers", recommendedUsers);
            
        } catch (SQLException e) {
            logger.error("Error retrieving recommended artists", e);
            request.setAttribute("errorRecommended", "Error retrieving recommended artists: " + e.getMessage());
        }
        
        request.setAttribute("loadedByServlet", true);
        
        request.getRequestDispatcher("/jsp/user/home-page.jsp").forward(request, response);
    }
}