<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="pet_manage.model.Appointment" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Appointments</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body { font-family: 'Poppins', sans-serif; background-color: #f4f7fc; }
        
        /* Card Layout */
        .container { display: flex; justify-content: center; padding-top: 50px; }
        .form-card {
            background: white; padding: 40px; border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.05); width: 100%; max-width: 900px;
        }

        /* Header */
        .header-row { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .back-link { color: #4CAF50; text-decoration: none; font-weight: bold; font-size: 14px; }
        .back-link:hover { text-decoration: underline; }

        /* Table Styling */
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th { text-align: left; padding: 15px; background-color: #4CAF50; color: white; text-transform: uppercase; font-size: 13px; font-weight: 700; }
        td { padding: 15px; border-bottom: 1px solid #eee; color: #444; font-size: 14px; vertical-align: middle; }
        tr:hover { background-color: #f9f9f9; }

        /* DYNAMIC STATUS BADGES */
        .status-badge { padding: 6px 12px; border-radius: 20px; font-size: 12px; font-weight: bold; display: inline-block; }
        
        .status-pending { 
            background: #fff3cd; 
            color: #856404; 
            border: 1px solid #ffeeba; 
        }
        
        .status-confirmed { 
            background: #d4edda; 
            color: #155724; 
            border: 1px solid #c3e6cb; 
        }

        /* Cancel Button */
        .btn-cancel {
            background-color: #e74c3c; color: white; border: none;
            padding: 8px 15px; border-radius: 5px; cursor: pointer;
            font-size: 13px; font-weight: bold; transition: 0.2s;
        }
        .btn-cancel:hover { background-color: #c0392b; }
    </style>
</head>
<body>

<div class="container">
    <div class="form-card">
        
        <div class="header-row">
            <h1 style="margin:0; color:#333;">My Appointments</h1>
            <a href="${pageContext.request.contextPath}/userDashboard" class="back-link">&larr; Back to Dashboard</a>
        </div>

        <% if ("cancelled".equals(request.getParameter("msg"))) { %>
            <div style="background:#d4edda; color:#155724; padding:10px; border-radius:5px; margin-bottom:15px;">
                ✅ Appointment cancelled successfully.
            </div>
        <% } %>

        <% 
        List<Appointment> list = (List<Appointment>) request.getAttribute("myList");
        if (list == null || list.isEmpty()) { 
        %>
            <div style="text-align:center; padding:40px; color:#777; background:#fafafa; border-radius:10px;">
                <h3>No appointments found.</h3>
                <p>You haven't booked any visits yet.</p>
                <a href="${pageContext.request.contextPath}/bookAppointment" style="color:#4CAF50; font-weight:bold;">Book Now</a>
            </div>
        <% } else { %>

            <table border="0">
                <thead>
                    <tr>
                        <th>Date & Time</th>
                        <th>Pet Name</th>
                        <th>Service</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Appointment a : list) { %>
                    <tr>
                        <td>
                            <div style="font-weight:600;"><%= a.getApptDate() %></div>
                            <div style="color:#888; font-size:12px;"><%= a.getApptTime() %></div>
                        </td>
                        <td style="font-weight:bold;"><%= a.getPetName() %></td>
                        <td><%= a.getService() %></td>
                        
                        <td>
                            <% if ("Pending".equalsIgnoreCase(a.getStatus()) || a.getStatus() == null) { %>
                                <span class="status-badge status-pending">Pending</span>
                            <% } else { %>
                                <span class="status-badge status-confirmed">Confirmed</span>
                            <% } %>
                        </td>

                        <td>
                            <form action="${pageContext.request.contextPath}/myHistory" method="post" onsubmit="return confirm('Cancel this appointment?');">
                                <input type="hidden" name="action" value="cancel">
                                <input type="hidden" name="id" value="<%= a.getId() %>">
                                <button type="submit" class="btn-cancel">Cancel</button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        <% } %>
    </div>
</div>

</body>
</html>