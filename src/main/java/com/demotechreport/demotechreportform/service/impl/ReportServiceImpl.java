package com.demotechreport.demotechreportform.service.impl;

import com.demotechreport.demotechreportform.dto.ReportDTO;
import com.demotechreport.demotechreportform.entity.Report;
import com.demotechreport.demotechreportform.entity.VehicleDriver;
import com.demotechreport.demotechreportform.mapper.MapperUtil;
import com.demotechreport.demotechreportform.repository.ReportRepository;
import com.demotechreport.demotechreportform.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final MapperUtil mapperUtil;

    public ReportServiceImpl(ReportRepository reportRepository, MapperUtil mapperUtil) {
        this.reportRepository = reportRepository;
        this.mapperUtil = mapperUtil;
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

        reportRepository.save(reportEnt);
    }

    @Override
    public void delete(ReportDTO reportDTO) {

    }

    @Override
    public void update(ReportDTO reportDTO) {

    }

    @Override
    public ReportDTO findByDtSupervisor(String dtSupervisor) {
        return mapperUtil.convert(reportRepository.findByDtSupervisor(dtSupervisor), new ReportDTO());
    }

    @Override
    public ReportDTO findUserById(Long source) {
        return mapperUtil.convert(reportRepository.findById(source).get(),new ReportDTO());
    }
}
