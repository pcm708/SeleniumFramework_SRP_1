package com.qa.hubspot.keywords;

import static com.qa.automation.utils.DataReadWrite.getProperty;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.automation.getpageobjects.GetPage;
import com.qa.automation.utils.Credentials;

public class GooglePageAction extends GetPage{
	public By username = By.id("identifierId");
	public By password = By.xpath("//input[@name='password']");
	public By nxt_btn = By.xpath("//span[text()='Next']");
	Credentials cred;
	
	public GooglePageAction(WebDriver driver) {
		super(driver);
		cred = new Credentials(getProperty("./Config.properties", "username"),
				getProperty("./Config.properties", "password")); 
	}

	public DashboardPageAction signIn() {
		fillText(element(username),cred.getAdminUsername());
		click(element(nxt_btn));
		fillText(element(password),cred.getAdminPassword());
		click(element(nxt_btn));
		return new DashboardPageAction(driver);
	}
}
