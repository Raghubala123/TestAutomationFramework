package com.framework.core;

import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;



public class WebDriverFactory {

	static WebDriver driver;
	private static WebDriverFactory webDriverFactory=null;
	PropertiesReader properties;
	String browser;
	String path;
	String scenarioName=TestCaseDetails.getScenarioName();
	String scenarioLine=TestCaseDetails.getScenarioLine();
	DesiredCapabilities capabilities;



	String USERNAME=null;
	String ACCESS_KEY=null;
	String URL=null;


	private WebDriverFactory() throws Exception
	{
		properties= PropertiesReader.getInstance();

		USERNAME=properties.getValue("UserName");
		ACCESS_KEY = properties.getValue("AccessKey");
		URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

		browser=properties.getValue("Browser");

		switch(browser)
		{

		case "CHROME":
			path=properties.getValue("ChromeDriverPath");
			if(path.equalsIgnoreCase("DEFAULT"))
			{
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			}
			else
			{
				System.setProperty("webdriver.chrome.driver",path);
			}

			driver=new ChromeDriver();
			break;



		case "FIREFOX":
			path=properties.getValue("FirefoxDriverPath");
			if(path.equalsIgnoreCase("DEFAULT"))
			{
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
			}
			else
			{
				System.setProperty("webdriver.gecko.driver",path);
			}

			driver=new FirefoxDriver();
			break;



		case "INTERNET EXPLORER":
			path=properties.getValue("IEDriverPath");
			if(path.equalsIgnoreCase("DEFAULT"))
			{
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\Drivers\\IEDriverServer.exe");
			}
			else
			{
				System.setProperty("webdriver.ie.driver",path);
			}

			driver=new InternetExplorerDriver();
			break;


		case "EDGE":
			path=properties.getValue("EdgeDriverPath");
			if(path.equalsIgnoreCase("DEFAULT"))
			{
				System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"\\Drivers\\IEDriverServer.exe");
			}
			else
			{
				System.setProperty("webdriver.edge.driver",path);
			}

			driver=new InternetExplorerDriver();
			break;


		case "SAUCELABS REMOTE BROWSER":
			String sauceLabsBrowser=properties.getValue("BrowserName");
			if(sauceLabsBrowser!=null)
			{
				switch(sauceLabsBrowser)
				{
				case "CHROME":
					capabilities = DesiredCapabilities.chrome();
					if(properties.getValue("BrowserName")!=null){capabilities.setCapability("browserName",properties.getValue("BrowserName"));}
					if(properties.getValue("BrowserVersion")!=null){capabilities.setCapability("browserVersion", properties.getValue("BrowserVersion"));}
					if(properties.getValue("PlatformName")!=null){capabilities.setCapability("platformName", properties.getValue("PlatformName"));}
					capabilities.setCapability("name",scenarioName+"_"+scenarioLine+"_"+System.currentTimeMillis());
					break;

				case "FIREFOX":
					capabilities = DesiredCapabilities.firefox();
					if(properties.getValue("BrowserName")!=null){capabilities.setCapability("browserName",properties.getValue("BrowserName"));}
					if(properties.getValue("BrowserVersion")!=null){capabilities.setCapability("browserVersion", properties.getValue("BrowserVersion"));}
					if(properties.getValue("PlatformName")!=null){capabilities.setCapability("platformName", properties.getValue("PlatformName"));}
					capabilities.setCapability("name",scenarioName+"_"+scenarioLine+"_"+System.currentTimeMillis());
					break;

				case "INTERNET EXPLORER":
					capabilities = DesiredCapabilities.internetExplorer();
					if(properties.getValue("BrowserName")!=null){capabilities.setCapability("browserName",properties.getValue("BrowserName"));}
					if(properties.getValue("BrowserVersion")!=null){capabilities.setCapability("browserVersion", properties.getValue("BrowserVersion"));}
					if(properties.getValue("PlatformName")!=null){capabilities.setCapability("platformName", properties.getValue("PlatformName"));}
					capabilities.setCapability("name",scenarioName+"_"+scenarioLine+"_"+System.currentTimeMillis());
					break;

				case "EDGE":
					capabilities = DesiredCapabilities.edge();
					if(properties.getValue("BrowserName")!=null){capabilities.setCapability("browserName",properties.getValue("BrowserName"));}
					if(properties.getValue("BrowserVersion")!=null){capabilities.setCapability("browserVersion", properties.getValue("BrowserVersion"));}
					if(properties.getValue("PlatformName")!=null){capabilities.setCapability("platformName", properties.getValue("PlatformName"));}
					capabilities.setCapability("name",scenarioName+"_"+scenarioLine+"_"+System.currentTimeMillis());
					break;

				default:
					Log.getLogInstance().info("Please enter a valid SauceLabs browser name.");
					break;
				}

				driver= new RemoteWebDriver(new URL(URL),capabilities);
			}


		case "SAUCELABS REMOTE DEVICE":
			String sauceLabsPlatform=properties.getValue("PlatformName");
			if(sauceLabsPlatform!=null)
			{
				switch(sauceLabsPlatform)
				{
				case "Android":
					capabilities = DesiredCapabilities.android();
					capabilities.setCapability("platformName", sauceLabsPlatform);
					if(properties.getValue("AndroidAppiumVersion")!=null){capabilities.setCapability("appiumVersion",properties.getValue("AndroidAppiumVersion"));}
					if(properties.getValue("AndroidDeviceName")!=null){capabilities.setCapability("deviceName", properties.getValue("AndroidDeviceName"));}
					if(properties.getValue("AndroidDeviceOrientation")!=null){capabilities.setCapability("deviceOrientation", properties.getValue("AndroidDeviceOrientation"));}
					if(properties.getValue("AndroidBrowserName")!=null){capabilities.setCapability("browserName", properties.getValue("AndroidBrowserName"));}
					if(properties.getValue("AndroidPlatformVersion")!=null){capabilities.setCapability("platformVersion", properties.getValue("AndroidPlatformVersion"));}
					capabilities.setCapability("name",scenarioName+"_"+scenarioLine+"_"+System.currentTimeMillis());
					break;

				case "iOS":
					capabilities = DesiredCapabilities.iphone();
					capabilities.setCapability("platformName", sauceLabsPlatform);
					if(properties.getValue("IOSAppiumVersion")!=null){capabilities.setCapability("appiumVersion",properties.getValue("IOSAppiumVersion"));}
					if(properties.getValue("IOSDeviceName")!=null){capabilities.setCapability("deviceName", properties.getValue("IOSDeviceName"));}
					if(properties.getValue("IOSDeviceOrientation")!=null){capabilities.setCapability("deviceOrientation", properties.getValue("IOSDeviceOrientation"));}
					if(properties.getValue("IOSBrowserName")!=null){capabilities.setCapability("browserName", properties.getValue("IOSBrowserName"));}
					if(properties.getValue("IOSPlatformVersion")!=null){capabilities.setCapability("platformVersion", properties.getValue("IOSPlatformVersion"));}
					capabilities.setCapability("name",scenarioName+"_"+scenarioLine+"_"+System.currentTimeMillis());
					break;

				default:
					Log.getLogInstance().info("Please enter a valid SauceLabs device platform.");
					break;
				}

				driver= new RemoteWebDriver(new URL(URL),capabilities);
			}

		default:
			Log.getLogInstance().info("Please enter a valid browser name.");
			break;
		}


	}

	public static WebDriverFactory getWebDriverFactoryInstance()
	{	
		try {
			if(webDriverFactory==null)
			{
				webDriverFactory= new WebDriverFactory();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.getLogInstance().info(e.getMessage());
		}

		return webDriverFactory;	
	}

	public WebDriver getWebDriverInstance()
	{	
		return driver;

	}

}