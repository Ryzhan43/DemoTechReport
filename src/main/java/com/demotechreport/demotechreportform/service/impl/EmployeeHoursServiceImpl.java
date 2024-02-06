package com.demotechreport.demotechreportform.service.impl;

import com.demotechreport.demotechreportform.dto.EmployeeHoursDTO;
import com.demotechreport.demotechreportform.entity.EmployeeHours;
import com.demotechreport.demotechreportform.mapper.MapperUtil;
import com.demotechreport.demotechreportform.repository.EmployeeHoursRepository;
import com.demotechreport.demotechreportform.service.EmployeeHoursService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeHoursServiceImpl implements EmployeeHoursService {

    final private EmployeeHoursRepository employeeHoursRepository;
    final private MapperUtil mapperUtil;

    public EmployeeHoursServiceImpl(EmployeeHoursRepository employeeHoursRepository, MapperUtil mapperUtil) {
        this.employeeHoursRepository = employeeHoursRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public void save(EmployeeHoursDTO employeeHoursDTO) {
        employeeHoursRepository.save(mapperUtil.convert(employeeHoursDTO, new EmployeeHours()));
    }
}
