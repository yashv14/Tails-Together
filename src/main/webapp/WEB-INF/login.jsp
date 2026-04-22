<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - TailsTogether</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login_style.css">
    
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
</head>
<body> 
    <div class="split-card">
        
        <div class="image-side">
            <div class="image-text">
                <h2>Your Pet's Happy Place 🐾</h2>
                <p>Join thousands of happy pets and their owners!</p>
            </div>
        </div>

        <div class="form-side">
            <h1 class="brand-title">Tails<span>Together</span></h1>
            <p class="form-subtitle">Welcome back! Please login to continue.</p>

            <% if (request.getAttribute("errorMessage") != null) { %>
                <div style="background:#fee; color: #c00; padding: 10px; border-radius: 8px; font-size: 13px; text-align: center; margin-bottom: 15px;">
                    <%= request.getAttribute("errorMessage") %>
                </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/login" method="post">
                
                <input type="text" name="username" class="modern-input" placeholder="Username" required>

                <input type="password" name="password" class="modern-input" placeholder="Password" required>

                <button type="submit" class="btn-gradient">Login</button>

            </form>

            <div class="form-footer">
                Don't have an account? <a href="${pageContext.request.contextPath}/register">Create Account</a>
            </div>
            
             <% if ("1".equals(request.getParameter("registered"))) { %>
                <div style="color: #28a745; font-size: 13px; text-align: center; margin-top: 10px;">
                    Account created! You can now login.
                </div>
            <% } %>
        </div>

    </div>

</body>
</html>