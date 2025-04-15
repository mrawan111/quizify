package dao;

import model.Test;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDAO {
    private Connection conn;

    public TestDAO(Connection conn) {
        this.conn = conn;
    }

    // Create Test
    public boolean createTest(Test test) throws SQLException {
        String sql = "INSERT INTO tests (title, recruiter_id, assessment_id, target_difficulty) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, test.getTitle());
            ps.setInt(2, test.getRecruiterId());
            ps.setInt(3, test.getAssessmentId());
            ps.setInt(4, test.getTargetDifficulty());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        test.setId(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        }
    }

    public List<Test> getAllTests() throws SQLException {
        List<Test> tests = new ArrayList<>();
        String sql = "SELECT * FROM tests ORDER BY created_date DESC";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Test test = new Test();
                test.setId(rs.getInt("id"));
                test.setTitle(rs.getString("title"));
                test.setRecruiterId(rs.getInt("recruiter_id"));
                test.setAssessmentId(rs.getInt("assessment_id"));
                test.setCreatedDate(rs.getTimestamp("created_date").toString());
                test.setTargetDifficulty(rs.getInt("target_difficulty"));
                tests.add(test);
            }
        }
        return tests;
    }

    // Get Test by ID
    public Test getTestById(int testId) throws SQLException {
        String sql = "SELECT * FROM tests WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, testId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTest(rs);
                }
            }
        }
        return null;
    }

    // Update Test
    public boolean updateTest(Test test) throws SQLException {
        String sql = "UPDATE tests SET title = ?, recruiter_id = ?, assessment_id = ?, target_difficulty = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, test.getTitle());
            ps.setInt(2, test.getRecruiterId());
            ps.setInt(3, test.getAssessmentId());
            ps.setInt(4, test.getTargetDifficulty());
            ps.setInt(5, test.getId());
            return ps.executeUpdate() > 0;
        }
    }

    // Delete Test
    public boolean deleteTest(int testId) throws SQLException {
        String sql = "DELETE FROM tests WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, testId);
            return ps.executeUpdate() > 0;
        }
    }

    // Helper method to map ResultSet to Test object
    private Test mapResultSetToTest(ResultSet rs) throws SQLException {
        Test test = new Test();
        test.setId(rs.getInt("id"));
        test.setTitle(rs.getString("title"));
        test.setRecruiterId(rs.getInt("recruiter_id"));
        test.setAssessmentId(rs.getInt("assessment_id"));
        test.setCreatedDate(rs.getString("created_date"));
        test.setTargetDifficulty(rs.getInt("target_difficulty"));
        return test;
    }
}