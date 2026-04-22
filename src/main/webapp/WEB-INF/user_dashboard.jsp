<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pet Care Hub</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dashboard_style.css">
</head>
<body>

    <div class="hero-header">
        <div class="circle-decor c1"></div>
        <div class="circle-decor c2"></div>
        
        <h1 class="hero-title">Hello, <%= session.getAttribute("user") %>! 🐾</h1>
        <p class="hero-subtitle">Welcome to TailsTogether.</p>
    </div>

    <div class="dashboard-container">
        
        <div class="card-grid">
            
            <a href="${pageContext.request.contextPath}/bookAppointment" class="menu-card">
                <div class="card-icon-box icon-purple">
                    📅
                </div>
                <div class="card-title">Book Appointment</div>
                <div class="card-desc">Schedule a new visit for your pet</div>
            </a>

            <a href="${pageContext.request.contextPath}/myHistory" class="menu-card">
                <div class="card-icon-box icon-orange">
                    📋
                </div>
                <div class="card-title">My History</div>
                <div class="card-desc">View your past and upcoming visits</div>
            </a>

            <a href="${pageContext.request.contextPath}/logout" class="menu-card">
                <div class="card-icon-box icon-red">
                    🚪
                </div>
                <div class="card-title">Logout</div>
                <div class="card-desc">Sign out of your account</div>
            </a>

        </div> </div>

</body>
</html>