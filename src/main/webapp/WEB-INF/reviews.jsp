<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="pet_manage.model.Review" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reviews - TailsTogether</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dashboard_style.css">
    <style>
        /* Specific Styles for Review Page */
        .review-container { max-width: 800px; margin: -50px auto 0; padding: 20px; position: relative; z-index: 10; }
        
        /* The Form Card */
        .review-form-card {
            background: white; padding: 30px; border-radius: 15px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.1); margin-bottom: 40px; text-align: center;
        }

        /* Star Rating CSS Magic */
        .rating {
            display: flex; flex-direction: row-reverse; justify-content: center; gap: 10px; margin: 20px 0;
        }
        .rating input { display: none; } /* Hide radio buttons */
        .rating label {
            cursor: pointer; width: 40px; height: 40px; font-size: 35px; color: #ddd; transition: 0.2s;
        }
        .rating label:before { content: '★'; }
        /* Highlight stars when checked or hovered */
        .rating input:checked ~ label,
        .rating input:checked ~ label ~ label,
        .rating label:hover,
        .rating label:hover ~ label { color: #ffc107; } /* Gold color */

        /* Reviews List */
        .review-card {
            background: white; padding: 20px; border-radius: 10px;
            margin-bottom: 15px; border-left: 5px solid #11998e; /* Green accent */
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        }
        .r-header { display: flex; justify-content: space-between; margin-bottom: 10px; }
        .r-user { font-weight: bold; color: #333; font-size: 16px; }
        .r-stars { color: #ffc107; letter-spacing: 2px; }
        .r-text { color: #666; font-style: italic; }
        .r-date { font-size: 12px; color: #999; }

        textarea { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 8px; margin-bottom: 10px; resize: vertical; }
        .btn-submit { background: linear-gradient(to right, #11998e, #38ef7d); color: white; padding: 10px 25px; border: none; border-radius: 20px; font-weight: bold; cursor: pointer; }
    </style>
</head>
<body>

    <div class="hero-header" style="height: 220px;">
        <h1 class="hero-title">Community Reviews 🌟</h1>
        <p class="hero-subtitle">See what others are saying about TailsTogether.</p>
        <a href="${pageContext.request.contextPath}/userDashboard" style="color:white; text-decoration:underline; font-size:14px;">Back to Dashboard</a>
    </div>

    <div class="review-container">
        
        <div class="review-form-card">
            <h3>Share Your Experience</h3>
            <form action="${pageContext.request.contextPath}/reviews" method="post">
                <div class="rating">
                    <input type="radio" name="rating" id="star5" value="5" required><label for="star5"></label>
                    <input type="radio" name="rating" id="star4" value="4"><label for="star4"></label>
                    <input type="radio" name="rating" id="star3" value="3"><label for="star3"></label>
                    <input type="radio" name="rating" id="star2" value="2"><label for="star2"></label>
                    <input type="radio" name="rating" id="star1" value="1"><label for="star1"></label>
                </div>
                <textarea name="comment" rows="3" placeholder="Write your feedback here..." required></textarea>
                <button type="submit" class="btn-submit">Post Review</button>
            </form>
        </div>

        <% 
        List<Review> list = (List<Review>) request.getAttribute("reviewList");
        if (list != null) {
            for (Review r : list) { 
        %>
            <div class="review-card">
                <div class="r-header">
                    <span class="r-user"><%= r.getUsername() %></span>
                    <span class="r-stars">
                        <% for(int i=0; i<r.getRating(); i++) { %>★<% } %>
                    </span>
                </div>
                <p class="r-text">"<%= r.getComment() %>"</p>
                <div class="r-date"><%= r.getReviewDate() %></div>
            </div>
        <% } } %>

    </div>

</body>
</html>