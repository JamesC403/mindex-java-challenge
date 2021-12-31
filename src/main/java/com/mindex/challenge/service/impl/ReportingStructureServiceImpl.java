package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    private int recusiveSearchReports(List<Employee> employees, int count) {
        int reporting = count;
        if (employees == null) {
            return reporting;
        }
        for (int i = 0; i < employees.size(); i++) {
            Employee tempEmployee = employeeRepository.findByEmployeeId(employees.get(i).getEmployeeId());
            if (tempEmployee.getDirectReports() != null) {
                reporting += tempEmployee.getDirectReports().size();
                recusiveSearchReports(tempEmployee.getDirectReports(), reporting);
            }
        }

        return reporting;
    }

    @Override
    public ReportingStructure renderReport(String id) {
        ReportingStructure newReport = new ReportingStructure();
        int directReportCount = 0;
        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: [{}]" + id);
        }
        newReport.setEmployee(employee);
        LOG.debug("Setting employee of report equal to the ID: [{}]", id);

        if (employee.getDirectReports() != null) {
            List<Employee> directReports = new ArrayList<Employee>();
            directReports = employee.getDirectReports();
            directReportCount += recusiveSearchReports(directReports, directReportCount);
            directReportCount += directReports.size();
        }
        newReport.setNumberOfReports(directReportCount);

        return newReport;
    }
}
