package com.bijaya_rai.Excel_XML_fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Bijaya Rai
 *
 */
public class XmlFileReader {
	private LinkedHashMap<String, String> dataFromXML = null;

	DocumentBuilderFactory dbf;
	DocumentBuilder builder;
	Document doc;

	protected LinkedHashMap<String, String> functionHandler(String fileName) {
		dataFromXML = new LinkedHashMap<>();
		dbf = DocumentBuilderFactory.newInstance();
		try {
			builder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileReader(fileName);

		// int i=0;
		// testing if the LinkedHaspMap is populated with KV pair
		/*
		 * Iterator tempIt= dataFromXML.entrySet().iterator(); while
		 * (tempIt.hasNext()) { //access each KV pair from hashmap Map.Entry
		 * pair = (Map.Entry)tempIt.next(); System.out.println(pair.getKey() +
		 * " = " + pair.getValue()); tempIt.remove();
		 * 
		 * i++;}
		 * 
		 * System.out.println("XML"+i);
		 */
		return dataFromXML;
	}

	private void fileReader(String fileName) {
		try {
			File fis = new File(fileName);

			doc = builder.parse(fis);

			// get the root element of the xml file
			Element root = doc.getDocumentElement();
			String tempData = "";
			NodeList list = doc.getElementsByTagName("TEXT");
			Element xmlData;
			for (int i = 0; i < list.getLength(); i++) {
				xmlData = (Element) list.item(i);
				tempData = "<TEXT id=\"" + xmlData.getAttribute("id") + "\"" + ">";
				dataFromXML.put(tempData, xmlData.getTextContent());

				// System.out.println(xmlData.getAttribute("id")+":"+
				// xmlData.getTextContent());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
