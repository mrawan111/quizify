package dao;

import model.Assessment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssessmentDAO {
    private Connection conn;

    public AssessmentDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Assessment> getAllAssessments() throws SQLException {
        List<Assessment> assessments = new ArrayList<>();
        String sql = "SELECT id, name, description FROM assessments";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Assessment assessment = new Assessment();
                assessment.setId(rs.getInt("id"));
                assessment.setName(rs.getString("name"));
                assessment.setDescription(rs.getString("description"));
                assessments.add(assessment);
            }
        }
        return assessments;
    }
}