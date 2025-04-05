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
      position: fixed;
      top: 0;
      left: 0;
      bottom: 0;
      width: 350px;
      background: white;
      border-right: 2px solid #dcdcdc;
      padding: 20px;
      box-sizing: border-box;
      overflow-y: auto;
      display: flex;
      flex-direction: column;
    }
    .sidebar a {
      text-decoration: none;
      color: black;
      display: flex;
      align-items: center;
      padding: 10px;
      margin: 5px 0;
    }
    .sidebar a img {
      width: 24px;
      height: 24px;
      margin-right: 8px;
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
    .user-info {
      margin-top: auto;
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
    <img src="<%= request.getContextPath() %>/media/PAINT.png" alt="Logo">
  </div>
  <nav>
    <a href="<%= request.getContextPath() %>/jsp/user/home-page.jsp">
      <img src="<%= request.getContextPath() %>/media/home_icon.png" alt="Home"> Home
    </a>
    <a href="<%= request.getContextPath() %>/notifications.html">
      <img src="<%= request.getContextPath() %>/media/notifications_icon.png" alt="Notifications"> Notifications
    </a>
    <a href="<%= request.getContextPath() %>/settings.html">
      <img src="<%= request.getContextPath() %>/media/settings_icon.png" alt="Settings"> Settings
    </a>
  </nav>
  <div class="user-info">
    <a href="<%= request.getContextPath() %>/user-profile.jsp?userId=<%= session.getAttribute("userID") %>" style="text-decoration: none; color: inherit;">
      <img src="profile.jpg" alt="User Image" id="user-profile">
      <p id="user-name">Name Surname</p>
      <p id="user-username">@username</p>
    </a>
  </div>
</aside>
</body>
</html>