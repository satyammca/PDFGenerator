package com.competency.pdfgenerator.iterator.elements;

/**
 * @author satyanarayana kolliboyin
 *
 */
import com.competency.pdfgenerator.iterator.BaseIterator;
import com.competency.pdfgenerator.utilities.GenerateStringFromXmlValue;
import com.competency.pdfgenerator.utilities.PDFConstants;
import com.competency.pdfgenerator.utilities.PDFCreatorUtil;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.ResourceBundle;

import org.jdom.Element;

public class LabelField extends BaseIterator {

	@Override
	public void mainTask(Element elt, Object theBean, Document document, String locale) {
		// System.out.println("ListData 123");
		String propertyFile = "content/Language_en";
		if (locale != null && !locale.equals("")) {
			propertyFile = "content/Language_" + locale;
		}
		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		try {
			String value1 = "";
			String title = elt.getAttributeValue("title");
			if (title != null && !title.equals("")) {
				String value = rb.getString(elt.getAttributeValue("title"));
				value1 = value != null ? value.toString() : "";
			}
			//Chunk chunk = new Chunk(new String(value1.getBytes("ISO-8859-1"), "UTF-8"));
			Chunk chunk = PDFCreatorUtil.formatChunk(value1, true);
			//Font font = PDFConstants.getFont(locale);
			//font.setSize(10.0f);
			//chunk.setFont(font);
			PdfPCell cell = new PdfPCell(new Phrase(chunk));
			if (locale.equalsIgnoreCase(PDFConstants.LOCALE_ARABIC)) {
				cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
			}
			cell.setBorder(Rectangle.NO_BORDER);
			textTable.addCell(cell);
			columnNo++;
			String codetype = elt.getAttributeValue("codetype");
			StringBuilder sb = new StringBuilder();
			
			chunk = new Chunk(new GenerateStringFromXmlValue().getFieldValue(elt, theBean));
			
			//chunk.setFont(font);
			cell = new PdfPCell(new Phrase(chunk));
			if (locale.equalsIgnoreCase(PDFConstants.LOCALE_ARABIC)) {
				cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
			}
			cell.setBorder(Rectangle.NO_BORDER);
			textTable.addCell(cell);
			columnNo++;
			if (columnNo == 4) {
				columnNo = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
