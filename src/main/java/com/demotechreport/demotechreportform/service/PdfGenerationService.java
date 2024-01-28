package com.demotechreport.demotechreportform.service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfGenerationService {

    private final ReportService reportService;

    public PdfGenerationService(ReportService reportService) {
        this.reportService = reportService;
    }



    public byte[] generatePdf(String dtSupervisor) throws IOException {
        Document document = new Document();
        ByteArrayOutputStream   byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();
        document.add(new Paragraph("PDF Content: " + reportService.findByDtSupervisor(dtSupervisor).toString()));
        document.close();

        return byteArrayOutputStream.toByteArray();
    }


    public byte[] generatePdfWithTable() throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                float margin = 50;
                float yStart = page.getMediaBox().getHeight() - margin;
                float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
                float yPosition = yStart;
                float tableHeight = 100f; // Adjust as needed
                float marginX = 5f;

                int rows = 5; // Number of rows in the table
                int cols = 3; // Number of columns in the table
                float rowHeight = 20f;
                float tableWidthMargin = tableWidth / cols;
                float tableHeightMargin = tableHeight / rows;

                // Draw table headers
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                float nextX = marginX;
                float nextY = yPosition;
                for (int i = 0; i < cols; i++) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(nextX, nextY);
                    contentStream.showText("Header " + (i + 1));
                    contentStream.endText();
                    nextX += tableWidthMargin;
                }

                // Draw table rows
                nextY -= rowHeight;
                for (int i = 0; i < rows; i++) {
                    nextX = marginX;
                    contentStream.setLineWidth(1f);
                    contentStream.moveTo(marginX, nextY);
                    contentStream.lineTo(marginX + tableWidth, nextY);
                    contentStream.stroke();

                    for (int j = 0; j < cols; j++) {
                        contentStream.beginText();
                        contentStream.newLineAtOffset(nextX, nextY - 15); // Adjust vertical alignment
                        contentStream.showText("Data " + (i + 1) + "," + (j + 1));
                        contentStream.endText();
                        nextX += tableWidthMargin;
                    }

                    nextY -= rowHeight;
                }

                contentStream.close();
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return outputStream.toByteArray();
        }
    }
}
