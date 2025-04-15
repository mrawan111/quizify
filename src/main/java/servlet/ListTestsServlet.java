package servlet;

import dao.*;
import model.*;
import utils.DButil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/admin/tests")
public class ListTestsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = DButil.getConnection()) {
            TestDAO testDAO = new TestDAO(conn);
            UserDAO userDAO = new UserDAO(conn);
            AssessmentDAO assessmentDAO = new AssessmentDAO(conn);

            // Debugging: Log counts before setting attributes
            System.out.println("Tests count: " + testDAO.getAllTests().size());
            System.out.println("Recruiters count: " + userDAO.getUsersByRole("recruiter").size());
            System.out.println("Assessments count: " + assessmentDAO.getAllAssessments().size());

            request.setAttribute("tests", testDAO.getAllTests());
            request.setAttribute("recruiters", userDAO.getUsersByRole("recruiter"));
            request.setAttribute("assessments", assessmentDAO.getAllAssessments());

            request.getRequestDispatcher("/admin/manageTests.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error. Check server logs.");
            request.getRequestDispatcher("/admin/manageTests.jsp").forward(request, response);
        }
    }
}