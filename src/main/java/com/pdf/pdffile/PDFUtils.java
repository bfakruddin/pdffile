package com.pdf.pdffile;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.*;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDFUtils {
    public static void main(String[] args) throws IOException {
        PDFUtils pdfUtils = new PDFUtils();
//        pdfUtils.addTextToPDF();
//        pdfUtils.addImageToPDF();
//        pdfUtils.createCustomizedPDFFile();
        pdfUtils.chauffeurSalaryPDFFile();
    }

    private void chauffeurSalaryPDFFile() throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.setLeading(15f);


        MyTextClass myTextClass = new MyTextClass(document, contentStream);

        // Font set
        PDFont pdFont = PDType1Font.HELVETICA;
        PDFont headingFont = PDType1Font.HELVETICA_BOLD;
        PDFont boldFont = PDType1Font.HELVETICA_BOLD;
        PDFont italicFont = PDType1Font.HELVETICA_OBLIQUE;

        // capture page width and height
        int pageWidth = (int) page.getTrimBox().getWidth();
        int pageHeight = (int) page.getTrimBox().getHeight();
        System.out.println("Page Width: "+pageWidth + " Page Height : "+pageHeight);
        Format d_format = new SimpleDateFormat("dd MMM yyyy");
        Format t_format = new SimpleDateFormat("HH:mm");

        // Date Field
        float dateTextWidth = myTextClass.getTextWidth("Date: "+d_format.format(new Date()), pdFont, 16);

        System.out.println((int)pageWidth-10-dateTextWidth);

        myTextClass.addSingleLineText("Date: "+d_format.format(new Date()), (int) (pageWidth-10-dateTextWidth),
                pageHeight-65, pdFont, 9, Color.BLACK);

        // Driver Salary heading
        String heading = "Driver Salary Receipt";
        float headingTextWidth = myTextClass.getTextWidth(heading, headingFont, 12);
        myTextClass.addSingleLineText(heading, (int) ((pageWidth/2)-(headingTextWidth/2)), pageHeight-90, headingFont,
                12,
                Color.BLACK);

        // Print salary details
        // Adding string at right alignment top
        String vehicleNumber_Constant = "Vehicle Number : ";
        String vehicleNumber = "TS07 HW 5578";
        myTextClass.addSingleLineText(vehicleNumber_Constant+ vehicleNumber, 50, pageHeight-120, pdFont, 10, Color.BLACK);

        String salaryOfTheMonth_Constant = "Salary of the Month : ";
        String salaryOfTheMonth = "December";
        myTextClass.addSingleLineText(salaryOfTheMonth_Constant + salaryOfTheMonth, 50, pageHeight-140, pdFont, 10,
                Color.BLACK);

        String amountPaid_Constant = "Amount Paid : ";
        String amountPaid = "15000";
        myTextClass.addSingleLineText(amountPaid_Constant + amountPaid, 50, pageHeight-160, pdFont, 10,
                Color.BLACK);

        String date_Constant = "Date : ";
        String date = d_format.format(new Date());

        myTextClass.addSingleLineText(date_Constant+date, 50,
                pageHeight-180, pdFont, 10, Color.BLACK);

        String employeeName = "Baba Fakruddin Dudekula";
        String driverName = "Bomma Nagarjuna";
        // Print the certificate declaration
        String employeeDeclaration = "Received From Mr./Ms. " +
                employeeName +
                amountPaid+" to driver " +
                driverName +
                " towards salary of the month of "+salaryOfTheMonth;
        myTextClass.addSingleLineText(employeeDeclaration, 50, pageHeight-210, pdFont, 20, Color.BLACK);

        String employeeName_Constant = "Employee Name : ";
        float employeeName_Length = myTextClass.getTextWidth(employeeName_Constant+ employeeName, pdFont, 12);
        myTextClass.addSingleLineText(employeeName_Constant + employeeName, (int) (pageWidth-10-employeeName_Length),
                pageHeight-240, pdFont, 10, Color.BLACK);

        //Add Image of stamp with driver salary at the bottom left of the page
        PDImageXObject stampWithDriverSign = PDImageXObject.createFromFile("/Users/baba/Documents/Technical" +
                "-Practicing/Java_Projects" +
                "/pdffile/src/main/resources/Images/stamp_With_Driver_Sign.png", document);
        contentStream.drawImage(stampWithDriverSign, 55, 200, 95, 70);

        //Add Driver Sign at the right bottom
        PDImageXObject driverSign = PDImageXObject.createFromFile("/Users/baba/Documents/Technical-Practicing" +
                "/Java_Projects/pdffile/src/main/resources/Images/Driver_Sign.png", document);
        contentStream.drawImage(driverSign, 400, 180, 130, 40);

        // Add Driver Name below Signature
        myTextClass.addSingleLineText("Driver Name: Bomma Nagarjuna", 380, 160, pdFont, 14, Color.BLACK);

        // Add Driver license number below Driver Name
        PDImageXObject driverLicenseNumber = PDImageXObject.createFromFile("/Users/baba/Documents/Technical" +
                "-Practicing/Java_Projects/pdffile/src/main/resources/Images/Driver_License.png", document);
        contentStream.drawImage(driverLicenseNumber, 380, 80, 200, 70);


        //Close content Stream
        contentStream.close();
        document.save(new File("/Users/baba/Documents/chauffeurSalary.pdf"));
        document.close();
    }

    private void createCustomizedPDFFile() throws IOException {

        // Create customized inputs of text , table in a pdf

        PDDocument pdDocument = new PDDocument();
        PDPage pdPage = new PDPage();
        pdDocument.addPage(pdPage);
        PDPageContentStream pdPageContentStream = new PDPageContentStream(pdDocument, pdPage);
        int pageWidth = (int) pdPage.getTrimBox().getWidth();
        int pageHeight = (int) pdPage.getTrimBox().getHeight();

        PDImageXObject pdImageXObject = PDImageXObject.createFromFile("/Users/baba/Documents/Technical-Practicing" +
                "/Java_Projects/pdffile/src/main/resources/Images/images.jpeg", pdDocument);
        pdPageContentStream.drawImage(pdImageXObject, 0, pageHeight-233, pageWidth, 239);
//        pdPageContentStream.drawImage(pdImageXObject, 0, 75, 320, 239);

        // add Text
        String name = "Baba Fakruddin D";
        String contact = "9866641635";

        Format d_format = new SimpleDateFormat("dd/MM/yyyy");
        Format t_format = new SimpleDateFormat("HH:mm");

        MyTextClass myTextClass = new MyTextClass(pdDocument, pdPageContentStream);

        PDFont pdFont = PDType1Font.HELVETICA;
        PDFont italicFont = PDType1Font.HELVETICA_OBLIQUE;

        // Adding string at right alignment top
        String[] contactDetails = new String[]{"9866641635", "8008704141"};
        myTextClass.addMultiLineText(contactDetails, 18, (int) (pageWidth-pdFont.getStringWidth("8008704141")/1000 * 15 -10),
                pageHeight-25, pdFont, 15, Color.BLACK);

        // Text with big font
        myTextClass.addSingleLineText("Indian Tadka", 25, pageHeight-150, pdFont, 40, Color.BLACK);

        myTextClass.addSingleLineText("Customer Name "+name, 25, pageHeight-250, pdFont, 16, Color.BLACK);
        myTextClass.addSingleLineText("Mo. No "+contact, 25, pageHeight-274, pdFont, 16, Color.BLACK);

        String invoiceNo = "Invoice #"+ (int) Math.random();
        float textWidth = myTextClass.getTextWidth(invoiceNo, pdFont, 16);
        myTextClass.addSingleLineText(invoiceNo, (int)(pageWidth-25-textWidth), pageHeight-250, pdFont, 16, Color.BLACK);

        float dateTextWidth = myTextClass.getTextWidth("Date: "+d_format.format(new Date()), pdFont, 16);

        myTextClass.addSingleLineText("Date: "+d_format.format(new Date()), (int) (pageWidth-25-dateTextWidth),
                pageHeight-274, pdFont, 16, Color.BLACK);

        String time = t_format.format(new Date());
        float timeTextWidth = myTextClass.getTextWidth("Time"+time, pdFont, 16);
        myTextClass.addSingleLineText("Time: "+time, (int) (pageWidth-25-timeTextWidth), pageHeight-298, pdFont, 16,
                Color.BLACK);

        // add multi line text
        String[] paymentMethod = {"Methods of payment we accept: ", "Cash, PhonePe, GPay, RuPay, Maestro,", "Visa, " +
                "MasterCard and American Express"};
        myTextClass.addMultiLineText(paymentMethod, 15, 25, 180, italicFont, 10, new Color(122, 122, 122));

        // authorise Signatory
        pdPageContentStream.setStrokingColor(Color.BLACK);
        pdPageContentStream.setLineWidth(2);
        pdPageContentStream.moveTo(pageWidth-250, 150);
        pdPageContentStream.lineTo(pageWidth-25, 150);
        pdPageContentStream.stroke();

        String authoSign = "Authorised Signatory";
        float authoSignWidth = myTextClass.getTextWidth(authoSign, italicFont, 16);
        int xPosition = pageWidth-250+pageWidth-25;
        myTextClass.addSingleLineText(authoSign, (int)(xPosition-authoSignWidth)/2, 125, italicFont, 16, Color.BLACK);

        pdPageContentStream.close();
        pdDocument.save("/Users/baba/Documents/Customized.pdf");
        pdDocument.close();

    }

    private void createPDFFile(String filePath) throws IOException {
        PDDocument pdDocument = new PDDocument();
        PDPage pdPage = new PDPage();
        pdDocument.addPage(pdPage);
        pdDocument.save("/Users/baba/Documents/Raja.pdf");
        pdDocument.save(new File("/Users/baba/Documents/Raja.pdf"));
        pdDocument.close();
    }

    private void loadPDFFile() throws IOException {
        File oldFile = new File("/Users/baba/Documents/Shareef_BIO_DATA.pdf");
        PDDocument pdDocument = PDDocument.load(oldFile);
        pdDocument.addPage(new PDPage());
        pdDocument.save("/Users/baba/Documents/Raja.pdf");
    }

    private void addTextToPDF() throws IOException {
        PDDocument pdDocument = new PDDocument();
        PDPage pdPage = new PDPage();
        pdDocument.addPage(pdPage);

        PDPageContentStream pdPageContentStream = new PDPageContentStream(pdDocument, pdPage);
        pdPageContentStream.beginText();
        pdPageContentStream.setFont(PDType1Font.TIMES_ROMAN, 18);
        pdPageContentStream.setLeading(16.0f);
        pdPageContentStream.newLineAtOffset(25, pdPage.getTrimBox().getHeight()-25);

        String line1 = "This is first line";
        String line2 = "This is second line";
        String line3 = "This is third line";

        pdPageContentStream.showText(line1);
        pdPageContentStream.newLine();
        pdPageContentStream.showText(line2);
        pdPageContentStream.newLine();
        pdPageContentStream.showText(line3);
        pdPageContentStream.newLine();

        pdPageContentStream.endText();
        pdPageContentStream.close();

        pdDocument.save("/Users/baba/Documents/Technical-Practicing/savedPDFs/chauffeurSalary.pdf");
        pdDocument.close();

    }

    private void addImageToPDF() throws IOException {
        PDDocument pdDocument = new PDDocument();
        PDPage pdPage = new PDPage();
//        pdDocument.getDocument();
        pdDocument.addPage(pdPage);

        PDImageXObject pdImageXObject = PDImageXObject.createFromFile("/Users/baba/Documents/Technical-Practicing" +
                "/savedPDFs/revenue_stamp.jpg", pdDocument);
        PDPageContentStream pdPageContentStream = new PDPageContentStream(pdDocument, pdPage);
        pdPageContentStream.drawImage(pdImageXObject, 75, 75, 320, 212);
        pdPageContentStream.close();


        pdDocument.save("/Users/baba/Documents/Technical-Practicing/savedPDFs/chauffeurSalary.pdf");
        pdDocument.close();

    }

    private static class MyTextClass{
        PDDocument pdDocument;
        PDPageContentStream pdPageContentStream;

        public MyTextClass(PDDocument pdDocument, PDPageContentStream pdPageContentStream) {
            this.pdDocument = pdDocument;
            this.pdPageContentStream = pdPageContentStream;
        }

        void addSingleLineText(String text, int xPosition, int yPosition, PDFont font, float fontSize, Color color) throws IOException {
            pdPageContentStream.beginText();
            pdPageContentStream.setFont(font, fontSize);
            pdPageContentStream.setNonStrokingColor(color);
            pdPageContentStream.newLineAtOffset(xPosition, yPosition);
            pdPageContentStream.showText(text);
            pdPageContentStream.endText();
            pdPageContentStream.moveTo(0, 0);
        }

        void addMultiLineText(String[] textArray, float leading, int xPosition, int yPosition, PDFont font,
                              float fontSize, Color color) throws IOException {
            pdPageContentStream.beginText();
            pdPageContentStream.setFont(font, fontSize);
            pdPageContentStream.setNonStrokingColor(color);
            pdPageContentStream.setLeading(leading);
            pdPageContentStream.newLineAtOffset(xPosition, yPosition);

            for (String textLine: textArray){
                pdPageContentStream.showText(textLine);
                pdPageContentStream.newLine();
            }

            pdPageContentStream.endText();
            pdPageContentStream.moveTo(0, 0);
        }

        float getTextWidth(String text, PDFont font, float fontSize) throws IOException {
            return font.getStringWidth(text)/1000 * fontSize;
        }
    }

    private static class MyTableClass{
        PDDocument pdDocument;
        PDPageContentStream pdPageContentStream;
        private int[] colWidths;
        private int cellHeight;
        private int xPosition;
        private int yPosition;
        private int colPosition;
        private int xInitialPosition;
        private float fontSize;
        private PDFont font;
        private Color fontColor;

        public MyTableClass(PDDocument pdDocument, PDPageContentStream pdPageContentStream) {
            this.pdDocument = pdDocument;
            this.pdPageContentStream = pdPageContentStream;
        }

        void setTable(int[] colWidths, int cellHeight, int xPosition, int yPosition){
            this.colWidths = colWidths;
            this.cellHeight = cellHeight;
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            xInitialPosition = xPosition;

        }

        void setTableFont(PDFont font, float fontSize, Color fontColor){
            this.font = font;
            this.fontSize = fontSize;
            this.fontColor = fontColor;
        }

        void addCell(String text, Color fillColor) throws IOException {
            pdPageContentStream.setStrokingColor(1f);

            if(fillColor != null){
                pdPageContentStream.setNonStrokingColor(fillColor);
            }

            pdPageContentStream.addRect(xPosition, yPosition, colWidths[colPosition], cellHeight);

            if (fillColor == null){
                pdPageContentStream.stroke();
            }else{
                pdPageContentStream.fillAndStroke();
            }

            pdPageContentStream.beginText();
            pdPageContentStream.setNonStrokingColor(fontColor);

            // Values in cell to align right
            if (colPosition == 4 || colPosition == 2){
                float fontWidth = font.getStringWidth(text)/1000 * fontSize;
                pdPageContentStream.newLineAtOffset(xPosition + colWidths[colPosition]-20-fontWidth, yPosition+10);
            }else{
                pdPageContentStream.newLineAtOffset(xPosition+20, yPosition+10);
            }

            pdPageContentStream.showText(text);
            pdPageContentStream.endText();

            xPosition = xPosition + colWidths[colPosition];
            colPosition++;

            // Shift to next row once reach the last column
            if (colPosition == colWidths.length){
                colPosition = 0;
                xPosition = xInitialPosition;
                yPosition = yPosition-cellHeight;
            }
        }


    }

}
