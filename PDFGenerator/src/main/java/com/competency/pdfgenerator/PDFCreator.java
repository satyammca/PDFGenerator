package com.competency.pdfgenerator;

import com.competency.pdfgenerator.iterator.BaseIterator;
import com.competency.pdfgenerator.utilities.XMLUtil;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.el.ExpressionFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;


public class PDFCreator {
	private static Log _log = LogFactory.getLog(PDFCreator.class);
	private static ExpressionFactory factory = new de.odysseus.el.ExpressionFactoryImpl();
	
	public InputStream generatePDF(Object theBean, String xmlFileName, String pdfPath,
			String locale) { 
		_log.info("PDFCreator.generatePDF()  START");
		ByteArrayInputStream bais =null;
		try {
			de.odysseus.el.util.SimpleContext context = new de.odysseus.el.util.SimpleContext();
			context.setVariable("foo",
					factory.createValueExpression(theBean, theBean.getClass()));
			// Document pdfStructureDom =
			// XMLUtil.getDomObj(PDFCreator.class.getResourceAsStream("/xml/PDFstructure.xml"));
			Document pdfStructureDom = XMLUtil.getDomObj(this.getClass().getClassLoader().getResource(xmlFileName).getPath());
			com.itextpdf.text.Document pdfDocument = new com.itextpdf.text.Document(
					PageSize.A4);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			PdfWriter writer = PdfWriter.getInstance(pdfDocument,
//					new FileOutputStream(pdfPath)); 
			
			PdfWriter.getInstance(pdfDocument, baos);
			processPDF(pdfStructureDom, theBean, pdfDocument, locale);
		//	inputStream = new FileInputStream(pdfPath); 
			bais= new ByteArrayInputStream(baos.toByteArray());
			//_log.info("Stream created");
			_log.info("PDFCreator.generatePDF()  END");
		} catch (Exception ex) {
			_log.error(ex.getMessage());
		}
		return bais;
	} 
	
	public InputStream generatePDF(Object theBean, String xmlFileName,
			String locale) { 
		_log.info("PDFCreator.generatePDF()  START");
		ByteArrayInputStream bais =null;
		try {

			de.odysseus.el.util.SimpleContext context = new de.odysseus.el.util.SimpleContext();
			context.setVariable("foo",factory.createValueExpression(theBean, theBean.getClass()));
			// Document pdfStructureDom =
			// XMLUtil.getDomObj(PDFCreator.class.getResourceAsStream("/xml/PDFstructure.xml"));
			Document pdfStructureDom = XMLUtil.getDomObj(this.getClass().getClassLoader().getResource(xmlFileName).getPath());
			com.itextpdf.text.Document pdfDocument = new com.itextpdf.text.Document(
					PageSize.A4);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			PdfWriter writer = PdfWriter.getInstance(pdfDocument,
//					new FileOutputStream(pdfPath)); 
			
			PdfWriter.getInstance(pdfDocument, baos);
			processPDF(pdfStructureDom, theBean, pdfDocument, locale);
		//	inputStream = new FileInputStream(pdfPath); 
			bais= new ByteArrayInputStream(baos.toByteArray());
		//	_log.info("Stream created");
			_log.info("PDFCreator.generatePDF() END");
		} catch (Exception ex) {
			_log.error(ex.getMessage());
		}
		return bais;
	}

	public static void processPDF(Document pdfStructureDom, Object theBean,
			com.itextpdf.text.Document pdfDocument, String locale)
			throws Exception {
		_log.info("PDFCreator.processPDF()  START");
		List<Element> blockList = XMLUtil.getElementList("pdfcreator/block",
				pdfStructureDom);
		pdfDocument.open();
		for (Element blockElt : blockList) {
			BaseIterator.processBlock(blockElt, theBean, pdfDocument, locale);
		}
		pdfDocument.close();
		_log.info("PDFCreator.processPDF()  END");
	}
}
