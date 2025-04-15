package servlet;

import dao.TestDAO;
import model.Test;
import utils.DButil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
@WebServlet("/admin/createTest")
public class CreateTestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {
            // Validate and parse parameters
            String title = request.getParameter("title");
            int recruiterId = parseId(request.getParameter("recruiter_id"), "recruiter_id");
            int assessmentId = parseId(request.getParameter("assessmentId"), "Assessment ID");
            int targetDifficulty = validateDifficulty(request.getParameter("targetDifficulty"));

            // Create test object
            Test test = new Test();
            test.setTitle(title);
            test.setRecruiterId(recruiterId);
            test.setAssessmentId(assessmentId);
            test.setTargetDifficulty(targetDifficulty);

            // Database operation
            try (Connection conn = DButil.getConnection()) {
                TestDAO testDAO = new TestDAO(conn);
                if (testDAO.createTest(test)) {
                    session.setAttribute("successMessage", "Test '" + title + "' created successfully!");
                } else {
                    session.setAttribute("errorMessage", "Failed to create test.");
                }
            }
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "Invalid ID format: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            session.setAttribute("errorMessage", e.getMessage());
        } catch (SQLException e) {
            session.setAttribute("errorMessage", "Database error: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/admin/tests");
    }

    // Helper methods
    private int parseId(String idParam, String fieldName) {
        if (idParam == null || idParam.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        try {
            return Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid " + fieldName);
        }
    }

    private int validateDifficulty(String difficultyParam) {
        int difficulty = parseId(difficultyParam, "Difficulty");
        if (difficulty < 1 || difficulty > 10) {
            throw new IllegalArgumentException("Difficulty must be between 1-10");
        }
        return difficulty;
    }
}