package com.demotechreport.demotechreportform.dto;

import com.demotechreport.demotechreportform.enums.Vehicle;
import com.demotechreport.demotechreportform.enums.Weekday;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.apache.catalina.filters.AddDefaultCharsetFilter;

import java.util.Date;
import java.util.WeakHashMap;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class  ReportDTO {
    private Date date;

    private Weekday weekday;
    private String dtSupervisor;
    private String dtManager;
    private Vehicle vehicle;
    private String address;

}
