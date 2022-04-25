package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.expression.spel.SpelParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.thymeleaf.exceptions.TemplateProcessingException;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class EmployeeController {

    @GetMapping("/employees")
    public String allEmployees(Model model) {
        EmployeeRepository er = new EmployeeRepository();

        ArrayList<Employee> employeeEntities = er.getAllEntities();
        model.addAttribute("employees", employeeEntities);
        return "employees";
    }

    @GetMapping("/single-employee")
    public String singleEmployee() {
        return "single-employee";
    }

    @GetMapping("/search-single-employee")
    public String searchSingleEmployee(@RequestParam(required = false) String id, Model model) {
        EmployeeRepository er = new EmployeeRepository();
        try {
            int employeeID = Integer.parseInt(id);
            Employee employeeEntity = er.getSingleEntityById(employeeID);
            ArrayList<Employee> employee = new ArrayList<>();
            employee.add(employeeEntity);
            model.addAttribute("employee", employee);



        } catch (NumberFormatException e) {
            String msg = "First time";
            model.addAttribute("message", msg);
        }
        catch (TemplateProcessingException l) {
            l.printStackTrace();
            System.out.println("Thymeleaf processing failed");
        }
        catch (SpelParseException m) {
            m.printStackTrace();
            System.out.println("Parse failed");
        }
        catch (NullPointerException n) {
            System.out.println("Employee == null");
            return "redirect:/single-employee";
        }

        return "search-single-employee";
    }

    @GetMapping("/create-employee")
    public String createEmployee () {
        return "create-employee";
    }

    @PostMapping("/make-employee")
    public String makeEmployee (WebRequest dataFromForm, HttpSession session) {
        String id = dataFromForm.getParameter("id");
        String name = dataFromForm.getParameter("name");
        String job = dataFromForm.getParameter("job");
        String managerID = dataFromForm.getParameter("manager");
        String hireDate = dataFromForm.getParameter("hiredate");
        String salary = dataFromForm.getParameter("salary");
        String commission = dataFromForm.getParameter("commission");
        String department = dataFromForm.getParameter("department");
        System.out.println(id + name + job + managerID + hireDate + salary + commission + department);


        int idFix = Integer.parseInt(id);
        int managerIDFix = Integer.parseInt(managerID);
        int salaryFix = Integer.parseInt(salary);
        int commissionFix = Integer.parseInt(commission);
        int departmentFix = Integer.parseInt(department);

        String[] hireDateArray = hireDate.split("-");
        int year = Integer.parseInt(hireDateArray[0])-1900;
        int month = Integer.parseInt(hireDateArray[1])-1;
        int day = Integer.parseInt(hireDateArray[2]);
        Date hireDateFix = new java.sql.Date(year,month,day);

        EmployeeRepository rs = new EmployeeRepository();
        Employee tmp = new Employee(idFix,name,job,managerIDFix,hireDateFix,salaryFix,commissionFix, departmentFix);
        rs.create(tmp);



        return "redirect:/created";
    }
    @GetMapping("/created")
    public String createdUser() {
        return "/created";
    }


}
