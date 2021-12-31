package com.mindex.challenge.data;

public class ReportingStructure {
    
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure() {
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setNumberOfReports(int reports) {
        this.numberOfReports = reports;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }
}
