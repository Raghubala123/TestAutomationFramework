package com.framework.methods;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.framework.core.Keywords;
import com.framework.core.Log;
import com.framework.core.Report;
import com.framework.core.TestCaseDetails;
import com.framework.core.TestData;
import com.framework.core.WebDriverFactory;
import com.framework.pageobjects.SearchEnginePage;
import com.relevantcodes.extentreports.LogStatus;

public class ReusableMethods {
	
	Keywords keywords= new Keywords();
	private WebDriver driver= WebDriverFactory.getWebDriverFactoryInstance().getWebDriverInstance();
	//Report report= Report.getReportInstance();
	Logger log=Log.getLogInstance();
	//static TestData testData=TestData.getTestDataInstance();
	Report report=Report.getReportInstance();
	TestData testData=TestData.getTestDataInstance();
	
	public void launchURL()
	{
		try {
			String url=testData.getData("TestData","URL");
			keywords.get(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void searchText()
	{
		try {
			PageFactory.initElements(driver,SearchEnginePage.class);
			String value= testData.getData("TestData","text");
			
			new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(SearchEnginePage.searchField));
			//System.out.println(SearchEnginePage.searchField.size());
			//keywords.waitForElementToBeVisible(SearchEnginePage.searchField, "Search Field");
			keywords.click(SearchEnginePage.searchField, "Search Field");
			keywords.sendKeys(SearchEnginePage.searchField, "Search Field", value);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public void clickSearchButton()
	{
		try {
			PageFactory.initElements(driver,SearchEnginePage.class);
			keywords.setElementAttribute(SearchEnginePage.searchFieldDropdown,"search field dropdown","style","display: none;");
			keywords.waitForElementToBeVisible(SearchEnginePage.searchButton, "Search Button");
			keywords.click(SearchEnginePage.searchButton, "Search Button");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkIfResultsAreDisplayed()	{
		
		try {
			String value= testData.getData("TestData","text");
			
			if(driver.getTitle().contains(value))
			{
				log.info("Results Displayed on the screen");
				report.updateTestLog(Status.PASS,"Results Displayed on the screen");
			}
			else
			{
				log.info("No Results Displayed");
				report.updateTestLog(Status.FAIL,"No Results Displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
