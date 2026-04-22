package pet_manage.servlet;

import pet_manage.dao.AppointmentDAO;
import pet_manage.model.Appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/appointments")
public class ListAppointmentServlet extends HttpServlet {

    private final AppointmentDAO dao = new AppointmentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // FIX 1: Removed try/catch (DAO handles errors internally now)
        // FIX 2: Changed .findAll() to .getAllAppointments() to match your DAO
        List<Appointment> list = dao.getAllAppointments();
        
        request.setAttribute("appointments", list);
        
        // Ensure this JSP file actually exists in your WEB-INF folder
        request.getRequestDispatcher("/WEB-INF/listAppointments.jsp").forward(request, response);
    }
}