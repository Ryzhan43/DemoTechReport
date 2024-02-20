package com.demotechreport.demotechreportform.service.impl;

import com.demotechreport.demotechreportform.dto.EmployeeHoursDTO;
import com.demotechreport.demotechreportform.entity.Employee;
import com.demotechreport.demotechreportform.entity.EmployeeHours;
import com.demotechreport.demotechreportform.mapper.MapperUtil;
import com.demotechreport.demotechreportform.repository.EmployeeHoursRepository;
import com.demotechreport.demotechreportform.service.EmployeeHoursService;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
        LocalTime startTime = LocalTime.parse(employeeHoursDTO.getStartTime());
        LocalTime endTime = LocalTime.parse(employeeHoursDTO.getEndTime());
        LocalTime totalTime = calculateTotalTime(startTime, endTime);

        employeeHoursRepository.save(mapperUtil.convert(employeeHoursDTO, new EmployeeHours()));
    }

    @Override
    public EmployeeHours convertToEntity(EmployeeHoursDTO employeeHoursDTO) {
        EmployeeHours employeeHoursConverted = new EmployeeHours();
        LocalTime startTime = LocalTime.parse(employeeHoursDTO.getStartTime());
        LocalTime endTime = LocalTime.parse(employeeHoursDTO.getEndTime());

        employeeHoursConverted.setEmployee(mapperUtil.convert(employeeHoursDTO.getEmployeeDTO(), new Employee()));

        employeeHoursConverted.setStartTime(startTime);
        employeeHoursConverted.setEndTime(endTime);
        employeeHoursConverted.setTotalTime(calculateTotalTime(startTime, endTime));

        return employeeHoursConverted;
    }


    private LocalTime calculateTotalTime(LocalTime startTime, LocalTime endTime) {
        if (endTime.isBefore(startTime)) {
            endTime = endTime.plus(1, ChronoUnit.DAYS); // Add 1 day to finish time
        }

        Duration duration = Duration.between(startTime, endTime);

        Long totalHours = duration.toHours();
        Long totalMinutes = duration.minusMinutes(totalHours*60).toMinutes();
        System.out.println("Total time: " + totalHours + " hours, " + totalMinutes + " minutes");
        String totalHoursString = ((totalHours.intValue()>=10) ? totalHours.intValue() : "0"+totalHours.intValue() ) +":"+((totalMinutes.intValue()>=10) ? totalMinutes.intValue() : "0"+totalMinutes.intValue() )+":00";

        return LocalTime.parse(totalHoursString);
    }
}
