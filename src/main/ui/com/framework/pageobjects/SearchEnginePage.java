package com.framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;

import com.framework.core.WebDriverFactory;

public class SearchEnginePage {
	
	
	@FindAll({@FindBy(how=How.CSS,using="input[type='text'][title='Search']"),@FindBy(how=How.CSS,using="input#sb_form_q"),@FindBy(how=How.CSS,using="input#email")})
	public static WebElement searchField;
	
	@FindAll({@FindBy(how=How.CSS,using="#tsf > div:nth-child(2) > div.A8SBwf.emcav > div.UUbT9"),@FindBy(how=How.CSS,using="div#sw_as>div:nth-child(2)")})
	public static WebElement searchFieldDropdown;
	//
	@FindAll({@FindBy(how=How.XPATH,using="//*[@id='tsf']/div[2]/div[1]/div[3]/center/input[1]"),@FindBy(how=How.CSS,using="input#sb_form_go")})
	public static WebElement searchButton;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*//input#sb_form_q      
	@FindAll({@FindBy(how=How.CSS,using="input[type='text']"),@FindBy(how=How.CSS,using="form>input:nth-child(1)[maxlength='1000']"),@FindBy(how=How.CSS,using="input#email")})
	public static WebElement searchField;
	//
	@FindAll({@FindBy(how=How.CSS,using="#tsf > div:nth-child(2) > div.A8SBwf.emcav > div.UUbT9"),@FindBy(how=How.CSS,using="div#sw_as>div:nth-child(2)")})
	public static WebElement searchFieldDropdown;
	//
	@FindAll({@FindBy(how=How.XPATH,using="//*[@id='tsf']/div[2]/div[1]/div[3]/center/input[1]"),@FindBy(how=How.CSS,using="input#sb_form_go")})
	public static WebElement searchButton;*/
	





}
