<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Help</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background: #f7f7f7;
    }
    .content {
      margin-left: 350px;
      padding: 40px;
      background: #fff;
      min-height: 100vh;
      box-sizing: border-box;
    }
    h1 {
      border-bottom: 2px solid #dcdcdc;
      padding-bottom: 10px;
    }
    h2 {
      color: #333;
    }
    p, li {
      line-height: 1.6;
      color: #555;
    }
    ul {
      margin-left: 20px;
    }
    .faq {
      margin-top: 20px;
    }
    .faq h3 {
      margin-bottom: 5px;
      color: #0073e6;
    }
    .faq p {
      margin-bottom: 15px;
    }
  </style>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<%@ include file="sidebar.jsp" %>
<div class="content">
  <h1>Help</h1>

  <h2>Frequently Asked Questions</h2>
  <div class="faq">
    <h3>How do I reset my password?</h3>
    <p>If you've forgotten your password, click on the "Forgot Password" link on the login page and follow the steps to retrieve it.</p>

    <h3>How do I update my profile information?</h3>
    <p>You can update your profile information by visiting your profile page after logging in and clicking on the Profile button.</p>
  </div>

  <h2>Contact Our Support</h2>
  <p>If you have further questions, please feel free to contact our support team via the Contact page.</p>

  <p style="margin-top:40px;">Last updated: April 2025</p>
</div>
</body>
</html>