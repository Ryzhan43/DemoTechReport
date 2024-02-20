package com.demotechreport.demotechreportform.service;

import com.demotechreport.demotechreportform.dto.ReportDTO;

import java.util.List;

public interface ReportService {
    void save(ReportDTO reportDTO);
    void delete(Long id);
    void update(ReportDTO reportDTO);
    ReportDTO findByDtSupervisor(String dtSupervisor);
    ReportDTO findReportById(Long id);

    List<ReportDTO> findAll();
}
