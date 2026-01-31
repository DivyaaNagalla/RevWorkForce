package com.revworkforce.service;

import com.revworkforce.dao.PerformanceDAO;

public class PerformanceService {

    private PerformanceDAO dao = new PerformanceDAO();

    public int submitReview(int empId,
                            int reviewYear,
                            String deliverables,
                            String accomplishments,
                            String improvements,
                            int selfRating) throws Exception {

        if (selfRating < 1 || selfRating > 5) {
            throw new Exception("Rating must be between 1 and 5");
        }

        if (deliverables == null || deliverables.trim().isEmpty())
            throw new Exception("Deliverables required");

        if (accomplishments == null || accomplishments.trim().isEmpty())
            throw new Exception("Accomplishments required");

        if (improvements == null || improvements.trim().isEmpty())
            throw new Exception("Improvements required");

        return dao.createReview(
                empId,
                reviewYear,
                deliverables,
                accomplishments,
                improvements,
                selfRating
        );
    }

    public void addGoal(int reviewId,
                        String description,
                        String priority,
                        String metric) throws Exception {

        if (!priority.matches("HIGH|MEDIUM|LOW"))
            throw new Exception("Invalid priority");

        dao.addGoal(reviewId, description, priority, metric);
    }

    public void updateGoalProgress(int goalId, int progress) throws Exception {

        if (progress < 0 || progress > 100)
            throw new Exception("Invalid progress");

        dao.updateGoalProgress(goalId, progress);
    }

    public void giveFeedback(int reviewId,
                             String feedback,
                             int rating,
                             int empId) throws Exception {

        if (rating < 1 || rating > 5)
            throw new Exception("Invalid rating");

        dao.giveManagerFeedback(reviewId, feedback, rating, empId);
    }

    public void viewTeamReviews(int managerId) throws Exception {
        dao.viewTeamReviews(managerId);
    }

    public void viewEmployeeGoals(int reviewId) throws Exception {
        dao.viewEmployeeGoals(reviewId);
    }

    public void viewTeamPerformanceSummary(int managerId) throws Exception {
        dao.teamPerformanceSummary(managerId);
    }
}
