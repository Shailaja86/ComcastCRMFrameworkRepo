

	
		package com.comcast.crm.contacttest;

		import java.io.FileInputStream;
		import java.io.IOException;
		import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
		import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
		import org.apache.poi.ss.usermodel.WorkbookFactory;
		import org.openqa.selenium.Alert;
		import org.openqa.selenium.By;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.chrome.ChromeDriver;
		import org.openqa.selenium.edge.EdgeDriver;
		import org.openqa.selenium.firefox.FirefoxDriver;
		import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

		public class CreateContactWithOrgTest {

			public static void main(String[] args) throws IOException, InterruptedException {
				ExcelUtility eLib=new ExcelUtility();
				FileUtility fLib=new FileUtility();
				JavaUtility jutil=new JavaUtility();
				WebDriverUtility wlib=new WebDriverUtility();
				
				String Browser=fLib.getDataFrompropertiesFile("browser");
				String Url=fLib.getDataFrompropertiesFile("url");
				String Username=fLib.getDataFrompropertiesFile("username");
				String Pass=fLib.getDataFrompropertiesFile("password");
				
				
				
				/*
				 * FileInputStream fis1=new
				 * FileInputStream("./src/test/resources/testScriptDataa.xlsx"); Workbook wb
				 * =WorkbookFactory.create(fis1); String
				 * orgName=wb.getSheet("contact").getRow(7).getCell(2).toString()+randomInt;
				 * String
				 * contactLastName=wb.getSheet("contact").getRow(7).getCell(3).toString();
				 * System.out.println(contactLastName); System.out.println(orgName); wb.close();
				 */
				
				String contactLastName=eLib.getDataFromExcel("contact", 7, 3)+jutil.getRandomNum();
				String orgName=eLib.getDataFromExcel("contact", 7, 2)+jutil.getRandomNum();
				
				
				WebDriver driver=null;
			    System.out.println(Browser);
			    if(Browser.equals("Chrome"))
			    {
			    	driver=new ChromeDriver();
			    }
			    else if(Browser.equals("Edge"))
			    {
			    	driver=new EdgeDriver();
			    }
			    else if(Browser.equals("Firefox"))
			    {
			    	driver=new FirefoxDriver();
			    }
			    else
			    {
			    	driver=new ChromeDriver();
			    }
			    //login to app
			    driver.manage().window().maximize();
			    //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			    wlib.waitForPageToLoad(driver);
			    driver.get(Url);
			    
			    driver.findElement(By.name("user_name")).sendKeys(Username);
			    
			    driver.findElement(By.name("user_password")).sendKeys(Pass);
			    
			    driver.findElement(By.id("submitButton")).click();
			    
			    Thread.sleep(2000);
			    //navigate to organization module
			    driver.findElement(By.linkText("Organizations")).click();
			    //click on create organization button
			    driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
			    
			    //enter all the details and create new organization
			    driver.findElement(By.name("accountname")).sendKeys(orgName);
			    
			    driver.findElement(By.name("button")).click();
			    //verify Header message Expected result
			    String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			    if(headerInfo.contains(orgName))
			    {
			    	System.out.println(orgName + " header is verified==pass");
			    }
			    else
			    {
			    	System.out.println(orgName + " header is not verified==Fail");
			    }
			    
			    
			    /*driver.findElement(By.name("Delete")).click();
			    
			    
			    Alert alert=driver.switchTo().alert();
			    alert.accept();*/
			    
			    //navigate to contact module
			    driver.findElement(By.linkText("Contacts")).click();
			    //click on create contact button
			    driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
			    
			    //enter all the details and create new contact
			    driver.findElement(By.name("lastname")).sendKeys(contactLastName);
			    
                driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
			    //search organization name in lookup(search)
                //orgName is dynamic data
                //switch to child window
				/*
				 * Set<String> set=driver.getWindowHandles(); Iterator<String>
				 * it=set.iterator(); while(it.hasNext()) { String windowId=it.next();
				 * driver.switchTo().window(windowId); String actUrl=driver.getCurrentUrl();
				 * if(actUrl.contains("module=Accounts")) { break; } }
				 */
                
                wlib.switchToTabOnURL(driver, "module=Accounts");
                
               Thread.sleep(2000);
			  driver.findElement(By.id("search_txt")).sendKeys(orgName);
			  
			  driver.findElement(By.name("search")).click();
			  Thread.sleep(2000);
			  //dynamic xpath: xpath also getting in runtime
			  //driver.findElement(By.xpath("//a[text()='SnapChat3']"));
			  //snapchat3 is dynamic
			  driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
			  Thread.sleep(2000);
			  
			  //switch to parent window
				/*
				 * Set<String> set1=driver.getWindowHandles(); Iterator<String>
				 * it1=set.iterator(); while(it1.hasNext()) { String windowId=it1.next();
				 * driver.switchTo().window(windowId); String actUrl=driver.getCurrentUrl();
				 * if(actUrl.contains("Contacts&action")) { break; } }
				 */
			  
			  wlib.switchToTabOnURL(driver, "Contacts&action");
			    
			    driver.findElement(By.name("button")).click();
			    //verify Header message Expected result
			    String headerInfo1=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			    System.out.println(headerInfo1);
			    if(headerInfo1.contains(contactLastName))
			    {
			    	System.out.println(contactLastName + " contact header is verified==pass");
			    }
			    else
			    {
			    	System.out.println(contactLastName + " contact header is not verified==Fail");
			    }
			    
			  //verify OgName info Expected result
			    String actualOrgName=driver.findElement(By.id("mouseArea_Organization Name")).getText();
			    System.out.println(actualOrgName);
			    // Facebook... having space
			    
				/*
				 * if(actualOrgName.equals(orgName)) { System.out.println(orgName +
				 * " info is created==pass"); } else { System.out.println(orgName +
				 * " info is not created==Fail"); }
				 */
			    //trim is used to remove the space before and after the string
			    if(actualOrgName.trim().equals(orgName)) 
			    { System.out.println(orgName +" info is created==pass"); 
			    } else { 
			    	System.out.println(orgName +" info is not created==Fail"); 
			    	}
						 
			   

			    WebElement Administrator=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				/*
				 * Actions a1=new Actions(driver); a1.moveToElement(Administrator).perform();
				 */
			   wlib.mousemoveOnElement(driver, Administrator);
			    
			   //logout 
			    WebElement SignOut=driver.findElement(By.xpath("//a[text()='Sign Out']"));
			    SignOut.click();
			    
			    driver.quit();
				

				}

		

	

}
