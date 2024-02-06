package com.demotechreport.demotechreportform.entity;

import com.demotechreport.demotechreportform.entity.common.BaseEntity;
import com.demotechreport.demotechreportform.enums.Weekday;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "reports")
public class Report extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Weekday weekday;
    private String dtSupervisor;
    private String dtManager;
    private Integer projectNumber;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name= "vehicle_driver_id")
    private List<VehicleDriver> vehicleDriver;
    private String address;
    private boolean shopping;
    private boolean disposal;
    private boolean scrap;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "report_id")
    private List<EmployeeHours> employeeHours;
    private String notes;
}
