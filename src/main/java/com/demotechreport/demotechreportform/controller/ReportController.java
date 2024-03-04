package com.demotechreport.demotechreportform.controller;

import com.demotechreport.demotechreportform.dto.ReportDTO;
import com.demotechreport.demotechreportform.enums.Vehicle;
import com.demotechreport.demotechreportform.enums.Weekday;
import com.demotechreport.demotechreportform.service.EmployeeHoursService;
import com.demotechreport.demotechreportform.service.EmployeeService;
import com.demotechreport.demotechreportform.service.ReportService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;
    private final EmployeeService employeeService;
    private final EmployeeHoursService employeeHoursService;

    public ReportController(ReportService reportService, EmployeeService employeeService, EmployeeHoursService employeeHoursService) {
        this.reportService = reportService;
        this.employeeService = employeeService;
        this.employeeHoursService = employeeHoursService;
    }

    @GetMapping("/create")
   private String createReport(Model model){
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setEmployeeHours(new ArrayList<>());

        model.addAttribute("weekdays", Weekday.values());
        model.addAttribute("vehicles", Vehicle.values());
        model.addAttribute("report", reportDTO);
       return "/report/create-report";
   }

    @PostMapping("/submit-create-report")
    private String saveReport(@ModelAttribute("report") ReportDTO reportDTO){
        System.out.println(reportDTO);
        reportService.save(reportDTO);
        return "redirect:/report/list-reports";
    }

    @GetMapping("/list-reports")
    private String showReportList(Model model){
        model.addAttribute("reports", reportService.findAll());
        return "/report/reports-list";
    }

    @PostMapping("/delete/{id}")
    private String deleteReport(@PathVariable("id") Long id)
    {
        reportService.delete(id);
        return "redirect:/report/list-reports";
    }

}
