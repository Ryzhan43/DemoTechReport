package com.demotechreport.demotechreportform.service;

import com.demotechreport.demotechreportform.dto.ReportDTO;

public interface ReportService {
    void save(ReportDTO reportDTO);
    void delete(ReportDTO reportDTO);
    void update(ReportDTO reportDTO);

    ReportDTO findByDtSupervisor(String dtSupervisor);

    ReportDTO findUserById(Long source);
}
