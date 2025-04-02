package it.unipd.dei.webapp.servlet;

import it.util.PasswordUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (Connection con = getDataSource().getConnection()) {
            String query = "SELECT Password FROM paint.Credentials WHERE Email = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("Password");

                if (PasswordUtil.verifyPassword(password, hashedPassword)) {
                    res.getWriter().println("Login successful!");
                } else {
                    res.getWriter().println("Invalid credentials.");
                }
            } else {
                res.getWriter().println("User not found.");
            }
        } catch (Exception e) {
            res.getWriter().println("An error occurred: " + e.getMessage());
        }
    }
}
