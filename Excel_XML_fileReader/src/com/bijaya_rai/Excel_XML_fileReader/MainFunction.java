package com.bijaya_rai.Excel_XML_fileReader;

import java.awt.Dimension;
import java.util.LinkedHashMap;

import javax.swing.JFrame;

/**
 * @author Bijaya Rai
 *
 */
public class MainFunction extends JFrame {


	public static void main(String[] args) {
		FileChooserTest gui= new FileChooserTest();
		gui.runGUI();
		
		String tempExcel = "text.xlsx";
		ExcelDataReader edr = new ExcelDataReader();
		LinkedHashMap<String, String> dataFromExcel = edr.functionHandler(tempExcel);
	//	System.out.println(path);
		
		
		tempExcel = "ApplicationTexts.xml";
		XmlFileReader xfr = new XmlFileReader();
		LinkedHashMap<String, String> dataFromXML=xfr.functionHandler(tempExcel);
		//System.out.println(path);
		
		
		XmlFileWriter xfw= new XmlFileWriter();
		xfw.createXML(dataFromExcel, dataFromXML);
		
		
	}

}
