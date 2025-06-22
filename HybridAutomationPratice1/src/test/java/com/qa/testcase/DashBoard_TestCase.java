package com.qa.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pages.DashBoard_Page;
import com.qa.base.TestBase;
//import com.qa.base.TestBase;

public class DashBoard_TestCase extends TestBase {
	
	public DashBoard_Page dbpage;
	
	
	
	
	public DashBoard_TestCase() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();	
		
	}
	
	@Test
	public void dashboardPageTest() {
		dbpage = new DashBoard_Page();
	
		dbpage.loginLink();
		
		
				
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
