package com.competency.pdfgenerator.examples;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;

import com.competency.pdfgenerator.PDFCreator;
import com.competency.pdfgenerator.examples.beans.PDFMapObject;
import com.competency.pdfgenerator.examples.beans.PersonalDetailsLocation;
import com.competency.pdfgenerator.examples.beans.PersonalDetailsModel;
import com.competency.pdfgenerator.examples.beans.WorkExpModel;
import com.competency.pdfgenerator.utilities.XMLUtil;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;


public class MainDemo {
	private static Log _log = LogFactory.getLog(MainDemo.class);
	public static void main(String[] args) {
		try {
			_log.info("MainDemo.main() START");
			createMainPDF();
			_log.info("MainDemo.main() END");
		} catch (Exception ex) {
			_log.error(ex);
		}

	}

	public static void createMainPDF() {
		_log.info("MainDemo.createMainPDF()  START");
		try {
			Locale locale = Locale.ENGLISH;
			String language = locale.getLanguage();
			PDFMapObject pdfMapObject = new PDFMapObject();
			//setting personal details. personal details includes person location info also.
			PersonalDetailsModel personalDetailsModel = getPersonalDetailsModel();
			//setting personal work experience details. 
			List<WorkExpModel>  workExpModelList = getWorkExpList();
			//setting personal details to pdf map object
			pdfMapObject.setPersonalDetailsModel(personalDetailsModel);
			//setting personal details to pdf map object
			pdfMapObject.setWorkExpModelList(workExpModelList);
			
			Document pdfStructureDom = XMLUtil.getDomObj(MainDemo.class.getClassLoader().getResourceAsStream("examples/XMLFiles/main.xml"));
			com.itextpdf.text.Document outputPDFDocument = new com.itextpdf.text.Document(PageSize.A4);
			//need to pass pdf file as output stream. The passed pdf file added with latest content 
			PdfWriter.getInstance(
					outputPDFDocument,
							new FileOutputStream("src/main/resources/examples/PDFFiles/mainOutput.pdf"));
			
			addingFieldData(pdfStructureDom, pdfMapObject, outputPDFDocument, language);
			_log.info("MainDemo.createMainPDF()  END");
		} catch (Exception e) {
			_log.error(e);
			
		}
		
	}

	public static void addingFieldData(Document pdfStructureDom,
			PDFMapObject pdfMapObject,
			com.itextpdf.text.Document outputPDFDocument, String language) {
		try {
			PDFCreator.processPDF(pdfStructureDom, pdfMapObject, outputPDFDocument, language);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static PersonalDetailsModel getPersonalDetailsModel() {
		PersonalDetailsModel personalDetailsModel = new PersonalDetailsModel();
		personalDetailsModel.setUserId(72331);
		personalDetailsModel.setEmailId("satya.kolliboina@gmail.com");
		personalDetailsModel.setFirstName("satyanarayana");
		personalDetailsModel.setLastName("kolliboyin");
		personalDetailsModel.setMobileNumber("9985165481");
		personalDetailsModel.setUserExperience("6+ years of experience in Portal and "
				+ "web applications development with background of Liferay portal "
				+ "and J2SE/J2EE Technologies");
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
	private static List<WorkExpModel> getWorkExpList() {
		List<WorkExpModel> workExpModels = new ArrayList<WorkExpModel>();
		workExpModels.add(new WorkExpModel("Cignex Datamatics", "Nov 30, 2012", "Continueing"));
		workExpModels.add(new WorkExpModel("Triumphsys", "Dec 13, 2010", "Jun 8, 2012"));
		
		return workExpModels;
	}
}
