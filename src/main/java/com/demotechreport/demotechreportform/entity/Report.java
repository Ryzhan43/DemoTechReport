package com.demotechreport.demotechreportform.entity;

import com.demotechreport.demotechreportform.entity.common.BaseEntity;
import com.demotechreport.demotechreportform.enums.Weekday;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "reports")
public class Report extends BaseEntity {
//    private Date date;
    @Enumerated(EnumType.STRING)
    private Weekday weekday;
    private String dtSupervisor;
    private String dtManager;
}
