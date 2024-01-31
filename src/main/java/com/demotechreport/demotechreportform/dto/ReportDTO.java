package com.demotechreport.demotechreportform.dto;

import com.demotechreport.demotechreportform.entity.common.BaseEntity;
import com.demotechreport.demotechreportform.enums.Vehicle;
import com.demotechreport.demotechreportform.enums.Weekday;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class  ReportDTO {

    private Long id;
    private Date date;
    private Weekday weekday;
    private String dtSupervisor;
    private Integer projectNumber;
    private String dtManager;
    private List<String> vehicle;
    private String address;
    private boolean shopping;
    private boolean disposal;
    private boolean scrap;
    private List<EmployeeHoursDTO> employeeHours;
    private List<String> notes;
}
