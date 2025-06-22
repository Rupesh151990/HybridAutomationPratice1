package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class DashBoard_Page extends TestBase {

	public DashBoard_Page() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'Start Here...')]")
	WebElement startHere;

	public Login_Page loginLink() {
		startHere.click();
		return new Login_Page();
	}

}
