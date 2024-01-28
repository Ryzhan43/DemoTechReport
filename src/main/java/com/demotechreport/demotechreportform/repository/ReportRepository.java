package com.demotechreport.demotechreportform.repository;

import com.demotechreport.demotechreportform.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    public Optional<Report> findById(Long id);

    Report findByDtSupervisor(String dtSupervisor);
}
