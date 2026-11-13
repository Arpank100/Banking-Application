package com.app.eshiksa.utility;

import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.html.table.Table;

import java.io.File;
import java.io.FileOutputStream;

import javax.swing.text.Document;

public class AprstcPaymentReceipt {
//	public static void main(String[] args) {
	public static void main(String[] args) {
	try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("PaymentReceipt.pdf"));
			document.open();
			Image logo = Image.getInstance("/home/parvinder/Pictures/GetepayLogo.png");  // Or provide full path like "C:/path/to/logo.png"
			logo.scaleToFit(80, 80);  // Adjust size as needed
			logo.setAlignment(Image.ALIGN_CENTER);  // Center the logo
			document.add(logo);
			document.add(Chunk.NEWLINE);
			// Font definitions
			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
			Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
			Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

			// Title
			Paragraph title = new Paragraph("APPTD Payment Receipt Details", titleFont);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			document.add(Chunk.NEWLINE);

			// Payment Info Table
			PdfPTable paymentTable = new PdfPTable(4);
			paymentTable.setWidthPercentage(100);
			paymentTable.setWidths(new float[] { 2, 3, 2, 3 });

			paymentTable.addCell(getCell("Payment Date:", boldFont));
			paymentTable.addCell(getCell("29/04/2025 12:10", normalFont));
			paymentTable.addCell(getCell("Pay Mode:", boldFont));
			paymentTable.addCell(getCell("UPI", normalFont));

			paymentTable.addCell(getCell("Transaction Number:", boldFont));
			paymentTable.addCell(getCell("124578890921098", normalFont));
			paymentTable.addCell(getCell("Status:", boldFont));
			paymentTable.addCell(getCell("Success", normalFont));

			document.add(paymentTable);
			document.add(Chunk.NEWLINE);

			// Account Info Table
			PdfPTable accountTable = new PdfPTable(4);
			accountTable.setWidthPercentage(100);
			accountTable.setWidths(new float[] { 2, 3, 2, 3 });

			accountTable.addCell(getCell("Account Number:", boldFont));
			accountTable.addCell(getCell("128798097986", normalFont));
			accountTable.addCell(getCell("Contact Number:", boldFont));
			accountTable.addCell(getCell("98079866", normalFont));

			accountTable.addCell(getCell("Account Name:", boldFont));
			accountTable.addCell(getCell("Suresh", normalFont));
			accountTable.addCell(getCell("GST Number:", boldFont));
			accountTable.addCell(getCell("26APFHV23451ZH", normalFont));

			document.add(accountTable);
			document.add(Chunk.NEWLINE);

			// Invoice Table
			PdfPTable invoiceTable = new PdfPTable(5);
			invoiceTable.setWidthPercentage(100);
			invoiceTable.setWidths(new float[] { 2, 2, 4, 3, 3 });

			invoiceTable.addCell(getHeaderCell("Inv No"));
			invoiceTable.addCell(getHeaderCell("Date"));
			invoiceTable.addCell(getHeaderCell("Description"));
			invoiceTable.addCell(getHeaderCell("Due Amount"));
			invoiceTable.addCell(getHeaderCell("Paid Amount"));

			invoiceTable.addCell(getCell("1234/2425/124", normalFont));
			invoiceTable.addCell(getCell("01-Jan-25", normalFont));
			invoiceTable.addCell(getCell("Rent Charges", normalFont));
			invoiceTable.addCell(getCell("4,000.00", normalFont));
			invoiceTable.addCell(getCell("4,000.00", normalFont));

			invoiceTable.addCell(getCell("", normalFont));
			invoiceTable.addCell(getCell("", normalFont));
			invoiceTable.addCell(getCell("Electrical Charges", normalFont));
			invoiceTable.addCell(getCell("2,000.00", normalFont));
			invoiceTable.addCell(getCell("2,000.00", normalFont));

			invoiceTable.addCell(getCell("", normalFont));
			invoiceTable.addCell(getCell("", normalFont));
			invoiceTable.addCell(getCell("Maintenance", normalFont));
			invoiceTable.addCell(getCell("1,000.00", normalFont));
			invoiceTable.addCell(getCell("1,000.00", normalFont));

			document.add(invoiceTable);
			document.add(Chunk.NEWLINE);

			// Total Amount
			Paragraph total = new Paragraph("Total Amount Paid: 7,000.00", boldFont);
			total.setAlignment(Element.ALIGN_RIGHT);
			document.add(total);

			document.add(new Paragraph("In words: Seven Thousand only", normalFont));

			document.close();
			System.out.println("PDF created successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Helper methods
	private static PdfPCell getCell(String text, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}

	private static PdfPCell getHeaderCell(String text) {
		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
		PdfPCell cell = new PdfPCell(new Phrase(text, headerFont));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		return cell;
	}
}
