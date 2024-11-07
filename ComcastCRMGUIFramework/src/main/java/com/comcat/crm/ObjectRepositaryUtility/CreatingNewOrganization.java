package com.comcat.crm.ObjectRepositaryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganization {
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(id="phone")
	private WebElement phoneNumEdt;
	
	@FindBy(name="button")
    private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDD;
	
	@FindBy(name="accounttype")
	private WebElement typeDD;
	
	
	
	WebDriver driver;
	public CreatingNewOrganization(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}
	
	public WebElement getIndustryDD() {
		return industryDD;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public WebElement getTypeDD() {
		return typeDD;
	}
	
	
	public WebElement getPhoneNumEdt() {
		return phoneNumEdt;
	}

	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	public void createOrg(String orgName,String Industry)
	{
		orgNameEdt.sendKeys(orgName);
		Select s1=new Select(industryDD);
		s1.selectByVisibleText(Industry);
		saveBtn.click();
	}
	public void createOrg(String orgName,String Industry,String Type)
	{
		orgNameEdt.sendKeys(orgName);
		Select s1=new Select(industryDD);
		s1.selectByVisibleText(Industry);
		Select s2=new Select(typeDD);
		s2.selectByVisibleText(Type);
		
		saveBtn.click();
		
	}
	public void createOrg1(String orgName,String phoneNumber)
	{
		orgNameEdt.sendKeys(orgName);
		phoneNumEdt.sendKeys(phoneNumber);
		saveBtn.click();
	}
	
}
