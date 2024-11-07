package com.comcat.crm.ObjectRepositaryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement CreateNewContactBtn;
	
	@FindBy(name="search_text")
	private WebElement searchBox;
	
	@FindBy(id="bas_searchfield")
	private WebElement searchDD;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	public WebElement getSearchBox() {
		return searchBox;
	}
	public WebElement getSearchDD() {
		return searchDD;
	}
	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
	WebDriver driver;
	public ContactPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public WebElement getCreateNewContactBtn() {
		return CreateNewContactBtn;
	}

}
