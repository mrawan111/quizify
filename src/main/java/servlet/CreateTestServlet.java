package servlet;

import dao.TestDAO;
import model.Test;
import utils.DButil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@WebServlet(
        urlPatterns = "/admin/createTest",
        initParams = {
                @WebInitParam(name = "dbUrl", value = "jdbc:postgresql://localhost:5432/Questify_db"),
                @WebInitParam(name = "dbUser", value = "postgres"),
                @WebInitParam(name = "dbPassword", value = "123")
        },
        loadOnStartup = 2
)
public class CreateTestServlet extends HttpServlet {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    @Override
    public void init() throws ServletException {
        this.dbUrl = getServletConfig().getInitParameter("dbUrl");
        this.dbUser = getServletConfig().getInitParameter("dbUser");
        this.dbPassword = getServletConfig().getInitParameter("dbPassword");
        System.out.println("CreateTestServlet initialized with DB: " + dbUrl);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            // Your existing implementation
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}