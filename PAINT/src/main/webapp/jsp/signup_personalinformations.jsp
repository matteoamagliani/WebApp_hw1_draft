<%@ page import="it.unipd.dei.webapp.ID" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Personal Information</title>
</head>
<body>
    <h2>Personal Information</h2>
    <form method="POST" action="../process_signup_personal_informations">
        <label>Name* <input type="text" name="<%= ID.NAME_ID %>" required></label><br>
        <label>Surname* <input type="text" name="<%= ID.SURNAME_ID %>" required></label><br>
        <label>Birth Date* <input type="date" name="<%= ID.BIRTHDATE_ID %>" required></label><br>
        <button type="submit">Next</button>
    </form>
</body>
</html>