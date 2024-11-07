package com.comcat.crm.ObjectRepositaryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerMsg;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement actOrgName;
	
	
	@FindBy(id="dtlview_Industry")
	private WebElement actIndustryName;
	
	@FindBy(id="dtlview_Type")
	private WebElement actType;
	
	@FindBy(id="dtlview_Phone")
	private WebElement actPhnNum;
	
	@FindBy(name="Delete")
	private WebElement DeleteBtn;
	
	
	
	
	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	public WebElement getActOrgName() {
		return actOrgName;
	}
	
	public WebElement getActIndustryName() {
		return actIndustryName;
	}
	public WebElement getActType() {
		return actType;
	}
	
	
	public WebElement getActPhnNum() {
		return actPhnNum;
	}
	public WebElement getDeleteBtn() {
		return DeleteBtn;
	}

}
