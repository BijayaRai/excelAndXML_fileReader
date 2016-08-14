package com.bijaya_rai.Excel_XML_fileReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

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
	private LinkedHashMap<String, String> afterTranslate;
	Iterator tempIt;
	private Map.Entry pair;
	String fileName="";

	protected void createXML(LinkedHashMap<String, String> dataFromExcel, LinkedHashMap<String, String> dataFromXml) {

		tempIt = dataFromExcel.entrySet().iterator();

		afterTranslate = new LinkedHashMap<>();
	
		while (tempIt.hasNext()) {
			pair = (Map.Entry) tempIt.next();
			// System.out.println(pair.getKey());

			if (dataFromXml.containsKey(pair.getKey().toString())) {
				int pos = new ArrayList<String>(dataFromXml.keySet()).indexOf(pair.getKey());
				// System.out.println(dataFromXml.keySet(pos).toString());
				// dataFromExcel.put((String) pair.getKey(),
				// dataFromXml.get(pair.getKey().toString()));

				afterTranslate.put(pair.getKey().toString(), pair.getValue().toString());

				// System.out.println("Key:" + pair.getKey().toString());
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
		writeToTextFile( 0,afterTranslate);
		// writeToXMLFile();
	}

	protected void writeToTextFile( int xmlOrText, LinkedHashMap<String, String> afterTranslateText) {
		/*
		 * DateFormat df = new SimpleDateFormat("dd/MM/yy"); Date dateobj = new
		 * Date(); String date = df.format(dateobj).toString();
		 * 
		 * String filenm= new StringBuilder(fileName).append(date).toString();
		 * System.out.println(filenm);
		 */
		fileName="translatedText";
		try {
			File translatedTextFile;
			if (xmlOrText == 0) {
				translatedTextFile = new File(fileName + ".xml");
			} else
				translatedTextFile = new File(fileName + ".txt");

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
					textToWrite = pair.getKey().toString() +pair.getValue().toString() + "</TEXT>";
					bw.write(textToWrite);
					bw.newLine();
					tempIt.remove();
				}
			} else
			{
				while (tempIt.hasNext()) {
					pair = (Map.Entry) tempIt.next();
					textToWrite = pair.getKey().toString()+ "=" + pair.getValue().toString();
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
