package com.comcast.crm.contactpomimplementation;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcat.crm.ObjectRepositaryUtility.ContactPage;
import com.comcat.crm.ObjectRepositaryUtility.ContactsInfoPage;
import com.comcat.crm.ObjectRepositaryUtility.CreatingNewContactPage;
import com.comcat.crm.ObjectRepositaryUtility.HomePage;
import com.comcat.crm.ObjectRepositaryUtility.LoginPage;

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
		    /*driver.get(Url);
		    
			*
			 * driver.findElement(By.name("user_name")).sendKeys(Username);
			 * 
			 * driver.findElement(By.name("user_password")).sendKeys(Pass);
			 * 
			 * driver.findElement(By.id("submitButton")).click();
			 */
		    LoginPage lp=new LoginPage(driver);
		    lp.loginToApp(Url, Username, Pass);
		    

		    //navigate to contact module
		    //driver.findElement(By.linkText("Contacts")).click();
		    HomePage hp=new HomePage(driver);
		    hp.getContactLink().click();
		    //click on create contact button
		    //driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		    ContactPage cp=new ContactPage(driver);
		    cp.getCreateNewContactBtn().click();
		    
		    	  
		    String startDate=jutil.getSystemDateYYYYDDMM();
		    String endDate=jutil.getRequiredDateYYYYMMDD(30);
		    
		    //enter all the details and create new contact
		    // driver.findElement(By.name("lastname")).sendKeys(lastName);
		    CreatingNewContactPage cnp=new CreatingNewContactPage(driver);
		    cnp.getLastnameEdt().sendKeys(lastName);
		    //support start date
		    //driver.findElement(By.id("jscal_field_support_start_date")).clear();
		    cnp.getSupportStartDate().clear();
		    //driver.findElement(By.id("jscal_field_support_start_date")).sendKeys(startDate);
		    cnp.getSupportStartDate().sendKeys(startDate);
		    //support end date
			/*
			 * driver.findElement(By.id("jscal_field_support_end_date")).clear();
			 * driver.findElement(By.id("jscal_field_support_end_date")).sendKeys(endDate);
			 */
		    
		    cnp.getSupportEndDate().clear();
		    cnp.getSupportEndDate().sendKeys(endDate);
		    Thread.sleep(2000);
		    //driver.findElement(By.name("button")).click();
		    cnp.getSaveBtn().click();
		    
		    
		    //String actStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		    ContactsInfoPage cip=new ContactsInfoPage(driver);
		   
		    String actStartDate=cip.getActSupportStartDate().getText();
		    
		    if(actStartDate.equals(startDate))
		    {
		    	System.out.println(actStartDate +" information is verified");

		    }
		    else
		    {
		    	System.out.println(actStartDate +" information is not verified");
		    }
		    
		    //String actEndDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		    String actEndDate=cip.getActSupportEndDate().getText();
		    if(actEndDate.equals(endDate))
		    {
		    	System.out.println(actEndDate +" information is verified");

		    }
		    else
		    {
		    	System.out.println(actEndDate +" information is not verified");
		    }

            //driver.findElement(By.name("Delete")).click();
			
		    cip.getDelBtn().click();
            wlib.switchToAlertAndAccept(driver);
		    
		    hp.logout();
		    
		    driver.quit();	
}
}
