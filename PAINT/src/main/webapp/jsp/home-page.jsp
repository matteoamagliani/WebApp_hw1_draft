<%--
  Created by IntelliJ IDEA.
  User: nicolostefani
  Date: 02/04/25
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>PAINT - Home</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      display: flex;
      height: 100vh;
    }

    .container {
      display: flex;
      width: 100%;
    }

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

    main {
      flex-grow: 1;
      padding: 20px;
    }

    .right-sidebar {
      width: 250px;
      background: white;
      padding: 20px;
      border-left: 2px solid #dcdcdc;
    }

    .search-container {
      display: flex;
      align-items: center;
    }

    .search-bar {
      flex-grow: 1;
      padding: 10px;
      margin-bottom: 10px;
    }

    .search-button {
      padding: 10px;
      background: #3498db;
      color: white;
      border: none;
      cursor: pointer;
    }

    #top-artists {
      display: flex;
      flex-direction: column;
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
  </style>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
  <!-- Sidebar -->
  <aside class="sidebar">
    <div class="logo">
      <img src="../media/PAINT.png">
    </div>
    <nav>
      <a href="home.html">🏠 Home</a>
      <br>
      <a href="notifications.html">🔔 Notifications</a>
      <br>
      <a href="settings.html">⚙️ Settings</a>
      <br>
    </nav>
    <div class="user-info">
      <img src="profile.jpg" alt="User Image" id="user-profile">
      <p id="user-name">Name Surname</p>
      <p id="user-username">@username</p>
    </div>
  </aside>

  <!-- Main Content -->
  <main>
    <h1>Home</h1>
    <div id="feed">
      <!-- Art pieces and events will be loaded here dynamically -->
    </div>
  </main>

  <!-- Right Sidebar -->
  <aside class="right-sidebar">
    <div class="search-container">
      <input type="text" placeholder="Search..." class="search-bar">
      <button class="search-button">🔍</button>
    </div>
    <h3>Recommended artists</h3>
    <div id="top-artists">
      <!-- Top 5 artists will be loaded here dynamically -->
    </div>
  </aside>
</div>
<script src="script.js"></script>
</body>
</html>