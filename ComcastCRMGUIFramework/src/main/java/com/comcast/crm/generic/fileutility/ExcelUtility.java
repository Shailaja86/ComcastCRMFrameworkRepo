package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcel(String sheet,int rowNum,int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./src/test/resources/testScriptDataa.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data=wb.getSheet(sheet).getRow(rowNum).getCell(cellNum).toString();
		wb.close();
		return data;
	}
	public int getRowCount(String sheet) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./src/test/resources/testScriptDataa.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowCount=wb.getSheet(sheet).getLastRowNum();
		wb.close();
		return rowCount;
	}
	public void setDataIntoExcel(String sheet,int rowNum,int cellNum,String data) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis=new FileInputStream("./src/test/resources/testScriptDataa.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		//workbook in read mode
		wb.getSheet(sheet).getRow(rowNum).createCell(cellNum).setCellValue(data);
		FileOutputStream fos=new FileOutputStream("./src/test/resources/testScriptDataa.xlsx");
		wb.write(fos);
		wb.close();
		
	}
	

}
