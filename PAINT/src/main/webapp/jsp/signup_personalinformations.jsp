<!-- personal_info.html -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Personal Information</title>
</head>
<body>
    <h2>Personal Information</h2>
    <form action="signup_addressinformations.jsp">
        <label>Name* <input type="text" name="name" required></label><br>
        <label>Surname* <input type="text" name="surname" required></label><br>
        <label for="role">Role*</label>
        <select name="role" id="role" required>
            <option value="" disabled selected>Select your role</option>
            <option value="artist">Artist</option>
            <option value="artgallery">Art Gallery</option>
            <option value="genericuser">Generic User</option>
            <option value="businessuser">Business User</option>
        </select>
        <label>Brand Name <input type="text" name="brand"></label><br>
        <label>Birth Date* <input type="date" name="birthdate" required></label><br>
        <label>Profile Image <input type="file" name="profile"></label><br>
        <button type="submit">Next</button>
    </form>
</body>
</html>