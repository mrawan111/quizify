package servlet;

import dao.TestDAO;
import utils.DButil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
public class DeleteTestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testId = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = DButil.getConnection()) {
            TestDAO testDAO = new TestDAO(conn);
            if (testDAO.deleteTest(testId)) {
                request.getSession().setAttribute("successMessage", "Test deleted successfully!");
            } else {
                request.getSession().setAttribute("errorMessage", "Failed to delete test.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Database error: " + e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/admin/manageTests.jsp");
    }
}