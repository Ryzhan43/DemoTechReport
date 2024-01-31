package com.demotechreport.demotechreportform.controller;

import com.demotechreport.demotechreportform.dto.ReportDTO;
import com.demotechreport.demotechreportform.enums.Vehicle;
import com.demotechreport.demotechreportform.enums.Weekday;
import com.demotechreport.demotechreportform.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/create")
   private String createReport(Model model){
        model.addAttribute("weekdays", Weekday.values());
        model.addAttribute("vehicles", Vehicle.values());
        model.addAttribute("report", new ReportDTO());
       return "/report/create-report";
   }

   @PostMapping("/submit-create-report")
   private String saveReport(@ModelAttribute("report") ReportDTO reportDTO){
       System.out.println(reportDTO);
       return "redirect:/report/create";
   }

}
