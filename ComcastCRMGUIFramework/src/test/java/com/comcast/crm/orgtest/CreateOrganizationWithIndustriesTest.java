package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganizationWithIndustriesTest {

	public static void main(String[] args) throws IOException, InterruptedException {
        ExcelUtility eLib=new ExcelUtility();
        JavaUtility jutil=new JavaUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		String Browser=fLib.getDataFrompropertiesFile("browser");
		String Url=fLib.getDataFrompropertiesFile("url");
		String Username=fLib.getDataFrompropertiesFile("username");
		String Pass=fLib.getDataFrompropertiesFile("password");
		
		
//		Random random=new Random();
//		int randomInt=random.nextInt(1000);
		
		
		
		/*
		 * FileInputStream fis1=new
		 * FileInputStream("./src/test/resources/testScriptDataa.xlsx"); Workbook wb
		 * =WorkbookFactory.create(fis1); String
		 * orgName=wb.getSheet("org").getRow(4).getCell(2).toString()+randomInt; String
		 * industry=wb.getSheet("org").getRow(4).getCell(3).toString(); String
		 * type=wb.getSheet("org").getRow(4).getCell(4).toString(); wb.close();
		 */
		
		String orgName=eLib.getDataFromExcel("org", 4, 2)+jutil.getRandomNum();
		String Industry=eLib.getDataFromExcel("org", 4, 3);
		String Type=eLib.getDataFromExcel("org", 4, 4);
		
		
		
		
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
	    WebElement industryy=driver.findElement(By.name("industry"));
//	    Select s1=new Select(industryy);
//	    s1.selectByValue(Industry);
	    
	    wlib.selectByText(industryy, Industry);
	    WebElement typee=driver.findElement(By.name("accounttype"));
//	    Select s2=new Select(typee);
//	    s2.selectByValue(Type);
	    wlib.selectByText(typee, Type);
	    
	    
	    driver.findElement(By.name("button")).click();
	    
	    
	    //verify industry and type info Expected result
	    
	    
	    String actualIndustryName=driver.findElement(By.id("dtlview_Industry")).getText();
	    if(actualIndustryName.equals(Industry))
	    {
	    	System.out.println( Industry + " information is verified==pass");
	    }
	    else
	    {
	    	System.out.println(Industry + " information is not verified==Fail");
	    }
	    String actualType=driver.findElement(By.id("dtlview_Type")).getText();
	    if(actualType.equals(Type))
	    {
	    	System.out.println(Type+ " information is verified==pass");
	    }
	    else
	    {
	    	System.out.println(Type + " information is not verified==Fail");
	    }
	    driver.findElement(By.name("Delete")).click();
	    
	    
//	    Alert alert=driver.switchTo().alert();
//	    alert.accept();
	    wlib.switchToAlertAndAccept(driver);

	    WebElement Administrator=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//	    Actions a1=new Actions(driver);
//	    a1.moveToElement(Administrator).perform();
	    
	    wlib.mousemoveOnElement(driver, Administrator);
	   
	   //logout 
	    WebElement SignOut=driver.findElement(By.xpath("//a[text()='Sign Out']"));
	    SignOut.click();
	    
	    driver.quit();
		

		}

}
