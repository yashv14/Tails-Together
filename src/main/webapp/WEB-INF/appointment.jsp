<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // CSRF Token Logic
    String csrf = (String) session.getAttribute("csrfToken");
    if (csrf == null) {
        csrf = java.util.UUID.randomUUID().toString();
        session.setAttribute("csrfToken", csrf);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Book Appointment</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        /* Header Navigation */
        .card-header {
            display: flex;
            justify-content: space-between; /* Keeps spacing even if one item is removed */
            align-items: center;
            margin-bottom: 25px;
            padding-bottom: 15px;
            border-bottom: 1px solid #eee;
        }
        .nav-link { text-decoration: none; font-size: 14px; font-weight: 500; }
        .link-home { color: #4CAF50; }
        
        /* Success Box Buttons */
        .success-actions {
            margin-top: 15px;
            display: flex;
            gap: 10px;
            justify-content: center;
        }
        .btn-small {
            padding: 8px 15px;
            text-decoration: none;
            border-radius: 4px;
            font-size: 13px;
            font-weight: bold;
            color: white;
            display: inline-block;
        }
        .btn-green { background-color: #28a745; } /* Dashboard Button */
        .btn-small:hover { opacity: 0.9; }
    </style>
</head>
<body>

<div class="container">
    <div class="form-card">
        
        <div class="card-header">
            <a href="${pageContext.request.contextPath}/userDashboard" class="nav-link link-home">&larr; Back to Dashboard</a>
            
            </div>

        <h1>Make an Appointment</h1>
        <p class="subtitle">Schedule a vet consultation, grooming session, or daycare slot.</p>

        <% if ("1".equals(request.getParameter("success"))) { %>
            <div class="alert alert-success">
                <h3 style="margin: 0; color: #155724;">✅ Appointment Booked!</h3>
                <p style="margin: 5px 0;">Your request has been saved successfully.</p>
                
                <div class="success-actions">
                    <a href="${pageContext.request.contextPath}/userDashboard" class="btn-small btn-green">Go to Dashboard</a>
                </div>
            </div>
        <% } else if ("1".equals(request.getParameter("error"))) { %>
            <div class="alert alert-danger">Something went wrong. Please try again.</div>
        <% } %>

        <form action="${pageContext.request.contextPath}/bookAppointment" method="post">
            <input type="hidden" name="csrfToken" value="<%= csrf %>" />

            <div class="form-group">
                <label for="ownerName">Your Name</label>
                <input type="text" id="ownerName" name="ownerName" class="form-control" placeholder="Enter your full name" required>
            </div>

            <div class="form-group">
                <label for="petName">Pet Name</label>
                <input type="text" id="petName" name="petName" class="form-control" placeholder="Enter pet's name" required>
            </div>

            <div class="form-group">
                <label for="service">Service</label>
                <select id="service" name="service" class="form-control" required>
                    <option value="">-- Select Service --</option>
                    <option>Veterinary Checkup</option>
                    <option>Pet Grooming</option>
                    <option>Pet Daycare</option>
                </select>
            </div>

            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="apptDate">Date</label>
                    <input type="date" id="apptDate" name="apptDate" class="form-control" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="apptTime">Time</label>
                    <input type="time" id="apptTime" name="apptTime" class="form-control" required>
                </div>
            </div>

            <div class="form-group">
                <label for="notes">Notes</label>
                <textarea id="notes" name="notes" class="form-control" rows="4" placeholder="Any special requests..."></textarea>
            </div>

            <button type="submit" class="btn-submit">Book Appointment</button>
        </form>
    </div>
</div>

</body>
</html>