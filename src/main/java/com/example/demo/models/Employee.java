package com.example.demo.models;

import java.util.Date;

public class Employee {
    private int employeeID;
    private String name;
    private String job;
    private int managerID;
    private Date hireDate;
    private int salary;
    private int commission;
    private int departmentNumber;

    public Employee(int employeeID, String name, String job, int managerID, Date hireDate, int salary, int commission, int departmentNumber) {
        this.employeeID = employeeID;
        this.name = name;
        this.job = job;
        this.managerID = managerID;
        this.hireDate = hireDate;
        this.salary = salary;
        this.commission = commission;
        this.departmentNumber = departmentNumber;
    }

    public Employee(int employeeID, String name, String job, int managerID, Date hireDate, int departmentNumber) {
        this.employeeID = employeeID;
        this.name = name;
        this.job = job;
        this.managerID = managerID;
        this.hireDate = hireDate;
        this.departmentNumber = departmentNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", managerID=" + managerID +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", commission=" + commission +
                ", departmentNumber=" + departmentNumber +
                '}';
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public int getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(int departmentNumber) {
        this.departmentNumber = departmentNumber;
    }
}
