package com.qa.extentreport;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.base.TestBase;

public class ExtentSparkReport extends TestBase {

	public static ExtentReports generateSparkReport() {

		ExtentReports extentReport = new ExtentReports();
		File extentReportPath = new File(
				System.getProperty("user.dir") + "//test-output//ExtentReport//extentReport.html");
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(extentReportPath);

		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setReportName("Automation CRM Test");
		sparkReport.config().setTimeStampFormat("dd/mm/yyy hh:mm:ss");

		extentReport.attachReporter(sparkReport);
		extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
		extentReport.setSystemInfo("Browser ", prop.getProperty("browser"));
		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));

		return extentReport;
	}

}
