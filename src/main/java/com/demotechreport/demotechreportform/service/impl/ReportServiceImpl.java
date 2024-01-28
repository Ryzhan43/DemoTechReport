package com.demotechreport.demotechreportform.service.impl;

import com.demotechreport.demotechreportform.dto.ReportDTO;
import com.demotechreport.demotechreportform.entity.Report;
import com.demotechreport.demotechreportform.mapper.MapperUtil;
import com.demotechreport.demotechreportform.repository.ReportRepository;
import com.demotechreport.demotechreportform.service.ReportService;
import org.springframework.stereotype.Service;

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
        reportRepository.save((mapperUtil.convert(reportDTO, new Report())));
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
