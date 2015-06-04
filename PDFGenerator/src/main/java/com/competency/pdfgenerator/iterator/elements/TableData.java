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
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ResourceBundle;

import org.jdom.Element;

public class TableData extends BaseIterator {

	@Override
	public void mainTask(Element elt, Object theBean, Document document, String locale) {
		// System.out.println("ListData 123");
		// String title = elt.getAttributeValue("title");
		// ResourceBundle.getBundle("properties/language").getString(title);
		try {
			// completePreviousTable(document);
			String fontSizeStr = elt.getAttributeValue("fontsize");
			float fontSize = 10.0f;
			if (fontSizeStr != null)
				fontSize = Float.valueOf(fontSizeStr);

			String propertyFile = "content/Language_en";
			if (locale != null && !locale.equals("")) {
				propertyFile = "content/Language_" + locale;
			}
			ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
			int row = 0, coln = 0;
			Class<?> cls = theBean.getClass();
			Field field = cls.getDeclaredField(elt.getAttributeValue("value"));
			field.setAccessible(true);
			List listVal = (List) field.get(theBean);
			String[] headers = null;
			String[][] data = null;
			List<Element> blocksChildList = elt.getChildren();
			headers = new String[blocksChildList.size()];
			data = new String[listVal.size()][blocksChildList.size()];
			// System.out.println("blocksChildList ====" +
			// blocksChildList.size() + "  List size=" + listVal.size());
			boolean header = true;
			if (elt.getAttributeValue("header") != null && elt.getAttributeValue("header").equals("false")) {
				header = false;
			}
			for (Object object : listVal) {
				coln = 0;
				for (Element blocksChildElt : blocksChildList) {
					try {
						if (row == 0 && header) {
							String colmTitle = "";
							String title = blocksChildElt.getAttributeValue("title");
							if (title != null && !title.equals("")) {
								String value = rb.getString(title);
								colmTitle = value != null ? value.toString() : "";
							}
							headers[coln] = colmTitle;
						}

						String colmValue = "";
						colmValue = new GenerateStringFromXmlValue().getFieldValue(blocksChildElt, object);
						
						data[row][coln] = colmValue;
						coln++;

					} catch (Exception ex) {

					}
				}
				// document.add(pr);
				// System.out.println();
				row++;
			}
			new TableData().tableMethod(document, headers, data, header, fontSize, locale);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tableMethod(Document document, String[] headers, String[][] data, boolean headerVal, float fontSize, String locale) throws UnsupportedEncodingException {

		//
		// Create a new document.
		//

		try {
			// System.out.println("header legth=" + headers.length);
			//Font font = PDFConstants.getFont(locale);
			System.out.println("font size=" + fontSize);
			//font.setSize(fontSize);
			Paragraph p1 = new Paragraph(" ");
			Paragraph p2 = new Paragraph();
			PdfPTable table = new PdfPTable(headers.length);
			if (locale.equalsIgnoreCase(PDFConstants.LOCALE_ARABIC)) {
				table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
			}
			table.setWidthPercentage(100);
			//table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
			if (headerVal) {
				for (int i = 0; i < headers.length; i++) {
					String header = headers[i];
					PdfPCell cell = new PdfPCell();
					if (locale.equalsIgnoreCase(PDFConstants.LOCALE_ARABIC)) {
						cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
					}
					Chunk chunk = PDFCreatorUtil.formatChunk(header, true);
					cell.setGrayFill(0.9f);
					cell.setPhrase(new Phrase(chunk.getContent(), chunk.getFont()));
					// cell.setBorder(Rectangle.NO_BORDER);
					table.addCell(cell);
				}
				table.completeRow();
			}

			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					String datum = data[i][j];
					// System.out.println("data=" + datum + " i=" + i + " j=" +
					// j);
					PdfPCell cell = new PdfPCell();
					if (locale.equalsIgnoreCase(PDFConstants.LOCALE_ARABIC)) {
						cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
					}
					Chunk chunk = PDFCreatorUtil.formatChunk(datum, false);
					cell.setPhrase(new Phrase(datum, chunk.getFont()));
					// cell.setBorder(Rectangle.NO_BORDER);
					table.addCell(cell);
				}
				table.completeRow();
			}
			p2.add(table);
			document.add(p1);
			document.add(p2);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
