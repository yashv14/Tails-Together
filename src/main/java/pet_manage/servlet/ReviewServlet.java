package pet_manage.servlet;

import pet_manage.dao.ReviewDAO;
import pet_manage.model.Review;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/reviews")
public class ReviewServlet extends HttpServlet {

    private final ReviewDAO dao = new ReviewDAO();

    // GET: Show the Review Page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Security Check
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Fetch list and send to JSP
        List<Review> reviews = dao.getAllReviews();
        request.setAttribute("reviewList", reviews);
        
        request.getRequestDispatcher("/WEB-INF/reviews.jsp").forward(request, response);
    }

    // POST: Submit a new Review
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("user");

        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        Review r = new Review();
        r.setUsername(username);
        r.setRating(rating);
        r.setComment(comment);

        dao.addReview(r);

        // Refresh the page
        response.sendRedirect(request.getContextPath() + "/reviews?success=1");
    }
}