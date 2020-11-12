package com.framework.runners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.framework.core.Log;
import com.framework.core.Report;
import com.framework.core.TestCaseDetails;
import com.framework.core.WebDriverFactory;

import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features ="src\\test\\resources\\Feature",
glue={"com.framework.stepdefinitions"},
tags=""
)
//@Bing2 or @Google4
public class TestRunner {
	
	public static WebDriver driver=WebDriverFactory.getWebDriverFactoryInstance().getWebDriverInstance();
	static Report report;

	@BeforeClass
	public static void initialize() {
		
		DOMConfigurator.configure("log4j.xml");
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		Date d= new Date();
		String timeStamp=sdf.format(d);
		String reportPath=System.getProperty("user.dir")+"\\Results\\"+timeStamp;
		File file= new File(reportPath);

		if(!file.exists())
		{
			file.mkdir();
		}

		TestCaseDetails.setReportPath(reportPath);

	}
	
	
	@AfterClass
	public static void terminate()
	{
		driver.quit();	
	}

}
