
	package com.comcast.crm.pomclassImplementation;

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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcat.crm.ObjectRepositaryUtility.CreatingNewOrganization;
import com.comcat.crm.ObjectRepositaryUtility.HomePage;
import com.comcat.crm.ObjectRepositaryUtility.LoginPage;
import com.comcat.crm.ObjectRepositaryUtility.OrganizationInfoPage;
import com.comcat.crm.ObjectRepositaryUtility.OrganizationsPage;

	public class CreateOrgWithPhoneNumberTest {

		public static void main(String[] args) throws IOException, InterruptedException {
			
			
			ExcelUtility eLib=new ExcelUtility();
	        JavaUtility jutil=new JavaUtility();
			FileUtility fLib=new FileUtility();
			WebDriverUtility wlib=new WebDriverUtility();
			
			String Browser=fLib.getDataFrompropertiesFile("browser");
			String Url=fLib.getDataFrompropertiesFile("url");
			String Username=fLib.getDataFrompropertiesFile("username");
			String Pass=fLib.getDataFrompropertiesFile("password");
			
			
			  //convert the numerical data into string in excel itself wb.close();
			 
			
			String orgName=eLib.getDataFromExcel("org", 1, 2)+jutil.getRandomNum();
			String phoneNumber=eLib.getDataFromExcel("org", 7, 3);
			
			
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
		    
		    LoginPage lp=new LoginPage(driver);
		    lp.loginToApp(Url,Username, Pass);
		    
		    Thread.sleep(2000);
		    
		    //navigate to organization module
		    HomePage hp=new HomePage(driver);
		    hp.getOrgLink().click();
		    
		    //click on create organization button
		    OrganizationsPage orgpage=new OrganizationsPage(driver);
		    orgpage.getCreateNeworgBtn().click();
		    
		    //enter all the details and create new organization
		    
//		    driver.findElement(By.name("accountname")).sendKeys(orgName);
//		    driver.findElement(By.id("phone")).sendKeys(phoneNumber);
//		    
//		    
//		    driver.findElement(By.name("button")).click();
		    CreatingNewOrganization cno=new CreatingNewOrganization(driver);
		    cno.createOrg1(orgName, phoneNumber);
		    
		    //verify PhnNuminfo Expected result
		    
		    OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		    //String actualPhnNo=driver.findElement(By.id("dtlview_Phone")).getText();
		    String actualPhnNo=oip.getActPhnNum().getText();
		    if(actualPhnNo.equals(phoneNumber))
		    {
		    	System.out.println(phoneNumber + " is verified==pass");
		    }
		    else
		    {
		    	System.out.println(phoneNumber + " is not verified==Fail");
		    }
		    
		    
		    oip.getDeleteBtn();
		    
		    
//		    Alert alert=driver.switchTo().alert();
//		    alert.accept();
            //wlib.switchToAlertAndAccept(driver);
		    
            //logout
            hp.logout();
		    driver.quit();
			

			}

	}


