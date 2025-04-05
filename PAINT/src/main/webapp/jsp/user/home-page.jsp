<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, java.util.UUID" %>
<%@ page import="it.unipd.dei.webapp.resource.Content" %>
<%@ page import="it.unipd.dei.webapp.resource.UserProfile" %>

<%
  //load data if not already loaded
  if (request.getAttribute("contents") == null && 
      request.getAttribute("recommendedUsers") == null && 
      request.getAttribute("loadedByServlet") == null) {
%>
    <jsp:include page="/home-content" />
<%
  }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>PAINT - Home</title>
  <style>
    html, body {
      margin: 0;
      padding: 0;
      height: 100%;
      overflow: hidden; 
      font-family: Arial, sans-serif;
    }

    .right-sidebar {
      position: fixed;
      top: 0;
      right: 0;
      bottom: 0;
      width: 350px;
      background: white;
      border-left: 2px solid #dcdcdc;
      padding: 20px;
      box-sizing: border-box;
      display: flex;
      flex-direction: column;
    }

    main {
      position: absolute;
      top: 0;
      bottom: 0;
      left: 350px;
      right: 350px;
      overflow-y: auto;
      padding: 20px;
      box-sizing: border-box;
    }

    #feed .content-item {
      border-bottom: 1px solid #dcdcdc;
      padding-bottom: 15px;
      margin-bottom: 15px;
    }
    #feed img {
      max-width: 100%;
      height: auto;
      display: block;
      margin-top: 10px;
    }

    .search-container {
      display: flex;
      align-items: center;
      margin-bottom: 20px;
    }
    .search-form {
      display: flex;
      width: 100%;
    }
    .search-input {
      flex-grow: 1;
      padding: 10px;
      border: 1px solid #dcdcdc;
      border-right: none;
      border-radius: 4px 0 0 4px;
      outline: none;
    }
    .search-button {
      padding: 10px 15px;
      background: #3498db;
      color: white;
      border: none;
      border-radius: 0 4px 4px 0;
      cursor: pointer;
    }
    #top-artists {
      flex-grow: 1;
      overflow-y: auto;
      margin-top: 10px;
    }
    .artist {
      display: flex;
      align-items: center;
      margin-bottom: 10px;
    }
    .artist img {
      width: 40px;
      height: 40px;
      border-radius: 50%;
      margin-right: 10px;
    }
    .right-footer {
      text-align: center;
      font-size: 0.9em;
      color: gray;
      padding-top: 10px;
      border-top: 1px solid #dcdcdc;
    }
    .right-footer a {
      color: gray;
      text-decoration: none;
      margin-right: 8px;
    }
    .right-footer p {
      margin-top: 10px;
    }
  </style>
  <link rel="stylesheet" href="styles.css">
</head>
<body>

<%@ include file="sidebar.jsp" %>

<main>
  <h1>Home</h1>
  <div id="feed">
    <%
      List<Content> contents = (List<Content>) request.getAttribute("contents");
      String errorContents = (String) request.getAttribute("errorContents");
      
      if (errorContents != null) {
    %>
      <p>Error: <%= errorContents %></p>
    <%
      } else if (contents != null && !contents.isEmpty()) {
        Collections.sort(contents, new Comparator<Content>() {
          public int compare(Content c1, Content c2) {
            return c2.getUploadDate().compareTo(c1.getUploadDate());
          }
        });

        for(Content item : contents) {
    %>
    <div class="content-item">
      <h2><%= item.getTitle() %> (<%= item.getType() %>)</h2>
      <p><%= item.getDescription() %></p>
      <p>Data: <%= item.getUploadDate() %></p>
      <%
          if(item.getImageData() != null) {
            String base64Image = Base64.getEncoder().encodeToString(item.getImageData());
      %>
      <img src="data:image/<%= item.getExtension() %>;base64,<%= base64Image %>" alt="<%= item.getTitle() %>">
      <%
          }
        } // fine for
      } else {
      %>
      <p>No content available or user session not active.</p>
      <%
      }
    %>
  </div>
</main>

<aside class="right-sidebar">
  <div style="flex: 1;">
    <div class="search-container">
      <form class="search-form" action="<%= request.getContextPath() %>/search-users" method="GET">
        <input type="text" name="query" class="search-input" placeholder="Search artists and galleries..." required minlength="2">
        <button type="submit" class="search-button">Search</button>
      </form>
    </div>
    
    <h3>Recommended artists</h3>
    <div id="top-artists" style="overflow-y: auto; max-height: calc(100% - 200px);">
      <%
        List<UserProfile> recommendedUsers = (List<UserProfile>) request.getAttribute("recommendedUsers");
        String errorRecommended = (String) request.getAttribute("errorRecommended");
        
        if (errorRecommended != null) {
      %>
          <p>Error loading recommended users: <%= errorRecommended %></p>
      <%
        } else if (recommendedUsers != null && !recommendedUsers.isEmpty()) {
          for(UserProfile user : recommendedUsers) {
      %>
      <div class="artist">
        <%
            byte[] profilePic = user.getProfilePicture();
            String base64Image = (profilePic != null) ? Base64.getEncoder().encodeToString(profilePic) : "";
        %>
        <img src="<%= (!base64Image.isEmpty() ? "data:image/" + user.getPictureExtension() + ";base64," + base64Image : "../../media/profile_placeholder.jpg") %>" alt="<%= user.getName() %>">
        <span><%= user.getName() %> <%= user.getSurname() %></span>
      </div>
      <%
          }
        } else {
      %>
          <p>No recommended artists available at the moment.</p>
      <%
        }
      %>
    </div>
  </div>
  <!-- footer pages -->
  <footer class="right-footer" style="margin-top: auto; text-align: center; font-size: 0.9em; color: gray; padding-top: 10px; border-top: 1px solid #dcdcdc;">
    <a href="terms.jsp" style="color:gray; text-decoration:none; margin-right:8px;">Terms & Conditions</a>
    <a href="privacy.jsp" style="color:gray; text-decoration:none; margin-right:8px;">Privacy</a>
    <a href="help.jsp" style="color:gray; text-decoration:none; margin-right:8px;">Help</a>
    <a href="contact.jsp" style="color:gray; text-decoration:none;">Contact</a>
    <p style="margin-top:10px;">© 2025 PAINT, Inc.</p>
  </footer>
</aside>
</body>
</html>