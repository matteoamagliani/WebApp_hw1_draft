<!-- address_info.html -->
<%@ page import="it.unipd.dei.webapp.ID" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Address Information</title>
</head>
<body>
    <h2>Address Information</h2>
    <form method="POST" action="../process_signup_address_informations">
        <label>Country* <input type="text" name="<%= ID.COUNTRY_ID %>" required></label><br>
        <label>City* <input type="text" name="<%= ID.CITY_ID %>" required></label><br>
        <label>AU Code* <input type="text" name="<%= ID.AU_CODE_ID %>" required></label><br>
        <label>Address* <input type="text" name="<%= ID.ADDRESS_ID %>" required></label><br>
        <button type="submit">Next</button>
    </form>
</body>
</html>