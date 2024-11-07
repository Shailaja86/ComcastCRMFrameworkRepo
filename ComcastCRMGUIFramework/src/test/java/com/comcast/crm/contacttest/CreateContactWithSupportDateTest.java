package com.comcast.crm.contacttest;


import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

	public class CreateContactWithSupportDateTest {

		public static void main(String[] args) throws IOException, InterruptedException {
			ExcelUtility eLib=new ExcelUtility();
			FileUtility fLib=new FileUtility();
			JavaUtility jutil=new JavaUtility();
			WebDriverUtility wlib=new WebDriverUtility();
			
			String Browser=fLib.getDataFrompropertiesFile("browser");
			String Url=fLib.getDataFrompropertiesFile("url");
			String Username=fLib.getDataFrompropertiesFile("username");
			String Pass=fLib.getDataFrompropertiesFile("password");
			
			
			String lastName=eLib.getDataFromExcel("contact", 1, 2)+jutil.getRandomNum();
			
			
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
		    //navigate to contact module
		    driver.findElement(By.linkText("Contacts")).click();
		    //click on create contact button
		    driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		    
		    
		    
		    
			/*
			 * Date dateobj=new Date(); //returns current date and time
			 * //System.out.println(dateobj); //get date SimpleDateFormat sim=new
			 * SimpleDateFormat("yyyy-MM-dd");
			 * 
			 * String startDate=sim.format(dateobj); //System.out.println(startDate);
			 * 
			 * Calendar cal=sim.getCalendar();
			 * 
			 * //after date 30 days date cal.add(Calendar.DAY_OF_MONTH, 30); String
			 * endDate=sim.format(cal.getTime());
			 * 
			 * //System.out.println(endDate);
			 */		  
		    String startDate=jutil.getSystemDateYYYYDDMM();
		    String endDate=jutil.getRequiredDateYYYYMMDD(30);
		    
		    //enter all the details and create new contact
		    driver.findElement(By.name("lastname")).sendKeys(lastName);
		    //support start date
		    driver.findElement(By.id("jscal_field_support_start_date")).clear();
		    driver.findElement(By.id("jscal_field_support_start_date")).sendKeys(startDate);
		    //support end date
		    driver.findElement(By.id("jscal_field_support_end_date")).clear();
		    driver.findElement(By.id("jscal_field_support_end_date")).sendKeys(endDate);
		    
		    Thread.sleep(2000);
		    driver.findElement(By.name("button")).click();
		    
		    
		    String actStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		    if(actStartDate.equals(startDate))
		    {
		    	System.out.println(actStartDate +" information is verified");

		    }
		    else
		    {
		    	System.out.println(actStartDate +" information is not verified");
		    }
		    
		    String actEndDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		    if(actEndDate.equals(endDate))
		    {
		    	System.out.println(actEndDate +" information is verified");

		    }
		    else
		    {
		    	System.out.println(actEndDate +" information is not verified");
		    }

            driver.findElement(By.name("Delete")).click();
			/*
			 * Alert alert=driver.switchTo().alert(); alert.accept();
			 */
            wlib.switchToAlertAndAccept(driver);
		    
		    WebElement Administrator=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		    Actions a1=new Actions(driver);
//		    a1.moveToElement(Administrator).perform();
		    
		    wlib.mousemoveOnElement(driver, Administrator);
		   
		   //logout 
		    WebElement SignOut=driver.findElement(By.xpath("//a[text()='Sign Out']"));
		    SignOut.click();
		    
		    driver.quit();	
}
}
