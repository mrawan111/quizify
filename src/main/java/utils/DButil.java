package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
    private static final String URL = "jdbc:postgresql://localhost:5432/Questify_db"; // عدل الـ db name لو مختلف
    private static final String USER = "postgres"; // عدل حسب المستخدم
    private static final String PASSWORD = "123"; // عدل حسب كلمة المرور

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
