package com.comcat.crm.ObjectRepositaryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement CreateNeworgBtn;
	
	@FindBy(name="search_text")
	private WebElement searchBox;
	
	@FindBy(id="bas_searchfield")
	private WebElement searchDD;
	
	
	
	@FindBy(name="submit")
	private WebElement searchbtnn;

	WebDriver driver;
	public OrganizationsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreateNeworgBtn() {
		return CreateNeworgBtn;
	}
	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}

	public WebElement getSearchBtn() {
		return searchbtnn;
	}

}
