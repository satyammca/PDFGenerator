package com.competency.pdfgenerator.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

/**
 * This class provide the methods to parse XML or JDOM object to get desired
 * value. functions accepts XPATH or node name or node attributes to return
 * value
 * 
 */

public class XMLUtil
{

	/**
	 * Gets the value of provided element from the dom
	 * 
	 * @param aoDoc - jdom from which value is to be fetched
	 * @param asParameter - Element name for which value to be fetched
	 * @return the value of provided element from the dom
	 * @throws Exception
	 */
	public static String getValue(Document aoDoc, String asParameter)
	{
		Element loEle = getElement("//" + asParameter, aoDoc);
		String lsValue = null;
		if (null != loEle)
		{
			if (null != loEle.getAttributeValue("value"))
			{
				lsValue = loEle.getAttributeValue("value");
			}
		}
		return lsValue;
	}

	/**
	 * Gets the attribute value of provided element from the dom
	 * 
	 * @param aoDoc - jdom from which value is to be fetched
	 * @param asElementName - Element name for which value to be fetched
	 * @param asAttributeName - attribute name for which value to be fetched
	 * @return the attribute value of provided element from the dom
	 * @throws Exception
	 */
	public static String getValue(Document aoDoc, String asElementName, String asAttributeName)
	{
		Element loEle = getElement("//" + asElementName, aoDoc);
		String lsValue = null;
		if (null != loEle)
		{
			if (null != loEle.getAttributeValue(asAttributeName))
			{
				lsValue = loEle.getAttributeValue(asAttributeName);
			}
		}
		return lsValue;
	}

	/**
	 * Gets the jdom object of xml at specified path
	 * 
	 * @param asFilePath - path from which xml to be read
	 * @return the jdom object of xml at specified path
	 * @throws Exception
	 */
	public static Document getDomObj(String asFilePath)
	{
		SAXBuilder loSaxBuilder = new SAXBuilder();
		Document loDocToRead = null;
		FileInputStream loFileInputStream = null;
		try
		{
			loFileInputStream = new FileInputStream(asFilePath);
			loDocToRead = loSaxBuilder.build(loFileInputStream);
		}
		catch (Exception aoEx)
		{
		}
		finally
		{
			try
			{
				if (null != loFileInputStream)
				{
					loFileInputStream.close();
				}
			}
			catch (IOException aoIoEx)
			{
			}
		}
		return loDocToRead;
	}

	/**
	 * Gets the jdom object of xml from input stream
	 * 
	 * @param aoIS - Inputstream from which xml to be read
	 * @return the jdom object of xml from input stream
	 * @throws Exception
	 */
	public static Document getDomObj(InputStream aoIS)
	{
		Document loFoc = null;
		try
		{
			SAXBuilder loBuilder = new SAXBuilder();
			loFoc = loBuilder.build(aoIS);

		}
		catch (Exception aoJDOMEx)
		{
		}
		return loFoc;
	}

	/**
	 * Gets the jdom object of xml from Reader object
	 * 
	 * @param aoReader - Reader object from which jdom to be created
	 * @return the jdom object of xml from Reader object
	 * @throws Exception
	 */
	public static Document getDomObj(Reader aoReader)
	{
		Document loDocObj = null;
		try
		{
			SAXBuilder loBuilder = new SAXBuilder();
			loDocObj = loBuilder.build(aoReader);

		}
		catch (JDOMException aoJDOMEx)
		{
			// throw new
			// ApplicationException("Error occured in parsing the file",
			// aoJDOMEx);
		}
		catch (IOException aoIOEx)
		{
			// throw new
			// ApplicationException("Error occured in parsing the file",
			// aoIOEx);
		}
		return loDocObj;
	}

	/**
	 * Sets the attribute for an element with specified value in a jdom
	 * 
	 * @param aoDoc - jdom to be updated
	 * @param asElementName - Element name to be updated
	 * @param asAttributeName - Attribute name to be updated
	 * @param asValue - value to be set
	 * @return the updated jdom
	 * @throws Exception
	 */
	public static Document setValue(Document aoDoc, String asElementName, String asAttributeName, String asValue)
	{

		Element loEle = getElement("//" + asElementName, aoDoc);
		if (null != loEle)
		{
			loEle.setAttribute(asAttributeName, asValue);
		}
		return aoDoc;
	}

	/**
	 * Gets an element from a jdom satisfying some specific xpath condition
	 * 
	 * @param asPath - Xpath to be checked
	 * @param aoDoc - jdom from which element to be searched
	 * @return Element satisfying the xpath
	 * @throws Exception
	 */
	public static Element getElement(String asPath, Document aoDoc)
	{
		Element loResult = null;
		try
		{
			XPath loXpath = XPath.newInstance(asPath);
			loResult = (Element) loXpath.selectSingleNode(aoDoc);
		}
		catch (JDOMException aoJDOMEx)
		{
			// throw new
			// ApplicationException("Error occurred in accessing tree",
			// aoJDOMEx);
		}
		return loResult;
	}

