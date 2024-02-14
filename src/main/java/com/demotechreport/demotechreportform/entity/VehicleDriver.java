package com.demotechreport.demotechreportform.entity;

import com.demotechreport.demotechreportform.entity.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicle_drivers")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class VehicleDriver extends BaseEntity {
    private String vehicleDriver;

}