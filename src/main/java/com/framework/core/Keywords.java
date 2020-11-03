package com.framework.core;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import io.cucumber.java.Scenario;


/**
 * This class contains keywords used in creating the automation scripts. 
 */

public class Keywords {


	static WebDriver driver=WebDriverFactory.getWebDriverFactoryInstance().getWebDriverInstance();
	//Report report= Report.getReportInstance();
	PropertiesReader properties= PropertiesReader.getInstance();
	Logger log=Log.getLogInstance();

	Report report=Report.getReportInstance();
	//TestData testData=TestCaseDetails.getTestData();

	JavascriptExecutor executor= (JavascriptExecutor)driver;

	WebDriverWait wait=new WebDriverWait(driver,Integer.parseInt(properties.getValue("Timeout")));

	/**
	 * Launches the application in the browser.
	 *  @param url                the URL of the application.
	 *  @version 1.0
	 *  @return void
	 *  */
	public void get(String url)
	{
		try {
			driver.get(url);
			log.info("Navigated to the URL '"+url+"' successfully");
			report.updateTestLog(LogStatus.PASS, "Navigated to the URL '"+url+"' successfully");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(LogStatus.FAIL,e.getMessage());
		}	
	}

	/**
	 * Clicks an element in the application.
	 *  @param element 			The web element to be clicked.
	 *  @param elementName		The name of the web element
	 *  @version 1.0
	 *  @return void
	 *  */
	public void click(WebElement element, String elementName)
	{
		try {
			element.click();
			log.info("Clicked the Element "+elementName+" successfully");
			report.updateTestLog(LogStatus.PASS, "Clicked the Element '"+elementName+"' successfully");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(LogStatus.FAIL,e.getMessage());
		}	
	}

	/**
	 * Enters text on a field in the application.
	 *  @param element 		   The field in which the text is to be typed.
	 * 	@param elementName	   The name of the field.
	 * 	@param value  	       The value to be entered in the field.
	 *  @version 1.0
	 *  @return None
	 *  */
	public void sendKeys(WebElement element, String elementName, String value)
	{
		try {
			element.sendKeys(value);
			log.info("Entered the text '"+value+"' on the Element '"+elementName+"' successfully");
			report.updateTestLog(LogStatus.PASS, "Entered the text '"+value+"' on the Element '"+elementName+"' successfully");

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(LogStatus.FAIL,e.getMessage());
		}	
	}

	/**
	 * Returns the title of the current window.
	 *  @param None
	 *  @version 1.0
	 *  @return the title of the current window
	 *  */
	public void getTitle() throws Exception
	{
		try {
			String value=driver.getTitle();
			log.info("Window title is :"+value);
			report.updateTestLog(LogStatus.PASS,"Window title is :"+value);

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(LogStatus.FAIL,e.getMessage());
		}	
	}


	/**
	 * Waits for an element to be visible in the DOM.
	 *  @param element         The web element.
	 * 	@param webElement	   The name of the web element.
	 *  @version 1.0
	 *  @return none
	 *  */
	public void waitForElementToBeVisible(WebElement element,String webElement)
	{
		try {

			wait.until(ExpectedConditions.visibilityOf(element));
			log.info("Element '"+webElement+"' is visible");
			report.updateTestLog(LogStatus.PASS, "Element '"+webElement+"' is visible");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(LogStatus.FAIL,e.getMessage());
		}
	}

	/**
	 * Changes the attribute of an element in the DOM.
	 *  @param element             The web element.
	 * 	@param webElementName	   The name of the web element.
	 *  @param attributeName       The name of the attribute whose value is to be changed.
	 *  @param attributeValue      The new attribute value that is to be set.
	 *  @version 1.0
	 *  @return none
	 *  */
	public void setElementAttribute(WebElement element,String webElementName,String attributeName,String attributeValue)
	{
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			executor.executeScript("arguments[0].setAttribute('"+attributeName+"','"+attributeValue+"');", element);
			log.info("Set the value of the attribute '"+attributeName+"' of the element '"+webElementName+"' as '"+attributeValue+"'");
			report.updateTestLog(LogStatus.PASS, "Set the value of the attribute "+attributeName+" of the element "+webElementName+" as '"+attributeValue+"'");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(LogStatus.FAIL,e.getMessage());
		}
	}

	public void clickUsingJS(WebElement element, String webElementName)
	{
		try {
			//wait.until(ExpectedConditions.visibilityOf(element));
			String id=element.getAttribute("id");
			if(id!=null)
			{
				executor.executeScript("document.getElementById('"+id+"').click();");
				log.info("Clicked the element '"+webElementName+"' successfully");
				report.updateTestLog(LogStatus.PASS, "Clicked the element '"+webElementName+"' successfully");
			}
			else
			{
				wait.until(ExpectedConditions.visibilityOf(element));
				executor.executeScript("arguments[0].click();", element);
				log.info("Clicked the element '"+webElementName+"' successfully");
				report.updateTestLog(LogStatus.PASS, "Clicked the element '"+webElementName+"' successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(LogStatus.FAIL,e.getMessage());
		}
	}

}
