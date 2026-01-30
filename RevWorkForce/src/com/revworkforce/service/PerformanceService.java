package com.revworkforce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revworkforce.dao.PerformanceDAO;

public class PerformanceService {

    private static final Logger logger =
            LoggerFactory.getLogger(PerformanceService.class);

    private PerformanceDAO dao;
    public PerformanceService() {
        this.dao = new PerformanceDAO();
        logger.info("PerformanceService initialized using default constructor");
    }
    public PerformanceService(PerformanceDAO dao) {
        this.dao = dao;
        logger.info("PerformanceService initialized using injected PerformanceDAO");
    }
    public void createReview(int empId,
                             int year,
                             String deliverables,
                             String achievements,
                             String improvements,
                             int rating) throws Exception {

        logger.info("Create performance review request: empId={}, year={}, rating={}",
                empId, year, rating);

        if (rating < 1 || rating > 5) {
            logger.warn("Invalid rating {} for empId={} (must be 1–5)", rating, empId);
            throw new Exception("Rating must be between 1 and 5");
        }

        try {
            dao.createReview(empId, year, deliverables, achievements, improvements, rating);
            logger.info("Performance review created successfully for empId={}", empId);
        } catch (Exception e) {
            logger.error("Failed to create performance review for empId={}", empId, e);
            throw e;
        }
    }
    public void viewMyReviews(int empId) throws Exception {
        logger.info("View my performance reviews request for empId={}", empId);
        dao.viewMyReviews(empId);
    }

    public void viewTeamReviews(int managerId) throws Exception {
        logger.info("View team performance reviews request for managerId={}", managerId);
        dao.viewTeamReviews(managerId);
    }

    public void giveManagerFeedback(int reviewId,
                                    int rating,
                                    String feedback) throws Exception {

        logger.info("Manager feedback request: reviewId={}, rating={}", reviewId, rating);

        if (rating < 1 || rating > 5) {
            logger.warn("Invalid manager rating {} for reviewId={}", rating, reviewId);
            throw new Exception("Rating must be between 1 and 5");
        }

        try {
            dao.giveManagerFeedback(reviewId, rating, feedback);
            logger.info("Manager feedback submitted successfully for reviewId={}", reviewId);
        } catch (Exception e) {
            logger.error("Failed to submit manager feedback for reviewId={}", reviewId, e);
            throw e;
        }
    }
}
