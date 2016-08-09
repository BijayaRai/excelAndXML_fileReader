package com.bijaya_rai.Excel_XML_fileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Bijaya Rai
 *
 */
public class ExcelDataReader {

	private LinkedHashMap<String, String> dataFromExcel=null;
	
	/**
	 * use this function to manage other funtion in this class
	 */
	protected LinkedHashMap<String, String> functionHandler(String temp) {
		
		dataFromExcel= new LinkedHashMap<String,String>();
		fileReader(temp);
		return dataFromExcel;
		
		/*
		 * testing if the LinkedHaspMap is populated with KV pair
		Iterator tempIt= dataFromExcel.entrySet().iterator();
		    while (tempIt.hasNext()) {
		    	//assign each pair to 
		        Map.Entry pair = (Map.Entry)tempIt.next();
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		        tempIt.remove();
		    }
		*/
		

	}

	private void fileReader(String fileName)
	{
		//LinkedHashMap<String, String> dataFromExcel= new LinkedHashMap<String,String>();
		
		List tempList= new ArrayList();
		FileInputStream file=null;
		
		try {
			//inpute stream for data collection
			 file= new FileInputStream(new File(fileName));
			
			XSSFWorkbook workbook= new XSSFWorkbook(file);
			
			//get the sheet at index
			XSSFSheet sheet= workbook.getSheetAt(0);
			
			Iterator rows= sheet.rowIterator();
			int maxCell= sheet.getRow(0).getLastCellNum();
			while(rows.hasNext())
			{
				XSSFRow row= (XSSFRow) rows.next();
				
				Iterator cells= row.cellIterator();
				
				List data= new ArrayList<>();
				
				for(int cellNum=0;cellNum<maxCell;cellNum++){
					XSSFCell cell;
					
					if(row.getCell(cellNum)==null){
						cell=row.createCell(cellNum);
					}else
						cell=row.getCell(cellNum);
					
					data.add(cell);
				//	System.out.println(data.get(0));
				}
				
				tempList.add(data);
			}
		//	System.out.println(tempList.get(0));
			file.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createKVpair(tempList);
	}
	private void createKVpair(List tempList) {
 
		for (int i = 0; i < tempList.size(); i++) {
            List list = (List) tempList.get(i);
            for (int j = 0; j < list.size(); j++) {
                Cell cell = (Cell) list.get(j);
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                   // System.out.print(cell.getNumericCellValue());
                } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                  //  System.out.print(cell.getRichStringCellValue());
                } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                  //  System.out.print(cell.getBooleanCellValue());
                } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                  //  System.out.print("NULL value");
                }
            }
            dataFromExcel.put(list.get(0).toString(),list.get(1).toString());
        }
	
    }

}
