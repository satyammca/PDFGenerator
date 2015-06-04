package com.competency.pdfgenerator.utilities;

/**
 * @author prashant.c.ghorpade
 *
 */

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;

import de.odysseus.el.util.SimpleContext;

import java.lang.reflect.Field;
import java.util.List;

import org.jdom.Element;

public class GenerateStringFromXmlValue {

	public String getFieldValue(Element elt, Object theBean) {
		String fieldValue = "";
		try {
			String varValue = elt.getAttributeValue("value");
			if (varValue != null && !varValue.equals("")) {
				String varName = "";
				Object obj = theBean;
				Field field = null;
				String fieldType = null;
				int index = 0;
				Class<?> cls = theBean.getClass();
				if (varValue != null && varValue.contains(".")) {
					String[] valueArr = varValue.split("\\.");
					for (int i = 0; i < valueArr.length - 1; i++) {
						//System.out.println("inside for=" + obj);
						varName = valueArr[i];
						//System.out.println("var name=" + varName);
						//System.out.println("fieldType name=" + fieldType);
						if (varName.contains("[")) {
							String[] objArr = varName.split("\\[");
							varName = objArr[0];
							//System.out.println("curr var name=" + varName);
							index = Integer.valueOf(objArr[1].substring(0,
									objArr[1].indexOf("]")));
							//System.out.println("index=" + index);
							field = cls.getDeclaredField(varName);
							field.setAccessible(true);
							obj = field.get(obj);
							//System.out.println("curr object=" + obj);
							obj = ((List) obj).get(index);
							//System.out.println("sub object=" + obj);
							cls = obj.getClass();
						} else {
							field = cls.getDeclaredField(varName);
							field.setAccessible(true);
							obj = field.get(obj);
							cls = obj.getClass();
						}

						// last variable inside the class
						if (i == valueArr.length - 2) {
							varName = valueArr[i + 1];

						}

					}
				} else {
					varName = varValue;
				}

				if (varName.contains("[")) {
					String[] objArr = varName.split("\\[");
					varName = objArr[0];
					//System.out.println("curr var name=" + varName);
					index = Integer.valueOf(objArr[1].substring(0, 1));
					//System.out.println("index=" + index);
					field = cls.getDeclaredField(varName);
					field.setAccessible(true);
					obj = field.get(obj);
					//System.out.println("curr object=" + obj);
					obj = ((List) obj).get(index);

				} else {
					field = cls.getDeclaredField(varName);
					field.setAccessible(true);
					obj = field.get(obj);
				}
				fieldValue = obj != null ? obj.toString() : "";
			}

		} catch (Exception e) {
			fieldValue = "";
			e.printStackTrace();
		}
		return fieldValue;

	}

}
