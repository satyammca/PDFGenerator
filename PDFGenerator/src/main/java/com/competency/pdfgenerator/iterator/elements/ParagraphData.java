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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.ResourceBundle;

import org.jdom.Element;

public class ParagraphData extends BaseIterator {

	@Override
	public void mainTask(Element elt, Object theBean, Document pdfDocument, String locale) {
		// TODO Auto-generated method stub
		try {
			for (int i = 0; i < 4 - columnNo; i++) {
				PdfPCell cell = new PdfPCell(new Phrase(" "));
				if (locale.equalsIgnoreCase(PDFConstants.LOCALE_ARABIC)) {
					cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
				}
				cell.setBorder(Rectangle.NO_BORDER);
				textTable.addCell(cell);
			}
			columnNo = 0;
			pdfDocument.add(textTable);
			textTable = new PdfPTable(4);
			if (locale.equalsIgnoreCase(PDFConstants.LOCALE_ARABIC)) {
				textTable.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
			}
			textTable.setWidthPercentage(100);
			Chunk chunk = new Chunk("  ");
			Paragraph paragraph = new Paragraph();
			paragraph.add(chunk);
			//Font font = PDFConstants.getFont(locale);
			//font.setSize(10.0f);
			String propertyFile = "content/Language_en";
			if (locale != null && !locale.equals("")) {
				propertyFile = "content/Language_" + locale;
			}
			ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
			String title = elt.getAttributeValue("title");
			if (title != null && !title.equals("")) {
				Object value = rb.getString(title);
				if (value != null && !value.equals("")) {
					String value1 = value != null ? value.toString() : "";
					//chunk = new Chunk(new String(value1.getBytes("ISO-8859-1"), "UTF-8"));
					chunk = PDFCreatorUtil.formatChunk(value1, true);
					chunk.append(" ");
					//chunk.setFont(font);
				}
			}
			
			chunk.append(new GenerateStringFromXmlValue().getFieldValue(elt, theBean));
			
			//chunk.setFont(font);
			paragraph.add(chunk);
			pdfDocument.add(paragraph);
		} catch (Exception ex) {

		}
	}

}
