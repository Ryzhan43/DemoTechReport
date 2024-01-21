package com.demotechreport.demotechreportform.controller;

import com.demotechreport.demotechreportform.dto.ReportDTO;
import com.demotechreport.demotechreportform.enums.Weekday;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class ReportController {
   @GetMapping("/create")
   private String createReport(Model model){
        model.addAttribute("weekdays", Weekday.values());
        model.addAttribute("report", new ReportDTO());
       return "/report/create-report";
   }

   @PostMapping("/submit-create-report")
   private String saveReport(@ModelAttribute("report") ReportDTO reportDTO){
       System.out.println(reportDTO.toString());
       return "redirect:/report/create";
   }

}
