package com.bijaya_rai.Excel_XML_fileReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Bijaya Rai
 *
 */
public class XmlFileWriter {
	DocumentBuilderFactory dbf;
	DocumentBuilder builder;
	Document doc;
	final String location=System.getProperty("user.home")+File.separator+"Desktop";
	private LinkedHashMap<String, String> afterTranslate;
	protected LinkedHashMap<String, String> pureDataFromXML;
	Iterator tempIt;
	private Map.Entry pair;
	String fileName = "";

	protected void createXML(LinkedHashMap<String, String> dataFromExcel, LinkedHashMap<String, String> dataFromXml) {

		tempIt = dataFromExcel.entrySet().iterator();
		// <TEXT id="month_names_short">

		afterTranslate = new LinkedHashMap<>();
		pureDataFromXML = new LinkedHashMap<>();
		String s = " ", finalString;
		while (tempIt.hasNext()) {
			pair = (Map.Entry) tempIt.next();
			// System.out.println(pair.getKey());

			if (dataFromXml.containsKey(pair.getKey().toString())) {
				// int pos = new
				// ArrayList<String>(dataFromXml.keySet()).indexOf(pair.getKey());
				// System.out.println(pair.getKey().toString());
				// dataFromExcel.put((String) pair.getKey(),
				// dataFromXml.get(pair.getKey().toString()));
				s = pair.getKey().toString();

				finalString = s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\""));

				afterTranslate.put(pair.getKey().toString(), pair.getValue().toString());
				pureDataFromXML.put(finalString, pair.getValue().toString());
				// System.out.println(finalString);
				// Value:"+pair.getValue());

			}

		}

		/*
		 * Iterator tempIt = dataFromXml.entrySet().iterator(); while
		 * (tempIt.hasNext()) { // assign each pair to Map.Entry pair =
		 * (Map.Entry) tempIt.next(); // System.out.println(pair.getKey() +
		 * " = " + pair.getValue()); i++; tempIt.remove(); }
		 * System.out.println(i);
		 */
		writeToTextFile(1, pureDataFromXML);
		writeToTextFile(0, afterTranslate);
		// writeToXMLFile();
	}

	protected void writeToTextFile(int xmlOrText, LinkedHashMap<String, String> afterTranslateText) {
		/*
		 * DateFormat df = new SimpleDateFormat("dd/MM/yy"); Date dateobj = new
		 * Date(); String date = df.format(dateobj).toString();
		 * 
		 * String filenm= new StringBuilder(fileName).append(date).toString();
		 * System.out.println(filenm);
		 */
		System.out.println(location);
		fileName = "translatedText";
		try {
			File translatedTextFile;
			if (xmlOrText == 0) {
				translatedTextFile = new File(location,fileName + ".xml");
			} else
				translatedTextFile = new File(location,fileName + ".txt");

			if (!translatedTextFile.exists()) {

				translatedTextFile.createNewFile();
			
			}

			FileWriter writer = new FileWriter(translatedTextFile);
			BufferedWriter bw = new BufferedWriter(writer);

			tempIt = afterTranslateText.entrySet().iterator();

			String textToWrite = "";

			if (xmlOrText == 0) {
				while (tempIt.hasNext()) {
					pair = (Map.Entry) tempIt.next();
					textToWrite = pair.getKey().toString() + pair.getValue().toString() + "</TEXT>";
					bw.write(textToWrite);
					bw.newLine();
					tempIt.remove();
				}
			} else {
				while (tempIt.hasNext()) {
					pair = (Map.Entry) tempIt.next();
					textToWrite = pair.getKey().toString() + "=" + pair.getValue().toString();
					bw.write(textToWrite);
					bw.newLine();
					tempIt.remove();
				}
			}

			bw.newLine();

			bw.write(textToWrite);
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void writeToXMLFile() {
		dbf = DocumentBuilderFactory.newInstance();
		try {
			builder = dbf.newDocumentBuilder();

			doc = builder.newDocument();

			// creating root elements
			Element rootElement = doc.createElement("Localisation");
			doc.appendChild(rootElement);
			// creating sub elements
			Element text = doc.createElement("TEXT");
			text.setAttribute("id", "TEST VALUE");
			text.appendChild(doc.createTextNode("TEXT NODE VALUE"));
			rootElement.appendChild(text);

			// writing xml data to xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource src = new DOMSource(doc);

			StreamResult res = new StreamResult(new File("result.xml"));

			transformer.transform(src, res);
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
