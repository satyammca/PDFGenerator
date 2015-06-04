package com.competency.pdfgenerator.iterator.elements;

/**
 * @author satyanarayana kolliboyin
 *
 */
import com.competency.pdfgenerator.iterator.BaseIterator;
import com.competency.pdfgenerator.utilities.PDFConstants;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

import org.jdom.Element;

public class Newline extends BaseIterator {

	@Override
	public void mainTask(Element elt, Object theBean, Document document, String locale) {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
