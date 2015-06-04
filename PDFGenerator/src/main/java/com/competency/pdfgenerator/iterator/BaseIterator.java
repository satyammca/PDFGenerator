package com.competency.pdfgenerator.iterator;

import com.competency.pdfgenerator.utilities.PDFConstants;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import de.odysseus.el.util.SimpleContext;

import java.util.List;
import java.util.ResourceBundle;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import org.jdom.Element;

public abstract class BaseIterator {
	private static ResourceBundle dependencyResolver;
	public static int columnNo = 0;
	public static PdfPTable textTable = null;
	private static ExpressionFactory factory = new de.odysseus.el.ExpressionFactoryImpl();
	static {
		dependencyResolver =  ResourceBundle.getBundle("dependency");
	}
	
	public static void processBlock(Element blockElt, Object theBean, Document pdfDocument, String locale) throws Exception {
		BaseIterator baseIterator;
		textTable = new PdfPTable(4);
		columnNo = 0;
		if (locale.equalsIgnoreCase(PDFConstants.LOCALE_ARABIC)) {
			textTable.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		}
		textTable.setWidthPercentage(100);
		// System.out.println("inside processBlock");
		Class loClass;
		List<Element> blocksChildList = blockElt.getChildren();
		for (Element blocksChildElt : blocksChildList) {
			try {
				// System.out.println(blocksChildElt.getName());
				// System.out.println("text="+blocksChildElt.getText()+"  attr="+blocksChildElt.getAttributeValue("title"));
				loClass = Class.forName(dependencyResolver.getString(blocksChildElt.getName()));
				// System.out.println("hiiiiii=" + loClass.getName());
				baseIterator = (BaseIterator) loClass.newInstance();
				baseIterator.mainTask(blocksChildElt, theBean, pdfDocument, locale);
			} catch (Exception ex) {

			}

		}
		for (int i = 0; i < 4 - columnNo; i++) {
			PdfPCell cell = new PdfPCell(new Phrase(" "));
			if (locale.equalsIgnoreCase(PDFConstants.LOCALE_ARABIC)) {
				cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
			}
			cell.setBorder(Rectangle.NO_BORDER);
			textTable.addCell(cell);
		}
		pdfDocument.add(textTable);
	}

	private static String readFromBean(Object object, String toRead, String objectType, SimpleContext context) throws ClassNotFoundException {
		ValueExpression e = factory.createValueExpression(context, "${" + toRead + "}", Class.forName(objectType));
		return (String) e.getValue(context);
	}

	public void completePreviousTable(Document document) {
		for (int i = 0; i < 4 - columnNo; i++) {
			PdfPCell cell = new PdfPCell(new Phrase(" "));
			cell.setBorder(Rectangle.NO_BORDER);
			textTable.addCell(cell);
		}
		columnNo = 0;
		try {
			document.add(textTable);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textTable = new PdfPTable(4);
		textTable.setWidthPercentage(100);
	}

	public abstract void mainTask(Element elt, Object theBean, com.itextpdf.text.Document pdfDocument, String locale);

}
