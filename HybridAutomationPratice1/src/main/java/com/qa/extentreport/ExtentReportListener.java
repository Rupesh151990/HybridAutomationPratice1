package com.qa.extentreport;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.base.TestBase;

public class ExtentReportListener extends TestBase implements ITestListener {

	public static ExtentReports extentreport;
	public static ExtentTest extenttest;

	@Override
	public void onStart(ITestContext context) {
		extentreport = ExtentSparkReport.generateSparkReport();

	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extenttest = extentreport.createTest(testName);
		extenttest.log(Status.INFO, testName + "Start Execution");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extenttest.log(Status.INFO, result.getThrowable());
		extenttest.log(Status.PASS, testName + "Test Case Got Passed Successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String testname = result.getName();
		System.out.println("Taking ScreenSHot" + testname);
		/*
		 * WebDriver driver = null ; try { driver =
		 * (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").
		 * get(result.getInstance()); } catch (IllegalArgumentException |
		 * IllegalAccessException | NoSuchFieldException e) { e.printStackTrace(); }
		 */
		
		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshot\\"+testname+".png";

		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		extenttest.addScreenCaptureFromPath(destinationScreenshotPath);
		extenttest.log(Status.INFO, result.getThrowable());
		extenttest.log(Status.FAIL, testname + "test case got Fail");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		extentreport.flush();

		// For Opening the report automatically after completion of Execution
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentReport.html";
		File extentreportpath = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentreportpath.toURI());
		} catch (IOException e) {
			e.printStackTrace(); 
		}

	}

}
