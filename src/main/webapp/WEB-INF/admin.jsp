<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="pet_manage.model.Appointment" %>
<%-- Removed unused Review import --%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dashboard_style.css">
    
    <style>
        /* --- ADMIN PANEL STYLES --- */
        .admin-panel { background: white; padding: 30px; border-radius: 20px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); margin-bottom: 40px; }

        /* Stats Grid */
        .stats-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 20px; margin-bottom: 30px; }
        .stat-card { background: #f8f9fa; padding: 20px; border-radius: 15px; display: flex; justify-content: space-between; align-items: center; border-left: 5px solid #ccc; }
        .card-blue { border-color: #667eea; background: #eef2ff; }
        .card-yellow { border-color: #f6c23e; background: #fffdf0; }
        .card-green { border-color: #1cc88a; background: #f0fff4; }
        .stat-title { font-size: 12px; font-weight: bold; text-transform: uppercase; color: #666; margin-bottom: 5px; }
        .stat-value { font-size: 28px; font-weight: bold; color: #333; }

        /* Search Bar */
        .search-container { margin-bottom: 20px; display: flex; justify-content: space-between; align-items: center; }
        .search-form { display: flex; gap: 10px; }
        .search-input { padding: 10px 15px; border: 1px solid #ddd; border-radius: 20px; outline: none; width: 250px; }
        .btn-purple { background: #667eea; color: white; padding: 8px 20px; border-radius: 20px; border: none; cursor: pointer; }

        /* Table */
        .table-container { overflow-x: auto; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th { text-align: left; padding: 15px; color: #666; font-size: 13px; text-transform: uppercase; border-bottom: 2px solid #eee; }
        td { padding: 15px; border-bottom: 1px solid #eee; color: #444; font-size: 14px; }

        /* Status Badges */
        .badge-status { padding: 5px 10px; border-radius: 12px; font-size: 11px; font-weight: bold; }
        .b-pending { background: #fff3cd; color: #856404; }
        .b-confirmed { background: #d4edda; color: #155724; }

        /* Buttons */
        .action-btn { border: none; padding: 6px 12px; border-radius: 6px; cursor: pointer; color: white; }
        .btn-check { background: #28a745; }
        .btn-trash { background: #dc3545; }
        
        /* Removed unused Review styles */
    </style>
</head>
<body>

    <div class="hero-header">
        <div class="circle-decor c1"></div>
        <div class="circle-decor c2"></div>
        <h1 class="hero-title">Admin Control Center 🛡️</h1>
        <p class="hero-subtitle">Manage appointments and status.</p>
        <a href="${pageContext.request.contextPath}/logout" style="color:white; font-weight:bold; margin-top:10px; display:inline-block; text-decoration: underline;">Logout</a>
    </div>

    <div class="dashboard-container">
        <div class="admin-panel">
            
            <div class="stats-grid">
                <div class="stat-card card-blue">
                    <div><div class="stat-title">Total Bookings</div><div class="stat-value"><%= (request.getAttribute("totalCount") != null) ? request.getAttribute("totalCount") : 0 %></div></div>
                    <div style="font-size:30px;">📅</div>
                </div>
                <div class="stat-card card-yellow">
                    <div>
                        <div class="stat-title">Pending Action</div>
                        <% Object pObj = request.getAttribute("pendingCount"); int pCount = (pObj != null) ? (int) pObj : 0; %>
                        <div class="stat-value"><%= pCount %></div>
                    </div>
                    <div style="font-size:30px;">⚠️</div>
                </div>
                <div class="stat-card card-green">
                    <div><div class="stat-title">Visits Today</div><div class="stat-value"><%= (request.getAttribute("todayCount") != null) ? request.getAttribute("todayCount") : 0 %></div></div>
                    <div style="font-size:30px;">🩺</div>
                </div>
            </div>

            <div class="search-container">
                <h3 style="margin:0; color: #444;">Manage Appointments</h3>
                <form action="${pageContext.request.contextPath}/adminDashboard" method="get" class="search-form">
                    <input type="text" name="search" class="search-input" placeholder="Search Owner or Pet..." value="<%= (request.getParameter("search") != null) ? request.getParameter("search") : "" %>">
                    <button type="submit" class="btn-purple">Search</button>
                    <% if (request.getParameter("search") != null) { %> <a href="${pageContext.request.contextPath}/adminDashboard" style="color:#e74c3c; align-self:center; font-weight:bold;">Clear</a> <% } %>
                </form>
            </div>

            <div class="table-container">
                <table>
                    <thead>
                        <tr><th>Date</th><th>Status</th><th>Owner / Pet</th><th>Service</th><th>Actions</th></tr>
                    </thead>
                    <tbody>
                        <% 
                        List<Appointment> list = (List<Appointment>) request.getAttribute("appointmentList");
                        if (list != null && !list.isEmpty()) {
                            for (Appointment a : list) { 
                        %>
                        <tr>
                            <td><b><%= a.getApptDate() %></b><br><span style="color:#888; font-size:12px;"><%= a.getApptTime() %></span></td>
                            <td>
                                <% if ("Pending".equalsIgnoreCase(a.getStatus()) || a.getStatus() == null) { %>
                                    <span class="badge-status b-pending">Pending</span>
                                <% } else { %>
                                    <span class="badge-status b-confirmed">Confirmed</span>
                                <% } %>
                            </td>
                            <td><b><%= a.getOwnerName() %></b><br>Pet: <%= a.getPetName() %></td>
                            <td><%= a.getService() %></td>
                            <td>
                                <div style="display:flex; gap:5px;">
                                    <% if ("Pending".equalsIgnoreCase(a.getStatus()) || a.getStatus() == null) { %>
                                        <form action="${pageContext.request.contextPath}/adminDashboard" method="post">
                                            <input type="hidden" name="action" value="confirm">
                                            <input type="hidden" name="id" value="<%= a.getId() %>">
                                            <button type="submit" class="action-btn btn-check" title="Confirm">✓</button>
                                        </form>
                                    <% } %>
                                    <form action="${pageContext.request.contextPath}/adminDashboard" method="post" onsubmit="return confirm('Delete?');">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="id" value="<%= a.getId() %>">
                                        <button type="submit" class="action-btn btn-trash" title="Delete">🗑</button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                        <% } } else { %>
                            <tr><td colspan="5" style="text-align:center; padding:30px; color:#888;">No appointments found.</td></tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
            
            </div>
    </div>

</body>
</html>