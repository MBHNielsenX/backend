package com.example.demo.repositories;

import com.example.demo.models.Department;
import com.example.demo.utility.DatabaseConnectionManager;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class  DepartmentRepository implements CRUDInterface<Department>{
    @Override
    public void create(Department entity) {

    }

    @Override
    public Department getSingleEntityById(int id) {
        return null;
    }

    @Override
    public ArrayList<Department> getAllEntities() {
        ArrayList<Department> departmentList = new ArrayList<>();
        try {
            Connection conn = DatabaseConnectionManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM departments");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int departmentNumber = rs.getInt("department_number");
                String departmentName = rs.getString("department_name");
                String location = rs.getString("location");
                Department tmp = new Department(departmentNumber,departmentName,location);
                departmentList.add(tmp);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Something wrong with database");
        }
        return departmentList;
    }

    @Override
    public boolean update(Department entity) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
