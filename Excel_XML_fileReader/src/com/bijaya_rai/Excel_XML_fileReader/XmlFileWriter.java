package com.bijaya_rai.Excel_XML_fileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
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

	protected void createXML(LinkedHashMap<String, String> dataFromExcel, LinkedHashMap<String, String> dataFromXml) {
		
		Iterator tempIt= dataFromExcel.entrySet().iterator();
	   
		while (tempIt.hasNext()) {
			 Map.Entry pair = (Map.Entry)tempIt.next();
			//	System.out.println(" sadf"+pair.getKey());
		
			 
			 //stupid mistake i am using the entire "<TEXT id="weekdays_shortest">" which wont match against the other
			 
			 if (dataFromXml.containsKey(pair.getKey())) {
				int pos = new ArrayList<String>(dataFromXml.keySet()).indexOf(pair.getKey());
			//	System.out.println(dataFromXml.keySet(pos).toString());
				//dataFromExcel.put("existing key", "newValue");
			  
				
				List<String> l = new ArrayList<String>(dataFromXml.values());
				l.get(0);
				System.out.println(l.toString());
			
			}
	    }
		
		
		
		writeToFile();
	}

	private void writeToFile() {
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
