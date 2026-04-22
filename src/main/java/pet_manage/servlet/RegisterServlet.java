package pet_manage.servlet;

import pet_manage.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    // Show the Registration Form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    // Handle the Form Submission
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String u = request.getParameter("username");
        String p = request.getParameter("password");
        String confirmP = request.getParameter("confirmPassword");

        // Basic Validation
        if (!p.equals(confirmP)) {
            request.setAttribute("errorMessage", "Passwords do not match!");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }

        // Attempt to Register
        if (userDAO.registerUser(u, p)) {
            // Success: Redirect to Login page with a success message
            response.sendRedirect(request.getContextPath() + "/login?registered=1");
        } else {
            // Failure: Username likely taken
            request.setAttribute("errorMessage", "Username already exists. Try another.");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }
}