package dao;

import model.Test;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDAO {
    private final Connection conn;

    public TestDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Test> getAllTests() throws SQLException {
        List<Test> tests = new ArrayList<>();
        String sql = "SELECT id, title, recruiter_id, assessment_id, created_date, target_difficulty " +
                "FROM tests ORDER BY created_date DESC";

        System.out.println("Executing query: " + sql);

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Test test = new Test();
                test.setId(rs.getInt("id"));
                test.setTitle(rs.getString("title"));
                test.setRecruiterId(rs.getInt("recruiter_id"));
                test.setAssessmentId(rs.getInt("assessment_id"));
                test.setCreatedDate(rs.getDate("created_date").toString());
                test.setTargetDifficulty(rs.getInt("target_difficulty"));
                tests.add(test);

                System.out.println("Found test: " + test.getId() + " - " + test.getTitle());
            }
        }
        return tests;
    }
}