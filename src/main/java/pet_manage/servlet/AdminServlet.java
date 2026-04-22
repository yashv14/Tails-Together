package pet_manage.servlet;

import pet_manage.dao.AppointmentDAO;
import pet_manage.dao.ReviewDAO;
import pet_manage.model.Appointment;
import pet_manage.model.Review;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminDashboard")
public class AdminServlet extends HttpServlet {

    private final AppointmentDAO dao = new AppointmentDAO();
    private final ReviewDAO reviewDao = new ReviewDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Stats
        request.setAttribute("pendingCount", dao.getPendingCount());
        request.setAttribute("totalCount", dao.getTotalCount());
        request.setAttribute("todayCount", dao.getTodayCount());

        // 2. Search vs View All Logic
        String search = request.getParameter("search");
        List<Appointment> list;
        
        if (search != null && !search.trim().isEmpty()) {
            list = dao.searchAppointments(search); // Call search logic
        } else {
            list = dao.getAllAppointments(); // Call standard list
        }
        
        request.setAttribute("appointmentList", list);

        // 3. Reviews
        List<Review> reviews = reviewDao.getAllReviews();
        request.setAttribute("adminReviews", reviews);

        request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            
            if ("confirm".equals(action)) {
                dao.updateStatus(id, "Confirmed");
            } else if ("delete".equals(action)) {
                dao.deleteAppointment(id);
            }
        }
        // Reload dashboard to see changes
        response.sendRedirect(request.getContextPath() + "/adminDashboard");
    }
}