package com.comcast.crm.listenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListImpClass implements ITestListener, ISuiteListener {
     //see video from -16:00
	//public ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		// Spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		// ExtentSparkReporter spark = new
		// ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add Env information and create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "windows 11");
		report.setSystemInfo("Browser", "Chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		System.out.println("====" + result.getMethod().getMethodName() + "====Start===");
		 test = report.createTest(result.getMethod().getMethodName());
		 UtilityClassObject.setTest(test);
		 test.log(Status.INFO, result.getMethod().getMethodName()+"==>STARTED<==");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("====" + result.getMethod().getMethodName() + "====END===");
	    test.log(Status.PASS, result.getMethod().getMethodName()+"==>completed<==");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();

		// create an object to event firing webdriver
		// to access driver we have to make webDriver in baseclass as static
		// public static webDriver driver=null; static variables will have only one
		// instance
		// static variable will not participate in parallel execution
		// create one more variable public WebDriver sdriver=null; after launching the
		// browser sdriver=driver;
		/*
		 * EventFiringWebDriver edriver = new EventFiringWebDriver(BaseClass.sdriver);
		 * // use getScreenshotAs method to get file type of screenshot File srcFile =
		 * edriver.getScreenshotAs(OutputType.FILE);
		 */
		TakesScreenshot edriver = (TakesScreenshot) BaseClass.sdriver;
		String filePath = edriver.getScreenshotAs(OutputType.BASE64);

		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		// store screenshot on local driver

		/*
		 * try { FileUtils.copyFile(srcFile, new File("./Screenshots/" + testName + "+"
		 * + time + ".png")); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		
		test.addScreenCaptureFromBase64String(filePath, testName+"_"+time);//if more than 1 failures
        test.log(Status.FAIL, result.getMethod().getMethodName()+"==>FAIlED<==");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

}
