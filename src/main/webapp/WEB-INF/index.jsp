<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pet Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            margin: 80px auto;
            width: 60%;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.2);
            text-align: center;
        }
        h1 {
            margin-bottom: 20px;
            color: #333;
        }
        a.btn {
            display: block;
            margin: 12px auto;
            width: 60%;
            padding: 12px;
            background: #2a8ef7;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-size: 18px;
        }
        a.btn:hover {
            background: #1f6ec4;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>🐾 Pet Management System</h1>
    <p>Welcome! Choose an option below to get started.</p>

    <!-- Links to pages -->
    <a class="btn" href="appointment.jsp">Book an Appointment</a>
    <a class="btn" href="appointments">View All Appointments</a>

    <a class="btn" href="addPet.jsp">Add a New Pet</a>
    <a class="btn" href="pets">View Pets</a>
</div>

</body>
</html>
