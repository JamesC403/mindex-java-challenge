package com.mindex.challenge.data;


//Unsure of whether BigDecimal use would be better than int since no calculations being done with salary.
public class Compensation {
    private Employee employee;
    private int salary;
    private String effectiveDate;
    private String employeeId;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String id) {
        this.employeeId = id;
    }

    public Compensation() {
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    //would have a check on datetime format but with time constraints could not figure out without using too much of time
    public void setEffectiveDate(String date) {
        this.effectiveDate = date;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
