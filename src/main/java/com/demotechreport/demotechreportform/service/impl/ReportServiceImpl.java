package com.demotechreport.demotechreportform.service.impl;

import com.demotechreport.demotechreportform.dto.EmployeeDTO;
import com.demotechreport.demotechreportform.dto.EmployeeHoursDTO;
import com.demotechreport.demotechreportform.dto.ReportDTO;
import com.demotechreport.demotechreportform.entity.Employee;
import com.demotechreport.demotechreportform.entity.EmployeeHours;
import com.demotechreport.demotechreportform.entity.Report;
import com.demotechreport.demotechreportform.entity.VehicleDriver;
import com.demotechreport.demotechreportform.mapper.MapperUtil;
import com.demotechreport.demotechreportform.repository.ReportRepository;
import com.demotechreport.demotechreportform.service.EmployeeHoursService;
import com.demotechreport.demotechreportform.service.EmployeeService;
import com.demotechreport.demotechreportform.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final MapperUtil mapperUtil;
    private final EmployeeService employeeService;
    private final EmployeeHoursService employeeHoursService;


    public ReportServiceImpl(ReportRepository reportRepository, MapperUtil mapperUtil, EmployeeService employeeService, EmployeeHoursService employeeHoursService) {
        this.reportRepository = reportRepository;
        this.mapperUtil = mapperUtil;
        this.employeeService = employeeService;
        this.employeeHoursService = employeeHoursService;
    }

    @Override
    public void save(ReportDTO reportDTO) {
        List<String> vehicleDriverListTemp = reportDTO.getVehicleDriver();
        Report reportEnt = mapperUtil.convert(reportDTO, new Report());
        List<VehicleDriver> vd = new ArrayList<>();


        if(vehicleDriverListTemp.isEmpty() != true){
            for(String vehicleDriverI : vehicleDriverListTemp){
                vd.add(new VehicleDriver(vehicleDriverI));
            }
            reportEnt.setVehicleDriver(vd);
        }

        if(reportEnt.getEmployeeHours() != null && !reportEnt.getEmployeeHours().isEmpty()) {
            List<EmployeeHours> employeeHoursConverted = reportDTO.getEmployeeHours().stream().map(a->employeeHoursService.convertToEntity(a)).collect(Collectors.toUnmodifiableList());
            reportEnt.setEmployeeHours(employeeHoursConverted);

            for (EmployeeHours employeeHours : reportEnt.getEmployeeHours()) {
                employeeHours.setReport(reportEnt);
            }
        }

        reportRepository.save(reportEnt);
    }

    @Override
    public void delete(Long id) {
        ReportDTO reportDTO = findReportById(id);
        reportRepository.delete(mapperUtil.convert(reportDTO, new Report()));
    }

    @Override
    public ReportDTO findReportById(Long id) {
        Report report = reportRepository.findById(id).get();

        return mapperUtil.convert(report, new ReportDTO());
    }

    @Override
    public void update(ReportDTO reportDTO) {

    }

    @Override
    public ReportDTO findByDtSupervisor(String dtSupervisor) {
        return mapperUtil.convert(reportRepository.findByDtSupervisor(dtSupervisor), new ReportDTO());
    }


    @Override
    public List<ReportDTO> findAll() {
        return reportRepository.findAll().stream().map(a->mapperUtil.convert(a, new ReportDTO())).collect(Collectors.toUnmodifiableList());
    }
}
