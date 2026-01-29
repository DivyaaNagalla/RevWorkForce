package com.revworkforce.service;

import com.revworkforce.dao.EmployeeDAO;
import com.revworkforce.model.Employee;
import com.revworkforce.util.PasswordGenerator;

public class EmployeeService {

    private EmployeeDAO dao = new EmployeeDAO();

    public Employee login(int empId, String password) throws Exception {

        Employee emp = dao.login(empId, password);

        if (emp == null)
            throw new Exception("Invalid Employee ID or Password");

        if (!"ACTIVE".equals(emp.getStatus()))
            throw new Exception("Account inactive");

        return emp;
    }

    public void changePassword(int empId,
                               String newPwd,
                               String confirmPwd) throws Exception {

        if (!newPwd.equals(confirmPwd))
            throw new Exception("Passwords do not match");

        PasswordValidator.validate(newPwd);
        dao.updatePassword(empId, newPwd);
    }

    public void addEmployee(String name, String email) throws Exception {

        EmailValidator.validate(email);
        String tempPwd = PasswordGenerator.generateTempPassword();

        int empId = dao.addEmployee(name, email, tempPwd);

        System.out.println("\n--- Employee Created ---");
        System.out.println("Employee ID   : " + empId);
        System.out.println("Temp Password : " + tempPwd);
    }

    public void assignManager(int empId, int managerId) throws Exception {
        dao.assignManager(empId, managerId);
    }

    public void updateProfile(int empId,
                              String phone,
                              String address,
                              String emergency) throws Exception {

        dao.updateProfile(empId, phone, address, emergency);
    }

    public void viewBirthdays() throws Exception {
        dao.getUpcomingBirthdays();
    }

    public void viewAnniversaries() throws Exception {
        dao.getWorkAnniversaries();
    }

    public void viewEmployeeDirectory() throws Exception {
        dao.getEmployeeDirectory();
    }
}
