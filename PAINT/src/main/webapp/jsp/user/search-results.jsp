<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, java.util.Base64" %>
<%@ page import="it.unipd.dei.webapp.resource.UserProfile" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Search Results - PAINT</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f5f5f5;
    }
    .content {
      margin-left: 350px;
      padding: 20px;
    }
    h1 {
      margin-bottom: 20px;
      border-bottom: 1px solid #ddd;
      padding-bottom: 10px;
    }
    .search-results {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
      gap: 20px;
    }
    .user-card {
      background: white;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
      overflow: hidden;
      transition: transform 0.2s, box-shadow 0.2s;
    }
    .user-card:hover {
      transform: translateY(-5px);
      box-shadow: 0 5px 15px rgba(0,0,0,0.1);
    }
    .user-card a {
      text-decoration: none;
      color: inherit;
      display: flex;
      padding: 15px;
    }
    .user-card img {
      width: 70px;
      height: 70px;
      border-radius: 50%;
      object-fit: cover;
      margin-right: 15px;
    }
    .user-info {
      display: flex;
      flex-direction: column;
      justify-content: center;
    }
    .name {
      font-weight: bold;
      font-size: 1.1em;
      margin-bottom: 5px;
    }
    .brand {
      color: #666;
      font-size: 0.9em;
    }
    .no-results {
      text-align: center;
      padding: 50px;
      color: #666;
    }
    .back-link {
      display: inline-block;
      margin-top: 20px;
      color: #3498db;
      text-decoration: none;
    }
    .back-link:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
  <%@ include file="sidebar.jsp" %>
  
  <div class="content">
    <h1>Search Results</h1>
    
    <%
      //check error code
      String error = (String) request.getAttribute("error");
      if (error != null) {
    %>
      <div class="no-results">
        <p><%= error %></p>
        <a href="<%= request.getContextPath() %>/jsp/user/home-page.jsp" class="back-link">Back to Home</a>
      </div>
    <%
      } else {
        //retrieve search results
        String query = (String) request.getAttribute("query");
        List<UserProfile> users = (List<UserProfile>) request.getAttribute("users");
        
        if (query == null || users == null) {
    %>
      <div class="no-results">
        <p>query null</p>
        <a href="<%= request.getContextPath() %>/jsp/user/home-page.jsp" class="back-link">Back to Home</a>
      </div>
    <%
        } else if (users.isEmpty()) {
    %>
      <div class="no-results">
        <p>No results found for: <strong><%= query %></strong></p>
        <a href="<%= request.getContextPath() %>/jsp/user/home-page.jsp" class="back-link">Back to Home</a>
      </div>
    <%
        } else {
    %>
      <p>Found <%= users.size() %> results for: <strong><%= query %></strong></p>
      <div class="search-results">
    <%
          for (UserProfile user : users) {
            String profilePicUrl = "";
            byte[] profilePic = user.getProfilePicture();
            if (profilePic != null && profilePic.length > 0) {
              String base64Image = Base64.getEncoder().encodeToString(profilePic);
              profilePicUrl = "data:image/" + user.getPictureExtension() + ";base64," + base64Image;
            } else {
              profilePicUrl = request.getContextPath() + "/media/profile_placeholder.jpg";
            }
    %>
        <div class="user-card">
          <a href="profile.jsp?id=<%= user.getId() %>">
            <img src="<%= profilePicUrl %>" alt="<%= user.getName() %>">
            <div class="user-info">
              <span class="name"><%= user.getName() %> <%= user.getSurname() %></span>
              <% if (user.getBrandName() != null && !user.getBrandName().isEmpty()) { %>
                <span class="brand"><%= user.getBrandName() %></span>
              <% } %>
            </div>
          </a>
        </div>
    <%
          }
    %>
      </div>
      <a href="<%= request.getContextPath() %>/jsp/user/home-page.jsp" class="back-link">Back to Home</a>
    <%
        }
      }
    %>
  </div>
</body>
</html>