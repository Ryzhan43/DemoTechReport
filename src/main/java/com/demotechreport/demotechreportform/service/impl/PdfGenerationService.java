package com.demotechreport.demotechreportform.service.impl;

import com.demotechreport.demotechreportform.dto.ReportDTO;
import com.demotechreport.demotechreportform.entity.Report;
import com.demotechreport.demotechreportform.service.ReportService;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDMMType1Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.graphics.color.PDGamma;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class PdfGenerationService {

    private final ReportService reportService;

    public PdfGenerationService(ReportService reportService) {
        this.reportService = reportService;
    }


//
//    public byte[] generatePdf(Long id) throws IOException {
//        Document document = new Document();
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        PdfWriter.getInstance(document, byteArrayOutputStream);
//
//        document.open();
//        document.add(new Paragraph("PDF Content: " + reportService.findReportById(id).toPdfString()));
//        document.close();
//
//        return byteArrayOutputStream.toByteArray();
//    }


    public byte[] generatePdf(Long id) throws IOException {
        File oldFile = new File("src/main/resources/demo-tech-form.pdf");
        PDDocument document = PDDocument.load(oldFile);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PDPage page = document.getPage(0); // Get the first page
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
        ReportDTO reportDTO = reportService.findReportById(id);

        //Set up the Date
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
        contentStream.newLineAtOffset(115, 670); // Set the position for adding text
        contentStream.showText(String.valueOf(reportDTO.getDate().getDay()));
        contentStream.endText();

        //Set up the Month
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
        contentStream.newLineAtOffset(155, 670); // Set the position for adding text
        contentStream.showText(String.valueOf(reportDTO.getDate().getMonth()));
        contentStream.endText();

        //Set up the Supervisor
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 14);
        contentStream.newLineAtOffset(140, 630); // Set the position for adding text
        contentStream.showText(reportDTO.getDtSupervisor().toString());
        contentStream.endText();

        //Set up the Manager
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 14);
        contentStream.newLineAtOffset(325, 630); // Set the position for adding text
        contentStream.showText(reportDTO.getDtManager().toString());
        contentStream.endText();

        //Set up the Project number
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 14);
        contentStream.newLineAtOffset(140, 542);
        contentStream.showText(reportDTO.getProjectNumber().toString());
        contentStream.endText();

        contentStream.setNonStrokingColor(Color.white); // Set color for the rectangle
        contentStream.addRect(130, 590, 400, 20); // Define rectangle position and size
        contentStream.fill();
        contentStream.setNonStrokingColor(Color.white); // Set color for the rectangle
        contentStream.addRect(90, 570, 400, 20); // Define rectangle position and size
        contentStream.fill();

        //Vehicles
        contentStream.setNonStrokingColor(Color.black);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 14);
        contentStream.newLineAtOffset(120, 600);
        contentStream.showText(reportDTO.showVehicles());
        contentStream.endText();


        //Set up the Address
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 14);
        contentStream.newLineAtOffset(280, 542);
        contentStream.showText(reportDTO.getAddress().toString());
        contentStream.endText();

        PDColor red = new PDColor(new float[]{1, 0, 0}, PDDeviceRGB.INSTANCE);

        switch (reportDTO.getWeekday()) {
            case MONDAY:
                contentStream.setNonStrokingColor(red);
                drawEllipse(contentStream, 310, 670, 50, 25);
                contentStream.fill();
                break;
            case TUESDAY:
                contentStream.setNonStrokingColor(red);
                drawEllipse(contentStream, 380, 670, 57, 25);
                contentStream.fill();
                break;
            case WEDNESDAY:
                contentStream.setNonStrokingColor(red);
                drawEllipse(contentStream, 455, 670, 72, 25);
                contentStream.fill();
                break;
            case THURSDAY:
                contentStream.setNonStrokingColor(red);
                drawEllipse(contentStream, 243, 650, 72, 25);
                contentStream.fill();
                break;
            case FRIDAY:
                contentStream.setNonStrokingColor(red);
                drawEllipse(contentStream, 325, 650, 50, 25);
                contentStream.fill();
                break;
            case SATURDAY:
                contentStream.setNonStrokingColor(red);
                drawEllipse(contentStream, 385, 650, 75, 25);
                contentStream.fill();
                break;
            case SUNDAY:
                contentStream.setNonStrokingColor(red);
                drawEllipse(contentStream, 470, 650, 55, 25);
                contentStream.fill();
                break;
            default:
                System.out.println("Invalid day");

        }
        // SEt up Disposal
        if(reportDTO.isDisposal()){
            contentStream.setNonStrokingColor(red);
            drawEllipse(contentStream, 242, 195, 30, 25);
            contentStream.fill();
        } else {
            contentStream.setNonStrokingColor(red);
            drawEllipse(contentStream, 279, 195, 25, 25);
            contentStream.fill();
        }

        if(reportDTO.isShopping()){
            contentStream.setNonStrokingColor(red);
            drawEllipse(contentStream, 402, 195, 30, 25);
            contentStream.fill();
        } else {
            contentStream.setNonStrokingColor(red);
            drawEllipse(contentStream, 445, 195, 25, 25);
            contentStream.fill();
        }


        contentStream.close();


        document.save(byteArrayOutputStream);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    private void drawEllipse(PDPageContentStream contentStream, float x, float y, float width, float height) throws IOException{
        float x0 = x + width / 2;
        float y0 = y + height / 2;
        float rx = width / 2;
        float ry = height / 2;
        float magicNumber = 0.551784f;

        // Starting point
        contentStream.moveTo(x0 + rx, y0);

        // First curve
        contentStream.curveTo(x0 + rx, y0 + magicNumber * ry, x0 + magicNumber * rx, y0 + ry, x0, y0 + ry);

        // Second curve
        contentStream.curveTo(x0 - magicNumber * rx, y0 + ry, x0 - rx, y0 + magicNumber * ry, x0 - rx, y0);

        // Third curve
        contentStream.curveTo(x0 - rx, y0 - magicNumber * ry, x0 - magicNumber * rx, y0 - ry, x0, y0 - ry);

        // Fourth curve
        contentStream.curveTo(x0 + magicNumber * rx, y0 - ry, x0 + rx, y0 - magicNumber * ry, x0 + rx, y0);

        contentStream.closePath();
        contentStream.setStrokingColor(255, 0, 0); // Set stroke color to red
        contentStream.stroke();
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

                contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
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
