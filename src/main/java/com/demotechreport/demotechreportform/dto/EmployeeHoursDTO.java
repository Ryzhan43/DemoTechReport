package com.demotechreport.demotechreportform.dto;

import java.time.LocalTime;

public class EmployeeHoursDTO {
    private Long id;
    private EmployeeDTO employeeDTO;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long travelTime;
    private LocalTime totalTime;
}
