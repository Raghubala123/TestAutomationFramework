package com.framework.stepdefinitions;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.openqa.selenium.WebDriver;

import com.framework.core.Database;
import com.framework.core.Keywords;
import com.framework.core.Log;
import com.framework.core.PropertiesReader;
import com.framework.core.Report;
import com.framework.core.TestCaseDetails;
import com.framework.core.TestData;
import com.framework.core.TestCaseDetails;
import com.framework.core.WebDriverFactory;
import com.framework.methods.ReusableMethods;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefinitions {

	public static WebDriver driver;
	static Report report;
	static Keywords keywords;
	static Logger log;
	static TestData testData;
	
	SimpleLayout layout;    
    FileAppender appender;
    
    String scenarioName=null;
	String scenarioLine=null;
	String reportPath=null;


	@Before
	public void initialize(Scenario scenario) throws Exception
	{
		String[] uri=scenario.getUri().toString().split("/");
		String featureFileName=uri[uri.length-1].replaceAll(".feature","").trim();
	
		
		TestCaseDetails.setFeatureFileName(featureFileName);
		TestCaseDetails.setScenarioName(scenario.getName());
		TestCaseDetails.setScenarioLine(String.valueOf(scenario.getLine()));
		
		scenarioName=TestCaseDetails.getScenarioName();
		scenarioLine=TestCaseDetails.getScenarioLine();
		reportPath=TestCaseDetails.getReportPath();
		
		PropertiesReader properties = PropertiesReader.getInstance();
		log=Log.getLogInstance();
		WebDriverFactory factory=WebDriverFactory.getWebDriverFactoryInstance();
		driver=factory.getWebDriverInstance();
		report=Report.getReportInstance();
		testData=TestData.getTestDataInstance();
		keywords= new Keywords();
		//database=Database.getDBInstance();
		
		layout = new SimpleLayout();    
		   // appender = new FileAppender(layout,reportPath+"\\"+scenarioName+"\\"+scenarioName+"_"+scenarioLine+"\\debug.log",false);
			appender = new FileAppender(layout,reportPath+"\\"+featureFileName+"\\debug.log",true);
		    log.addAppender(appender);
	    
	    //driver.manage().timeouts().implicitlyWait(Long.valueOf(properties.getValue("ImplicitWaitTimeout")), TimeUnit.SECONDS);
	    
	    log.info("*********************BEGINNING OF THE SCENARIO-"+scenarioName.toUpperCase()+"_"+scenarioLine+"********************");
	}


	@Given("the user navigates to the web site") 
	public void navigateToPage() { 
		ReusableMethods reusableMethods= new ReusableMethods();
		reusableMethods.launchURL();  
	} 

	@When("the user enters the text in the search field") 
	public void searchText() {
		ReusableMethods reusableMethods= new ReusableMethods();
		reusableMethods.searchText();
	} 

	@And("clicks the search button") 
	public void clickSearchButton() { 
		ReusableMethods reusableMethods= new ReusableMethods();
		reusableMethods.clickSearchButton();
	} 

	@Then("the user will be able to view the search results by verifying the title") 
	public void viewSearchResults() { 
		ReusableMethods reusableMethods= new ReusableMethods();
		reusableMethods.checkIfResultsAreDisplayed();
	}

	@After
	public void terminate() throws Exception
	{
		log.info("*********************END OF THE SCENARIO-"+scenarioName.toUpperCase()+"_"+scenarioLine+"********************");
		log.removeAllAppenders();
		appender.close();
		
		Class className=this.getClass();
		String methodName=new Throwable().getStackTrace()[0].getMethodName();
		Annotation annotationName = null;
		
		Method[] methods=className.getMethods();
		for(Method method:methods)
		{
			if(method.getName().equals(methodName))
			{
				for(Annotation annotation:method.getAnnotations())
				{
					if(annotation.toString().contains("@io.cucumber.java.After("))
					{
						annotationName=annotation;
						break;
					}
				}
			}	
		}
		Report.closeReport(annotationName);
		testData.closeWorkbook(annotationName);
		//Database.getDBInstance().closeDatabaseConnection(annotationName);	
	}

}
