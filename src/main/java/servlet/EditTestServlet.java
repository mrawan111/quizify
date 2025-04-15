package servlet;

import dao.TestDAO;
import model.Test;
import utils.DButil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class EditTestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testId = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = DButil.getConnection()) {
            TestDAO testDAO = new TestDAO(conn);
            Test test = testDAO.getTestById(testId);

            if (test != null) {
                request.setAttribute("test", test);
                request.getRequestDispatcher("/admin/editTest.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("errorMessage", "Test not found");
                response.sendRedirect(request.getContextPath() + "/admin/manageTests.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Database error: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/admin/manageTests.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testId = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        int recruiterId = Integer.parseInt(request.getParameter("recruiterId"));
        int assessmentId = Integer.parseInt(request.getParameter("assessmentId"));
        int targetDifficulty = Integer.parseInt(request.getParameter("targetDifficulty"));

        Test test = new Test();
        test.setId(testId);
        test.setTitle(title);
        test.setRecruiterId(recruiterId);
        test.setAssessmentId(assessmentId);
        test.setTargetDifficulty(targetDifficulty);

        try (Connection conn = DButil.getConnection()) {
            TestDAO testDAO = new TestDAO(conn);
            if (testDAO.updateTest(test)) {
                request.getSession().setAttribute("successMessage", "Test updated successfully!");
            } else {
                request.getSession().setAttribute("errorMessage", "Failed to update test.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Database error: " + e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/admin/manageTests.jsp");
    }
}