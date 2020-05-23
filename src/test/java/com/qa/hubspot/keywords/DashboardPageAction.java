package com.qa.hubspot.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.automation.getpageobjects.GetPage;

public class DashboardPageAction extends GetPage{

	public By homeIcon = By.id("nav-primary-home");
	
	public DashboardPageAction(WebDriver driver) {
		super(driver);
	}
	
	public void clickOnHubspotIcon() {
		isElementDisplayed(element(homeIcon));
		click(element(homeIcon));
	}
}
