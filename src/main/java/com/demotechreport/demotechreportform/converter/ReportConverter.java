package com.demotechreport.demotechreportform.converter;

import com.demotechreport.demotechreportform.dto.ReportDTO;
import com.demotechreport.demotechreportform.repository.ReportRepository;
import com.demotechreport.demotechreportform.service.ReportService;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;

public class ReportConverter implements Converter<Long, ReportDTO> {
    private final ReportService reportService;
    private final ReportRepository reportRepository;

    public ReportConverter(@Lazy ReportService reportService, ReportRepository reportRepository) {
        this.reportService = reportService;
        this.reportRepository = reportRepository;
    }

    @Override
    public ReportDTO convert(Long id) {
        return reportService.findUserById(id);
    }
}
