package com.competency.pdfgenerator.examples;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;

import com.competency.pdfgenerator.PDFCreator;
import com.competency.pdfgenerator.utilities.XMLUtil;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;


public class NewlineDemo {
	private static Log _log = LogFactory.getLog(NewlineDemo.class);
	public static void main(String[] args) {
		try {
			_log.info("NewlineDemo.main() START");
			createNewline();
			_log.info("NewlineDemo.main() END");
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	private static void createNewline() {
		_log.info("NewlineDemo.createNewline()  START");
		try {
			Locale locale = Locale.ENGLISH;
			String language = locale.getLanguage();
			Document pdfStructureDom = XMLUtil.getDomObj(FieldDataDemo.class.getClassLoader().getResourceAsStream("examples/XMLFiles/newline.xml"));
			com.itextpdf.text.Document outputPDFDocument = new com.itextpdf.text.Document(PageSize.A4);
			PdfWriter.getInstance(
					outputPDFDocument,
							new FileOutputStream("src/main/resources/examples/PDFFiles/newlineOutput.pdf"));
			
			addingNewline(pdfStructureDom, null, outputPDFDocument, language);
			_log.info("NewlineDemo.createNewline()  END");
		} catch (FileNotFoundException e) {
			_log.error(e);
		} catch (DocumentException e) {
			_log.error(e);
		} catch (Exception e) {
			_log.error(e);
		}
	}

	private static void addingNewline(Document pdfStructureDom,
			Object object, com.itextpdf.text.Document outputPDFDocument,
			String language) {
		try {
			PDFCreator.processPDF(pdfStructureDom, null, outputPDFDocument, language);
		} catch (Exception e) {
			_log.error(e);
		}
		
	}

}
