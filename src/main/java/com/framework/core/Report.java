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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;



public class Report {


	String scenarioName=null;
	String scenarioLine=null;
	static String featureFileName=null;
	
	private static String reportPath=TestCaseDetails.getReportPath();

	private static Report report = null;
	private static ExtentTest test=null;
	private static ExtentReports extentReport=null;
	private static ExtentHtmlReporter reporter=null;

	static Logger log=Log.getLogInstance();
	PropertiesReader properties=PropertiesReader.getInstance();

	private static WebDriver driver=WebDriverFactory.getWebDriverFactoryInstance().getWebDriverInstance();

	private Report() throws IOException
	{
		/*
		System.out.println(reportPath+"\\"+scenarioName+"\\"+scenarioName+"_"+scenarioLine+"\\"+scenarioName+"_"+scenarioLine+".html");
		reporter=new ExtentHtmlReporter(reportPath+"\\"+scenarioName+"\\"+scenarioName+"_"+scenarioLine+"\\"+scenarioName+"_"+scenarioLine+".html");
		extentReport= new ExtentReports(reportPath+"\\"+scenarioName+"\\"+scenarioName+"_"+scenarioLine+"\\"+scenarioName+"_"+scenarioLine+".html");
		test = extentReport.startTest(scenarioName);*/
		
		scenarioName=TestCaseDetails.getScenarioName();
		scenarioLine=TestCaseDetails.getScenarioLine();
		featureFileName=TestCaseDetails.getFeatureFileName();
		
		System.out.println(reportPath+"\\"+featureFileName+"\\"+featureFileName+".html");
		reporter=new ExtentHtmlReporter(reportPath+"\\"+featureFileName+"\\"+featureFileName+".html");
		reporter.setAppendExisting(true);
		extentReport= new ExtentReports();
		extentReport.attachReporter(reporter);
		test=extentReport.createTest(scenarioName+"_"+scenarioLine);
		
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

	/*LogStatus logStatus
	test.log(logStatus,stepName,test.addScreenCapture(capture(driver,scenarioName,scenarioLine)));
	test.log(logStatus,stepName);*/
	
	public void updateTestLog(Status logStatus,String stepName)
	{
		try {
			switch (logStatus) {

			case PASS:
				if(properties.getValue("PassedSteps").equalsIgnoreCase("YES"))
				{
					test.pass(stepName, MediaEntityBuilder.createScreenCaptureFromPath(capture(driver,scenarioName,scenarioLine)).build());
				}
				else
				{
					test.pass(stepName);
				}
				break;

			case FAIL:
				if(properties.getValue("FailedSteps").equalsIgnoreCase("YES"))
				{
					test.fail(stepName, MediaEntityBuilder.createScreenCaptureFromPath(capture(driver,scenarioName,scenarioLine)).build());
				}
				else
				{
					test.fail(stepName);
				}
				break;

			default:
				test.log(Status.INFO,stepName);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}

	}


	private static String capture(WebDriver driver,String scenarioName,String scenarioLine) {

		String filePath=null;
		try {
			File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			//File destination = new File(reportPath+"\\"+scenarioName+"\\"+scenarioName+"_"+scenarioLine+"\\Screenshots\\"+scenarioName+"_"+scenarioLine+"_"+System.currentTimeMillis()+".png");
			File destination = new File(reportPath+"\\"+featureFileName+"\\Screenshots\\"+scenarioName+"_"+scenarioLine+"_"+System.currentTimeMillis()+".png");

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
			extentReport.flush();
			//extentReport.close();
			report=null;
		}
	}


}
