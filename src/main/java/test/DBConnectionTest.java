package test;

import java.sql.Connection;
import java.sql.SQLException;

import utils.DButil;

public class DBConnectionTest {
    public static void main(String[] args) throws SQLException {
        Connection conn = DButil.getConnection();
        if (conn != null) {
            System.out.println("✅ Connection successful!");
        } else {
            System.out.println("❌ Connection failed.");
        }
    }
}
    