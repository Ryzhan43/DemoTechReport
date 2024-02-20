package com.demotechreport.demotechreportform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeHoursDTO {
    private Long id;
    private EmployeeDTO employeeDTO;
    private String startTime;
    private String endTime;
    private Long travelTime;
    private LocalTime totalTime;
}
