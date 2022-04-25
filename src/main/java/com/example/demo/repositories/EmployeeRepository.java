package com.example.demo.repositories;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.utility.DatabaseConnectionManager;
import org.thymeleaf.exceptions.TemplateProcessingException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeRepository implements CRUDInterface<Employee> {

    @Override
    public void create(Employee entity) {
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement stmt = con.prepareStatement(String.valueOf(ResultSet.TYPE_SCROLL_INSENSITIVE), ResultSet.TYPE_FORWARD_ONLY);

            int employeeID= entity.getEmployeeID();
            String name = entity.getName();
            String job = entity.getJob();
            int managerID = entity.getManagerID();
            Date hireDate = entity.getHireDate();
            int salary = entity.getSalary();
            int commission = entity.getCommission();
            int departmentNum = entity.getDepartmentNumber();

            String query = "INSERT INTO `department`.`employees` (`id`, `employee_name`, `job`, `manager`, `hiredate`, `salary`, `commission`, `department_number`)"
                    + "VALUES ('"+employeeID+"','"+name+"', '"+job+"', '"+managerID+"', '"+hireDate+"', '"+salary+"', '"+commission+"','"+departmentNum+"');";

            stmt.executeUpdate(query);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Employee getSingleEntityById(int employeeID) {
        Employee tmp = null;
        try {
            Connection conn = DatabaseConnectionManager.getConnection();

            PreparedStatement stmt = conn.prepareStatement(String.valueOf(ResultSet.TYPE_SCROLL_INSENSITIVE),ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees as u WHERE u.id = '"+employeeID+"'");
            while(rs.next()){
                int iD = rs.getInt("id");
                String name = rs.getString("employee_name");
                String job = rs.getString("job");
                int managerID = rs.getInt("manager");
                Date hireDate = rs.getDate("hiredate");
                int department = rs.getInt("department_number");
                tmp = new Employee(iD, name, job, managerID, hireDate, department);
            }
        }
        catch (TemplateProcessingException l) {
            l.printStackTrace();
            System.out.println("Thymeleaf processing failed");
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Something wrong with database");
        }

        return tmp;

    }

    @Override
    public ArrayList<Employee> getAllEntities() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        try {
            Connection conn = DatabaseConnectionManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employees");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int iD = rs.getInt("id");
                String name = rs.getString("employee_name");
                String job = rs.getString("job");
                int managerID = rs.getInt("manager");
                Date hireDate = rs.getDate("hiredate");
                int department = rs.getInt("department_number");
                Employee tmp = new Employee(iD, name, job, managerID, hireDate, department);
                employeeList.add(tmp);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Something wrong with database");
        }
        return employeeList;
    }

    @Override
    public boolean update(Employee entity) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
