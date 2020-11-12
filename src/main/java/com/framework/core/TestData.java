package com.framework.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData  {

	static TestData testData=null;

	static String featureFileName=null;
	static String scenarioName=null;
	static String scenarioLine=null;
	
	static Logger log=Log.getLogInstance();

	static XSSFWorkbook workbook=null;
	static XSSFSheet sheet=null;
	static XSSFRow row= null;
	static XSSFCell cell=null;


	
	
	private TestData() throws Exception
	{
		featureFileName=TestCaseDetails.getFeatureFileName();
		scenarioName=TestCaseDetails.getScenarioName();
		scenarioLine=TestCaseDetails.getScenarioLine();
		workbook = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\"+featureFileName+".xlsx")));
	}

	public static TestData getTestDataInstance()
	{
		if(testData==null)
		{
			try {
				testData= new TestData();
			} catch (Exception e) {
				e.printStackTrace();
				log.info(e.getMessage());
			}
		}

		return testData;
	}


	public String getData(String sheetName,String columnName) throws Exception
	{
		String cellData=null;

		try {

			int columnIndex=0;   
			sheet = workbook.getSheet(sheetName); 
			row=sheet.getRow(0);
			for(int i=0;i<row.getPhysicalNumberOfCells();i++)
			{
				XSSFCell cell= row.getCell(i);
				if(cell.getStringCellValue().equals(columnName))
				{
					columnIndex=i;
					break;
				}
			}

			for(int j=0;j<sheet.getPhysicalNumberOfRows();j++)
			{
				row=sheet.getRow(j);
				if(row.getCell(0).getStringCellValue().equals(scenarioName) && row.getCell(1).getStringCellValue().equals(scenarioLine))
				{
					XSSFCell cell=row.getCell(columnIndex);
					cellData=cell.getStringCellValue();
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}

		return cellData;
	}
	
	public void closeWorkbook(Annotation annotation) throws Exception
	{
		if(annotation.toString().contains("@io.cucumber.java.After("))
		{
			workbook.close();
			testData=null;
		}
	}

}
