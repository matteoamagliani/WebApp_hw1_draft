package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.dao.Homepage;
import it.unipd.dei.webapp.resource.UserProfile;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("query");
        Homepage dao = new Homepage();
        List<UserProfile> users;
        try {
            users = dao.searchUserProfiles(query);
        } catch (SQLException e) {
            throw new ServletException("Error searching users", e);
        }
        JSONArray jsonArray = new JSONArray();
        for (UserProfile user : users) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("id", user.getId().toString());
            jsonObj.put("name", user.getName());
            jsonObj.put("surname", user.getSurname());
            // Aggiungi altri campi se necessario
            jsonArray.put(jsonObj);
        }
        response.setContentType("application/json");
        response.getWriter().write(jsonArray.toString());
    }
}