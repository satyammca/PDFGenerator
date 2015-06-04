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
import com.competency.pdfgenerator.examples.beans.WorkExpModel;
import com.competency.pdfgenerator.utilities.XMLUtil;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;


public class TableDemo {
	private static Log _log = LogFactory.getLog(TableDemo.class);
	public static void main(String[] args) {
		try {
			_log.info("TableDemo.main() START");
			createTable();
			_log.info("TableDemo.main() END");
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public static void createTable() {
		_log.info("TableDemo.createTable()  START");
		try {
			Locale locale = Locale.ENGLISH;
			String language = locale.getLanguage();
			PDFMapObject pdfMapObject = new PDFMapObject();
			//setting personal details. personal details includes person location info also.
			List<WorkExpModel>  workExpModelList = getWorkExpList();
			
			//setting personal work experience datails
			pdfMapObject.setWorkExpModelList(workExpModelList);
			
			Document pdfStructureDom = XMLUtil.getDomObj(TableDemo.class.getClassLoader().getResourceAsStream("examples/XMLFiles/table.xml"));
			com.itextpdf.text.Document outputPDFDocument = new com.itextpdf.text.Document(PageSize.A4);
			//need to pass pdf file as output stream. The passed pdf file added with latest content 
			PdfWriter.getInstance(
					outputPDFDocument,
							new FileOutputStream("src/main/resources/examples/PDFFiles/tableOutput.pdf"));
			
			addingTable(pdfStructureDom, pdfMapObject, outputPDFDocument, language);
			_log.info("TableDemo.createTable()  END");
		} catch (Exception e) {
			_log.error(e);
		}
		
	}

	

	public static void addingTable(Document pdfStructureDom,
			PDFMapObject pdfMapObject,
			com.itextpdf.text.Document outputPDFDocument, String language) {
		try {
			PDFCreator.processPDF(pdfStructureDom, pdfMapObject, outputPDFDocument, language);
		} catch (Exception e) {
			_log.error(e);
		}
		
	}
	private static List<WorkExpModel> getWorkExpList() {
		List<WorkExpModel> workExpModels = new ArrayList<WorkExpModel>();
		workExpModels.add(new WorkExpModel("Cignex Datamatics", "Nov 30, 2012", "Continueing"));
		workExpModels.add(new WorkExpModel("Triumphsys", "Dec 13, 2010", "Jun 8, 2012"));
		
		return workExpModels;
	}
	
}
