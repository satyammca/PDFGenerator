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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

public class FieldData extends BaseIterator {

	@Override
	public void mainTask(org.jdom.Element elt, Object theBean,
			com.itextpdf.text.Document pdfDocument, String locale) {
		try {
			//Font font = PDFConstants.getFont(locale);
			//font.setSize(10.0f);
			String codetype = elt.getAttributeValue("codetype");
			Chunk chunk = null;
			String codesValue = null;
			
			codesValue = new GenerateStringFromXmlValue().getFieldValue(elt, theBean);
			
			chunk = PDFCreatorUtil.formatChunk(codesValue, true);
			PdfPCell cell = new PdfPCell(new Phrase(chunk));
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
