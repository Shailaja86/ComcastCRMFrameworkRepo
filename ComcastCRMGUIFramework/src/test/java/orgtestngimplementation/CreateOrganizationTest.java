package orgtestngimplementation;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listenerUtility.ListImpClass;
import com.comcat.crm.ObjectRepositaryUtility.CreatingNewOrganization;
import com.comcat.crm.ObjectRepositaryUtility.HomePage;
import com.comcat.crm.ObjectRepositaryUtility.OrganizationInfoPage;
import com.comcat.crm.ObjectRepositaryUtility.OrganizationsPage;
@Listeners(com.comcast.crm.listenerUtility.ListImpClass.class)
//execute this class alone so we use @Listeners
public class CreateOrganizationTest extends BaseClass {

	@Test(groups="smokeTest")
	public void createOrgTest() throws EncryptedDocumentException, IOException {

		/*
		 * ExcelUtility eLib=new ExcelUtility(); JavaUtility jutil=new JavaUtility();
		 * FileUtility fLib=new FileUtility(); WebDriverUtility wlib=new
		 * WebDriverUtility(); all the objects in base class
		 */
		
		//ListImpClass.test.log(Status.INFO, "read data from excel");
		//Instead of using listimpclass use utilityclassobject class this will help us in parallel execution
		
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");

		// read testscript data from excel file
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jutil.getRandomNum();

		// navigate to organization module
        //ListImpClass.test.log(Status.INFO, "navigate to org page");
		 UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organization button
		
		//ListImpClass.test.log(Status.INFO, "navigate to create org page");
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		OrganizationsPage orgpage = new OrganizationsPage(driver);
		orgpage.getCreateNeworgBtn().click();

		// enter all the details and create new organization
		//ListImpClass.test.log(Status.INFO, "create a new org");
		UtilityClassObject.getTest().log(Status.INFO, "create a new org");
		CreatingNewOrganization cno = new CreatingNewOrganization(driver);

		cno.createOrg(orgName);
		//display info
		//ListImpClass.test.log(Status.INFO, orgName+"===>create a new org");
		
		UtilityClassObject.getTest().log(Status.INFO, orgName+"===>create a new org");

		// verify Header message Expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerMsg = oip.getHeaderMsg().getText();
		boolean status=headerMsg.contains(orgName);
		System.out.println(headerMsg);
		Assert.assertTrue(status);
		/*
		 * if (headerMsg.contains(orgName)) { System.out.println(orgName +
		 * " name is verified===pass"); } else { System.out.println(orgName +
		 * " name is not verfied===fail"); }
		 */

		// verify OgName info Expected result
		String actOrgName = oip.getActOrgName().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actOrgName.trim(), orgName);
		/*
		 * if (actOrgName.contains(orgName)) { System.out.println(orgName +
		 * " name is verified===pass"); } else { System.out.println(orgName +
		 * " name is not verfied===fail"); }
		 */
		soft.assertAll();

	}

	@Test(groups="RegressionTest")
	public void createOrgWithIndustries(Method mtd) throws EncryptedDocumentException, IOException {

		System.out.println(mtd.getName()+" Test start");
		String orgName = eLib.getDataFromExcel("org", 4, 2) + jutil.getRandomNum();
		String Industry = eLib.getDataFromExcel("org", 4, 3);
		String Type = eLib.getDataFromExcel("org", 4, 4);

		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organization button

		OrganizationsPage orgpage = new OrganizationsPage(driver);
		orgpage.getCreateNeworgBtn().click();

		// enter all the details and create new organization
		CreatingNewOrganization cno = new CreatingNewOrganization(driver);

		cno.createOrg(orgName, Industry, Type);

		// verify industry and type info Expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualIndustryName = oip.getActIndustryName().getText();
		// String
		// actualIndustryName=driver.findElement(By.id("dtlview_Industry")).getText();
		/*
		 * if (actualIndustryName.equals(Industry)) { System.out.println(Industry +
		 * " information is verified==pass"); } else { System.out.println(Industry +
		 * " information is not verified==Fail"); }
		 */
		SoftAssert soft= new SoftAssert();
		soft.assertEquals(actualIndustryName, Industry);
		String actualType = oip.getActType().getText();
		// String actualType=driver.findElement(By.id("dtlview_Type")).getText();

		/*
		 * System.out.println(actualType); if (actualType.equals(Type)) {
		 * System.out.println(Type + " information is verified==pass"); } else {
		 * System.out.println(Type + " information is not verified==Fail"); }
		 */
		soft.assertEquals(actualType, Type);

		oip.getDeleteBtn().click();
		wlib.switchToAlertAndAccept(driver);
		System.out.println(mtd.getName()+" Test End");
     
	}

	@Test(groups="RegressionTest")
	public void createOrgWithPhoneNumber(Method mtd) throws EncryptedDocumentException, IOException {
		System.out.println(mtd.getName()+" Test start");
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jutil.getRandomNum();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);
		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organization button
		OrganizationsPage orgpage = new OrganizationsPage(driver);
		orgpage.getCreateNeworgBtn().click();

		// enter all the details and create new organization
		CreatingNewOrganization cno = new CreatingNewOrganization(driver);
		cno.createOrg1(orgName, phoneNumber);

		// verify PhnNuminfo Expected result

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		// String actualPhnNo=driver.findElement(By.id("dtlview_Phone")).getText();
		String actualPhnNo = oip.getActPhnNum().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actualPhnNo, phoneNumber);
		/*
		 * if (actualPhnNo.equals(phoneNumber)) { System.out.println(phoneNumber +
		 * " is verified==pass"); } else { System.out.println(phoneNumber +
		 * " is not verified==Fail"); }
		 */
		
		soft.assertAll();
		System.out.println(mtd.getName()+" Test End");

	}
	
	@Test(groups="RegressionTest")
	public void deleteOrg(Method mtd) throws EncryptedDocumentException, IOException {
		System.out.println(mtd.getName()+" Test Start");

		String orgName=eLib.getDataFromExcel("org", 10, 2)+jutil.getRandomNum();
		//navigate to organization module
	    
	    HomePage hp=new HomePage(driver);
	    hp.getOrgLink().click();
	   
	    //click on create organization button
	    OrganizationsPage orgpage=new OrganizationsPage(driver);
	    orgpage.getCreateNeworgBtn().click();
	    
	  //enter all the details and create new organization
	    CreatingNewOrganization cno=new CreatingNewOrganization(driver);
	    
	    cno.createOrg(orgName);
	    
	  //verify Header message Expected result
	    OrganizationInfoPage oip=new OrganizationInfoPage(driver);
	    String headerMsg=oip.getHeaderMsg().getText();
	    System.out.println(headerMsg);
	    System.out.println(orgName);
	    boolean status=headerMsg.contains(orgName);
	    Assert.assertEquals(status, true);
		/*
		 * if(headerMsg.contains(orgName)) {
		 * System.out.println(orgName+" name is verified===pass"); } else {
		 * System.out.println(orgName+" name is not verfied===fail"); }
		 */
	    
	    String actOrgName=oip.getActOrgName().getText();
	    SoftAssert soft=new SoftAssert();
	    System.out.println(actOrgName);
	    
	    soft.assertEquals(actOrgName.trim(), orgName);
	    
		/*
		 * if(actOrgName.contains(orgName)) {
		 * System.out.println(orgName+" name is verified===pass"); } else {
		 * System.out.println(orgName+" name is not verfied===fail"); }
		 */
	    
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
	    
	    soft.assertAll();
	    //System.out.println(mtd.getName()+" Test End");

	    	    
	}

}
