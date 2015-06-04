package com.competency.pdfgenerator.iterator.elements;


import com.competency.pdfgenerator.iterator.BaseIterator;
import com.competency.pdfgenerator.utilities.PDFConstants;
import com.competency.pdfgenerator.utilities.PDFCreatorUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import org.jdom.Element;

public class Heading extends BaseIterator {

	@Override
	public void mainTask(Element elt, Object theBean, Document document, String locale) {
		String propertyFile = "content/Language_en";
		if (locale != null && !locale.equals("")) {
			propertyFile = "content/Language_" + locale;
		}
		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		try {
			// System.out.println("inside create PDF ");
			String titleChunk = rb.getString(elt.getAttributeValue("title"));
			//Chunk chunk = new Chunk(new String(titleChunk.getBytes("ISO-8859-1"), "UTF-8"));
			Chunk chunk = PDFCreatorUtil.formatChunk(titleChunk, true);
			//Font font = PDFConstants.getFont(locale);
			Font font = chunk.getFont();
			font.setStyle(Font.UNDERLINE);
			font.setStyle(Font.ITALIC);
			font.setSize(12.0f);
			chunk.setFont(font);
			//chunk.setBackground(BaseColor.CYAN, 15, 0, 370, 0);
			PdfPCell cell = new PdfPCell(new Phrase(chunk));
			if (locale.equalsIgnoreCase(PDFConstants.LOCALE_ARABIC)) {
				cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
			}
			//paragraph.add(chunk);
			cell.setBackgroundColor(BaseColor.CYAN);
			cell.setBorder(Rectangle.NO_BORDER);
			textTable.addCell(cell);
			columnNo++;
			//document.add(cell);			
/*		} catch (DocumentException e) {
			e.printStackTrace();
*/		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*public static void main(String []args){
		String propertyFile = "content/Language_en";
		
		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		System.out.println("======:"+rb.getString("project-title"));
	}*/

}
