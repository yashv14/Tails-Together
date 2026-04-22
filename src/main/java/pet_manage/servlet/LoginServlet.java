package pet_manage.servlet;

import pet_manage.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Show the login form
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String u = request.getParameter("username");
        String p = request.getParameter("password");

        // Validate User against Database
        if (userDAO.validate(u, p)) {
            // LOGIN SUCCESS
            HttpSession session = request.getSession();
            session.setAttribute("user", u);
            
            // --- ROLE BASED REDIRECT ---
            if ("admin".equalsIgnoreCase(u)) {
                // If the user is Admin, go to the Admin Dashboard
                response.sendRedirect(request.getContextPath() + "/adminDashboard");
            } else {
                // UPDATED HERE: Normal users now go to their User Dashboard
                response.sendRedirect(request.getContextPath() + "/userDashboard");
            }
            
        } else {
            // LOGIN FAILED
            request.setAttribute("errorMessage", "Invalid Username or Password");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}