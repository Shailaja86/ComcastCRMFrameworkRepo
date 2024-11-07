
	
		package com.comcast.crm.contactpomimplementation;

		import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
			    
			    //driver.get(Url);
			    
			    LoginPage lp=new LoginPage(driver);
			    lp.loginToApp(Url,Username, Pass);
			    
			    Thread.sleep(2000);
			    //navigate to contact module
			    HomePage hp=new HomePage(driver);
			    hp.getContactLink().click();
			    
			    //driver.findElement(By.linkText("Contacts")).click();
			    //click on create contact button
			    ContactPage cp=new ContactPage(driver);
			    cp.getCreateNewContactBtn().click();
			    
			    
			    //enter all the details and create new contact
			    //driver.findElement(By.name("lastname")).sendKeys(lastName);
			    CreatingNewContactPage cnp=new CreatingNewContactPage(driver);
			    cnp.getLastnameEdt().sendKeys(lastName);
			    
			    //driver.findElement(By.name("button")).click();
			    cnp.getSaveBtn().click();
			    //verify Header message Expected result
			    //String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			    ContactsInfoPage cip=new ContactsInfoPage(driver);
			    //cip.getHeaderInfo();
			    String headerInfo=cip.getHeaderInfo().getText();
			    if(headerInfo.contains(lastName))
			    {
			    	System.out.println(lastName + "is created==pass");
			    }
			    else
			    {
			    	System.out.println(lastName + "is not created==Fail");
			    }
			    //verify LastName info Expected result
			    //String actualLastName=driver.findElement(By.id("dtlview_Last Name")).getText();
			    String actLastName=cip.getActualLastName().getText();
			    if(actLastName.equals(lastName))
			    {
			    	System.out.println(lastName + " information is verified==pass");
			    }
			    else
			    {
			    	System.out.println(lastName + " information is not verified==Fail");
			    }
			    
			    cip.getDelBtn().click();
			    //driver.findElement(By.name("Delete")).click();
			    
			    
				/*
				 * Alert alert=driver.switchTo().alert(); alert.accept();
				 */
			    
			    wlib.switchToAlertAndAccept(driver);

			    hp.logout();
			    
			    driver.quit();
				

				

	}

}
