<%--
  Created by IntelliJ IDEA.
  User: nicolostefani
  Date: 02/04/25
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, java.sql.*, java.util.UUID" %>
<%@ page import="it.unipd.dei.webapp.dao.Homepage" %>
<%@ page import="it.unipd.dei.webapp.resource.Content" %>
<%@ page import="it.unipd.dei.webapp.resource.UserProfile" %>
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
      overflow: hidden; /* Evita scroll sull'intera pagina */
      font-family: Arial, sans-serif;
    }

    /* Sidebar destra fissa */
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
    /* Area centrale scrollabile */
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
    /* Contenuti interni */
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
    /* Right sidebar: search & recommended artists */
    .search-container {
      display: flex;
      align-items: center;
      margin-bottom: 10px;
    }
    .search-bar {
      flex-grow: 1;
      padding: 10px;
      margin-bottom: 0;
      box-sizing: border-box;
    }
    .search-button {
      padding: 10px;
      background: #3498db;
      color: white;
      border: none;
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
    /* Footer link: for right sidebar, fissato in basso */
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
    /* Modal per la ricerca */
    .modal {
      display: none;
      position: fixed;
      z-index: 1000;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      overflow: auto;
      background-color: rgba(0,0,0,0.5);
    }
    .modal-content {
      background-color: #fefefe;
      margin: 10% auto;
      padding: 20px;
      border: 1px solid #888;
      width: 80%;
    }
    #close-modal {
      float: right;
      font-size: 28px;
      font-weight: bold;
      cursor: pointer;
    }
  </style>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<!-- La sidebar sinistra viene inclusa -->
<%@ include file="sidebar.jsp" %>
<!-- Area centrale -->
<main>
  <h1>Home</h1>
  <div id="feed">
    <%
      // Recupera l'ID utente dalla sessione e mostra i contenuti (artpiece ed eventi)
      String userIdStr = (String) session.getAttribute("userID");
      if(userIdStr != null) {
        UUID userId = UUID.fromString(userIdStr);
        Homepage dao = new Homepage();
        List<Content> contents = dao.getUserContent(userId);

        // Ordina i contenuti in ordine cronologico decrescente
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
      %>
    </div>
    <%
      } // fine for
    } else {
    %>
    <p>User session not available.</p>
    <%
      }
    %>
  </div>
</main>
<!-- Sidebar destra -->
<aside class="right-sidebar">
  <div style="flex: 1;">
    <div class="search-container">
      <input type="text" placeholder="Search..." class="search-bar">
      <button class="search-button">🔍</button>
    </div>
    <!-- Modal per i risultati della ricerca -->
    <div id="search-modal" class="modal">
      <div class="modal-content">
        <span id="close-modal">&times;</span>
        <div id="modal-results"></div>
      </div>
    </div>
    <h3>Recommended artists</h3>
    <div id="top-artists" style="overflow-y: auto; max-height: calc(100% - 200px);">
      <%
        try {
          Homepage dao = new Homepage();
          List<UserProfile> recommendedUsers = dao.getRecommendedUsers();
          for(UserProfile user : recommendedUsers) {
      %>
      <div class="artist">
        <%
          byte[] profilePic = user.getProfilePicture();
          String base64Image = (profilePic != null) ? Base64.getEncoder().encodeToString(profilePic) : "";
        %>
        <img src="<%= (!base64Image.isEmpty() ? "data:image/" + user.getPictureExtension() + ";base64," + base64Image : "profile_placeholder.jpg") %>" alt="<%= user.getName() %>">
        <span><%= user.getName() %> <%= user.getSurname() %></span>
      </div>
      <%
          }
        } catch(Exception ex) {
          out.println("<p>Error loading recommended users.</p>");
        }
      %>
    </div>
  </div>
  <!-- Footer links e la scritta PAINT, Inc., ancorate in fondo -->
  <footer class="right-footer" style="margin-top: auto; text-align: center; font-size: 0.9em; color: gray; padding-top: 10px; border-top: 1px solid #dcdcdc;">
    <a href="terms.jsp" style="color:gray; text-decoration:none; margin-right:8px;">Terms & Conditions</a>
    <a href="privacy.jsp" style="color:gray; text-decoration:none; margin-right:8px;">Privacy</a>
    <a href="help.jsp" style="color:gray; text-decoration:none; margin-right:8px;">Help</a>
    <a href="contact.jsp" style="color:gray; text-decoration:none;">Contact</a>
    <p style="margin-top:10px;">© 2025 PAINT, Inc.</p>
  </footer>
  <script>
    const searchBar = document.querySelector('.search-bar');
    const searchButton = document.querySelector('.search-button');
    const modal = document.getElementById('search-modal');
    const modalResults = document.getElementById('modal-results');
    const closeModal = document.getElementById('close-modal');

    function doSearch() {
      const query = searchBar.value.trim();
      if(query.length < 2) {
        modalResults.innerHTML = '<p>Inserisci almeno 2 caratteri.</p>';
        modal.style.display = 'block';
        return;
      }
      fetch('search-user?query=' + encodeURIComponent(query))
              .then(response => response.json())
              .then(data => {
                modalResults.innerHTML = '';
                if(data.length === 0) {
                  modalResults.innerHTML = '<p>Nessun risultato trovato.</p>';
                }
                data.forEach(user => {
                  const div = document.createElement('div');
                  div.className = 'artist';
                  div.innerHTML = '<span>' + user.name + ' ' + user.surname + '</span>';
                  modalResults.appendChild(div);
                });
                modal.style.display = 'block';
              })
              .catch(error => {
                console.error('Error fetching search results:', error);
                modalResults.innerHTML = '<p>Errore nel caricamento dei risultati.</p>';
                modal.style.display = 'block';
              });
    }

    searchBar.addEventListener('keyup', function(event) {
      if(event.key === "Enter") {
        doSearch();
      }
    });

    searchButton.addEventListener('click', doSearch);

    closeModal.addEventListener('click', function(){
      modal.style.display = 'none';
    });

    window.addEventListener('click', function(event) {
      if(event.target == modal) {
        modal.style.display = 'none';
      }
    });
  </script>
</aside>
<script src="script.js"></script>
</body>
</html>