package com.revwokforce.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revworkforce.dao.EmployeeDAO;
import com.revworkforce.model.Employee;
import com.revworkforce.service.EmployeeService;

public class EmployeeServiceTest {

    @Mock
    private EmployeeDAO employeeDAO;

    private EmployeeService employeeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeService(employeeDAO);
    }

    // ================= LOGIN =================

    @Test
    public void testLoginSuccess() throws Exception {
        Employee emp = new Employee();
        emp.setStatus("ACTIVE");

        when(employeeDAO.login(101, "pwd123")).thenReturn(emp);

        Employee result = employeeService.login(101, "pwd123");

        assertNotNull(result);
        verify(employeeDAO).login(101, "pwd123");
    }

    @Test(expected = Exception.class)
    public void testLoginInvalidCredentials() throws Exception {
        when(employeeDAO.login(101, "wrong")).thenReturn(null);
        employeeService.login(101, "wrong");
    }

    @Test(expected = Exception.class)
    public void testLoginInactiveAccount() throws Exception {
        Employee emp = new Employee();
        emp.setStatus("INACTIVE");

        when(employeeDAO.login(101, "pwd123")).thenReturn(emp);

        employeeService.login(101, "pwd123");
    }

    // ================= CHANGE PASSWORD =================

    @Test
    public void testChangePasswordSuccess() throws Exception {
        when(employeeDAO.isPasswordUsedBefore(101, "New@123456")).thenReturn(false);

        employeeService.changePassword(101, "New@123456", "New@123456");

        verify(employeeDAO).updatePassword(101, "New@123456");
        verify(employeeDAO).savePasswordHistory(101, "New@123456");
    }

    @Test(expected = Exception.class)
    public void testChangePasswordMismatch() throws Exception {
        employeeService.changePassword(101, "abc", "xyz");
    }

    @Test(expected = Exception.class)
    public void testChangePasswordReuse() throws Exception {
        when(employeeDAO.isPasswordUsedBefore(101, "Old@123")).thenReturn(true);

        employeeService.changePassword(101, "Old@123", "Old@123");
    }

    // ================= ADD EMPLOYEE =================

    @Test
    public void testAddEmployee() throws Exception {
        Date dob = Date.valueOf("2000-01-01");

        when(employeeDAO.addEmployee(anyString(), anyString(), any(Date.class), anyString()))
                .thenReturn(201);

        employeeService.addEmployee("Divya", "divya@gmail.com", dob);

        verify(employeeDAO).addEmployee(anyString(), anyString(), any(Date.class), anyString());
    }

    // ================= UPDATE PROFILE =================

    @Test
    public void testUpdateProfileSuccess() throws Exception {
        Employee emp = new Employee();

        when(employeeDAO.getEmployeeById(101)).thenReturn(emp);

        Employee result = employeeService.updateProfile(
                101, "9876543210", "Hyderabad", "9123456789");

        assertNotNull(result);
        verify(employeeDAO).updateProfile(101, "9876543210", "Hyderabad", "9123456789");
    }

    @Test(expected = Exception.class)
    public void testUpdateProfileInvalidPhone() throws Exception {
        employeeService.updateProfile(101, "123", "Addr", "9123456789");
    }

    // ================= FORGOT PASSWORD =================

    @Test
    public void testForgotPasswordSuccess() throws Exception {
        when(employeeDAO.validateSecurityAnswers(101, "a1", "a2")).thenReturn(true);

        employeeService.forgotPasswordWithSecurity(101, "a1", "a2");

        verify(employeeDAO).resetPassword(eq(101), anyString());
        verify(employeeDAO).savePasswordHistory(eq(101), anyString());
    }

    @Test(expected = Exception.class)
    public void testForgotPasswordInvalidAnswers() throws Exception {
        when(employeeDAO.validateSecurityAnswers(101, "a1", "a2")).thenReturn(false);

        employeeService.forgotPasswordWithSecurity(101, "a1", "a2");
    }
}

