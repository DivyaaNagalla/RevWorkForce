package com.revworkforce.model;

public class Goal {

    private int goalId;
    private int reviewId;
    private String description;
    private String priority;
    private String successMetric;
    private int progress;
    private String status;

    public int getGoalId() { return goalId; }
    public void setGoalId(int goalId) { this.goalId = goalId; }

    public int getReviewId() { return reviewId; }
    public void setReviewId(int reviewId) { this.reviewId = reviewId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getSuccessMetric() { return successMetric; }
    public void setSuccessMetric(String successMetric) { this.successMetric = successMetric; }

    public int getProgress() { return progress; }
    public void setProgress(int progress) { this.progress = progress; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
