
	
		package com.comcast.crm.contacttest;

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

		public class CreateContactTest {

			public static void main(String[] args) throws IOException, InterruptedException {
				
				/*
				 * FileInputStream fis =new
				 * FileInputStream("./src/main/resources/commondata.properties"); Properties
				 * pr=new Properties(); pr.load(fis); String Browser=pr.getProperty("browser");
				 * String Url=pr.getProperty("url"); String Pass=pr.getProperty("password");
				 * String Username=pr.getProperty("username");
				 */
				//create object
				ExcelUtility eLib=new ExcelUtility();
				FileUtility fLib=new FileUtility();
				JavaUtility jutil=new JavaUtility();
				WebDriverUtility wlib=new WebDriverUtility();
				
				String Browser=fLib.getDataFrompropertiesFile("browser");
				String Url=fLib.getDataFrompropertiesFile("url");
				String Username=fLib.getDataFrompropertiesFile("username");
				String Pass=fLib.getDataFrompropertiesFile("password");
				
				/*
				 * Random random=new Random(); int randomInt=random.nextInt(1000);
				 */
				
				
				
				
				/*
				 * FileInputStream fis1=new
				 * FileInputStream("./src/test/resources/testScriptDataa.xlsx"); Workbook wb
				 * =WorkbookFactory.create(fis1); String
				 * lastName=wb.getSheet("contact").getRow(1).getCell(2).toString()+randomInt;
				 * wb.close();
				 */
				
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
			    
			    //enter all the details and create new contact
			    driver.findElement(By.name("lastname")).sendKeys(lastName);
			    
			    driver.findElement(By.name("button")).click();
			    //verify Header message Expected result
			    String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			    if(headerInfo.contains(lastName))
			    {
			    	System.out.println(lastName + "is created==pass");
			    }
			    else
			    {
			    	System.out.println(lastName + "is not created==Fail");
			    }
			    //verify LastName info Expected result
			    String actualLastName=driver.findElement(By.id("dtlview_Last Name")).getText();
			    if(actualLastName.equals(lastName))
			    {
			    	System.out.println(lastName + " information is verified==pass");
			    }
			    else
			    {
			    	System.out.println(lastName + " information is not verified==Fail");
			    }
			    
			    driver.findElement(By.name("Delete")).click();
			    
			    
				/*
				 * Alert alert=driver.switchTo().alert(); alert.accept();
				 */
			    
			    wlib.switchToAlertAndAccept(driver);

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
