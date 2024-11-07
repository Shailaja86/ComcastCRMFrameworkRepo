
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
import com.comcat.crm.ObjectRepositaryUtility.CreatingNewOrganization;
import com.comcat.crm.ObjectRepositaryUtility.HomePage;
import com.comcat.crm.ObjectRepositaryUtility.LoginPage;
import com.comcat.crm.ObjectRepositaryUtility.OrganizationInfoPage;
import com.comcat.crm.ObjectRepositaryUtility.OrganizationsPage;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		ExcelUtility eLib = new ExcelUtility();
		FileUtility fLib = new FileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();

		String Browser = fLib.getDataFrompropertiesFile("browser");
		String Url = fLib.getDataFrompropertiesFile("url");
		String Username = fLib.getDataFrompropertiesFile("username");
		String Pass = fLib.getDataFrompropertiesFile("password");

		/*
		 * FileInputStream fis1=new
		 * FileInputStream("./src/test/resources/testScriptDataa.xlsx"); Workbook wb
		 * =WorkbookFactory.create(fis1); String
		 * orgName=wb.getSheet("contact").getRow(7).getCell(2).toString()+randomInt;
		 * String
		 * contactLastName=wb.getSheet("contact").getRow(7).getCell(3).toString();
		 * System.out.println(contactLastName); System.out.println(orgName); wb.close();
		 */

		String contactLastName = eLib.getDataFromExcel("contact", 7, 3) + jutil.getRandomNum();
		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jutil.getRandomNum();

		WebDriver driver = null;
		System.out.println(Browser);
		if (Browser.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("Edge")) {
			driver = new EdgeDriver();
		} else if (Browser.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		// login to app
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wlib.waitForPageToLoad(driver);

		/*
		 * driver.get(Url);
		 * 
		 * driver.findElement(By.name("user_name")).sendKeys(Username);
		 * 
		 * driver.findElement(By.name("user_password")).sendKeys(Pass);
		 * 
		 * driver.findElement(By.id("submitButton")).click();
		 * 
		 * Thread.sleep(2000);
		 */

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(Url, Username, Pass);
		// navigate to organization module
		// driver.findElement(By.linkText("Organizations")).click();
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organization button
		// driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNeworgBtn().click();
		// enter all the details and create new organization
		// driver.findElement(By.name("accountname")).sendKeys(orgName);
		CreatingNewOrganization cno = new CreatingNewOrganization(driver);
		cno.getOrgNameEdt().sendKeys(orgName);

		// driver.findElement(By.name("button")).click();
		cno.getSaveBtn().click();
		// verify Header message Expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);

		String headerInfo = oip.getHeaderMsg().getText();
		// String
		// headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " header is verified==pass");
		} else {
			System.out.println(orgName + " header is not verified==Fail");
		}

		// navigate to contact module
		// driver.findElement(By.linkText("Contacts")).click();
		hp.getContactLink().click();
		// click on create contact button
		// driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// enter all the details and create new contact
		// driver.findElement(By.name("lastname")).sendKeys(contactLastName);

		CreatingNewContactPage cnp = new CreatingNewContactPage(driver);
		cnp.getLastnameEdt().sendKeys(contactLastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// search organization name in lookup(search)
		// orgName is dynamic data
		// switch to child window
		/*
		 * Set<String> set=driver.getWindowHandles(); Iterator<String>
		 * it=set.iterator(); while(it.hasNext()) { String windowId=it.next();
		 * driver.switchTo().window(windowId); String actUrl=driver.getCurrentUrl();
		 * if(actUrl.contains("module=Accounts")) { break; } }
		 */

		wlib.switchToTabOnURL(driver, "module=Accounts");

		Thread.sleep(2000);
		// driver.findElement(By.id("search_txt")).sendKeys(orgName);
		op.getSearchBox().sendKeys(orgName);
		// driver.findElement(By.name("search")).click();
		op.getSearchBtn().click();
		//Thread.sleep(2000);
		// dynamic xpath: xpath also getting in runtime
		// driver.findElement(By.xpath("//a[text()='SnapChat3']"));
		// snapchat3 is dynamic
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		Thread.sleep(2000);

		// switch to parent window
		/*
		 * Set<String> set1=driver.getWindowHandles(); Iterator<String>
		 * it1=set.iterator(); while(it1.hasNext()) { String windowId=it1.next();
		 * driver.switchTo().window(windowId); String actUrl=driver.getCurrentUrl();
		 * if(actUrl.contains("Contacts&action")) { break; } }
		 */

		wlib.switchToTabOnURL(driver, "Contacts&action");

		// driver.findElement(By.name("button")).click();
		cnp.getSaveBtn().click();
		// verify Header message Expected result

		ContactsInfoPage cip = new ContactsInfoPage(driver);

		String headerInfo1 = cip.getHeaderInfo().getText();
		// String
		// headerInfo1=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		System.out.println(headerInfo1);
		if (headerInfo1.contains(contactLastName)) {
			System.out.println(contactLastName + " contact header is verified==pass");
		} else {
			System.out.println(contactLastName + " contact header is not verified==Fail");
		}

		// verify OgName info Expected result

		// String actualOrgName=driver.findElement(By.id("mouseArea_Organization
		// Name")).getText();
		String actualOrgName = oip.getActOrgName().getText();
		System.out.println(actualOrgName);
		// Facebook... having space

		// trim is used to remove the space before and after the string
		if (actualOrgName.trim().equals(orgName)) {
			System.out.println(orgName + " info is created==pass");
		} else {
			System.out.println(orgName + " info is not created==Fail");
		}

		hp.logout();

		driver.quit();

	}

}
