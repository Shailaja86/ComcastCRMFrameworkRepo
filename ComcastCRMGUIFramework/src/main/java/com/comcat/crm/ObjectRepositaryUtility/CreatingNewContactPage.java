package com.comcat.crm.ObjectRepositaryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	@FindBy(name="lastname")
	private WebElement lastnameEdt;
	
	@FindBy(id="mobile")
	private WebElement MobileEdt;
	
	@FindBy(name="button")
	private WebElement saveBtn;
	@FindBy(id="jscal_field_support_start_date")
	private WebElement SupportStartDate;
	@FindBy(id="jscal_field_support_end_date")
	private WebElement SupportEndDate;
	
	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public WebElement getLastnameEdt() {
		return lastnameEdt;
	}
	public WebElement getMobileEdt() {
		return MobileEdt;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebElement getSupportStartDate() {
		return SupportStartDate;
	}
	public WebElement getSupportEndDate() {
		return SupportEndDate;
	}
	

}
