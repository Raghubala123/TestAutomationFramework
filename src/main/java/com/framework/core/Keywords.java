package com.framework.core;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.relevantcodes.extentreports.LogStatus;
import com.framework.core.WebDriverFactory;


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
	
	FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);

	Select select=null;

	Actions actions=null;

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
			report.updateTestLog(Status.PASS, "Navigated to the URL '"+url+"' successfully");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
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
			report.updateTestLog(Status.PASS, "Clicked the Element '"+elementName+"' successfully");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
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
			report.updateTestLog(Status.PASS, "Entered the text '"+value+"' on the Element '"+elementName+"' successfully");

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
		}	
	}

	/**
	 * Returns the title of the current window.
	 *  @param None
	 *  @version 1.0
	 *  @return the title of the current window
	 *  */
	public String getTitle() throws Exception
	{
		String title=null;
		try {
			title=driver.getTitle();
			log.info("Window title is :"+title);
			report.updateTestLog(Status.PASS,"Window title is :"+title);

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
		}

		return title;
	}

	/**
	 * Returns the URL of the current window.
	 *  @param None
	 *  @version 1.0
	 *  @return the URL of the current window
	 *  */
	public String getCurrentURL() throws Exception
	{
		String url=null;
		try {
			url=driver.getCurrentUrl();
			log.info("Window title is :"+url);
			report.updateTestLog(Status.PASS,"Window URL is :"+url);

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
		}

		return url;
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
			log.info("The Element '"+webElement+"' is visible");
			report.updateTestLog(Status.PASS, "The Element '"+webElement+"' is visible");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
		}
	}


	/**
	 * Waits for an element to be clickable in the DOM.
	 *  @param element         The web element.
	 * 	@param webElement	   The name of the web element.
	 *  @version 1.0
	 *  @return none
	 *  */
	public void waitForElementToBeClickable(WebElement element,String webElement)
	{
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			log.info("The Element '"+webElement+"' is clickable");
			report.updateTestLog(Status.PASS, "The Element '"+webElement+"' is clickable");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
		}
	}


	/**
	 * Waits for a list of all elements to be visible in the DOM.
	 *  @param element         The list of web elements.
	 * 	@param webElement	   The name of the list.
	 *  @version 1.0
	 *  @return none
	 *  */
	public void waitForWebElementListToBeVisible(List<WebElement> element,String listName)
	{
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(element));
			log.info("The Elements '"+listName+"' is visible");
			report.updateTestLog(Status.PASS, "The Elements '"+listName+"' is visible");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
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
			report.updateTestLog(Status.PASS, "Set the value of the attribute "+attributeName+" of the element "+webElementName+" as '"+attributeValue+"'");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
		}
	}

	public void JSClick(WebElement element, String webElementName)
	{
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			executor.executeScript("arguments[0].click();", element);
			log.info("Clicked the element '"+webElementName+"' successfully");
			report.updateTestLog(Status.PASS, "Clicked the element '"+webElementName+"' successfully");

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
		}
	}


	public void JSClickUsingID(WebElement element, String webElementName)
	{
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			String id=element.getAttribute("id");

			executor.executeScript("document.getElementById('"+id+"').click();");
			log.info("Clicked the element '"+webElementName+"' successfully");
			report.updateTestLog(Status.PASS, "Clicked the element '"+webElementName+"' successfully");

		}catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
		}
	}


	public void switchToWindowByTitle(String title)
	{
		boolean isWindowAvailable=false;

		try {
			for(String handle:driver.getWindowHandles())
			{
				driver.switchTo().window(handle);
				if(driver.getTitle().contains(title))
				{
					isWindowAvailable=true;
					log.info("Navigated to the page having the title '"+title+"' successfully");
					report.updateTestLog(Status.PASS, "Navigated to the window having the title '"+title+"' successfully");
					break;
				}
			}

			if(isWindowAvailable==false)
			{
				log.info("No window with the title '"+title+"' is available");
				report.updateTestLog(Status.FAIL, "No window with the title '"+title+"' is available");
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
		}
	}



	public void switchToWindowByURL(String url)
	{
		boolean isWindowAvailable=false;

		try {
			for(String handle:driver.getWindowHandles())
			{
				driver.switchTo().window(handle);
				if(driver.getTitle().contains(url))
				{
					isWindowAvailable=true;
					log.info("Navigated to the page having the URL '"+url+"' successfully");
					report.updateTestLog(Status.PASS, "Navigated to the window having the URL '"+url+"' successfully");
					break;
				}
			}

			if(isWindowAvailable==false)
			{
				log.info("No window with the URL '"+url+"' is available");
				report.updateTestLog(Status.FAIL, "No window with the URL '"+url+"' is available");
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
		}
	}

	public void switchToFrame(String name)
	{
		try{
			driver.switchTo().frame(name);
			log.info("Switched to frame having the name '"+name+"' successfully");
			report.updateTestLog(Status.PASS, "Switched to frame having the name '"+name+"' successfully");
			
		}catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
		}
	}
	
	public void switchToFrame(int id)
	{
		try{
			driver.switchTo().frame(id);
			log.info("Switched to frame having the ID '"+id+"' successfully");
			report.updateTestLog(Status.PASS, "Switched to frame having the ID '"+id+"' successfully");
			
		}catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
		}
	}
	
	public void switchToFrame(WebElement element, String name)
	{
		try{
			driver.switchTo().frame(element);
			log.info("Switched to the Web Element '"+name+"' successfully");
			report.updateTestLog(Status.PASS, "Switched to the Web Element '"+name+"' successfully");
			
		}catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
		}
	}
	
	public void selectElementByVisibleText(WebElement element, String name,String value)
	{
		try{
			fluentWait.until(ExpectedConditions.visibilityOf(element));
			select= new Select(element);
			select.selectByVisibleText(value);
			log.info("Selected the option '"+value+"' from the dropdown '"+name+"' successfully");
			report.updateTestLog(Status.PASS, "Selected the option '"+value+"' from the dropdown '"+name+"' successfully");
			
		}catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			report.updateTestLog(Status.FAIL,e.getMessage());
		}
	}























}

