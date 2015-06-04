package com.competency.pdfgenerator.examples;

import com.competency.pdfgenerator.PDFCreator;
import com.competency.pdfgenerator.utilities.XMLUtil;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Locale;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;


public class LabelDemo {

	private static Log _log = LogFactory.getLog(LabelDemo.class);
	public static void main(String[] args) {
		try {
			_log.info("LabelDemo.main() START");
			createLabel();
			_log.info("LabelDemo.main() END");
		} catch (Exception ex) {
			_log.error(ex);
		}

	}

	private static void createLabel() {
		_log.info("LabelDemo.createLabel()  START");
		try {
			Locale locale = Locale.ENGLISH;
			String language = locale.getLanguage();
			Document pdfStructureDom = XMLUtil.getDomObj(FieldDataDemo.class.getClassLoader().getResourceAsStream("examples/XMLFiles/label.xml"));
			com.itextpdf.text.Document outputPDFDocument = new com.itextpdf.text.Document(PageSize.A4);
			PdfWriter.getInstance(
					outputPDFDocument,
							new FileOutputStream("src/main/resources/examples/PDFFiles/labelOutput.pdf"));
			
			addingLabel(pdfStructureDom, null, outputPDFDocument, language);
			_log.info("LabelDemo.createLabel()  END");
		} catch (FileNotFoundException e) {
			_log.error(e);
		} catch (DocumentException e) {
			_log.error(e);
		} catch (Exception e) {
			_log.error(e);
		}
	}

	private static void addingLabel(Document pdfStructureDom,
			Object object, com.itextpdf.text.Document outputPDFDocument,
			String language) {
		try {
			PDFCreator.processPDF(pdfStructureDom, null, outputPDFDocument, language);
		} catch (Exception e) {
			_log.error(e);
		}
		
	}

}
