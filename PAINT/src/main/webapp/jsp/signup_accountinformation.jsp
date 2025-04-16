<!-- account_info.html -->
<%@ page import="it.unipd.dei.webapp.ID" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Account Information</title>
</head>
<body>
    <h2>Account Information</h2>
    <form method="POST" action="../create_userprofile">
        <label>Username* <input type="text" name="<%= ID.USERNAME_ID %>" required></label><br>
        <label>Email* <input type="email" name="<%= ID.EMAIL_ID %>" required></label><br>
        <label>Password* <input type="password" name="<%= ID.PASSWORD_ID %>" required></label><br>
        <label for="role">Role*</label>
        <select name="<%= ID.ROLE_ID %>" id="role" required>
            <option value="" disabled selected>Select your role</option>
            <option value="artist">Artist</option>
            <option value="artgallery">Art Gallery</option>
            <option value="genericuser">Generic User</option>
            <option value="businessuser">Business User</option>
        </select>
        <label>Brand Name <input type="text" name="<%= ID.BRAND_NAME_ID %>"></label><br>
        <label>Profile Image <input type="file" name="<%= ID.PROFILE_IMAGE_ID %>"></label><br>
        <button type="submit">Sign Up</button>
    </form>
</body>
</html>