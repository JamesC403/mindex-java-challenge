package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    public Compensation create(Compensation compensation) {

        if (compensation.getEmployee().getEmployeeId() != null) {
            LOG.debug("Creating new Compensation for [{}]", compensation.getEmployee().getEmployeeId());
            //setting outer layer id equal to one on employee if they are not already
            if (compensation.getEmployeeId() == null || compensation.getEmployeeId() != compensation.getEmployee().getEmployeeId()) {
                compensation.setEmployeeId(compensation.getEmployee().getEmployeeId());
            }
            compensationRepository.insert(compensation);
            return compensation;
        } else {
            String id = UUID.randomUUID().toString();
            LOG.debug("Creating new Compensation for [{}]", compensation);
            compensation.getEmployee().setEmployeeId(id);
            compensation.setEmployeeId(id);
            compensationRepository.insert(compensation);
        }
       
        return compensation;
    }

    @Override
    public Compensation read(String id) {
        LOG.debug("Reading Compensation with id [{}]", id);

        Compensation compensation = compensationRepository.findByEmployeeId(id);
        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return compensation;
    }
    
}
