package com.demotechreport.demotechreportform.entity;

import com.demotechreport.demotechreportform.entity.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee extends BaseEntity {
     private String firstName;
     private String lastName;

}
