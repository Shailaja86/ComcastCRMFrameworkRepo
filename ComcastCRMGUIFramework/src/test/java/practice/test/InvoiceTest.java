package practice.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

import junit.framework.Assert;

//@Listeners(com.comcast.crm.listenerUtility.ListImpClass.class)
//no need write @listeners we have to add @listeners tag in suite xml file see listener.xml
public class InvoiceTest extends BaseClass {

	/*
	 * @Test(retryAnalyzer=com.comcast.crm.listenerUtility.RetryListenerImp.class)
	 * public void activateSim() { System.out.println("execute createInvoiceTest");
	 * Assert.assertEquals("", "Login"); System.out.println("step1");
	 * System.out.println("step2"); System.out.println("step3");
	 * System.out.println("step4");
	 * 
	 * }
	 */

	
	  @Test 
	  public void createInvoiceTest() {
	  System.out.println("execute createInvoiceTest"); 
	  //failing intentionally we will get assertError Exception 
	  String acttitle=driver.getTitle();
	 Assert.assertEquals(acttitle, "Login");
	  
	  //create invoice steps System.out.println("step1");
	  System.out.println("step2"); System.out.println("step3");
	  System.out.println("step4");
	  
	  }
	  
	  @Test
	  public void createInvoiceContactTest() {
	  System.out.println("execute createInvoicewithContactTest");
	  System.out.println("step1"); System.out.println("step2");
	  System.out.println("step3"); System.out.println("step4");
	  
	  }
	 
}
