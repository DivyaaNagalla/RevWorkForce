package com.revwokforce.test;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revworkforce.dao.LeaveAdminDAO;
import com.revworkforce.service.LeaveAdminService;

public class LeaveAdminServiceTest {

    @Mock
    private LeaveAdminDAO leaveAdminDAO;

    private LeaveAdminService leaveAdminService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        leaveAdminService = new LeaveAdminService(leaveAdminDAO);
    }

    // ================= ADD LEAVE TYPE =================

    @Test
    public void testAddLeaveType() throws Exception {
        leaveAdminService.addLeaveType("Casual Leave", 12);
        verify(leaveAdminDAO).addLeaveType("Casual Leave", 12);
    }

    // ================= ASSIGN LEAVE QUOTA =================

    @Test
    public void testAssignLeaveQuota() throws Exception {
        leaveAdminService.assignLeaveQuota(101, 1, 10);
        verify(leaveAdminDAO).assignLeaveQuota(101, 1, 10);
    }

    // ================= ADJUST LEAVE BALANCE =================

    @Test
    public void testAdjustLeaveBalance() throws Exception {
        leaveAdminService.adjustLeaveBalance(101, 1, 8);
        verify(leaveAdminDAO).adjustLeaveBalance(101, 1, 8);
    }
}

