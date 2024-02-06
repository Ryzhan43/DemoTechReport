package com.demotechreport.demotechreportform.entity;

import com.demotechreport.demotechreportform.dto.EmployeeDTO;
import com.demotechreport.demotechreportform.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "employee_hours")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeHours extends BaseEntity {
    @OneToOne
    private Employee employee;
    private Long startTime;
    private Long endTime;
    private Long travelTime;
    private LocalTime totalTime;

    @ManyToOne
    @JoinColumn(name = "report_id") // The foreign key column in the employee_hours table
    private Report report;
}
