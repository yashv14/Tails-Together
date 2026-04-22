<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Account - TailsTogether</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register_style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
</head>
<body>

    <div class="split-card">
        
        <div class="image-side">
            <div class="image-text">
                <h2>Join the Family! 🐶</h2>
                <p>Create an account to manage your pet's health and appointments easily.</p>
            </div>
        </div>

        <div class="form-side">
            <h1 class="brand-title">Tails<span>Together</span></h1>
            <p class="form-subtitle">It only takes a minute to get started.</p>

            <% if (request.getAttribute("errorMessage") != null) { %>
                <div class="error-box">
                    <%= request.getAttribute("errorMessage") %>
                </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/register" method="post">
                
                <input type="text" name="username" class="modern-input" placeholder="Choose a Username" required>

                <input type="password" name="password" class="modern-input" placeholder="Create Password" required>

                <input type="password" name="confirmPassword" class="modern-input" placeholder="Confirm Password" required>

                <button type="submit" class="btn-gradient">Sign Up</button>

            </form>

            <div class="form-footer">
                Already have an account? <a href="${pageContext.request.contextPath}/login">Log In</a>
            </div>
        </div>

    </div>

</body>
</html>