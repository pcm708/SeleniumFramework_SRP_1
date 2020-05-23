package com.qa.hubspot.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.automation.getpageobjects.GetPage;

public class LoginPageAction extends GetPage {

	public By googleSSO = By.className("buttonText");
	public By signUpLink = By.xpath("//a//*[contains(text(),'Sign up')]");

	public LoginPageAction(WebDriver driver) {
		super(driver);
	}
	
	public GooglePageAction click_signInWithGoogle() {
		isElementDisplayed(element(googleSSO));
		click(element(googleSSO));
		return new GooglePageAction(driver);
	}
	
	public SignUpPageAction click_signUpLink() {
		isElementDisplayed(element(signUpLink));
		click(element(signUpLink));
		return new SignUpPageAction(driver);
	}
}