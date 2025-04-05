package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.dao.SearchUserProfilesDAO;
import it.unipd.dei.webapp.resource.UserProfile;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchUserServlet extends AbstractDatabaseServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("query");
        List<UserProfile> users = null;
        
        try {
            try (Connection con = getDataSource().getConnection()) {
                SearchUserProfilesDAO dao = new SearchUserProfilesDAO(con, query);
                users = dao.searchUserProfiles();
            }
            
            // Genera la risposta JSON
            JSONArray jsonArray = new JSONArray();
            if (users != null) {
                for (UserProfile user : users) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("id", user.getId().toString());
                    jsonObj.put("name", user.getName());
                    jsonObj.put("surname", user.getSurname());
                    jsonArray.put(jsonObj);
                }
            }
            
            response.setContentType("application/json");
            response.getWriter().write(jsonArray.toString());
        } catch (SQLException e) {
            throw new ServletException("Error searching users", e);
        }
    }
}