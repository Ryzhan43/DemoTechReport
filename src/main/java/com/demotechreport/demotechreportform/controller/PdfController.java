package com.demotechreport.demotechreportform.controller;

import com.demotechreport.demotechreportform.service.ReportService;
import com.demotechreport.demotechreportform.service.impl.PdfGenerationService;
import com.lowagie.text.DocumentException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/pdf")
public class PdfController {


    final private PdfGenerationService pdfGenerationService;
    final private ReportService reportService;

    public PdfController(PdfGenerationService pdfGenerationService, ReportService reportService) {
        this.pdfGenerationService = pdfGenerationService;
        this.reportService = reportService;
    }

    @PostMapping("/generate/{id}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable("id") Long id) {
        try {
            byte[] pdfBytes = pdfGenerationService.generatePdf(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
//            headers.setContentDispositionFormData("attachment", "example.pdf");
            headers.setContentDispositionFormData("attachment", "demo-tech-form.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (DocumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
