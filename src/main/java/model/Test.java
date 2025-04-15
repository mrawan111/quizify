package model;

public class Test {
    private int id;
    private String title;
    private int recruiterId;
    private int assessmentId;
    private String createdDate;
    private int targetDifficulty;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getRecruiterId() { return recruiterId; }
    public void setRecruiterId(int recruiterId) { this.recruiterId = recruiterId; }

    public int getAssessmentId() { return assessmentId; }
    public void setAssessmentId(int assessmentId) { this.assessmentId = assessmentId; }

    public String getCreatedDate() { return createdDate; }
    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }

    public int getTargetDifficulty() { return targetDifficulty; }
    public void setTargetDifficulty(int targetDifficulty) { this.targetDifficulty = targetDifficulty; }
}
