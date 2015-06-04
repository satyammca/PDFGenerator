package com.competency.pdfgenerator.iterator.elements;

/**
 * @author satyanarayana kolliboyin
 *
 */
import com.competency.pdfgenerator.iterator.BaseIterator;
import com.itextpdf.text.Document;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ResourceBundle;

import org.jdom.Element;

public class Iterator extends BaseIterator {
	@Override
	public void mainTask(Element elt, Object theBean, Document pdfDocument, String locale) {
		// TODO Auto-generated method stub
		try {
			ResourceBundle dependencyBundle1 = ResourceBundle.getBundle("dependency");
			String listElementVal = elt.getAttributeValue("value");
			Class<?> cls = theBean.getClass();
			Field field = cls.getDeclaredField(listElementVal);
			field.setAccessible(true);
			List listVal = (List) field.get(theBean);
			Class loClass;
			BaseIterator baseIterator;
			List<Element> blocksChildList = elt.getChildren();
			if (listVal != null) {
				for (Object object : listVal) {
					for (Element blocksChildElt : blocksChildList) {
						try {
							// System.out.println(blocksChildElt.getName());
							// System.out.println("text="+blocksChildElt.getText()+"  attr="+blocksChildElt.getAttributeValue("title"));
							loClass = Class.forName(dependencyBundle1.getString(blocksChildElt.getName()));
							baseIterator = (BaseIterator) loClass.newInstance();
							baseIterator.mainTask(blocksChildElt, object, pdfDocument, locale);
						} catch (Exception ex) {

						}

					}
				}
			}
		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}
}
