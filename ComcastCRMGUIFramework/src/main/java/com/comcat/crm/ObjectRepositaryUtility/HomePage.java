package com.comcat.crm.ObjectRepositaryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	@FindBy(linkText="Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText="Contacts")
	private WebElement contactLink;
	
	@FindBy(linkText="More")
	private WebElement moreLink;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement AdminWidget; 
	
	@FindBy(linkText="Sign Out")
	private WebElement signoutLink;
	
	

	

	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getOrgLink() {
		return orgLink;
	}
	public WebElement getContactLink() {
		return contactLink;
	}
	
	public WebElement getCampaignLink() {
		return campaignLink;
	}
	
	public WebElement getMoreLink() {
		return moreLink;
	}
	public WebElement getAdminWidget() {
		return AdminWidget;
	}
	public WebElement getSignout() {
		return signoutLink;
	}
	
	//business library
	public void navigateToCampaignPage()
	{
		Actions act=new Actions(driver);
		act.moveToElement(moreLink).perform();
		campaignLink.click();
	}
	public void logout()
	{
		Actions a1=new Actions(driver);
		a1.moveToElement(AdminWidget).perform();
		signoutLink.click();
	}
	
	
	
	
	
	

}
