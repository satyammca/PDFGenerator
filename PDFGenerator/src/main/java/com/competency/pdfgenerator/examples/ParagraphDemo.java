package com.competency.pdfgenerator.examples;

import java.io.FileOutputStream;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;

import com.competency.pdfgenerator.PDFCreator;
import com.competency.pdfgenerator.examples.beans.PDFMapObject;
import com.competency.pdfgenerator.examples.beans.PersonalDetailsModel;
import com.competency.pdfgenerator.utilities.XMLUtil;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;


public class ParagraphDemo {
	private static Log _log = LogFactory.getLog(ParagraphDemo.class);
	public static void main(String[] args) {
		try {
			_log.info("ParagraphDemo.main() START");
			createParagraph();
			_log.info("ParagraphDemo.main() END");
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public static void createParagraph() {
		_log.info("ParagraphDemo.createParagraph()  START");
		try {
			Locale locale = Locale.ENGLISH;
			String language = locale.getLanguage();
			PDFMapObject pdfMapObject = new PDFMapObject();
			//setting personal details. personal details includes person location info also.
			PersonalDetailsModel personalDetailsModel = getPersonalDetailsModel();
			//setting personal details to pdf map object
			pdfMapObject.setPersonalDetailsModel(personalDetailsModel);
			
			Document pdfStructureDom = XMLUtil.getDomObj(ParagraphDemo.class.getClassLoader().getResourceAsStream("examples/XMLFiles/paragraph.xml"));
			com.itextpdf.text.Document outputPDFDocument = new com.itextpdf.text.Document(PageSize.A4);
			//need to pass pdf file as output stream. The passed pdf file added with latest content 
			PdfWriter.getInstance(
					outputPDFDocument,
							new FileOutputStream("src/main/resources/examples/PDFFiles/paragraphOutput.pdf"));
			
			addingParagraph(pdfStructureDom, pdfMapObject, outputPDFDocument, language);
			_log.info("ParagraphDemo.createParagraph()  END");
		} catch (Exception e) {
			_log.error(e);
		}
		
	}

	public static void addingParagraph(Document pdfStructureDom,
			PDFMapObject pdfMapObject,
			com.itextpdf.text.Document outputPDFDocument, String language) {
		try {
			PDFCreator.processPDF(pdfStructureDom, pdfMapObject, outputPDFDocument, language);
		} catch (Exception e) {
			_log.error(e);
		}
		
	}

	private static PersonalDetailsModel getPersonalDetailsModel() {
		PersonalDetailsModel personalDetailsModel = new PersonalDetailsModel();
		personalDetailsModel.setUserExperience("6+ years of experience in Portal and web applications development with background of Liferay portal "
				+ "and J2SE/J2EE Technologies");
	    return personalDetailsModel;
	}

}
