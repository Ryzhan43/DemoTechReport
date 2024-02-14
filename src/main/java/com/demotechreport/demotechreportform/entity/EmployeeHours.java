package com.demotechreport.demotechreportform.entity;

import com.demotechreport.demotechreportform.dto.EmployeeDTO;
import com.demotechreport.demotechreportform.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;


@Entity
@Table(name = "employee_hours")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeHours extends BaseEntity {
    @OneToOne(cascade = CascadeType.PERSIST)
    private Employee employee;
    private Long startTime;
    private Long endTime;
    private Long travelTime;
    private LocalTime totalTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id")
    private Report report;
}
