<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Contact</title>
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
      margin-top: 20px;
    }
    p, li {
      line-height: 1.6;
      color: #555;
    }
    ul {
      margin-left: 20px;
    }
  </style>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
  <%@ include file="sidebar.jsp" %>
  <div class="content">
    <h1>Contact Us</h1>
    <p>If you have any questions or need further assistance, please refer to the contact information below. Our support team is on hand to help during business hours.</p>
    
    <h2>Contact Information</h2>
    <p><strong>Email:</strong> support@example.com</p>
    <p><strong>Phone:</strong> +1 (234) 567-890</p>
    <p><strong>Address:</strong> 123 Main Street, City, Country</p>
    
    <h2>Business Hours</h2>
    <p>Monday - Friday: 9:00 AM to 6:00 PM</p>
    <p>Saturday: 10:00 AM to 4:00 PM</p>
    <p>Sunday: Closed</p>
    
    <h2>Additional Information</h2>
    <p>For further inquiries, please email us at the address provided or call our customer support hotline. We strive to respond to all queries within 24 hours on business days.</p>
    
    <p style="margin-top:40px;">Last updated: April 2025</p>
  </div>
</body>