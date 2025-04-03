<%@ page import="it.unipd.dei.webapp.resource.UserProfile" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Personal Information</title>
</head>
<body>
    <h2>Personal Information</h2>
    <form method="POST" action="../process_signup_personal_informations">
        <label>Name* <input type="text" name="<%= UserProfile.NAME_NAME_CLEAN %>" required></label><br>
        <label>Surname* <input type="text" name="<%= UserProfile.SURNAME_NAME %>" required></label><br>
        <label>Birth Date* <input type="date" name="<%= UserProfile.BIRTH_DATE_NAME %>" required></label><br>
        <button type="submit">Next</button>
    </form>
</body>
</html>