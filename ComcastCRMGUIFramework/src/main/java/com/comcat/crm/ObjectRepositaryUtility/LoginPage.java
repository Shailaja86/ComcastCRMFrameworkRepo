package com.comcat.crm.ObjectRepositaryUtility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
/**
 * @author Shylu
 * Contains login page elements and business library like login
 */
public class LoginPage extends WebDriverUtility
//extends to webdriverutility becoz no need to write implicitlywait no need to create object for webdriverutility
{ //rule1 create a seperate java class
	//rule2 object creation
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	@FindAll({@FindBy(id="submitbutton"),@FindBy(xpath="//input[@type='submit']")})
	 private WebElement loginbtn;
	
	//object initialization
	//create a construtor and pass the driver object to create an object
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		//this means current class object 
		PageFactory.initElements(driver, this);
	}
	//object encapsulation
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}
	public WebElement getPasswordEdt() {
		return passwordEdt;
	}
	public WebElement getLoginbtn() {
		return loginbtn;
	}
	//provide action or business library  multiple element access
	/**
	 * login into app based on username , password, url arguements
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToApp(String url,String username,String password)
	{
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		waitForPageToLoad(driver);
		driver.get(url);
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginbtn.click();
	}
	

		
		
	
	
	
	
	
	

}
