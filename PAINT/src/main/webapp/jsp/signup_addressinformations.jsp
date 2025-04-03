<%@ page import="it.unipd.dei.webapp.resource.Location" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Address Information</title>
</head>
<body>
    <h2>Address Information</h2>
    <form method="POST" action="../process_signup_address_informations">
        <label>Country* <input type="text" name="<%= Location.COUNTRY_NAME %>" required></label><br>
        <label>City* <input type="text" name="<%= Location.CITY_NAME %>" required></label><br>
        <label>Postal Code* <input type="text" name="<%= Location.POSTAL_CODE_NAME %>" required></label><br>
        <label>Address* <input type="text" name="<%= Location.ADDRESS_NAME %>" required></label><br>
        <button type="submit">Next</button>
    </form>
</body>
</html>