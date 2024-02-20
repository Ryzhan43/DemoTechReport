package com.demotechreport.demotechreportform.service;

import com.demotechreport.demotechreportform.dto.EmployeeHoursDTO;
import com.demotechreport.demotechreportform.entity.EmployeeHours;

import java.util.List;

public interface EmployeeHoursService {
    void save(EmployeeHoursDTO employeeHoursDTO);

    EmployeeHours convertToEntity(EmployeeHoursDTO a);
}
