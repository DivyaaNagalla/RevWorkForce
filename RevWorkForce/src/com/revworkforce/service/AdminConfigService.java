package com.revworkforce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revworkforce.dao.AdminConfigDAO;

public class AdminConfigService {

    private static final Logger logger =
            LoggerFactory.getLogger(AdminConfigService.class);

    private AdminConfigDAO dao;
    public AdminConfigService() {
        this.dao = new AdminConfigDAO();
        logger.info("AdminConfigService initialized using default constructor");
    }
    public AdminConfigService(AdminConfigDAO dao) {
        this.dao = dao;
        logger.info("AdminConfigService initialized using injected AdminConfigDAO");
    }

    public void addDepartment(String name) throws Exception {
        logger.info("Request received to add department: {}", name);
        try {
            dao.addDepartment(name);
            logger.info("Department added successfully: {}", name);
        } catch (Exception e) {
            logger.error("Failed to add department: {}", name, e);
            throw e;
        }
    }

    public void addDesignation(String name) throws Exception {
        logger.info("Request received to add designation: {}", name);
        try {
            dao.addDesignation(name);
            logger.info("Designation added successfully: {}", name);
        } catch (Exception e) {
            logger.error("Failed to add designation: {}", name, e);
            throw e;
        }
    }
}
