package pet_manage.servlet; // <--- 1. This was missing

import jakarta.servlet.ServletException; // <--- 2. These imports were missing
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/userDashboard")
public class UserDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // --- 🔒 SECURITY CHECK (Optional but Recommended) ---
        // If someone tries to jump here without logging in, kick them out
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        // ----------------------------------------------------

        // Show the Dashboard JSP
        req.getRequestDispatcher("/WEB-INF/user_dashboard.jsp").forward(req, resp);
    }
}