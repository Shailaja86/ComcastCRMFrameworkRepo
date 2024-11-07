package com.comcast.crm.pomclassImplementation;

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
import com.comcat.crm.ObjectRepositaryUtility.CreatingNewOrganization;
import com.comcat.crm.ObjectRepositaryUtility.HomePage;
import com.comcat.crm.ObjectRepositaryUtility.LoginPage;
import com.comcat.crm.ObjectRepositaryUtility.OrganizationInfoPage;
import com.comcat.crm.ObjectRepositaryUtility.OrganizationsPage;

public class DeleteOrg {

	public static void main(String[] args) throws IOException, InterruptedException {
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jutil=new JavaUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		String Browser=fLib.getDataFrompropertiesFile("browser");
		String Url=fLib.getDataFrompropertiesFile("url");
		String Username=fLib.getDataFrompropertiesFile("username");
		String Pass=fLib.getDataFrompropertiesFile("password");
		
		
		String orgName=eLib.getDataFromExcel("org", 10, 2)+jutil.getRandomNum();
		
		
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
	    //driver.manage().window().maximize();
	    //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	   wlib.waitForPageToLoad(driver);
	    driver.get(Url);
	    
//	    driver.findElement(By.name("user_name")).sendKeys(Username);    
//	    driver.findElement(By.name("user_password")).sendKeys(Pass);  
//	    driver.findElement(By.id("submitButton")).click();
	    //write object intialization in login class becoz simplify object intialization
	    //provide constructor in loginpage.java
	    //LoginPage lp=PageFactory.initElements(driver, LoginPage.class);
	    //one of way of accessing the elements... access single element perform an action
//	    lp.getUsernameEdt().sendKeys("admin");
//	    lp.getPasswordEdt().sendKeys("admin");
//	    lp.getLoginbtn().click();
	    
	    //instead of writing above three lines
	    
	    LoginPage lp=new LoginPage(driver);
	    
	    lp.loginToApp(Url,Username, Pass);
	    
	    
	    Thread.sleep(2000);
	    //navigate to organization module
	    //driver.findElement(By.linkText("Organizations")).click();
	    HomePage hp=new HomePage(driver);
	    hp.getOrgLink().click();
	   
	    //click on create organization button
	    OrganizationsPage orgpage=new OrganizationsPage(driver);
	    orgpage.getCreateNeworgBtn().click();
	    
	   // driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	    
	    //enter all the details and create new organization
	    CreatingNewOrganization cno=new CreatingNewOrganization(driver);
	    //cno.getOrgNameEdt().sendKeys(orgName);
	    //cno.getSaveBtn().click();
	    cno.createOrg(orgName);
	    
	    //driver.findElement(By.name("accountname")).sendKeys(orgName);
	    //driver.findElement(By.name("button")).click();
	    
	    //verify Header message Expected result
	    OrganizationInfoPage oip=new OrganizationInfoPage(driver);
	    String headerMsg=oip.getHeaderMsg().getText();
	    if(headerMsg.contains(orgName))
	    {
	    	System.out.println(orgName+" name is verified===pass");
	    }
	    else
	    {
	    	System.out.println(orgName+" name is not verfied===fail");
	    }
	    
	    
//	    String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//	    if(headerInfo.contains(orgName))
//	    {
//	    	System.out.println(orgName + "is created==pass");
//	    }
//	    else
//	    {
//	    	System.out.println(orgName + "is not created==Fail");
//	    }
	    //verify OgName info Expected result
	    String actOrgName=oip.getHeaderMsg().getText();
	    if(actOrgName.contains(orgName))
	    {
	    	System.out.println(orgName+" name is verified===pass");
	    }
	    else
	    {
	    	System.out.println(orgName+" name is not verfied===fail");
	    }
	    
	    
	    
//	    String actualOrgName=driver.findElement(By.id("dtlview_Organization Name")).getText();
//	    if(actualOrgName.equals(orgName))
//	    {
//	    	System.out.println(orgName + " is created==pass");
//	    }
//	    else
//	    {
//	    	System.out.println(orgName + " is not created==Fail");
//	    }
	    
	    //go back organization page
	    hp.getOrgLink().click();
	    //search for organization
	    orgpage.getSearchBox().sendKeys(orgName);
	    wlib.selectByText(orgpage.getSearchDD(),"Organization Name" );
	    orgpage.getSearchBtn().click();
	    //in dynamic web table  select and delete org
	    //Using driver.findElement() we can identify dynamic elements in runtime
	   // Using @FindBy() annotation we cant store dynamic xpath becoz lazy intializations it will load all the elements at time of object creation                                                                                                                           

	    driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
	    
	    wlib.switchToAlertAndAccept(driver);
	    	    
	    
	    
	    hp.logout();
	    
	    
	    
	    driver.quit();
		

		}

	
}

