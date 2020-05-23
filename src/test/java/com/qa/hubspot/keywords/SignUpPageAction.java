package com.qa.hubspot.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import com.qa.automation.getpageobjects.GetPage;

public class SignUpPageAction extends GetPage{

	public By googleSignupLink = By.xpath("//button[contains(@class,'GoogleOAuthButton__GoogleUIButton')]");
	public By firstNameFiled = By.xpath("//input[@data-selenium=\"firstName\"]");
	public By lastNameField = By.xpath("//input[@data-selenium=\"firstName\"]");
	public By eMailField =By.xpath("//input[@data-selenium=\"email\"]");
	
	public SignUpPageAction(WebDriver driver) {
		super(driver);
	}

	public boolean verifySignUpPage() {
		boolean flag = false;
		wait.resetImplicitTimeout(5);
		try {
		isElementDisplayed(element(googleSignupLink));
		isElementDisplayed(element(firstNameFiled));
		isElementDisplayed(element(lastNameField));
		isElementDisplayed(element(eMailField));
		flag= true;
		wait.resetImplicitTimeout(wait.getTimeout());
		return flag;
	}catch(NoSuchElementException e) {
		wait.resetImplicitTimeout(wait.getTimeout());
		return flag;
		}
	}
}