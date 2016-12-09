<<<<<<< HEAD
package com.ttl.customersocialapp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGenerator {
	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(
			Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	public static String starloc = "", endloc = "", strttime = "",
			endtime = "", dst = "", jdate = "";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(
					"C:/PDF/CreateTable.pdf"));
			document.open();
		//	addMetaData(document);
			addTitlePage(document);
			createTable(document);
			document.close();

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}

	}

	static void addMetaData(Document document) {
		document.addTitle("Create PDF table");
		document.addSubject("Create PDF table");
		document.addAuthor("Java Honk");
		document.addCreator("Java Honk");
	}

	static void addTitlePage(Document document) throws DocumentException {

		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);
		preface.add(new Paragraph("YOUR JOURNEY HISTORY", TIME_ROMAN));

		creteEmptyLine(preface, 1);
	/*	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		preface.add(new Paragraph("Table created on "
				+ simpleDateFormat.format(new Date()), TIME_ROMAN_SMALL));*/
		document.add(preface);

	}

	static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	static void createTable(Document document) throws DocumentException {

		Paragraph paragraph = new Paragraph();
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(6);

		PdfPCell c1 = new PdfPCell(new Phrase("Start Loc"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("End Loc"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Start Time"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("End Time"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Distance"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Journy Date"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		table.setHeaderRows(1);

		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(starloc);
		table.addCell(endloc);
		table.addCell(strttime);
		table.addCell(endtime);
		table.addCell(dst);
		table.addCell(jdate);

		/*
		 * for (int i = 0; i < 5; i++) {
		 * table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		 * table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		 * table.addCell("Java"); table.addCell("Honk");
		 * table.addCell("Success"); }
		 */

		document.add(table);
	}

}
=======
package com.ttl.customersocialapp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGenerator {
	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	
	public static String starloc = "", endloc = "", strttime = "",
			endtime = "", dst = "", jdate = "";

	
	public static void main(String[] args) {

		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(
					"C:/PDF/CreateTable.pdf"));
			document.open();
		
			addTitlePage(document);
			createTable(document);
			document.close();

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}

	}

	static void addMetaData(Document document) {
		document.addTitle("Create PDF table");
		document.addSubject("Create PDF table");
		document.addAuthor("Java Honk");
		document.addCreator("Java Honk");
	}

	static void addTitlePage(Document document) throws DocumentException {

		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);
		preface.add(new Paragraph("YOUR JOURNEY HISTORY", TIME_ROMAN));

		creteEmptyLine(preface, 1);
	
		document.add(preface);

	}

	static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	static void createTable(Document document) throws DocumentException {

		Paragraph paragraph = new Paragraph();
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(6);

		PdfPCell c1 = new PdfPCell(new Phrase("Start Loc"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("End Loc"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Start Time"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("End Time"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Distance"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Journy Date"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		table.setHeaderRows(1);

		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(starloc);
		table.addCell(endloc);
		table.addCell(strttime);
		table.addCell(endtime);
		table.addCell(dst);
		table.addCell(jdate);

		

		document.add(table);
	}

}
>>>>>>> TMSC QA 3.4V
