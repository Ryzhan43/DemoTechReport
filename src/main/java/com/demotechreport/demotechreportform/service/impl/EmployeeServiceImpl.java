package com.demotechreport.demotechreportform.service.impl;

import com.demotechreport.demotechreportform.dto.EmployeeDTO;
import com.demotechreport.demotechreportform.entity.Employee;
import com.demotechreport.demotechreportform.mapper.MapperUtil;
import com.demotechreport.demotechreportform.repository.EmployeeRepository;
import com.demotechreport.demotechreportform.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    final private EmployeeRepository employeeRepository;
    final private MapperUtil mapperUtil;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, MapperUtil mapperUtil) {
        this.employeeRepository = employeeRepository;

        this.mapperUtil = mapperUtil;
    }


    @Override
    public void save(EmployeeDTO employeeDTO) {
        employeeRepository.save(mapperUtil.convert(employeeDTO, new Employee()));
    }
}
