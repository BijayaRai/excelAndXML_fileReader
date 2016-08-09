package com.bijaya_rai.Excel_XML_fileReader;

import java.util.LinkedHashMap;

/**
 * @author Bijaya Rai
 *
 */
public class MainFunction {


	public static void main(String[] args) {
		String tempExcel = "text.xlsx";
		ExcelDataReader edr = new ExcelDataReader();
		LinkedHashMap<String, String> dataFromExcel = edr.functionHandler(tempExcel);

		tempExcel = "ApplicationTexts.xml";
		XmlFileReader xfr = new XmlFileReader();
		LinkedHashMap<String, String> dataFromXML=xfr.functionHandler(tempExcel);

		XmlFileWriter xfw= new XmlFileWriter();
		xfw.createXML(dataFromExcel, dataFromXML);
	}

}
