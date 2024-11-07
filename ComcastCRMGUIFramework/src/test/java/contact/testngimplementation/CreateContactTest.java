
package contact.testngimplementation;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcat.crm.ObjectRepositaryUtility.ContactPage;
import com.comcat.crm.ObjectRepositaryUtility.ContactsInfoPage;
import com.comcat.crm.ObjectRepositaryUtility.CreatingNewContactPage;
import com.comcat.crm.ObjectRepositaryUtility.CreatingNewOrganization;
import com.comcat.crm.ObjectRepositaryUtility.HomePage;
import com.comcat.crm.ObjectRepositaryUtility.OrganizationInfoPage;
import com.comcat.crm.ObjectRepositaryUtility.OrganizationsPage;
/**
 * @author Shylu
 */
public class CreateContactTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createContactTest() throws EncryptedDocumentException, IOException {

		/* read testscript data*/
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jutil.getRandomNum();

		/* navigate to contact module*/
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		/* click on create contact button*/
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		/* enter all the details and create new contact*/

		CreatingNewContactPage cnp = new CreatingNewContactPage(driver);
		cnp.getLastnameEdt().sendKeys(lastName);
		cnp.getSaveBtn().click();
		/* verify Header message Expected result*/

		ContactsInfoPage cip = new ContactsInfoPage(driver);
		/* cip.getHeaderInfo(); */
		String headerInfo = cip.getHeaderInfo().getText();
		/* headerInfo is dynamic data */
		boolean status = headerInfo.contains(lastName);
		Assert.assertEquals(status, true);
		/*
		 * if (headerInfo.contains(lastName)) { System.out.println(lastName +
		 * "is created==pass"); } else { System.out.println(lastName +
		 * "is not created==Fail"); }
		 */
		/* verify LastName info Expected result*/

		String actLastName = cip.getActualLastName().getText();
		/* non mandatory info */
		SoftAssert assertobj = new SoftAssert();
		assertobj.assertEquals(actLastName, lastName);
		/*
		 * if (actLastName.equals(lastName)) { System.out.println(lastName +
		 * " information is verified==pass"); } else { System.out.println(lastName +
		 * " information is not verified==Fail"); }
		 */
		assertobj.assertAll();
		cip.getDelBtn().click();

		wlib.switchToAlertAndAccept(driver);

	}

	@Test(groups = "RegressionTest")
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException {

		String contactLastName = eLib.getDataFromExcel("contact", 7, 3) + jutil.getRandomNum();
		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jutil.getRandomNum();

		// navigate to organization module

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organization button

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNeworgBtn().click();
		// enter all the details and create new organization
		CreatingNewOrganization cno = new CreatingNewOrganization(driver);
		cno.getOrgNameEdt().sendKeys(orgName);

		cno.getSaveBtn().click();
		// verify Header message Expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);

		String headerInfo = oip.getHeaderMsg().getText();

		boolean status = headerInfo.contains(orgName);
		Assert.assertEquals(status, true);

		/*
		 * if (headerInfo.contains(orgName)) { System.out.println(orgName +
		 * " header is verified==pass"); } else { System.out.println(orgName +
		 * " header is not verified==Fail"); }
		 */

		// navigate to contact module

		hp.getContactLink().click();
		// click on create contact button

		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// enter all the details and create new contact

		CreatingNewContactPage cnp = new CreatingNewContactPage(driver);
		cnp.getLastnameEdt().sendKeys(contactLastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// search organization name in lookup(search)
		// orgName is dynamic data
		// switch to child window

		wlib.switchToTabOnURL(driver, "module=Accounts");

		cp.getSearchBox().sendKeys(orgName);

		cp.getSearchBtn().click();
		// Thread.sleep(2000);
		// dynamic xpath: xpath also getting in runtime
		// driver.findElement(By.xpath("//a[text()='SnapChat3']"));
		// snapchat3 is dynamic
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		wlib.switchToTabOnURL(driver, "Contacts&action");

		cnp.getSaveBtn().click();
		// verify Header message Expected result

		ContactsInfoPage cip = new ContactsInfoPage(driver);

		String headerInfo1 = cip.getHeaderInfo().getText();

		System.out.println(headerInfo1);
		boolean status1 = headerInfo.contains(contactLastName);
		Assert.assertEquals(status, true);
		/*
		 * if (headerInfo1.contains(contactLastName)) {
		 * System.out.println(contactLastName + " contact header is verified==pass"); }
		 * else { System.out.println(contactLastName +
		 * " contact header is not verified==Fail"); }
		 */

		// verify OgName info Expected result

		String actualOrgName = oip.getActOrgName().getText();
		System.out.println(actualOrgName);
		// Facebook... having space

		// trim is used to remove the space before and after the string
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualOrgName.trim(), orgName);
		/*
		 * if (actualOrgName.trim().equals(orgName)) { System.out.println(orgName +
		 * " info is created==pass"); } else { System.out.println(orgName +
		 * " info is not created==Fail"); }
		 */
		soft.assertAll();

	}

	@Test(groups = "RegressionTest")
	public void CreateContactWithSupportDateTest() throws EncryptedDocumentException, IOException {
		// read testscript
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jutil.getRandomNum();

		// navigate to contact module

		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		// click on create contact button

		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		String startDate = jutil.getSystemDateYYYYDDMM();
		String endDate = jutil.getRequiredDateYYYYMMDD(30);

		// enter all the details and create new contact

		CreatingNewContactPage cnp = new CreatingNewContactPage(driver);
		cnp.getLastnameEdt().sendKeys(lastName);
		// support start date

		cnp.getSupportStartDate().clear();

		cnp.getSupportStartDate().sendKeys(startDate);
		// support end date

		cnp.getSupportEndDate().clear();
		cnp.getSupportEndDate().sendKeys(endDate);

		cnp.getSaveBtn().click();

		ContactsInfoPage cip = new ContactsInfoPage(driver);

		String actStartDate = cip.getActSupportStartDate().getText();

		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actStartDate, startDate);

		/*
		 * if (actStartDate.equals(startDate)) { System.out.println(actStartDate +
		 * " information is verified");
		 * 
		 * } else { System.out.println(actStartDate + " information is not verified"); }
		 */

		String actEndDate = cip.getActSupportEndDate().getText();

		soft.assertEquals(actEndDate, endDate);
		/*
		 * if (actEndDate.equals(endDate)) { System.out.println(actEndDate +
		 * " information is verified");
		 * 
		 * } else { System.out.println(actEndDate + " information is not verified"); }
		 */
		cip.getDelBtn().click();
		wlib.switchToAlertAndAccept(driver);
		soft.assertAll();

	}

}
