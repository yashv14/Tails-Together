package pet_manage.servlet;

import pet_manage.dao.AppointmentDAO;
import pet_manage.model.Appointment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/myHistory")
public class UserHistoryServlet extends HttpServlet {

    private final AppointmentDAO dao = new AppointmentDAO();

    // 1. Show the History List
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("user") : null;

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<Appointment> myApps = dao.getAppointmentsByUser(username);
        request.setAttribute("myList", myApps);
        request.getRequestDispatcher("/WEB-INF/user_history.jsp").forward(request, response);
    }

    // 2. Handle Cancellation (NEW)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Security Check
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");
        
        if ("cancel".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.deleteAppointment(id); // Reusing the delete method we made for Admin
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Refresh the page to show the updated list
        response.sendRedirect(request.getContextPath() + "/myHistory?msg=cancelled");
    }
}