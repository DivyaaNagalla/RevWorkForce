package com.revworkforce.ui;

import java.util.Scanner;
import java.sql.Date;

import com.revworkforce.service.EmployeeService;
import com.revworkforce.util.EmailValidator;

public class AddEmployeeUI {

    public static void addEmployee() {

        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        try {
            System.out.println("\n--- Add Employee ---");

            System.out.print("Name : ");
            String name = sc.nextLine();

            String email;
            while(true){
            	 System.out.print("Email : ");
            	 email = sc.nextLine();
            
           try{
        	   EmailValidator.validate(email);
        	   email=email.trim();
        	   break;
           }catch(Exception e){
        	   System.out.println(e.getMessage());
        	   System.out.println("please enter email again.\n");
           }
            }
             
            System.out.print("DOB (yyyy-mm-dd): ");
            String dobStr = sc.nextLine();
            Date dob = Date.valueOf(dobStr);

            service.addEmployee(name, email, dob);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
