package pet_manage.servlet;

import pet_manage.dao.AppointmentDAO;
import pet_manage.model.Appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/bookAppointment")
public class BookAppointmentServlet extends HttpServlet {

    private final AppointmentDAO dao = new AppointmentDAO();

    // Show the form (GET request)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // --- 🔒 SECURITY CHECK START ---
        HttpSession session = request.getSession(false); 
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return; 
        }
        // --- 🔒 SECURITY CHECK END ---

        request.getRequestDispatcher("/WEB-INF/appointment.jsp")
               .forward(request, response);
    }

    // Handle form submission (POST request)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // --- 🔒 SECURITY CHECK START ---
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        // --- 🔒 SECURITY CHECK END ---

        // 1. Capture Form Data
        String owner = request.getParameter("ownerName");
        String pet = request.getParameter("petName");
        String service = request.getParameter("service");
        String date = request.getParameter("apptDate");
        String time = request.getParameter("apptTime");
        String notes = request.getParameter("notes");
        
        // 2. Capture Current User (NEW CHANGE)
        String currentUser = (String) session.getAttribute("user");

        try {
            // 3. Create Object and Set Data
            Appointment a = new Appointment();
            a.setUsername(currentUser); // <--- CRITICAL: Link appointment to logged-in user
            a.setOwnerName(owner);
            a.setPetName(pet);
            a.setService(service);
            a.setNotes(notes);

            // 4. Safe Date Parsing
            if (date != null && !date.trim().isEmpty()) {
                a.setApptDate(LocalDate.parse(date));
            }
            
            // 5. Safe Time Parsing
            if (time != null && !time.trim().isEmpty()) {
                if (time.length() == 5) {
                    time = time + ":00";
                }
                a.setApptTime(LocalTime.parse(time));
            }

            // 6. Attempt to Save to Database
            int id = dao.save(a);

            // 7. Check Result
            if (id > 0) {
                // Success: Redirect with success flag
                response.sendRedirect(request.getContextPath() + "/bookAppointment?success=1");
            } else {
                printErrorToBrowser(response, "Database returned ID 0", 
                    "The SQL query ran, but no rows were inserted. Check your DAO logic.");
            }

        } catch (Exception e) {
            e.printStackTrace(); 
            printErrorToBrowser(response, "Critical System Error", e);
        }
    }

    // Helper method for error display
    private void printErrorToBrowser(HttpServletResponse response, String title, Exception e) throws IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html><body style='font-family:sans-serif; padding:20px;'>");
        out.println("<h2 style='color:red;'>" + title + "</h2>");
        out.println("<p><strong>Error Type:</strong> " + e.getClass().getName() + "</p>");
        out.println("<p><strong>Message:</strong> " + e.getMessage() + "</p>");
        out.println("<h3>Stack Trace:</h3>");
        out.println("<pre style='background:#f4f4f4; padding:15px; border:1px solid #ccc;'>");
        e.printStackTrace(out);
        out.println("</pre>");
        out.println("</body></html>");
    }

    private void printErrorToBrowser(HttpServletResponse response, String title, String message) throws IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html><body style='font-family:sans-serif; padding:20px;'>");
        out.println("<h2 style='color:red;'>" + title + "</h2>");
        out.println("<p>" + message + "</p>");
        out.println("</body></html>");
    }
}