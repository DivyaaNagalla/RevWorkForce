package com.revwokforce.test;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revworkforce.dao.AdminConfigDAO;
import com.revworkforce.service.AdminConfigService;

public class AdminConfigServiceTest {

    @Mock
    private AdminConfigDAO adminConfigDAO;

    private AdminConfigService adminConfigService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        adminConfigService = new AdminConfigService(adminConfigDAO);
    }

    // ================= ADD DEPARTMENT =================

    @Test
    public void testAddDepartment() throws Exception {
        adminConfigService.addDepartment("Engineering");
        verify(adminConfigDAO).addDepartment("Engineering");
    }

    // ================= ADD DESIGNATION =================

    @Test
    public void testAddDesignation() throws Exception {
        adminConfigService.addDesignation("Software Engineer");
        verify(adminConfigDAO).addDesignation("Software Engineer");
    }
}

