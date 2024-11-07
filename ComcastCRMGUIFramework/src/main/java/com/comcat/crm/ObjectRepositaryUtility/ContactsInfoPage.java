package com.comcat.crm.ObjectRepositaryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInfoPage {

	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement HeaderInfo;
	@FindBy(id="dtlview_Last Name")
	private WebElement actualLastName;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement actSupportStartDate;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement actSupportEndDate;
	
	
	@FindBy(name="Delete")
	private WebElement delBtn;
	WebDriver driver;
	public ContactsInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public WebElement getActSupportStartDate() {
		return actSupportStartDate;
	}
	public WebElement getActSupportEndDate() {
		return actSupportEndDate;
	}
	public WebElement getHeaderInfo() {
		return HeaderInfo;
	}
	public WebElement getActualLastName() {
		return actualLastName;
	}
	public WebElement getDelBtn() {
		return delBtn;
	}
	
}