	/**
	 * Gets an element from an element satisfying some specific xpath condition
	 * 
	 * @param asPath - Xpath to be checked
	 * @param aoEle - element from which element to be searched
	 * @return Element satisfying the xpath
	 * @throws Exception
	 */
	public static Element getElement(String asPath, Element aoEle)
	{
		Element loResult = null;
		try
		{
			XPath loXpath = XPath.newInstance(asPath);
			loResult = (Element) loXpath.selectSingleNode(aoEle);
		}
		catch (JDOMException aoJDOMEx)
		{
			// throw new ApplicationException("Error occured in aceessing tree",
			// aoJDOMEx);
		}
		return loResult;
	}

	/**
	 * Deletes an element from a jdom satisfying some specific xpath condition
	 * 
	 * @param asXPathh - Xpath to be checked
	 * @param aoDoc - jdom from which element to be deleted
	 * @throws Exception
	 */
	public static void removeNode(String asXPath, Document aoDoc)
	{
		Element loNode = getElement(asXPath, aoDoc);
		if (loNode != null)
		{
			removeNode(loNode);
		}
	}

	/**
	 * Removes the node from its parent
	 * 
	 * @param aoElement - element to be removed
	 */
	public static void removeNode(Element aoElement)
	{
		if (aoElement.isRootElement())
		{
			aoElement.removeContent();
		}
		else
		{
			Element loParent = aoElement.getParentElement();
			loParent.removeContent(aoElement);
		}
	}

	/**
	 * Gets the xml as String from jdom
	 * 
	 * @param aoDoc - jdom object for which string has to be generated
	 * @return jdom object as String
	 * @throws Exception
	 */
	public static String getXMLAsString(Document aoDoc)
	{
		String lsReturn = null;
		Format loFormat = Format.getRawFormat();
		loFormat.setExpandEmptyElements(true);
		loFormat.setEncoding("UTF-8");
		XMLOutputter loXO = new XMLOutputter(loFormat);
		lsReturn = loXO.outputString(aoDoc.getRootElement());
		return lsReturn;
	}

	/**
	 * Gets the Element as String from element
	 * 
	 * @param aoElement - Element object for which string has to be generated
	 * @return Element object as String
	 * @throws Exception
	 */
	public static String getXMLAsString(Element aoElement)
	{
		String lsReturn = null;
		Format loFormat = Format.getRawFormat();
		loFormat.setExpandEmptyElements(true);
		loFormat.setEncoding("UTF-8");
		XMLOutputter loXO = new XMLOutputter(loFormat);
		lsReturn = loXO.outputString(aoElement);
		return lsReturn;
	}

	/**
	 * Writes the jdom object to Writer
	 * 
	 * @param aoDoc - jdom to be saved
	 * @param aoWriter - Writer object
	 * @throws Exception
	 */
	public static void writeDoc(Document aoDoc, Writer aoWriter)
	{
		XMLOutputter loXO = new XMLOutputter(Format.getPrettyFormat());
		try
		{
			loXO.output(aoDoc, aoWriter);
		}
		catch (IOException aoIOEx)
		{
			// throw new ApplicationException("Error occured in aceessing tree",
			// aoIOEx);
		}
	}

	/**
	 * Gets element list from jdom satisfying some specific xpath condition
	 * 
	 * @param asXPath - Xpath to be checked
	 * @param aoDoc - jdom from which element to be searched
	 * @return List of element satisfying the xpath condition
	 * @throws Exception
	 */
	public static List<Element> getElementList(String asXPath, Document aoDoc)
	{
		List<Element> loLresult = null;
		try
		{
			XPath loXpath = XPath.newInstance(asXPath);
			loLresult = loXpath.selectNodes(aoDoc);
		}
		catch (JDOMException aoJDOMEx)
		{
			// throw new ApplicationException("Error occured in aceessing tree",
			// aoJDOMEx);
		}
		return loLresult;
	}

	/**
	 * Gets element list from Element satisfying some specific xpath condition
	 * 
	 * @param asXPath - Xpath to be checked
	 * @param aoEle - Element object from which an element has to be searched
	 * @return List of element satisfying the xpath condition
	 * @throws Exception
	 */
	public static List<Element> getElementList(String asXPath, Element aoEle)
	{
		List<Element> loLresult = null;
		try
		{
			XPath loXpath = XPath.newInstance(asXPath);
			loLresult = loXpath.selectNodes(aoEle);
		}
		catch (JDOMException aoJDOMEx)
		{
			// throw new ApplicationException("Error occured in aceessing tree",
			// aoJDOMEx);
		}
		return loLresult;
	}
}