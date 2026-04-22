<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, pet_manage.model.Appointment" %>

<%
    List<Appointment> list = (List<Appointment>) request.getAttribute("appointments");
%>
<!DOCTYPE html>
<html>
<head><meta charset="utf-8"><title>Appointments</title></head>
<body>
<h2>Appointments</h2>
<a href="appointment.jsp">Book new appointment</a>
<table border="1" cellpadding="6">
    <tr>
        <th>ID</th><th>Owner</th><th>Pet</th><th>Service</th><th>Date</th><th>Time</th><th>Notes</th>
    </tr>
<%
    if (list == null || list.isEmpty()) {
%>
      <tr><td colspan="7">No appointments</td></tr>
<%
    } else {
        for (Appointment a : list) {
%>
            <tr>
                <td><%= a.getId() %></td>
                <td><%= a.getOwnerName() %></td>
                <td><%= a.getPetName() %></td>
                <td><%= a.getService() %></td>
                <td><%= a.getApptDate() %></td>
                <td><%= a.getApptTime() %></td>
                <td><%= a.getNotes() %></td>
            </tr>
<%
        }
    }
%>
</table>
</body>
</html>
