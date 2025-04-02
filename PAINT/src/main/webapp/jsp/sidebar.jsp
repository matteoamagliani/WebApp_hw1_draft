<%--
  Created by IntelliJ IDEA.
  User: nicolostefani
  Date: 02/04/25
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
      .sidebar {
        width: 250px;
        background: white;
        color: black;
        padding: 20px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        border-right: 2px solid #dcdcdc;
      }
      .sidebar a {
        text-decoration: none;
        color: black;
        display: block;
        padding: 10px;
        margin: 5px 0;
      }
      .logo {
        display: flex;
        align-items: center;
      }
      .logo img {
        width: 200px;
        height: 200px;
        margin: auto;
        align-items: center;
      }
      .user-info img {
        width: 50px;
        height: 50px;
        border-radius: 50%;
      }
    </style>
</head>
<body>
<aside class="sidebar">
  <div class="logo">
    <img src="../media/PAINT.png" alt="Logo">
  </div>
  <nav>
    <a href="home-page.jsp">🏠 Home</a>
    <a href="notifications.html">🔔 Notifications</a>
    <a href="settings.html">⚙️ Settings</a>
  </nav>
  <div class="user-info">
    <a href="user-profile.jsp?userId=<%= session.getAttribute("userID") %>" style="text-decoration: none; color: inherit;">
      <img src="profile.jpg" alt="User Image" id="user-profile">
      <p id="user-name">Name Surname</p>
      <p id="user-username">@username</p>
    </a>
  </div>
</aside>
</body>
</html>
