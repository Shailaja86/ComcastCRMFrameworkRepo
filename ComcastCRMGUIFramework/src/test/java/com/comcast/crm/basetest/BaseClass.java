package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcat.crm.ObjectRepositaryUtility.HomePage;
import com.comcat.crm.ObjectRepositaryUtility.LoginPage;

public class BaseClass {
	public DataBaseUtility dblib=new DataBaseUtility();
	 public ExcelUtility eLib=new ExcelUtility();
	public  JavaUtility jutil=new JavaUtility();
	public FileUtility flib=new FileUtility();
	 public WebDriverUtility wlib=new WebDriverUtility();
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	@BeforeSuite(groups={"smokeTest","RegressionTest"})
	public void configBS()
	{
		System.out.println("====connect to db ,report config===");
		dblib.getConnectionn();
	}

	//@Parameters("BROWSER") 
	@BeforeClass(groups={"smokeTest","RegressionTest"})
	//public void configBC(String browser) throws IOException 
	public void configBC() throws IOException 

	{
		System.out.println("==launch the browser==");
		//String BROWSER=browser;
		String BROWSER=flib.getDataFrompropertiesFile("browser");
		
		
		//dont declare webdriver here declare it as global becoz driver object required for login logout
		
		if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		sdriver=driver;
	}
	
	@BeforeMethod(groups={"smokeTest","RegressionTest"})
	public void configBM() throws IOException {
		System.out.println("===login to application===");
		String Url=flib.getDataFrompropertiesFile("url");
		String Username=flib.getDataFrompropertiesFile("username");
		String Pass=flib.getDataFrompropertiesFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(Url, Username, Pass);
	}
	
	@AfterMethod(groups={"smokeTest","RegressionTest"})
	public void configAM()
	{
		System.out.println("===logout====");
		
		HomePage hp=new HomePage(driver);
		hp.logout();
	}
	
	
	@AfterClass(groups={"smokeTest","RegressionTest"})
	public void configAC()
	{
		System.out.println("===close the browser===");
		driver.quit();
		
	}
	
	@AfterSuite(groups={"smokeTest","RegressionTest"})
	public void configAS() throws SQLException
	{
	System.out.println("===close db,report backup");
	dblib.closeDbConnection();
	}

}
