package com.competency.pdfgenerator.examples;

import java.io.FileOutputStream;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;

import com.competency.pdfgenerator.PDFCreator;
import com.competency.pdfgenerator.examples.beans.PDFMapObject;
import com.competency.pdfgenerator.examples.beans.PersonalDetailsLocation;
import com.competency.pdfgenerator.examples.beans.PersonalDetailsModel;
import com.competency.pdfgenerator.utilities.XMLUtil;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;


public class FieldDataDemo {
	private static Log _log = LogFactory.getLog(FieldDataDemo.class);
	public static void main(String[] args) {
		try {
			_log.info("FieldDataDemo.main() START");
			createFieldData();
			_log.info("FieldDataDemo.main() END");
		} catch (Exception ex) {
			_log.error(ex.getMessage());
		}

	}

	public static void createFieldData() {
		try {
			_log.info("FieldDataDemo.createFieldData() START");
			Locale locale = Locale.ENGLISH;
			String language = locale.getLanguage();
			PDFMapObject pdfMapObject = new PDFMapObject();
			//setting personal details. personal details includes person location info also.
			PersonalDetailsModel personalDetailsModel = getPersonalDetailsModel();
			//setting personal details to pdf map object
			pdfMapObject.setPersonalDetailsModel(personalDetailsModel);
			
			Document pdfStructureDom = XMLUtil.getDomObj(FieldDataDemo.class.getClassLoader().getResourceAsStream("examples/XMLFiles/fieldData.xml"));
			_log.info("pdfStructureDom:"+pdfStructureDom);
			com.itextpdf.text.Document outputPDFDocument = new com.itextpdf.text.Document(PageSize.A4);
			//need to pass pdf file as output stream. The passed pdf file added with latest content 
			PdfWriter.getInstance(
					outputPDFDocument,
							new FileOutputStream("src/main/resources/examples/PDFFiles/fieldDataOutput.pdf"));
			
			addingFieldData(pdfStructureDom, pdfMapObject, outputPDFDocument, language);
			_log.info("FieldDataDemo.createFieldData()  END");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			_log.error(e);
		}
		
	}

	public static void addingFieldData(Document pdfStructureDom,
			PDFMapObject pdfMapObject,
			com.itextpdf.text.Document outputPDFDocument, String language) {
		_log.info("FieldDataDemo.addingFieldData()  START");
		try {
			PDFCreator.processPDF(pdfStructureDom, pdfMapObject, outputPDFDocument, language);
			_log.info("FieldDataDemo.addingFieldData()  END");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			_log.error(e);
		}
		
	}

	private static PersonalDetailsModel getPersonalDetailsModel() {
		PersonalDetailsModel personalDetailsModel = new PersonalDetailsModel();
		personalDetailsModel.setUserId(72331);
		personalDetailsModel.setEmailId("satya.kolliboina@gmail.com");
		personalDetailsModel.setFirstName("satyanarayana");
		personalDetailsModel.setLastName("kolliboyin");
		personalDetailsModel.setMobileNumber("9985165481");
		PersonalDetailsLocation personalDetailsLocation = getPersonalDetailsLocation();
		personalDetailsModel.setPersonalDetailsLocation(personalDetailsLocation);
	    return personalDetailsModel;
	}

	private static PersonalDetailsLocation getPersonalDetailsLocation() {
		PersonalDetailsLocation personalDetailsLocation = new PersonalDetailsLocation();
		personalDetailsLocation.setLocality("Pune");
		personalDetailsLocation.setState("Maharastra");
		personalDetailsLocation.setCountry("India");
		return personalDetailsLocation;
	}

}
