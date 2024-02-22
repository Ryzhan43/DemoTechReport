package com.demotechreport.demotechreportform.dto;

import com.demotechreport.demotechreportform.entity.VehicleDriver;
import com.demotechreport.demotechreportform.entity.common.BaseEntity;
import com.demotechreport.demotechreportform.enums.Vehicle;
import com.demotechreport.demotechreportform.enums.Weekday;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
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
    private String dtManager;
    private Integer projectNumber;
    private List<String> vehicleDriver;
    private String address;
    private boolean shopping;
    private boolean disposal;
    private boolean scrap;
    private List<EmployeeHoursDTO> employeeHours;
    private String notes;

    public String toPdfString(){
        StringBuilder strMain = new StringBuilder();

        //Date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MM:yyyy");
        strMain.append("\nDate of the project: " + simpleDateFormat.format(this.date) + "\n");
        System.out.println("+++++++++++++++");
        System.out.println(strMain);
        //Weekday
        strMain.append("WEEK DAY: " + this.getWeekday() + "\n");

        //Supervisor
        strMain.append("DT Supervisor: " + this.dtSupervisor+"\n");

        //Manager
        strMain.append("Manager: " + this.dtManager+"\n");

        //Vehicles
        for(String vehicleDriver1 : vehicleDriver){
            strMain.append(vehicleDriver1 + "\n");
        }

        //project number
        strMain.append("Project#: " + this.projectNumber+"\n");

        //Address
        strMain.append("Address: " + this.address+"\n");

        strMain.append("\n");
        strMain.append("=================================\n");

        //Shopping
        if(shopping) {
            strMain.append("Shopping: YES" + "\n");
        }

        //Disposal
        if(disposal) {
            strMain.append("Disposal: YES" + "\n");
        }

        //Disposal
        if(scrap) {
            strMain.append("Scrap: YES" + "\n");
        }

        strMain.append("=================================\n");

        //EmployeeHours

//        this.employeeHours.stream().forEach(a->
//
//                strMain.concat(a.getEmployeeDTO().getFirstName() + " " + a.getEmployeeDTO().getLastName() + " " + a.getStartTime() + " " + a.getEndTime() + "\n")
//
//        );

        //Note
        strMain.append("Notes: " + this.notes);

        System.out.println(strMain);
        return strMain.toString();
    }
}
