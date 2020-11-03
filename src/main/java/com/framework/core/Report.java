package com.framework.core;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;

import io.cucumber.java.Scenario;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Report {


	String scenarioName=TestCaseDetails.getScenarioName();;
	String scenarioLine=TestCaseDetails.getScenarioLine();
	
	private static String reportPath=TestCaseDetails.getReportPath();

	private static Report report = null;
	private static ExtentTest test=null;
	private static ExtentReports extentReport=null;
	
	static Logger log=Log.getLogInstance();
	
	private static WebDriver driver=WebDriverFactory.getWebDriverFactoryInstance().getWebDriverInstance();

	private Report() throws IOException
	{
		System.out.println(reportPath+"\\"+scenarioName+"\\"+scenarioName+"_"+scenarioLine+"\\"+scenarioName+"_"+scenarioLine+".html");
		extentReport= new ExtentReports(reportPath+"\\"+scenarioName+"\\"+scenarioName+"_"+scenarioLine+"\\"+scenarioName+"_"+scenarioLine+".html");
		test = extentReport.startTest(scenarioName);
	}

	public static Report getReportInstance()
	{
		try {
			if(report==null)
			{
				report=new Report();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return report;
	}

	public void updateTestLog(LogStatus logStatus,String stepName)
	{
		try {
			test.log(logStatus,stepName,test.addScreenCapture(capture(driver,scenarioName,scenarioLine)));
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}

	}


	private static String capture(WebDriver driver,String scenarioName,String scenarioLine) {

		String filePath=null;
		try {
			File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destination = new File(reportPath+"\\"+scenarioName+"\\"+scenarioName+"_"+scenarioLine+"\\Screenshots\\"+scenarioName+"_"+scenarioLine+"_"+System.currentTimeMillis()+".png");
			filePath = destination.getAbsolutePath();
			FileUtils.copyFile(sourceFile,destination);
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}

		return filePath;
	}


	public static void closeReport(Annotation annotation)
	{
		if(annotation.toString().contains("@io.cucumber.java.After("))
		{
			extentReport.endTest(test);
			extentReport.flush();
			extentReport.close();
			report=null;
		}
	}


}
