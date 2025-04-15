package servlet;

import model.Test;
import model.Assessment;
import model.User;
import dao.TestDAO;
import dao.AssessmentDAO;
import dao.UserDAO;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet(
        urlPatterns = {"/admin/tests", "/admin/manageTests"},
        initParams = {
                @WebInitParam(name = "dbUrl", value = "jdbc:postgresql://localhost:5432/Questify_db"),
                @WebInitParam(name = "dbUser", value = "postgres"),
                @WebInitParam(name = "dbPassword", value = "123")
        },
        loadOnStartup = 1
)
public class ListTestsServlet extends HttpServlet {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    @Override
    public void init() throws ServletException {
        this.dbUrl = getServletConfig().getInitParameter("dbUrl");
        this.dbUser = getServletConfig().getInitParameter("dbUser");
        this.dbPassword = getServletConfig().getInitParameter("dbPassword");
        System.out.println("ListTestsServlet initialized with DB URL: " + dbUrl);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Processing request for /admin/tests");

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            TestDAO testDAO = new TestDAO(conn);
            AssessmentDAO assessmentDAO = new AssessmentDAO(conn);
            UserDAO userDAO = new UserDAO(conn);

            List<Test> tests = testDAO.getAllTests();
            List<Assessment> assessments = assessmentDAO.getAllAssessments();
            List<User> recruiters = userDAO.getUsersByRole("recruiter");

            System.out.println("Loaded " + tests.size() + " tests, " +
                    assessments.size() + " assessments, " +
                    recruiters.size() + " recruiters");

            request.setAttribute("tests", tests);
            request.setAttribute("assessments", assessments);
            request.setAttribute("recruiters", recruiters);

        } catch (SQLException e) {
            System.err.println("Database error in ListTestsServlet:");
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
        }

        request.getRequestDispatcher("/admin/manageTests.jsp").forward(request, response);
    }
}