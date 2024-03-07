package com.demotechreport.demotechreportform.repository;

import com.demotechreport.demotechreportform.entity.Employee;
import com.demotechreport.demotechreportform.entity.EmployeeHours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeHoursRepository extends JpaRepository<EmployeeHours, Long> {
    List<EmployeeHours> findAllByReport_Id(Long id);
}
