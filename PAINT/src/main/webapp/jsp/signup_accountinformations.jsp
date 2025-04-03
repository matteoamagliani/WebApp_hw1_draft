<%@ page import="it.unipd.dei.webapp.resource.Credentials" %>
<%@ page import="it.unipd.dei.webapp.resource.ArtisticProfile" %>
<%@ page import="it.unipd.dei.webapp.resource.UserProfile" %>
<%@ page import="it.unipd.dei.webapp.resource.ImageExtensions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Account Information</title>
</head>
<body>
    <h2>Account Information</h2>
    <form method="POST" action="../create_userprofile" enctype="multipart/form-data">
        <label>Username* <input type="text" name="<%= Credentials.USERNAME_NAME %>" required></label><br>
        <label>Email* <input type="email" name="<%= Credentials.EMAIL_NAME %>" required></label><br>
        <label>Password* <input type="password" name="<%= Credentials.PASSWORD_NAME_CLEAN %>" required></label><br>
        <label for="role">Role*</label>
        <select name="<%= ArtisticProfile.ROLE_NAME_CLEAN %>" id="role" required>
            <option value="" disabled selected>Select your role</option>
            <option value="artist">Artist</option>
            <option value="artgallery">Art Gallery</option>
            <option value="genericuser">Generic User</option>
            <option value="businessuser">Business User</option>
        </select>
        <label>Brand Name <input type="text" name="<%= UserProfile.BRAND_NAME_NAME %>"></label><br>
        <label>Profile Image <input type="file" name="<%= UserProfile.PROFILE_PICTURE_NAME %>" accept="<%= ImageExtensions.getAcceptAttribute() %>"></label><br>
        <button type="submit">Sign Up</button>
    </form>
</body>
</html>