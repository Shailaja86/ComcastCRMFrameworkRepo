package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public void waitForElementPresent(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void switchToTabOnURL(WebDriver driver,String partialUrl)
	{
		Set<String> set=driver.getWindowHandles();
        Iterator<String> it=set.iterator();
        while(it.hasNext()) {
        	String windowId=it.next();
        	driver.switchTo().window(windowId);
        	String actUrl=driver.getCurrentUrl();
        	if(actUrl.contains(partialUrl))
        	{
        		break;
        	}
        }
	}
	
	public void switchToTabOnTitle(WebDriver driver,String partialTitle)
	{
		Set<String> set=driver.getWindowHandles();
        Iterator<String> it=set.iterator();
        while(it.hasNext()) {
        	String windowId=it.next();
        	driver.switchTo().window(windowId);
        	String actUrl=driver.getCurrentUrl();
        	if(actUrl.contains(partialTitle))
        	{
        		break;
        	}
        }
	}
	//switch to frame based on index ,name n id attribute  ,webelement
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String nameID)
	{
		driver.switchTo().frame(nameID);
	}
	
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	//switch to alert and accept or dismiss
	
	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	//select class
	public void selectByText(WebElement element,String text)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	
	public void select(WebElement element,int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	public void selectByValuee(WebElement element,String Value)
	{
		Select sel=new Select(element);
		sel.selectByValue(Value);
	}
	
	
	//action class
	public void mousemoveOnElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	public void doubleClick(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick().perform();
	}

	

}
